package com.sanalstil.pEJB.pDatabaseBeans.pIddaa.pCoupons;

import javax.annotation.PostConstruct;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import org.jboss.ejb3.annotation.SecurityDomain;
import com.sanalstil.pEJB.pBaseBean.cBaseBean;
import com.sanalstil.pEJB.pDatabase.pResultObjects.cDataJsonResult;


@Stateless(mappedName="com/sanalstil/pEJB/pDatabaseBeans/pIddaa/pCoupons/cCoupons")
@SecurityDomain("other")
@Remote(ICoupons.class)
public class cCouponsProxy extends cBaseBean implements ICoupons
{
	private ICoupons m_Coupons= null;
	
	@PostConstruct
	public void Constructor()
	{
		m_Coupons = new cCouponsMain();
	}

	@RolesAllowed({"Admin"})
	public cDataJsonResult GetTop(int _Amount, String _Type) 
	{
		return m_Coupons.GetTop(_Amount, _Type);
	}
	
}
