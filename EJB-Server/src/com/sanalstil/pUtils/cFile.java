package com.sanalstil.pUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class cFile {
	public static Boolean Exists(String t_file) {
		java.io.File f = new java.io.File(t_file);
		return f.exists();
	}

	public static String GetExtension(String t_filename) {
		return t_filename.substring(t_filename.lastIndexOf(".") + 1, t_filename
				.length());
	}

	static public String GetContents(String aFile) {
		// ...checks on aFile are elided
		StringBuilder contents = new StringBuilder();

		try {
			// use buffering, reading one line at a time
			// FileReader always assumes default encoding is OK!
			BufferedReader input = new BufferedReader(new FileReader(aFile));
			try {
				String line = null; // not declared within while loop
				/*
				 * readLine is a bit quirky : it returns the content of a line
				 * MINUS the newline. it returns null only for the END of the
				 * stream. it returns an empty String if two newlines appear in
				 * a row.
				 */
				while ((line = input.readLine()) != null) {
					contents.append(line);
					contents.append(System.getProperty("line.separator"));
				}
			} finally {
				input.close();
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		return contents.toString();
	}
	static public void PutContents(String t_filename,String t_string){
		try 
		{
			System.out.println("cFile içinde putContents metoduna bakman gerekiyor....!");
			//org.apache.commons.io.FileUtils.writeStringToFile(new java.io.File(t_filename), t_string, "UTF-8");
		} 
		catch (/*IOException e1*/ Exception e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}
}
