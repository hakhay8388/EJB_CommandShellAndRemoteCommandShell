package com.sanalstil.pEjb.pClient.pRemoteClasses.pRemoteDatabase.pIddaa.pBetList.pVsanalstilyball;

import com.sanalstil.pEJB.pDatabase.pResultObjects.cDataJsonResult;
import com.sanalstil.pEJB.pDatabaseBeans.pIddaa.pBetList.pFootball.IBasketball;
import com.sanalstil.pEJB.pDatabaseBeans.pIddaa.pBetList.pVsanalstilyball.IVsanalstilyball;
import com.sanalstil.pEjb.pClient.pRemoteClasses.pBaseRemoteClass.cBaseRemoteClass;

public class cVsanalstilyball extends cBaseRemoteClass<IVsanalstilyball> implements IVsanalstilyball
{
	IVsanalstilyball m_Vsanalstilyball = null; 
		
		public cVsanalstilyball ()
		{
			try
			{
				m_Vsanalstilyball = (IVsanalstilyball) GetContext().lookup("com/sanalstil/pEJB/pDatabaseBeans/pIddaa/pBetList/pVsanalstilyball/cVsanalstilyball");
			}
			catch(Exception _Ex)
			{
				EJBClientLogger.ConsolError("Remote cVsanalstilyball Class Creation Error..!");
				EJBClientLogger.FileError("Remote cVsanalstilyball Class Creation Error..!",_Ex);
			}
		}


		@Override
		public IVsanalstilyball GetRemoteInstance() 
		{
			return m_Vsanalstilyball;
		}

		public boolean NullControl()
		{
			if (m_Vsanalstilyball != null)
			{
				return true;
			}
			else
			{
				EJBClientLogger.ConsolError("Remote cVsanalstilyball->m_Vsanalstilyball Is Null..!");
				EJBClientLogger.FileError("Remote cVsanalstilyball->m_Vsanalstilyball Is Null..!");
				return false;
			}				
		}

		public cDataJsonResult GetMatchProgram() 
		{
			if (NullControl())
			{
				try
				{
					return m_Vsanalstilyball.GetMatchProgram();
				}
				catch (Exception _Ex)
				{
					EJBClientLogger.ConsolError("Remote cVsanalstilyball->GetMatchProgram Method Call Error..!");
					EJBClientLogger.FileError("Remote cVsanalstilyball->GetMatchProgram Method Call Error..!", _Ex);
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
					return m_Vsanalstilyball.GetLiveMatchProgram();
				}
				catch (Exception _Ex)
				{
					EJBClientLogger.ConsolError("Remote cVsanalstilyball->GetMatchProgram Method Call Error..!");
					EJBClientLogger.FileError("Remote cVsanalstilyball->GetMatchProgram Method Call Error..!", _Ex);
					return new cDataJsonResult();
				}
			}
			return new cDataJsonResult();	
		}	
}
