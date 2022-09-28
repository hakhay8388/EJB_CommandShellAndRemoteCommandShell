package com.sanalstil.pEjb.pClient.pRemoteClasses.pRemoteDatabase.pIddaa.pBetList.pAthletism;

import com.sanalstil.pEJB.pDatabase.pResultObjects.cDataJsonResult;
import com.sanalstil.pEJB.pDatabaseBeans.pIddaa.pBetList.pAthletism.IAthletism;
import com.sanalstil.pEjb.pClient.pRemoteClasses.pBaseRemoteClass.cBaseRemoteClass;

public class cAthletism extends cBaseRemoteClass<IAthletism> implements IAthletism
{
	IAthletism m_Athletism = null; 
		
		public cAthletism ()
		{
			try
			{
				m_Athletism = (IAthletism) GetContext().lookup("com/sanalstil/pEJB/pDatabaseBeans/pIddaa/pBetList/pAthletism/cAthletism");
			}
			catch(Exception _Ex)
			{
				EJBClientLogger.ConsolError("Remote cAthletism Class Creation Error..!");
				EJBClientLogger.FileError("Remote cAthletism Class Creation Error..!",_Ex);
			}
		}


		@Override
		public IAthletism GetRemoteInstance() 
		{
			return m_Athletism;
		}

		public boolean NullControl()
		{
			if (m_Athletism != null)
			{
				return true;
			}
			else
			{
				EJBClientLogger.ConsolError("Remote cAthletism->m_Athletism Is Null..!");
				EJBClientLogger.FileError("Remote cAthletism->m_Athletism Is Null..!");
				return false;
			}				
		}

		public cDataJsonResult GetMatchProgram() 
		{
			if (NullControl())
			{
				try
				{
					return m_Athletism.GetMatchProgram();
				}
				catch (Exception _Ex)
				{
					EJBClientLogger.ConsolError("Remote cAthletism->GetMatchProgram Method Call Error..!");
					EJBClientLogger.FileError("Remote cAthletism->GetMatchProgram Method Call Error..!", _Ex);
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
					return m_Athletism.GetLiveMatchProgram();
				}
				catch (Exception _Ex)
				{
					EJBClientLogger.ConsolError("Remote cAthletism->GetMatchProgram Method Call Error..!");
					EJBClientLogger.FileError("Remote cAthletism->GetMatchProgram Method Call Error..!", _Ex);
					return new cDataJsonResult();
				}
			}
			return new cDataJsonResult();	
		}	
}
