package com.imebra.dicom;

/**

 @package com.imebra.dicom


 @class VOILUT
 @brief Transforms the value of the input image's pixels by using the presentation VOI/LUT defined in the DataSet.

 The DataSet could define more than one VOI/LUT: by default the transform uses the first VOI or LUT defined in the
  Dataset.

 To control which VOI/LUT is used for the transformation the application must use the functions getVOILUTId(),
  getVOILUTDescription(), setVOILUT() or set the VOI or the LUT directly by calling setCenterWidth() or setLUT().



 @fn VOILUT::VOILUT(DataSet dataset)
 @brief Constructor.

 @param dataset the dataSet from which the input images comes from




 @fn int VOILUT::getVOILUTId(int VOILUTNumber)
 @brief Retrieve an ID for a VOI or a LUT.

 The returned ID can be used with the functions getVOILUTDescription() and setVOILUT().

 The function returns 0 when the requested VOI/LUT doesn't exist.

 The parameter VOILUTNumber is a zero based index used to scan all the available VOIs first and then all the LUTs.

 For instance, if VOILUTNumber is 3 and the DataSet contains 2 VOIs and 3 LUTs, then the function will return the ID
  for the second LUT.

 @param VOILUTNumber  a number that identifies the VOI or the LUT for which the ID is requested.
                      The value 0 refers to the first VOI in the dataSet or to the first LUT if there isn't any defined
                      VOI. Bigger values refer to the following VOIs or LUTs
 @return an ID that can be used with getVOILUTDescription() and setVOILUT(), or 0 if the requested VOI/LUT doesn't
                       exist



 @fn String VOILUT::getVOILUTDescription(int VOILUTId)
 @brief Return a description for the VOI or LUT with the specified ID.

 The VOI/LUT ID can be obtained by calling getVOILUTId().

 @param VOILUTId the id of the VOI/LUT for which the description is required
 @return         the VOI/LUT description




 @fn void VOILUT::setVOILUT(int VOILUTId)
 @brief Define the VOI/LUT to use for the transformation.

 The VOI/LUT ID can be obtained by calling getVOILUTId().

 Disable the VOI/LUT transform if the parameter is 0.

 @param VOILUTId the ID of the VOI/LUT to use for the transformation, or 0 to disable the transformation




 @fn void VOILUT::applyOptimalVOI(Image inputImage, int topLeftX, int topLeftY, int width, int height)
 @brief Finds and apply the optimal VOI values.
 @@param inputImage    the image for which the optimal VOI must be found
 @param topLeftX the horizontal coordinate of the top-left corner of the area for which the optimal VOI must be
                      found
 @param topLeftY the vertical coordinate of the top-left corner of the area for which the optimal VOI must be
                      found
 @param width    the width of the area for which the optimal VOI must be found
 @param height   the height of the area for which the optimal VOI must be found




 @fn void VOILUT::setCenterWidth(int center, int width)
 @brief Define the VOI width/center to use for the transformation.

 @param center   the center value of the VOI
 @param width    the width value of the VOI



 @fn int VOILUT::getCenter()
 @brief Returns the VOI center used for the transformation.

 @return the VOI center



 @fn int VOILUT::getWidth()
 @brief Returns the VOI width used for the transformation.

 @return the VOI width

 */

