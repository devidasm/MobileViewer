/*
$fileHeader$
*/

/*! \file dataSet_swig.cpp
    \brief Implementation of the class dataSet for SWIG.
*/

#include "dataSet_swig.h"
#include "dataSet_swig.h"

DataSet::DataSet(): m_pDataSet(new puntoexe::imebra::dataSet)
{
}

DataSet::DataSet(const DataSet& right): m_pDataSet(right.m_pDataSet)
{
}

#ifndef SWIG
DataSet::DataSet(puntoexe::ptr<puntoexe::imebra::dataSet> pDataSet): m_pDataSet(pDataSet)
{
    if(pDataSet == 0)
    {
        m_pDataSet = new puntoexe::imebra::dataSet;
    }
}

#endif

DataSet& DataSet::operator=(const DataSet& right)
{
	m_pDataSet = right.m_pDataSet;
	return *this;
}

Image DataSet::getImage(int frameNumber)
{
    return Image(m_pDataSet->getImage((imbxUint32)frameNumber));
}

DataSet DataSet::getSequenceItem(int groupId, int order, int tagId, int itemId)
{
	return DataSet(m_pDataSet->getSequenceItem(groupId, order, tagId, itemId));
}

int DataSet::getSignedLong(int groupId, int order, int tagId, int elementNumber)
{
	return m_pDataSet->getSignedLong(groupId, order, tagId, elementNumber);
}

void DataSet::setSignedLong(int groupId, int order, int tagId, int elementNumber, int newValue, std::string defaultType)
{
	m_pDataSet->setSignedLong(groupId, order, tagId, elementNumber, newValue, defaultType);
}

int DataSet::getUnsignedLong(int groupId, int order, int tagId, int elementNumber)
{
	return m_pDataSet->getUnsignedLong(groupId, order, tagId, elementNumber);
}

void DataSet::setUnsignedLong(int groupId, int order, int tagId, int elementNumber, int newValue, std::string defaultType)
{
	m_pDataSet->setUnsignedLong(groupId, order, tagId, elementNumber, newValue, defaultType);
}

double DataSet::getDouble(int groupId, int order, int tagId, int elementNumber)
{
	return m_pDataSet->getDouble(groupId, order, tagId, elementNumber);
}

void DataSet::setDouble(int groupId, int order, int tagId, int elementNumber, double newValue, std::string defaultType)
{
	m_pDataSet->setDouble(groupId, order, tagId, elementNumber, newValue, defaultType);
}

std::wstring DataSet::getString(int groupId, int order, int tagId, int elementNumber)
{
	return m_pDataSet->getUnicodeString(groupId, order, tagId, elementNumber);
}

void DataSet::setString(int groupId, int order, int tagId, int elementNumber, std::wstring newString, std::string defaultType)
{
	m_pDataSet->setUnicodeString(groupId, order, tagId, elementNumber, newString, defaultType);
}

std::string DataSet::getDefaultDataType(int groupId, int order, int tagId)
{
	return m_pDataSet->getDataType(groupId, order, tagId);
}

std::string DataSet::getDataType(int groupId, int order, int tagId)
{
	return m_pDataSet->getDataType(groupId, order, tagId);
}




