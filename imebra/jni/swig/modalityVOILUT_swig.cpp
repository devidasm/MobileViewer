/*
$fileHeader$
*/

/*! \file modalityVOILUT_swig.cpp
    \brief Implementation of the ModalityVOILUT class for SWIG.

*/


#include "modalityVOILUT_swig.h"

ModalityVOILUT::ModalityVOILUT(const DataSet& dataset): Transform(new puntoexe::imebra::transforms::modalityVOILUT(dataset.m_pDataSet))
{
}
