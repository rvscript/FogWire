
/**
* FogWire
* Personal Encryption Software
* (c)5dz Productions 2009
* @version 1.0
* @authors Ricardo Viera, Dmitry Sharlot, Joseph Everett, Anthony Sinatra
*/

//****************************************************************************************
/*	FogWire Check Database Entries class
* 
* This class connects to the FogWire database and brings back a list of registered users
* 
*/
//****************************************************************************************

import java.sql.Statement;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CheckDatabaseEntries {


	public static void main(String args[]) throws SQLException, IOException, ClassNotFoundException {

		// Load MySQL driver
		Class.forName("com.mysql.jdbc.Driver");

		// Connect to the FogWire database
		Connection conn = DriverManager
		.getConnection("jdbc:mysql://localhost/fogwire?"
				+ "user=root&password=Java131-1");

		Statement stmt = conn.createStatement();
		
		ResultSet rs = stmt.executeQuery("select * from  fogmail");

		//Brings back results for last name, first name and email address
		while (rs.next()) {
			System.out.println("NAME: (" + rs.getString("l_name")+ ", "+ rs.getString("f_name")+ ") EMAIL: " +rs.getString("e_mail"));
		}
	}
}