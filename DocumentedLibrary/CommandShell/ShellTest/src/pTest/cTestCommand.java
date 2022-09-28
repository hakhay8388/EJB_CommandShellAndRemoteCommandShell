package pTest;

import java.util.LinkedList;
import java.util.List;

import com.sanalstil.pShell.cShell;
import com.sanalstil.pShell.pCommandID.cCommandID;
import com.sanalstil.pShell.pCommands.cBaseCommand;
import com.sanalstil.pShell.pCommands.cParameterSubParameter;

public class cTestCommand extends cBaseCommand
{
	public cTestCommand(cShell _OwnerShell) 
	{
		super(_OwnerShell, new cCommandID(0, "Test"));
	}

	@Override
	public void InterpretCommand(List<String> _CommandParameter, List<cParameterSubParameter> _ParameterList)
	{
		NonParameterRun();
		for (int i = 0; i < _ParameterList.size();i++)
		{
			cParameterSubParameter __Parameter = _ParameterList.get(i);
			GetParameterClassByParameter(__Parameter.Parameter).ReciveCommand(this, __Parameter.SubParameters);
		}
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

	@Override
	public boolean IsMultiRunnable() 
	{
		return true;
	}
}
