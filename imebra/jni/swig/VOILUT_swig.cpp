/*
$fileHeader$
*/

/*! \file VOILUT_swig.cpp
    \brief Implementation of the VOILUT class for SWIG.

*/


#include "VOILUT_swig.h"

VOILUT::VOILUT(const DataSet& dataset): Transform(new puntoexe::imebra::transforms::VOILUT(dataset.m_pDataSet))
{
}

int VOILUT::getVOILUTId(int VOILUTNumber)
{
    return (int) (((puntoexe::imebra::transforms::VOILUT*)m_pTransform.get())->getVOILUTId((imbxUint32) VOILUTNumber));

}

std::wstring VOILUT::getVOILUTDescription(int VOILUTId)
{
    return ((puntoexe::imebra::transforms::VOILUT*)m_pTransform.get())->getVOILUTDescription((imbxUint32) VOILUTId);

}

void VOILUT::setVOILUT(int VOILUTId)
{
    ((puntoexe::imebra::transforms::VOILUT*)m_pTransform.get())->setVOILUT((imbxUint32) VOILUTId);
}

void VOILUT::setCenterWidth(int center, int width)
{
    ((puntoexe::imebra::transforms::VOILUT*)m_pTransform.get())->setCenterWidth((imbxInt32) center, (imbxInt32) width);
}

void VOILUT::applyOptimalVOI(Image inputImage, int topLeftX, int topLeftY, int width, int height)
{
    ((puntoexe::imebra::transforms::VOILUT*)m_pTransform.get())->applyOptimalVOI(inputImage.m_pImage, (imbxUint32) topLeftX, (imbxUint32) topLeftY, (imbxUint32) width, (imbxUint32) height);
}

int VOILUT::getCenter()
{
    imbxInt32 center, width;
    ((puntoexe::imebra::transforms::VOILUT*)m_pTransform.get())->getCenterWidth(&center, &width);
    return (int)center;
}

int VOILUT::getWidth()
{
    imbxInt32 center, width;
    ((puntoexe::imebra::transforms::VOILUT*)m_pTransform.get())->getCenterWidth(&center, &width);
    return (int)width;
}

