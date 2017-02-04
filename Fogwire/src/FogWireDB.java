/**
* FogWire
* Personal Encryption Software
* (c)5dz Productions 2009
* @version 1.0
* @authors Ricardo Viera, Dmitry Sharlot, Joseph Everett, Anthony Sinatra
*/

//****************************************************************************************
/*	FogWire Database class
 * 
 *  Code for:
 *  Connecting to the database
 *  Adding new User
 *  Authenticating application
 *  Authenticating existing User
 *  
*/
//****************************************************************************************


import java.io.FileNotFoundException;
import java.io.ObjectInputStream.GetField;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;

public class FogWireDB {

	private static Connection conn;
	private static Statement stmt;
	static FogWire1121 message = new FogWire1121();

	// This method loads MySQL driver (will reference "mysql-connector-java-5.1.8-bin.jar" file)
	public static void loadDriver(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			System.out.println("Driver problem!");
		}
	}

	// This method will connect to the FogWire database
	public static void connectToDatabase(){	
		try {
			conn = DriverManager.//getConnection("jdbc:mysql://localhost/rvscript_fogwire?"+"user=rvscript_guest&password=rv-sde101-101");
			getConnection("jdbc:mysql://localhost/fogwire?"+"user=root&password=Java131-1");
			//Will let you know if you have successfully connected to the database
			System.out.println("Connected to the database!");
			
		} catch (SQLException e1) {
			System.out.println("Connection to the database problem!");
		}
		return;
	}

	//This method will interact with the database and Authenticate a user
	public static void Authenticate() throws SQLException {
		//Sets up parameters within the database
		conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
		conn.setAutoCommit(false);
		
		//This creates the statement object
		stmt = conn.createStatement();

		/*
		 * Tests user Authenticity by comparing locally stored information
		 * about the user with the information stored on the database
		 * 
		 */
		try {	
		    	FWProfile user = new FWProfile();
		    	String userFname = user.getFname();
		    	String userLname = user.getLname();
		    	String userMail = user.getEmail();

			
			//Fetches all of the result of the query to be analyzed
			ResultSet rs = stmt.executeQuery("select * from fogmail");

			//Flag to let the application know if the user has been authenticated
			boolean found = false;
			
			while (rs.next()) {
				/*
				 * If all of the values checked match the database then - Authenticate user
				 */
				if ((rs.getString("F_NAME").equalsIgnoreCase(userFname))& 
					(rs.getString("L_NAME").equalsIgnoreCase(userLname))& 
					(rs.getString("E_MAIL").equalsIgnoreCase(userMail))){
					
					
					/*
					 * RSA Key authentication test
					 */

					//Grabs RSA public key 'e' from the database
					int e = rs.getInt("PUB_KEY");
					System.out.println("Key from DB is e: " + e);

					//Grabs RSA private keys 'd' and 'fee' from the local files
					int d = user.getPrvkey();
					int fi = user.getPrvkey2();
					System.out.println("Local file d is: " + d);
					System.out.println("Local file fi is: " + fi);
					
					int testOurKey = 0;
					System.out.println("e: " + e);
					System.out.println("d: " + d);
					System.out.println("fi: " + fi);
					
					long ee = 0; long dd = 0 ; long f = fi;
					ee = e; dd = (long) d; 
					
					//test equation for RSA key
					testOurKey = (int) (ee*dd%f);

					System.out.println("Test our key: " + testOurKey);
					if (testOurKey == 1){
					    found  = true; 
					    System.out.println("Authenticated user: " + rs.getString("L_NAME")+ ", " + rs.getString("F_NAME"));
					}
					
					
					
				}
			}
			
			//If a value does not match there is an authentication error
			if(found == false){
				System.out.println("Authentication failed - please check user information and try again");
			        try {
				    message.fogWireMessage("Authentication Failed! Invalid User");
				    System.exit(1);
				    
				} catch (FileNotFoundException e) {
				    // TODO Auto-generated catch block
				    e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			// If a problem occurs at any point of the authentication process the database is rolled back
			System.out.println("Authentication failed - please check user information and try again");
			try {
			    message.fogWireMessage("1- Authentication Failed! Invalid User");
			} catch (FileNotFoundException e1) {
			    // TODO Auto-generated catch block
			    e1.printStackTrace();
			}
			 //   System.exit(1);
			//This will roll back database and close connection to the database if there is an Authentication error
			conn.rollback();
			stmt.close();
			conn.close();
			System.out.println("Database rolled back - connection closed!");
			return;
		}

		//If Authentication is successful we close out of the database
		conn.commit();
		stmt.close();
		conn.close();
		System.out.println("Database connection closed!");

	}
	
	
	
	//This method will interact with the database and Authenticate the recipient
	public static boolean AuthenticateRecipient() throws SQLException {
		//Sets up parameters within the database
		conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
		conn.setAutoCommit(false);
		
		//This creates the statement object
		stmt = conn.createStatement();

		/*
		 * Tests Recipients Authenticity by comparing the TO: field to the database
		 * 
		 */
		
		//Flag to let the application know if the recipient has been authenticated
		boolean found = false;
		
		try {	
		    	
		    	String recipentMail = FogWire1121.toField.getText();
		    	System.out.println("What we are putting in: " + recipentMail);
		    	
			//Fetches the result of the query to be analyzed
			ResultSet rs = stmt.executeQuery("select * from fogmail");
			
			while (rs.next()) {
				/*
				 * If the TO: field of the GUI matches an entry in the database then - Authenticate recipient
				 */
				if (((rs.getString("E_MAIL").equalsIgnoreCase(recipentMail)))){
				    found  = true; 
				}
			}
			//If a value does not match there is a recipient authentication error
			if(found == false){
				System.out.println("Authentication failed - please check recipient information and try again");
			}
			
		} catch (SQLException e) {
			// If a problem occurs at any point of the authentication process the database is rolled back
			System.out.println("Authentication failed - please check user information and try again");
			
			//This will roll back database and close connection to the database if there is an Authentication error
			conn.rollback();
			stmt.close();
			conn.close();
			System.out.println("Database rolled back - connection closed!");
			return found;
		}

		//If Authentication is successful we close out of the database
		conn.commit();
		stmt.close();
		conn.close();
		System.out.println("Database connection closed!");
		return found;

	}

	
	//This method will interact with the database and Add a user
	public static void AddUser() throws SQLException {
		//Sets up a few parameters within the database
		conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
		conn.setAutoCommit(false);

		//This creates the statement object
		stmt = conn.createStatement();
		
		/*
		 * We will pass down User information from the GUI to be checked/Authenticated 
		 * with the database
		 * Below is a test sample
		 */
		
		//FogWireLogin login = new FogWireLogin();
		
		String userFname = FogWireLogin.getFirstName().getText();
		String userLname = FogWireLogin.getLastName().getText();
		String userMail = FogWireLogin.getEmailAddress().getText();
		
		//RSAKeys rsa = new RSAKeys();
		int pubKey = RSAKeys.getPublicKeyE();
		
		try {
			
			//This will add the necessary values into the database - creating a new user
			stmt.executeUpdate("insert into fogmail values ('"+userFname+"','"+userLname+"','"+userMail+"','"+pubKey+"')");

			//Fetches all of the result of the query to be analyzed
			ResultSet rs = stmt.executeQuery("select * from fogmail");

			//This will be the value passed back to the GUI saying either the user is added to the database
			//or there was a problem and the insert failed
			boolean found = false;
     
			
			while (rs.next()) {
				/*
				 * If all of the values checked match the database then - Add user
				 */
				if (((rs.getString("E_MAIL").equalsIgnoreCase(userMail)))& 
					(rs.getString("L_NAME").equalsIgnoreCase(userLname))& 
					(rs.getString("E_MAIL").equalsIgnoreCase(userMail))//& 
					//(rs.getString("PUB_KEY").equalsIgnoreCase(pubKey)) 
					){
					System.out.println("Registered user: " + rs.getString("F_NAME") + " " + rs.getString("L_NAME"));
					found = true;
				}
			}
			//If their is a problem with adding the user - we ask the user to register again
			if(found == false)
				System.out.println("User not registered please try again");

		} catch (SQLException e) {
			// If a problem occurs at any point in the addition process the database is rolled back
			System.out.println("The user: " + userFname + " " + userLname + " is already registered");
			
			//Below error traces used for testing only. Will be deleted for the final version.
			//System.out.println("SQL exception caught in your code!!! - rolled back");
			//e.printStackTrace();
			
			//Will roll back database and close connection to the database if there is an addition error
			conn.rollback();
			stmt.close();
			conn.close();
			System.out.println("Database connection closed!");
			return;
		}

		//If Addition is successful we close out of the database
		conn.commit();
		stmt.close();
		conn.close();
		System.out.println("Database connection closed!");

	}
}
