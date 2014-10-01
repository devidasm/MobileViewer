/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 2.0.7
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package com.imebra.dicom;

public class imebraJNI {
  public final static native long new_DataSet__SWIG_0();
  public final static native long new_DataSet__SWIG_1(long jarg1, DataSet jarg1_);
  public final static native long DataSet_assign(long jarg1, DataSet jarg1_, long jarg2, DataSet jarg2_);
  public final static native long DataSet_getImage(long jarg1, DataSet jarg1_, int jarg2);
  public final static native long DataSet_getSequenceItem(long jarg1, DataSet jarg1_, int jarg2, int jarg3, int jarg4, int jarg5);
  public final static native int DataSet_getSignedLong(long jarg1, DataSet jarg1_, int jarg2, int jarg3, int jarg4, int jarg5);
  public final static native void DataSet_setSignedLong__SWIG_0(long jarg1, DataSet jarg1_, int jarg2, int jarg3, int jarg4, int jarg5, int jarg6, String jarg7);
  public final static native void DataSet_setSignedLong__SWIG_1(long jarg1, DataSet jarg1_, int jarg2, int jarg3, int jarg4, int jarg5, int jarg6);
  public final static native int DataSet_getUnsignedLong(long jarg1, DataSet jarg1_, int jarg2, int jarg3, int jarg4, int jarg5);
  public final static native void DataSet_setUnsignedLong__SWIG_0(long jarg1, DataSet jarg1_, int jarg2, int jarg3, int jarg4, int jarg5, int jarg6, String jarg7);
  public final static native void DataSet_setUnsignedLong__SWIG_1(long jarg1, DataSet jarg1_, int jarg2, int jarg3, int jarg4, int jarg5, int jarg6);
  public final static native double DataSet_getDouble(long jarg1, DataSet jarg1_, int jarg2, int jarg3, int jarg4, int jarg5);
  public final static native void DataSet_setDouble__SWIG_0(long jarg1, DataSet jarg1_, int jarg2, int jarg3, int jarg4, int jarg5, double jarg6, String jarg7);
  public final static native void DataSet_setDouble__SWIG_1(long jarg1, DataSet jarg1_, int jarg2, int jarg3, int jarg4, int jarg5, double jarg6);
  public final static native String DataSet_getString(long jarg1, DataSet jarg1_, int jarg2, int jarg3, int jarg4, int jarg5);
  public final static native void DataSet_setString__SWIG_0(long jarg1, DataSet jarg1_, int jarg2, int jarg3, int jarg4, int jarg5, String jarg6, String jarg7);
  public final static native void DataSet_setString__SWIG_1(long jarg1, DataSet jarg1_, int jarg2, int jarg3, int jarg4, int jarg5, String jarg6);
  public final static native String DataSet_getDefaultDataType(long jarg1, DataSet jarg1_, int jarg2, int jarg3, int jarg4);
  public final static native String DataSet_getDataType(long jarg1, DataSet jarg1_, int jarg2, int jarg3, int jarg4);
  public final static native void delete_DataSet(long jarg1);
  public final static native long new_BaseStream();
  public final static native void delete_BaseStream(long jarg1);
  public final static native long new_Stream__SWIG_0();
  public final static native long new_Stream__SWIG_1(long jarg1, Stream jarg1_);
  public final static native long Stream_assign(long jarg1, Stream jarg1_, long jarg2, Stream jarg2_);
  public final static native void Stream_openFileRead(long jarg1, Stream jarg1_, String jarg2);
  public final static native void Stream_openFileWrite(long jarg1, Stream jarg1_, String jarg2);
  public final static native void delete_Stream(long jarg1);
  public final static native long new_StreamReader__SWIG_0(long jarg1, BaseStream jarg1_, int jarg2, int jarg3);
  public final static native long new_StreamReader__SWIG_1(long jarg1, BaseStream jarg1_, int jarg2);
  public final static native long new_StreamReader__SWIG_2(long jarg1, BaseStream jarg1_);
  public final static native void delete_StreamReader(long jarg1);
  public final static native long CodecFactory_load(long jarg1, StreamReader jarg1_, int jarg2);
  public final static native long new_CodecFactory();
  public final static native void delete_CodecFactory(long jarg1);
  public final static native long new_DirectoryRecord__SWIG_0();
  public final static native long new_DirectoryRecord__SWIG_1(long jarg1, DirectoryRecord jarg1_);
  public final static native long DirectoryRecord_assign(long jarg1, DirectoryRecord jarg1_, long jarg2, DirectoryRecord jarg2_);
  public final static native long DirectoryRecord_getRecordDataSet(long jarg1, DirectoryRecord jarg1_);
  public final static native long DirectoryRecord_getNextRecord(long jarg1, DirectoryRecord jarg1_);
  public final static native long DirectoryRecord_getFirstChildRecord(long jarg1, DirectoryRecord jarg1_);
  public final static native long DirectoryRecord_getReferencedRecord(long jarg1, DirectoryRecord jarg1_);
  public final static native void DirectoryRecord_setNextRecord(long jarg1, DirectoryRecord jarg1_, long jarg2, DirectoryRecord jarg2_);
  public final static native void DirectoryRecord_setFirstChildRecord(long jarg1, DirectoryRecord jarg1_, long jarg2, DirectoryRecord jarg2_);
  public final static native void DirectoryRecord_setReferencedRecord(long jarg1, DirectoryRecord jarg1_, long jarg2, DirectoryRecord jarg2_);
  public final static native String DirectoryRecord_getFilePart(long jarg1, DirectoryRecord jarg1_, int jarg2);
  public final static native void DirectoryRecord_setFilePart(long jarg1, DirectoryRecord jarg1_, int jarg2, String jarg3);
  public final static native String DirectoryRecord_getTypeString(long jarg1, DirectoryRecord jarg1_);
  public final static native void DirectoryRecord_setTypeString(long jarg1, DirectoryRecord jarg1_, String jarg2);
  public final static native boolean DirectoryRecord_isNull(long jarg1, DirectoryRecord jarg1_);
  public final static native void delete_DirectoryRecord(long jarg1);
  public final static native long new_DicomDir(long jarg1, DataSet jarg1_);
  public final static native long DicomDir_getDirectoryDataSet(long jarg1, DicomDir jarg1_);
  public final static native long DicomDir_getNewRecord(long jarg1, DicomDir jarg1_);
  public final static native long DicomDir_getFirstRootRecord(long jarg1, DicomDir jarg1_);
  public final static native void DicomDir_setFirstRootRecord(long jarg1, DicomDir jarg1_, long jarg2, DirectoryRecord jarg2_);
  public final static native long DicomDir_buildDataSet(long jarg1, DicomDir jarg1_);
  public final static native void delete_DicomDir(long jarg1);
  public final static native long new_DataHandler(long jarg1, DataHandler jarg1_);
  public final static native long DataHandler_assign(long jarg1, DataHandler jarg1_, long jarg2, DataHandler jarg2_);
  public final static native boolean DataHandler_pointerIsValid(long jarg1, DataHandler jarg1_, int jarg2);
  public final static native void DataHandler_setSize(long jarg1, DataHandler jarg1_, int jarg2);
  public final static native int DataHandler_getSize(long jarg1, DataHandler jarg1_);
  public final static native int DataHandler_getUnitSize(long jarg1, DataHandler jarg1_);
  public final static native String DataHandler_getDataType(long jarg1, DataHandler jarg1_);
  public final static native char DataHandler_getPaddingByte(long jarg1, DataHandler jarg1_);
  public final static native int DataHandler_getSignedLong(long jarg1, DataHandler jarg1_, int jarg2);
  public final static native int DataHandler_getUnsignedLong(long jarg1, DataHandler jarg1_, int jarg2);
  public final static native double DataHandler_getDouble(long jarg1, DataHandler jarg1_, int jarg2);
  public final static native String DataHandler_getString(long jarg1, DataHandler jarg1_, int jarg2);
  public final static native void DataHandler_getDate(long jarg1, DataHandler jarg1_, int jarg2, int[] jarg3, int[] jarg4, int[] jarg5, int[] jarg6, int[] jarg7, int[] jarg8, int[] jarg9, int[] jarg10, int[] jarg11);
  public final static native void DataHandler_setDate(long jarg1, DataHandler jarg1_, int jarg2, int jarg3, int jarg4, int jarg5, int jarg6, int jarg7, int jarg8, int jarg9, int jarg10, int jarg11);
  public final static native void DataHandler_setSignedLong(long jarg1, DataHandler jarg1_, int jarg2, int jarg3);
  public final static native void DataHandler_setUnsignedLong(long jarg1, DataHandler jarg1_, int jarg2, int jarg3);
  public final static native void DataHandler_setDouble(long jarg1, DataHandler jarg1_, int jarg2, double jarg3);
  public final static native void DataHandler_setString(long jarg1, DataHandler jarg1_, int jarg2, String jarg3);
  public final static native void delete_DataHandler(long jarg1);
  public final static native int Image_depthU8_get();
  public final static native int Image_depthS8_get();
  public final static native int Image_depthU16_get();
  public final static native int Image_depthS16_get();
  public final static native int Image_depthU32_get();
  public final static native int Image_depthS32_get();
  public final static native int Image_endOfDepths_get();
  public final static native long new_Image__SWIG_0();
  public final static native long new_Image__SWIG_1(long jarg1, Image jarg1_);
  public final static native long Image_assign(long jarg1, Image jarg1_, long jarg2, Image jarg2_);
  public final static native long Image_create(long jarg1, Image jarg1_, int jarg2, int jarg3, int jarg4, String jarg5, int jarg6);
  public final static native void Image_setHighBit(long jarg1, Image jarg1_, int jarg2);
  public final static native double Image_getSizeMmY(long jarg1, Image jarg1_);
  public final static native double Image_getSizeMmX(long jarg1, Image jarg1_);
  public final static native void Image_setSizeMm(long jarg1, Image jarg1_, double jarg2, double jarg3);
  public final static native int Image_getSizeX(long jarg1, Image jarg1_);
  public final static native int Image_getSizeY(long jarg1, Image jarg1_);
  public final static native long Image_getDataHandler(long jarg1, Image jarg1_, boolean jarg2);
  public final static native String Image_getColorSpace(long jarg1, Image jarg1_);
  public final static native int Image_getChannelsNumber(long jarg1, Image jarg1_);
  public final static native int Image_getDepth(long jarg1, Image jarg1_);
  public final static native int Image_getHighBit(long jarg1, Image jarg1_);
  public final static native void delete_Image(long jarg1);
  public final static native long new_Transform(long jarg1, Transform jarg1_);
  public final static native long Transform_assign(long jarg1, Transform jarg1_, long jarg2, Transform jarg2_);
  public final static native void delete_Transform(long jarg1);
  public final static native boolean Transform_isEmpty(long jarg1, Transform jarg1_);
  public final static native long Transform_allocateOutputImage(long jarg1, Transform jarg1_, long jarg2, Image jarg2_, int jarg3, int jarg4);
  public final static native void Transform_runTransform(long jarg1, Transform jarg1_, long jarg2, Image jarg2_, int jarg3, int jarg4, int jarg5, int jarg6, long jarg7, Image jarg7_, int jarg8, int jarg9);
  public final static native long new_TransformsChain();
  public final static native void TransformsChain_addTransform(long jarg1, TransformsChain jarg1_, long jarg2, Transform jarg2_);
  public final static native void delete_TransformsChain(long jarg1);
  public final static native String ColorTransformsFactory_normalizeColorSpace(String jarg1);
  public final static native boolean ColorTransformsFactory_isMonochrome(String jarg1);
  public final static native boolean ColorTransformsFactory_isSubsampledX(String jarg1);
  public final static native boolean ColorTransformsFactory_isSubsampledY(String jarg1);
  public final static native boolean ColorTransformsFactory_canSubsample(String jarg1);
  public final static native String ColorTransformsFactory_makeSubsampled(String jarg1, boolean jarg2, boolean jarg3);
  public final static native int ColorTransformsFactory_getNumberOfChannels(String jarg1);
  public final static native long ColorTransformsFactory_getTransform(String jarg1, String jarg2);
  public final static native long new_ColorTransformsFactory();
  public final static native void delete_ColorTransformsFactory(long jarg1);
  public final static native long new_ModalityVOILUT(long jarg1, DataSet jarg1_);
  public final static native void delete_ModalityVOILUT(long jarg1);
  public final static native long new_VOILUT(long jarg1, DataSet jarg1_);
  public final static native int VOILUT_getVOILUTId(long jarg1, VOILUT jarg1_, int jarg2);
  public final static native String VOILUT_getVOILUTDescription(long jarg1, VOILUT jarg1_, int jarg2);
  public final static native void VOILUT_setVOILUT(long jarg1, VOILUT jarg1_, int jarg2);
  public final static native void VOILUT_applyOptimalVOI(long jarg1, VOILUT jarg1_, long jarg2, Image jarg2_, int jarg3, int jarg4, int jarg5, int jarg6);
  public final static native void VOILUT_setCenterWidth(long jarg1, VOILUT jarg1_, int jarg2, int jarg3);
  public final static native int VOILUT_getCenter(long jarg1, VOILUT jarg1_);
  public final static native int VOILUT_getWidth(long jarg1, VOILUT jarg1_);
  public final static native void delete_VOILUT(long jarg1);
  public final static native long new_Memory__SWIG_0();
  public final static native long new_Memory__SWIG_1(int jarg1);
  public final static native void Memory_transfer(long jarg1, Memory jarg1_, long jarg2, Memory jarg2_);
  public final static native void Memory_copyFrom(long jarg1, Memory jarg1_, long jarg2, Memory jarg2_);
  public final static native void Memory_clear(long jarg1, Memory jarg1_);
  public final static native void Memory_resize(long jarg1, Memory jarg1_, int jarg2);
  public final static native void Memory_reserve(long jarg1, Memory jarg1_, int jarg2);
  public final static native int Memory_size(long jarg1, Memory jarg1_);
  public final static native long Memory_data(long jarg1, Memory jarg1_, byte[] jarg2);
  public final static native void Memory_assign(long jarg1, Memory jarg1_, byte[] jarg2);
  public final static native boolean Memory_empty(long jarg1, Memory jarg1_);
  public final static native void delete_Memory(long jarg1);
  public final static native void MemoryPool_flush();
  public final static native long new_MemoryPool();
  public final static native void delete_MemoryPool(long jarg1);
  public final static native long new_DrawBitmap__SWIG_0(long jarg1, DrawBitmap jarg1_);
  public final static native long new_DrawBitmap__SWIG_1(long jarg1, Image jarg1_, long jarg2, TransformsChain jarg2_);
  public final static native long DrawBitmap_assign(long jarg1, DrawBitmap jarg1_, long jarg2, DrawBitmap jarg2_);
  public final static native int DrawBitmap_getBitmap__SWIG_0(long jarg1, DrawBitmap jarg1_, int jarg2, int jarg3, int jarg4, int jarg5, int jarg6, int jarg7, int[] jarg8, int jarg9);
  public final static native long DrawBitmap_getBitmap__SWIG_1(long jarg1, DrawBitmap jarg1_, int jarg2, int jarg3, int jarg4, int jarg5, int jarg6, int jarg7, long jarg8, Memory jarg8_);
  public final static native void delete_DrawBitmap(long jarg1);
  public final static native long Stream_SWIGUpcast(long jarg1);
  public final static native long TransformsChain_SWIGUpcast(long jarg1);
  public final static native long ModalityVOILUT_SWIGUpcast(long jarg1);
  public final static native long VOILUT_SWIGUpcast(long jarg1);
}