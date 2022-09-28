package pDatabase;

import org.json.simple.JSONObject;

import pAppMain.cAppMain;
import pDatabase.pMongoDatabase.cMongoDatabase;

import com.sanalstil.pEjb.pClient.pRemoteClasses.pRemoteDatabase.cRemoteDatabase;

public class cDatabase 
{
	public static cRemoteDatabase RemoteDatabase = null;
	public static cMongoDatabase MongoDatabase = null;
	
	public static void Initialize(JSONObject _Settings)
	{
		try
		{
			cAppMain.ApplicationLogger.FileInfo("Remote Database Loading.");
			cAppMain.ApplicationLogger.ConsolInfo("Remote Database Loading...");
			RemoteDatabase = new cRemoteDatabase();
			cAppMain.ApplicationLogger.ConsolInfo("-> Done");
			cAppMain.ApplicationLogger.FileInfo("-> Done");
		}
		catch(Exception ex)
		{
			cAppMain.ApplicationLogger.ConsolError("-> Error");
			cAppMain.ApplicationLogger.FileError("-> Error", ex);
		}		
 
		try
		{
			cAppMain.ApplicationLogger.FileInfo("Mongo Database Loading.");
			cAppMain.ApplicationLogger.ConsolInfo("Mongo Database Loading...");
			MongoDatabase = new cMongoDatabase(_Settings);
			cAppMain.ApplicationLogger.ConsolInfo("-> Done");
			cAppMain.ApplicationLogger.FileInfo("-> Done");
		}
		catch(Exception ex)
		{
			cAppMain.ApplicationLogger.ConsolError("-> Error");
			cAppMain.ApplicationLogger.FileError("-> Error",ex);

		}

	}
}
