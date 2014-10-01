/*
$fileHeader$
*/

/*! \file stream_swig.h
	\brief Declaration of the stream class for SWIG.

*/

#if !defined(imebraStream_SWIG_146DA5A_5276_4804_B9AB_A3D54C6B123A__INCLUDED_)
#define imebraStream_SWIG_146DA5A_5276_4804_B9AB_A3D54C6B123A__INCLUDED_

#ifndef SWIG
#include "../imebra/project_files/library/base/include/stream.h"
#endif

#include "baseStream_swig.h"

/// \addtogroup group_swig_bindings
/// @{

class Stream : public BaseStream
{
public:
	Stream();
	Stream(const Stream& right);
	Stream& operator=(const Stream& right);

	void openFileRead(const std::wstring& name);
	void openFileWrite(const std::wstring& name);

};

///@}

#endif // !defined(imebraStream_SWIG_146DA5A_5276_4804_B9AB_A3D54C6B123A__INCLUDED_)
