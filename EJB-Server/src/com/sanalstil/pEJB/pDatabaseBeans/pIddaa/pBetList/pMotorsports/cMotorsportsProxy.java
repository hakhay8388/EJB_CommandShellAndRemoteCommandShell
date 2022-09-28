package com.sanalstil.pEJB.pDatabaseBeans.pIddaa.pBetList.pMotorsports;

import javax.annotation.PostConstruct;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import org.jboss.ejb3.annotation.SecurityDomain;

import com.sanalstil.pEJB.pBaseBean.cBaseBean;
import com.sanalstil.pEJB.pDatabase.pResultObjects.cDataJsonResult;

@Stateless(mappedName="com/sanalstil/pEJB/pDatabaseBeans/pIddaa/pBetList/pMotorsports/cMotorsports")
@SecurityDomain("other")
@Remote(IMotorsports.class)
public class cMotorsportsProxy extends cBaseBean implements IMotorsports
{

	private IMotorsports m_Motorsports= null;
	
	@PostConstruct
	public void Constructor()
	{
		m_Motorsports = new cMotorsportsMain();
	}

	@RolesAllowed({"Admin"})
	public cDataJsonResult GetMatchProgram() 
	{
		return m_Motorsports.GetMatchProgram();
	}

	@RolesAllowed({"Admin"})
	public cDataJsonResult GetLiveMatchProgram() 
	{
		return m_Motorsports.GetLiveMatchProgram();
	}
}

