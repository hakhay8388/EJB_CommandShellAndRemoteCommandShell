package com.sanalstil.pEjb.pClient.pRemoteClasses.pRemoteDatabase.pIddaa.pBetList;




import com.sanalstil.pEJB.pDatabaseBeans.pIddaa.pBetList.IBetList;
import com.sanalstil.pEJB.pDatabaseBeans.pIddaa.pBetList.cBetListUpdateResult;
import com.sanalstil.pEjb.pClient.pRemoteClasses.pBaseRemoteClass.cBaseRemoteClass;
import com.sanalstil.pEjb.pClient.pRemoteClasses.pRemoteDatabase.pIddaa.pBetList.pAthletism.cAthletism;
import com.sanalstil.pEjb.pClient.pRemoteClasses.pRemoteDatabase.pIddaa.pBetList.pBasketball.cBasketball;
import com.sanalstil.pEjb.pClient.pRemoteClasses.pRemoteDatabase.pIddaa.pBetList.pBillard.cBillard;
import com.sanalstil.pEjb.pClient.pRemoteClasses.pRemoteDatabase.pIddaa.pBetList.pFootball.cFootball;
import com.sanalstil.pEjb.pClient.pRemoteClasses.pRemoteDatabase.pIddaa.pBetList.pHandball.cHandball;
import com.sanalstil.pEjb.pClient.pRemoteClasses.pRemoteDatabase.pIddaa.pBetList.pMotorsports.cMotorsports;
import com.sanalstil.pEjb.pClient.pRemoteClasses.pRemoteDatabase.pIddaa.pBetList.pTennis.cTennis;
import com.sanalstil.pEjb.pClient.pRemoteClasses.pRemoteDatabase.pIddaa.pBetList.pVsanalstilyball.cVsanalstilyball;


public class cBetList extends cBaseRemoteClass<IBetList> implements IBetList
{
	public cAthletism Athletism = null;	
	public cBasketball Basketball = null;
	public cBillard Billard = null;
	public cFootball Football = null;
	public cHandball Handball = null;
	public cMotorsports Motorsports = null;
	public cTennis Tennis = null;
	public cVsanalstilyball Vsanalstilyball = null;

	
	
	IBetList m_BetList = null; 
	
	public cBetList ()
	{
		try
		{
			m_BetList = (IBetList) GetContext().lookup("com/sanalstil/pEJB/pDatabaseBeans/pIddaa/pBetList/cBetList");
		}
		catch(Exception _Ex)
		{
			EJBClientLogger.ConsolError("Remote cBetList Class Creation Error..!");
			EJBClientLogger.FileError("Remote cBetList Class Creation Error..!",_Ex);
		}
		
		Athletism = new cAthletism();
		Basketball = new cBasketball();
		Billard = new cBillard();
		Football = new cFootball();
		Handball = new cHandball();
		Motorsports = new cMotorsports();
		Tennis = new cTennis();
		Vsanalstilyball = new cVsanalstilyball();

	}


	@Override
	public IBetList GetRemoteInstance() 
	{
		return m_BetList;
	}

	public cBetListUpdateResult IsUpdated() 
	{
		if (m_BetList != null)
		{
			try
			{
				return m_BetList.IsUpdated();
			}
			catch (Exception _Ex)
			{
				EJBClientLogger.ConsolError("Remote cBetList->IsUpdated Method Call Error..!");
				EJBClientLogger.FileError("Remote cBetList->IsUpdated Method Call Error..!", _Ex);
				return new cBetListUpdateResult(false, -1, -1);
			}
		}
		else
		{
			EJBClientLogger.ConsolError("Remote cBetList->m_BetList Is Null..!");
			EJBClientLogger.FileError("Remote cBetList->m_BetList Is Null..!");
			return new cBetListUpdateResult(false, -1, -1);
		}
	}
}
