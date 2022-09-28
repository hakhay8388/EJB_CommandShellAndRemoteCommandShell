package pCommandShell.pThreadListCommand;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import pAppMain.cAppMain;
import pCommandShell.cCommandIDs;

import com.sanalstil.pLogManager.cLogger;
import com.sanalstil.pShell.cShell;
import com.sanalstil.pShell.pCommands.cBaseCommand;
import com.sanalstil.pShell.pCommands.cParameterSubParameter;


public class cThreadListCommand extends cBaseCommand 
{
    private Map m_CpuTimes = new HashMap();
	private Map m_CpuTimeFetch = new HashMap();
    
	@SuppressWarnings("rawtypes")
	public cThreadListCommand(cShell _OwnerShell) 
	{
		super(_OwnerShell, cCommandIDs.ThreadListCommandID);
	    m_CpuTimes = new HashMap();
	    m_CpuTimeFetch = new HashMap();

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
		long cpus = Runtime.getRuntime().availableProcessors();
		ThreadMXBean threads = ManagementFactory.getThreadMXBean();
		long now = System.currentTimeMillis();

		ThreadMXBean __Threads = ManagementFactory.getThreadMXBean();
			System.out.println("");
			System.out.println("Command List ");
			System.out.println("-------------------------------------------------");
			System.out.println("");
			for (int i = 0; i < OwnerShell.CommandList.size();i++)
			{
				for(int j = 0; j <  OwnerShell.CommandList.get(i).OwnerThreadList.size();j++)
				{
					OwnerShell.CommandList.get(i).OwnerThreadList.get(j).getPriority();
				    long id = OwnerShell.CommandList.get(i).OwnerThreadList.get(j).getId();
				    Long idid = new Long(id);
				    long current = 0;
				    if (m_CpuTimes.get(idid) != null) {
				        long prev = ((Long) m_CpuTimes.get(idid)).longValue();
				        current = threads.getThreadCpuTime(id);
				        long catchTime = ((Long) m_CpuTimeFetch.get(idid)).longValue();
				        double percent = ((double)(current - prev)) / ((double)((now - catchTime) * cpus * 10000));
				        if (percent > 0 && prev > 0) 
					    {
				        	System.out.println(OwnerShell.CommandList.get(i).CommandID.CommandName+ "\t-\t Thread " + j + " Cpu Usage = " + percent + " (" + prev + ", " + current + ")");
					    }
					}
					m_CpuTimes.put(idid, new Long(current));  
					m_CpuTimeFetch.put(idid, new Long(now));					 
				}
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
		return "Thread List Commands and Infos";
	}

	@Override
	public cLogger GetCommandLogger() 
	{
		return cAppMain.ApplicationLogger;
	}




}
