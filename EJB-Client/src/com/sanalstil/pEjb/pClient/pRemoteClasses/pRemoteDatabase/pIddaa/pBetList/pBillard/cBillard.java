package com.sanalstil.pEjb.pClient.pRemoteClasses.pRemoteDatabase.pIddaa.pBetList.pBillard;

import com.sanalstil.pEJB.pDatabase.pResultObjects.cDataJsonResult;
import com.sanalstil.pEJB.pDatabaseBeans.pIddaa.pBetList.pBillard.IBillard;
import com.sanalstil.pEjb.pClient.pRemoteClasses.pBaseRemoteClass.cBaseRemoteClass;

public class cBillard extends cBaseRemoteClass<IBillard> implements IBillard
{
	IBillard m_Billard = null; 
		
		public cBillard ()
		{
			try
			{
				m_Billard = (IBillard) GetContext().lookup("com/sanalstil/pEJB/pDatabaseBeans/pIddaa/pBetList/pBillard/cBillard");
			}
			catch(Exception _Ex)
			{
				EJBClientLogger.ConsolError("Remote cBillard Class Creation Error..!");
				EJBClientLogger.FileError("Remote cBillard Class Creation Error..!",_Ex);
			}
		}


		@Override
		public IBillard GetRemoteInstance() 
		{
			return m_Billard;
		}

		public boolean NullControl()
		{
			if (m_Billard != null)
			{
				return true;
			}
			else
			{
				EJBClientLogger.ConsolError("Remote cBillard->m_Billard Is Null..!");
				EJBClientLogger.FileError("Remote cBillard->m_Billard Is Null..!");
				return false;
			}				
		}

		public cDataJsonResult GetMatchProgram() 
		{
			if (NullControl())
			{
				try
				{
					return m_Billard.GetMatchProgram();
				}
				catch (Exception _Ex)
				{
					EJBClientLogger.ConsolError("Remote cBillard->GetMatchProgram Method Call Error..!");
					EJBClientLogger.FileError("Remote cBillard->GetMatchProgram Method Call Error..!", _Ex);
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
					return m_Billard.GetLiveMatchProgram();
				}
				catch (Exception _Ex)
				{
					EJBClientLogger.ConsolError("Remote cBillard->GetMatchProgram Method Call Error..!");
					EJBClientLogger.FileError("Remote cBillard->GetMatchProgram Method Call Error..!", _Ex);
					return new cDataJsonResult();
				}
			}
			return new cDataJsonResult();	
		}	
}
