package com.sanalstil.pEJB.pDatabaseBeans.pIddaa;

import javax.annotation.PostConstruct;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import org.jboss.ejb3.annotation.SecurityDomain;
import com.sanalstil.pEJB.pBaseBean.cBaseBean;


@Stateless(mappedName="com/sanalstil/pEJB/pDatabaseBeans/pIddaa/cIddaa")
@SecurityDomain("other")
@Remote(IIddaa.class)
public class cIddaaProxy extends cBaseBean implements IIddaa
{
	private IIddaa m_Iddaa= null;
	
	@PostConstruct
	public void Constructor()
	{
		m_Iddaa = new cIddaaMain();
	}

}
