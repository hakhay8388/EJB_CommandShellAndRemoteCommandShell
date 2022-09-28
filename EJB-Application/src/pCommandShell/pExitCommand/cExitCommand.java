package pCommandShell.pExitCommand;

import java.util.LinkedList;
import java.util.List;

import com.sanalstil.pLogManager.cLogger;
import com.sanalstil.pShell.cShell;
import com.sanalstil.pShell.pCommands.cBaseCommand;
import com.sanalstil.pShell.pCommands.cParameterSubParameter;

import pAppMain.cAppMain;
import pCommandShell.cCommandIDs;
import pCommandShell.pExitCommand.pParameters.cForceParameter;
import pCommandShell.pExitCommand.pParameters.cWaitTaskParameter;

public class cExitCommand extends cBaseCommand
{
	public boolean ForceExit = false;
	public boolean WaitTasksEnd = false;
	public cForceParameter ForceParameter = null;
	public cWaitTaskParameter WaitTaskParameter = null;	
	
	public cExitCommand(cShell _OwnerShell) 
	{
		super(_OwnerShell, cCommandIDs.ExitCommandID);
		ForceParameter = new cForceParameter();
		Connect(ForceParameter);
		WaitTaskParameter = new cWaitTaskParameter();
		Connect(WaitTaskParameter);
		
	}

	@Override
	public void InterpretCommand(List<String> _CommandParameter, List<cParameterSubParameter> _ParameterList)
	{
		ForceExit = false;
		NonParameterRun();
		for (int i = 0; i < _ParameterList.size();i++)
		{
			cParameterSubParameter __Parameter = _ParameterList.get(i);
			if(!GetParameterClassByParameter(__Parameter.Parameter).ReciveCommand(this, __Parameter.SubParameters))
			{
				return;
			}
		}
		Action();
	}
	
	private void NonParameterRun()
	{
		for (int i = 0;i < CommandParameter.size(); i++)
		{
			if (CommandParameter.get(i).GetCallParameter().equals(""))
			{
				CommandParameter.get(i).ReciveCommand(this, new LinkedList<String>());
			}
		}
	}
	
	public void Action()
	{
		if (ForceExit)
		{
			OwnerShell.ExitShell();
		}
		else if (WaitTasksEnd)
		{
			OwnerShell.PrepareCommandToExit();
			PrintConsole("Waiting Tasks For Exiting Shell.");
			while(true)
			{
				boolean __InAction = false;
				for (int i = 0; i < OwnerShell.CommandList.size(); i++)
				{
					if (OwnerShell.CommandList.get(i).InActionSubThreads())
					{
						__InAction = true;
						break;
					}
				}
				if (__InAction)
				{
					System.out.print(".");
				}
				else
				{
					OwnerShell.ExitShell();
					System.out.println("");
					System.out.println("");
					PrintConsole("All Operations Finished..!\nPress Enter For Exit Console.");
					break;
				}
				try
				{
					Thread.sleep(1000);
					
				}
				catch(Exception _Ex)
				{
					cAppMain.ApplicationLogger.ConsolError("Thread cant set status to sleep..!");
					cAppMain.ApplicationLogger.FileError("Thread cant set status to sleep..!", _Ex);
				}
			}
		}
		else
		{
			boolean __InAction = false;
			for (int i = 0; i < OwnerShell.CommandList.size(); i++)
			{
				if (OwnerShell.CommandList.get(i).InActionSubThreads())
				{
					__InAction = true;
					break;
				}
			}
			if (__InAction)
			{
				PrintConsole("Sorry, Can not Exit..! Some Command(s) in Action...");
			}
			else
			{
				OwnerShell.ExitShell();
			}
		}
	}

	@Override
	public boolean IsMultiRunnable() 
	{
		return false;
	}

	@Override
	public String GetCommandInfoString() 
	{
		return "Exit Shell Command.";
	}

	@Override
	public cLogger GetCommandLogger() 
	{
		return cAppMain.ApplicationLogger;
	}


}



