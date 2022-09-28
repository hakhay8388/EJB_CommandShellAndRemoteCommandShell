package com.sanalstil.pShell.pCommands;

import java.util.LinkedList;
import java.util.List;

import com.sanalstil.pLogManager.cLogger;
import com.sanalstil.pShell.cShell;
import com.sanalstil.pShell.pCommandID.cCommandID;
import com.sanalstil.pShell.pCommands.pGeneralReciver.cCommandHelpParamater;
import com.sanalstil.pShell.pCommands.pGeneralReciver.cCommandStopAllParameter;
import com.sanalstil.pShell.pCommands.pGeneralReciver.cCommandStopParameter;


/**  
 * Shell komut ara yüzüne, yeni komut eklemek istediğinizde bu objeden türetmeniz gerekmektedir.
 * Türettiğiniz bu komut içinde abstract InterpretCommand metodunu ve abstract IsMultiRunnable metodunu override etmeniz gerekmektedir.
 * Türettiğiniz bu komuttan tek bir instance oluşturmalısınız daha fazlasını değil. Oluşturulan komut instance'ı Shell içrisindeki komut listesine kendini ekleyecektir.
 * Artık türetilen bu komut konsol aracılığı ile çağırıldığında  override edilen InterpretCommand metodu çağrılarak türettiğiniz yeni komuta devredilecektir.
 * InterpretCommand metodu List(String) tipinden _CommandParameter parametresi ve List(cParameterSubParameter) tipinden _ParameterList parametresi olmak üzere iki parametre almaktadır.
 * _CommandParameter parametresi "-" ile başlamayan ve hemen komuttan sonra kullanılmış olan alt parametre listesini içerir.
 * _ParameterList parametresi ise cParameterSubParameter tipinden objeler listesi içerir. Bu objelerin her birinin içinde iki değişken vardır.
 * Birisi Parameter adında string tipinde "-" ile başlamış parametrenin kendisidir. 
 * İkincisi ise parametreden hemen sonra kullanılmış "-" başlamayan alt parametre listesini içeren SubParameters adında String tipi içeren listedir.
 * Bu method içinden parametrelere devredilebilir, parametrelerle komutu init edebilir yada direk olarak bu method içinden işleminizi gerçekleştirebilirsiniz.
 * Komutun her çağrıldığında komut için bir Thread açılarak çalıştırılır. Bu sayede konsol meşgul edilmemiş olur. 
 * Fakat bazen komut tamamlanmadan aynı komut tekrar çağrılmasını engelemek istiyebilirsiniz. Bunun içinde, oluşturulan yeni komutta
 * override etmek zorunda olduğunuz IsMultiRunnable fonksiyonu devreye girmektedir. Eğer bu komut çoklu çalışabiliyorsa
 * IsMultiRunnable fonksiyonu true, çalışamıyorsa false döndürmelidir.
 * 
 * @author SanalStil AR-GE  -  Hayri Eryürek  
 * @version Tarih : 07.04.2011 - Version 1.0  
 * @see cShell, cCommandID, ICommandParameter, cParameterSubParameter
 */  

public abstract class cBaseCommand implements Runnable
{

