package com.sanalstil.pEJB.pDatabaseBeans.pIddaa.pBetList.pVsanalstilyball;

import javax.annotation.PostConstruct;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import org.jboss.ejb3.annotation.SecurityDomain;

import com.sanalstil.pEJB.pBaseBean.cBaseBean;
import com.sanalstil.pEJB.pDatabase.pResultObjects.cDataJsonResult;

@Stateless(mappedName="com/sanalstil/pEJB/pDatabaseBeans/pIddaa/pBetList/pVsanalstilyball/cVsanalstilyball")
@SecurityDomain("other")
@Remote(IVsanalstilyball.class)
public class cVsanalstilyballProxy extends cBaseBean implements IVsanalstilyball
{

	private IVsanalstilyball m_Vsanalstilyball= null;
	
	@PostConstruct
	public void Constructor()
	{
		m_Vsanalstilyball = new cVsanalstilyballMain();
	}

	@RolesAllowed({"Admin"})
	public cDataJsonResult GetMatchProgram() 
	{
		return m_Vsanalstilyball.GetMatchProgram();
	}

	@RolesAllowed({"Admin"})
	public cDataJsonResult GetLiveMatchProgram() 
	{
		return m_Vsanalstilyball.GetLiveMatchProgram();
	}
}

