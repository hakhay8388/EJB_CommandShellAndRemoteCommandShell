package com.sanalstil.pEJB.pDatabaseBeans.pIddaa.pBetList.pAthletism;

import com.sanalstil.pEJB.pDatabase.cDatabase;
import com.sanalstil.pEJB.pDatabase.pResultObjects.cDataJsonResult;

public class cAthletismMain implements IAthletism
{
	@Override
	public cDataJsonResult GetMatchProgram() 
	{
		return cDatabase.Oracle.Iddaa.BetList.Athletism.GetMatchProgram();
	}
	
	@Override
	public cDataJsonResult GetLiveMatchProgram() 
	{
		return cDatabase.Oracle.Iddaa.BetList.Athletism.GetLiveMatchProgram();
	}
}
