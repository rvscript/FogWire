/**
* FogWire
* Personal Encryption Software
* (c)5dz Productions 2009
* @version 1.0
* @authors Ricardo Viera, Dmitry Sharlot, Joseph Everett, Anthony Sinatra
*/

public class InnerFunction {
	
	Ecomponents ec = new Ecomponents();
	
	public InnerFunction()
	{
		
	}
	public int IFunction(int ri, int k)
	{
	    int r, r1, r2;
	    r  = ec.xOR(ri, k);
		r1 = ec.sBox(r);
		r2 = ec.pBox(r1);
	    return r2;
	}
	public int IFunctionD(int ri, int k)
	{
		int r, r1, r2;
		r = ec.pBoxDecrypt(ri);
		r1= ec.sBoxDecrypt(r);
		r2= ec.xOR(r1, k);
		return r2;
	}
}
