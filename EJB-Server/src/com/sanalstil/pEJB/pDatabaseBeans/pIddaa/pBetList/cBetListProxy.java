package com.sanalstil.pEJB.pDatabaseBeans.pIddaa.pBetList;

import javax.annotation.PostConstruct;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import org.jboss.ejb3.annotation.SecurityDomain;

import com.sanalstil.pEJB.pBaseBean.cBaseBean;

@Stateless(mappedName="com/sanalstil/pEJB/pDatabaseBeans/pIddaa/pBetList/cBetList")
@SecurityDomain("other")
@Remote(IBetList.class)
public class cBetListProxy extends cBaseBean implements IBetList
{

	private IBetList m_BetList= null;
	
	@PostConstruct
	public void Constructor()
	{
		m_BetList = new cBetListMain();
	}

	@RolesAllowed({"Admin"})
	public cBetListUpdateResult IsUpdated() 
	{
		return m_BetList.IsUpdated();
	}

}

