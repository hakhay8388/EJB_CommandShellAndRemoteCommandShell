package com.sanalstil.pEJB.pDatabase.pOracle.pIddaa.pBetList.pAthletism;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.simple.JSONArray;

import oracle.jdbc.OracleTypes;
import oracle.jdbc.OracleCallableStatement;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.sanalstil.pEJB.pDatabase.pOracle.cOracleDB;
import com.sanalstil.pEJB.pDatabase.pOracle.pBaseDBPackage.cBaseDBPackage;
import com.sanalstil.pEJB.pDatabase.pResultObjects.cDataJsonResult;
import com.sanalstil.pEJB.pDatabase.pUtils.cResultSetConverter;
import com.sanalstil.pEJB.pStartup.cStartup;

public class cAthletism  extends cBaseDBPackage 
{
	public cAthletism(cOracleDB _OrcaleDb)
	{
		super(_OrcaleDb);
		LoadSubPackage();
	}

	@Override
	public void LoadSubPackage()
	{
		// TODO Auto-generated method stub
	}
	
	public cDataJsonResult GetLiveMatchProgram()
	{
		return new cDataJsonResult();
	}
	
	public cDataJsonResult GetMatchProgram()
	{
		cDataJsonResult __Result = new cDataJsonResult();
		Connection __Connection = null;
        CallableStatement __PrepareCall = null;
        ResultSet __ResultSet = null;
        try {
        	
        	cStartup.ServerLogger.FileInfo("Called cAthletism->GetMatchProgram()");
        	
        	__Connection = OrcaleDB.DataSource.getConnection();
           	__PrepareCall = __Connection.prepareCall("begin EBAHIS.EMI_P_IDDAA_BETLIST_v2.getAthletism(?); end;");
           	__PrepareCall.registerOutParameter(1, OracleTypes.CURSOR);
           	__PrepareCall.execute();
           	

			__ResultSet = ((OracleCallableStatement) __PrepareCall).getCursor(1);
			BasicDBList tData = cResultSetConverter.ResultSet_To_BasicDBList(__ResultSet);
			__Result.dataObject.put("rows", tData);
			
			cStartup.ServerLogger.FileInfo("Called cAthletism->GetMatchProgram() -> SUCCESS");
		}
		catch (SQLException _Ex) 
		{
        	cStartup.ServerLogger.ConsolError("Error..! -> com.sanalstil.pEJB.pDatabase.pOracle.pIddaa.pBetList.pAthletism.cAthletism.GetMatchProgram()");
        	cStartup.ServerLogger.FileError("Error..! -> com.sanalstil.pEJB.pDatabase.pOracle.pIddaa.pBetList.pAthletism.cAthletism.GetMatchProgram()", _Ex);
		}
		finally
		{
			CloseConnection(__ResultSet, __PrepareCall, __Connection);
		}
		__Result.setSuccess(true);
		return __Result;
	}

}
