package com.sanalstil.pEjb.pClient.pRemoteClasses.pRemoteDatabase.pIddaa.pCoupons;


import com.sanalstil.pEJB.pDatabase.pResultObjects.cDataJsonResult;
import com.sanalstil.pEJB.pDatabaseBeans.pIddaa.pCoupons.ICoupons;
import com.sanalstil.pEjb.pClient.pRemoteClasses.pBaseRemoteClass.cBaseRemoteClass;


public class cCoupons extends cBaseRemoteClass<ICoupons> implements ICoupons
{
	ICoupons m_Coupons = null; 
	
	public cCoupons ()
	{
		try
		{
			m_Coupons = (ICoupons) GetContext().lookup("com/sanalstil/pEJB/pDatabaseBeans/pIddaa/pCoupons/cCoupons");
		}
		catch(Exception _Ex)
		{
			EJBClientLogger.ConsolError("Remote cCoupons Class Creation Error..!");
			EJBClientLogger.FileError("Remote cCoupons Class Creation Error..!",_Ex);
		}
	}

	public cDataJsonResult GetTop(int _Amount, String _Type) 
	{
		if (m_Coupons != null)
		{
			try
			{
				return m_Coupons.GetTop(_Amount, _Type);
			}
			catch (Exception _Ex)
			{
				EJBClientLogger.ConsolError("Remote cCoupons->GetTop Method Call Error..!");
				EJBClientLogger.FileError("Remote cCoupons->GetTop Method Call Error..!", _Ex);
				return new cDataJsonResult();
			}
		}
		else
		{
			return new cDataJsonResult();
		}
	}

	@Override
	public ICoupons GetRemoteInstance() 
	{
		return m_Coupons;
	}

}
