package com.sanalstil.pShell.pCommands.pGeneralReciver;

import java.util.List;

import com.sanalstil.pShell.pCommands.ICommandParameter;
import com.sanalstil.pShell.pCommands.cBaseCommand;


/**
 * @author SanalStil AR-GE  -  Hayri Eryürek  
 * @version Tarih : 07.04.2011 - Version 1.0  
 * @see cBaseCommand, ICommandParameter
 */  

public class cCommandHelpParamater implements ICommandParameter 
{
	public static String HelpParameter = "-help";
		
	public boolean ReciveCommand(cBaseCommand _Command, List<String> _SubParameter) 
	{
		System.out.println();
		System.out.println("-------- Help ---------");
		System.out.println();		
		for (int i = 0; i < _Command.CommandParameter.size();i++)
		{
			ICommandParameter __Reciver = _Command.CommandParameter.get(i);
			if (!__Reciver.GetCallParameter().equals(""))
			{
				System.out.println(__Reciver.GetCallParameter() + "\t" + __Reciver.GetHelpString());
			}
		}
		return false;
	}
	
	public String GetCallParameter() 
	{
		return HelpParameter;
	}

	public String GetHelpString() 
	{
		return "Help Command";
	}


}
