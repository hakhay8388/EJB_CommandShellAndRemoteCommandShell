package com.sanalstil.pEJB.pDatabase.pOracle.pIddaa.pBetList;

import java.sql.CallableStatement;
import java.sql.Connection;

import oracle.jdbc.OracleTypes;

import com.sanalstil.pEJB.pDatabase.pOracle.cOracleDB;
import com.sanalstil.pEJB.pDatabase.pOracle.pBaseDBPackage.cBaseDBPackage;
import com.sanalstil.pEJB.pDatabase.pOracle.pIddaa.pBetList.pAthletism.cAthletism;
import com.sanalstil.pEJB.pDatabase.pOracle.pIddaa.pBetList.pBasketball.cBasketball;
import com.sanalstil.pEJB.pDatabase.pOracle.pIddaa.pBetList.pBillard.cBillard;
import com.sanalstil.pEJB.pDatabase.pOracle.pIddaa.pBetList.pFootball.cFootball;
import com.sanalstil.pEJB.pDatabase.pOracle.pIddaa.pBetList.pHandball.cHandball;
import com.sanalstil.pEJB.pDatabase.pOracle.pIddaa.pBetList.pMotorsports.cMotorsports;
import com.sanalstil.pEJB.pDatabase.pOracle.pIddaa.pBetList.pTennis.cTennis;
import com.sanalstil.pEJB.pDatabase.pOracle.pIddaa.pBetList.pVsanalstilyball.cVsanalstilyball;
import com.sanalstil.pEJB.pDatabaseBeans.pIddaa.pBetList.cBetListUpdateResult;
import com.sanalstil.pEJB.pStartup.cStartup;

public class cBetList extends cBaseDBPackage 
{
	public cFootball Football = null;
	public cBasketball Basketball = null;
	public cAthletism Athletism = null;
	public cBillard Billard = null;
	public cHandball Handball = null;
	public cMotorsports Motorsports = null;
	public cTennis Tennis = null;
	public cVsanalstilyball Vsanalstilyball = null;

	
	public long LastUpdateTime=-1;
	public int LastTotal=-1;
	public cBetList(cOracleDB _OrcaleDb)
	{
		super(_OrcaleDb);
		LoadSubPackage();
	}
	
	@Override
	public void LoadSubPackage() 
	{
		Football = new cFootball(OrcaleDB);
		Basketball = new cBasketball(OrcaleDB);
		Athletism = new cAthletism(OrcaleDB);
		Billard = new cBillard(OrcaleDB);
		Handball = new cHandball(OrcaleDB);
		Motorsports = new cMotorsports(OrcaleDB);
		Tennis = new cTennis(OrcaleDB);
		Vsanalstilyball = new cVsanalstilyball(OrcaleDB);
	}

	public cBetListUpdateResult IsUpdated()
	{
		Connection __Connection = null;
        CallableStatement __PrepareCall = null;
        cBetListUpdateResult __Result = new cBetListUpdateResult(false, LastUpdateTime, LastTotal) ;
        try
        {
        	cStartup.ServerLogger.FileInfo("Called cBetList->IsUpdated()");
        	__Connection = OrcaleDB.DataSource.getConnection();
        	__PrepareCall = __Connection.prepareCall("begin EBAHIS.EMI_P_WEB_CACHE.iddaa_betlist(?,?); end;");
        	__PrepareCall.registerOutParameter(1, OracleTypes.INTEGER);
        	__PrepareCall.registerOutParameter(2, OracleTypes.INTEGER);
        	__PrepareCall.execute();

        	Long __UpdateTime = __PrepareCall.getLong(1);
    		int __Total = __PrepareCall.getInt(2);
    		
    		if(__UpdateTime.compareTo(LastUpdateTime)!=0)
    		{
    			__Result.Updated = true;
    		}
    		else if(__Total!= LastTotal)
    		{
    			__Result.Updated = true;
    		}
    		LastUpdateTime = __UpdateTime;
    		LastTotal = __Total;
	    	__Result.LastUpdateTime = LastUpdateTime;
	    	__Result.LastTotal = LastTotal;
    		cStartup.ServerLogger.FileInfo("Called cBetList->IsUpdated() -> SUCCESS");
		}
		catch (Exception _Ex) 
		{
        	cStartup.ServerLogger.ConsolError("Error..! -> com.sanalstil.pEJB.pDatabase.pOracle.pIddaa.pBetList.cBetList.IsUpdated()");
        	cStartup.ServerLogger.FileError("Error..! -> com.sanalstil.pEJB.pDatabase.pOracle.pIddaa.pBetList.cBetList.IsUpdated()", _Ex);
		}
		finally
		{
			CloseConnection(null, __PrepareCall, __Connection);
		}		
		return __Result;
	}
	
	public long GetLastUpdateTime()
	{
		return LastUpdateTime;
	}

	public long GetLastTotal()
	{
		return LastTotal;
	}
}
