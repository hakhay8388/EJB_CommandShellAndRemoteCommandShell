package com.sanalstil.pEJB.pDatabaseBeans.pIddaa.pCoupons;

import com.sanalstil.pEJB.pDatabase.pResultObjects.cDataJsonResult;

public interface ICoupons 
{
	public cDataJsonResult GetTop(int _Amount, String _Type);
}
