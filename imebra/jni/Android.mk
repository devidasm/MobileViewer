LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE := imebra_lib
LOCAL_CPPFLAGS += -fexceptions
LOCAL_CPPFLAGS += -DPUNTOEXE_USE_JAVA -DIMEBRA_MEMORY_POOL_MAX_SIZE=4000000
LOCAL_LDLIBS :=  -llog
LOCAL_SRC_FILES := \
    ./swig/baseStream_swig.cpp \
    ./swig/codecFactory_swig.cpp \
    ./swig/dataSet_swig.cpp \
    ./swig/dicomDir_swig.cpp \
    ./swig/streamReader_swig.cpp \
    ./swig/stream_swig.cpp \
    ./swig/dataHandler_swig.cpp \
    ./swig/image_swig.cpp \
    ./swig/transform_swig.cpp \
    ./swig/transformsChain_swig.cpp \
    ./swig/colorTransformsFactory_swig.cpp \
    ./swig/modalityVOILUT_swig.cpp \
    ./swig/VOILUT_swig.cpp \
    ./swig/memory_swig.cpp \
    ./swig/drawBitmap_swig.cpp \
    ./swig/onload.cpp \
    ./swig/swig_wrap.cpp \
    ./swig/charsetConversionJava.cpp \
    ./imebra/project_files/library/imebra/src/YBRPARTIALToRGB.cpp \
    ./imebra/project_files/library/imebra/src/YBRFULLToRGB.cpp \
    ./imebra/project_files/library/imebra/src/YBRFULLToMONOCHROME2.cpp \
    ./imebra/project_files/library/imebra/src/waveform.cpp \
    ./imebra/project_files/library/imebra/src/VOILUT.cpp \
    ./imebra/project_files/library/imebra/src/viewHelper.cpp \
    ./imebra/project_files/library/imebra/src/transformsChain.cpp \
    ./imebra/project_files/library/imebra/src/transformHighBit.cpp \
    ./imebra/project_files/library/imebra/src/transaction.cpp \
    ./imebra/project_files/library/imebra/src/RGBToYBRPARTIAL.cpp \
    ./imebra/project_files/library/imebra/src/RGBToYBRFULL.cpp \
    ./imebra/project_files/library/imebra/src/RGBToMONOCHROME2.cpp \
    ./imebra/project_files/library/imebra/src/PALETTECOLORToRGB.cpp \
    ./imebra/project_files/library/imebra/src/MONOCHROME2ToYBRFULL.cpp \
    ./imebra/project_files/library/imebra/src/MONOCHROME2ToRGB.cpp \
    ./imebra/project_files/library/imebra/src/MONOCHROME1ToRGB.cpp \
    ./imebra/project_files/library/imebra/src/MONOCHROME1ToMONOCHROME2.cpp \
    ./imebra/project_files/library/imebra/src/modalityVOILUT.cpp \
    ./imebra/project_files/library/imebra/src/LUT.cpp \
    ./imebra/project_files/library/imebra/src/jpegCodec.cpp \
    ./imebra/project_files/library/imebra/src/image.cpp \
    ./imebra/project_files/library/imebra/src/drawBitmap.cpp \
    ./imebra/project_files/library/imebra/src/dicomDir.cpp \
    ./imebra/project_files/library/imebra/src/dicomDict.cpp \
    ./imebra/project_files/library/imebra/src/dicomCodec.cpp \
    ./imebra/project_files/library/imebra/src/dataSet.cpp \
    ./imebra/project_files/library/imebra/src/dataHandlerTime.cpp \
    ./imebra/project_files/library/imebra/src/dataHandlerStringUT.cpp \
    ./imebra/project_files/library/imebra/src/dataHandlerStringUnicode.cpp \
    ./imebra/project_files/library/imebra/src/dataHandlerStringUI.cpp \
    ./imebra/project_files/library/imebra/src/dataHandlerStringST.cpp \
    ./imebra/project_files/library/imebra/src/dataHandlerStringSH.cpp \
    ./imebra/project_files/library/imebra/src/dataHandlerStringPN.cpp \
    ./imebra/project_files/library/imebra/src/dataHandlerStringLT.cpp \
    ./imebra/project_files/library/imebra/src/dataHandlerStringLO.cpp \
    ./imebra/project_files/library/imebra/src/dataHandlerStringIS.cpp \
    ./imebra/project_files/library/imebra/src/dataHandlerStringDS.cpp \
    ./imebra/project_files/library/imebra/src/dataHandlerStringCS.cpp \
    ./imebra/project_files/library/imebra/src/dataHandlerStringAS.cpp \
    ./imebra/project_files/library/imebra/src/dataHandlerStringAE.cpp \
    ./imebra/project_files/library/imebra/src/dataHandlerString.cpp \
    ./imebra/project_files/library/imebra/src/dataHandlerDateTimeBase.cpp \
    ./imebra/project_files/library/imebra/src/dataHandlerDateTime.cpp \
    ./imebra/project_files/library/imebra/src/dataHandlerDate.cpp \
    ./imebra/project_files/library/imebra/src/dataHandler.cpp \
    ./imebra/project_files/library/imebra/src/dataGroup.cpp \
    ./imebra/project_files/library/imebra/src/data.cpp \
    ./imebra/project_files/library/imebra/src/colorTransformsFactory.cpp \
    ./imebra/project_files/library/imebra/src/colorTransform.cpp \
    ./imebra/project_files/library/imebra/src/codecFactory.cpp \
    ./imebra/project_files/library/imebra/src/codec.cpp \
    ./imebra/project_files/library/imebra/src/charsetsList.cpp \
    ./imebra/project_files/library/imebra/src/buffer.cpp \
	./imebra/project_files/library/imebra/src/transform.cpp \
    ./imebra/project_files/library/base/src/thread.cpp \
    ./imebra/project_files/library/base/src/streamWriter.cpp \
    ./imebra/project_files/library/base/src/streamReader.cpp \
    ./imebra/project_files/library/base/src/streamController.cpp \
    ./imebra/project_files/library/base/src/stream.cpp \
    ./imebra/project_files/library/base/src/memoryStream.cpp \
    ./imebra/project_files/library/base/src/memory.cpp \
    ./imebra/project_files/library/base/src/huffmanTable.cpp \
    ./imebra/project_files/library/base/src/exception.cpp \
    ./imebra/project_files/library/base/src/criticalSection.cpp \
    ./imebra/project_files/library/base/src/charsetConversion.cpp \
    ./imebra/project_files/library/base/src/baseStream.cpp \
    ./imebra/project_files/library/base/src/baseObject.cpp


include $(BUILD_SHARED_LIBRARY)
