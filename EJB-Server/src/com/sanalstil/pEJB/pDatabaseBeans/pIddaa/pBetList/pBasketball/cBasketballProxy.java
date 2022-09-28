package com.sanalstil.pEJB.pDatabaseBeans.pIddaa.pBetList.pBasketball;

import javax.annotation.PostConstruct;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import org.jboss.ejb3.annotation.SecurityDomain;

import com.sanalstil.pEJB.pBaseBean.cBaseBean;
import com.sanalstil.pEJB.pDatabase.pResultObjects.cDataJsonResult;

@Stateless(mappedName="com/sanalstil/pEJB/pDatabaseBeans/pIddaa/pBetList/pBasketball/cBasketball")
@SecurityDomain("other")
@Remote(IBasketball.class)
public class cBasketballProxy extends cBaseBean implements IBasketball
{

	private IBasketball m_Basketball= null;
	
	@PostConstruct
	public void Constructor()
	{
		m_Basketball = new cBasketballMain();
	}

	@RolesAllowed({"Admin"})
	public cDataJsonResult GetMatchProgram() 
	{
		return m_Basketball.GetMatchProgram();
	}

	@RolesAllowed({"Admin"})
	public cDataJsonResult GetLiveMatchProgram() 
	{
		return m_Basketball.GetLiveMatchProgram();
	}
}

