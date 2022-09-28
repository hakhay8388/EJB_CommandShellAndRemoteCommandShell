package com.sanalstil.pEJB.pComponents.pThread;

import javax.annotation.PostConstruct;
import javax.ejb.Remote;
import javax.ejb.Stateful;

import com.sanalstil.pEJB.pBaseBean.cBaseBean;


@Stateful(mappedName="pUtils/cThread")
@Remote(IThread.class)
public class cThreadProxy extends cBaseBean implements IThread
{
	private IThread m_Thread = null;
	
	@PostConstruct
	public void Constructor()
	{
		m_Thread = new cThreadMain();
	}

	@Override
	public void Start()
	{
		m_Thread.Start();
	}

	@Override
	public void Stop() 
	{
		m_Thread.Stop();
	}

	@Override
	public void AddFunction(Runnable _Function) 
	{
		m_Thread.AddFunction(_Function);
	}

	@Override
	public Boolean RemoveFunction(Runnable _Function) 
	{
		return m_Thread.RemoveFunction(_Function);
	}
	

}
