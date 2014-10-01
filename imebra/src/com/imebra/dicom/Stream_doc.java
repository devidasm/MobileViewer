package com.imebra.dicom;

/**

 @package com.imebra.dicom


 @class Stream
 @brief This class derives from the baseStream and implements a file stream.

 This class can be used to read/write on physical files in the mass storage.


 @fn void Stream::openFileRead(String name)
 @brief Open a file for reading.

 @param name the name of the file to open




 @fn void Stream::openFileWrite(String name)
 @brief Open a file for writing.

 If the file doesn't exist then a new file will be created. If the file does exist then it will be truncated
  to zero bytes.

 @param name the name of the file to open




 */