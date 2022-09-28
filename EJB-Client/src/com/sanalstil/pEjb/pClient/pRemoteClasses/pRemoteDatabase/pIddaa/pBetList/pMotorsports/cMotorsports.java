package com.sanalstil.pEjb.pClient.pRemoteClasses.pRemoteDatabase.pIddaa.pBetList.pMotorsports;

import com.sanalstil.pEJB.pDatabase.pResultObjects.cDataJsonResult;
import com.sanalstil.pEJB.pDatabaseBeans.pIddaa.pBetList.pBasketball.IBasketball;
import com.sanalstil.pEJB.pDatabaseBeans.pIddaa.pBetList.pMotorsports.IMotorsports;
import com.sanalstil.pEjb.pClient.pRemoteClasses.pBaseRemoteClass.cBaseRemoteClass;

public class cMotorsports extends cBaseRemoteClass<IMotorsports> implements IMotorsports
{
	IMotorsports m_Motorsports = null; 
		
		public cMotorsports ()
		{
			try
			{
				m_Motorsports = (IMotorsports) GetContext().lookup("com/sanalstil/pEJB/pDatabaseBeans/pIddaa/pBetList/pMotorsports/cMotorsports");
			}
			catch(Exception _Ex)
			{
				EJBClientLogger.ConsolError("Remote cMotorsports Class Creation Error..!");
				EJBClientLogger.FileError("Remote cMotorsports Class Creation Error..!",_Ex);
			}
		}


		@Override
		public IMotorsports GetRemoteInstance() 
		{
			return m_Motorsports;
		}

		public boolean NullControl()
		{
			if (m_Motorsports != null)
			{
				return true;
			}
			else
			{
				EJBClientLogger.ConsolError("Remote cMotorsports->m_Motorsports Is Null..!");
				EJBClientLogger.FileError("Remote cMotorsports->m_Motorsports Is Null..!");
				return false;
			}				
		}

		public cDataJsonResult GetMatchProgram() 
		{
			if (NullControl())
			{
				try
				{
					return m_Motorsports.GetMatchProgram();
				}
				catch (Exception _Ex)
				{
					EJBClientLogger.ConsolError("Remote cMotorsports->GetMatchProgram Method Call Error..!");
					EJBClientLogger.FileError("Remote cMotorsports->GetMatchProgram Method Call Error..!", _Ex);
					return new cDataJsonResult();
				}
			}
			return new cDataJsonResult();
		}
		
		public cDataJsonResult GetLiveMatchProgram() 
		{
			if (NullControl())
			{
				try
				{
					return m_Motorsports.GetLiveMatchProgram();
				}
				catch (Exception _Ex)
				{
					EJBClientLogger.ConsolError("Remote cMotorsports->GetMatchProgram Method Call Error..!");
					EJBClientLogger.FileError("Remote cMotorsports->GetMatchProgram Method Call Error..!", _Ex);
					return new cDataJsonResult();
				}
			}
			return new cDataJsonResult();	
		}	
}
