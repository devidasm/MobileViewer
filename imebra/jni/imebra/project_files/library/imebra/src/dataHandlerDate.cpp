/*
$fileHeader$
*/

/*! \file dataHandlerDate.cpp
    \brief Implementation of the dataHandlerDate class.

*/

#include <sstream>
#include <iomanip>

#include "../../base/include/exception.h"
#include "../include/dataHandlerDate.h"


namespace puntoexe
{

namespace imebra
{

namespace handlers
{

///////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////
//
//
//
// dataHandlerDate
//
//
//
///////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////

///////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////
//
//
// Get the unit size
//
//
///////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////
imbxUint32 dataHandlerDate::getUnitSize() const
{
	return 8;
}


///////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////
//
//
// Get the maximum field's size
//
//
///////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////
imbxUint32 dataHandlerDate::maxSize() const
{
	return 10;
}


///////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////
//
//
// Retrieve the date
//
//
///////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////
void dataHandlerDate::getDate(const imbxUint32 index,
		imbxInt32* pYear, 
		imbxInt32* pMonth, 
		imbxInt32* pDay, 
		imbxInt32* pHour, 
		imbxInt32* pMinutes,
		imbxInt32* pSeconds,
		imbxInt32* pNanoseconds,
		imbxInt32* pOffsetHours,
		imbxInt32* pOffsetMinutes) const
{
	PUNTOEXE_FUNCTION_START(L"dataHandlerDate::getDate");

	*pYear = 0;
	*pMonth = 0;
	*pDay = 0;
	*pHour = 0;
	*pMinutes = 0;
	*pSeconds = 0;
	*pNanoseconds = 0;
	*pOffsetHours = 0;
	*pOffsetMinutes = 0;

	std::wstring dateString=dataHandlerDateTimeBase::getUnicodeString(index);
	parseDate(dateString, pYear, pMonth, pDay);

	PUNTOEXE_FUNCTION_END();
}


///////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////
//
//
// Set the date
//
//
///////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////
void dataHandlerDate::setDate(const imbxUint32 index,
		imbxInt32 year, 
		imbxInt32 month, 
		imbxInt32 day, 
		imbxInt32 /* hour */, 
		imbxInt32 /* minutes */,
		imbxInt32 /* seconds */,
		imbxInt32 /* nanoseconds */,
		imbxInt32 /* offsetHours */,
		imbxInt32 /* offsetMinutes */)
{
	PUNTOEXE_FUNCTION_START(L"dataHandlerDate::setDate");

	std::wstring dateString=buildDate(year, month, day);
	dataHandlerDateTimeBase::setUnicodeString(index, dateString);

	PUNTOEXE_FUNCTION_END();
}


///////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////
//
//
// Get a string representation of the date
//
//
///////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////
std::wstring dataHandlerDate::getUnicodeString(const imbxUint32 index) const
{
	PUNTOEXE_FUNCTION_START(L"dataHandlerDate::getUnicodeString");

	imbxInt32 year, month, day, hour, minutes, seconds, nanoseconds, offsetHours, offsetMinutes;
	getDate(index, &year, &month, &day, &hour, &minutes, &seconds, &nanoseconds, &offsetHours, &offsetMinutes);

	std::wostringstream convStream;
	convStream << std::setfill(L'0');
	convStream << std::setw(4) << year;
	convStream << std::setw(1) << L"-";
	convStream << std::setw(2) << month;
	convStream << std::setw(1) << "-";
	convStream << std::setw(2) << day;

	return convStream.str();

	PUNTOEXE_FUNCTION_END();
}


///////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////
//
//
// Set a string representation of the date
//
//
///////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////
void dataHandlerDate::setUnicodeString(const imbxUint32 index, const std::wstring& value)
{
	PUNTOEXE_FUNCTION_START(L"dataHandlerDate::setUnicodeString");

	std::vector<std::wstring> components;
	split(value, L"-/.", &components);
	if(components.size() != 3)
	{
		return;
	}

	imbxInt32 year, month, day;
	
	std::wistringstream yearStream(components[0]);
	yearStream >> year;

	std::wistringstream monthStream(components[1]);
	monthStream >> month;

	std::wistringstream dayStream(components[2]);
	dayStream >> day;

	setDate(index, year, month, day, 0, 0, 0, 0, 0, 0);

	PUNTOEXE_FUNCTION_END();
}


///////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////
//
//
// Parse the buffer's content
//
//
///////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////
void dataHandlerDate::parseBuffer(const ptr<memory>& memoryBuffer)
{
	PUNTOEXE_FUNCTION_START(L"dataHandlerDate::parseBuffer");

	// Parse the date
	///////////////////////////////////////////////////////////
	dataHandlerString::parseBuffer(memoryBuffer);

	// Adjust the parsed string so it is a legal date
	///////////////////////////////////////////////////////////
	std::wstring unicodeString;
	if(getSize() > 0)
	{
		unicodeString = dataHandlerString::getUnicodeString(0);
	}

	// Remove trailing spaces an invalid chars
	///////////////////////////////////////////////////////////
	size_t removeTrail;
	for(removeTrail = unicodeString.size(); removeTrail != 0 && ( unicodeString[removeTrail - 1] == 0x20 || unicodeString[removeTrail - 1] == 0x0); --removeTrail){};
	unicodeString.resize(removeTrail);

	std::vector<std::wstring> components;
	split(unicodeString, L"./-", &components);
	std::wstring normalizedTime;

	if(components.size() == 1)
	{
		normalizedTime = components.front();
	}
	else
	{
		if(components.size() > 0)
		{
			normalizedTime = padLeft(components[0], L'0', 4);
		}
		if(components.size() > 1)
		{
			normalizedTime += padLeft(components[1], L'0', 2);
		}
		if(components.size() > 2)
		{
			normalizedTime += padLeft(components[2], L'0', 2);
		}
	}

	dataHandlerString::setUnicodeString(0, normalizedTime);

	PUNTOEXE_FUNCTION_END();
}

} // namespace handlers

} // namespace imebra

} // namespace puntoexe
