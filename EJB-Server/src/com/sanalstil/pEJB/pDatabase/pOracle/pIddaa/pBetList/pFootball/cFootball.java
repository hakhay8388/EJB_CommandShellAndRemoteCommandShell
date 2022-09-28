package com.sanalstil.pEJB.pDatabase.pOracle.pIddaa.pBetList.pFootball;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

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

public class cFootball  extends cBaseDBPackage 
{
	public cFootball(cOracleDB _OrcaleDb)
	{
		super(_OrcaleDb);
		LoadSubPackage();
	}

	@Override
	public void LoadSubPackage()
	{
		// TODO Auto-generated method stub
	}
	
	public cDataJsonResult GetMatchProgram()
	{
		
		cDataJsonResult __Result = new cDataJsonResult();
		Connection __Connection = null;
        CallableStatement __PrepareCall = null;
        ResultSet __ResultSet = null;
        boolean __Fail = false;
        try {
        	
        	cStartup.ServerLogger.FileInfo("Called cFootball->GetMatchProgram()");
        	
        	__Connection = OrcaleDB.DataSource.getConnection();
           	__PrepareCall = __Connection.prepareCall("begin EBAHIS.EMI_P_IDDAA_BETLIST_v2.getFootball(?); end;");
           	__PrepareCall.registerOutParameter(1, OracleTypes.CURSOR);
           	__PrepareCall.execute();
           	
			__ResultSet = ((OracleCallableStatement) __PrepareCall).getCursor(1);
			BasicDBList tData = cResultSetConverter.ResultSet_To_BasicDBList(__ResultSet);
			//System.out.print("football getMatchProgram dataHashList :"+dataHashList);
			String[] tempFixOne = {"F.1","F.X","F.2","UNDER","OVER","S.1","S.X","S.2","DC.1X","DC.12","DC.X2"};
			JSONArray tDateList = new JSONArray();
			
			
			ArrayList<String> tLnsArray = new ArrayList<String>();
			
			for (int i=0,is=tData.size();i<is;i++)
			{
				BasicDBObject row =  (BasicDBObject) tData.get(i);
				String[] tempTeams = row.get("TEAM").toString().split(" - ");
				if(row.get("TN1") == null)row.put("TN1", tempTeams[0]);
				if(row.get("TN2") == null)row.put("TN2", tempTeams[1]);
				row.remove("TEAM");
				
				if(!row.get("H1F").toString().equals(new String("0.0"))){
					row.put("HCF", "-"+row.get("H1F").toString());
				}
				else if(!row.get("H2F").toString().equals(new String("0.0"))){
					row.put("HCF", "+"+row.get("H2F").toString());
				}
				else {
					row.put("HCF",0);
				}
				row.remove("H1F");
				row.remove("H2F");
				for (String col : tempFixOne) {
					if(Double.valueOf(row.get(col).toString()).compareTo(1.0)==0)row.put(col, "-");
				}
				
				if(!tDateList.contains(row.get("DATE").toString().substring(0, 10)))tDateList.add(row.get("DATE").toString().substring(0, 10));
				
				if(!tLnsArray.contains(row.get("LNS").toString())){
					tLnsArray.add(row.get("LNS").toString());
				}
			}
			JSONArray tLnsList = new JSONArray();
			if(tLnsArray.size()>0){
				StringBuffer tStringBuffer = new StringBuffer();

				for(int i=0;i<tLnsArray.size();i++){
					tStringBuffer.append(",");
					tStringBuffer.append(tLnsArray.get(i));
				}
				tStringBuffer.append(",");

		        try 
		        {
		            __PrepareCall = __Connection.prepareCall("begin EBAHIS.EMI_P_IDDAA_BETLIST_v2.getCbsLeagueNamesFootball(?, ?); end;");
			        __PrepareCall.registerOutParameter(1, OracleTypes.CURSOR);
			        __PrepareCall.setString(2, tStringBuffer.toString());
			        __PrepareCall.execute();
        
		        	ResultSet __ResultSet2 = ((OracleCallableStatement) __PrepareCall).getCursor(1);
			        while(__ResultSet2.next())
			        {
			        	JSONArray tArray = new JSONArray();
			        	tArray.add(__ResultSet2.getString(1));
			        	tArray.add(__ResultSet2.getString(2));
			        	tLnsList.add(tArray);
			        }
			        
			        __PrepareCall.close();
			        __ResultSet2.close();
			  
			        //System.out.println("getNewsTricker result.dataArray : "+result.dataArray);
				}
				catch (SQLException _Ex)
				{
					__Fail = true;
					cStartup.ServerLogger.ConsolError("Error..! -> com.sanalstil.pEJB.pDatabase.pOracle.pIddaa.pBetList.pFootball.cFootball.GetMatchProgram()");
        			cStartup.ServerLogger.FileError("Error..! -> com.sanalstil.pEJB.pDatabase.pOracle.pIddaa.pBetList.pFootball.cFootball.GetMatchProgram()", _Ex);
				}
			}
			
			__Result.dataObject.put("DATELIST", tDateList);
			__Result.dataObject.put("LNSLIST", tLnsList);
			__Result.dataObject.put("rows", tData);
			if (!__Fail)
			{
				cStartup.ServerLogger.FileInfo("Called cFootball->GetMatchProgram() -> SUCCESS");
			}
			else
			{
				cStartup.ServerLogger.FileInfo("Called cFootball->GetMatchProgram() -> Sub try catch error..!");
			}
			
		}
		catch (SQLException _Ex) 
		{
        	cStartup.ServerLogger.ConsolError("Error..! -> com.sanalstil.pEJB.pDatabase.pOracle.pIddaa.pBetList.pFootball.cFootball.GetMatchProgram()");
        	cStartup.ServerLogger.FileError("Error..! -> com.sanalstil.pEJB.pDatabase.pOracle.pIddaa.pBetList.pFootball.cFootball.GetMatchProgram()", _Ex);
		}
		finally
		{
			CloseConnection(__ResultSet, __PrepareCall, __Connection);
		}
		__Result.setSuccess(true);
		return __Result;
	}
	
