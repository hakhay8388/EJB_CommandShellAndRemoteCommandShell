package com.sanalstil.pEJB.pDatabase.pOracle.pIddaa;


import com.sanalstil.pEJB.pDatabase.pOracle.cOracleDB;
import com.sanalstil.pEJB.pDatabase.pOracle.pBaseDBPackage.cBaseDBPackage;
import com.sanalstil.pEJB.pDatabase.pOracle.pIddaa.pBetList.cBetList;
import com.sanalstil.pEJB.pDatabase.pOracle.pIddaa.pCoupons.cCoupons;

public class cIddaa extends cBaseDBPackage
{
	public cCoupons Coupons = null;
	public cBetList BetList = null; 
	public cIddaa(cOracleDB _OrcaleDb)
	{
		super(_OrcaleDb);
		LoadSubPackage();
	}

	@Override
	public void LoadSubPackage() 
	{
		Coupons = new cCoupons(this.OrcaleDB);
		BetList = new cBetList(this.OrcaleDB);
	}
}
