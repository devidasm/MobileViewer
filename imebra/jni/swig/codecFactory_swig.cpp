/*
$fileHeader$
*/

/*! \file codecFactory_swig.cpp
	\brief Implementation of the class used to retrieve the codec able to
		handle the requested transfer syntax (SWIG).

*/

#include "codecFactory_swig.h"

DataSet CodecFactory::load(StreamReader reader, int maxSizeBufferLoad)
{
    puntoexe::ptr<puntoexe::imebra::codecs::codecFactory> factory(puntoexe::imebra::codecs::codecFactory::getCodecFactory());
    return DataSet(factory->load(reader.m_pReader, maxSizeBufferLoad));
}
