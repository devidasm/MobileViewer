/*
$fileHeader$
*/

/*! \file baseStream_swig.cpp
    \brief Implementation of the the base class for the streams (memory, file, ...)
			for SWIG.
*/

#include "baseStream_swig.h"

BaseStream::BaseStream()
{}

BaseStream::BaseStream(puntoexe::ptr<puntoexe::baseStream> pStream): m_pStream(pStream)
{
}

BaseStream::BaseStream(const BaseStream& right): m_pStream(right.m_pStream)
{
}

BaseStream& BaseStream::operator=(const BaseStream& right)
{
	m_pStream = right.m_pStream;
	return *this;
}


