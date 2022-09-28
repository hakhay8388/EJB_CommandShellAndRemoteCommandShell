package com.sanalstil.pEJB.pDatabaseBeans.pIddaa.pBetList.pFootball;

import com.sanalstil.pEJB.pDatabase.cDatabase;
import com.sanalstil.pEJB.pDatabase.pResultObjects.cDataJsonResult;

public class cFootballMain implements IBasketball
{
	@Override
	public cDataJsonResult GetMatchProgram() 
	{
		return cDatabase.Oracle.Iddaa.BetList.Football.GetMatchProgram();
	}
	
	@Override
	public cDataJsonResult GetLiveMatchProgram() 
	{
		return cDatabase.Oracle.Iddaa.BetList.Football.GetLiveMatchProgram();
	}
}
