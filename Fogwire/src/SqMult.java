/**
* FogWire
* Personal Encryption Software
* (c)5dz Productions 2009
* @version 1.0
* @authors Ricardo Viera, Dmitry Sharlot, Joseph Everett, Anthony Sinatra
*/

public class SqMult {
	double a;
	int n,e,d;
	double dec;
	public SqMult(int e, int n, int d, int a)
	{
		this.e = e;
		this.n = n;
		this.a = a;
		this.d = d;
		this.dec = EncSqMult();
		System.out.println("Public Key(e = "+e+",n = "+n+")");
		System.out.println("Private Key(d = "+d+")");
		System.out.println("Number to Encrypt "+a);
		System.out.println("Number to Decrypt "+dec);
		System.out.println("Number after decryption: \n"+
				"decSqMult(dec) = "+decSqMult(dec));
	}
	public double EncSqMult()
	{
		String binOfe = Integer.toBinaryString(e);
		double y = 1;
		for(int i = 0; i < binOfe.length(); i++)
		{
			if(binOfe.charAt((binOfe.length() -1)- i)==49)
			    y = (a * y) % n;
			a = Math.pow(a, 2.0)%n;
		}
		//System.out.println("y = " + y);
		return y;
	}
	public double decSqMult(double z)
	{
		String binOfe = Integer.toBinaryString(d);
		double y = 1;
		for(int i = 0; i < binOfe.length(); i++)
		{
			if(binOfe.charAt((binOfe.length() -1)- i)==49)
			    y = (z * y) % n;
			z = (Math.pow(z, 2.0)%n);
		}
		//System.out.println("y = " + y);
		return y;
	}
}
