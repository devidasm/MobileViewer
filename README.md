MobileViewer

This uses Nvidia's Android Development Kit, which can be found on their developer website (free account required)

============
9/29/2014
Initial commit.

Overall:
- Right now, simple UI with some basic app functionality. OpenGL ES setup with some framework done for meshes and shaders.

Trackball:
- A bit wonky but it's functional. For now, will leave alone until adding more interaction functionality.

GFX stuff:
- So far, just have a base framework for meshes right now. In reality, spheres are the only mesh to be drawn at the moment. Sufficient for now.
- Shaders are very basic right now (only implemented for spheres, really). Has the following functionality:
  *MVP matrix
  *A color buffer (which the colors are actually the normals of the sphere)

TODO:
- Look into DICOM libraries for Android
- Get 2D textures to work, then move onto 3D textures
- Make some sort of interface that overlays OpenGL ES screen to add switches/scroll bars to add interactivity.

============
9/30/2014

Got a DICOM decoder to work, right now works with a simple .DCM file. Soon, I'll add so all .DCM files will be viewable that's on the SD card.

Side note on some Android quirks:

Do not add a library as an "external library", for this will allow it to appear fine on the computer, but the library will not be packaged into the APK. Include any libraries that you need into the 'libs' folder and then you can reference anything you need from there. 
