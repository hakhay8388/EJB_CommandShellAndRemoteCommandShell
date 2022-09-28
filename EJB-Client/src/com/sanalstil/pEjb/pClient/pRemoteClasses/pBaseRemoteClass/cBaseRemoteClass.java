package com.sanalstil.pEjb.pClient.pRemoteClasses.pBaseRemoteClass;

import java.util.Properties;
import javax.naming.Context;
import javax.naming.InitialContext;

import com.sanalstil.pLogManager.cLogManager;
import com.sanalstil.pLogManager.cLogger;


public abstract class cBaseRemoteClass<T>
{
	static InitialContext EJBContext = null;
	public static cLogger EJBClientLogger = null;
	public static cLogManager LoggerManager = null;
	
	public static void Initialize(String _UserName, String _Password, cLogManager _LoggerManager,String _LoggerPath)
	{
		LoggerManager = _LoggerManager;
		EJBClientLogger = LoggerManager.GetLogger("EJB-Client", _LoggerPath);
		try
		{	
			EJBClientLogger.FileInfo("Creating EJB Context...");
			Properties __Properties = new Properties();
		    __Properties.setProperty(Context.SECURITY_PRINCIPAL, _UserName);
		    __Properties.setProperty(Context.SECURITY_CREDENTIALS, _Password);
		    __Properties.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.security.jndi.JndiLoginInitialContextFactory");
			EJBContext = new InitialContext(__Properties);
			EJBClientLogger.FileInfo("Creating EJB Context... -> Done");
		}
		catch(Exception _Ex)
		{
			EJBClientLogger.ConsolError("Creating EJB Context... -> Error");
			EJBClientLogger.FileError("Creating EJB Context... -> Error", _Ex);
		}
	}
	
	public InitialContext GetContext()
	{
		/*if (EJBContext == null)
		{
			Properties __EjbSetting = new Properties();
			
		    try 
		    {
		    	__EjbSetting.load(new FileInputStream("EjbSetting.properties"));
		    } 
		    catch (IOException ex) 
		    {
		    	ex.printStackTrace();
		    	return null;
		    }

			
		    Properties __Properties = new Properties();
		    String __UserName = __EjbSetting.getProperty("username");
		    String __Password = __EjbSetting.getProperty("password");
		      
		    __Properties.setProperty(Context.SECURITY_PRINCIPAL, __UserName);
		    __Properties.setProperty(Context.SECURITY_CREDENTIALS, __Password);
		    __Properties.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.security.jndi.JndiLoginInitialContextFactory");

			try 
			{
				EJBContext = new InitialContext(__Properties);
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				return null;
			}
		}*/
		return EJBContext;
	}
	
	public abstract T GetRemoteInstance();
}
