package pCommandShell.pUpdateTopCouponsCommand;

import pDatabase.cDatabase;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCsanalstilction;
import com.sanalstil.pEJB.pDatabase.pResultObjects.cDataJsonResult;
import com.sanalstil.pShell.pCommands.cBaseCommandSubThread;
import com.sanalstil.pUtils.cDate;


public class cUpdateTopCouponsCommandThread extends cBaseCommandSubThread
{
	public cUpdateTopCouponsCommandThread(cUpdateTopCouponsCommand _Command)
	{
		super(_Command);
	}
	
	public void run()
	{
		try
		{
			cUpdateTopCouponsCommand __OwnerCommand = ((cUpdateTopCouponsCommand)OwnerCommand);
			OwnerCommand.GetCommandLogger().FileInfo("UpdateTopCouponsCommand Sub Thread Started...");
			for (int j = 0; j < __OwnerCommand.MongoObjectNameList.size(); j++)
			{
				cDataJsonResult __Result = cDatabase.RemoteDatabase.Iddaa.Coupons.GetTop(__OwnerCommand.TopNCoupons, __OwnerCommand.MongoObjectNameList.get(j));
				for (int i = 0; i < cDatabase.MongoDatabase.MongoDbs.size(); i++)
				{
					DBCsanalstilction __MongoCsanalstilctionDataIddaa = cDatabase.MongoDatabase.MongoDbs.get(i).getCsanalstilction("iddaa_coupons_top");
					BasicDBObject tSearch = new BasicDBObject("name",__OwnerCommand.MongoObjectNameList.get(j));
					BasicDBObject tReplace = new BasicDBObject();
					long __LastUpdateTime = cDate.GetTimestap(); 			
					tReplace.put("updateTime", __LastUpdateTime);
					tReplace.put("data", __Result.dataArray.toString());
					__MongoCsanalstilctionDataIddaa.update(tSearch, new BasicDBObject("$set",tReplace), true, true);
				}
			}
			OwnerCommand.GetCommandLogger().FileInfo("UpdateTopCouponsCommand Sub Thread Success...");
			
		}
		catch(Exception _Ex)
		{
			OwnerCommand.GetCommandLogger().ConsolError("UpdateTopCouponsCommand Thread Error..!");
			OwnerCommand.GetCommandLogger().FileError("UpdateTopCouponsCommand Thread Error..!", _Ex);
		}
		SubThreadFinished();
	}
}
