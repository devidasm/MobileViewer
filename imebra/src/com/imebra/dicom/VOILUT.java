/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 2.0.7
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package com.imebra.dicom;

public class VOILUT extends Transform {
  private long swigCPtr;

  protected VOILUT(long cPtr, boolean cMemoryOwn) {
    super(imebraJNI.VOILUT_SWIGUpcast(cPtr), cMemoryOwn);
    swigCPtr = cPtr;
  }

  protected static long getCPtr(VOILUT obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        imebraJNI.delete_VOILUT(swigCPtr);
      }
      swigCPtr = 0;
    }
    super.delete();
  }

  public VOILUT(DataSet dataset) {
    this(imebraJNI.new_VOILUT(DataSet.getCPtr(dataset), dataset), true);
  }

  public int getVOILUTId(int VOILUTNumber) {
    return imebraJNI.VOILUT_getVOILUTId(swigCPtr, this, VOILUTNumber);
  }

  public String getVOILUTDescription(int VOILUTId) {
    return imebraJNI.VOILUT_getVOILUTDescription(swigCPtr, this, VOILUTId);
  }

  public void setVOILUT(int VOILUTId) {
    imebraJNI.VOILUT_setVOILUT(swigCPtr, this, VOILUTId);
  }

  public void applyOptimalVOI(Image inputImage, int topLeftX, int topLeftY, int width, int height) {
    imebraJNI.VOILUT_applyOptimalVOI(swigCPtr, this, Image.getCPtr(inputImage), inputImage, topLeftX, topLeftY, width, height);
  }

  public void setCenterWidth(int center, int width) {
    imebraJNI.VOILUT_setCenterWidth(swigCPtr, this, center, width);
  }

  public int getCenter() {
    return imebraJNI.VOILUT_getCenter(swigCPtr, this);
  }

  public int getWidth() {
    return imebraJNI.VOILUT_getWidth(swigCPtr, this);
  }

}