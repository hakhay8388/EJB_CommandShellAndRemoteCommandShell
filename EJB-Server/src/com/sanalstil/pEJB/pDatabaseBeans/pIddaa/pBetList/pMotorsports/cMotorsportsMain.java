package com.sanalstil.pEJB.pDatabaseBeans.pIddaa.pBetList.pMotorsports;

import com.sanalstil.pEJB.pDatabase.cDatabase;
import com.sanalstil.pEJB.pDatabase.pResultObjects.cDataJsonResult;

public class cMotorsportsMain implements IMotorsports
{
	@Override
	public cDataJsonResult GetMatchProgram() 
	{
		return cDatabase.Oracle.Iddaa.BetList.Motorsports.GetMatchProgram();
	}
	
	@Override
	public cDataJsonResult GetLiveMatchProgram() 
	{
		return cDatabase.Oracle.Iddaa.BetList.Motorsports.GetLiveMatchProgram();
	}
}
