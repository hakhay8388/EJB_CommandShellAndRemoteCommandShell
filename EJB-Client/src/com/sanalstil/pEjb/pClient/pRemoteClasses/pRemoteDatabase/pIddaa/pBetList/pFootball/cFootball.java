package com.sanalstil.pEjb.pClient.pRemoteClasses.pRemoteDatabase.pIddaa.pBetList.pFootball;

import com.sanalstil.pEJB.pDatabase.pResultObjects.cDataJsonResult;
import com.sanalstil.pEJB.pDatabaseBeans.pIddaa.pBetList.pFootball.IBasketball;
import com.sanalstil.pEjb.pClient.pRemoteClasses.pBaseRemoteClass.cBaseRemoteClass;

public class cFootball extends cBaseRemoteClass<IBasketball> implements IBasketball
{
	IBasketball m_Football = null; 
		
		public cFootball ()
		{
			try
			{
				m_Football = (IBasketball) GetContext().lookup("com/sanalstil/pEJB/pDatabaseBeans/pIddaa/pBetList/pFootball/cFootball");
			}
			catch(Exception _Ex)
			{
				EJBClientLogger.ConsolError("Remote cFootball Class Creation Error..!");
				EJBClientLogger.FileError("Remote cFootball Class Creation Error..!",_Ex);
			}
		}


		@Override
		public IBasketball GetRemoteInstance() 
		{
			return m_Football;
		}

		public boolean NullControl()
		{
			if (m_Football != null)
			{
				return true;
			}
			else
			{
				EJBClientLogger.ConsolError("Remote cFootball->m_Football Is Null..!");
				EJBClientLogger.FileError("Remote cFootball->m_Football Is Null..!");
				return false;
			}				
		}

		public cDataJsonResult GetMatchProgram() 
		{
			if (NullControl())
			{
				try
				{
					return m_Football.GetMatchProgram();
				}
				catch (Exception _Ex)
				{
					EJBClientLogger.ConsolError("Remote cFootball->GetMatchProgram Method Call Error..!");
					EJBClientLogger.FileError("Remote cFootball->GetMatchProgram Method Call Error..!", _Ex);
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
					return m_Football.GetLiveMatchProgram();
				}
				catch (Exception _Ex)
				{
					EJBClientLogger.ConsolError("Remote cFootball->GetMatchProgram Method Call Error..!");
					EJBClientLogger.FileError("Remote cFootball->GetMatchProgram Method Call Error..!", _Ex);
					return new cDataJsonResult();
				}
			}
			return new cDataJsonResult();	
		}	
}
