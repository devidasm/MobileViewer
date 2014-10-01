/*
$fileHeader$
*/

/*! \file streamReader_swig.h
	\brief Declaration of the the class used to read the streams for SWIG.

*/

#if !defined(imebraStreamReader_SWIG_F6221390_BC44_4B83_B5BB_3485222FF1DD__INCLUDED_)
#define imebraStreamReader_SWIG_F6221390_BC44_4B83_B5BB_3485222FF1DD__INCLUDED_

#ifndef SWIG
#include "../imebra/project_files/library/base/include/streamReader.h"
#include "baseStream_swig.h"
#endif


class CodecFactory;

/// \addtogroup group_swig_bindings
/// @{

class StreamReader
{
	friend class CodecFactory;
public:
	StreamReader(const BaseStream& stream, int virtualStart = 0, int virtualLength = 0);

#ifndef SWIG
protected:
	puntoexe::ptr<puntoexe::streamReader> m_pReader;
#endif
};

///@}

#endif // !defined(imebraStreamReader_SWIG_F6221390_BC44_4B83_B5BB_3485222FF1DD__INCLUDED_)
