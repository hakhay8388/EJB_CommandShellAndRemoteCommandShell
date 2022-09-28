package com.sanalstil.pEJB.pDatabase.pResultObjects;

import java.io.Serializable;
import java.util.ArrayList;
@SuppressWarnings("unchecked")
public class cDataResult implements Serializable 
{
	protected String errorCode="0000";
	protected boolean success=true;
	protected String errorInfo="";
	
	public ArrayList data = new ArrayList();

	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode){
		this.errorCode = errorCode;
	}
	public boolean isSuccess(){
		return success;
	}
	public void setSuccess(boolean success){
		this.success = success;
	}
	public String getErrorInfo(){
		return errorInfo;
	}
	public void setErrorInfo(String errorInfo){
		this.errorInfo = errorInfo;
	}
	
}
