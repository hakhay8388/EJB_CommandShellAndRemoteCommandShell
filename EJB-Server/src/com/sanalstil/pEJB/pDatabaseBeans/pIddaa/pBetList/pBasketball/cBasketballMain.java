package com.sanalstil.pEJB.pDatabaseBeans.pIddaa.pBetList.pBasketball;

import com.sanalstil.pEJB.pDatabase.cDatabase;
import com.sanalstil.pEJB.pDatabase.pResultObjects.cDataJsonResult;

public class cBasketballMain implements IBasketball
{
	@Override
	public cDataJsonResult GetMatchProgram() 
	{
		return cDatabase.Oracle.Iddaa.BetList.Basketball.GetMatchProgram();
	}
	
	@Override
	public cDataJsonResult GetLiveMatchProgram() 
	{
		return cDatabase.Oracle.Iddaa.BetList.Basketball.GetLiveMatchProgram();
	}
}
