package com.imebra.dicom;

/**

\package com.imebra.dicom


\class BaseStream
\brief This class represents a stream.

Specialized classes derived from this class can read/write from/to files stored on the computer's
disks, on the network or in memory.
The application can read or write into the stream by using the StreamReader or the StreamWriter classes

While this class can be used across several threads, the streamReader and the streamWriter can be used in
one thread only. This is not a big deal, since one stream can be connected to several streamReaders and
streamWriters.

The library supplies the specialized class Stream, used to read or write into physical files.

*/
