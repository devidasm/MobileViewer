package com.imebra.dicom;

/**

 \package com.imebra.dicom

\class CodecFactory
\brief This class maintains a list of the available codecs.

It is used to automatically select the right codec that can parse the specified stream of data.


\fn DataSet CodecFactory::load(StreamReader reader, int maxSizeBufferLoad)
\brief Build a DataSet structure from the specified stream of data.

The function selects automatically the codec that can read the specified stream.

@param reader            the stream that contain the data to be parsed
@param maxSizeBufferLoad if a loaded buffer exceedes the size in the parameter then it is not loaded immediatly but
 it will be loaded on demand. Some codecs may ignore this parameter.
@return                  the dataSet containing the parsed data

*/