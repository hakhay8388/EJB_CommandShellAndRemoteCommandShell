package com.sanalstil.pEJB.pDatabase.pOracle.pIddaa.pBetList.pBasketball;



import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
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



public class cBasketball  extends cBaseDBPackage 
{
	public cBasketball(cOracleDB _OrcaleDb)
	{
		super(_OrcaleDb);
		LoadSubPackage();
	}

	@Override
	public void LoadSubPackage()
	{
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
        boolean __Fail = false;
        try {
        	cStartup.ServerLogger.FileInfo("Called cBasketball->GetMatchProgram()");
        	
        	__Connection = OrcaleDB.DataSource.getConnection();
           	__PrepareCall = __Connection.prepareCall("begin EBAHIS.EMI_P_IDDAA_BETLIST_v2.getBasketball(?); end;");
           	__PrepareCall.registerOutParameter(1, OracleTypes.CURSOR);
           	__PrepareCall.execute();


			__ResultSet = ((OracleCallableStatement) __PrepareCall).getCursor(1);
			BasicDBList tData = cResultSetConverter.ResultSet_To_BasicDBList(__ResultSet);

			//System.out.print("basketball getMatchProgram dataHashList :"+dataHashList);
			
			JSONArray tDateList = new JSONArray();
			
			
			JSONArray tLnsArray = new JSONArray();

			String[] tempFixOne = {"F.1","F.2","UNDER","OVER","S.1","S.2"};
			for (int i=0,is=tData.size();i<is;i++) {
				BasicDBObject row =  (BasicDBObject) tData.get(i);
				String[] tempTeams = row.get("TEAM").toString().split(" - ");
				if(row.get("TN1") == null)row.put("TN1", tempTeams[0]);
				if(row.get("TN2") == null)row.put("TN2", tempTeams[1]);
				row.remove("TEAM"); 
				
				if(Double.valueOf(row.get("H1F").toString()).compareTo(0.0)!=0){
					row.put("HCF", "-"+row.get("H1F").toString());
				}
				else if(Double.valueOf(row.get("H2F").toString()).compareTo(0.0)!=0){
					row.put("HCF", "+"+row.get("H2F").toString());
				}
				else {
					row.put("HCF",0);
				}
				row.remove("H1F");
				row.remove("H2F");
				
				if(Double.valueOf(row.get("H1S").toString()).compareTo(0.0)!=0){
					row.put("HCH", "-"+row.get("H1S").toString());
				}
				else if(Double.valueOf(row.get("H2S").toString()).compareTo(0.0)!=0){
					row.put("HCH", "+"+row.get("H2S").toString());
				}
				else {
					row.put("HCH",0);
				}
				row.remove("H1S");
				row.remove("H2S");
				
				
				for (String col : tempFixOne) {
					//row.remove(col);
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
		            __PrepareCall = __Connection.prepareCall("begin EBAHIS.EMI_P_IDDAA_BETLIST_v2.getCbsLeagueNamesBasketball(?, ?); end;");
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

				}
				catch (SQLException _Ex)
				{
					cStartup.ServerLogger.ConsolError("Error..! -> com.sanalstil.pEJB.pDatabase.pOracle.pIddaa.pBetList.pBasketball.cBasketball.GetMatchProgram()");
        			cStartup.ServerLogger.FileError("Error..! -> com.sanalstil.pEJB.pDatabase.pOracle.pIddaa.pBetList.pBasketball.cBasketball.GetMatchProgram()", _Ex);
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
        }
		catch (SQLException _Ex) 
		{
	       	cStartup.ServerLogger.ConsolError("Error..! -> com.sanalstil.pEJB.pDatabase.pOracle.pIddaa.pBetList.pBasketball.cBasketball.GetMatchProgram()");
	       	cStartup.ServerLogger.FileError("Error..! -> com.sanalstil.pEJB.pDatabase.pOracle.pIddaa.pBetList.pBasketball.cBasketball.GetMatchProgram()", _Ex);
		}
		finally
		{
			CloseConnection(__ResultSet, __PrepareCall, __Connection);
		}
		__Result.setSuccess(true);
		return __Result;
	}
	
}