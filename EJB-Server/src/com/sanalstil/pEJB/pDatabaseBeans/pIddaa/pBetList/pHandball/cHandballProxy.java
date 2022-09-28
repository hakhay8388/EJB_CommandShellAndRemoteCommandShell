package com.sanalstil.pEJB.pDatabaseBeans.pIddaa.pBetList.pHandball;

import javax.annotation.PostConstruct;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import org.jboss.ejb3.annotation.SecurityDomain;

import com.sanalstil.pEJB.pBaseBean.cBaseBean;
import com.sanalstil.pEJB.pDatabase.pResultObjects.cDataJsonResult;

@Stateless(mappedName="com/sanalstil/pEJB/pDatabaseBeans/pIddaa/pBetList/pHandball/cHandball")
@SecurityDomain("other")
@Remote(IHandball.class)
public class cHandballProxy extends cBaseBean implements IHandball
{

	private IHandball m_Handball= null;
	
	@PostConstruct
	public void Constructor()
	{
		m_Handball = new cHandballMain();
	}

	@RolesAllowed({"Admin"})
	public cDataJsonResult GetMatchProgram() 
	{
		return m_Handball.GetMatchProgram();
	}

	@RolesAllowed({"Admin"})
	public cDataJsonResult GetLiveMatchProgram() 
	{
		return m_Handball.GetLiveMatchProgram();
	}
}

