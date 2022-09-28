package com.sanalstil.pEJB.pDatabase.pResultObjects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
@SuppressWarnings("unchecked")
public class cDataProvider  implements Serializable
{
	protected String errorCode="0000";
	protected boolean success=true;
	protected String errorInfo="";
	
	protected HashMap result=new HashMap();
	protected ArrayList data = null;

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
	public HashMap getResult()
	{
		result.put("success", success);
		result.put("data", success);
		return result;
	}
	public void setResult(HashMap result) 
	{
		this.result = result;
	}
	
}
