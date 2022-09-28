package com.sanalstil.pShell;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

import com.sanalstil.pShell.pCommands.cBaseCommand;
import com.sanalstil.pShell.pCommands.cParameterSubParameter;
import com.sanalstil.pShell.pCommands.pGeneralReciver.cCommandHelpParamater;
import com.sanalstil.pShell.pCommands.pGeneralReciver.cCommandStopAllParameter;
import com.sanalstil.pShell.pCommands.pGeneralReciver.cCommandStopParameter;


/**
 * @author SanalStil AR-GE  -  Hayri Eryürek  
 * @version Tarih : 07.04.2011 - Version 1.0  
 * @see Shell
 */  

public class cShellInterpreter  extends Thread
{
	private cShell OwnerShell = null;
	private boolean ExitShell;
	private BufferedReader ConsoleBufferReader;
	
	public cShellInterpreter(cShell _OwnerShell)
	{
		OwnerShell = _OwnerShell;
		ExitShell = false;
	}
	
	public void ExitShell()
	{
		ExitShell = true;
	}
	
	public void run() 
	{
		  ConsoleBufferReader = new BufferedReader(new InputStreamReader(System.in));
	      try {
	    	  while(!ExitShell)
	    	  {	    		
	    		  System.out.print("> ");
	    		  try 
	    		  {
	    			  Thread.sleep(100);
	    		  } 
	    		  catch (Exception e) 
	    		  {
	    		  }
	    		  if (ExitShell)
	    		  {
	    			  break;  
	    		  }
	    		  List<cParameterSubParameter> __ParameterList = ParseCommandLine(ConsoleBufferReader.readLine());
	    		  if (ExitShell)
	    		  {
	    			  break;
	    		  }
	    		  cBaseCommand __Command = OwnerShell.GetCommandByName(__ParameterList.get(0).Parameter);
	    		  if (__Command != null)
	    		  {
	    			  List<String> __CommandSubParameter = __ParameterList.get(0).SubParameters;
	    			  __ParameterList.remove(0);
	    			  if (ControlParameter(__Command, __ParameterList))
	    			  {

	    				boolean __GenericCommands = false;
						for (int j = 0; j < __ParameterList.size();j++)
						{
							if (__ParameterList.get(j).Parameter.equals(cCommandHelpParamater.HelpParameter))
							{
								__Command.GetParameterClassByParameter(cCommandHelpParamater.HelpParameter).ReciveCommand(__Command, __ParameterList.get(j).SubParameters);
								__GenericCommands = true;
							}
						}
		    			
						for (int j = 0; j < __ParameterList.size();j++)
						{
							if (__ParameterList.get(j).Parameter.equals(cCommandStopParameter.StopParameter))
							{
								__Command.GetParameterClassByParameter(cCommandStopParameter.StopParameter).ReciveCommand(__Command, __ParameterList.get(j).SubParameters);
								__GenericCommands = true;
							}
						}
						
						for (int j = 0; j < __ParameterList.size();j++)
						{
							if (__ParameterList.get(j).Parameter.equals(cCommandStopAllParameter.StopAllParameter))
							{
								__Command.GetParameterClassByParameter(cCommandStopAllParameter.StopAllParameter).ReciveCommand(__Command, __ParameterList.get(j).SubParameters);
								__GenericCommands = true;
							}
						}
						
						if (!__GenericCommands)
						{
		    			  	if ((!__Command.IsRunning()) || (__Command.IsMultiRunnable() && __Command.IsRunning()))
		    			  	{
		    			  		__Command.ParameterList = __ParameterList;
		    			  		__Command.CommandSubParameter = __CommandSubParameter;
		    			  		Thread __Thread = new Thread(__Command);
		    			  		__Command.OwnerThreadList.add(__Thread);
			    				__Thread.start();
			    			}
			    			else
			    			{
		    					System.out.println("This Command Already Running and No Permission Multi Run..!");
			    			}
						}
		    		 }
		    		 else
		    		 {
		    			 __Command.PrintHelp();
		    		 }
	    		  }
	    		  else
	    		  {
	    			  System.out.println("Command Not Found..!");
	    		  }
	    	  }
	      } 
	      catch (IOException ioe) 
	      {
	         System.out.println("IO error trying to read your name!");
	         System.exit(1);
	      }
	      KillAllCommand();
	      System.out.println("End of Shell");
	}
	
	private void KillAllCommand()
	{
		for (int i = 0; i < OwnerShell.CommandList.size() ;i++)
		{
			OwnerShell.CommandList.get(i).Stop();
		}
	}

	
	public boolean ControlParameter(cBaseCommand _Command, List<cParameterSubParameter> _ParameterList)
	{
		for (int i = 0; i < _ParameterList.size() ;i++)
		{
			Boolean __Found = false;
			String __CommandParameter = _ParameterList.get(i).Parameter;
			for (int j = 0; j < _Command.CommandParameter.size();j++)
			{
				if (_Command.CommandParameter.get(j).GetCallParameter().equals(__CommandParameter))
				{
					__Found = true;
				}
			}
			if (!__Found)
			{
				return false;
			}
		}
		return true;
	}
	
	public List<cParameterSubParameter> ParseCommandLine(String _CommandLine)
	{
		String[] __ParsedCommand = _CommandLine.split(" ");
		List<cParameterSubParameter> __Result = new LinkedList<cParameterSubParameter>();
		int __Index = 0;

		for (int i = 0;i < __ParsedCommand.length; i++)
		{
			if (i == 0)
			{
				__Result.add(new cParameterSubParameter(__ParsedCommand[i], new LinkedList<String>()));
			}
			else
			{
				String __Parameter = __ParsedCommand[i].substring(0, 1);
				if (__Parameter.equals("-"))
				{
					__Index++;
					cParameterSubParameter __ParameterSubParameter = new cParameterSubParameter(__ParsedCommand[i], new LinkedList<String>());
					__Result.add(__ParameterSubParameter);
				}
				else
				{
					__Result.get(__Index).SubParameters.add(__ParsedCommand[i]);
				}
			}
		}
		return __Result;
	}
}