	public boolean Exiting = false;
	/**
	 * Komutun bağlı olduğu Shell
	 */
	public cShell OwnerShell = null;
	/**
	 * Komut Adını ve ID'sini içeren cCommandID objesi
	 */
	public cCommandID CommandID = null;
	/**
	 * Kendini komutun alıcısı olarak ekleyen ICommandParameter arayüzünden türemiş objelerin listesi  
	 */	
	public List<ICommandParameter> CommandParameter = new LinkedList<ICommandParameter>();
	/**
	 * Komutun son kullanıldığındaki parametre ve bu parametrelerinin alt parametre listelerini tutan cParameterSubParameter objesinin listesi 
	 */	
	public List<cParameterSubParameter> ParameterList = null;
	/**
	 * Komutun son kullanıldığındaki alt parametre listesi 
	 */	
	public List<String> CommandSubParameter = null;
	private int m_IsRunning;
	/**
	 * Komutun her kullanıldığında açılan Thread'lerin listesi
 	 * Bütün Thread'ler sonlanana dek liste temizlenmez. Yani gerçekte son bulmuş Thread'ler varsabile tüm Thread'ler
 	 * son bulunca liste temizlenecektir.
	 */		
	public List<Thread> OwnerThreadList = null;
	
	
	public List<cBaseCommandSubThread> CommandsSubThreads = null;
	/**
	 * @param _OwnerShell Parametresi komutun bağlanacağı Shell'i içermelidir.
	 * @param _CommandID Parametresi tipi cCommandID obje tipindendir. cCommandID objesi içinde CommandName değişkeninde komutun çağrılacağı ad, 
	 * CommandID içinde ise bu komuta ait ID numarası yer almalıdır. 
	 */	
	public cBaseCommand(cShell _OwnerShell, cCommandID _CommandID)
	{
		OwnerThreadList = new LinkedList<Thread>();
		OwnerShell = _OwnerShell;
		CommandID = _CommandID;
		ConnectGeneralReciver();
		OwnerShell.CommandList.add(this);
		m_IsRunning = 0;
	}
	
	private void ConnectGeneralReciver()
	{
		cCommandHelpParamater __HelpCommandReciver = new cCommandHelpParamater();
		this.Connect(__HelpCommandReciver);
		cCommandStopParameter __CommandStopParameter = new cCommandStopParameter();
		this.Connect(__CommandStopParameter);
		cCommandStopAllParameter __CommandStopAllParameter = new cCommandStopAllParameter();
		this.Connect(__CommandStopAllParameter);

	}

	/**
	 * Komuta ait parametre listesini yardım olarak listeler. 
	 */	
	public void PrintHelp()
	{
		ICommandParameter __Parameter = GetParameterClassByParameter(cCommandHelpParamater.HelpParameter);
		if (__Parameter != null)
		{
			__Parameter.ReciveCommand(this, new LinkedList<String>());
		}
	}

	/**
	 * Komuta bağlanmış olan parametre listesi içinden parametre kelimesini verip (örnek : "-s" ) parametre objesini
	 * almak için kullanılır.
	 * @param _Parameter tipi String olup parametre kelimesi içerir
	 * @return Donen obje ICommandParameter tipindendir. Aranan kelimeye ait bir obje listede bulunuyorsa döner. Bulunmuyorsa null döner.  
	 */
	public ICommandParameter GetParameterClassByParameter(String _Parameter)
	{
		for (int i = 0;i < CommandParameter.size(); i++)
		{
			if (CommandParameter.get(i).GetCallParameter().equals(_Parameter))
			{
				return CommandParameter.get(i);
			}
		}
		return null;
	}
	
	/**
	 * Kendini ilgili komutun alıcı olarak eklemek isteyen ICommandParameter arayüzünden türemiş
	 * objelerin komuta bağlanması için kullanılır.
	 * @param _CommandReciver tipi ICommandParameter olup komut alıcısını içerir.
	 * @return Donen deger boolean tipinden olacaktır. Eğer aynı parametre adına sahip başka bir parametre
	 * objesi yoksa bağlantı sağlanacak ve True değeri dönecektir. Eğer başka bir parametre objeside bu komuta
	 * aynı isimle bağlnmışsa aynı isimle ikinci bir parametre daha bağlanamayacağı için False değeri dönecektir. 
	 */
	public boolean Connect(ICommandParameter _CommandReciver)
	{
		if (_CommandReciver.GetCallParameter() != "")
		{
			ICommandParameter __Object = GetParameterClassByParameter(_CommandReciver.GetCallParameter());
			if (__Object == null)
			{
				CommandParameter.add(_CommandReciver);
				return true;
			}
			else
			{
				return false;
			}	
		}
		else
		{
			CommandParameter.add(_CommandReciver);
			return true;
		}
	}

