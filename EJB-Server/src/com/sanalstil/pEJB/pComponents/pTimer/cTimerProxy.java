package com.sanalstil.pEJB.pComponents.pTimer;

import javax.annotation.PostConstruct;
import javax.annotation.security.PermitAll;
import javax.ejb.Remote;
import javax.ejb.Stateful;

import org.jboss.ejb3.annotation.SecurityDomain;

import com.sanalstil.pEJB.pBaseBean.cBaseBean;


@Stateful(mappedName="pUtils/cTimer")
@SecurityDomain("other")
@Remote(ITimer.class)
@PermitAll
public class cTimerProxy extends cBaseBean implements ITimer
{
	private ITimer m_Timer = null;
	
	@PostConstruct
	public void Constructor()
	{
		m_Timer = new cTimerMain();
	}

	@Override
	public void Start()
	{
		m_Timer.Start();
	}

	@Override
	public void Stop() 
	{
		m_Timer.Stop();
	}

	@Override
	public void AddFunction(ITimerRunnable _Function) 
	{
		m_Timer.AddFunction(_Function);
	}

	@Override
	public Boolean RemoveFunction(ITimerRunnable _Function) 
	{
		return m_Timer.RemoveFunction(_Function);
	}

	@Override
	public void SetInterval(int _Interval) 
	{
		m_Timer.SetInterval(_Interval);
	}

	@Override
	public int GetInterval() 
	{
		return m_Timer.GetInterval();
	}

}
