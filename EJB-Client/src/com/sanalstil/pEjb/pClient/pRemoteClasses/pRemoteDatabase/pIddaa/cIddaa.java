package com.sanalstil.pEjb.pClient.pRemoteClasses.pRemoteDatabase.pIddaa;

import com.sanalstil.pEJB.pDatabaseBeans.pIddaa.IIddaa;
import com.sanalstil.pEjb.pClient.pRemoteClasses.pBaseRemoteClass.cBaseRemoteClass;
import com.sanalstil.pEjb.pClient.pRemoteClasses.pRemoteDatabase.pIddaa.pBetList.cBetList;
import com.sanalstil.pEjb.pClient.pRemoteClasses.pRemoteDatabase.pIddaa.pCoupons.cCoupons;


public class cIddaa extends cBaseRemoteClass<IIddaa> implements IIddaa
{
	public cCoupons Coupons = null;
	public cBetList BetList = null;
	IIddaa m_Iddaa = null; 
	
	public cIddaa ()
	{
		try
		{
			m_Iddaa = (IIddaa) GetContext().lookup("com/sanalstil/pEJB/pDatabaseBeans/pIddaa/cIddaa");
		}
		catch(Exception _Ex)
		{
			EJBClientLogger.ConsolError("Remote cIddaa Class Creation Error..!");
			EJBClientLogger.FileError("Remote cIddaa Class Creation Error..!", _Ex);			
		}
		Coupons = new cCoupons();
		BetList = new cBetList();
	}

	@Override
	public IIddaa GetRemoteInstance() 
	{
		return m_Iddaa;
	}

}
