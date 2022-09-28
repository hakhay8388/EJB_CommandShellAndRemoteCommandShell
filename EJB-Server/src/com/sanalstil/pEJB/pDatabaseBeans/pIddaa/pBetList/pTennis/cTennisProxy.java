package com.sanalstil.pEJB.pDatabaseBeans.pIddaa.pBetList.pTennis;

import javax.annotation.PostConstruct;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import org.jboss.ejb3.annotation.SecurityDomain;

import com.sanalstil.pEJB.pBaseBean.cBaseBean;
import com.sanalstil.pEJB.pDatabase.pResultObjects.cDataJsonResult;

@Stateless(mappedName="com/sanalstil/pEJB/pDatabaseBeans/pIddaa/pBetList/pTennis/cTennis")
@SecurityDomain("other")
@Remote(ITennis.class)
public class cTennisProxy extends cBaseBean implements ITennis
{

	private ITennis m_Tennis= null;
	
	@PostConstruct
	public void Constructor()
	{
		m_Tennis = new cTennisMain();
	}

	@RolesAllowed({"Admin"})
	public cDataJsonResult GetMatchProgram() 
	{
		return m_Tennis.GetMatchProgram();
	}

	@RolesAllowed({"Admin"})
	public cDataJsonResult GetLiveMatchProgram() 
	{
		return m_Tennis.GetLiveMatchProgram();
	}
}

