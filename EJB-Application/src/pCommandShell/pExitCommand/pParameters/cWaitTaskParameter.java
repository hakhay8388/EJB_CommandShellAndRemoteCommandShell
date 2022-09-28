package pCommandShell.pExitCommand.pParameters;

import java.util.List;

import com.sanalstil.pShell.pCommands.ICommandParameter;
import com.sanalstil.pShell.pCommands.cBaseCommand;
import pCommandShell.pExitCommand.cExitCommand;

public class cWaitTaskParameter implements ICommandParameter 
{

	public boolean ReciveCommand(cBaseCommand _Command, List<String> _SubParameter)
	{
		((cExitCommand)_Command).WaitTasksEnd = true;
		return true;
	}

	public String GetCallParameter() 
	{
		return "-w";
	}

	public String GetHelpString() 
	{
		return "Wait Tasks End For Exiting Shell.";
	}

}

