package pStartup;

import com.sanalstil.pShell.cShell;

import pTest.cExitCommand;
import pTest.cTestCommandNonParameterRun;
import pTest.cTestCommandParameter_b;
import pTest.cTestCommand;

public class cStartup {

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		cShell __Shell  = new cShell();
		cTestCommand __Command = new cTestCommand(__Shell);
		cTestCommandNonParameterRun __a = new cTestCommandNonParameterRun();
		Boolean __Result = __Command.Connect(__a);
		cTestCommandParameter_b __B = new cTestCommandParameter_b();
		__Result = __Command.Connect(__B);
		cExitCommand __ExitCommand = new cExitCommand(__Shell);		
		__Shell.StartShell();
		
	}

}
