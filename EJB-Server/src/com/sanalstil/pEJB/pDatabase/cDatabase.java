package com.sanalstil.pEJB.pDatabase;

import com.sanalstil.pEJB.pDatabase.pOracle.cOracleDB;



public class cDatabase 
{
	public static boolean DatabaseLoaded = false;
	public static cOracleDB Oracle = null;
	
	public static void Load() 
	{
		if (!DatabaseLoaded)
		{
			DatabaseLoaded = true;
			LoadDatabases();
		}
	} 
	
	private static void LoadDatabases()
	{
		Oracle = new cOracleDB();
	}
}
