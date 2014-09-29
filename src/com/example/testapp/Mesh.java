package com.example.testapp;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLES30;
import android.opengl.GLUtils;

//Inspired from http://www.jayway.com/2010/02/15/opengl-es-tutorial-for-android-part-v/

public class Mesh {
	private FloatBuffer vertices = null;
	private FloatBuffer colorBuffer = null;
	private FloatBuffer texCoords = null;
	private ShortBuffer indices  = null;
	private int mProgram;
    private int mPositionHandle;
    private int mColorHandle;
    private int mColorAttHandle;
    private int mMVPMatrixHandle;
	private int numIndices = -1;
	private int COORDS_PER_VERTEX = 3;
	private int COLORS_PER_VERTEX = 4; //Color values per vertex
	private int textureID;
	float[] color = new float[4];
	
	
	int BYTES_PER_FLOAT = 4;
	int BYTES_PER_SHORT = 2;
	
	private int vbo[] = new int[1];
	private int ibo[] = new int[1];
	
	public void draw(float[] mvpMatrix) {
        // Add program to OpenGL environment
        GLES30.glUseProgram(mProgram);

        // get handle to vertex shader's vPosition member
        mPositionHandle = GLES30.glGetAttribLocation(mProgram, "vPosition");
        MyGLRenderer.checkGlError("glGetAttribLocation");
        
        mColorAttHandle = GLES30.glGetAttribLocation(mProgram, "vColorsAtt");
        MyGLRenderer.checkGlError("glGetAttribLocation");
        
        int vertexStride = COORDS_PER_VERTEX * 4; // 4 bytes per vertex value
        int colorstride  = COLORS_PER_VERTEX * 4; // 4 bytes per color value
        
        // Prepare the triangle coordinate data
        GLES30.glVertexAttribPointer(
                mPositionHandle, COORDS_PER_VERTEX,
                GLES30.GL_FLOAT, false,
                vertexStride, vertices);
        // Enable a handle to the triangle vertices
        GLES30.glEnableVertexAttribArray(mPositionHandle);
        
        // Prepare the triangle color data
        GLES30.glVertexAttribPointer(
        		mColorAttHandle, COLORS_PER_VERTEX,
                GLES30.GL_FLOAT, false,
                colorstride, colorBuffer);
        // Enable a handle to the triangle vertices
        GLES30.glEnableVertexAttribArray(mColorAttHandle);
        
        /*
         * Example code for uniform variables
         */
       /* // get handle to fragment shader's vColor member
        mColorHandle = GLES30.glGetUniformLocation(mProgram, "vColor");

        // Set color for drawing the triangle
        GLES30.glUniform4fv(mColorHandle, 1, color, 0);*/
        
        // get handle to shape's transformation matrix
        mMVPMatrixHandle = GLES30.glGetUniformLocation(mProgram, "uMVPMatrix");
        MyGLRenderer.checkGlError("glGetUniformLocation");

        // Apply the projection and view transformation
        GLES30.glUniformMatrix4fv(mMVPMatrixHandle, 1, false, mvpMatrix, 0);
        MyGLRenderer.checkGlError("glUniformMatrix4fv");
                
        // Draw the mesh
        GLES30.glDrawElements(
                GLES30.GL_TRIANGLES, numIndices,
                GLES30.GL_UNSIGNED_SHORT, indices);

        // Disable vertex array
        GLES30.glDisableVertexAttribArray(mPositionHandle);
    }
	
	protected void setVertices(float[] verts)
	{
		ByteBuffer vertBuff = ByteBuffer.allocateDirect(verts.length * 4);
		vertBuff.order(ByteOrder.nativeOrder());	
		
		vertices = vertBuff.asFloatBuffer();
		vertices.put(verts);
		vertices.position(0);
		
		GLES30.glGenBuffers(1, vbo, 0);
		
		GLES30.glBindBuffer(GLES30.GL_ARRAY_BUFFER, vbo[0]);
		GLES30.glBufferData(GLES30.GL_ARRAY_BUFFER, vertices.capacity() * BYTES_PER_FLOAT, vertices, GLES30.GL_STATIC_DRAW);
		GLES30.glBindBuffer(GLES30.GL_ARRAY_BUFFER, 0);
	}
	
	protected void setIndices(short[] ind)
	{
		ByteBuffer indBuff = ByteBuffer.allocateDirect(ind.length * 2);
		indBuff.order(ByteOrder.nativeOrder());
		
		indices = indBuff.asShortBuffer();
		indices.put(ind);
		indices.position(0);
		
		GLES30.glGenBuffers(1, ibo, 0);
		
		GLES30.glBindBuffer(GLES30.GL_ELEMENT_ARRAY_BUFFER, ibo[0]);
		GLES30.glBufferData(GLES30.GL_ELEMENT_ARRAY_BUFFER, indices.capacity() * BYTES_PER_SHORT, indices, GLES30.GL_STATIC_DRAW);
		GLES30.glBindBuffer(GLES30.GL_ELEMENT_ARRAY_BUFFER, 0);
		numIndices = ind.length;
	}
	
	protected void setTextureCoords(float[] texC)
	{
		ByteBuffer texBuff = ByteBuffer.allocateDirect(texC.length * 4);
		texBuff.order(ByteOrder.nativeOrder());	
		
		texCoords = texBuff.asFloatBuffer();
		texCoords.put(texC);
		texCoords.position(0);
	}
	
	protected void setShaders(int vertexShader, int fragmentShader)
	{
        mProgram = GLES30.glCreateProgram();             // create empty OpenGL Program
        GLES30.glAttachShader(mProgram, vertexShader);   // add the vertex shader to program
        GLES30.glAttachShader(mProgram, fragmentShader); // add the fragment shader to program
        GLES30.glLinkProgram(mProgram);                  // create OpenGL program executables
	}
	
	protected void setColor(float r, float g, float b, float a)
	{
		color[0] = r;
		color[1] = g;
		color[2] = b;
		color[3] = a;
	}
	
	protected void setColors(float[] colors)
	{
		ByteBuffer cbb = ByteBuffer.allocateDirect(colors.length * 4);
	    cbb.order(ByteOrder.nativeOrder());
	    colorBuffer = cbb.asFloatBuffer();
	    colorBuffer.put(colors);
	    colorBuffer.position(0);
	}
	
	protected void loadTexture(Context context, int resourceId)
	{

		AssetManager assman = context.getAssets();

			//Read in the resource
			try{
				final Bitmap bitmap = BitmapFactory.decodeStream(assman.open("textures/soccer.jpg"));
				
				final int [] textureHandle = new int[1];
				
				int c = bitmap.getPixel(200, 200);
				int x = c;
				GLES30.glGenTextures(1, textureHandle, 0);
				textureID = textureHandle[0];
				
				GLES30.glBindTexture(GLES30.GL_TEXTURE_2D_ARRAY, textureID);
				
				
			} catch(IOException e)
			{
				
			}
	}
}

