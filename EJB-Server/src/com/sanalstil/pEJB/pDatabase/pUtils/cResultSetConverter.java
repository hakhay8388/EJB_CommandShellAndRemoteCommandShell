package com.sanalstil.pEJB.pDatabase.pUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import oracle.sql.CLOB;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;

public class cResultSetConverter{
	@SuppressWarnings("unchecked")
	public static ArrayList ResultSet_To_ArrayList(ResultSet t_rs){
		ArrayList <HashMap>result = new ArrayList();
		
		ResultSetMetaData rsmd;
		try {
			rsmd = t_rs.getMetaData();

	        Integer noColumns = rsmd.getColumnCount();
	        Integer i;
	        while (t_rs.next()) {
	        	HashMap tempHash = new HashMap();
	        	//System.out.print("rs.next() :"+rs.getString(1));
	            for (i = 1; i <= noColumns; i++) {
	                //System.out.print(rsmd.getColumnName(i) +":"+rs.getObject(rsmd.getColumnName(i)) + ", ");
	                tempHash.put(rsmd.getColumnName(i), t_rs.getObject(rsmd.getColumnName(i)));
	            }
	            result.add(tempHash);
	        }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return result;
	} 
	@SuppressWarnings("unchecked")
	public static ArrayList ResultSet_To_ArrayListWithoutOne(ResultSet t_rs){
		ArrayList <HashMap>result = new ArrayList();
		
		ResultSetMetaData rsmd;
		try {
			rsmd = t_rs.getMetaData();

	        Integer noColumns = rsmd.getColumnCount();
	        Integer i;
	        while (t_rs.next()) {
	        	HashMap tempHash = new HashMap();
	        	//System.out.print("rs.next() :"+rs.getString(1));
	            for (i = 1; i <= noColumns; i++) {
	             
	            	//System.out.println(rsmd.getColumnName(i) );
	            	if(t_rs.getObject(rsmd.getColumnName(i))!=null && t_rs.getObject(rsmd.getColumnName(i)).toString().compareTo("1")==0
	            			&&(rsmd.getColumnName(i).toString().compareTo("F.1")==0 || rsmd.getColumnName(i).toString().compareTo("F.2")==0 || 
	            					rsmd.getColumnName(i).toString().compareTo("UNDER")==0 || rsmd.getColumnName(i).toString().compareTo("OVER")==0
	            					||rsmd.getColumnName(i).toString().compareTo("S.1")==0 || rsmd.getColumnName(i).toString().compareTo("S.2")==0 
	            					|| rsmd.getColumnName(i).toString().compareTo("F.X")==0 || rsmd.getColumnName(i).toString().compareTo("S.X")==0
	            					|| rsmd.getColumnName(i).toString().compareTo("DC.1X")==0 || rsmd.getColumnName(i).toString().compareTo("DC.12")==0
	            					|| rsmd.getColumnName(i).toString().compareTo("DC.X2")==0))
	            		 tempHash.put(rsmd.getColumnName(i), "-");
	            	else tempHash.put(rsmd.getColumnName(i), t_rs.getObject(rsmd.getColumnName(i)));
	            }
	            result.add(tempHash);
	        }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return result;
	} 

	public static JSONArray ResultSet_To_JSONArray  (ResultSet t_rs){

		JSONArray result =new JSONArray();

		ResultSetMetaData rsmd;
		try {
			rsmd = t_rs.getMetaData();

	        Integer noColumns = rsmd.getColumnCount();
	        Integer i;
	        while (t_rs.next()) {
	        	JSONObject temObj =new JSONObject();
	        	
	        	//System.out.print("rs.next() :"+rs.getString(1));
	            for (i = 1; i <= noColumns; i++) {
	            	switch(rsmd.getColumnType(i)){
            		case 2:
            			if(rsmd.getScale(i)>0){
            				temObj.put(rsmd.getColumnName(i), t_rs.getDouble(rsmd.getColumnName(i)));
            			}
            			else{
            				if(rsmd.getPrecision(i)>14){
            					temObj.put(rsmd.getColumnName(i), t_rs.getLong(rsmd.getColumnName(i)));
            				}
            				else{
            					temObj.put(rsmd.getColumnName(i), t_rs.getInt(rsmd.getColumnName(i)));
            				}
            			}
            			break;
            			default:
            				temObj.put(rsmd.getColumnName(i), t_rs.getObject(rsmd.getColumnName(i)));
            			break;
	            	}
	            }
	            result.add(temObj);
	        }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return result;
	}
	public static BasicDBList ResultSet_To_BasicDBList(ResultSet t_rs){
		
		BasicDBList result =new BasicDBList();

		ResultSetMetaData rsmd;
		try {
			rsmd = t_rs.getMetaData();

	        Integer noColumns = rsmd.getColumnCount();
	        Integer i;
	        while (t_rs.next()) {
	        	BasicDBObject temObj = new BasicDBObject();
	        	
	        	//System.out.print("rs.next() :"+rs.getString(1));
	            for (i = 1; i <= noColumns; i++) {
	            	switch(rsmd.getColumnType(i)){
	            		case 2:
	            			if(rsmd.getScale(i)>0){
	            				temObj.put(rsmd.getColumnName(i), t_rs.getDouble(rsmd.getColumnName(i)));
	            			}
	            			else{
	            				if(rsmd.getPrecision(i)>14){
	            					temObj.put(rsmd.getColumnName(i), t_rs.getLong(rsmd.getColumnName(i)));
	            				}
	            				else{
	            					temObj.put(rsmd.getColumnName(i), t_rs.getInt(rsmd.getColumnName(i)));
	            				}
	            			}
	            		break;
	            		default:
	            			temObj.put(rsmd.getColumnName(i), t_rs.getObject(rsmd.getColumnName(i)));
	            		break;
	            	}
	            }
	            result.add(temObj);
	        }
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return result;
	}
	public static JSONArray ResultSet_To_JSONArrayOne (ResultSet t_rs){

		JSONArray result =new JSONArray();
		try {
	        while (t_rs.next()) {
	        	result.add(t_rs.getObject(1));
	        }
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return result;
	}
	public static JSONArray ResultSet_To_JSONArrayJSONArray(ResultSet t_rs){

		JSONArray result =new JSONArray();

		ResultSetMetaData rsmd;
		try {
			rsmd = t_rs.getMetaData();

	        Integer noColumns = rsmd.getColumnCount();
	        Integer i;
	        while (t_rs.next()) {
	        	JSONArray tArray =new JSONArray();
	        	
	        	//System.out.print("rs.next() :"+rs.getString(1));
	            for (i = 1; i <= noColumns; i++) {
	                //System.out.print(rsmd.getColumnName(i) +":"+t_rs.getObject(rsmd.getColumnName(i)) + ", ");
	            	tArray.add(t_rs.getObject(rsmd.getColumnName(i)));
	            }
	            result.add(tArray);
	        }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return result;
	}
	public static JSONArray ResultSet_To_JSONArray2  (ResultSet t_rs){

		JSONArray result =new JSONArray();

		ResultSetMetaData rsmd;
		try {
			rsmd = t_rs.getMetaData();

	        Integer noColumns = rsmd.getColumnCount();
	        Integer i;
	        int count=0;
	        while (t_rs.next()) {
	        	count++;
	        	if(count>20)break;
	        	JSONObject temObj =new JSONObject();
	        	
	        	//System.out.print("rs.next() :"+rs.getString(1));
	            for (i = 1; i <= noColumns; i++) {
	                //System.out.print(rsmd.getColumnName(i) +":"+t_rs.getObject(rsmd.getColumnName(i)) + ", ");
	            	temObj.put(rsmd.getColumnName(i), t_rs.getObject(rsmd.getColumnName(i)));
	            	
	            }
	            result.add(temObj);
	        }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return result;
	}
	public static JSONArray ResultSet_To_JSONArrayReverse  (ResultSet t_rs){

		JSONArray result =new JSONArray();

		ResultSetMetaData rsmd;
		try {
			rsmd = t_rs.getMetaData();

	        Integer noColumns = rsmd.getColumnCount();
	        Integer i;
	        while (t_rs.next()) {
	        	JSONObject temObj =new JSONObject();
	        	
	        	//System.out.print("rs.next() :"+rs.getString(1));
	            for (i = 1; i <= noColumns; i++) {
	                //System.out.print(rsmd.getColumnName(i) +":"+rs.getObject(rsmd.getColumnName(i)) + ", ");
	            	temObj.put(rsmd.getColumnName(i), t_rs.getObject(rsmd.getColumnName(i)));
	            	
	            }
	            result.add(0, temObj);
	        }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return result;
	} 
	public static JSONArray ResultSet_To_JSONArrayClob  (ResultSet t_rs){

		JSONArray result =new JSONArray();

		ResultSetMetaData rsmd;
		try {
			rsmd = t_rs.getMetaData();

	        Integer noColumns = rsmd.getColumnCount();
	        Integer i;
	        while (t_rs.next()) {
	        	JSONObject temObj =new JSONObject();
	        	
	        	//System.out.print("rs.next() :"+rs.getString(1));
	            for (i = 1; i <= noColumns; i++) {
	               
	            	
	            	//System.out.println(rsmd.getColumnName(i) +":"+t_rs.getObject(rsmd.getColumnName(i)) + ", ");
	        
	            	if(t_rs.getObject(rsmd.getColumnName(i)) != null && t_rs.getObject(rsmd.getColumnName(i)).getClass().toString().equals("class oracle.sql.CLOB")){
	            		CLOB tClob = (CLOB) t_rs.getClob(rsmd.getColumnName(i));
	            		
	            		StringBuffer tStringBuffer = new StringBuffer();
	            		 Reader is = tClob.getCharacterStream();  
	            		  BufferedReader br = new BufferedReader (is); 
	            		  try{
	            			  String s = br.readLine();  
	            			  while(s != null) {
	            				  tStringBuffer.append(s);
	            				  s = br.readLine();  
	            			  }
	            		} catch (IOException e) {
	            				e.printStackTrace();
	            		}
	            		temObj.put(rsmd.getColumnName(i), tStringBuffer.toString());

	            	}
	            	else temObj.put(rsmd.getColumnName(i), t_rs.getObject(rsmd.getColumnName(i)));
	            	
	            }
	            result.add(temObj);
	        }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return result;
	}
	public static JSONArray ResultSet_To_JSONArrayClob2JSONObject  (ResultSet t_rs){

		JSONArray result =new JSONArray();

		ResultSetMetaData rsmd;
		try {
			rsmd = t_rs.getMetaData();

	        Integer noColumns = rsmd.getColumnCount();
	        Integer i;
	        while (t_rs.next()) {
	        	JSONObject temObj =new JSONObject();
	            for (i = 1; i <= noColumns; i++) {
	            	if(t_rs.getObject(rsmd.getColumnName(i)) != null && t_rs.getObject(rsmd.getColumnName(i)).getClass().toString().equals("class oracle.sql.CLOB")){
	            		CLOB tClob = (CLOB) t_rs.getClob(rsmd.getColumnName(i));
	            		
	            		StringBuffer tStringBuffer = new StringBuffer();
	            		 Reader is = tClob.getCharacterStream();  
	            		  BufferedReader br = new BufferedReader (is); 
	            		  try{
	            			  String s = br.readLine();  
	            			  while(s != null) {
	            				  tStringBuffer.append(s);
	            				  s = br.readLine();  
	            			  }
	            		} catch (IOException e) {
	            				e.printStackTrace();
	            		}
		            	JSONObject tData =  (JSONObject) JSONValue.parse(tStringBuffer.toString());
		            	temObj.put(rsmd.getColumnName(i), tData);
	            	}
	            	else temObj.put(rsmd.getColumnName(i), t_rs.getObject(rsmd.getColumnName(i)));
	            	
	            }
	            result.add(temObj);
	        }
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return result;
	}
	public static JSONObject ResultSet_To_JSONObject  (ResultSet t_rs){

		JSONObject result =new JSONObject();

		ResultSetMetaData rsmd;
		try {
			rsmd = t_rs.getMetaData();

	        Integer noColumns = rsmd.getColumnCount();
	        Integer i;
	        if(t_rs.next()){
	            for (i = 1; i <= noColumns; i++) {
	                //System.out.print(rsmd.getColumnName(i) +":"+rs.getObject(rsmd.getColumnName(i)) + ", ");
	            	result.put(rsmd.getColumnName(i), t_rs.getObject(rsmd.getColumnName(i)));
	            }
	        }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return result;
	}
	public static JSONObject ResultSet_To_JSONObjectClob  (ResultSet t_rs){

		JSONObject result =new JSONObject();

		ResultSetMetaData rsmd;
		try {
			rsmd = t_rs.getMetaData();

	        Integer noColumns = rsmd.getColumnCount();
	        Integer i;
	        if(t_rs.next()){
	            for (i = 1; i <= noColumns; i++) {

	           
	            	if(t_rs.getObject(rsmd.getColumnName(i)).getClass().toString().equals("class oracle.sql.CLOB")){
	            		CLOB tClob = (CLOB) t_rs.getClob(rsmd.getColumnName(i));
	            		
	            		StringBuffer tStringBuffer = new StringBuffer();
	            		 Reader is = tClob.getCharacterStream();  
	            		  BufferedReader br = new BufferedReader (is); 
	            		  try{
	            			  String s = br.readLine();  
	            			  while(s != null) {
	            				  tStringBuffer.append(s);
	            				  s = br.readLine();  
	            			  }
	            		} catch (IOException e) {
	            				e.printStackTrace();
	            		}
	            		result.put(rsmd.getColumnName(i), tStringBuffer.toString());

	            	}
	            	else result.put(rsmd.getColumnName(i), t_rs.getObject(rsmd.getColumnName(i)));
	            }
	        }
		} catch (SQLException e) {

			e.printStackTrace();
		}
        return result;
	}
	
	public static JSONObject ResultSet_To_JSONObjectArray (ResultSet t_rs, String t_property){

		JSONObject result =new JSONObject();

		ResultSetMetaData rsmd;
		try {
			rsmd = t_rs.getMetaData();

	        Integer noColumns = rsmd.getColumnCount();
	        Integer i;
	        while (t_rs.next()) {
	        	JSONObject temObj =new JSONObject();
	        	
	        	//System.out.print("rs.next() :"+rs.getString(1));
	            for (i = 1; i <= noColumns; i++) {
	                //System.out.print(rsmd.getColumnName(i) +":"+rs.getObject(rsmd.getColumnName(i)) + ", ");
	            	temObj.put(rsmd.getColumnName(i), t_rs.getObject(rsmd.getColumnName(i)));
	            	
	            }
	            temObj.remove(t_property);
	            result.put(t_rs.getObject(t_property).toString(),temObj);
	        }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return result;
	} 
}
