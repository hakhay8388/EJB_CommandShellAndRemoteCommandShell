package pCommandShell.pLsCommand;

import java.util.LinkedList;
import java.util.List;

import pAppMain.cAppMain;
import pCommandShell.cCommandIDs;

import com.sanalstil.pLogManager.cLogger;
import com.sanalstil.pShell.cShell;
import com.sanalstil.pShell.pCommands.cBaseCommand;
import com.sanalstil.pShell.pCommands.cParameterSubParameter;


public class cLsCommand extends cBaseCommand 
{

	public cLsCommand(cShell _OwnerShell) 
	{
		super(_OwnerShell, cCommandIDs.LsCommandID);
	}

	@Override
	public void InterpretCommand(List<String> _CommandParameter, List<cParameterSubParameter> _ParameterList)
	{
		NonParameterRun();
		for (int i = 0; i < _ParameterList.size();i++)
		{
			cParameterSubParameter __Parameter = _ParameterList.get(i);
			if(!GetParameterClassByParameter(__Parameter.Parameter).ReciveCommand(this, __Parameter.SubParameters))
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
		System.out.println("");
		System.out.println("Command List ");
		System.out.println("-------------------------------------------------");
		System.out.println("");
		for (int i = 0; i < OwnerShell.CommandList.size();i++)
		{
			System.out.println(OwnerShell.CommandList.get(i).CommandID.CommandName+ "\t\t" + OwnerShell.CommandList.get(i).GetCommandInfoString());
		}
		System.out.println("");
		System.out.print(">");
	}

	@Override
	public boolean IsMultiRunnable() 
	{
		return false;
	}

	@Override
	public String GetCommandInfoString() 
	{
		return "List All Commands and Infos";
	}

	@Override
	public cLogger GetCommandLogger() 
	{
		return cAppMain.ApplicationLogger;
	}


}
