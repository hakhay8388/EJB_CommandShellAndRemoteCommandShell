package com.sanalstil.pEjb.pClient.pRemoteClasses.pRemoteComponents;


import com.sanalstil.pEJB.pComponents.pThread.IThread;
import com.sanalstil.pEjb.pClient.pRemoteClasses.pBaseRemoteClass.cBaseRemoteClass;



public class cThread extends cBaseRemoteClass<IThread> implements IThread
{
	private IThread m_Thread;
	
	public cThread() throws Exception
	{
		try
		{
			m_Thread = (IThread) GetContext().lookup("pUtils/cThread");
		}
		catch (Exception _Ex)
		{
			EJBClientLogger.ConsolError("Remote cThread Creation Error..!");
			EJBClientLogger.FileError("Remote cThread Creation Error..!", _Ex);
		}
	}

	public void Start() 
	{
		try
		{
			m_Thread.Start();
		}
		catch (Exception _Ex)
		{
			EJBClientLogger.ConsolError("Remote cThread Start Method Call Error..!");
			EJBClientLogger.FileError("Remote cThread Start Method Call Error..!", _Ex);
		}
	}
	public void Stop()
	{
		try
		{
			m_Thread.Stop();
		}
		catch (Exception _Ex)
		{
			EJBClientLogger.ConsolError("Remote cThread Stop Method Call Error..!");
			EJBClientLogger.FileError("Remote cThread Stop Method Call Error..!", _Ex);
		}
	}
	public void AddFunction(Runnable _Function) 
	{
		if (_Function instanceof cBaseRemoteClass)
		{
			try
			{
				m_Thread.AddFunction((Runnable)((cBaseRemoteClass<?>)_Function).GetRemoteInstance());
			}
			catch (Exception _Ex)
			{
				EJBClientLogger.ConsolError("Remote cThread Stop Method Call Error..!");
				EJBClientLogger.FileError("Remote cThread Stop Method Call Error..!", _Ex);			
			}
		}
	}
	public Boolean RemoveFunction(Runnable _Function) 
	{
		try
		{
			return m_Thread.RemoveFunction(_Function);
		}
		catch (Exception _Ex)
		{
			EJBClientLogger.ConsolError("Remote cThread Stop Method Call Error..!");
			EJBClientLogger.FileError("Remote cThread Stop Method Call Error..!", _Ex);
			return false;
		}
	}

	@Override
	public IThread GetRemoteInstance() 
	{
		return m_Thread;
	}
	
}
