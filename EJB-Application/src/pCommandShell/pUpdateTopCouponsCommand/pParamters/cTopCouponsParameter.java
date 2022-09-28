package pCommandShell.pUpdateTopCouponsCommand.pParamters;

import java.util.List;

import com.sanalstil.pShell.pCommands.ICommandParameter;
import com.sanalstil.pShell.pCommands.cBaseCommand;

import pCommandShell.pUpdateTopCouponsCommand.cUpdateTopCouponsCommand;

public class cTopCouponsParameter implements ICommandParameter
{
	public boolean ReciveCommand(cBaseCommand _Command, List<String> _SubParameter)
	{
		if (_SubParameter.size() > 0)
		{
			try
			{
				int __Value = Integer.parseInt(_SubParameter.get(0));
				
				((cUpdateTopCouponsCommand)_Command).TopNCoupons = __Value; 
				return true;
			}
			catch(Exception ex)
			{
				_Command.PrintConsole("Coupons Count Sub Parameter is Wrong..!");
				return false;
			}
		}
		else
		{
			_Command.PrintConsole("Coupons Count Sub Parameter was not set..!");
			return false;
		}
	}

	public String GetCallParameter() 
	{
		return "-n";
	}

	public String GetHelpString() 
	{
		return "Set how many top coupons to update (sample: ' -n 10') default value is 20 ";
	}

}
