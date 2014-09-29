/*
 * Copyright (C) 2011 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.testapp;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import android.content.Context;
import android.content.res.AssetManager;
import android.opengl.GLES30;

/**
 * A two-dimensional square for use as a drawn object in OpenGL ES 2.0.
 */
public class Square extends Mesh{


    /**
     * Sets up the drawing object data for use in an OpenGL ES context.
     */
    public Square(Context context) {
     
    	//Set the vertex buffer
        // number of coordinates per vertex in this array
        float squareCoords[] = {
                -0.5f,  0.5f, 0.0f,   // top left
                -0.5f, -0.5f, 0.0f,   // bottom left
                 0.5f, -0.5f, 0.0f,   // bottom right
                 0.5f,  0.5f, 0.0f }; // top right
        
        setVertices(squareCoords);        

        //Set the index buffer
        short drawOrder[] = { 0, 1, 2, 0, 2, 3 }; // order to draw vertices
        setIndices(drawOrder);

        float color[] = { 0.2f, 0.709803922f, 0.898039216f, 1.0f };
    	setColor(color[0], color[1], color[2], color[3]);
        
        ///////Shader stuff//////
        String vShader = MyGLRenderer.readShader("shaders/vertexShader", context);
        String pShader = MyGLRenderer.readShader("shaders/pixelShader", context);
               
        // prepare shaders and OpenGL program
        int vertexShader = MyGLRenderer.loadShader(
                GLES30.GL_VERTEX_SHADER,
                vShader);
        int fragmentShader = MyGLRenderer.loadShader(
                GLES30.GL_FRAGMENT_SHADER,
                pShader);
        
        setShaders(vertexShader, fragmentShader);
    }
}