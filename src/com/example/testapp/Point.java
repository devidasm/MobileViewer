package com.example.testapp;


/*
 * This class is meant to be like a 3 float member data structure. Can do vector like operations.
 */
public class Point {
	float x = 0;
	float y = 0;
	float z = 0;
	
	public float length()
	{		
		return (float) Math.sqrt(x*x + y*y);	
	}
	
	public void normalize()
	{
		float l = this.length();
		
		x = x/l;
		y = y/l;
		z = z/l;
	}
	
	public Point sub(Point that)
	{
		Point res = new Point();
		
		res.x = this.x - that.x;
		res.y = this.y - that.y;
		res.z = this.z - that.z;
		
		return res;
	}
	
	public Point add(Point that)
	{
		Point res = new Point();
		
		res.x = this.x + that.x;
		res.y = this.y + that.y;
		res.z = this.z + that.z;
		
		return res;
	}
	
	public Point mult(Point that)
	{
		Point res = new Point();
		
		res.x = this.x * that.x;
		res.y = this.y * that.y;
		res.z = this.z * that.z;
		
		return res;
	}
	
	public float dot(Point that)
	{
		return this.x * that.x + this.y * that.y + this.z * that.z;
	}
	
	public Point cross(Point that)
	{
		Point res = new Point();
		
		res.x = this.y * that.z - this.z * that.y;
		res.y = this.z * that.x - this.x * that.z;
		res.z = this.x * that.y - this.y * that.x;
		
		return res;
	}
	
}
