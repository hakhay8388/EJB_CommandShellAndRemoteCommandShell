package com.sanalstil.pEJB.pDatabase.pResultObjects;

import java.io.Serializable;
import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

@SuppressWarnings("unchecked")
public class cDataJsonResult extends JSONObject implements Serializable 
{
	protected String errorCode="0000";
	protected boolean success=true;
	protected String errorInfo="";
	
	public JSONArray data = new JSONArray();
	public String dataString ="";
	public JSONArray dataArray = new JSONArray();
	public JSONObject dataObject = new JSONObject();
	public HashMap dataHashMap = new HashMap();
	
	public String getErrorCode() 
	{
		return errorCode;
	}
	public void setErrorCode(String errorCode) 
	{
		this.errorCode = errorCode;
	}
	public boolean isSuccess() 
	{
		return success;
	}
	public void setSuccess(boolean success) 
	{
		this.success = success;
	}
	public String getErrorInfo() 
	{
		return errorInfo;
	}
	public void setErrorInfo(String errorInfo) 
	{
		this.errorInfo = errorInfo;
	}
}
