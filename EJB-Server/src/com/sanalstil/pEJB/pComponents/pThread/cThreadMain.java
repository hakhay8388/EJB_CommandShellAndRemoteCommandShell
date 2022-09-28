package com.sanalstil.pEJB.pComponents.pThread;

import java.util.LinkedList;
import java.util.List;

public class cThreadMain implements IThread, Runnable 
{
	private List<Runnable> m_ThreadFunctions = null;
	private Thread m_Thread = null;
	
	public cThreadMain()
	{
		m_ThreadFunctions = new LinkedList<Runnable>();
		m_Thread = new Thread(this);
	}
	
	public void AddFunction(Runnable _Function)
	{
		m_ThreadFunctions.add(_Function);
	}

	public Boolean RemoveFunction(Runnable _Function)
	{
		return m_ThreadFunctions.remove(_Function);
	}
	
	@Override
	public void Start() 
	{
		m_Thread.start();
	}

	@SuppressWarnings("deprecation")
	@Override
	public void Stop() 
	{
		m_Thread.stop();
	}

	@Override
	public void run() 
	{
		for (int i = 0; i < m_ThreadFunctions.size();i++)
		{
			m_ThreadFunctions.get(i).run();
		}
	}
}
