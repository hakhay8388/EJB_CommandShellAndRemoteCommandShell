package com.sanalstil.pEjb.pClient.pRemoteClasses.pRemoteComponents;


import com.sanalstil.pEJB.pComponents.pTimer.ITimer;
import com.sanalstil.pEJB.pComponents.pTimer.ITimerRunnable;
import com.sanalstil.pEjb.pClient.pRemoteClasses.pBaseRemoteClass.cBaseRemoteClass;


public class cTimer extends cBaseRemoteClass<ITimer> implements ITimer
{
	private ITimer m_Timer;
	
	public cTimer()
	{
		try
		{
			m_Timer = (ITimer) GetContext().lookup("pUtils/cTimer");
		}
		catch (Exception _Ex)
		{
			EJBClientLogger.ConsolError("Remote cTimer Creation Error..!");
			EJBClientLogger.FileError("Remote cTimer Creation Error..!", _Ex);
		}
	}
	public void Start()  
	{
		try
		{
			m_Timer.Start();
		}
		catch (Exception _Ex)
		{
			EJBClientLogger.ConsolError("Remote cTimer Start Method Call Error..!");
			EJBClientLogger.FileError("Remote cTimer Start Method Call Error..!", _Ex);
		}
	}
	public void Stop()
	{
		try
		{
			m_Timer.Stop();
		}
		catch (Exception _Ex)
		{
			EJBClientLogger.ConsolError("Remote cTimer Stop Method Call Error..!");
			EJBClientLogger.FileError("Remote cTimer Stop Method Call Error..!", _Ex);
		}
	}
	public void AddFunction(ITimerRunnable _Function) 
	{
		try
		{
			m_Timer.AddFunction(_Function);
		}
		catch (Exception _Ex)
		{
			EJBClientLogger.ConsolError("Remote cTimer AddFunction Method Call Error..!");
			EJBClientLogger.FileError("Remote cTimer AddFunction Method Call Error..!", _Ex);
		}
	}
	public Boolean RemoveFunction(ITimerRunnable _Function) 
	{
		try
		{
			return m_Timer.RemoveFunction(_Function);
		}
		catch (Exception _Ex)
		{
			EJBClientLogger.ConsolError("Remote cTimer RemoveFunction Method Call Error..!");
			EJBClientLogger.FileError("Remote cTimer RemoveFunction Method Call Error..!", _Ex);
			return false;
		}
	}
	public void SetInterval(int _Interval) 
	{
		try
		{
			m_Timer.SetInterval(_Interval);
		}
		catch (Exception _Ex)
		{
			EJBClientLogger.ConsolError("Remote cTimer SetInterval Method Call Error..!");
			EJBClientLogger.FileError("Remote cTimer SetInterval Method Call Error..!", _Ex);
		}
	}
	public int GetInterval() 
	{
		try
		{
			return m_Timer.GetInterval();
		}
		catch (Exception _Ex)
		{
			EJBClientLogger.ConsolError("Remote cTimer SetInterval Method Call Error..!");
			EJBClientLogger.FileError("Remote cTimer SetInterval Method Call Error..!", _Ex);
			return 0;
		}
	}
	
	@Override
	public ITimer GetRemoteInstance() 
	{
		return m_Timer;
	}
}