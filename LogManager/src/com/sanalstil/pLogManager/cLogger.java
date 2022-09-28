package com.sanalstil.pLogManager;

import org.apache.log4j.DailyRollingFileAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;


public class cLogger
{
	private Logger FileLogger = null;

	public String Name;
	public String LogPath;
	public cLogManager LogManager;
	
	public cLogger(cLogManager _LogManager, String _LoggerName, String _LoggerPath)
	{
		Name = _LoggerName;
		LogPath = _LoggerPath;
		LogManager = _LogManager;
		InitializeLogger();
		LogManager.LoggerList.add(this);
	}
	
	public void InitializeLogger()
	{
		LogManager.ConsoleLogger.info("Setting FileLogger...");
	    try {
	    	 FileLogger = Logger.getLogger(Name);
	         FileLogger.addAppender(new DailyRollingFileAppender(new PatternLayout("%d %-5p: %m%n"),LogPath + Name,"_yyyy-MM-dd"));
	         LogManager.ConsoleLogger.info("-> Done.");	         
	    } catch(Exception e) {
	    	LogManager.ConsoleLogger.info("-> Error.");
	    	e.printStackTrace();	    	
	    }
	}

	public void ConsolInfo(String _Message)
	{
		LogManager.ConsoleLogger.info(_Message);
	}
	
	public void ConsolError(String _Message)
	{
		LogManager.ConsoleLogger.error(_Message);
	}

	public void ConsolWarning(String _Message)
	{
		LogManager.ConsoleLogger.warn(_Message);
	}
	
	public void ConsolFatal(String _Message)
	{
		LogManager.ConsoleLogger.fatal(_Message);
	}
	
	public void FileInfo(String _Message)
	{
		FileLogger.info(_Message);
		LogManager.MergedLogger.info(_Message);
	}

	public void FileError(String _Message)
	{
		FileLogger.error(_Message);
		LogManager.MergedLogger.error(_Message);
	}
	
	public void FileError(String _Message, Exception _Ex)
	{
		FileLogger.error(_Message);
		LogManager.MergedLogger.error(_Message);
		if (_Ex != null)
		{
			if (LogManager.PrintExceptionStackTraceToScreen)
			{
				_Ex.printStackTrace();
			}
			LogManager.MergedLogger.error(_Message);
			LogManager.MergedLogger.error(_Message, _Ex);
		}
	}
	
	public void FileWarning(String _Message)
	{
		FileLogger.warn(_Message);
		LogManager.MergedLogger.warn(_Message);
	}
	
	public void FileWarning(String _Message, Exception _Ex)
	{
		FileLogger.warn(_Message);
		LogManager.MergedLogger.warn(_Message);
		if (_Ex != null)
		{
			if (LogManager.PrintExceptionStackTraceToScreen)
			{
				_Ex.printStackTrace();
			}
			LogManager.MergedLogger.warn(_Message);
			LogManager.MergedLogger.warn(_Message, _Ex);
		}
	}
	
	public void FileFatal(String _Message)
	{
		FileLogger.fatal(_Message);
		LogManager.MergedLogger.fatal(_Message);
	}
	
	public void FileFatal(String _Message, Exception _Ex)
	{
		FileLogger.fatal(_Message);
		LogManager.MergedLogger.fatal(_Message);
		if (_Ex != null)
		{
			if (LogManager.PrintExceptionStackTraceToScreen)
			{
				_Ex.printStackTrace();
			}
			LogManager.MergedLogger.fatal(_Message);
			LogManager.MergedLogger.fatal(_Message, _Ex);			
		}
	}
}
