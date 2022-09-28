package com.sanalstil.pEJB.pDatabase.pOracle.pIddaa.pCoupons;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;

import com.sanalstil.pEJB.pDatabase.pOracle.cOracleDB;
import com.sanalstil.pEJB.pDatabase.pOracle.pBaseDBPackage.cBaseDBPackage;
import com.sanalstil.pEJB.pDatabase.pResultObjects.cDataJsonResult;
import com.sanalstil.pEJB.pDatabase.pUtils.cResultSetConverter;
import com.sanalstil.pEJB.pStartup.cStartup;

public class cCoupons extends cBaseDBPackage
{
	public cCoupons(cOracleDB _OrcaleDb)
	{
		super(_OrcaleDb);
		LoadSubPackage();
	}
	
	public cDataJsonResult GetTop(int _Amount, String _Type)
	{
		cDataJsonResult __Result = new cDataJsonResult();
		Connection __Connection = null;
        CallableStatement __PrepareCall = null;
        ResultSet __ResultSet = null;
        try {
        	__Connection = OrcaleDB.DataSource.getConnection();
           	__PrepareCall = __Connection.prepareCall("begin EBAHIS.EMI_P_IDDAA_COUPONS.getTop(?,?,?); end;");
           	__PrepareCall.registerOutParameter(1, OracleTypes.CURSOR);
           	__PrepareCall.setString(2, _Type);
           	__PrepareCall.setInt(3, _Amount);
           	__PrepareCall.execute();
            __ResultSet = ((OracleCallableStatement) __PrepareCall).getCursor(1);
            __Result.dataArray = cResultSetConverter.ResultSet_To_JSONArray(__ResultSet);
            cStartup.ServerLogger.FileInfo("Called cCoupons->GetTop("+ _Amount+", " + _Type +") -> ResultSet = " + __Result.dataArray.size() +" -> SUCCSESS");
        }
        catch (SQLException _Ex) 
        {
        	cStartup.ServerLogger.ConsolError("Error..! -> com.sanalstil.pEJB.pDatabase.pOracle.pIddaa.pCoupons.GetTop()");
        	cStartup.ServerLogger.FileError("Error..! -> com.sanalstil.pEJB.pDatabase.pOracle.pIddaa.pCoupons.GetTop()", _Ex);
        }
        finally
        {
        	CloseConnection(__ResultSet, __PrepareCall, __Connection);
        }
		return __Result;
	}

	@Override
	public void LoadSubPackage() 
	{
	}
}
