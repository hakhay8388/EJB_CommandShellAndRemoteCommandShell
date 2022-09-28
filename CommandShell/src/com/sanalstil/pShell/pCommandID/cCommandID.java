package com.sanalstil.pShell.pCommandID;


/**
 * Komut oluşturma aşamasında cBaseCommand objesinin içine verilmek üzere tasarlanmıştır.
 * Bu obje komutun konsoldan çağrılma adını ve ID'sini içerir  
 * @author SanalStil AR-GE  -  Hayri Eryürek  
 * @version Tarih : 07.04.2011 - Version 1.0  
 */  

public class cCommandID
{
	/**
	 * Komut ID'si.
	 */
	public int CommandID;
	/**
	 * Komut Adı.
	 */
	public String CommandName;

	/**
	 * Komutun konsoldan çağrılma adını ve ID'si girmeliziniz. 
	 * @param _CommandID tipi Integer. Komut ID'si.
	 * @param _CommandName tipi String. Komut Adı.
	 */	
	public cCommandID(int _CommandID, String _CommandName)
	{
		CommandID = _CommandID;
		CommandName = _CommandName;
	}
}
