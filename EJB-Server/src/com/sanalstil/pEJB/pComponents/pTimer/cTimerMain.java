package com.sanalstil.pEJB.pComponents.pTimer;

import java.util.LinkedList;
import java.util.List;
import javax.swing.Timer;
import java.awt.event.*;


public class cTimerMain implements ITimer 
{
	private List<ITimerRunnable> m_TimerFunctions = null;
	private Timer m_Timer = null;
	private int m_Interval;
	private boolean m_Started;
	
	public cTimerMain()
	{
		m_TimerFunctions = new LinkedList<ITimerRunnable>();
		m_Interval = 1000;
		
		ActionListener __TaskPerformer = new ActionListener()
	    {
			@Override
			public void actionPerformed(ActionEvent e)
	        {
				Run(e);
	        }

	     };

		m_Timer = new Timer(m_Interval, __TaskPerformer);
		m_Started = false;		
	}
	
	public void AddFunction(ITimerRunnable _Function)
	{
		m_TimerFunctions.add(_Function);
	}

	public Boolean RemoveFunction(ITimerRunnable _Function)
	{
		return m_TimerFunctions.remove(_Function);
	}
	
	@Override
	public void Start() 
	{
		if (!m_Started)
		{
			m_Started = true;
			m_Timer.setDelay(m_Interval);
			m_Timer.start();
		}
	}

	@Override
	public void Stop() 
	{
		if (m_Started)
		{
			m_Started = false;
			m_Timer.stop();
		}
	}

	public void Run(ActionEvent e) 
	{
		System.out.println("Timer Calisti.!");
		for (int i = 0; i < m_TimerFunctions.size();i++)
		{
			m_TimerFunctions.get(i).Run(e);
		}
	}

	@Override
	public void SetInterval(int _Interval) 
	{
		m_Interval = _Interval;
		if (m_Started)
		{
			Stop();
			Start();
		}
	}

	@Override
	public int GetInterval() 
	{
		return m_Interval;
	}
	
}
