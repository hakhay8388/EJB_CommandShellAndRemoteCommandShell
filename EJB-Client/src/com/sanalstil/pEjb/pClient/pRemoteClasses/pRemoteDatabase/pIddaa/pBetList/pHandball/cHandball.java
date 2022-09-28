package com.sanalstil.pEjb.pClient.pRemoteClasses.pRemoteDatabase.pIddaa.pBetList.pHandball;

import com.sanalstil.pEJB.pDatabase.pResultObjects.cDataJsonResult;
import com.sanalstil.pEJB.pDatabaseBeans.pIddaa.pBetList.pBasketball.IBasketball;
import com.sanalstil.pEJB.pDatabaseBeans.pIddaa.pBetList.pHandball.IHandball;
import com.sanalstil.pEjb.pClient.pRemoteClasses.pBaseRemoteClass.cBaseRemoteClass;

public class cHandball extends cBaseRemoteClass<IHandball> implements IHandball
{
	IHandball m_Handball = null; 
		
		public cHandball ()
		{
			try
			{
				m_Handball = (IHandball) GetContext().lookup("com/sanalstil/pEJB/pDatabaseBeans/pIddaa/pBetList/pHandball/cHandball");
			}
			catch(Exception _Ex)
			{
				EJBClientLogger.ConsolError("Remote cHandball Class Creation Error..!");
				EJBClientLogger.FileError("Remote cHandball Class Creation Error..!",_Ex);
			}
		}


		@Override
		public IHandball GetRemoteInstance() 
		{
			return m_Handball;
		}

		public boolean NullControl()
		{
			if (m_Handball != null)
			{
				return true;
			}
			else
			{
				EJBClientLogger.ConsolError("Remote cHandball->m_Handball Is Null..!");
				EJBClientLogger.FileError("Remote cHandball->m_Handball Is Null..!");
				return false;
			}				
		}

		public cDataJsonResult GetMatchProgram() 
		{
			if (NullControl())
			{
				try
				{
					return m_Handball.GetMatchProgram();
				}
				catch (Exception _Ex)
				{
					EJBClientLogger.ConsolError("Remote cHandball->GetMatchProgram Method Call Error..!");
					EJBClientLogger.FileError("Remote cHandball->GetMatchProgram Method Call Error..!", _Ex);
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
					return m_Handball.GetLiveMatchProgram();
				}
				catch (Exception _Ex)
				{
					EJBClientLogger.ConsolError("Remote cHandball->GetMatchProgram Method Call Error..!");
					EJBClientLogger.FileError("Remote cHandball->GetMatchProgram Method Call Error..!", _Ex);
					return new cDataJsonResult();
				}
			}
			return new cDataJsonResult();	
		}	
}
