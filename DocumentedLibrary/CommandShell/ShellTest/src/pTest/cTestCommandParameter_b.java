package pTest;

import java.util.List;

import com.sanalstil.pShell.pCommands.ICommandParameter;
import com.sanalstil.pShell.pCommands.cBaseCommand;

public class cTestCommandParameter_b implements ICommandParameter 
{

	public boolean ReciveCommand(cBaseCommand _Command, List<String> _SubParameter) 
	{
		_Command.PrintConsole("Komut '-b'  parametresi ile çağrılmıştır.");
		for (int i = 0; i < _SubParameter.size();i++)
		{
			_Command.PrintConsole("Alt Parametre : " + _SubParameter.get(i));
		}
		return true;
	}

	public String GetCallParameter() 
	{
		return "-b";
	}

	public String GetHelpString() 
	{
		return "'-b' Yadım yazısı buraya yazılmalıdır.";
	}

}
