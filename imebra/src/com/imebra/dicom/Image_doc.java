package com.imebra.dicom;

/**

 @package com.imebra.dicom


 @class Image
 @brief brief The class Image contains the decompressed data of one DICOM image.

 The image's data includes:
 - the image's size, in pixels
 - the image's size, in millimeters
 - the bit depth (bytes per color channel) and high
   bit
 - the color palette (if available)
 - the pixels' data

 An image can be obtained from a dataSet object by calling DataSet::getImage(), or it can be initialized with create().

 Images can also be allocated by the transforms by calling Transform::allocateOutputImage().

 Images are embedded into the dicom structures (represented by the DataSet class).




 @fn DataHandler Image::create(int sizeX, int sizeY, Image.bitDepth depth, String colorSpace, int highBit)
 @brief Create the image.

 An image with the specified size (in pixels), bit depth and color space is allocated.

 The number of channels to allocate is automatically calculated using the colorSpace parameter.

 @param sizeX    the image's width, in pixels.
 @param sizeY    the image's height, in pixels.
 @param depth    the size of a single color's component.
 @param colorSpace The color space as defined by the DICOM standard. Valid colorspace are:
                  - "RGB"
                  - "YBR_FULL"
                  - "YBR_PARTIAL"
                  - "YBR_RCT" (Not yet supported)
                  - "YBR_ICT" (Not yet supported)
                  - "PALETTE COLOR"
                  - "MONOCHROME2"
                  - "MONOCHROME1"
 @param highBit  the highest bit used for integer values.
 @return         the data handler containing the image's data




 @fn void Image::setHighBit(int highBit)
 @brief Set the high bit.

 @param highBit       the image's high bit



 @fn double Image::getSizeMmY()
 @brief Retrieve the image's height, in millimeters.

 The image's size in millimeters is automatically read from the dicom structure or can be set using setSizeMm().
 @return the image's height, in millimeters



 @fn double Image::getSizeMmX()
 @brief Retrieve the image's width, in millimeters.

 The image's size in millimeters is automatically read from the dicom structure or can be set using setSizeMm().
 @return the image's width, in millimeters




 @fn void Image::setSizeMm(double sizeX, double sizeY)
 @brief Set the image's size, in millimeters.

 @param sizeX the new image's width, in millimeters.
 @param sizeY the new image's height, in millimeters.



 @fn int Image::getSizeX()
 @brief Get the image's width, in pixels.

 @return the image's width, in pixels



 @fn int Image::getSizeY()
 @brief Get the image's height, in pixels.

 @return the image's height, in pixels



 @fn DataHandler Image::getDataHandler(boolean bWrite)
 @brief Retrieve a data handler for managing the image's buffer

 The retrieved data handler gives access to the image's buffer.

 The image's buffer stores the data in the following format:
 - when multiple channels are present, then the channels are ALWAYS interleaved
 - the channels are NEVER subsampled or oversampled. The subsampling/oversampling is done by the codecs when the
   image is stored or loaded from the dicom structure.
 - the first stored value represent the first channel of the top/left pixel.
 - each row is stored countiguously, from the top to the bottom.

 @param bWrite   true if the application wants to write into the buffer, false otherwise.
 @return a pointer to the data handler for the image's buffer.



 @fn String Image::getColorSpace()
 @brief Get the image's color space (DICOM standard)

 @return a string with the image's color space




 @fn int Image::getChannelsNumber()
 @brief Get the number of allocated channels.

 @return the number of color channels in the image




 @fn Image::bitDepth Image::getDepth()
 @brief Get the image's bit depth.

 The bit depth indicates the number of bits used to store every single value.

 @return the bit depth.




 @fn int Image::getHighBit()
 @brief Get the high bit.

 @return the image's high bit



 @class Image::bitDepth
 @brief Define the number of allocated bytes per value.


 */