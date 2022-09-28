package com.sanalstil.pUtils;

public class cOS
{
	public static boolean IsLinux()
	{
	    return System.getProperty("os.name").matches(".*Linux.*");
	}
	
	public static boolean IsWindows()
	{
		return System.getProperty("os.name").matches(".*Windows.*");        
	}
}
