/*
$fileHeader$
*/

/*! \file image_swig.h
    \brief Implementation of the class Image for SWIG.

*/

#include "image_swig.h"

Image::Image()
{
}

Image::Image(const Image& right): m_pImage(right.m_pImage)
{}

Image::Image(puntoexe::ptr<puntoexe::imebra::image> pImage): m_pImage(pImage)
{}

Image& Image::operator=(const Image& right)
{
    m_pImage = right.m_pImage;
    return *this;
}

DataHandler Image::create(
		const int sizeX,
		const int sizeY,
		const bitDepth depth,
		std::wstring colorSpace,
		const int highBit)
{
    return DataHandler(m_pImage->create((imbxUint32)sizeX, (imbxUint32)sizeY, (puntoexe::imebra::image::bitDepth)depth, colorSpace, (imbxUint8)highBit));
}

void Image::setHighBit(int highBit)
{
    m_pImage->setHighBit((imbxUint8)highBit);
}

double Image::getSizeMmX()
{
    double sizeX, sizeY;
    m_pImage->getSizeMm(&sizeX, &sizeY);
    return sizeX;
}

double Image::getSizeMmY()
{
    double sizeX, sizeY;
    m_pImage->getSizeMm(&sizeX, &sizeY);
    return sizeY;
}

void Image::setSizeMm(const double sizeX, const double sizeY)
{
    m_pImage->setSizeMm(sizeX, sizeY);
}

int Image::getSizeX()
{
    imbxUint32 sizeX, sizeY;
    m_pImage->getSize(&sizeX, &sizeY);
    return (int)sizeX;
}

int Image::getSizeY()
{
    imbxUint32 sizeX, sizeY;
    m_pImage->getSize(&sizeX, &sizeY);
    return (int)sizeY;
}

DataHandler Image::getDataHandler(const bool bWrite)
{
    imbxUint32 rowSize, channelPixelSize, channelsNumber;
    return DataHandler(m_pImage->getDataHandler(bWrite, &rowSize, &channelPixelSize, &channelsNumber));
}


std::wstring Image::getColorSpace()
{
    return m_pImage->getColorSpace();
}

int Image::getChannelsNumber()
{
    return m_pImage->getChannelsNumber();
}

Image::bitDepth Image::getDepth()
{
    return (bitDepth)(m_pImage->getDepth());
}

int Image::getHighBit()
{
    return (int)(m_pImage->getHighBit());
}
