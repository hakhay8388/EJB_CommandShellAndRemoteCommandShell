package com.sanalstil.pEjb.pClient.pRemoteClasses.pRemoteDatabase.pIddaa.pBetList.pBasketball;

import com.sanalstil.pEJB.pDatabase.pResultObjects.cDataJsonResult;
import com.sanalstil.pEJB.pDatabaseBeans.pIddaa.pBetList.pBasketball.IBasketball;
import com.sanalstil.pEjb.pClient.pRemoteClasses.pBaseRemoteClass.cBaseRemoteClass;

public class cBasketball extends cBaseRemoteClass<IBasketball> implements IBasketball
{
	IBasketball m_Basketball = null; 
		
		public cBasketball ()
		{
			try
			{
				m_Basketball = (IBasketball) GetContext().lookup("com/sanalstil/pEJB/pDatabaseBeans/pIddaa/pBetList/pBasketball/cBasketball");
			}
			catch(Exception _Ex)
			{
				EJBClientLogger.ConsolError("Remote cBasketball Class Creation Error..!");
				EJBClientLogger.FileError("Remote cBasketball Class Creation Error..!",_Ex);
			}
		}


		@Override
		public IBasketball GetRemoteInstance() 
		{
			return m_Basketball;
		}

		public boolean NullControl()
		{
			if (m_Basketball != null)
			{
				return true;
			}
			else
			{
				EJBClientLogger.ConsolError("Remote cBasketball->m_Basketball Is Null..!");
				EJBClientLogger.FileError("Remote cBasketball->m_Basketball Is Null..!");
				return false;
			}				
		}

		public cDataJsonResult GetMatchProgram() 
		{
			if (NullControl())
			{
				try
				{
					return m_Basketball.GetMatchProgram();
				}
				catch (Exception _Ex)
				{
					EJBClientLogger.ConsolError("Remote cBasketball->GetMatchProgram Method Call Error..!");
					EJBClientLogger.FileError("Remote cBasketball->GetMatchProgram Method Call Error..!", _Ex);
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
					return m_Basketball.GetLiveMatchProgram();
				}
				catch (Exception _Ex)
				{
					EJBClientLogger.ConsolError("Remote cBasketball->GetMatchProgram Method Call Error..!");
					EJBClientLogger.FileError("Remote cBasketball->GetMatchProgram Method Call Error..!", _Ex);
					return new cDataJsonResult();
				}
			}
			return new cDataJsonResult();	
		}	
}
