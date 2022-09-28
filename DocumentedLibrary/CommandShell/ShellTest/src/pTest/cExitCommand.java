package pTest;

import java.util.List;
import com.sanalstil.pShell.cShell;
import com.sanalstil.pShell.pCommandID.cCommandID;
import com.sanalstil.pShell.pCommands.cBaseCommand;
import com.sanalstil.pShell.pCommands.cParameterSubParameter;

public class cExitCommand extends cBaseCommand
{
	public cExitCommand(cShell _OwnerShell) 
	{
		super(_OwnerShell, new cCommandID(0, "Exit"));
	}

	@Override
	public void InterpretCommand(List<String> _CommandParameter, List<cParameterSubParameter> _ParameterList)
	{
		OwnerShell.ExitShell();
	}

	@Override
	public boolean IsMultiRunnable() 
	{
		return false;
	}
}
