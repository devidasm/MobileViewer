/*
$fileHeader$
*/

/*! \file streamReader_swig.cpp
    \brief Implementation of the the class used to read the streams for SWIG.

*/

#include "streamReader_swig.h"

StreamReader::StreamReader(const BaseStream& stream, int virtualStart, int virtualLength): m_pReader(new puntoexe::streamReader(stream.m_pStream, virtualStart, virtualLength))
{
}
