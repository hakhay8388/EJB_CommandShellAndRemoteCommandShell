package pCommandShell.pUpdateTopCouponsCommand;

import java.util.LinkedList;
import java.util.List;

import pAppMain.cAppMain;
import pCommandShell.cCommandIDs;
import pCommandShell.pUpdateTopCouponsCommand.pParamters.cTimerParameter;
import pCommandShell.pUpdateTopCouponsCommand.pParamters.cTopCouponsParameter;

import com.sanalstil.pLogManager.cLogManager;
import com.sanalstil.pLogManager.cLogger;
import com.sanalstil.pShell.cShell;
import com.sanalstil.pShell.pCommands.cBaseCommand;
import com.sanalstil.pShell.pCommands.cBaseCommandSubThread;
import com.sanalstil.pShell.pCommands.cParameterSubParameter;
import com.sanalstil.pShell.pCommands.pGeneralReciver.cCommandStopAllParameter;
import com.sanalstil.pShell.pCommands.pGeneralReciver.cCommandStopParameter;


public class cUpdateTopCouponsCommand extends cBaseCommand 
{
	public cTimerParameter TimerParameter = null;
	public cTopCouponsParameter TopCouponsParameter = null;	
	public int TimerSec = 1000;
	public int TopNCoupons = 20;
	public List<String> MongoObjectNameList = null;
	public cLogger UpdateTopCouponsCommandLogger = null;
 
	
	public cUpdateTopCouponsCommand(cShell _OwnerShell) 
	{
		super(_OwnerShell, cCommandIDs.UpdateTopCouponCommandID);
		
		UpdateTopCouponsCommandLogger = cAppMain.LogManager.GetLogger("TopCouponsLog", cAppMain.LogPath);
		
		CommandsSubThreads = new LinkedList<cBaseCommandSubThread>();
		MongoObjectNameList = new LinkedList<String>();
		AddObjectNames();
		TimerParameter = new cTimerParameter();
		Connect(TimerParameter);
		TopCouponsParameter = new cTopCouponsParameter();
		Connect(TopCouponsParameter);
	}
	
	private void AddObjectNames()
	{
		MongoObjectNameList.add("amount");
		MongoObjectNameList.add("odd");
		MongoObjectNameList.add("shared");
		MongoObjectNameList.add("prepared");
		MongoObjectNameList.add("holly");
	}

	@Override
	public void InterpretCommand(List<String> _CommandParameter, List<cParameterSubParameter> _ParameterList)
	{
		PrintConsole(CommandID.CommandName + " Command's Action Started...");
		UpdateTopCouponsCommandLogger.FileInfo(CommandID.CommandName + " Command Started..!");
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
			if (CommandsSubThreads.size() < 1)
			{
				cUpdateTopCouponsCommandThread __SubThread = new cUpdateTopCouponsCommandThread(this);
				__SubThread.Start();
				
				try
				{
					Thread.sleep(TimerSec);
				}
				catch(Exception _Ex)
				{
					UpdateTopCouponsCommandLogger.ConsolError("Thread cant set status to sleep..!");
					UpdateTopCouponsCommandLogger.FileError("Thread cant set status to sleep..!", _Ex);
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
					UpdateTopCouponsCommandLogger.ConsolError("Thread cant set status to sleep..!");
					UpdateTopCouponsCommandLogger.FileError("Thread cant set status to sleep..!", _Ex);
				}
			}
		}
		UpdateTopCouponsCommandLogger.FileInfo(CommandID.CommandName + "Action Finished..!");
	}

	@Override
	public boolean IsMultiRunnable() 
	{
		return false;
	}

	@Override
	public String GetCommandInfoString() 
	{
		return "Mongo N Top Coupons Updater";	
	}

	@Override
	public cLogger GetCommandLogger() 
	{
		return UpdateTopCouponsCommandLogger;
	}


}
