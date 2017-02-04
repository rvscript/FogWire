/**
* FogWire
* Personal Encryption Software
* (c)5dz Productions 2009
* @version 1.0
* @authors Ricardo Viera, Dmitry Sharlot, Joseph Everett, Anthony Sinatra
*/

public class RSAKeys {
	static int publicKeyE = 0;
	static int Fee = 0;
	static int privateKeyD = 0;
	static int publicKeyN = 0;
	
	public RSAKeys()
	{
		RSAkeygenerator rsa = new RSAkeygenerator();
		setPublicKeyE();
		setFee();
		setPrivateKey();
		setPublicKeyN();
	}
	public void setPublicKeyE()
	{		
		publicKeyE = RSAkeygenerator.pe;
		Fee = RSAkeygenerator.fi;
	}
	public static int getPublicKeyE()
	{
		return publicKeyE;
	}
	public void setFee()
	{
		Fee = RSAkeygenerator.fi;
	}
	public int getFee()
	{
		return Fee;
	}
	public void setPrivateKey()
	{
		privateKeyD = RSAkeygenerator.pd;
	}
	public int getPrivateKey()
	{
		return privateKeyD;
	}
	public void setPublicKeyN()
	{
	   publicKeyN = RSAkeygenerator.pn;
	}
}
