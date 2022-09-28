package com.sanalstil.pEJB.pDatabaseBeans.pIddaa.pBetList;

import com.sanalstil.pEJB.pDatabase.cDatabase;

public class cBetListMain implements IBetList
{
	public cBetListUpdateResult IsUpdated()
	{
		return cDatabase.Oracle.Iddaa.BetList.IsUpdated();
	}

}
