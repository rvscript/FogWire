/**
* FogWire
* Personal Encryption Software
* (c)5dz Productions 2009
* @version 1.0
* @authors Ricardo Viera, Dmitry Sharlot, Joseph Everett, Anthony Sinatra
*/

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.TextField;

import javax.imageio.stream.FileImageOutputStream;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.UIManager;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.ImageIcon;


import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Writer;
import java.sql.SQLException;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.regex.PatternSyntaxException;
import java.awt.Toolkit;

public class FogWireLogin extends JFrame {

	private JPanel contentPane;
	private static JTextField firstName;
	private static JTextField lastName;
	private static JTextField emailAddress;
	JLabel lblRegisterStatus;
	private static JTextField fogCode;


//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
			public static void run() {
				FogWireLogin frame = new FogWireLogin();
				try {
					//verifyRegistration()  <- checks to see if this software is registered.  if yes, load fogWire, no load login
					//FogWireLogin frame = new FogWireLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				
			}
	/**
	 * this frame loads only when the product has not been registered
	 * a method checks to see if a key has been created...if not, then the login screen appears, otherwise, load fogwire
	 */
	public FogWireLogin() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(FogWireLogin.class.getResource("/images/5dlg.gif")));
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Toolkit tk = getToolkit();
		Dimension size = tk.getScreenSize();
		setSize(477,350);
		setLocation(size.width/2 - getWidth()/2, size.height/2 - getHeight()/2);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		{
			// Register button 

			JButton btnRegister = new JButton("REGISTER");

			// Register button action
			btnRegister.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {

					
					if(checkLogin(firstName.getText(),lastName.getText(),emailAddress.getText(), fogCode.getText())== true){

						try {
							createDirectoryIfNeeded();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					
					
									
					lblRegisterStatus.setText("Contacting Registration Server...");
					// connect to database..load details
					// change label to registering...
					// create keys
					}
					else
						System.out.println("checkLogin is false...");
					
				}
			});
			btnRegister.setForeground(UIManager.getColor("EditorPane.background"));
			btnRegister.setFont(new Font("Tahoma", Font.BOLD, 11));
			btnRegister.setBackground(SystemColor.activeCaption);
			btnRegister.setBounds(175, 254, 133, 46);
			contentPane.add(btnRegister);
		}
		{	// first name text field parameters
			// calls from this field would be firstName.getText();
			firstName = new JTextField();
			firstName.setBounds(116, 113, 229, 20);
			contentPane.add(firstName);
			firstName.setColumns(10);
			firstName.setText("");
		}
		{	// last name text field parameters
			lastName = new JTextField();
			lastName.setBounds(116, 145, 229, 20);
			contentPane.add(lastName);
			lastName.setColumns(10);
			lastName.setText("");
		}
		{	// email address text field parameters
			emailAddress = new JTextField();
			emailAddress.setBounds(116, 177, 229, 20);
			contentPane.add(emailAddress);
			emailAddress.setColumns(10);
			emailAddress.setText("");
		}
		{	// first name label parameters
			JLabel lblFirstName = new JLabel("First Name: ");
			lblFirstName.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblFirstName.setBounds(24, 116, 71, 14);
			contentPane.add(lblFirstName);
		}
		{	// last name label parameters
			JLabel lblLastName = new JLabel("Last Name:");
			lblLastName.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblLastName.setBounds(24, 148, 90, 14);
			contentPane.add(lblLastName);
		}
		{	// email label parameters
			JLabel lblEmailAddress = new JLabel("Email Address:");
			lblEmailAddress.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblEmailAddress.setBounds(24, 180, 100, 14);
			contentPane.add(lblEmailAddress);
		}
		{
			//label saying register your fogwire
			lblRegisterStatus = new JLabel(" Register Your FogWire");
			lblRegisterStatus.setForeground(SystemColor.activeCaption);
			lblRegisterStatus.setFont(new Font("Verdana", Font.BOLD, 15));
			lblRegisterStatus.setBounds(116, 72, 268, 30);
			contentPane.add(lblRegisterStatus);
		}
		{
			// label for the logo
			JLabel label = new JLabel("");
			label.setIcon(new ImageIcon(FogWireLogin.class.getResource("/images/FogWire225x61.gif")));
			label.setBounds(116, 11, 225, 60);
			contentPane.add(label);
		}
		{
			JLabel lblFogCode = new JLabel("Fog Code:");
			lblFogCode.setToolTipText("Choose a 5 digit Fog Code");
			lblFogCode.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblFogCode.setBounds(24, 211, 100, 14);
			contentPane.add(lblFogCode);
		}
		{
			fogCode = new JTextField();
			fogCode.setText("");
			fogCode.setColumns(10);
			fogCode.setBounds(116, 208, 229, 20);
			contentPane.add(fogCode);
		}
		
	}
	
	
	// getters for Login fields, used by checkLogin
	
	protected static JTextField getFirstName() {
		return firstName;
	}

	protected static JTextField getLastName() {
		return lastName;
	}

	protected static JTextField getEmailAddress() {
		return emailAddress;
	}

	protected static JTextField getFogCode() {
		return fogCode;
	}

	/**
	 * checks to see if this computer has been registered with FogWire
	 * the user provides credentials and those credentials are checked against the database
	 */
	protected boolean checkLogin(String fname, String lname, String email, String fogcode)
	//JTextField lname, JTextField email
	{
		boolean check = true;

		while(fname.equalsIgnoreCase("") || lname.equalsIgnoreCase("")|| email.equalsIgnoreCase("")|| fogcode.equalsIgnoreCase(""))
		{
			try {
				FogWire1121.fogWireMessage("All Fields are Required to Register!");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			check = false;
			return check;
		}

		if(checkEmail(email)== false){
			check = false;
		}
		
		if (checkFogCode(fogcode)==false)
		{
			check = false;
		}
		
		return check;

	}

	static boolean checkEmail(String emailRegex)
	{
	    //test email string
	    Pattern p = Pattern.compile("^(['_a-z0-9-]+)(\\.['_a-z0-9-]+)*@([a-z0-9-]+)(\\.[a-z0-9-]+)*(\\.[a-z]{2,5})$");
	    Matcher m = p.matcher(emailRegex.toLowerCase());
	    boolean b = m.matches();

	    if(!(b))
	    {
		try {
		    FogWire1121.fogWireMessage("Email Address is invalid!");
		} catch (FileNotFoundException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		}
	    }
	    return b;
	}

	
	//Method to check if the Recipient is registered with FogWire
	static boolean checkRecipient(String emailRegex)
	{
	    boolean recAuth = false;
	    FogWireDB db = new FogWireDB();
	    db.loadDriver();
	    db.connectToDatabase();
	    try {
		recAuth = FogWireDB.AuthenticateRecipient();
	    } catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }

	    if(!(recAuth))
	    {
		try {
		    FogWire1121.fogWireMessage("Sorry! "+ FogWire1121.toField.getText()+ " is not registered");
		    FogWire1121.toField.setText("");
		} catch (FileNotFoundException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		}
	    }

	    return recAuth;
	}
	
	static boolean checkFogCode(String fcRegex)
	{
		//test email string
	        Pattern p = Pattern.compile("[1-5]{1}[0-9]{4}");
		Matcher m = p.matcher(fcRegex);
		boolean b = m.matches();

		if(!(b))
		{
		try {
			FogWire1121.fogWireMessage("FogCode is invalid!  Enter a 5 digit FogCode between 10,000 - 59,999.");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}

		return b;
	}
	
	
	private void createDirectoryIfNeeded() throws IOException
	{
		
	  File FogDir = new File("C:\\FogWire2");
	  File LockDir = new File("C:\\FogWire2\\Locked_Docs");
	  File UnlockDir = new File("C:\\FogWire2\\Unlocked_Docs");
	  File foggy = new File("C:\\FogWire2\\foggy.txt");
	  File unlockedFile = new File ("C:\\FogWire2\\Unlocked_Docs\\unlocked.txt");
	  File lockedFile = new File ("C:\\FogWire2\\Locked_Docs\\locked.txt");
	  
	  
	  System.out.println("creating directory: " + FogDir);
	  FogDir.mkdir();
	  LockDir.mkdir();
	  UnlockDir.mkdir();
	  foggy.createNewFile();
	  unlockedFile.createNewFile();
	  lockedFile.createNewFile();
	  
	  
	  // create profile document and required files
	  FileWriter fr = new FileWriter("C:\\FogWire2\\foggy.txt");
	  FileWriter ef = new FileWriter("C:\\FogWire2\\Unlocked_Docs\\unlocked.txt");
	  FileWriter df = new FileWriter("C:\\FogWire2\\Locked_Docs\\locked.txt");
	  
	  RSAKeys rsa = new RSAKeys();
	
	 // String tempKey = Integer.toString(rsa.getPrivateKey());
	//  int pubKey = rsa.getPublicKeyE();
	 // String fi = Integer.toString(rsa.getFee());
	  
	  
	  String separator = ",";
	  fr.write(getFirstName().getText());
	  fr.write(separator);
	  fr.write(getLastName().getText());
	  fr.write(separator);
	  fr.write(getEmailAddress().getText());
	  fr.write(separator);
	  //fr.write((int) rsa.getPrivateKey());
	  fr.write(Integer.toString(rsa.getPrivateKey()));
	  fr.write(separator);
	  //fr.write((int) rsa.getFee());
	  fr.write(Integer.toString(rsa.getFee()));
	  fr.write(separator);
	  fr.write(getFogCode().getText());
	  
	  fr.close();
	  	  
	  ef.write("");
	  ef.close();
	  
	  df.write("");
	  df.close();
	  /*	 	  
	  FogWireDB db = new FogWireDB();
	  db.loadDriver();
	  db.connectToDatabase();
	  try {
		db.AddUser();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  
	*/  
	  //setVisible(false);
	  dispose();
	  FogWire1121.run();	
	}
}



