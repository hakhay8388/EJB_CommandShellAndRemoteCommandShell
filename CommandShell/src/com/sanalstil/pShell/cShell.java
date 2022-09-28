package com.sanalstil.pShell;

import java.util.LinkedList;
import java.util.List;

import com.sanalstil.pShell.pCommands.cBaseCommand;



/**  
 * Uygulamalarınıza, Konsol arayüzü olmak amacıyla tasarlanmıştır. 
 * @author SanalStil AR-GE  -  Hayri Eryürek  
 * @version Tarih : 07.04.2011 - Version 1.0  
 * @see Shell
 */  
public class cShell 
{
	/**
	 * Shell'de kullanılabilecek komut listesini içerir.
	 **/
	public List<cBaseCommand> CommandList = null;
	private cShellInterpreter ShellInterpreter = null;	
	
	public cShell()
	{
		CommandList = new LinkedList<cBaseCommand>();
	}
	
  /**  
   * cBaseCommand Objesinden türetilen her objenin instance'ı, liste içine eklenir. Bu komut sayesinde parametre olarak verilen komut ismi, 
   * liste içindeki komutların oluşturulma aşamasında verilen isimleri ile karşılaştırılarak aranır. 
   * Eğer bu isimde bir komutu bulursa türediği cBaseCommand obje cinsinden obje olarak döner.
   * Bulamazsa sonuç null olarak döner.    
   * @param _CommandName parametresi, String tipinde bir parametredir. 
   * Bu metod, _CommandName ile komutlar oluşturulurken cCommandID içinde verilen CommandName değişkenlerinde karşılaştırma yapar.
   * 
   * @return Bu metodun sonucu olarak cBaseCommand obje cinsinden bir komut objesi, Komut bulunamazsa null sonuç döner. 
   */  
	public cBaseCommand GetCommandByName(String _CommandName)
	{
		for (int i = 0; i < CommandList.size(); i++)
		{
			cBaseCommand __Command = CommandList.get(i);
			if (__Command.CommandID.CommandName.equals(_CommandName))
			{
				return __Command;
			}
		}
		return null;
	}
	
	/**
	   * cBaseCommand Objesinden türetilen her obje, liste içine eklenir. Bu komut sayesinde parametre olarak verilen komut ID si, 
	   * liste içindeki komutların oluşturulma aşamasında verilen ID leri ile karşılaştırılarak aranır. 
	   * Eğer bu ID de bir komutu bulursa türediği cBaseCommand obje cinsinden obje olarak döndürür.
	   * Bulamazsa sonuç null olarak döner.    
	   * @param _CommandID parametresi, int tipinde bir parametredir. 
	   * Bu metod, _CommandID ile komutlar oluşturulurken cCommandID içinde verilen CommandID değişkenlerinde karşılaştırma yapar.
	   * 
	   * @return Bu metodun sonucu olarak cBaseCommand obje cinsinden bir komut objesi döner. Eğer komut bulunamazsa null sonuç döner. 
	   */  
	public cBaseCommand GetCommandByID(int _CommandID)
	{
		for (int i = 0; i < CommandList.size(); i++)
		{
			cBaseCommand __Command = CommandList.get(i);
			if (__Command.CommandID.CommandID == _CommandID)
			{
				return __Command;
			}
		}
		return null;
	}
	
	/**
	   * Shell oluşturulduktan sonra komutlar ve parametreler eklenir.
	   * Daha sonra Shell'i başlatmak için StartShell() komutu çağrılır.    
	 **/
	public void StartShell()
	{
		if (ShellInterpreter == null)
		{
			ShellInterpreter = new cShellInterpreter(this);
		}
		ShellInterpreter.start();
	}
	
	/**
	   * Konsola birşeyler yazdırabilmek için PrintConsole() komutu kullanılır. 
   		@param _Object parametresi Object tipinden her objeyi alablir.
	 **/	
	public void PrintConsole(Object _Object)
	{
		System.out.println(_Object);
		System.out.print("> ");
	}
	
	/**
	   * Shell'i kapatmak için StopShell()komutu çağrılır.
	 **/	
	public void ExitShell()
	{
		if (ShellInterpreter != null)
		{
			ShellInterpreter.ExitShell();
		}
	} 
	
	public void PrepareCommandToExit()
	{
		for (int i = 0; i < CommandList.size(); i++ )
		{
			CommandList.get(i).Exiting = true;
		}
	}
}
