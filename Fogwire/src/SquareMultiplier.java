/**
* FogWire
* Personal Encryption Software
* (c)5dz Productions 2009
* @version 1.0
* @authors Ricardo Viera, Dmitry Sharlot, Joseph Everett, Anthony Sinatra
*/

public class SquareMultiplier {
    double e,fee,fof;
	public SquareMultiplier(int e, int fee, int fof)
	{
		this.e = e;
		this.fee = fee;
		this.fof = fof;
	}
	public double multiplyForD()
	{
		int fofint = (int)fof-1;
		String binOfe = Integer.toBinaryString(fofint);
		double y = 1;
		for(int i = 0; i<binOfe.length(); i++)
		{
			if(binOfe.charAt((binOfe.length() -1)- i)==49)
			    y = (e * y) % fee;
			e = Math.pow(e, 2.0)%fee;
		}
		System.out.println("y = " + y);
		return y;
	}
}
