package com.sanalstil.pEJB.pDatabaseBeans.pIddaa.pCoupons;

import com.sanalstil.pEJB.pDatabase.cDatabase;
import com.sanalstil.pEJB.pDatabase.pResultObjects.cDataJsonResult;
import com.sanalstil.pEJB.pStartup.cStartup;

public class cCouponsMain implements ICoupons
{
	@Override
	public cDataJsonResult GetTop(int _Amount, String _Type) 
	{
		try
		{
			return cDatabase.Oracle.Iddaa.Coupons.GetTop(_Amount, _Type);
		}
		catch(Exception _Ex)
		{
			cStartup.ServerLogger.FileError("cDatabase.Oracle.Iddaa.Coupons.GetTop(_Amount, _Type)", _Ex);
			return new cDataJsonResult();
		}
	}
}
