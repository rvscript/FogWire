/**
* FogWire
* Personal Encryption Software
* (c)5dz Productions 2009
* @version 1.0
* @authors Ricardo Viera, Dmitry Sharlot, Joseph Everett, Anthony Sinatra
*/

//****************************************************************************************
/*	FogWire email class
* 
*  Code to send email through the FogWire gmail account
* 
*/
//****************************************************************************************

import java.io.File;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeMessage.RecipientType;

public class FogMail {
	
    	//set Default gmail user information
	private static final String SMTP_HOST_NAME = "smtp.gmail.com"; 
	private static final String SMTP_AUTH_USER = "fogwire@gmail.com";
	private static final String SMTP_AUTH_PWD  = "FiveDudes7";
	
	public void sendMail(){
		try {   
		    
		    FogWire1121.fogWireSetDetails("Sending Mail.....",FogWire1121.encDetailArea); 
		    
			//This sets the right properties for gmail message sending
			Properties props = System.getProperties();            
			props.put("mail.smtp.starttls.enable", "true");        
			props.put("mail.smtp.host", SMTP_HOST_NAME);  
			// set gmail authorization on
			props.put("mail.smtp.auth", "true");   
			// set gmail smtp port   
			props.put("mail.smtp.port", "587"); 
			
			//Authenticates correct user and password with gmail
			Authenticator auth = new Authenticator() {
				@Override                
				protected PasswordAuthentication getPasswordAuthentication() {                    
					return new PasswordAuthentication(SMTP_AUTH_USER, SMTP_AUTH_PWD); 
				}           
			};    
			
			//Creates a new session by calling the Properties and the Authentication
			Session session = Session.getDefaultInstance(props, auth); 
			
			
			MimeMessage msg = new MimeMessage(session);   
			//From: Address
			msg.setFrom(new InternetAddress("SMTP_AUTH_USER"));  
			//Subject line
			msg.setSubject("You have a new message from FogWire!");  
			//To: Address
			msg.setRecipient(RecipientType.TO, new InternetAddress(FogWire1121.toField.getText())); 
			//Attaches a file 
			MimeBodyPart attachMent = new MimeBodyPart();     
			
			//Points to where we grab the file from - specifies the path
			FileDataSource dataSource = new FileDataSource(new File("C:\\FogWire2\\Locked_Docs\\locked.txt"));
			
			attachMent.setDataHandler(new DataHandler(dataSource));        
			
			//Sets the name for the file that will be attached
			attachMent.setFileName("locked.txt");  
			
			attachMent.setDisposition(MimeBodyPart.ATTACHMENT);        
			Multipart multipart = new MimeMultipart();              
			multipart.addBodyPart(attachMent);           
			msg.setContent(multipart);           
			Transport.send(msg);  
			
			//Lets you know if the file has been sent successfully
			System.out.println("Sucessfully Sent Mail");
			
		} catch (AddressException ex) {            
			Logger.getLogger(FogMail.class.getName()).log(Level.SEVERE, null, ex);  
			System.out.println("Error - email not sent");
			FogWire1121.fogWireSetDetails("Error - email not sent",FogWire1121.encDetailArea); 
		} catch (MessagingException ex) {            
			Logger.getLogger(FogMail.class.getName()).log(Level.SEVERE, null, ex);  
			System.out.println("Error - email not sent");
			FogWire1121.fogWireSetDetails("Error - email not sent",FogWire1121.encDetailArea); 
		}    
	}
}


