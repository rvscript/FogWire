/**
* FogWire
* Personal Encryption Software
* (c)5dz Productions 2009
* @version 1.0
* @authors Ricardo Viera, Dmitry Sharlot, Joseph Everett, Anthony Sinatra
*/

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class FWProfile {
	
	private String fname;
	private String lname;
	private String email;
	private int prvkey;
	private int prvkey2;
	private int fogcode;
	private static  File foggy = new File("C:\\FogWire2\\foggy.txt");
	
	/**
	 * 
	 */
	public FWProfile() {
	    super();
	}

	public String getFname() 
	{
		try {
			fname = loadFoggy(foggy, 0);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return fname;
	}

	public String getLname() 
	{	
		try {
		lname = loadFoggy(foggy, 1);
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		return lname;
	}

	public String getEmail() 
	{
		try {
			email = loadFoggy(foggy, 2);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return email;
	}

	public int getPrvkey() 
	{
		try {
			prvkey = Integer.parseInt(loadFoggy(foggy,3));

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		return prvkey;
	}

	public int getPrvkey2() 
	{
		try {
			prvkey2 = Integer.parseInt(loadFoggy(foggy,4).trim());

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		return prvkey2;
	}
	public int getFogcode() 
	{
		try {
		fogcode = Integer.parseInt(loadFoggy(foggy,5).trim());
		System.out.println("Testing this fogcode : " +fogcode);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fogcode;
	}
	
	
	public static void setFogcode(int fogcode) {
		
		try {
			updateFoggy(foggy,fogcode,5);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void setKey(int prvkey) {
		try {
			updateFoggy(foggy,prvkey,3);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	public static void setKey2(int prvkey) {
		try {
			updateFoggy(foggy,prvkey,4);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

		
	private synchronized static String loadFoggy(File foggy, int position) throws IOException, FileNotFoundException
	{
		BufferedReader br = new BufferedReader (new FileReader(foggy));
		String thisLine;
		String[] profDoc = new String[6];
		
		while ((thisLine = br.readLine()) != null)
				{
					profDoc = thisLine.trim().split(",");
					
				}
		return profDoc[position];
		
	}
	
	private synchronized static void updateFoggy(File foggy,int newValue, int position)throws IOException, FileNotFoundException
	{
		// turn new value into a string
		String Value = Integer.toString(newValue);
		String thisLine;
		BufferedReader br = new BufferedReader (new FileReader(foggy));
		String[] profDoc = new String[6];
		
		while ((thisLine = br.readLine()) != null)
		{
			profDoc = thisLine.trim().split(",");
			
		}
		//update the array
		profDoc[position] = Value;
		
		//write out a new file
		FileWriter fr = new FileWriter(foggy);
		String separator = ",";
		  
		for (int i = 0; i < profDoc.length; i++) 
		{
			fr.write(profDoc[i] + separator);
			
		}
		fr.close();		
				
	}
	
	
}
