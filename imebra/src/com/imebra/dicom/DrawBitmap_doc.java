package com.imebra.dicom;

/**

 @package com.imebra.dicom

 @class DrawBitmap
 @brief This class takes an image as an input and returns an 8 bit BGRA bitmap of the requested image's area.



 @fn DrawBitmap::DrawBitmap(Image sourceImage, TransformsChain transformsChain)
 @brief Constructor.

 @param sourceImage  the input image that has to be rendered
 @param transformsChain the list of transforms to be applied to the image before it is rendered. Can be an empty
                         chain. The transformation to BGRA and high bit shift are applied automatically by this class




 @fn int DrawBitmap::getBitmap(int totalWidthPixels, int totalHeightPixels, int visibleTopLeftX, int visibleTopLeftY, int visibleBottomRightX, int visibleBottomRightY, int[] buffer, int bufferSize)
 @brief Renders the image specified in the constructor into an BGRA buffer.

 The caller must pass a preallocated array to the method, which will fill the array with the proper data. The resulting
  array can be used directly to create a Bitmap object on Android.

 Calling getBitmap() with a zero integers long buffer will cause the method to return the expected length of the
  array, in integers.

 @image html drawbitmap.png "drawbitmap"
 @image latex drawbitmap.png "drawbitmap" width=15cm

 The figure illustrates how the getBitmap() method works:
 -# the image is resized according to the parameters
     totalWidthPixels and totalHeightPixels
 -# the area specified by visibleTopLeftX,
     visibleTopLeftY - visibleBottomRightX,
     visibleBottomRightY is rendered into the buffer

 Please note that the rendering algorithm achieves the described results without actually resizing the image.

 @param totalWidthPixels the width of the magnified or shrunken image in pixels (magnified width on the figure "drawbitmap")
 @param totalHeightPixels the height of the magnified or shrunken image in pixels (magnified height on the figure "drawbitmap")
 @param visibleTopLeftX  the X coordinate of the top left corner of image area that has to be rendered (visible top
                         left magnified X on the figure "drawbitmap")
 @param visibleTopLeftY  the Y coordinate of the top left corner of image area that has to be rendered (visible top
                         left magnified Y on the figure "drawbitmap")
 @param visibleBottomRightX the X coordinate of the bottom right corner of image area that has to be rendered (visible
                            bottom right magnified X on the figure "drawbitmap")
 @param visibleBottomRightY the Y coordinate of the bottom right corner of image area that has to be rendered (visible
                            bottom right magnified Y on the figure "drawbitmap")
 @param buffer           a preallocated array of integers that the method fill with the necessary information so that
                          the same array can be used in a call to Bitmap::createBitmap()
 @param bufferSize       the size of the array, in integers





 @fn Memory DrawBitmap::getBitmap(int totalWidthPixels, int totalHeightPixels, int visibleTopLeftX, int visibleTopLeftY, int visibleBottomRightX, int visibleBottomRightY, Memory reuseMemory)
 @brief Renders the image specified in the constructor into an BGRA buffer.

 @image html drawbitmap.png "drawbitmap"
 @image latex drawbitmap.png "drawbitmap" width=15cm

 The figure illustrates how the getBitmap() method works:
 -# the image is resized according to the parameters
 totalWidthPixels and totalHeightPixels
 -# the area specified by visibleTopLeftX,
 visibleTopLeftY - visibleBottomRightX,
 visibleBottomRightY is rendered into the buffer

 Please note that the rendering algorithm achieves the described results without actually resizing the image.

 @param totalWidthPixels the width of the magnified or shrunken image in pixels (magnified width on the figure "drawbitmap")
 @param totalHeightPixels the height of the magnified or shrunken image in pixels (magnified height on the figure "drawbitmap")
 @param visibleTopLeftX  the X coordinate of the top left corner of image area that has to be rendered (visible top
 left magnified X on the figure "drawbitmap")
 @param visibleTopLeftY  the Y coordinate of the top left corner of image area that has to be rendered (visible top
 left magnified Y on the figure "drawbitmap")
 @param visibleBottomRightX the X coordinate of the bottom right corner of image area that has to be rendered (visible
 bottom right magnified X on the figure "drawbitmap")
 @param visibleBottomRightY the Y coordinate of the bottom right corner of image area that has to be rendered (visible
 bottom right magnified Y on the figure "drawbitmap")
 @param reuseMemory      the Memory object that will be filled with the image data. Must not be null
 @return the memory object specified in the parameter reuseMemory

 */