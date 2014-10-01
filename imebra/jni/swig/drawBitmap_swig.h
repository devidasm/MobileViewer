/*
$fileHeader$
*/

/*! \file drawBitmap_swig.h
    \brief Declaration of the class DrawBitmap for SWIG.

*/

#if !defined(imebraDrawBitmap_SWIG_3146DA5A_5276_4804_B9AB_A3D54C6B123A__INCLUDED_)
#define imebraDrawBitmap_SWIG_3146DA5A_5276_4804_B9AB_A3D54C6B123A__INCLUDED_

#ifndef SWIG
#include "../imebra/project_files/library/imebra/include/drawBitmap.h"
#endif
#include "image_swig.h"
#include "transformsChain_swig.h"
#include "memory_swig.h"

class DrawBitmap
{
public:
    DrawBitmap(const DrawBitmap& right);
    DrawBitmap(const Image& sourceImage, const TransformsChain& transformsChain);

    DrawBitmap& operator=(const DrawBitmap& right);

    int getBitmap(
        int totalWidthPixels, int totalHeightPixels,
        int visibleTopLeftX, int visibleTopLeftY, int visibleBottomRightX, int visibleBottomRightY,
        int* buffer, int bufferSize);


    Memory getBitmap(
        int totalWidthPixels, int totalHeightPixels,
        int visibleTopLeftX, int visibleTopLeftY, int visibleBottomRightX, int visibleBottomRightY,
        Memory reuseMemory);

#ifndef SWIG
    puntoexe::ptr<puntoexe::imebra::drawBitmap> m_pDrawBitmap;
#endif

};

#endif // !defined(imebraDrawBitmap_SWIG_3146DA5A_5276_4804_B9AB_A3D54C6B123A__INCLUDED_)
