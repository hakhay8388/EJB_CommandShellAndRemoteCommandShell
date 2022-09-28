package com.sanalstil.pEJB.pBaseBean;

import com.sanalstil.pEJB.pStartup.cStartup;


public class cBaseBean 
{
	public static boolean Loaded = false;
	
	public cBaseBean()
	{
		if (!Loaded)
		{
			Loaded = true;
			cStartup.Initialize();			
		}
	}
}