	/**
	 * Kendini ilgili komutun alıcı olarak eklemek isteyen ICommandParameter arayüzünden türemiş
	 * objelerin komuta bağlanması için kullanılır.
	 * @param _CommandReciver tipi ICommandParameter olup komut alıcısını içerir.
	 * @return Donen değer boolean tipinden olacaktır. Bağlantısı kesilmek istenen obje hali hazırda bu komuta bağlıysa
	 * bağlantısı 
	 */
	public boolean Disconnect(ICommandParameter _CommandReciver)
	{
		int __Count = CommandParameter.size();
		CommandParameter.remove(_CommandReciver);
		if (__Count > CommandParameter.size())
		{
			return true;
		}
		else
		{
			return false;
		}	
	}
	
	/**
	 * Konsole aracılığı ile, yeni türettiğiniz komut çağrıldığında, override edilen InterpretCommand metodu sizin komutunuzdan çağrılacaktır. 
	 */
	public abstract void InterpretCommand(List<String> _CommandParameter, List<cParameterSubParameter> _ParameterList);
	
	/**
	 * Türettiğiniz komutun aynı anda birden fazla şekilde çalışabilmesini istiyorsanız override ettiğiniz IsMultiRunnable
	 * metodu True istemiyorsanız False döndürmelidir.
	 */
	public abstract boolean IsMultiRunnable();

	 public void run() 
	 {
		 m_IsRunning++;
		 InterpretCommand(CommandSubParameter, ParameterList);
		 m_IsRunning--;
		 if (m_IsRunning <= 0)
		 {
			 OwnerThreadList.clear();
			 m_IsRunning = 0;
		 }
     } 
	 
	/**
	 * Komutunuz konsola birşey basmak istediğinde kullanılır.
	 */	 
	 public void PrintConsole(Object _Object)
	 {
		 OwnerShell.PrintConsole(_Object);
	 }
	 
	/**
	 * Komutunuzun o anda çalıp çalışmadığını kontrol etmek amacıyla kullanılır.
	 * Aktif bir komut thread'i varsa True yoksa False dönecektir.
	 */	 
	 public boolean IsRunning()
	 {
		 return m_IsRunning > 0;
	 }
	 
	/**
	 * Komutunuzun o anda çalır durumdaki Threadlerini kapatmak için kullanılır. Alt threadlerin sonlanması beklenir.
	*/	 
	 @SuppressWarnings("deprecation")
	 public void Stop()
	 {
		 for (int i = 0; i < OwnerThreadList.size();i++)
		 {
			 OwnerThreadList.get(i).stop();
		 }
		 m_IsRunning = 0;
		 OwnerThreadList.clear();
	 }
	 
	/**
	 * Komutunuzun o anda çalır durumdaki tüm Threadlerini kapatmak için kullanılır. Alt threadlerde kapatılmaya zorlanır.
	*/	 
	 @SuppressWarnings("deprecation")
	 public void StopWithSubThreads()
	 {
		 for (int i = 0; i < OwnerThreadList.size();i++)
		 {
			 OwnerThreadList.get(i).stop();
		 }
		 
		 if (CommandsSubThreads != null)
		 {
			 for (int i = 0; i < CommandsSubThreads.size();i++)
			 {
				 CommandsSubThreads.get(i).Stop();
			 }
		 }
		 m_IsRunning = 0;
		 OwnerThreadList.clear();
	 }
	 
	 /**
	  *@return String tipinde komut yardım yazısı döner. 
	  */
	 public boolean InActionSubThreads()
	 {
		 if (CommandsSubThreads != null)
		 {
			 return CommandsSubThreads.size() > 0;
		 }
		 return false;
	 }
	 
	 public abstract String GetCommandInfoString();
	 public abstract cLogger GetCommandLogger();
}
