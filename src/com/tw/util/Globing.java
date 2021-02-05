package com.tw.util;

import java.nio.ByteBuffer;

public class Globing {
	  public static byte getsum(ByteBuffer b,int start,int length)
    {
    	byte result =b.get(start);
    	for(int i= 1;i<length;i++)
    	{
    		result = (byte)(result+b.get(start+i));
    	}
    	return result;
    }
}
