/*
$fileHeader$
*/

/*! \file drawBitmap_swig.cpp
    \brief Implementation of the class DrawBitmap for SWIG.

*/

#include "drawBitmap_swig.h"

DrawBitmap::DrawBitmap(const DrawBitmap& right): m_pDrawBitmap(right.m_pDrawBitmap)
{
}

DrawBitmap::DrawBitmap(const Image& sourceImage, const TransformsChain& transformsChain):
    m_pDrawBitmap(new puntoexe::imebra::drawBitmap(
        sourceImage.m_pImage,
        (puntoexe::imebra::transforms::transformsChain*)transformsChain.m_pTransform.get()))
{
}

DrawBitmap& DrawBitmap::operator=(const DrawBitmap& right)
{
    m_pDrawBitmap = right.m_pDrawBitmap;

}

int DrawBitmap::getBitmap(
        int totalWidthPixels, int totalHeightPixels,
        int visibleTopLeftX, int visibleTopLeftY, int visibleBottomRightX, int visibleBottomRightY,
        int* buffer, int bufferSize)
{
    return (int)m_pDrawBitmap->getBitmap<puntoexe::imebra::drawBitmapBGRA, 4>(
        (imbxInt32)totalWidthPixels,
        (imbxInt32)totalHeightPixels,
        (imbxInt32)visibleTopLeftX,
        (imbxInt32)visibleTopLeftY,
        (imbxInt32)visibleBottomRightX,
        (imbxInt32)visibleBottomRightY,
        (imbxUint8*)buffer, (size_t)bufferSize * sizeof(int)) / sizeof(int);

}


Memory DrawBitmap::getBitmap(
        int totalWidthPixels, int totalHeightPixels,
        int visibleTopLeftX, int visibleTopLeftY, int visibleBottomRightX, int visibleBottomRightY,
        Memory reuseMemory)
{
    return Memory(m_pDrawBitmap->getBitmap<puntoexe::imebra::drawBitmapBGRA, 4>(
        (imbxInt32)totalWidthPixels,
        (imbxInt32)totalHeightPixels,
        (imbxInt32)visibleTopLeftX,
        (imbxInt32)visibleTopLeftY,
        (imbxInt32)visibleBottomRightX,
        (imbxInt32)visibleBottomRightY,
        reuseMemory.m_pMemory));

}

