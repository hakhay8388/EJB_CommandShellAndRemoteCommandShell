package com.sanalstil.pEJB.pDatabase.pOracle.pBaseDBPackage;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.sanalstil.pEJB.pDatabase.pOracle.cOracleDB;
import com.sanalstil.pEJB.pStartup.cStartup;

public abstract class cBaseDBPackage 
{
	public cOracleDB OrcaleDB = null;
	
	public cBaseDBPackage(cOracleDB _OrcaleDb) 
	{
		OrcaleDB = _OrcaleDb;
	}
	
	public abstract void LoadSubPackage();
	
	
	public boolean CloseConnection(ResultSet _ResultSet, CallableStatement _CallableStatement, Connection _Connection)
	{
		boolean __Result = true;
		if (_ResultSet != null)
		{
	        try
	        {
	        	_ResultSet.close();
			}
			catch (SQLException _Ex) 
			{
	        	cStartup.ServerLogger.ConsolError("Error..! -> " + this.getClass().getName() + " -> ResultSet.close()");
	        	cStartup.ServerLogger.FileError("Error..! -> " + this.getClass().getName() + " -> ResultSet.close()", _Ex);
	        	__Result = false;
			}
		}
		
		if (_CallableStatement != null)
		{
	        try
	        {
	        	_CallableStatement.close();
			}
			catch (SQLException _Ex) 
			{
	        	cStartup.ServerLogger.ConsolError("Error..! -> " + this.getClass().getName() + " -> CallableStatement.close()");
	        	cStartup.ServerLogger.FileError("Error..! -> " + this.getClass().getName() + " -> CallableStatement.close()", _Ex);
	        	__Result = false;
			}
		}
		
		
		if (_Connection != null)
		{
	        try
	        {
	        	_Connection.close();
			}
			catch (SQLException _Ex) 
			{
	        	cStartup.ServerLogger.ConsolError("Error..! -> " + this.getClass().getName() + " -> Connection.close()");
	        	cStartup.ServerLogger.FileError("Error..! -> " + this.getClass().getName() + " -> Connection.close()", _Ex);
	        	__Result = false;
			}
		}
		return __Result;
	}
}
