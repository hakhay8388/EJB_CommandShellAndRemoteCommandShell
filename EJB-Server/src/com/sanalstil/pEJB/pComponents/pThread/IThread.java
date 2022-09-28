package com.sanalstil.pEJB.pComponents.pThread;

public interface IThread
{
	void Start();
	void Stop();
	void AddFunction(Runnable _Function);
	Boolean RemoveFunction(Runnable _Function);
}
