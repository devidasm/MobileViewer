package com.imebra.dicom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.*;
import android.os.Handler;
import android.view.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Display a Dicom image, allowing the user to zoom in/out and pan.
 *
 * The transforms specified in TransformsChain are applied to the image before it is displayed on the screen.
 * The color space transform is applied automatically by the view so it doesn't have to be added to the TransformsChain.
 *
 * The view renders the image asynchronously, by calculating the tiles to be displayed in a separate thread.
 *
 */
public class DicomView extends View
{

    private Paint m_backgroundPaint = null;

    private float m_zoomScale = 1.0f;
    private float m_centerPointX = 0.5f;
    private float m_centerPointY = 0.5f;

    private int m_totalWidth;    ///< total width of the image (after zoom in/out). Calculated by updateCoordinates()
    private int m_totalHeight;   ///< total height of the image (after zoom in/out). Calculated by updateCoordinates()
    private int m_topLeftX;      ///< X coordinate of the first visible top left pixel (after zoom in/out). Calculated by updateCoordinates()
    private int m_topLeftY;      ///< Y coordinate of the first visible top left pixel (after zoom in/out). Calculated by updateCoordinates()
    private int m_bottomRightX;  ///< X coordinate of the first non-visible bottom-right pixel (after zoom in/out). Calculated by updateCoordinates()
    private int m_bottomRightY;  ///< Y coordinate of the first non-visible bottom-right pixel (after zoom in/out). Calculated by updateCoordinates()
    private int m_bitmapOffsetX; ///< The offset to apply to the X coordinate before displaying the bitmap (used for scrolling)
    private int m_bitmapOffsetY; ///< The offset to apply to the Y coordinate before displaying the bitmap (used for scrolling)

    private static int m_tileSizeX = 256; ///< The width of the tiles used to render the image
    private static int m_tileSizeY = 256; ///< The height of the tiles used to render the image

    private TransformsChain m_transformsChain; ///< The transforms chain to apply before rendering the image
    private Image m_image;                     ///< The image to be rendered
    private DrawBitmap m_drawBitmap;           ///< The DrawBitmap object that renders the image
    private long m_drawBitmapCounter = 0;      ///< Every time drawBitmap changes or the transforms chain changes then this counter increases

    private Bitmap m_fullBitmap;               ///< Full bitmap displayed while the tiles are being rendered
    private long m_fullDrawBitmapCounter = 0;  ///< The m_drawBitmapCounter value at the moment the full bitmap was rendered

    final private List<RenderTileRequest> m_renderTileRequests = new ArrayList<RenderTileRequest>(); ///< List of requests for rendering tiles
    RenderTileRequest m_fullImageRenderRequest = null; ///< request to render the full image
    private long m_renderFullDrawBitmapCounter = 0;    ///< value of m_drawBitmapCounter when the request to render the full image was filed

    private boolean m_bFirstDraw; ///< true if the view has just been initialized

    private List<TileInfo> m_tiles = new ArrayList<TileInfo>(); ///< List of rendered tiles

    boolean m_bTerminate = false; ///< true if the rendering threads must terminate

    private ScaleGestureDetector m_scaleGestureDetector; ///< The gesture detector for scaling the image (zoom)
    private GestureDetector m_scrollGestureDetector;     ///< The gesture detector for panning the image (scroll)

    private boolean m_bScaleEnabled = true;
    private boolean m_bPanEnabled = true;

    private Thread m_renderTilesThread = null;


    /**
     * onDrawOverlay() is called after the image has been rendered in order to draw an overlay over it.
     */
    public interface DrawOverlayListener
    {
        /**
         * Called after the image has been rendered to draw an overlay over it.
         *
         * When this method is called the canvas is already translated according to the current scroll.
         *
         * @param canvas      the canvas on which the overlay will be drawn
         * @param totalWidth  the total with of the image already rendered on the canvas
         * @param totalHeight the total height of the image already rendered on the canvas
         * @param image       the image rendered on the canvas
         */
        void onDrawOverlay(Canvas canvas, int totalWidth, int totalHeight, Image image);
    }
    DrawOverlayListener m_drawOverlayListener = null;


