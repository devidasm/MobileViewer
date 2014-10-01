/*
$fileHeader$
*/

/*! \file dataSet_swig.h
    \brief Declaration of the class dataSet for SWIG.

*/

#if !defined(imebraDataSet_SWIG_93F684BF_0024_4bf3_89BA_D98E82A1F44C__INCLUDED_)
#define imebraDataSet_SWIG_93F684BF_0024_4bf3_89BA_D98E82A1F44C__INCLUDED_

#ifndef SWIG
#include "../imebra/project_files/library/imebra/include/dataSet.h"
#endif

#include "image_swig.h"

/// \addtogroup group_swig_bindings Bindings
///
/// The binding classes interface the Imebra library
///  with software written in Java, PHP, Python and others.
///
///////////////////////////////////////////////////////////
/// @{

class DataSet
{
#ifndef SWIG
	friend class DirectoryRecord;
	friend class DicomDir;
#endif
public:
	// Costructor
	///////////////////////////////////////////////////////////
	DataSet();

	DataSet(const DataSet& right);

	DataSet& operator=(const DataSet& right);

#ifndef SWIG
	DataSet(puntoexe::ptr<puntoexe::imebra::dataSet> pDataSet);
#endif

	Image getImage(int frameNumber);

	DataSet getSequenceItem(int groupId, int order, int tagId, int itemId);

	int getSignedLong(int groupId, int order, int tagId, int elementNumber);

	void setSignedLong(int groupId, int order, int tagId, int elementNumber, int newValue, std::string defaultType = "");

	int getUnsignedLong(int groupId, int order, int tagId, int elementNumber);

	void setUnsignedLong(int groupId, int order, int tagId, int elementNumber, int newValue, std::string defaultType = "");

	double getDouble(int groupId, int order, int tagId, int elementNumber);

	void setDouble(int groupId, int order, int tagId, int elementNumber, double newValue, std::string defaultType = "");

	std::wstring getString(int groupId, int order, int tagId, int elementNumber);

	void setString(int groupId, int order, int tagId, int elementNumber, std::wstring newString, std::string defaultType = "");

	std::string getDefaultDataType(int groupId, int order, int tagId);

	std::string getDataType(int groupId, int order, int tagId);

#ifndef SWIG
	puntoexe::ptr<puntoexe::imebra::dataSet> m_pDataSet;
#endif
};

///@}

#endif // !defined(imebraDataSet_SWIG_93F684BF_0024_4bf3_89BA_D98E82A1F44C__INCLUDED_)
