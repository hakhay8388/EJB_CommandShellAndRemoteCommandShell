package com.sanalstil.pEJB.pDatabaseBeans.pIddaa.pBetList.pAthletism;

import javax.annotation.PostConstruct;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import org.jboss.ejb3.annotation.SecurityDomain;

import com.sanalstil.pEJB.pBaseBean.cBaseBean;
import com.sanalstil.pEJB.pDatabase.pResultObjects.cDataJsonResult;

@Stateless(mappedName="com/sanalstil/pEJB/pDatabaseBeans/pIddaa/pBetList/pAthletism/cAthletism")
@SecurityDomain("other")
@Remote(IAthletism.class)
public class cAthletismProxy extends cBaseBean implements IAthletism
{

	private IAthletism m_Athletism= null;
	
	@PostConstruct
	public void Constructor()
	{
		m_Athletism = new cAthletismMain();
	}

	@RolesAllowed({"Admin"})
	public cDataJsonResult GetMatchProgram() 
	{
		return m_Athletism.GetMatchProgram();
	}

	@RolesAllowed({"Admin"})
	public cDataJsonResult GetLiveMatchProgram() 
	{
		return m_Athletism.GetLiveMatchProgram();
	}
}