    /**
     * onDrawFixedOverlay() is called after the image has been rendered in order to draw a fixed overlay (not dependent
     *  on scrolling or scaling) over it
     */
    public interface DrawFixedOverlayListener
    {
        void onDrawFixedOverlay(Canvas canvas);
    }
    DrawFixedOverlayListener m_drawFixedOverlayListener = null;


    /**
     * Simple constructor to use when creating a view from code.
     *
     * @param context the Context the view is running in, through which it can access the current theme, resources, etc.
     *
     */
    public DicomView (android.content.Context context)
    {
        super(context);
        initializeParameters(context, null);
    }


    /**
     * Constructor that is called when inflating a view from XML. This is called when a view is being constructed from
     *  an XML file, supplying attributes that were specified in the XML file.
     * The method onFinishInflate() will be called after all children have been added.
     *
     * @param context the Context the view is running in, through which it can access the current theme, resources, etc.
     * @param attrs   the attributes of the XML tag that is inflating the view
     */
    public DicomView (android.content.Context context, android.util.AttributeSet attrs)
    {
        super(context, attrs);
        initializeParameters(context, attrs);
    }


    /**
     * Perform inflation from XML and apply a class-specific base style.
     *
     * @param context the Context the view is running in, through which it can access the current theme, resources, etc.
     * @param attrs   the attributes of the XML tag that is inflating the view
     * @param defStyle the default style to apply to this view. If 0, no style will be applied
     */
    public DicomView (android.content.Context context, android.util.AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
        initializeParameters(context, attrs);
    }


    /**
     * Set a listener that draws an overlay over the image.
     *
     * @param listener the listener that will draw an overlay over the image
     */
    public void setDrawOverlayListener(DrawOverlayListener listener)
    {
        m_drawOverlayListener = listener;
    }


    /**
     * Set a listener that draws a fixed overlay over the image.
     *
     * @param listener the listener that will draw a fixed overlay over the image
     */
    public void setDrawFixedOverlayListener(DrawFixedOverlayListener listener)
    {
        m_drawFixedOverlayListener = listener;
    }


    /**
     * Set the image and the chain of transforms to apply to the image before displaying it.
     *
     * The color transform is applied automatically by the view and therefore doesn't need to be added to the
     *  TransformsChain object.
     *
     * @param image      the image to be displayed
     * @param transforms the chain of transforms to be applied to the image before it is rendered.
     *                   The color transform to RGB is not necessary since it is performed automatically by the view.
     */
    public void setImage(Image image, TransformsChain transforms)
    {
        if(m_image == null || image.getSizeY() != m_image.getSizeY() || image.getSizeX() != m_image.getSizeX())
        {
            m_zoomScale = 1.0f;
        }
        m_image = image;
        m_transformsChain = transforms;
        m_drawBitmap = new DrawBitmap(image, transforms);
        m_drawBitmapCounter++;
        renderTiles();
    }


    /**
     * Enable or disable the detection of the scaling gesture.
     *
     * When the scaling gesture is enabled (default) then the user can zoom in or out on the displayed image.
     *
     * @param bEnabled true to enable the detection of the scaling gesture, false to disable it
     *
     */
    public void enableScaling(boolean bEnabled)
    {
        m_bScaleEnabled = bEnabled;
    }


    /**
     * Enable or disable the detection of the panning gesture.
     *
     * When the panning gesture is enabled (default) then the user can scroll the displayed image.
     *
     * @param bEnabled true to enable the detection of the panning gesture, false to disable it
     *
     */
    public void enablePanning(boolean bEnabled)
    {
        m_bPanEnabled = bEnabled;
    }


    /**
     * Called when the view is attached to a window. The rendering threads are started
     *
     */
    protected void onAttachedToWindow()
    {
        m_bFirstDraw = true;

        if(m_renderTilesThread == null)
        {
            m_renderTilesThread = new Thread(new RenderImage());
            m_renderTilesThread.start();
        }
    }


