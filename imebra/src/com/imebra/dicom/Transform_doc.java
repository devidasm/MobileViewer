package com.imebra.dicom;

/**

 @package com.imebra.dicom



 @class Transform
 @brief This is the base class for the transforms.

 A transform takes one input and one output image: the output image is modified according to the transform's type,
  input image's content and transform's parameter.



 @fn boolean Transform::isEmpty()
 @brief Returns true if the transform doesn't do anything.

 @return false if the transform does something, or true if the transform doesn't do anything (e.g. an empty
         TransformsChain object).



 @fn Image Transform::allocateOutputImage(Image inputImage, int width, int height)
 @brief Allocate an output image that is compatible with the transform given the specified input image.

 @param inputImage image that will be used as input image in runTransform()
 @param width      the width of the output image, in pixels
 @param height     the height of the output image, in pixels
 @return an image suitable to be used as output image in runTransform()



 @fn void Transform::runTransform(Image inputImage, int inputTopLeftX, int inputTopLeftY, int inputWidth, int inputHeight, Image outputImage, int outputTopLeftX, int outputTopLeftY)
 @brief Executes the transform.

 @param inputImage    the input image for the transform
 @param inputTopLeftX the horizontal position of the top left corner of the area to process
 @param inputTopLeftY the vertical position of the top left corner of the area to process
 @param inputWidth    the width of the area to process
 @param inputHeight   the height of the area to process
 @param outputImage   the output image for the transform
 @param outputTopLeftX the horizontal position of the top left corner of the output area
 @param outputTopLeftY the vertical position of the top left corner of the output area


 */