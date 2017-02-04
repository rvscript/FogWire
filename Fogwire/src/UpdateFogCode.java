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

public class UpdateFogCode extends JFrame {

	private JPanel contentPane;
	JLabel lblFogCode;
	private JTextField fogCode;


	//	/**
	//	 * Launch the application.
	//	 */
	//	public static void main(String[] args) {
	//		EventQueue.invokeLater(new Runnable() {
	public static void run() {
		UpdateFogCode ufc = new UpdateFogCode ();
		try {
			//verifyRegistration()  <- checks to see if this software is registered.  if yes, load fogWire, no load login
			//FogWireLogin frame = new FogWireLogin();
			ufc.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}


	}
	/**
	 * this frame loads only when the product has not been registered
	 * a method checks to see if a key has been created...if not, then the login screen appears, otherwise, load fogwire
	 */
	public UpdateFogCode() {
		setTitle("Enter a New Fog Code");
		setIconImage(Toolkit.getDefaultToolkit().getImage(UpdateFogCode.class.getResource("/images/5dlg.gif")));
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Toolkit tk = getToolkit();
		Dimension size = tk.getScreenSize();
		setSize(306,213);
		setLocation(size.width/2 - getWidth()/2, size.height/2 - getHeight()/2);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		{
			// Register button 

			JButton btnRegister = new JButton("Submit");

			// Register button action
			btnRegister.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {

					if (FogWireLogin.checkFogCode(fogCode.getText()))
					{

						FWProfile.setFogcode(Integer.parseInt(fogCode.getText()));
						FogWire1121.fogWireSetDetails("FogCode updating...done",FogWire1121.encDetailArea);
						dispose();
					}

					else
					{					
						fogCode.setText("");
					}


				}
			});
			btnRegister.setForeground(UIManager.getColor("EditorPane.background"));
			btnRegister.setFont(new Font("Tahoma", Font.BOLD, 11));
			btnRegister.setBackground(SystemColor.activeCaption);
			btnRegister.setBounds(104, 129, 114, 30);
			contentPane.add(btnRegister);
		}
		{
			//label saying register your fogwire
			lblFogCode = new JLabel(" Enter a New Fog Code");
			lblFogCode.setForeground(SystemColor.activeCaption);
			lblFogCode.setFont(new Font("Verdana", Font.BOLD, 15));
			lblFogCode.setBounds(58, 22, 206, 30);
			contentPane.add(lblFogCode);
		}
		{
			JLabel lblPasscode = new JLabel("Fog Code:");
			lblPasscode.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblPasscode.setBounds(24, 78, 114, 14);
			contentPane.add(lblPasscode);
		}
		{
			fogCode = new JTextField();
			fogCode.setToolTipText("Please Enter your Fog Code (consisting of 5 numbers)");
			fogCode.setBounds(86, 75, 133, 20);
			contentPane.add(fogCode);
			fogCode.setColumns(10);
		}
		dispose();
	}


}