    /**
     * Called when the view is detached from a window. The rendering threads are stopped.
     */
    protected void onDetachedFromWindow()
    {
        synchronized (m_renderTileRequests)
        {
            m_bTerminate = true;
            m_renderTileRequests.clear();
            m_renderTileRequests.notifyAll();
        }
        m_tiles.clear();
        try {
            m_renderTilesThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        m_renderTilesThread = null;
    }


    /**
     * Initializes the class.
     *
     * @param context
     * @param attrs
     */
    protected void initializeParameters(android.content.Context context, android.util.AttributeSet attrs)
    {
        DicomViewGestureDetector gestureListener = new DicomViewGestureDetector();
        m_scaleGestureDetector = new ScaleGestureDetector(context, gestureListener);
        m_scrollGestureDetector = new GestureDetector(context, gestureListener);

        if(m_backgroundPaint == null)
        {
            TypedArray privateAttributes = context.getTheme().obtainStyledAttributes(attrs, com.imebra.R.styleable.DicomView, 0, 0);

            int backgroundColor = privateAttributes == null ? 0xff000000 : privateAttributes.getColor(com.imebra.R.styleable.DicomView_backgroundColor, 0xff000000) & 0xffffffff;

            m_backgroundPaint = new Paint();
            m_backgroundPaint.setColor(backgroundColor);
            m_backgroundPaint.setStyle(Paint.Style.FILL);
            m_backgroundPaint.setStrokeWidth(0);
        }
    }


    /**
     * When a touch event arrives forward it to the gesture detectors for scaling or zooming
     *
     * @param event
     * @return
     */
    public boolean onTouchEvent (MotionEvent event)
    {
        return (m_bPanEnabled && m_scrollGestureDetector.onTouchEvent(event)) | (m_bScaleEnabled && m_scaleGestureDetector.onTouchEvent(event));
    }


    /**
     * Draw all the tiles
     *
     * @param canvas
     */
    protected void onDraw (Canvas canvas)
    {
        if(m_bFirstDraw)
        {
            renderTiles();
            m_bFirstDraw = false;
        }

        // Paint everything black
        RectF paintBackground = new RectF(0, 0, canvas.getWidth(), canvas.getHeight());
        //canvas.drawRect(paintBackground, m_backgroundPaint);

        canvas.translate(m_bitmapOffsetX - m_topLeftX, m_bitmapOffsetY - m_topLeftY);

        updateCoordinates();

        // Draw full bitmap
        ///////////////////
        if(m_fullDrawBitmapCounter != m_drawBitmapCounter)
        {
            m_fullBitmap = null;
        }
        if(m_fullBitmap != null)
        {
            canvas.drawBitmap(m_fullBitmap, null, new Rect(0, 0, m_totalWidth, m_totalHeight), null);
        }

        // List of tiles to remove
        //////////////////////////
        List<TileInfo> tilesToRemove = new ArrayList<TileInfo>();
        List<TileInfo> differentSizeTiles = new ArrayList<TileInfo>();

        // Number of tiles required for the whole area
        //////////////////////////////////////////////
        int numTiles = 0;
        for(int scanY = 0; scanY < m_totalHeight; scanY += m_tileSizeY)
        {
            if(scanY >= m_bottomRightY || scanY + m_tileSizeY <= m_topLeftY)
            {
                continue;
            }
            for(int scanX = 0; scanX < m_totalWidth; scanX += m_tileSizeX)
            {
                if(scanX < m_bottomRightX && scanX + m_tileSizeX > m_topLeftX)
                {
                    ++numTiles;
                }

            }
        }

        int numCorrectTiles = 0;
        for(TileInfo tile: m_tiles)
        {
            Rect displayPosition = findTileDisplayPosition(tile);

            if(displayPosition.right <= m_topLeftX ||
                    displayPosition.bottom <= m_topLeftY ||
                    displayPosition.left >= m_bottomRightX ||
                    displayPosition.top >= m_bottomRightY ||
                    tile.drawBitmapCounter != m_drawBitmapCounter)
            {
                tilesToRemove.add(tile);
                continue;
            }

            if(tile.totalWidth != m_totalWidth || tile.totalHeight != m_totalHeight)
            {
                differentSizeTiles.add(tile);
            }
            else if(tile.topLeftX < m_bottomRightX &&
                    tile.topLeftY < m_bottomRightY &&
                    tile.bottomRightX > m_topLeftX &&
                    tile.bottomRightY > m_topLeftY)
            {
                numCorrectTiles++;
            }

            canvas.drawBitmap(tile.bitmap, null, displayPosition, null);
        }

        m_tiles.removeAll(tilesToRemove);
        if(numCorrectTiles == numTiles)
        {
            m_tiles.removeAll(differentSizeTiles);
        }

        if(m_drawOverlayListener != null)
        {
            m_drawOverlayListener.onDrawOverlay(canvas, m_totalWidth, m_totalHeight, m_image);
        }

        canvas.translate(m_topLeftX - m_bitmapOffsetX, m_topLeftY - m_bitmapOffsetY);

        if(m_drawFixedOverlayListener != null)
        {
            m_drawFixedOverlayListener.onDrawFixedOverlay(canvas);
        }
    }


    /**
     * Called to display an overlay over the rendered image.
     *
     * The canvas is already translated according to the current scrolling settings.
     *
     * @param canvas      the canvas on which the overlay will be drawn
     * @param totalWidth  the total width of the image displayed on the canvas
     * @param totalHeight the total height of the image displayed on the canvas
     * @param image       the image displayed on the canvas
     */
    protected void onDrawOverlay(Canvas canvas, int totalWidth, int totalHeight, Image image)
    {

    }


    /**
     * Set the requested size
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    protected void onMeasure (int widthMeasureSpec, int heightMeasureSpec)
    {
        int reqWidth = View.MeasureSpec.getSize(widthMeasureSpec);
        int reqHeight = View.MeasureSpec.getSize(heightMeasureSpec);

        int minimumWidth = getSuggestedMinimumWidth();
        int minimumHeight = getSuggestedMinimumHeight();
        if(reqWidth < minimumWidth)
        {
            reqWidth = minimumWidth;
        }
        if(reqHeight < minimumHeight)
        {
            reqHeight = minimumHeight;
        }

        setMeasuredDimension(reqWidth, reqHeight);
    }


    /**
     * Queue a request to create all the tiles necessary to fill the screen
     *
     */
    protected void renderTiles()
    {
        synchronized (m_renderTileRequests)
        {
            if(m_image == null || m_image.getSizeX() == 0 || m_image.getSizeY() == 0)
            {
                m_tiles.clear();
                return;
            }

            updateCoordinates();

            if(m_fullDrawBitmapCounter != m_drawBitmapCounter)
            {
                m_fullBitmap = null;
            }

            // Remove the previous requests but the one for full image
            //////////////////////////////////////////////////////////
            m_renderTileRequests.clear();

            if(m_fullBitmap == null && m_renderFullDrawBitmapCounter != m_drawBitmapCounter)
            {
                WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
                Display display = wm.getDefaultDisplay();
                int displaySize = StrictMath.max(display.getWidth(), display.getHeight());
                int imageWidth = m_image.getSizeX();
                int imageHeight = m_image.getSizeY();
                float imageRatio = (float)imageWidth / (float)imageHeight;
                int fullSizeWidth;
                int fullSizeHeight;
                if(imageHeight < imageWidth)
                {
                    fullSizeHeight = displaySize;
                    fullSizeWidth = (int)(fullSizeHeight * imageRatio + .5f);
                }
                else
                {
                    fullSizeWidth = displaySize;
                    fullSizeHeight = (int)(fullSizeWidth / imageRatio + .5f);
                }

                m_fullImageRenderRequest = new RenderTileRequest(m_drawBitmap, m_drawBitmapCounter, fullSizeWidth, fullSizeHeight, 0, 0, fullSizeWidth, fullSizeHeight, true);
                m_renderFullDrawBitmapCounter = m_drawBitmapCounter;
            }

            for(int scanY = 0; scanY < m_totalHeight; scanY += m_tileSizeY)
            {
                if(scanY >= m_bottomRightY || scanY + m_tileSizeY <= m_topLeftY)
                {
                    continue;
                }
                int bottomRightY = StrictMath.min(m_totalHeight, scanY + m_tileSizeY);

                for(int scanX = 0; scanX < m_totalWidth; scanX += m_tileSizeX)
                {
                    if(scanX >= m_bottomRightX || scanX + m_tileSizeX <= m_topLeftX)
                    {
                        continue;
                    }

                    int bottomRightX = StrictMath.min(m_totalWidth, scanX + m_tileSizeX);

                    // Check if the requested tile already exists
                    /////////////////////////////////////////////
                    boolean bFound = false;
                    for(TileInfo tile: m_tiles)
                    {
                        if(tile.totalWidth == m_totalWidth &&
                                tile.totalHeight == m_totalHeight &&
                                tile.topLeftX == scanX &&
                                tile.topLeftY == scanY &&
                                tile.bottomRightX == bottomRightX &&
                                tile.bottomRightY == bottomRightY &&
                                tile.drawBitmapCounter == m_drawBitmapCounter)
                        {
                            bFound = true;
                            break;
                        }
                    }
                    if(bFound)
                    {
                        continue;
                    }

                    m_renderTileRequests.add(new RenderTileRequest(m_drawBitmap, m_drawBitmapCounter, m_totalWidth, m_totalHeight, scanX, scanY, bottomRightX, bottomRightY, false));
                }
            }
            m_renderTileRequests.notifyAll();
        }

    }


    /**                                                                   m_
     * Called when a bitmap is ready to be rendered.
     *
     * @param totalWidth
     * @param totalHeight
     * @param topLeftX
     * @param topLeftY
     * @param bottomRightX
     * @param bottomRightY
     * @param bitmap
     */
    private void renderImageReady(long drawBitmapCounter, int totalWidth, int totalHeight, int topLeftX, int topLeftY, int bottomRightX, int bottomRightY, Bitmap bitmap, boolean bFullBitmap)
    {
        if(drawBitmapCounter != m_drawBitmapCounter)
        {
            return;
        }
        if(bFullBitmap)
        {
            if(bitmap != m_fullBitmap)
            {
                m_fullBitmap = bitmap;
                m_fullDrawBitmapCounter = drawBitmapCounter;
                invalidate();
            }
            return;
        }
        if(totalWidth != m_totalWidth || totalHeight != m_totalHeight)
        {
            invalidate();
            return;
        }

        // Check if the tile already exists
        ///////////////////////////////////
        TileInfo tile = null;
        for(TileInfo tileInfo: m_tiles)
        {
            if(tileInfo.topLeftX == topLeftX &&
                    tileInfo.topLeftY == topLeftY &&
                    tileInfo.bottomRightX == bottomRightX &&
                    tileInfo.bottomRightY == bottomRightY &&
                    tileInfo.totalWidth == totalWidth &&
                    tileInfo.totalHeight == totalHeight &&
                    tileInfo.drawBitmapCounter == drawBitmapCounter)
            {
                tile = tileInfo;
                break;
            }
        }
        if(tile == null)
        {
            tile = new TileInfo(drawBitmapCounter, totalWidth, totalHeight, topLeftX, topLeftY, bottomRightX, bottomRightY, bitmap);
            m_tiles.add(tile);
        }

        Rect invalidateRect = findTileDisplayPosition(tile);
        invalidateRect.offset(m_bitmapOffsetX - m_topLeftX, m_bitmapOffsetY - m_topLeftY);
        invalidate(invalidateRect);
    }


    /**
     * This runnable runs on a separate thread and render the requested tiles
     *
     */
    private class RenderImage implements Runnable
    {
        private int m_renderBuffer[] = new int[4096];

        public void run()
        {
            Handler looperHandler = new Handler(DicomView.this.getContext().getMainLooper());

            // Loop until an exit request arrives
            /////////////////////////////////////
            for(;;)
            {
                // Get the next render request
                //////////////////////////////
                RenderTileRequest renderRequest;
                synchronized (m_renderTileRequests)
                {
                    if(!m_bTerminate && m_renderTileRequests.isEmpty() && m_fullImageRenderRequest == null)
                    {
                        try {
                            m_renderTileRequests.wait();
                        }
                        catch(InterruptedException e)
                        {

                        }
                    }
                    if(m_bTerminate)
                    {
                        return;
                    }
                    renderRequest = m_fullImageRenderRequest;
                    m_fullImageRenderRequest = null;
                    if(renderRequest == null && !m_renderTileRequests.isEmpty())
                    {
                        renderRequest = m_renderTileRequests.remove(0);
                    }
                    if(renderRequest == null)
                    {
                        continue;
                    }
                }
                int requiredSize = renderRequest.drawBitmap.getBitmap(renderRequest.totalWidth,
                        renderRequest.totalHeight,
                        renderRequest.topLeftX,
                        renderRequest.topLeftY,
                        renderRequest.bottomRightX,
                        renderRequest.bottomRightY, m_renderBuffer, 0);
                if(requiredSize == 0)
                {
                    continue;
                }
                if(m_renderBuffer.length < requiredSize)
                {
                    m_renderBuffer = new int[requiredSize];
                }
                renderRequest.drawBitmap.getBitmap(renderRequest.totalWidth,
                        renderRequest.totalHeight,
                        renderRequest.topLeftX,
                        renderRequest.topLeftY,
                        renderRequest.bottomRightX,
                        renderRequest.bottomRightY, m_renderBuffer, m_renderBuffer.length);

                Bitmap renderBitmap = Bitmap.createBitmap(m_renderBuffer, renderRequest.bottomRightX - renderRequest.topLeftX, renderRequest.bottomRightY - renderRequest.topLeftY, Bitmap.Config.ARGB_8888);
                if(renderBitmap == null)
                {
                    return;
                }
                looperHandler.post(new CallRenderImageReady(renderRequest.drawBitmapCounter,
                        renderRequest.totalWidth,
                        renderRequest.totalHeight,
                        renderRequest.topLeftX,
                        renderRequest.topLeftY,
                        renderRequest.bottomRightX,
                        renderRequest.bottomRightY,
                        renderBitmap,
                        renderRequest.bFullBitmap));
            }
        }
    }


    /**
     * This runnable is called on the main thread to signal the availability of a rendered tile
     *
     */
    private class CallRenderImageReady implements Runnable
    {
        private int m_totalWidth, m_totalHeight, m_topLeftX, m_topLeftY, m_bottomRightX, m_bottomRightY;
        private long m_drawBitmapCounter;
        private Bitmap m_bitmap;
        private boolean m_bFullBitmap;

        public CallRenderImageReady(long drawBitmapCounter, int totalWidth, int totalHeight, int topLeftX, int topLeftY, int bottomRightX, int bottomRightY, Bitmap bitmap, boolean bFullBitmap)
        {
            m_drawBitmapCounter = drawBitmapCounter;
            m_totalWidth = totalWidth;
            m_totalHeight = totalHeight;
            m_topLeftX = topLeftX;
            m_topLeftY = topLeftY;
            m_bottomRightX = bottomRightX;
            m_bottomRightY = bottomRightY;
            m_bitmap = bitmap;
            m_bFullBitmap = bFullBitmap;
        }

        public void run()
        {
            DicomView.this.renderImageReady(m_drawBitmapCounter, m_totalWidth, m_totalHeight, m_topLeftX, m_topLeftY, m_bottomRightX, m_bottomRightY, m_bitmap, m_bFullBitmap);
        }
    }


    /**
     * Find the display position for a tile. The position doesn't take into account the offset of the bitmap.
     *
     * @param tile the tile for which the position is required
     * @return the position of the tile
     */
    private Rect findTileDisplayPosition(TileInfo tile)
    {
        int topLeftX = tile.topLeftX;
        int topLeftY = tile.topLeftY;
        int bottomRightX = tile.bottomRightX;
        int bottomRightY = tile.bottomRightY;

        if(tile.totalWidth != m_totalWidth || tile.totalHeight != m_totalHeight)
        {
            float ratioSizeX = (float)m_totalWidth / (float)tile.totalWidth;
            float ratioSizeY = (float)m_totalHeight / (float)tile.totalHeight;
            topLeftX = (int)((float)topLeftX * ratioSizeX + 0.5f);
            topLeftY = (int)((float)topLeftY * ratioSizeY + 0.5f);
            bottomRightX = (int)((float)bottomRightX * ratioSizeX + 0.5f);
            bottomRightY = (int)((float)bottomRightY * ratioSizeY + 0.5f);
        }

        return new Rect(topLeftX, topLeftY, bottomRightX, bottomRightY);

    }


    /**
     * Using the values m_zoomScale, m_centerPointX and m_centerPointY calculates the following values:
     * - m_bitmapOffsetX
     * - m_bitmapOffsetY
     * - m_totalWidth
     * - m_totalHeight
     * - m_topLeftX
     * - m_topLeftY
     * - m_bottomRightX
     * - m_bottomRightY
     *
     */
    private void updateCoordinates()
    {
        m_bitmapOffsetX = 0;
        m_bitmapOffsetY = 0;

        int imageWidth = m_image.getSizeX();
        int imageHeight = m_image.getSizeY();
        float imageRatio = (float)imageWidth / (float)imageHeight;

        int viewWidth = getWidth();
        int viewHeight = getHeight();
        float viewRatio = (float)viewWidth/(float)viewHeight;

        float baseImageWidth, baseImageHeight; // bitmap size with zoom = 1
        if(imageRatio > viewRatio)
        {
            baseImageWidth = viewWidth;
            baseImageHeight = (float)viewWidth / imageRatio;
        }
        else
        {
            baseImageHeight = viewHeight;
            baseImageWidth = (float)viewHeight * imageRatio;
        }

        baseImageWidth *= m_zoomScale;
        baseImageHeight *= m_zoomScale;

        m_totalWidth = (int)(baseImageWidth + .5f);
        m_totalHeight = (int)(baseImageHeight + .5f);

        // Find the horizontal coordinates
        //////////////////////////////////
        if(viewWidth > m_totalWidth)
        {
            m_bitmapOffsetX = (viewWidth - m_totalWidth) / 2;
            m_centerPointX= 0.5f;
            m_topLeftX = 0;
            m_bottomRightX = m_totalWidth;
        }
        else
        {
            float centerPointX = baseImageWidth * m_centerPointX;
            m_topLeftX = (int)(centerPointX + .5f) - viewWidth / 2;
            if(m_topLeftX < 0)
            {
                m_topLeftX = 0;
                m_centerPointX = 0.5f* (float)viewWidth / (float)m_totalWidth;
            }
            m_bottomRightX = m_topLeftX + viewWidth;
            if(m_bottomRightX > m_totalWidth)
            {
                m_bottomRightX = m_totalWidth;
                m_topLeftX = m_bottomRightX - viewWidth;
                m_centerPointX = 1f - 0.5f* (float)viewWidth / (float)m_totalWidth;
            }
        }

        // Find the vertical coordinates
        ////////////////////////////////
        if(viewHeight > m_totalHeight)
        {
            m_bitmapOffsetY = (viewHeight - m_totalHeight) / 2;
            m_centerPointY= 0.5f;
            m_topLeftY = 0;
            m_bottomRightY = m_totalHeight;
        }
        else
        {
            float centerPointY = baseImageHeight * m_centerPointY;
            m_topLeftY = (int)(centerPointY + .5f) - viewHeight / 2;

            if(m_topLeftY < 0)
            {
                m_topLeftY = 0;
                m_centerPointY = 0.5f* (float)viewHeight / (float)m_totalHeight;
            }
            m_bottomRightY = m_topLeftY + viewHeight;
            if(m_bottomRightY > m_totalHeight)
            {
                m_bottomRightY = m_totalHeight;
                m_topLeftY = m_bottomRightY - viewHeight;
                m_centerPointY = 1f - 0.5f* (float)viewHeight / (float)m_totalHeight;
            }
        }
    }


    /**
     * Contains the details of a rendered tile or full bitmap.
     */
    private class TileInfo
    {
        public TileInfo(long drawBitmapCounter, int totalWidth, int totalHeight, int topLeftX, int topLeftY, int bottomRightX, int bottomRightY, Bitmap renderedBitmap)
        {
            this.drawBitmapCounter = drawBitmapCounter;
            this.totalWidth = totalWidth;
            this.totalHeight = totalHeight;
            this.topLeftX = topLeftX;
            this.topLeftY = topLeftY;
            this.bottomRightX = bottomRightX;
            this.bottomRightY = bottomRightY;
            this.bitmap = renderedBitmap;
        }

        public int totalWidth, totalHeight, topLeftX, topLeftY, bottomRightX, bottomRightY;
        public long drawBitmapCounter;
        public Bitmap bitmap;
    }


    /**
     * Contains a request for rendering a single tile of the image.
     */
    private class RenderTileRequest
    {
        public DrawBitmap drawBitmap;
        public long drawBitmapCounter;
        public int totalWidth, totalHeight, topLeftX, topLeftY, bottomRightX, bottomRightY;
        public boolean bFullBitmap;

        /**
         * Constructor
         *
         * @param drawBitmap        the DrawBitmap object to use to render the tile
         * @param drawBitmapCounter the current value of m_drawBitmapCounter
         * @param totalWidth        the total width of the whole bitmap that contains the tile
         * @param totalHeight       the total height of the whole bitmap that contains the tile
         * @param topLeftX          the top left X coordinate of the tile
         * @param topLeftY          the top left Y coordinate of the tile
         * @param bottomRightX      the bottom right X coordinate of the tile
         * @param bottomRightY      the bottom right Y coordinate of the tile
         * @param bFullBitmap       true if the whole bitmap is being rendered
         */
        public RenderTileRequest(DrawBitmap drawBitmap, long drawBitmapCounter, int totalWidth, int totalHeight, int topLeftX, int topLeftY, int bottomRightX, int bottomRightY, boolean bFullBitmap)
        {
            this.drawBitmap = drawBitmap;
            this.drawBitmapCounter = drawBitmapCounter;
            this.totalWidth = totalWidth;
            this.totalHeight = totalHeight;
            this.topLeftX = topLeftX;
            this.topLeftY = topLeftY;
            this.bottomRightX = bottomRightX;
            this.bottomRightY = bottomRightY;
            this.bFullBitmap = bFullBitmap;
        }
    }


    /**
     * Intercepts the gestures.
     */
    private class DicomViewGestureDetector implements ScaleGestureDetector.OnScaleGestureListener, GestureDetector.OnGestureListener
    {
        public boolean onDown(MotionEvent e)
        {
            return true;
        }

        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY)
        {
            return false;
        }

        public void onLongPress(MotionEvent e)
        {
        }

        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY)
        {
            if(m_totalWidth == 0 || m_totalHeight == 0)
            {
                return false;
            }
            // Convert pixels to float 0...1

            float unifiedDistanceX = distanceX / (float)m_totalWidth;
            float unifiedDistanceY = distanceY / (float)m_totalWidth;
            m_centerPointX += unifiedDistanceX;
            m_centerPointY += unifiedDistanceY;
            renderTiles();
            invalidate();
            return true;
        }

        public void onShowPress(MotionEvent e)
        {
        }

        public boolean onSingleTapUp(MotionEvent e)
        {
            return true;
        }

        public boolean onScaleBegin(ScaleGestureDetector detector)
        {
            return m_drawBitmap != null;
        }

        public boolean onScale(ScaleGestureDetector detector) {
            m_zoomScale *= detector.getScaleFactor();

            // Don't let the object get too small or too large.
            m_zoomScale = Math.max(1.0f, Math.min(m_zoomScale, 20.0f));

            updateCoordinates();

            invalidate();
            return true;
        }

        public void onScaleEnd(ScaleGestureDetector detector)
        {
            renderTiles();

        }


    }


}
