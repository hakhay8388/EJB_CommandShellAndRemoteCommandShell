package pCommandShell.pExitCommand.pParameters;

import java.util.List;

import pCommandShell.pExitCommand.cExitCommand;

import com.sanalstil.pShell.pCommands.ICommandParameter;
import com.sanalstil.pShell.pCommands.cBaseCommand;

public class cForceParameter implements ICommandParameter
{
	public boolean ReciveCommand(cBaseCommand _Command, List<String> _SubParameter)
	{
		((cExitCommand)_Command).ForceExit = true;
		return true;
	}

	public String GetCallParameter() 
	{
		return "-f";
	}

	public String GetHelpString() 
	{
		return "Force to Exit Shell.";
	}

}
