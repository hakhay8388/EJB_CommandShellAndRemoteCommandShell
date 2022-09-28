package com.sanalstil.pEJB.pDatabaseBeans.pIddaa.pBetList.pFootball;

import javax.annotation.PostConstruct;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import org.jboss.ejb3.annotation.SecurityDomain;

import com.sanalstil.pEJB.pBaseBean.cBaseBean;
import com.sanalstil.pEJB.pDatabase.pResultObjects.cDataJsonResult;

@Stateless(mappedName="com/sanalstil/pEJB/pDatabaseBeans/pIddaa/pBetList/pFootball/cFootball")
@SecurityDomain("other")
@Remote(IBasketball.class)
public class cFootballProxy extends cBaseBean implements IBasketball
{

	private IBasketball m_Football= null;
	
	@PostConstruct
	public void Constructor()
	{
		m_Football = new cFootballMain();
	}

	@RolesAllowed({"Admin"})
	public cDataJsonResult GetMatchProgram() 
	{
		return m_Football.GetMatchProgram();
	}

	@RolesAllowed({"Admin"})
	public cDataJsonResult GetLiveMatchProgram() 
	{
		return m_Football.GetLiveMatchProgram();
	}
}

