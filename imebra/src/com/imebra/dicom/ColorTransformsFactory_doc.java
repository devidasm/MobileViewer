package com.imebra.dicom;

/**

 @package com.imebra.dicom

 @class ColorTransformsFactory
 @brief This class maintains a list of all the available colorTransform classes and retrieve the most appropriate
         transform class when a color space conversion is needed.

 The class can also retrieve information from a name of a color space (in dicom standard).
 For instance, both the Dicom color space "YBR_FULL_422" and "YBR_FULL" describe the color space YBR_FULL, but the
  first indicates that the image is subsampled both horizontally and vertically.

 The ColorTransformsFactory can normalize the color space name (e.g.: convert "YBR_FULL_422" to "YBR_FULL") and can
  retrieve the subsampling parameters.



 @fn String ColorTransformsFactory::normalizeColorSpace(String colorSpace)
 @brief Normalize a color space name.

 The function converts all the chars to uppercase and remove additional information from the color space.

 For instance, the color space "ybr_full_420" is converted to "YBR_FULL".

 @param colorSpace the color space name to be normalized
 @return the normalized color space name




 @fn boolean ColorTransformsFactory::isMonochrome(String colorSpace)
 @brief Returns true if the color space name specified in the parameter has only one color channel and is monochrome
         (it doesn't have a lookup color table).

 At the moment, only the color space names "MONOCHROME1" and "MONOCHROME2" indicate a monochrome color space.

 @param colorSpace the name of the color space to be checked
 @return true if the color space indicated in the parameter is monochrome, or false otherwise




 @fn boolean ColorTransformsFactory::isSubsampledX(String colorSpace)
 @brief Returns true if the name of the color space indicates that the chrominance channels are subsampled horizontally.

 @param colorSpace the name of the color space to be checked
 @return true if the name of the color space in the parameter colorSpace has the chrominance channels subsampled
          horizontally



 @fn boolean ColorTransformsFactory::isSubsampledY(String colorSpace)
 @brief Returns true if the name of the color space indicates that the chrominance channels are subsampled vertically.

 @param colorSpace the name of the color space to be checked
 @return true if the name of the color space in the parameter colorSpace has the chrominance channels subsampled
          vertically




 @fn boolean ColorTransformsFactory::canSubsample(String colorSpace)
 @brief Returns true if the color space specified in the parameter can be subsampled.

 For instance, the color spaces "YBR_FULL" and "YBR_PARTIAL" can be subsampled but the color space "RGB" cannot be
  subsampled.

 @param colorSpace the name of the color space to check
 @return true if the name of the color space in the parameter colorSpace can be subsampled





 @fn String ColorTransformsFactory::makeSubsampled(String colorSpace, boolean bSubsampleX, boolean bSubsampleY)
 @brief Add the subsamplig information to a color space name.

 Only the color spaces for which canSubsample() returns true can have the subsampling information.

 @param colorSpace the name of the color space to which the subsampling information should be added
 @param bSubsampleX if true, then the function will make the color space subsampled horizontally. The color space will
                     also be subsampled vertically
 @param bSubsampleY if true, then the function will make the color space subsampled vertically
 @return the color space name with subsampling information




 @fn int ColorTransformsFactory::getNumberOfChannels(String colorSpace)
 @brief Returns the number of channels used by the specified color space.

 For instance, the color space "RGB" has 3 color channels, while the "MONOCHROME2" color space has 1 color channel.

 @param colorSpace the name of the color space for which the number of channels must be returned
 @return the number of color channels in the specified color channel




 @fn Transform ColorTransformsFactory::getTransform(String startColorSpace, String endColorSpace)
 @brief Returns a transform that can convert the pixels from one color space to another color space.

 If no conversion is needed then the function throws an exception.

 @param startColorSpace the color space from which the conversion has to take place
 @param endColorSpace   the color space resulting from the conversion
 @return the transform that can convert the startColorSpace into endColorSpace


 */