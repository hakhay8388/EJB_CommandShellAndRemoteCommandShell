package pDatabase.pMongoDatabase;

import java.util.LinkedList;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import com.mongodb.DB;
import com.mongodb.DBAddress;
import com.mongodb.Mongo;
import com.mongodb.MongoOptions;



public class cMongoDatabase 
{
	public List<DB> MongoDbs;
	public List<Mongo> MongoConnections;

	
	public cMongoDatabase(JSONObject _Settings)
	{
		InitializeMongoDB(_Settings);
	}
	
	public void InitializeMongoDB(JSONObject _Settings)
	{
		MongoConnections = new LinkedList<Mongo>();
		MongoDbs = new LinkedList<DB>();
		
	    JSONObject tObjDataMongo =  (JSONObject) _Settings.get("dataMongo");
	    JSONObject tConfig = (JSONObject) tObjDataMongo.get("ebahis");
	    JSONArray __Hosts =   (JSONArray) tConfig.get("hosts");
	
	    MongoOptions mOptions = new MongoOptions();
	    mOptions.autoConnectRetry = new Boolean(tConfig.get("autoConnectRetry").toString());
	    mOptions.connectionsPerHost = Integer.valueOf(tConfig.get("connectionsPerHost").toString());
	    mOptions.connectTimeout =Integer.valueOf(tConfig.get("connectTimeout").toString());
	    mOptions.socketTimeout = Integer.valueOf(tConfig.get("socketTimeout").toString());
	
	    for (int i = 0; i < __Hosts.size(); i++)
	    {
		    DBAddress tAddress = null;
			try 
			{
				tAddress = new DBAddress(__Hosts.get(i).toString(),tConfig.get("db").toString());
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
			MongoConnections.add(new Mongo( tAddress ,mOptions));
			MongoDbs.add(MongoConnections.get(MongoConnections.size() - 1).getDB(tConfig.get("db").toString()));
	    }
	}
	
	
}
