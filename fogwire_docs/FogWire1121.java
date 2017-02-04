
//imports
import java.awt.*;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;
import javax.swing.text.html.*;

import java.awt.event.*;
import java.io.*;
import java.net.URL;

/**
 * FogWire
 * Personal Encryption Software
 * (c)5d Productions
 * build number 4
 * 11.21.09
 */
public class FogWire1121 extends JFrame {
	private JPanel mainPanel, decPanel, hlpPanel;
	protected static JTextPane encDetailArea, decDetailArea;
	private JButton btnlock, btnunlock, btnClear, btnLockNSend, btnBrowseForFile, btnBrowseForDec, btnChangeFC;
	private JEditorPane encTextPane, decTextPane, lockHelp, unlockHelp;
	private JScrollPane encScroller, decScroller, encDetScroller, decDetScroller, encHlpScroller, decHlpScroller;
	private JFileChooser decChooser, encChooser;
	public static JTextField toField;
	private JTabbedPane tabbedPane;
	private JLabel lblUnlockedTxt, lblClickClearTo, lblViewDetails, lblEnterTheText, lblTo;
	private JPanel encPanel;
	private boolean wasClicked = false;
	private boolean badEmail = false;
	private File unlockedFile = new File ("C:\\FogWire2\\Unlocked_Docs\\unlocked.txt");
	private File lockedFile = new File ("C:\\FogWire2\\Locked_Docs\\locked.txt");
	//files required for encryption decryption
	private File lockedDocs = new File ("C:\\FogWire2\\Locked_Docs");
	private File unlockedDocs = new File ("C:\\FogWire2\\Unlocked_Docs");

