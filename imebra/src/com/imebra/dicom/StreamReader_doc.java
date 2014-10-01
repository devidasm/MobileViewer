package com.imebra.dicom;

/**

 \package com.imebra.dicom

 \class StreamReader
 \brief Represents a stream reader. A stream reader can read data from a stream.

 Several stream readers can share a single BaseStream derived object.

 The stream reader object is not multithread safe, but one single stream can have several StreamReader objects
 (in different threads) connected to it.

 A StreamReader object can also be connected only to a part of a stream: when this feature is used, then the
 StreamReader's client thinks that he is using a whole stream, while the reader limits its view
 to allowed stream's bytes only.



 \fn StreamReader::StreamReader(BaseStream stream, int virtualStart, int virtualLength)
 \brief Build a streamReader and connect it to an existing stream.

 The stream reader can also be connected to only a part of the stream.

 When the streamReader is connected to a part of a stream then all the its functions will act on the
 viewable stream's part only.
 @param stream             the stream that will be controlled by the reader
 @param virtualStart       the first stream's byte visible to the reader
 @param virtualLength      the number of bytes visible to the reader. A value of 0 means that all the bytes
 are visible



 */