/*
$fileHeader$
*/

/*! \file modalityVOILUT_swig.h
	\brief Declaration of the class ModalityVOILUT for SWIG.

*/

#if !defined(imebraModalityVOILUT_SWIG_F6221390_BC44_4B83_B5BB_3485222FF1DD__INCLUDED_)
#define imebraModalityVOILUT_SWIG_F6221390_BC44_4B83_B5BB_3485222FF1DD__INCLUDED_

#ifndef SWIG
#include "../imebra/project_files/library/imebra/include/modalityVOILUT.h"
#endif

#include "transform_swig.h"
#include "dataSet_swig.h"

class ModalityVOILUT: public Transform
{

public:
    ModalityVOILUT(const DataSet& dataset);
};

#endif // imebraModalityVOILUT_SWIG_F6221390_BC44_4B83_B5BB_3485222FF1DD__INCLUDED_
