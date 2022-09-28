package pCommandShell;

import pAppMain.cAppMain;
import pCommandShell.pExitCommand.cExitCommand;
import pCommandShell.pLsCommand.cLsCommand;
import pCommandShell.pThreadListCommand.cThreadListCommand;
import pCommandShell.pUpdateBetListCommand.cUpdateBetListCommand;
import pCommandShell.pUpdateTopCouponsCommand.cUpdateTopCouponsCommand;

import com.sanalstil.pShell.cShell;


public class cCommandShell 
{
	public static cShell Shell = null;
	public static cLsCommand LsCommand = null;
	public static cExitCommand ExitCommand = null;
	public static cUpdateTopCouponsCommand UpdateTopCouponsCommand = null;
	public static cThreadListCommand ThreadListCommand = null; 
	public static cUpdateBetListCommand UpdateBetListCommand = null;
	

	
	public static void Initialize()
	{
		cAppMain.ApplicationLogger.ConsolInfo("Command Shell Loading...");
		cCommandIDs.LoadCommandIDs();
		Shell  = new cShell();
		UpdateTopCouponsCommand = new cUpdateTopCouponsCommand(Shell);
		LsCommand = new cLsCommand(Shell);
		ExitCommand = new cExitCommand(Shell);
		//ThreadListCommand = new cThreadListCommand(Shell);
		UpdateBetListCommand = new cUpdateBetListCommand(Shell);
		cAppMain.ApplicationLogger.ConsolInfo("-> Done");
	}


	
	public static void StartShell()
	{
		cAppMain.ApplicationLogger.ConsolInfo("Starting Shell...");
		Shell.StartShell();
	}
	
	public static void StopShell()
	{
		cAppMain.ApplicationLogger.ConsolInfo("Exiting Shell...");
		Shell.ExitShell();
	}
}
