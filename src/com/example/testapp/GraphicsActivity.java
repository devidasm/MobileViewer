package com.example.testapp;

import android.app.Activity;
import android.content.res.AssetManager;
import android.opengl.*;
import android.os.Bundle;

public class GraphicsActivity extends Activity {

	 private GLSurfaceView mGLView;

	 @Override
	 public void onCreate(Bundle savedInstanceState) {
		 super.onCreate(savedInstanceState);

         // Create a GLSurfaceView instance and set it
         // as the ContentView for this Activity.
         mGLView = new MyGLSurfaceView(this);         
         setContentView(mGLView);
     }
}
