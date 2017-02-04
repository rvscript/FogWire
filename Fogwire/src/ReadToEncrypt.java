/**
* FogWire
* Personal Encryption Software
* (c)5dz Productions 2009
* @version 1.0
* @authors Ricardo Viera, Dmitry Sharlot, Joseph Everett, Anthony Sinatra
*/

import java.io.*;

public class ReadToEncrypt {
	String firstCopy = "locked.txt";
	File firstC;
	File theFile;
	String thefile = "unlocked.txt";
	DesEncryption de = new DesEncryption();
	DesDecryption dd = new DesDecryption();
	// ***********************************************************
	// *****--Constructor to Class . All Methods may run from here--*****

	public ReadToEncrypt() throws IOException {
		
		this.theFile = new File(thefile);
		this.firstC = new File(firstCopy);
		//checkFile();
		writeToFile(firstCopy);
		System.out.println("complete now check "+firstCopy+ " doc/jpg/txt/pdf");
	}

	// ************************************************************

	private void checkFile() throws IOException {
		if (!firstC.exists()) {
			firstC.createNewFile();
			System.out.println("New file " + firstC.toString()
					+ " has been created to the current directory");
		}

		if (!theFile.exists()) {
			theFile.createNewFile();
			System.out.println("New file " + theFile.toString()
					+ " has been created to the current directory");
		}
	}

	private void writeToFile(String file) throws IOException {
		DataOutputStream out = new DataOutputStream(
				new BufferedOutputStream(new FileOutputStream("C:\\FogWire2\\Locked_Docs\\"+firstCopy)));
		DataInputStream in = new DataInputStream(
				new BufferedInputStream(new FileInputStream("C:\\FogWire2\\Unlocked_Docs\\"+theFile)));

		try {

			int c;
			while ((c = in.readChar()) != -1) {
				System.out.println(c + "= c");
				//c=dd.DesDecryptionRounds(c);
				c=de.DesEncryptionRounds(c);
				System.out.println(c + "= c");				
				out.writeChar(c);
			}
			System.out.println();
		} catch (EOFException e) {
			System.out.println("EOF Exception check read method of file");
		} finally {

			if (in != null) {
				in.close();
			}

			if (out != null) {
				out.close();
			}

			System.out.println("\nMake sure the extension is "
					+ "correct and open "+firstCopy);
		}
  }
}