	@SuppressWarnings("unchecked")
	public cDataJsonResult GetLiveMatchProgram()
	{
		cDataJsonResult __Result = new cDataJsonResult();

		Connection __Connection = null;
        CallableStatement __PrepareCall = null;
        ResultSet __ResultSet = null;
        try
        {
        	cStartup.ServerLogger.FileInfo("Called cFootball->GetLiveMatchProgram()");
        	__Connection = OrcaleDB.DataSource.getConnection();
        	__PrepareCall = __Connection.prepareCall("begin EBAHIS.EMI_P_IDDAA_BETLIST_v2.getFootballLive(?); end;");
        	__PrepareCall.registerOutParameter(1, OracleTypes.CURSOR);
        	__PrepareCall.execute();

        	__ResultSet = ((OracleCallableStatement) __PrepareCall).getCursor(1);
			@SuppressWarnings("rawtypes")
			ArrayList<HashMap> __DataHashList = cResultSetConverter.ResultSet_To_JSONArray(__ResultSet);
			
			__Result.dataObject.put("rows", __DataHashList);
			cStartup.ServerLogger.FileInfo("Called cFootball->GetLiveMatchProgram() -> SUCCESS");
		}
		catch (SQLException _Ex) 
		{
        	cStartup.ServerLogger.ConsolError("Error..! -> com.sanalstil.pEJB.pDatabase.pOracle.pIddaa.pBetList.pFootball.cFootball.GetLiveMatchProgram()");
        	cStartup.ServerLogger.FileError("Error..! -> com.sanalstil.pEJB.pDatabase.pOracle.pIddaa.pBetList.pFootball.cFootball.GetLiveMatchProgram()", _Ex);
		}
		finally
		{
				CloseConnection(__ResultSet, __PrepareCall, __Connection);
		}		
		return __Result;
	}
}
