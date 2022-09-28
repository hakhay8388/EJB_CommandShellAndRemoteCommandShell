package pCommandShell;

import java.util.LinkedList;
import java.util.List;

import com.sanalstil.pShell.pCommandID.cCommandID;

public class cCommandIDs 
{
	public static cCommandID LsCommandID = null;
	public static cCommandID ExitCommandID = null;
	public static cCommandID ThreadListCommandID = null;
	public static cCommandID UpdateTopCouponCommandID = null;
	public static cCommandID UpdateBetListCommandID = null;
	
	public static List<cCommandID> CommandIDList = null;
	
	public static void LoadCommandIDs()
	{
		CommandIDList = new LinkedList<cCommandID>();
		
		LsCommandID = new cCommandID(1, "ls");
		CommandIDList.add(LsCommandID);
		ExitCommandID = new cCommandID(2, "exit");
		CommandIDList.add(ExitCommandID);
		ThreadListCommandID = new cCommandID(3, "tls");
		CommandIDList.add(ThreadListCommandID);
		UpdateTopCouponCommandID = new cCommandID(4, "updatetop");
		CommandIDList.add(UpdateTopCouponCommandID);
		UpdateBetListCommandID = new cCommandID(5, "updatebetlist");
		CommandIDList.add(UpdateBetListCommandID);
	}
	
}
