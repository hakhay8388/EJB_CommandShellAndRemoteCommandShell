package pAppMain;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Locale;
import java.util.Properties;


import com.sanalstil.pEjb.pClient.pRemoteClasses.pBaseRemoteClass.cBaseRemoteClass;
import com.sanalstil.pLogManager.cLogManager;
import com.sanalstil.pLogManager.cLogger;
import com.sanalstil.pUtils.cFile;
import com.sanalstil.pUtils.cOS;

import pCommandShell.cCommandShell;
import pDatabase.cDatabase;


import org.json.simple.JSONObject;
import org.json.simple.JSONValue;



public class cAppMain {

	public static Properties InitFile = null;
	public static JSONObject Settings = null;
	public static String MergedLogFileName = "AllLogs";
	public static String MergedLogPath = "";
	public static String LogPath = "";
	public static cLogManager LogManager = null;
	public static cLogger ApplicationLogger = null;
	
	public static void main(String[] args) 
	{
		LoadInitFiles("init.properties");
		Locale newLocale = new Locale("tr", "TR");
		Locale.setDefault(newLocale);
		InitializeLoggers();
		InitializeEjbClient();
		cDatabase.Initialize(Settings);
		cCommandShell.Initialize();
		cCommandShell.StartShell();
	}
	
	public static void InitializeLoggers()
	{
		if (cOS.IsWindows())
		{
			MergedLogPath = Settings.get("windowsLogPath")+InitFile.getProperty("parent")+"\\"+InitFile.getProperty("name")+"\\"+InitFile.getProperty("type")+"\\v"+InitFile.getProperty("version")+"\\mergedFileLog\\";
			LogManager = new cLogManager(MergedLogFileName, MergedLogPath);
			LogManager.PrintExceptionStackTraceToScreen = false;
			LogPath = Settings.get("windowsLogPath")+InitFile.getProperty("parent")+"\\"+InitFile.getProperty("name")+"\\"+InitFile.getProperty("type")+"\\v"+InitFile.getProperty("version")+"\\fileLog\\";
			ApplicationLogger = LogManager.GetLogger("ApplicationLog", LogPath);			
		}
		if (cOS.IsLinux())
		{
			MergedLogPath = Settings.get("linuxLogPath")+InitFile.getProperty("parent")+"/"+InitFile.getProperty("name")+"/"+InitFile.getProperty("type")+"/v"+InitFile.getProperty("version")+"/mergedFileLog/";
			LogManager = new cLogManager(MergedLogFileName, MergedLogPath);
			LogManager.PrintExceptionStackTraceToScreen = false;
			LogPath = Settings.get("linuxLogPath")+InitFile.getProperty("parent")+"/"+InitFile.getProperty("name")+"/"+InitFile.getProperty("type")+"/v"+InitFile.getProperty("version")+"/fileLog/";
			ApplicationLogger = LogManager.GetLogger("ApplicationLog", LogPath); 
		}
	}

	
	public static void LoadInitFiles(String _InitPropertyFilePath)
	{
		InitFile = new Properties();
		
	    try 
	    {
	    	InitFile.load(new FileInputStream(_InitPropertyFilePath));
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
	
	public static void InitializeEjbClient()
	{
		ApplicationLogger.ConsolInfo("Ejb Client is Loading...");
		Properties __EjbSetting = new Properties();
	    try 
	    {
	    	ApplicationLogger.FileInfo("Ejb Client Loading...");
	    	__EjbSetting.load(new FileInputStream("EjbSetting.properties"));
		    String __UserName = __EjbSetting.getProperty("username");
		    String __Password = __EjbSetting.getProperty("password");
			cBaseRemoteClass.Initialize(__UserName, __Password, LogManager, LogPath);
			ApplicationLogger.FileInfo("-> Done");
			ApplicationLogger.ConsolInfo("-> Done");
	    } 
	    catch (IOException _Ex) 
	    {
	    	ApplicationLogger.FileError("-> Error",_Ex);
	    	ApplicationLogger.ConsolInfo("-> Error");
	    }
	    catch(Exception _Ex)
	    {
	    	ApplicationLogger.FileError("-> Error",_Ex);
	    	ApplicationLogger.ConsolInfo("-> Error");
	    }	    
	}

}
