package com.example.testapp;

import java.nio.IntBuffer;

import android.content.Context;
import android.opengl.GLES30;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;

public class MyGLSurfaceView extends GLSurfaceView{

	private final MyGLRenderer mRenderer;
	
	public MyGLSurfaceView(Context context) {
        super(context);

        // Create an OpenGL ES 2.0 context.
        setEGLContextClientVersion(3);
        
        // Set the Renderer for drawing on the GLSurfaceView
        mRenderer = new MyGLRenderer(context);
        setRenderer(mRenderer);

        // Render the view only when there is a change in the drawing data
        setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
	}
	
    private final float TOUCH_SCALE_FACTOR = 10f;
    private float mPreviousX;
    private float mPreviousY;
    private Point prevPoint = new Point();
    private boolean firstTime = true;
	@Override
    public boolean onTouchEvent(MotionEvent e) {
        // MotionEvent reports input details from the touch screen
        // and other input controls. In this case, you are only
        // interested in events where the touch position changed.

        float x = e.getX();
        float y = e.getY();

        switch (e.getAction()) {
            case MotionEvent.ACTION_MOVE:

            	Point currPoint = trackBallmapping(x, y);
            	
            	if(firstTime)
            	{
            		firstTime = false;
            		prevPoint.x = currPoint.x;
            		prevPoint.y = currPoint.y;
            		prevPoint.z = currPoint.z;
            	}
            	     
                //Trackball algorithm from animation class
                Point direction = currPoint.sub(prevPoint);
                
                float velocity = direction.length();
                
                if(velocity > 0.0001)                
                {
                	Point rotAxis = prevPoint.cross(currPoint);
                	float angle = 10;
                	
                	mRenderer.setAngle(angle);
                	mRenderer.setRotAxis(rotAxis);
                	
                }
                
                prevPoint = currPoint;
                
                requestRender();
        }

        mPreviousX = x;
        mPreviousY = y;
        return true;
    }

	public Point trackBallmapping(float x, float y)
	{
		
		Point v = new Point();
		
		float d;
				
		//This doesn't work for some reason... look into it later
		int arr[] = new int[4];		
		GLES30.glGetIntegerv(GLES30.GL_VIEWPORT, arr, 0);
		//So for now, just hard code the portrait resolution
		int screen_width = 1900;
		int screen_height = 942;
		
		v.x = (screen_width - 2.0f * x ) / screen_width;
		v.y = (screen_height - 2.0f * y )/ screen_height;
		v.z = 0;
		
		d = v.length();
		
		if(d >= 1.0)
			d = 1.0f;
		
		v.z = (float) Math.sqrt(1.001 - d*d);		
		
		v.normalize();
		
		return v;
	}
}
