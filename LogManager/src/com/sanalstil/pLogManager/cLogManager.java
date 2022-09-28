package com.sanalstil.pLogManager;

import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.DailyRollingFileAppender;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;



public class cLogManager 
{
	public List<cLogger> LoggerList = null;
	
	public Logger MergedLogger = null;
	public Logger ConsoleLogger = null;
	public Logger fileLogger = null;
	public boolean PrintExceptionStackTraceToScreen = true;
	
	public cLogManager(String _MergedFileName, String _MergedFilePath)
	{
		LoggerList = new LinkedList<cLogger>();
		ConsoleLogger =  Logger.getLogger("ConsoleLogger");
		System.out.println("Setting ConsoleLogger...");
	    try {
	    	ConsoleLogger.addAppender(new ConsoleAppender(new PatternLayout("%d %-5p: %m%n")));
	         System.out.println("-> Done.");
	    } catch(Exception e) {
	    	System.out.println("-> Error.");
	    	e.printStackTrace();
	    }    
		MergedLogger =  Logger.getLogger("MergedFileLogger");
		System.out.println("Setting MergedFileLogger...");
	    try 
	    {
	    	 MergedLogger.addAppender(new DailyRollingFileAppender(new PatternLayout("%d %-5p: %m%n"),_MergedFilePath + _MergedFileName,"_yyyy-MM-dd"));
	         System.out.println("-> Done.");
	    } 
	    catch(Exception e) 
	    {
	    	System.out.println("-> Error.");
	    	e.printStackTrace();
	    }    
	}

	public cLogger GetLogger(String _LoggerName, String _LoggerPath)
	{
		cLogger __Logger = GetLoggerByName(_LoggerName, _LoggerPath);
		if (__Logger != null)
		{
			return __Logger;
		}
		else
		{
			__Logger = new cLogger(this, _LoggerName, _LoggerPath);
			return __Logger;			
		}
	}
	
	public cLogger GetLoggerByName(String _LoggerName, String _LoggerPath)
	{
		for (int i = 0; i < LoggerList.size();i++)
		{
			if (LoggerList.get(i).Name.equals(_LoggerName) && LoggerList.get(i).equals(_LoggerPath))
			{
				return LoggerList.get(i);
			}
		}
		return null;
	}
	
}
