package pCommandShell.pUpdateTopCouponsCommand.pParamters;

import java.util.List;

import pCommandShell.pUpdateTopCouponsCommand.cUpdateTopCouponsCommand;

import com.sanalstil.pShell.pCommands.ICommandParameter;
import com.sanalstil.pShell.pCommands.cBaseCommand;

public class cTimerParameter implements ICommandParameter
{
	public boolean ReciveCommand(cBaseCommand _Command, List<String> _SubParameter)
	{
		if (_SubParameter.size() > 0)
		{
			try
			{
				int __Value = Integer.parseInt(_SubParameter.get(0));
				((cUpdateTopCouponsCommand)_Command).TimerSec = __Value; 
				return true;
			}
			catch(Exception ex)
			{
				_Command.PrintConsole("Timer Sub Parameter is Wrong..!");
				return false;
			}
		}
		else
		{
			_Command.PrintConsole("Timer Sub Parameter was not set..!");
			return false;
		}
	}

	public String GetCallParameter() 
	{
		return "-t";
	}

	public String GetHelpString() 
	{
		return "Set top coupons updater timer (sample: ' -t 5000') default value is 1000 ms";
	}

}
