package com.sanalstil.pEJB.pDatabaseBeans.pIddaa.pBetList;

import java.io.Serializable;

public class cBetListUpdateResult implements Serializable 
{
	public boolean Updated;
	public long LastUpdateTime;
	public long LastTotal;
	
	public cBetListUpdateResult(boolean _Updated, long _LastUpdateTime, long _LastTotal)
	{
		Updated = _Updated;
		LastUpdateTime = _LastUpdateTime;
		LastTotal = _LastTotal;
	}
}
