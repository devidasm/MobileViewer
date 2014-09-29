package com.example.testapp;

import java.util.Vector;

import android.content.Context;
import android.opengl.GLES30;

public class Sphere extends Mesh {
    /**
     * Sets up sphere data, including shaders
     */
    public Sphere(Context context, float radius, int stacks, int slices) {
    	  	
    	
    	//Variables for calculating vertices
    	float pi = (float)Math.PI;
    	float pi2 = pi * 2;
    	
    	float phiStep = pi/stacks;
    	float thetaStep = 2.0f * pi / slices;
    	
    	
    	//Create sphere vertices
    	Vector<Vertex> vertices = new Vector<Vertex>();
    	
    	//Colors are the same as the normals in this case. 
    	Vector<Float>  colors   = new Vector<Float>();
    	Vertex topVertex = new Vertex();
    	
    	topVertex.x = 0;
    	topVertex.y = radius;
    	topVertex.z = 0;
    
    	Vertex bottomVertex = new Vertex();
    	
    	bottomVertex.x = 0;
    	bottomVertex.y = -radius;
    	bottomVertex.z = 0;
    	
    	//Add the top vertex first
    	vertices.add(topVertex);
    	
    	
    	//Add in between vertices
    	for(int i = 1; i <= stacks -1; ++i)
    	{
    		float phi = i * phiStep;
    		
    		for(int j = 0; j <= slices; ++j)
    		{
    			float theta = j*thetaStep;
    			
    			Vertex v = new Vertex();
    			
    			v.x = (float) (radius * Math.sin(phi) * Math.cos(theta));
    			v.y = (float) (radius * Math.cos(phi));
    			v.z = (float) (radius * Math.sin(phi) * Math.sin(theta));
    			
    			colors.add(v.x);
    			colors.add(v.y);
    			colors.add(v.z);
    			colors.add(1.0f);
    			
    			//Texture coordinates now
    			v.u = theta / pi2;
    			v.v = phi / pi;
    			
    			vertices.add(v);
    			
    		}
    	}
    	
    	//Add the bottom vertex last
    	vertices.add(bottomVertex);
    	colors.add(bottomVertex.x);
		colors.add(bottomVertex.y);
		colors.add(bottomVertex.z);
		colors.add(1.0f);
    	
    	
    	//Indices
    	Vector<Short> indices = new Vector<Short>();
    	//Top indices
    	for(int i = 1; i <= slices; ++i)
    	{
    		indices.add((short)0);
    		indices.add((short) (i+1));
    		indices.add((short)i);
    	}
    	
    	//In between indices
    	int baseIndex = 1;
    	int ringVertexCount = slices + 1;
    	
    	for(int i = 0; i < stacks - 2; ++i)
    	{
    		for(int j = 0; j < slices; ++j)
    		{
    			indices.add((short) (baseIndex + i * ringVertexCount + j));
    			indices.add((short) (baseIndex + i * ringVertexCount + j + 1));
    			indices.add((short) (baseIndex + (i+1)*ringVertexCount + j));
    			
    			indices.add((short) (baseIndex + (i+1)*ringVertexCount +j));
    			indices.add((short) (baseIndex + i*ringVertexCount + j + 1));
    			indices.add((short) (baseIndex + (i+1)*ringVertexCount + j + 1));
    		}
    	}
    	
    	
    	//Bottom indices
    	int southPoleIndex = vertices.size() - 1;
    	
    	baseIndex = southPoleIndex - ringVertexCount;
    	
    	for(int i = 0; i < slices; ++i)
    	{
    		indices.add((short) southPoleIndex);
    		indices.add((short) (baseIndex + i));
    		indices.add((short) (baseIndex + i + 1));
    	}
    	
    	
    	//Put things into appropriate data structures...
    	
    	float[] verts = new float[vertices.size() * 3];
    	float[] color = new float[colors.size()];
    	float[] texc  = new float[vertices.size() * 2];
    	
    	int count = 0;
    	int countTex = 0;
    	for(int i = 0; i < vertices.size(); ++i)
    	{
    		Vertex ver = vertices.get(i);
    		
    		verts[count++] = ver.x;
    		verts[count++] = ver.y;
    		verts[count++] = ver.z;
    		
    		texc[countTex++] = ver.u;
    		texc[countTex++] = ver.v;
    	}
    	
    	setVertices(verts);
    	setTextureCoords(texc);
    	
    	short[] ind  = new short[indices.size()];
    	for(int i = 0; i < indices.size(); ++i)
    	{
    		ind[i] = indices.get(i);
    	}
    	
    	setIndices(ind);
    	
    	for(int i = 0; i < colors.size(); ++i)
    	{
    		color[i] = colors.get(i);
    	}
    	
    	
    	
    	float col[] = { 1.0f, 0.709803922f, 0.898039216f, 1.0f };
    	setColor(col[0], col[1], col[2], col[3]);
    	
    	setColors(color);
    	
    	
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
        
        loadTexture(context, 1);
    	    	
    }
}
