package com.sanalstil.pEJB.pStartup;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.DailyRollingFileAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.xml.DOMConfigurator;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.sanalstil.pEJB.pDatabase.cDatabase;
import com.sanalstil.pLogManager.cLogManager;
import com.sanalstil.pLogManager.cLogger;
import com.sanalstil.pUtils.cFile;
import com.sanalstil.pUtils.cOS;

public class cStartup 
{
	public static boolean Loaded = false;
	public static JSONObject Settings;
	public static Properties InitFile;
	public static String MergedLogPath;
	public static String LogPath;
	public static String MergedLogFileName = "AllLogs";
	public static cLogManager LogManager = null;
	public static cLogger ServerLogger = null;

	
	private static Logger logger = Logger.getLogger(cStartup.class);
    
	public static void Initialize()
	{
		if (!Loaded)
		{
			Loaded = false;
			LoadInitFiles();
			InitializeLoggers();
			cDatabase.Load();
		}
	}
	
	public static void InitializeLoggers()
	{
		if (cOS.IsLinux())
		{
			MergedLogPath = Settings.get("linuxLogPath")+InitFile.getProperty("parent")+"/"+InitFile.getProperty("name")+"/"+InitFile.getProperty("type")+"/v"+InitFile.getProperty("version")+"/mergedFileLog/";
			LogManager = new cLogManager(MergedLogFileName, MergedLogPath);
			LogPath = Settings.get("linuxLogPath")+InitFile.getProperty("parent")+"/"+InitFile.getProperty("name")+"/"+InitFile.getProperty("type")+"/v"+InitFile.getProperty("version")+"/fileLog/";
			ServerLogger = LogManager.GetLogger("ServerLog", LogPath); 
		}
		if (cOS.IsWindows())
		{
			MergedLogPath = Settings.get("windowsLogPath")+InitFile.getProperty("parent")+"\\"+InitFile.getProperty("name")+"\\"+InitFile.getProperty("type")+"\\v"+InitFile.getProperty("version")+"\\mergedFileLog\\";
			LogManager = new cLogManager(MergedLogFileName, MergedLogPath);
			LogPath = Settings.get("windowsLogPath")+InitFile.getProperty("parent")+"\\"+InitFile.getProperty("name")+"\\"+InitFile.getProperty("type")+"\\v"+InitFile.getProperty("version")+"\\fileLog\\";
			ServerLogger = LogManager.GetLogger("ServerLog", LogPath);			
		}
	}

	public static String GetInitFilePath()
	{
		if (cOS.IsWindows())
		{
			File __InitFile = new File(System.getProperty("jboss.server.home.dir"));
			String __Path = __InitFile.getAbsolutePath();
			return __Path + "\\conf\\EJB-Server\\init.properties";
		}
		if (cOS.IsLinux())
		{
			File __InitFile = new File(System.getProperty("jboss.server.home.dir"));
			String __Path = __InitFile.getAbsolutePath();
			return __Path + "/conf/EJB-Server/init.properties";
		}
		return "";
	}
	
	public static void LoadInitFiles()
	{
		InitFile = new Properties();
		
	    try 
	    {
	    	InitFile.load(new FileInputStream(GetInitFilePath()));
	    } 
	    catch (IOException ex) 
	    {
	    	System.out.println("Init File Can Not Load..!");
	    	ex.printStackTrace();
	    }
	    
	    String tPath = "";
        if(System.getProperty("os.name").matches(".*Windows.*"))
        {
        	tPath = InitFile.getProperty("settingsFilePathWIN");
        }
        else if(System.getProperty("os.name").matches(".*Linux.*"))
        {
        	tPath = InitFile.getProperty("settingsFilePathUNIX");
        }
        
	    String tFile = tPath+InitFile.getProperty("name")+"/"+InitFile.getProperty("type")+"/v"+InitFile.getProperty("version")+"/"+InitFile.getProperty("settingsFileName");
        
        if(cFile.Exists(tFile))
        {
        	String __Content = cFile.GetContents(tFile);
        	Settings = (JSONObject) JSONValue.parse(__Content);;
        }
        else
        {
        	System.out.println("Setting File Not Found..!");
        }
	}	
}
