import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class sendMailAuthFogWire_V1
{

	private static final String SMTP_HOST_NAME = "smtp.gmail.com"; 
	private static final String SMTP_AUTH_USER = "fogwire@gmail.com";
	
	//This password will be encrypted so it will look like gibberish.
	//private static final String SMTP_AUTH_PWD  = "#$%^&*()))(*&";
	
	//We will have a Decryption class that will take SMTP_AUTH_PWD Decrypt it
	//and bring it back here as - SMTP_AUTH_PWD_tmp - we will use this as the Authenticator 
	//to gmail and then null it out right after..
	private static String SMTP_AUTH_PWD_tmp;
	//so this will really be: just a return String SMTP_AUTH_PWD_tmp - containing the Decrypted password
	
	private static final String emailMsgTxt      = "Testing...main body of text";
	private static final String emailSubjectTxt  = "Testing java email class";
	private static final String emailFromAddress = "fogwire@gmail.com";

	// Add List of Email address to who email needs to be sent to
	// this has to go from the GUI to here - 
	private static final String[] emailList = {"the_5dz@googlegroups.com"};

	public static void main(String args[]) throws Exception
	{
		sendMailAuthFogWire_V1 authProg = new sendMailAuthFogWire_V1();
		// sendMailAuth smtpMailSender = new sendMailAuth();
		authProg.postMail( emailList, emailSubjectTxt, emailMsgTxt, emailFromAddress);
		
		SMTP_AUTH_PWD_tmp = ""; //will Null out the password String;
		System.out.println("After: " + SMTP_AUTH_PWD_tmp); //should show empty string
		
		System.out.println("Sucessfully Sent mail to All Users");
	}

	public void postMail( String recipients[ ], String subject,
			String message , String from) throws MessagingException
			{
		boolean debug = false;

		//Set the host smtp address to gmail
		Properties props = new Properties();
		props.put("mail.smtp.starttls.enable","true");
		props.put("mail.smtp.port","587");
		props.put("mail.smtp.host", SMTP_HOST_NAME);
		props.put("mail.smtp.auth", "true");

		Authenticator auth = new SMTPAuthenticator();
		System.out.println("Before: " + SMTP_AUTH_PWD_tmp);
		Session session = Session.getDefaultInstance(props, auth);

		session.setDebug(debug);
		
		// create a message
		Message msg = new MimeMessage(session);

		// set the from and to address
		InternetAddress addressFrom = new InternetAddress(from);
		msg.setFrom(addressFrom);

		InternetAddress[] addressTo = new InternetAddress[recipients.length];
		for (int i = 0; i < recipients.length; i++)
		{
			addressTo[i] = new InternetAddress(recipients[i]);
		}
		msg.setRecipients(Message.RecipientType.TO, addressTo);

		// Setting the Subject and Content Type
		msg.setSubject(subject);
		msg.setContent(message, "text/plain");
		Transport.send(msg);
			}

	// SimpleAuthenticator is used to authenticate
	private class SMTPAuthenticator extends javax.mail.Authenticator
	{
		public PasswordAuthentication getPasswordAuthentication()
		{
			String username = SMTP_AUTH_USER;
			String password = SMTP_AUTH_PWD_tmp;
			return new PasswordAuthentication(username, password);
		}
	}
}



