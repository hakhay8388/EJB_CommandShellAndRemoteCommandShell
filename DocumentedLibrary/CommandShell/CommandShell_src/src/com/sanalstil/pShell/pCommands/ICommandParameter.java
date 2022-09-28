package com.sanalstil.pShell.pCommands;

import java.util.List;


/**
 * Komutlara parametre alıcısı olarak eklenmek istenen objelerin komutlara bağlanabilmesi için gliştirilmiş bir arayüzedür.
 * @author SanalStil AR-GE  -  Hayri Eryürek  
 * @version Tarih : 07.04.2011 - Version 1.0  
 * @see cBaseCommand
 */  
public interface ICommandParameter 
{
	/**
	 * 
	 * @param _Command parametresi cBaseCommand obje tipindendir. Parametreye objesine hangi komuttan gelindiğini 
	 * bildirmek amacı ile kullanılır. 
	 * @param _SubParameter parametresi String tipinden Liste içerir. Parametrenin alt parametrelerine erişmek amacı ile kullanılır.
	 */
	boolean ReciveCommand(cBaseCommand _Command, List<String> _SubParameter);
	/**
	 * 
	 * @return Konsoldan parametre yönlendirmesi yapılabilmesi amacı ile kullanılır. örnek : return "-help";
	 */
	String GetCallParameter();
	/**
	 * 
	 * @return Kullanıcıya bu parametrenin amacını yardım istediğinde gösterebilmek amacı ile kullanılır.
	 * örnek "return "Yardım Komutu";
	 */
	String GetHelpString();
}
