/*
$fileHeader$
*/

/*! \file stream_swig.cpp
    \brief Implementation of the stream class for SWIG.

*/

#include "stream_swig.h"

Stream::Stream(): BaseStream(new puntoexe::stream)
{
}

Stream::Stream(const Stream& right): BaseStream(right.m_pStream)
{
}

Stream& Stream::operator=(const Stream& right)
{
	m_pStream = right.m_pStream;
	return *this;
}

void Stream::openFileRead(const std::wstring& name)
{
	(dynamic_cast<puntoexe::stream*>(m_pStream.get()))->openFile(name, std::ios_base::in);
}

void Stream::openFileWrite(const std::wstring& name)
{
	(dynamic_cast<puntoexe::stream*>(m_pStream.get()))->openFile(name, std::ios_base::out);
}

