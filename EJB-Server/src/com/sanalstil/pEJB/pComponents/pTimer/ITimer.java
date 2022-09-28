package com.sanalstil.pEJB.pComponents.pTimer;

public interface ITimer
{
	void Start();
	void Stop();
	void AddFunction(ITimerRunnable _Function);
	Boolean RemoveFunction(ITimerRunnable _Function);
	void SetInterval(int _Interval);
	int GetInterval();
}
