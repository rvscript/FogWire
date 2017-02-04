package images;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Test_conn {
	private static Connection conn;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		loadDriver();
		connectToDatabase();
	}
	public static void loadDriver(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			System.out.println("Driver problem!");
		}
	}

	public static void connectToDatabase(){	
		try {
			conn = DriverManager.getConnection("jdbc:mysql://rvscript.com:3306/rvscript_fogwire?"+"user=rvscript_guest&password=rv_sde101-101");
			//getConnection("jdbc:mysql://localhost/fogwire?"+"user=root&password=Java131-1");
			//Will let you know if you have successfully connected to the database
			System.out.println("Connected to the database!");
			
		} catch (SQLException e1) {
			System.out.println("Connection to the database problem!");
		}
		return;
	}

}
