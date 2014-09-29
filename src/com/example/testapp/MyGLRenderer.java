package com.example.testapp;

import java.io.IOException;
import java.io.InputStream;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.content.res.AssetManager;
import android.opengl.GLES20;
import android.opengl.GLES30;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import android.util.Log;

public class MyGLRenderer implements GLSurfaceView.Renderer{

    // mMVPMatrix is an abbreviation for "Model View Projection Matrix"
    private final float[] mMVPMatrix = new float[16];
    private final float[] mProjectionMatrix = new float[16];
    private final float[] mViewMatrix = new float[16];
    private final float[] mRotationMatrix = new float[16];
    private final float[] mRotationOnGoingMatrix = new float[16];
    
    
    private float mAngle, mOnGoingAngle;
    private Point rotAxis = new Point();
    
	Square mSquare;
	Sphere mSphere;
	
	Context con;
	
	
	public MyGLRenderer(Context context)
	{
		con = context;	
		rotAxis.x = 0;
		rotAxis.y = 0;
		rotAxis.z = 1;
		
		Matrix.setRotateM(mRotationOnGoingMatrix, 0, 0, rotAxis.x, rotAxis.y, rotAxis.z);
	}
	
	
	private static final String TAG = "MyGLRenderer";
	@Override
	public void onDrawFrame(GL10 gl) {
		float[] mat = new float[16];
        // Draw background color
        GLES20.glClear(GLES30.GL_COLOR_BUFFER_BIT | GLES30.GL_DEPTH_BUFFER_BIT);

        // Set the camera position (View matrix)
        Matrix.setLookAtM(mViewMatrix, 0, 0, 0, -3, 0f, 0f, 0f, 0f, 1.0f, 0.0f);

        // Calculate the projection and view transformation
        Matrix.multiplyMM(mMVPMatrix, 0, mProjectionMatrix, 0, mViewMatrix, 0);
        
        Matrix.setRotateM(mRotationMatrix, 0, mAngle, rotAxis.x, rotAxis.y, rotAxis.z);
        
        Matrix.multiplyMM(mRotationOnGoingMatrix, 0, mRotationMatrix, 0, mRotationOnGoingMatrix, 0);
        
        Matrix.multiplyMM(mat,  0, mMVPMatrix, 0, mRotationOnGoingMatrix, 0);
        // Draw square
        //mSquare.draw(mat);
        // Draw sphere
        mSphere.draw(mat);
	}

	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) {
        // Adjust the viewport based on geometry changes,
        // such as screen rotation
        GLES30.glViewport(0, 0, width, height);

        float ratio = (float) width / height;

        // this projection matrix is applied to object coordinates
        // in the onDrawFrame() method
        Matrix.frustumM(mProjectionMatrix, 0, -ratio, ratio, -1, 1, 3, 7);
	}

	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		// TODO Auto-generated method stub
		GLES30.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
		
		// initialize a triangle
	   // mTriangle = new Triangle();
	    // initialize a square
	    mSquare = new Square(con);
	    mSphere = new Sphere(con, 1.0f, 20, 20);    
	}
	
    /**
    * Utility method for debugging OpenGL calls. Provide the name of the call
    * just after making it:
    *
    * <pre>
    * mColorHandle = GLES30.glGetUniformLocation(mProgram, "vColor");
    * MyGLRenderer.checkGlError("glGetUniformLocation");</pre>
    *
    * If the operation is not successful, the check throws an error.
    *
    * @param glOperation - Name of the OpenGL call to check.
    */
    public static void checkGlError(String glOperation) {
        int error;
        while ((error = GLES30.glGetError()) != GLES30.GL_NO_ERROR) {
            Log.e(TAG, glOperation + ": glError " + error);
            throw new RuntimeException(glOperation + ": glError " + error);
        }
    }
    
    /**
     * Utility method for compiling a OpenGL shader.
     *
     * <p><strong>Note:</strong> When developing shaders, use the checkGlError()
     * method to debug shader coding errors.</p>
     *
     * @param type - Vertex or fragment shader type.
     * @param shaderCode - String containing the shader code.
     * @return - Returns an id for the shader.
     */
    public static int loadShader(int type, String shaderCode){

        // create a vertex shader type (GLES30.GL_VERTEX_SHADER)
        // or a fragment shader type (GLES30.GL_FRAGMENT_SHADER)
        int shader = GLES30.glCreateShader(type);

        // add the source code to the shader and compile it
        GLES30.glShaderSource(shader, shaderCode);
        GLES30.glCompileShader(shader);

        return shader;
    }
    
    public static String readShader(String name, Context con)
    {

        AssetManager assetManager = con.getAssets();
        String shaderText = new String();
        try{
        	InputStream shader = assetManager.open(name);
        	
        	int size = shader.available();
        	
        	byte[] buffer = new byte[size];
        	
        	shader.read(buffer);
        	shader.close();
        	
        	//Change the byte buffer into a string
        	shaderText = new String(buffer);
        	        	
        }
        catch (IOException e1){
        	
        }
        
        return shaderText;
    }
    
    
    
    /**
     * Returns the rotation angle of the triangle shape (mTriangle).
     *
     * @return - A float representing the rotation angle.
     */
    public float getAngle() {
        return mAngle;
    }

    /**
     * Sets the rotation angle.
     */
    public void setAngle(float angle) {
        mAngle = angle;
    }
    
    /**
     * Sets the rotation axis.
     */
    public void setRotAxis(Point rot) {
        rotAxis = rot;
    }

}
