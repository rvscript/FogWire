/**
* FogWire
* Personal Encryption Software
* (c)5dz Productions 2009
* @version 1.0
* @authors Ricardo Viera, Dmitry Sharlot, Joseph Everett, Anthony Sinatra
*/

import java.io.File;
import java.sql.SQLException;


public class FogWireDriver {

	/**
	 * @param args
	 */


	public static void main(String[] args) {

		//try to get this file
		File myFile = new File("C:\\FogWire2\\foggy.txt");
		try{
			if(myFile.exists()){
				System.out.println("Found FogWire application...loading");
				/*
				FogWireDB db = new FogWireDB();
				  db.loadDriver();
				  db.connectToDatabase();
				  try {
					db.Authenticate();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				*/
							
				//start Main FogWire
				FogWire1121.run();
				FogWire1121.fogWireSetDetails("Connecting to DB...done",FogWire1121.encDetailArea); 
			}
			else{
				System.out.println("FogWire application not found...initializing login");
				//start Login registration
				FogWireLogin.run();
			}
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
