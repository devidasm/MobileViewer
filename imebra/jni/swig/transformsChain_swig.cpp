/*
$fileHeader$
*/

/*! \file transformsChain_swig.cpp
    \brief Implementation of the TransformsChain class for SWIG.

*/

#include "transformsChain_swig.h"

TransformsChain::TransformsChain(): Transform(new puntoexe::imebra::transforms::transformsChain)
{
}

TransformsChain::TransformsChain(puntoexe::ptr<puntoexe::imebra::transforms::transformsChain> pTransform): Transform(pTransform)
{}

void TransformsChain::addTransform(const Transform& transform)
{
    ((puntoexe::imebra::transforms::transformsChain*)m_pTransform.get())->addTransform(transform.m_pTransform);
}
