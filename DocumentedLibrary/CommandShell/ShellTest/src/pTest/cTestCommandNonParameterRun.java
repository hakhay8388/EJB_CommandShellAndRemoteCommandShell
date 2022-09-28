package pTest;

import java.util.List;

import com.sanalstil.pShell.pCommands.ICommandParameter;
import com.sanalstil.pShell.pCommands.cBaseCommand;

public class cTestCommandNonParameterRun implements ICommandParameter 
{


	public String GetCallParameter()
	{
		return "";
	}

	public String GetHelpString() 
	{
		return null;
	}

	public boolean ReciveCommand(cBaseCommand _Command, List<String> _SubParameter) 
	{
		_Command.PrintConsole("Komut parametresiz çağrıldığındada çalışır");
		return true;
	}

}