	//	public static void main(String[] args) {
	//		EventQueue.invokeLater(new Runnable() {
	public static void run(){
		try {

			FogWire1121 frame = new FogWire1121();
			frame.setVisible(true);


		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//		});
	//	}
	//****************************************************************************************
	/*	FOG WIRE GUI COMPONENTS INSTANTIATED AND ADDED TO GUI
	 */
	//****************************************************************************************
	public FogWire1121() {

		// create chooser objects in main constructor to speed up operation
		encChooser = new JFileChooser();
		decChooser = new JFileChooser();

		setIconImage(Toolkit.getDefaultToolkit().getImage(FogWire1121.class.getResource("/images/5dlg.gif")));
		setTitle("FogWire: Personal Encrytion Software");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Toolkit tk = getToolkit();
		Dimension size = tk.getScreenSize();
		setSize(900,600);
		setLocation(size.width/2 - getWidth()/2, size.height/2 - getHeight()/2);
		mainPanel = new JPanel();
		mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		mainPanel.setLayout(new BorderLayout(0, 0));
		setContentPane(mainPanel);
		{
			tabbedPane = new JTabbedPane(JTabbedPane.TOP);
			tabbedPane.setBorder(null);
			mainPanel.add(tabbedPane, BorderLayout.CENTER);

			encPanel = new JPanel();
			encPanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			tabbedPane.addTab("LOCK DATA", null, encPanel, null);
			encPanel.setLayout(null);
			{
				encTextPane = new JEditorPane();
				//encTextPane.setEditable(false);
				encTextPane.addKeyListener(new KeyAdapter() {
					@Override
					public void keyPressed(KeyEvent arg0) {
						btnBrowseForFile.setEnabled(false);
						lblClickClearTo.setVisible(true);
						btnBrowseForFile.setBackground(mainPanel.getBackground());

					}



				});
				encTextPane.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
				encTextPane.setText("");
				encScroller = new JScrollPane(encTextPane, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER );
				encScroller.getVerticalScrollBar().setUnitIncrement( 16 );
				encScroller.setBounds(30,128, 722, 249);
				encPanel.add(encScroller);

			}
			{
				btnlock = new JButton("LOCK ONLY");
				btnlock.setForeground(SystemColor.text);
				btnlock.setBackground(SystemColor.textHighlight);
				btnlock.setBorder(new CompoundBorder(new CompoundBorder(new BevelBorder(BevelBorder.RAISED, new Color(212, 208, 200), new Color(255, 255, 255), new Color(64, 64, 64), new Color(128, 128, 128)), new LineBorder(new Color(212, 208, 200))), null));
				btnlock.setFont(new Font("Tahoma", Font.BOLD, 11));
				btnlock.setToolTipText("click to lock and save data");
				btnlock.setBounds(395, 11, 112, 40);
				encPanel.add(btnlock);
				btnlock.setActionCommand("lock");
				btnlock.addActionListener( new FogWireBtnAction() );
			}
			{
				btnLockNSend = new JButton("LOCK & SEND");
				btnLockNSend.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						if(toField.getText().equalsIgnoreCase(""))
						{
							badEmail = true;
						}
					}
				});
				btnLockNSend.setBorder(new CompoundBorder(new CompoundBorder(new BevelBorder(BevelBorder.RAISED, new Color(212, 208, 200), new Color(255, 255, 255), new Color(64, 64, 64), new Color(128, 128, 128)), new LineBorder(new Color(212, 208, 200))), null));
				btnLockNSend.setBackground(SystemColor.textHighlight);
				btnLockNSend.setForeground(SystemColor.text);
				btnLockNSend.setFont(new Font("Tahoma", Font.BOLD, 11));
				btnLockNSend.setToolTipText("click to lock and send data");
				btnLockNSend.setBounds(262, 11, 123, 40);
				encPanel.add(btnLockNSend);
				btnLockNSend.addActionListener( new FogWireBtnAction() );
			}
			{
				btnClear = new JButton("CLEAR");
				btnClear.setBorder(new CompoundBorder(new CompoundBorder(new BevelBorder(BevelBorder.RAISED, new Color(212, 208, 200), new Color(255, 255, 255), new Color(64, 64, 64), new Color(128, 128, 128)), new LineBorder(new Color(212, 208, 200))), null));
				btnClear.setForeground(SystemColor.text);
				btnClear.setBackground(SystemColor.textHighlight);
				btnClear.setFont(new Font("Tahoma", Font.BOLD, 11));
				btnClear.setToolTipText("click to clear text field");
				btnClear.setBounds(619, 388, 99, 40);
				encPanel.add(btnClear);
				btnClear.setActionCommand("CLEAR");
				btnClear.addActionListener( new FogWireBtnAction() );
			}
			{
				btnBrowseForFile = new JButton("BROWSE FOR FILE TO LOCK");
				btnBrowseForFile.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						wasClicked = true;
					}
				});
				btnBrowseForFile.setBorder(new CompoundBorder(new CompoundBorder(new BevelBorder(BevelBorder.RAISED, new Color(212, 208, 200), new Color(255, 255, 255), new Color(64, 64, 64), new Color(128, 128, 128)), new LineBorder(new Color(212, 208, 200))), null));
				btnBrowseForFile.setBackground(SystemColor.textHighlight);
				btnBrowseForFile.setForeground(SystemColor.text);
				btnBrowseForFile.setFont(new Font("Tahoma", Font.BOLD, 11));
				btnBrowseForFile.setToolTipText("BROWSE FOR A TEXT FILE TO LOCK");
				btnBrowseForFile.setBounds(30, 11, 223, 40);
				encPanel.add(btnBrowseForFile);
				btnBrowseForFile.setActionCommand("lockBrowser");
				btnBrowseForFile.addActionListener( new FogWireBtnAction() );
			}
			{
				encDetailArea = new JTextPane();
				encDetailArea.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
				encDetScroller = new JScrollPane(encDetailArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
				encDetailArea.setText("");
				encDetailArea.setEditable(false);
				encDetScroller.getVerticalScrollBar().setUnitIncrement(8);
				encDetScroller.setBounds(30, 437, 722, 90);
				encPanel.add(encDetScroller);
			}
			{
				lblViewDetails = new JLabel("View Details:");
				lblViewDetails.setFont(new Font("Tahoma", Font.BOLD, 11));
				lblViewDetails.setBounds(31, 412, 98, 14);
				encPanel.add(lblViewDetails);
			}
			{
				lblEnterTheText = new JLabel("Enter the Text to Be Locked:");
				lblEnterTheText.setFont(new Font("Tahoma", Font.BOLD, 11));
				lblEnterTheText.setBounds(30, 103, 211, 14);
				encPanel.add(lblEnterTheText);
			}
			{

				lblTo = new JLabel("To:");
				lblTo.setFont(new Font("Tahoma", Font.BOLD, 11));
				lblTo.setToolTipText("Enter the Recipient's email address");
				lblTo.setBounds(30, 75, 46, 14);
				encPanel.add(lblTo);
			}
			{
				toField = new JTextField("");
				toField.setText("");
				toField.setToolTipText("Enter the Recipient's email address");
				toField.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
				toField.setBounds(63, 72, 273, 20);
				encPanel.add(toField);
				toField.setColumns(10);
			}
			{
				JLabel encLabel = new JLabel("");
				encLabel.setIcon(new ImageIcon(FogWire1121.class.getResource("/images/FogWire225x61.gif")));
				encLabel.setBounds(642, 11, 225, 61);
				encPanel.add(encLabel);
			}
			{
				lblClickClearTo = new JLabel("Click CLEAR to enable Browse for File to Lock button --->");
				lblClickClearTo.setFont(new Font("Tahoma", Font.BOLD, 11));
				lblClickClearTo.setForeground(SystemColor.activeCaption);
				lblClickClearTo.setVisible(false);
				lblClickClearTo.setBounds(263, 401, 353, 14);
				encPanel.add(lblClickClearTo);
			}
			{
				btnChangeFC = new JButton("New Fog Code");
				btnChangeFC.setToolTipText("Please Enter your Fog Code (consisting of 5 numbers)");
				btnChangeFC.setForeground(Color.WHITE);
				btnChangeFC.setFont(new Font("Tahoma", Font.BOLD, 9));
				btnChangeFC.setBackground(SystemColor.controlDkShadow);
				btnChangeFC.setActionCommand("lock");
				btnChangeFC.setBounds(517, 16, 105, 31);
				btnChangeFC.addActionListener( new FogWireBtnAction() );
				encPanel.add(btnChangeFC);
			}



			{

				// unlock tab    

				decPanel = new JPanel();
				decPanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
				tabbedPane.addTab("LOCK", null, decPanel, null);
				decPanel.setLayout(null);

				tabbedPane.addTab("UNLOCK DATA", null, decPanel, null);
				decPanel.setLayout(null);

				{

					decTextPane = new JEditorPane();
					decTextPane.setEditable(false);
					decTextPane .setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
					decTextPane .setToolTipText("");
					decScroller = new JScrollPane(decTextPane, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER );
					decScroller.getVerticalScrollBar().setUnitIncrement( 16 );
					decScroller.setBounds(30,87, 718, 316);
					decTextPane.setText("");
					decPanel.add(decScroller);


				}


				{
					btnunlock = new JButton("UNLOCK");
					btnunlock.setForeground(SystemColor.activeCaptionText);
					btnunlock.setBackground(SystemColor.textHighlight);
					btnunlock.setBorder(new CompoundBorder(new CompoundBorder(new BevelBorder(BevelBorder.RAISED, new Color(212, 208, 200), new Color(255, 255, 255), new Color(64, 64, 64), new Color(128, 128, 128)), new LineBorder(new Color(212, 208, 200))), null));
					btnunlock.setFont(new Font("Tahoma", Font.BOLD, 11));
					btnunlock.setToolTipText("CLICK TO UNLOCK LOCKED DATA");
					btnunlock.setBounds(273, 11, 108, 36);
					btnunlock.setVisible(false);
					decPanel.add(btnunlock);
					btnunlock.addActionListener( new FogWireBtnAction() );
				}
				{
					decDetailArea = new JTextPane();
					decDetailArea.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
					decDetailArea.setText("");
					decDetailArea.setEditable(false);
					decDetScroller = new JScrollPane(decDetailArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
					decDetScroller.getVerticalScrollBar().setUnitIncrement(8);
					decDetScroller.setBounds(30, 437, 718, 90);
					decPanel.add(decDetScroller);
				}
				{
					JLabel label = new JLabel("View Details:");
					label.setFont(new Font("Tahoma", Font.BOLD, 11));
					label.setBounds(30, 414, 150, 14);
					decPanel.add(label);
				}
				{
					lblUnlockedTxt = new JLabel("Unlocked Text Will Appear Below:");
					lblUnlockedTxt.setFont(new Font("Tahoma", Font.BOLD, 11));
					lblUnlockedTxt.setBounds(30, 62, 211, 14);
					decPanel.add(lblUnlockedTxt);
				}
				{
					btnBrowseForDec= new JButton("BROWSE FOR A FILE TO UNLOCK");
					btnBrowseForDec.setBorder(new CompoundBorder(new CompoundBorder(new BevelBorder(BevelBorder.RAISED, new Color(212, 208, 200), new Color(255, 255, 255), new Color(64, 64, 64), new Color(128, 128, 128)), new LineBorder(new Color(212, 208, 200))), null));
					btnBrowseForDec.setBackground(SystemColor.textHighlight);
					btnBrowseForDec.setForeground(SystemColor.text);
					btnBrowseForDec.setFont(new Font("Tahoma", Font.BOLD, 11));
					btnBrowseForDec.setToolTipText("BROWSE FOR A FILE TO UNLOCK");
					btnBrowseForDec.setBounds(30, 9, 223, 40);
					decPanel.add(btnBrowseForDec);
					btnBrowseForDec.setActionCommand("unlockBrowser");
					btnBrowseForDec.addActionListener( new FogWireBtnAction() );
				}

				{
					JLabel decLabel = new JLabel("");
					decLabel.setIcon(new ImageIcon(FogWire1121.class.getResource("/images/FogWire225x61.gif")));
					decLabel.setBounds(642, 11, 225, 61);
					decPanel.add(decLabel);
				}


				//Help Tab

				hlpPanel = new JPanel();
				hlpPanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
				tabbedPane.addTab("HELP", null, hlpPanel, null);
				hlpPanel.setLayout(null);
				{
					URL lockHTML = FogWire1121.class.getResource("/images/lock.html");

					lockHelp = new JEditorPane();
					HTMLEditorKit kit = new HTMLEditorKit();
					lockHelp.setEditorKit(kit);
					lockHelp.setEditable(false);
					lockHelp.setBackground(Color.WHITE);

					if (lockHTML != null) {
						try {
							lockHelp.setPage(lockHTML);
						} catch (IOException e) {
							try {
								fogWireMessage("Cannot Load Lock Instructions: " + lockHTML);
							} catch (FileNotFoundException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					} else {
						try {
							fogWireMessage("Couldn't find file: lock.html");
						} catch (FileNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

					StyleSheet ss = kit.getStyleSheet();
					ss.addRule("body {color:#000; font-family:times; margin: 4px; }");
					ss.addRule("h1 {color: blue;}");
					ss.addRule("h2 {color: #ff0000;}");
					ss.addRule("pre {font : 10px monaco; color : black; background-color : #fafafa; }");
					decHlpScroller = new JScrollPane(lockHelp, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
					decHlpScroller.getVerticalScrollBar().setUnitIncrement(8);
					decHlpScroller.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
					decHlpScroller.setBounds(206, 83, 589, 196);
					hlpPanel.add(decHlpScroller);

				}

				{
					JLabel hlpLabel = new JLabel("");
					hlpLabel.setIcon(new ImageIcon(FogWire1121.class.getResource("/images/FogWire225x61.gif")));
					hlpLabel.setBounds(642, 11, 225, 61);
					hlpPanel.add(hlpLabel);
				}
				{
					JLabel lblLockInstructions = new JLabel("Data Locking Instructions:");
					lblLockInstructions.setFont(new Font("Tahoma", Font.BOLD, 11));
					lblLockInstructions.setBounds(10, 84, 186, 14);
					hlpPanel.add(lblLockInstructions);
				}
				{
					JLabel lblUnlockInstructions = new JLabel("Data Unlocking Instructions:");
					lblUnlockInstructions.setFont(new Font("Tahoma", Font.BOLD, 11));
					lblUnlockInstructions.setBounds(10, 311, 186, 14);
					hlpPanel.add(lblUnlockInstructions);
				}
				{
					URL unlockHTML = FogWire1121.class.getResource("/images/unlock.html");

					unlockHelp = new JEditorPane();
					HTMLEditorKit kit = new HTMLEditorKit();
					unlockHelp.setEditorKit(kit);
					unlockHelp.setEditable(false);
					unlockHelp.setBackground(Color.WHITE);

					if (unlockHTML != null) {
						try {
							unlockHelp.setPage(unlockHTML);
						} catch (IOException e) {
							try {
								fogWireMessage("Cannot Load Unlock Instructions: " + unlockHTML);
							} catch (FileNotFoundException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					} else {
						System.err.println("Couldn't find file: unlock.html");
					}

					StyleSheet ss = kit.getStyleSheet();
					ss.addRule("body {color:#000; font-family:times; margin: 4px; }");
					ss.addRule("h1 {color: blue;}");
					ss.addRule("h2 {color: #ff0000;}");
					ss.addRule("pre {font : 10px monaco; color : black; background-color : #fafafa; }");
					encHlpScroller = new JScrollPane(unlockHelp, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
					encHlpScroller.getVerticalScrollBar().setUnitIncrement(8);
					encHlpScroller.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
					encHlpScroller.setBounds(206, 310, 589, 186);
					hlpPanel.add(encHlpScroller);
					//	unlockHelp.setColumns(10);
				}
			}

		}
	}


	//****************************************************************************************
	/*	END GUI CREATION 
	 */
	//****************************************************************************************

	//****************************************************************************************
	/*	INNER CLASS FOR ACTION LISTENTER
	 * this will be redone and refactored in the next release.
	 * it's extremely ugly, just know that it will be fixed soon.
	 */
	//****************************************************************************************
	class FogWireBtnAction implements ActionListener {
		public void actionPerformed(ActionEvent action) {
			// TODO Auto-generated method stub
			btnAction(action);
			if (!(decTextPane.getText().equalsIgnoreCase("")))
			{
				btnunlock.setVisible(true);
			}

		}

		@SuppressWarnings("static-access")
		public void btnAction (ActionEvent action)
		{


			if (action.getSource() == btnlock)

			{
				String encString = encTextPane.getText();

				if (encString.equalsIgnoreCase(""))
				{
					try {
						fogWireMessage("No Data to Lock");
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				else
				{
					if(!(encChooser.isVisible()))
					{
						encString = encTextPane.getText();


						try {
							FogWireMakeFile(unlockedFile,encString);
							ReadToEncrypt re = new ReadToEncrypt();

							//	ReadWriteBinFile rwf = new ReadWriteBinFile(encFile.getName());


						} catch (FileNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}  

						try {
							fogWireMessage("File Successfully Locked!");
							FogWire1121.fogWireSetDetails("File Successfully locked to locked.txt ",FogWire1121.encDetailArea); 
						} catch (FileNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						encTextPane.setText("");
						toField.setText("");
						btnBrowseForFile.setEnabled(true);
						lblClickClearTo.setVisible(false);

						btnBrowseForFile.setBackground(SystemColor.textHighlight);
					}
					else

					{
						try {
							FogWireMakeFile(unlockedFile,encString);
							ReadToEncrypt re = new ReadToEncrypt();
							//	ReadWriteBinFile rwf = new ReadWriteBinFile(encFile.getName());
						} catch (FileNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}  
						encTextPane.setText("");
						try {
							fogWireMessage("File Successfully Locked!");
							FogWire1121.fogWireSetDetails("File Successfully locked to locked.txt ",FogWire1121.encDetailArea); 
						} catch (FileNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

				}
			}


			else if (action.getSource() == btnunlock)
			{
				String decString = decTextPane.getText();
				// take the selected file in the chooser and update the contents of unlocked.txt with that file
				try {
					FogWireMakeFile(lockedFile,decString);
					ReadToDecrypt de = new ReadToDecrypt();
					//after file is decrypted, display it in the decryption pane
					fogWireReader(unlockedFile, decTextPane);
					btnunlock.setEnabled(false);
					FogWire1121.fogWireSetDetails("File Successfully Unlocked. ",FogWire1121.decDetailArea); 
				}
						
					catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				 catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 
				

			}


			else if (action.getSource() == btnClear)
			{
				encTextPane.setText("");
				toField.setText("");
				btnBrowseForFile.setEnabled(true);
				lblClickClearTo.setVisible(false);

				btnBrowseForFile.setBackground(SystemColor.textHighlight);
			}


			//LOCK AND SEND
			else if (action.getSource() == btnLockNSend)
			{

				//!(badEmail) && (FogWireLogin.checkEmail(toField.getText()))
				if (FogWireLogin.checkEmail(toField.getText()))
				{
					
					try {
						FogMail sm = new FogMail();
						
						String encString = encTextPane.getText();

						if (encString.equalsIgnoreCase(""))
						{
							try {
								fogWireMessage("No Data to Lock");
							} catch (FileNotFoundException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}

						else
						{
							if(!(encChooser.isVisible()))
							{
								encString = encTextPane.getText();


								try {
									FogWireMakeFile(unlockedFile,encString);
									ReadToEncrypt re = new ReadToEncrypt();

									//	ReadWriteBinFile rwf = new ReadWriteBinFile(encFile.getName());


								} catch (FileNotFoundException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}  

								try {
									fogWireMessage("File Successfully Locked!");
									FogWire1121.fogWireSetDetails("File Successfully locked to locked.txt ",FogWire1121.encDetailArea); 
								} catch (FileNotFoundException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								encTextPane.setText("");
								toField.setText("");
								btnBrowseForFile.setEnabled(true);
								lblClickClearTo.setVisible(false);

								btnBrowseForFile.setBackground(SystemColor.textHighlight);
							}
							else

							{
								try {
									FogWireMakeFile(unlockedFile,encString);
									ReadToEncrypt re = new ReadToEncrypt();
									//	ReadWriteBinFile rwf = new ReadWriteBinFile(encFile.getName());
								} catch (FileNotFoundException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}  
								encTextPane.setText("");
								try {
									fogWireMessage("File Successfully Locked!");
									FogWire1121.fogWireSetDetails("File Successfully locked to locked.txt ",FogWire1121.encDetailArea); 
								} catch (FileNotFoundException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}

						}
						sm.sendMail();
						fogWireMessage("File Sent Successfully!");
						FogWire1121.fogWireSetDetails("File sent successfully to: "+toField.getText(),FogWire1121.encDetailArea); 
						encTextPane.setText("");
						toField.setText("");
						btnBrowseForFile.setEnabled(true);
						lblClickClearTo.setVisible(false);
						btnBrowseForFile.setBackground(SystemColor.textHighlight);
					}

					catch (Exception e) {
						// TODO Auto-generated catch block
						try {
							fogWireMessage("Error Sending File!");
						} catch (FileNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						};
					}	
				}
			}

			else if (action.getSource() == btnBrowseForFile)
			{
				try {

					encChooser.addChoosableFileFilter(new TextFilter());
					encChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
					encChooser.setCurrentDirectory(unlockedDocs);

					int returnVal = encChooser.showOpenDialog(getParent());

					String filename = encChooser.getSelectedFile().getName();
					int dot = filename.lastIndexOf(".");
					String extension = filename.substring(dot);

					
					if(returnVal == JFileChooser.APPROVE_OPTION && extension.equalsIgnoreCase(".txt"))
					{
						fogWireReader(encChooser.getSelectedFile(), encTextPane);
					}

					else
					{
						fogWireMessage("Invalid File type. Please select a Text file (*.txt)");
					}
				}
				catch (FileNotFoundException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				catch (IOException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}



			}
			else if (action.getSource() == btnBrowseForDec)
			{
				try {
					btnunlock.setVisible(true);
					btnunlock.setEnabled(true);
					decChooser.addChoosableFileFilter(new TextFilter());
					decChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
					decChooser.setCurrentDirectory(lockedDocs);

					int returnVal = decChooser.showOpenDialog(getParent());

					String filename = decChooser.getSelectedFile().getName();
					int dot = filename.lastIndexOf(".");
					String extension = filename.substring(dot);

					if(returnVal == JFileChooser.APPROVE_OPTION && extension.equalsIgnoreCase(".txt"))
					{
						fogWireReader(decChooser.getSelectedFile(), decTextPane);
					}

					else
					{
						fogWireMessage("Invalid File type. Please select a Text file (*.txt)");
					}
					

				}
				catch (FileNotFoundException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				catch (IOException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}

			else if (action.getSource() == btnChangeFC)
			{
				try {
					UpdateFogCode.run();
					 
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

			//dispose();




		}
	}
	//****************************************************************************************
	/*	END INNER CLASS FOR ACTION LISTENTER
	 * this will be redone and refactored in the next release.
	 * it's extremely ugly, just know that it will improve
	 */
	//****************************************************************************************

	//****************************************************************************************
	/*	FogWireReadMethod
	 * takes in a file from the file chooser and dumps it into the appropriate text pane, lock
	 * or unlock.  this runs behind the file chooser buttons
	 * this will take Ricardo's writer method and give it the string that's in the text area
	 */
	//****************************************************************************************

	public void fogWireReader(File inputFile, Component c) throws FileNotFoundException, IOException
	{
		{
			String s = "";
			FileInputStream in = null;
			try
			{
				
				int size = (int) inputFile.length();
				if (size > 0)
				{
					byte[] buffer = new byte[size];
					in = new FileInputStream(inputFile);
					in.read(buffer, 0, size);
					s = new String(buffer, 0, size, "ISO8859_1");
					in.close();
				}
			}
			catch (FileNotFoundException e)
			{
				System.err.println("File not found: " + e);
			}
			catch (IOException e)
			{
				System.err.println("I/O problems: " + e);
			}
			finally
			{
				if (in != null)
				{
					try
					{
						in.close();
					}
					catch (IOException ignore) {}
				}
			}
			// return the string to the appropriate text area
			((JEditorPane) c).setText(s);
		}

	
	}
	public static void fogWireMessage (String message) throws FileNotFoundException
	{
		JOptionPane optionPane = new JOptionPane();
		optionPane.setMessage(message);
		optionPane.setMessageType(JOptionPane.INFORMATION_MESSAGE);
		JDialog dialog = optionPane.createDialog(null, message);
		dialog.add(optionPane);
		dialog.setVisible(true);
	}

	public static void fogWireSetDetails (String details, Component c)
	{
		String t = ((JTextPane) c).getText();
		
		((JTextPane) c).setText(t + details + "\n");
	}

	public static  String checkData (String data, JFileChooser jfc, Component c) throws FileNotFoundException
	{
		if (!(jfc.isVisible()))
		{
			data = ((JTextComponent) c).getText();
		}
		else if (jfc.isVisible() && jfc.getSelectedFile().getName().compareToIgnoreCase(data)== 0)
		{
			data = jfc.getSelectedFile().getName();

		}

		else
		{
			try {
				fogWireMessage("Cannot find data to unlock!");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return data;	
	}

	static void FogWireMakeFile(File placeHolderFile, String textArea)
	throws FileNotFoundException, IOException {

		if (placeHolderFile == null) {
			throw new IllegalArgumentException("File should not be null.");
		}
		if (!placeHolderFile.exists()) 
		{
			File tmpFile = new File ("C:\\FogWire2\\Locked_Docs\\temp.txt");
			tmpFile.createNewFile();
			FileWriter tm = new FileWriter("C:\\FogWire2\\Locked_Docs\\temp.txt");
			tm.write("");
			tm.close();

			FogWireMakeFile(placeHolderFile,textArea);

		}
		if (!placeHolderFile.isFile()) {
			throw new IllegalArgumentException("Problem writing to: " + placeHolderFile);
		}
		if (!placeHolderFile.canWrite()) {
			throw new IllegalArgumentException("File cannot be written: " + placeHolderFile);
		}

		//use buffering to overwrite existing placeholder file with the data from the GUI
		Writer output = new BufferedWriter(new FileWriter(placeHolderFile));
		try {
			//FileWriter always assumes default encoding is OK..
			// may need to change encoding to force formatting.
			
	
		    	output.write( textArea );
			output.append(" ");
		}
		finally {
			output.close();
		}
		//return placeHolderFile.getName();
	}


} // end FogWire GUI