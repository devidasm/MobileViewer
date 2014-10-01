package com.imebra.dicom;

/**

\package com.imebra.dicom

 \class DataHandler
 \brief A data handler allows to read/write the data stored in a DataSet object without worrying about the data format.

 Data handlers work on a local copy of the buffer so they don't need to worry about multithreading accesses.

 Data handlers are also used to access to the decompressed image's pixels.
 Call Image::getDataHandler() to obtain a data handler.


 \fn DataHandler::DataHandler(DataHandler right)
 \brief Copy constructor. The new DataHandler will refer the same data as the first handler. The new handler must
 be used in the same thread as the source handler.

 @param right the source DataHandler


 \fn DataHandler DataHandler::assign(DataHandler right)
 \brief Copy the reference to the first handler's data. The two handlers will refer to the same data and must be used
 in the same thread.

 @param right the source DataHandler
 @return a reference to the DataHandler



 \fn boolean DataHandler::pointerIsValid(int index)
 \brief Returns true if the specified index points to a valid element in the buffer.

 If the index is out of the valid range then this method returns false.

 @param index the index to be tested
 @return true if the index is valid, false if it is out of range



 \fn void DataHandler::setSize(int elementsNumber)
 \brief Set the buffer's size, in elements.

 The function resize the data handler's local buffer to the requested number of elements.

 @param elementsNumber the requested buffer's size, in data elements



 \fn int DataHandler::getSize()
 \brief Retrieve the data handler's local buffer buffer size (in elements).

 @return the buffer's size in elements



 \fn int DataHandler::getUnitSize()
 \brief Returns a single element's size in bytes.

 If the element doesn't have a fixed size, then this function return 0.

 @return the element's size in bytes, or zero if the element doesn't have a fixed size



 \fn String DataHandler::getDataType()
 \brief Get the dicom data type managed by this handler.

 The dicom data type is formed by 2 uppercase chars, as described by the dicom standard.

 @return the data handler's dicom data type



 \fn char DataHandler::getPaddingByte()
 \brief Return the byte that this handler uses to fill its content to make its size even.

 @return the byte used to make the content's size even



 \fn int DataHandler::getSignedLong(int index)
 \brief Retrieve the buffer's element referenced by the zero-based index specified in the parameter and returns it
 as a signed long value.

 Returns 0 if the specified index is out of range. You can check the validity of the index by using the function
 pointerIsValid().

 @param index   the zero base index of the buffer's element to retrieve
 @return the value of the data element referenced by the index, transformed into a signed long, or 0 if the index is
 out of range



 \fn int DataHandler::getUnsignedLong(int index)
 \brief Retrieve the buffer's element referenced by the zero-based index specified in the parameter and returns it as
 an unsigned long value.

 Returns 0 if the specified index is out of range. You can check the validity of the index by using the function
 pointerIsValid().

 @param index   the zero base index of the buffer's element to retrieve
 @return the value of the data element referenced by the index, transformed into an unsigned long, or 0 if the index
 is out of range




 \fn double DataHandler::getDouble(int index)
 \brief Retrieve the buffer's element referenced by the zero-based index specified in the parameter and returns it as a
 double floating point value.

 Returns 0 if the specified index is out of range. You can check the validity of the index by using the function
 pointerIsValid().

 @param index   the zero base index of the buffer's element to retrieve
 @return the value of the data element referenced by the index, transformed into a double floating point, or 0 if the
 index is out of range



 \fn String DataHandler::getString(int index)
 \brief Retrieve the buffer's element referenced by the zero-based index specified in the parameter and returns it as
 an unicode string value.

 Returns 0 if the specified index is out of range. You can check the validity of the index by using the function
 pointerIsValid().

 @param index   the zero base index of the buffer's element to retrieve
 @return the value of the data element referenced by the index, transformed into an unicode string, or 0 if the index
 is out of range



 \fn void DataHandler::getDate(int index, int[] pYear, int[] pMonth, int[] pDay, int[] pHour, int[] pMinutes, int[] pSeconds, int[] pNanoseconds, int[] pOffsetHours, int[] pOffsetMinutes)
 \brief Retrieve the buffer's element referenced by the zero-based index specified in the parameter and returns it as a
 date/time value.

 Returns all zeros if the specified index is out of range.
 You can check the validity of the index by using the function pointerIsValid().

 @param index   the zero base index of the buffer's element to retrieve
 @param pYear   a pointer to a value that will be filled with the UTC date's year
 @param pMonth  a pointer to a value that will be filled with the UTC date's month
 @param pDay    a pointer to a value that will be filled with the UTC date's day of the month
 @param pHour   a pointer to a value that will be filled with the UTC hour
 @param pMinutes a pointer to a value that will be filled with the UTC minutes
 @param pSeconds a pointer to a value that will be filled with the UTC seconds
 @param pNanoseconds a pointer to a value that will be filled with the UTC nanosecods
 @param pOffsetHours a pointer to a value that will be filled with the difference between the date time zone and the UTC time zone
 @param pOffsetMinutes a pointer to a value that will be filled with the difference between the date time zone and the UTC time zone




 \fn void DataHandler::setDate(int index, int year, int month, int day, int hour, int minutes, int seconds, int nanoseconds, int offsetHours, int offsetMinutes)
 \brief Set the buffer's element referenced by the zero-based index specified in the parameter to a date/time value.

 Does nothing if the specified index is out of range. You can check the validity of the index by using the function
 pointerIsValid(), you can resize the buffer by using the function setSize().

 @param index   the zero base index of the buffer's element to be set
 @param year   the UTC date's year
 @param month  the UTC date's month
 @param day    the UTC date's day of the month
 @param hour   the UTC hour
 @param minutes the UTC minutes
 @param seconds the UTC seconds
 @param nanoseconds the UTC nanosecods
 @param offsetHours the difference between the date time zone and the UTC time zone
 @param offsetMinutes the difference between the date time zone and the UTC time zone




 \fn void DataHandler::setSignedLong(int index, int value)
 \brief Set the buffer's element referenced by the zero-based index specified in the parameter to a signed long value.

 Does nothing if the specified index is out of range.
 You can check the validity of the index by using the function pointerIsValid(), you can resize the buffer by using the
 function setSize().

 @param index   the zero base index of the buffer's element to be set
 @param value the value to write into the data element.



 \fn void DataHandler::setUnsignedLong(int index, int value)
 \brief Set the buffer's element referenced by the zero-based index specified in the parameter to an unsigned long value.

 Does nothing if the specified index is out of range. You can check the validity of the index by using the function
 pointerIsValid(), you can resize the buffer by using the function setSize().

 @param index   the zero base index of the buffer's element to be set
 @param value the value to write into the data element.



 \fn void DataHandler::setDouble(int index, double value)
 \brief Set the buffer's element referenced by the zero-based index specified in the parameter to a double floating
 point value.

 Does nothing if the specified index is out of range. You can check the validity of the index by using the function
 pointerIsValid(), you can resize the buffer by using the function setSize().

 @param index   the zero base index of the buffer's element to be set
 @param value the value to write into the data element.



 \fn void DataHandler::setString(int index, String value)
 \brief Set the buffer's element referenced by the zero-based index specified in the parameter to a string value.

 Does nothing if the specified index is out of range. You can check the validity of the index by using the function
 pointerIsValid(), you can resize the buffer by using the function setSize().

 @param index   the zero base index of the buffer's element to be set
 @param value the value to write into the data element.

*/