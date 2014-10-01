/*
$fileHeader$
*/

/*! \file MONOCHROME1ToMONOCHROME2.h
    \brief Declaration of the class MONOCHROME1ToMONOCHROME2.

*/

#if !defined(imebraMONOCHROME1ToMONOCHROME2_E27C63E7_A907_4899_9BD3_8026AD7D110C__INCLUDED_)
#define imebraMONOCHROME1ToMONOCHROME2_E27C63E7_A907_4899_9BD3_8026AD7D110C__INCLUDED_

#include "colorTransform.h"

///////////////////////////////////////////////////////////
//
// Everything is in the namespace puntoexe::imebra
//
///////////////////////////////////////////////////////////
namespace puntoexe
{

namespace imebra
{

namespace transforms
{

namespace colorTransforms
{

/// \addtogroup group_transforms
///
/// @{

///////////////////////////////////////////////////////////
/// \brief Transforms an image from the colorspace 
///         MONOCHROME1 into the color space MONOCHROME2.
///
/// The input image has to have the colorspace MONOCHROME1,
///  while the output image is created by the transform
///  and will have the colorspace MONOCHROME2.
///
///////////////////////////////////////////////////////////
class MONOCHROME1ToMONOCHROME2: public colorTransform
{
public:
	virtual std::wstring getInitialColorSpace();
	virtual std::wstring getFinalColorSpace();
	virtual ptr<colorTransform> createColorTransform();

        DEFINE_RUN_TEMPLATE_TRANSFORM;

        template <class inputType, class outputType>
        void templateTransform(
            inputType* inputHandlerData, size_t /* inputHandlerSize */, imbxUint32 inputHandlerWidth, const std::wstring& inputHandlerColorSpace,
            ptr<palette> /* inputPalette */,
            imbxInt32 /* inputHandlerMinValue */, imbxUint32 inputHandlerNumValues,
            imbxInt32 inputTopLeftX, imbxInt32 inputTopLeftY, imbxInt32 inputWidth, imbxInt32 inputHeight,
            outputType* outputHandlerData, size_t /* outputHandlerSize */, imbxInt32 outputHandlerWidth, const std::wstring& outputHandlerColorSpace,
            ptr<palette> /* outputPalette */,
            imbxInt32 outputHandlerMinValue, imbxUint32 outputHandlerNumValues,
            imbxInt32 outputTopLeftX, imbxInt32 outputTopLeftY)

        {
            checkColorSpaces(inputHandlerColorSpace, outputHandlerColorSpace);

            inputType* pInputMemory(inputHandlerData);
            outputType* pOutputMemory(outputHandlerData);

            pInputMemory += inputTopLeftY * inputHandlerWidth + inputTopLeftX;
            pOutputMemory += outputTopLeftY * outputHandlerWidth + outputTopLeftX;
            
            imbxInt32 inputHandlerNumValuesMinusOne(inputHandlerNumValues - 1);

            if(inputHandlerNumValues == outputHandlerNumValues)
            {
                imbxInt32 offset(inputHandlerNumValuesMinusOne + outputHandlerMinValue);
                for(; inputHeight != 0; --inputHeight)
                {
                    for(int scanPixels(inputWidth); scanPixels != 0; --scanPixels)
                    {
                        *(pOutputMemory++) = (outputType)(offset - (imbxInt32)*(pInputMemory++));
                    }
                    pInputMemory += inputHandlerWidth - inputWidth;
                    pOutputMemory += outputHandlerWidth - inputWidth;
                }
            }
            else
            {
                for(; inputHeight != 0; --inputHeight)
                {
                    for(int scanPixels(inputWidth); scanPixels != 0; --scanPixels)
                    {
                        *pOutputMemory++ = (outputType)(((inputHandlerNumValuesMinusOne - (imbxInt32)*(pInputMemory++)) * outputHandlerNumValues) / inputHandlerNumValues + outputHandlerMinValue);
                    }
                    pInputMemory += inputHandlerWidth - inputWidth;
                    pOutputMemory += outputHandlerWidth - inputWidth;
                }
            }
        }

};


///////////////////////////////////////////////////////////
/// \brief Transforms an image from the colorspace 
///         MONOCHROME2 into the color space MONOCHROME1.
///
/// The input image has to have the colorspace MONOCHROME2,
///  while the output image is created by the transform
///  and will have the colorspace MONOCHROME1.
///
///////////////////////////////////////////////////////////
class MONOCHROME2ToMONOCHROME1: public MONOCHROME1ToMONOCHROME2
{
public:
	virtual std::wstring getInitialColorSpace();
	virtual std::wstring getFinalColorSpace();
	virtual ptr<colorTransform> createColorTransform();
};

/// @}

} // namespace colorTransforms

} // namespace transforms

} // namespace imebra

} // namespace puntoexe

#endif // !defined(imebraMONOCHROME1ToMONOCHROME2_E27C63E7_A907_4899_9BD3_8026AD7D110C__INCLUDED_)
