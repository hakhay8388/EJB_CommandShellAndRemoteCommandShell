package com.sanalstil.pEJB.pDatabase.pOracle;


import java.sql.SQLException;

import org.json.simple.JSONObject;

import com.sanalstil.pEJB.pDatabase.pOracle.pIddaa.cIddaa;
import com.sanalstil.pEJB.pStartup.cStartup;


import oracle.jdbc.pool.OracleDataSource;


public class cOracleDB
{
	public OracleDataSource DataSource;
	public cIddaa Iddaa = null;

	
	public cOracleDB() 
	{
		LoadOracleDB();
		LoadPackage();
	}
	
	private OracleDataSource LoadOracleDB()
	{
		JSONObject tConfig = null;
		JSONObject tObjDataOracle =  (JSONObject) cStartup.Settings.get("dataOracle");
		
		tConfig = (JSONObject) tObjDataOracle.get("ebahis");
		cStartup.ServerLogger.ConsolInfo("Loading Oracle Connection...");
		cStartup.ServerLogger.FileInfo("Loading Oracle Connection...");
		try 
		{
			DataSource = new OracleDataSource();
			DataSource.setURL("jdbc:oracle:thin:@"+tConfig.get("host").toString()+":"+tConfig.get("port").toString()+":"+tConfig.get("sid").toString());
			DataSource.setUser(tConfig.get("user").toString());
			DataSource.setPassword(tConfig.get("pass").toString());
			cStartup.ServerLogger.FileInfo("Loading Oracle Connection...-> Done.");
			cStartup.ServerLogger.ConsolInfo("Loading Oracle Connection...-> Done.");
		} 
		catch (SQLException _Ex) 
		{
			cStartup.ServerLogger.FileError("Loading Oracle Connection...-> Error.", _Ex);
			cStartup.ServerLogger.ConsolError("Loading Oracle Connection...-> Error.");
		}
		return DataSource;
	}
	
	public void LoadPackage()
	{
		try
		{
			cStartup.ServerLogger.FileInfo("Loading Oracle Database Packages...");
			cStartup.ServerLogger.ConsolInfo("Loading Oracle Database Packages...");
			Iddaa = new cIddaa(this);
			cStartup.ServerLogger.ConsolInfo("Loading Oracle Database Packages... -> Done.");
			cStartup.ServerLogger.FileInfo("Loading Oracle Database Packages... -> Done.");
		}
		catch(Exception _Ex)
		{
			cStartup.ServerLogger.ConsolError("Loading Oracle Database Packages... -> Error.");
			cStartup.ServerLogger.FileError("Loading Oracle Database Packages... -> Error.", _Ex);
		}
	}
	

}
