package com.imebra.dicom;

/**

 @package com.imebra.dicom


 @class DataSet
 @brief A data set is a collection of groups of tags.

 The dataSet is usually built from a dicom stream by using the CodecFactory object.

 Also the tags with the data type SQ (sequence) contains one or more embedded DataSet objects that can be retrieved
  getDataSet().

 For an introduction to the DataSet, read \ref quick_tour_dataSet.


 \fn DataSet::DataSet()
 \brief Constructs an empty DataSet.

 \fn DataSet::DataSet(DataSet right)
 \brief Construct a DataSet that references the same data as the DataSet supplied in the parameter.

 @param right the source DataSet
 @return a reference to the DataSet



 \fn DataSet& DataSet::assign(DataSet right)
 \brief Copy the reference to the first DataSet's data. The two datasets will refer to the same data.

 @param right the source DataSet
 @return a reference to the DataSet



 \fn Image DataSet::getImage(int frameNumber)
 \brief Retrieve an image from the dataset.

 The right codec will be automatically used to decode the image embedded into the dataset.

 The function throw an exception if the requested image doesn't exist or the image's tag is corrupted.

 The retrieved image should be processed by the ModalityVOILUT transform in order to convert the pixels value to a
  meaningful space.
 Infact, the dicom image's pixel values saved by other application have a meaningful value only for the application
  that generated them, while the modality VOI/LUT transformation will convert those values to a more portable unit
  (e.g.: optical density).

 Further transformations are applied by VOILUT transform, in order to adjust the image's contrast for displaying
  purposes.

 @param frameNumber The frame number to retrieve. The first frame number is 0
 @return            the retrieved image



 \fn DataSet DataSet::getSequenceItem(int groupId, int order, int tagId, int itemId)
 \brief Retrieve a data set embedded into a sequence tag.

 Sequence tags store several binary data which can be individually parsed as a normal dicom file (without the preamble
  of 128 bytes and the DICM signature).

 When using sequences an application can store several nested dicom structures.

 This function parse a single item of a sequence tag and return a DataSet object which stores the retrieved tags.

 If the requested tag's type is not a sequence or the requested item in the sequence is missed, then an empty
  DataSet will be returned.

 @param groupId The group to which the sequence tag to be parsed belongs
 @param order   If the group is recurring in the file (it appears several times), then use this parameter to specify
                 which group must be retrieved. This parameter is used to deal with old DICOM files, since the new one
                 should use the sequence items to achieve the same result. It should be set to zero
 @param tagId   The id of the tag to parse
 @param itemId  The id of the tag's item to parse (zero based)
 @return        A pointer to the retrieved data set. If the requested group, tag or buffer (sequence item) doesn't
                 exist, or if the tag's type is not a sequence (SQ), then an empty DaraSet is returned



 \fn int DataSet::getSignedLong(int groupId, int order, int tagId, int elementNumber)
 \brief Read the value of the requested tag and return it as a signed long.

 @param groupId The group to which the tag to be read belongs
 @param order   If the group is recurring in the file (it appears several times), then use this parameter to specify
                 to which group the group belongs. This parameter is used to deal with old DICOM files, since the new
                 one should use the sequence items to achieve the same result. It should be set to zero
 @param tagId   The id of the tag to retrieve
 @param elementNumber The element's number to retrieve. A tag can store several elements: this parameter specifies
                 which element must be retrieved. The first element's number is 0
 @return        The tag's content, as a signed long


 \fn void DataSet::setSignedLong(int groupId, int order, int tagId, int elementNumber, int newValue, String defaultType)
 \brief Set a tag's value as a signed long.

 If the specified tag doesn't exist, then a new one will be created and inserted into the dataset.

 @param groupId The group to which the tag to be write belongs
 @param order   If the group is recurring in the file (it appears several times), then use this parameter to specify
                 to which group the group belongs. This parameter is used to deal with old DICOM files, since the new
                 ones should use the sequence items to achieve the same result. It should be set to zero
 @param tagId   The id of the tag to set
 @param elementNumber The element's number to set. A tag can store several elements: this parameter specifies which
                 element must be set. The first element's number is 0
 @param newValue the value to be written into the tag
 @param defaultType if the specified tag doesn't exist then the function will create a new tag with the data type
                  specified in this parameter


 \fn int DataSet::getUnsignedLong(int groupId, int order, int tagId, int elementNumber)
 \brief Read the value of the requested tag and return it as an unsigned long.

 @param groupId The group to which the tag to be read belongs
 @param order   If the group is recurring in the file (it appears several times), then use this parameter to specify
 to which group the group belongs. This parameter is used to deal with old DICOM files, since the new
 one should use the sequence items to achieve the same result. It should be set to zero
 @param tagId   The id of the tag to retrieve
 @param elementNumber The element's number to retrieve. A tag can store several elements: this parameter specifies
 which element must be retrieved. The first element's number is 0
 @return        The tag's content, as an unsigned long



 \fn void DataSet::setUnsignedLong(int groupId, int order, int tagId, int elementNumber, int newValue, String defaultType)
 \brief Set a tag's value as an unsigned long.

 If the specified tag doesn't exist, then a new one will be created and inserted into the dataset.

 @param groupId The group to which the tag to be write belongs
 @param order   If the group is recurring in the file (it appears several times), then use this parameter to specify
 to which group the group belongs. This parameter is used to deal with old DICOM files, since the new
 ones should use the sequence items to achieve the same result. It should be set to zero
 @param tagId   The id of the tag to set
 @param elementNumber The element's number to set. A tag can store several elements: this parameter specifies which
 element must be set. The first element's number is 0
 @param newValue the value to be written into the tag
 @param defaultType if the specified tag doesn't exist then the function will create a new tag with the data type
 specified in this parameter


 \fn double DataSet::getDouble(int groupId, int order, int tagId, int elementNumber)
 \brief Read the value of the requested tag and return it as a double.

 @param groupId The group to which the tag to be read belongs
 @param order   If the group is recurring in the file (it appears several times), then use this parameter to specify
 to which group the group belongs. This parameter is used to deal with old DICOM files, since the new
 one should use the sequence items to achieve the same result. It should be set to zero
 @param tagId   The id of the tag to retrieve
 @param elementNumber The element's number to retrieve. A tag can store several elements: this parameter specifies
 which element must be retrieved. The first element's number is 0
 @return        The tag's content, as a double


 \fn void DataSet::setDouble(int groupId, int order, int tagId, int elementNumber, double newValue, String defaultType)
 \brief Set a tag's value as a double.

 If the specified tag doesn't exist, then a new one will be created and inserted into the dataset.

 @param groupId The group to which the tag to be write belongs
 @param order   If the group is recurring in the file (it appears several times), then use this parameter to specify
 to which group the group belongs. This parameter is used to deal with old DICOM files, since the new
 ones should use the sequence items to achieve the same result. It should be set to zero
 @param tagId   The id of the tag to set
 @param elementNumber The element's number to set. A tag can store several elements: this parameter specifies which
 element must be set. The first element's number is 0
 @param newValue the value to be written into the tag
 @param defaultType if the specified tag doesn't exist then the function will create a new tag with the data type
 specified in this parameter


 @fn String DataSet::getString(int groupId, int order, int tagId, int elementNumber);
 @brief Read the value of the requested tag and return it as an unicode string.

 @param groupId The group to which the tag to be read belongs
 @param order   If the group is recurring in the file (it appears several times), then use this parameter to specify
 to which group the group belongs. This parameter is used to deal with old DICOM files, since the new
 one should use the sequence items to achieve the same result. It should be set to zero
 @param tagId   The id of the tag to retrieve
 @param elementNumber The element's number to retrieve. A tag can store several elements: this parameter specifies
 which element must be retrieved. The first element's number is 0
 @return        The tag's content, as an unicode string


 \fn void DataSet::setString(int groupId, int order, int tagId, int elementNumber, String newString, String defaultType)
 \brief Set a tag's value as a string.

 If the specified tag doesn't exist, then a new one will be created and inserted into the dataset.

 @param groupId The group to which the tag to be write belongs
 @param order   If the group is recurring in the file (it appears several times), then use this parameter to specify
 to which group the group belongs. This parameter is used to deal with old DICOM files, since the new
 ones should use the sequence items to achieve the same result. It should be set to zero
 @param tagId   The id of the tag to set
 @param elementNumber The element's number to set. A tag can store several elements: this parameter specifies which
 element must be set. The first element's number is 0
 @param newValue the value to be written into the tag
 @param defaultType if the specified tag doesn't exist then the function will create a new tag with the data type
 specified in this parameter


 \fn String DataSet::getDefaultDataType(int groupId, int order, int tagId)
 \brief Return the default data type for the specified tag's id.

 The default data type is retrieved from an internal dictionary which stores the default properties of each dicom's tag.
 @param groupId    The group to which the tag belongs
 @param tagId      The id of the tag.
 @return           the tag's default type. The returned string is a constant.



 \fn String DataSet::getDataType(int groupId, int order, int tagId)
 \brief Return the data type of a tag.

 @param groupId    The group to which the tag belongs
 @param order      When multiple groups with the same it are present, then use this parameter to specify which group
                    must be used. The first group as an order of 0.
 @param tagId      The id of the tag for which the type must be retrieved.
 @return           a string with the tag's type.



 */