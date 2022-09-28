package com.sanalstil.pEJB.pDatabaseBeans.pIddaa.pBetList.pVsanalstilyball;

import com.sanalstil.pEJB.pDatabase.cDatabase;
import com.sanalstil.pEJB.pDatabase.pResultObjects.cDataJsonResult;

public class cVsanalstilyballMain implements IVsanalstilyball
{
	@Override
	public cDataJsonResult GetMatchProgram() 
	{
		return cDatabase.Oracle.Iddaa.BetList.Vsanalstilyball.GetMatchProgram();
	}
	
	@Override
	public cDataJsonResult GetLiveMatchProgram() 
	{
		return cDatabase.Oracle.Iddaa.BetList.Vsanalstilyball.GetLiveMatchProgram();
	}
}
