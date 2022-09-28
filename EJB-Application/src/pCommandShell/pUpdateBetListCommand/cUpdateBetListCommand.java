package pCommandShell.pUpdateBetListCommand;

import java.util.LinkedList;
import java.util.List;

import pAppMain.cAppMain;
import pCommandShell.cCommandIDs;
import pCommandShell.pUpdateBetListCommand.pParameter.cTimerParameter;
import com.sanalstil.pLogManager.cLogger;
import com.sanalstil.pShell.cShell;
import com.sanalstil.pShell.pCommands.cBaseCommand;
import com.sanalstil.pShell.pCommands.cBaseCommandSubThread;
import com.sanalstil.pShell.pCommands.cParameterSubParameter;
import com.sanalstil.pShell.pCommands.pGeneralReciver.cCommandStopParameter;
import com.sanalstil.pShell.pCommands.pGeneralReciver.cCommandStopAllParameter;


public class cUpdateBetListCommand extends cBaseCommand 
{
	public cTimerParameter TimerParameter = null;
	public int TimerSec = 1000;
	public List<String> MongoObjectNameList = null;
	public cLogger UpdateBetListCommandLogger = null;
 
	
	public cUpdateBetListCommand(cShell _OwnerShell) 
	{
		super(_OwnerShell, cCommandIDs.UpdateBetListCommandID);
		
		UpdateBetListCommandLogger = cAppMain.LogManager.GetLogger("BetListLog", cAppMain.LogPath);
		CommandsSubThreads = new LinkedList<cBaseCommandSubThread>();
		MongoObjectNameList = new LinkedList<String>();
		TimerParameter = new cTimerParameter();
		Connect(TimerParameter);
	}

	@Override
	public void InterpretCommand(List<String> _CommandParameter, List<cParameterSubParameter> _ParameterList)
	{
		PrintConsole(CommandID.CommandName + " Command's Action Started...");
		UpdateBetListCommandLogger.FileInfo(CommandID.CommandName + " Command Started..!");
		NonParameterRun();
		for (int i = 0; i < _ParameterList.size();i++)
		{
			cParameterSubParameter __Parameter = _ParameterList.get(i);
			if(!GetParameterClassByParameter(__Parameter.Parameter).ReciveCommand(this, __Parameter.SubParameters))
			{
				return;
			}
		}
		if (_ParameterList.size() == 1)
		{
			if (_ParameterList.get(0).Parameter.equals(cCommandStopParameter.StopParameter) || _ParameterList.get(0).Parameter.equals(cCommandStopAllParameter.StopAllParameter))
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
		while(!Exiting)
		{
			if (!InActionSubThreads())
			{
				cUpdateBetListCommandThread __SubThread = new cUpdateBetListCommandThread(this);
				__SubThread.Start();
				
				try
				{
					Thread.sleep(TimerSec);
				}
				catch(Exception _Ex)
				{
					UpdateBetListCommandLogger.ConsolError("Thread cant set status to sleep..!");
					UpdateBetListCommandLogger.FileError("Thread cant set status to sleep..!", _Ex);
				}
			}
			else
			{
				try
				{
					Thread.sleep(1000);
				}
				catch(Exception _Ex)
				{
					UpdateBetListCommandLogger.ConsolError("Thread cant set status to sleep..!");
					UpdateBetListCommandLogger.FileError("Thread cant set status to sleep..!", _Ex);
				}
			}
		}
		UpdateBetListCommandLogger.FileInfo(CommandID.CommandName + "Action Finished..!");
	}

	@Override
	public boolean IsMultiRunnable() 
	{
		return false;
	}

	@Override
	public String GetCommandInfoString() 
	{
		return "Mongo Cache DB Betlist Updater";	
	}

	@Override
	public cLogger GetCommandLogger() 
	{
		return UpdateBetListCommandLogger;
	}


}
