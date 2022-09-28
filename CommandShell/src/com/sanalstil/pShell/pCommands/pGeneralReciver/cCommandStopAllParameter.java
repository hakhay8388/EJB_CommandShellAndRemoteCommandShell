package com.sanalstil.pShell.pCommands.pGeneralReciver;

import java.util.List;

import com.sanalstil.pShell.pCommands.ICommandParameter;
import com.sanalstil.pShell.pCommands.cBaseCommand;


/**
 * @author SanalStil AR-GE  -  Hayri Eryürek  
 * @version Tarih : 07.04.2011 - Version 1.0  
 * @see cBaseCommand, ICommandParameter
 */  


public class cCommandStopAllParameter implements ICommandParameter
{
	public static String StopAllParameter = "-stopall";
	
	public boolean ReciveCommand(cBaseCommand _Command, List<String> _SubParameter)
	{
		_Command.StopWithSubThreads();
		System.out.println(_Command.CommandID.CommandName + "'s Threads and Sub Threads ara Stopped..!");
		_Command.GetCommandLogger().FileInfo(_Command.CommandID.CommandName + "'s Threads and Sub Threads ara Stopped..!");
		return true;
	}

	public String GetCallParameter() 
	{
		return StopAllParameter;
	}

	public String GetHelpString() 
	{
		return "Stop Command Thread and Stop All Sub Threads.";
	}

}
