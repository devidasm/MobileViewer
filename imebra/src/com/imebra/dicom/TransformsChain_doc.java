package com.imebra.dicom;

/**

 @package com.imebra.dicom


 @class TransformsChain
 @brief Executes a sequence of transforms.

 Before calling doTransform, specify the sequence by calling addTransform().
 Each specified transforms take the output of the previous transform as input.

 The first defined transform takes the input images defined in the runTransform call(), the last defined transforms uses
  the output images defined in the tranTransform() call.


 @fn void TransformsChain::addTransform(Transform transform)
 @brief Add a transform to the transforms chain.

 The added Transform will take the output of the previously added transform as an input image and will supply its
  output to the next added transform or as an output of the TransformsChain if it is the last added Transform.

 @param transform the transform to be added to TransformsChain


 */