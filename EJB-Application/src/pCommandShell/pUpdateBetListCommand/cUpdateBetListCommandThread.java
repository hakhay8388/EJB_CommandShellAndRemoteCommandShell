package pCommandShell.pUpdateBetListCommand;

import org.json.simple.JSONObject;

import pDatabase.cDatabase;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCsanalstilction;
import com.sanalstil.pEJB.pDatabase.pResultObjects.cDataJsonResult;
import com.sanalstil.pEJB.pDatabaseBeans.pIddaa.pBetList.cBetListUpdateResult;
import com.sanalstil.pShell.pCommands.cBaseCommandSubThread;
import com.sanalstil.pUtils.cDate;


public class cUpdateBetListCommandThread extends cBaseCommandSubThread
{
	public cUpdateBetListCommandThread(cUpdateBetListCommand _Command)
	{
		super(_Command);
	}
	
	public void UpdateMongo(JSONObject _JsonObject)
	{
		for (int i = 0; i < cDatabase.MongoDatabase.MongoDbs.size(); i++)
		{
			DBCsanalstilction __MongoCsanalstilctionDataIddaa = cDatabase.MongoDatabase.MongoDbs.get(i).getCsanalstilction("iddaa_betlist");
			BasicDBObject tSearch = new BasicDBObject("name", "betlist");
			BasicDBObject tReplace = new BasicDBObject();
			long __LastUpdateTime = cDate.GetTimestap(); 			
			tReplace.put("updateTime", __LastUpdateTime);
			tReplace.put("data", _JsonObject.toString());
			__MongoCsanalstilctionDataIddaa.update(tSearch, new BasicDBObject("$set",tReplace), true, true);
		}
	}
	
	public void run()
	{
		try
		{
			OwnerCommand.GetCommandLogger().FileInfo("cUpdateBetListCommandThread Thread Started...");	
			
			cBetListUpdateResult __IsUpdated = cDatabase.RemoteDatabase.Iddaa.BetList.IsUpdated();
			
			if (__IsUpdated.Updated)
			{
				JSONObject __JsonObjectBetlistData = new JSONObject();
				/*cDataJsonResult __AthletismResult = cDatabase.RemoteDatabase.Iddaa.BetList.Athletism.GetMatchProgram();
				cDataJsonResult __BasketballResult = cDatabase.RemoteDatabase.Iddaa.BetList.Basketball.GetMatchProgram();
				cDataJsonResult __BillardResult = cDatabase.RemoteDatabase.Iddaa.BetList.Billard.GetMatchProgram();*/
				cDataJsonResult __FootballResult = cDatabase.RemoteDatabase.Iddaa.BetList.Football.GetMatchProgram();
/*				cDataJsonResult __HandballResult = cDatabase.RemoteDatabase.Iddaa.BetList.Handball.GetMatchProgram();
				cDataJsonResult __MotorsportsResult = cDatabase.RemoteDatabase.Iddaa.BetList.Motorsports.GetMatchProgram();
				cDataJsonResult __TennisResult = cDatabase.RemoteDatabase.Iddaa.BetList.Tennis.GetMatchProgram();
				cDataJsonResult __VsanalstilyResult = cDatabase.RemoteDatabase.Iddaa.BetList.Vsanalstilyball.GetMatchProgram();*/
				
				/*__JsonObjectBetlistData.put("athletismData", __AthletismResult.dataObject);
				__JsonObjectBetlistData.put("basketballData", __BasketballResult.dataObject);
				__JsonObjectBetlistData.put("billardData", __BillardResult.dataObject);*/
				__JsonObjectBetlistData.put("footballData", __FootballResult.dataObject);
				/*__JsonObjectBetlistData.put("handballData", __HandballResult.dataObject);
				__JsonObjectBetlistData.put("MotorsportsData", __MotorsportsResult.dataObject);
				__JsonObjectBetlistData.put("tennisData", __TennisResult.dataObject);
				__JsonObjectBetlistData.put("vsanalstilyballData", __VsanalstilyResult.dataObject);*/
				
				UpdateMongo(__JsonObjectBetlistData);
	
				OwnerCommand.GetCommandLogger().FileInfo("Updated-> UpdateTime: "+__IsUpdated.LastUpdateTime + " Total: "+__IsUpdated.LastTotal);
				OwnerCommand.GetCommandLogger().FileInfo("cUpdateBetListCommandThread Thread Success...");
			}
			else
			{
				OwnerCommand.GetCommandLogger().FileInfo("No Update-> UpdateTime: "+__IsUpdated.LastUpdateTime + " Total: "+__IsUpdated.LastTotal);
				OwnerCommand.GetCommandLogger().FileInfo("cUpdateBetListCommandThread Thread Success...");
			}
			
		}
		catch(Exception _Ex)
		{
			OwnerCommand.GetCommandLogger().ConsolError("cUpdateBetListCommandThread Thread Error..!");
			OwnerCommand.GetCommandLogger().FileError("cUpdateBetListCommandThread Thread Error..!", _Ex);
		}
		SubThreadFinished();
	}
}
