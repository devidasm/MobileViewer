package com.imebra.dicom;

/**

 @package com.imebra.dicom


 @class ModalityVOILUT
 @brief This class transforms the pixel values of the image retrieved from the dataset into values that are meaningful
         to th application.

 For instance, the original pixel values could store a device specific value that has a meaning only when used by the
  device that generated it: this transform uses the modality VOI/LUT defined in the DataSet to convert the original
  values into optical density or other known measure units.

 If the DataSet doesn't define any modality VOI/LUT transformation, then the input image is simply copied into the
  output image.


 @fn ModalityVOILUT::ModalityVOILUT(DataSet dataset)
 @brief Constructor.

 @param dataset the dataSet from which the input images come from


 */