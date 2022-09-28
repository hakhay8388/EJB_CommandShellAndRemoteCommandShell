package com.sanalstil.pShell.pCommands.pGeneralReciver;

import java.util.List;

import com.sanalstil.pShell.pCommands.ICommandParameter;
import com.sanalstil.pShell.pCommands.cBaseCommand;


/**
 * @author SanalStil AR-GE  -  Hayri Eryürek  
 * @version Tarih : 07.04.2011 - Version 1.0  
 * @see cBaseCommand, ICommandParameter
 */  


public class cCommandStopParameter implements ICommandParameter
{
	public static String StopParameter = "-stop";
	
	public boolean ReciveCommand(cBaseCommand _Command, List<String> _SubParameter)
	{
		_Command.Stop();
		System.out.println(_Command.CommandID.CommandName + "'s Thread is Stopped..!");
		_Command.GetCommandLogger().FileInfo(_Command.CommandID.CommandName + "'s Thread is Stopped..!");
		return true;
	}

	public String GetCallParameter() 
	{
		return StopParameter;
	}

	public String GetHelpString() 
	{
		return "Stop Command Thread and Waiting Sub Threads to Stop.";
	}

}
