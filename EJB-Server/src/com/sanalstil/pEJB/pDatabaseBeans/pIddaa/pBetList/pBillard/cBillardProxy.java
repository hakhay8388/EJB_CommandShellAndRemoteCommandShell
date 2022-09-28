package com.sanalstil.pEJB.pDatabaseBeans.pIddaa.pBetList.pBillard;

import javax.annotation.PostConstruct;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import org.jboss.ejb3.annotation.SecurityDomain;

import com.sanalstil.pEJB.pBaseBean.cBaseBean;
import com.sanalstil.pEJB.pDatabase.pResultObjects.cDataJsonResult;

@Stateless(mappedName="com/sanalstil/pEJB/pDatabaseBeans/pIddaa/pBetList/pBillard/cBillard")
@SecurityDomain("other")
@Remote(IBillard.class)
public class cBillardProxy extends cBaseBean implements IBillard
{

	private IBillard m_Billard= null;
	
	@PostConstruct
	public void Constructor()
	{
		m_Billard = new cBillardMain();
	}

	@RolesAllowed({"Admin"})
	public cDataJsonResult GetMatchProgram() 
	{
		return m_Billard.GetMatchProgram();
	}

	@RolesAllowed({"Admin"})
	public cDataJsonResult GetLiveMatchProgram() 
	{
		return m_Billard.GetLiveMatchProgram();
	}
}

