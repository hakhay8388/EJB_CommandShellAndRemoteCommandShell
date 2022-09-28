package com.sanalstil.pShell.pCommands;


public abstract class cBaseCommandSubThread implements Runnable
{
	public Thread OwnerThread = null;
	public cBaseCommand OwnerCommand = null;
	
	public cBaseCommandSubThread(cBaseCommand _Command)
	{
		OwnerCommand = _Command;
		OwnerCommand.CommandsSubThreads.add(this);
		OwnerThread = new Thread(this);
	}
	
	public void Start()
	{
		OwnerCommand.GetCommandLogger().FileInfo(OwnerCommand.CommandID.CommandName + "'s -> Sub Thread Starting...");
		OwnerThread.start();
	}

	@SuppressWarnings("deprecation")
	public void Stop()
	{
		OwnerCommand.GetCommandLogger().FileInfo(OwnerCommand.CommandID.CommandName + "'s -> Sub Thread Stoping...");
		OwnerThread.stop();
	}

	public abstract void run();
	public void SubThreadFinished()
	{
		OwnerCommand.CommandsSubThreads.remove(this);	
	}
}
