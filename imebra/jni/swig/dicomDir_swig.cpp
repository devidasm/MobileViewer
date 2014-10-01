/*
$fileHeader$
*/

/*! \file dicomDir_swig.cpp
    \brief Implementation of the classes that parse/create a DICOMDIR
		structure (dicomDir and directoryRecord) for SWIG.

*/

#include "dicomDir_swig.h"
#include "dataSet_swig.h"

DirectoryRecord::DirectoryRecord(){}

DirectoryRecord::DirectoryRecord(const DirectoryRecord& right): m_pDirectoryRecord(right.m_pDirectoryRecord)
{
}

DirectoryRecord::DirectoryRecord(puntoexe::ptr<puntoexe::imebra::directoryRecord> pDirectoryRecord): m_pDirectoryRecord(pDirectoryRecord)
{
}

DirectoryRecord& DirectoryRecord::operator=(const DirectoryRecord& right)
{
	m_pDirectoryRecord = right.m_pDirectoryRecord;
	return *this;
}

DataSet DirectoryRecord::getRecordDataSet()
{
	if(m_pDirectoryRecord == 0) throw std::logic_error("Null directoryRecord");
	return DataSet(m_pDirectoryRecord->getRecordDataSet());
}

DirectoryRecord DirectoryRecord::getNextRecord()
{
	if(m_pDirectoryRecord == 0) throw std::logic_error("Null directoryRecord");
	return DirectoryRecord(m_pDirectoryRecord->getNextRecord());
}

DirectoryRecord DirectoryRecord::getFirstChildRecord()
{
	if(m_pDirectoryRecord == 0) throw std::logic_error("Null directoryRecord");
	return DirectoryRecord(m_pDirectoryRecord->getFirstChildRecord());
}
	
DirectoryRecord DirectoryRecord::getReferencedRecord()
{
	if(m_pDirectoryRecord == 0) throw std::logic_error("Null directoryRecord");
	return DirectoryRecord(m_pDirectoryRecord->getReferencedRecord());
}

void DirectoryRecord::setNextRecord(const DirectoryRecord& nextRecord)
{
	if(m_pDirectoryRecord == 0) throw std::logic_error("Null directoryRecord");
	m_pDirectoryRecord->setNextRecord(nextRecord.m_pDirectoryRecord);
}

void DirectoryRecord::setFirstChildRecord(const DirectoryRecord& firstChildRecord)
{
	if(m_pDirectoryRecord == 0) throw std::logic_error("Null directoryRecord");
	m_pDirectoryRecord->setFirstChildRecord(firstChildRecord.m_pDirectoryRecord);
}

void DirectoryRecord::setReferencedRecord(const DirectoryRecord& referencedRecord)
{
	if(m_pDirectoryRecord == 0) throw std::logic_error("Null directoryRecord");
	m_pDirectoryRecord->setReferencedRecord(referencedRecord.m_pDirectoryRecord);
}
	
std::wstring DirectoryRecord::getFilePart(int part)
{
	if(m_pDirectoryRecord == 0) throw std::logic_error("Null directoryRecord");
	return m_pDirectoryRecord->getFilePart(part);
}

void DirectoryRecord::setFilePart(int part, const std::wstring& partName)
{
	if(m_pDirectoryRecord == 0) throw std::logic_error("Null directoryRecord");
	m_pDirectoryRecord->setFilePart(part, partName);
}

std::wstring DirectoryRecord::getTypeString()
{
	if(m_pDirectoryRecord == 0) throw std::logic_error("Null directoryRecord");
	return m_pDirectoryRecord->getTypeString();
}

void DirectoryRecord::setTypeString(const std::wstring& recordType)
{
	if(m_pDirectoryRecord == 0) throw std::logic_error("Null directoryRecord");
	m_pDirectoryRecord->setTypeString(recordType);
}

bool DirectoryRecord::isNull()
{
	return m_pDirectoryRecord == 0;
}


DicomDir::DicomDir(DataSet fromDataSet): m_pDicomDir(new puntoexe::imebra::dicomDir(fromDataSet.m_pDataSet))
{}

DataSet DicomDir::getDirectoryDataSet()
{
	return DataSet(m_pDicomDir->getDirectoryDataSet());
}

DirectoryRecord DicomDir::getNewRecord()
{
	return DirectoryRecord(m_pDicomDir->getNewRecord());
}

DirectoryRecord DicomDir::getFirstRootRecord()
{
	return DirectoryRecord(m_pDicomDir->getFirstRootRecord());
}

void DicomDir::setFirstRootRecord(DirectoryRecord firstRootRecord)
{
	if(firstRootRecord.m_pDirectoryRecord == 0) throw std::logic_error("Null directoryRecord");
	m_pDicomDir->setFirstRootRecord(firstRootRecord.m_pDirectoryRecord);
}

DataSet DicomDir::buildDataSet()
{
	return DataSet(m_pDicomDir->buildDataSet());
}
