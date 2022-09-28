package com.sanalstil.pShell.pCommands;

import java.util.List;


/**
 * Komut parametresi ve o parametreye ait alt parametrelerin listesini içerir. 
 * @author SanalStil AR-GE  -  Hayri Eryürek  
 * @version Tarih : 07.04.2011 - Version 1.0  
 * @see cBaseCommand
 */  

public class cParameterSubParameter
{
	/**
	 * Parametre Adı. örnek "-stop"
	 */
	public String Parameter;
	/**
	 * Parametre alt parametre listesi. (Örnek : list -p page1 page2) Yandaki örnekte page1 ve page2 alt parametrelerdir.
	 * Bu örnek için liste içinde -p parametresinin alt parametresi olan "page1" ve "page2" kelimeleri bulunmaktadır.
	 */
	public List<String> SubParameters;
	
	/**
	 * @param _Parametre tipi String. Ana parametreyi içerir. örnek : "-p"
	 * @param _SubParameter String tipi bir liste içerir. Ana parametrenin Alt parametrelerini içerir. örnek : {"page1", "page2"}
	 */
	public cParameterSubParameter(String _Parameter, List<String> _SubParameter)
	{
		Parameter = _Parameter;
		SubParameters = _SubParameter;
	}
}
