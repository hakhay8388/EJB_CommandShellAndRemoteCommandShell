package com.sanalstil.pEjb.pClient.pRemoteClasses.pRemoteDatabase.pIddaa.pBetList.pTennis;

import com.sanalstil.pEJB.pDatabase.pResultObjects.cDataJsonResult;
import com.sanalstil.pEJB.pDatabaseBeans.pIddaa.pBetList.pBasketball.IBasketball;
import com.sanalstil.pEJB.pDatabaseBeans.pIddaa.pBetList.pTennis.ITennis;
import com.sanalstil.pEjb.pClient.pRemoteClasses.pBaseRemoteClass.cBaseRemoteClass;

public class cTennis extends cBaseRemoteClass<ITennis> implements ITennis
{
	ITennis m_Tennis = null; 
		
		public cTennis ()
		{
			try
			{
				m_Tennis = (ITennis) GetContext().lookup("com/sanalstil/pEJB/pDatabaseBeans/pIddaa/pBetList/pTennis/cTennis");
			}
			catch(Exception _Ex)
			{
				EJBClientLogger.ConsolError("Remote cTennis Class Creation Error..!");
				EJBClientLogger.FileError("Remote cTennis Class Creation Error..!",_Ex);
			}
		}


		@Override
		public ITennis GetRemoteInstance() 
		{
			return m_Tennis;
		}

		public boolean NullControl()
		{
			if (m_Tennis != null)
			{
				return true;
			}
			else
			{
				EJBClientLogger.ConsolError("Remote cTennis->m_Tennis Is Null..!");
				EJBClientLogger.FileError("Remote cTennis->m_Tennis Is Null..!");
				return false;
			}				
		}

		public cDataJsonResult GetMatchProgram() 
		{
			if (NullControl())
			{
				try
				{
					return m_Tennis.GetMatchProgram();
				}
				catch (Exception _Ex)
				{
					EJBClientLogger.ConsolError("Remote cTennis->GetMatchProgram Method Call Error..!");
					EJBClientLogger.FileError("Remote cTennis->GetMatchProgram Method Call Error..!", _Ex);
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
					return m_Tennis.GetLiveMatchProgram();
				}
				catch (Exception _Ex)
				{
					EJBClientLogger.ConsolError("Remote cTennis->GetMatchProgram Method Call Error..!");
					EJBClientLogger.FileError("Remote cTennis->GetMatchProgram Method Call Error..!", _Ex);
					return new cDataJsonResult();
				}
			}
			return new cDataJsonResult();	
		}	
}
