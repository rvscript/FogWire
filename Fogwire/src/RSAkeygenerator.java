/**
* FogWire
* Personal Encryption Software
* (c)5dz Productions 2009
* @version 1.0
* @authors Ricardo Viera, Dmitry Sharlot, Joseph Everett, Anthony Sinatra
*/

import java.util.Random;

public class RSAkeygenerator implements PrimeArray{
Random g = new Random();
static int fi,pe,pd,pn;
int n,p,q,fee,e;
int feeOffee=1;
int arrayIndex = primes.length-1;
double d;
int hm = 100000000; int lm = 1000000;
long keyOK=0;
	public RSAkeygenerator(){
		while (keyOK != 1)
		{
		feeOffee = 1;
		choosePQ_N_fee();
		setfeeOffee(fee);
		this.e = chooseE();
		this.d = createD(e);
		
		keyTest();
		}
	}
	private int choosePQ_N_fee(){
		boolean On = true;
		int i = 0;
		while(On){
		   i = g.nextInt(arrayIndex);
		   p = primes[i];
		   i = g.nextInt(arrayIndex);
		   q = primes[i];
		   if(p*q>lm&&p*q < hm){
			   n = p*q;
			   fee = (p-1)*(q-1);
			   On = false;
		   }
		   else 
			   On = true;
		}
		System.out.println("P = "+p+" Q = "+q+" N = "+n+" fee = "+fee); 
	    return n;
	}
	private int setfeeOffee(int f){
		Tree t = new Tree(f);
		for(double a: t.primeFactors)
			feeOffee *= (a-1);
		System.out.println("feeOffee = "+feeOffee);
		return feeOffee;
	}
	private int chooseE(){
		int e = 1;
		
		while(e == 1)
			e = eSelector();
		System.out.println("our 'e' = "+e);
		return e;
	}
	private int eSelector()
	{
		int e = 1;
		int inc = 0;
		int etemp = g.nextInt(fee)+2;
		
		    while(inc < primes.length){			
			     if(etemp == primes[inc])
			    {
		           e = etemp;
		           inc = primes.length;	
			    }			     
			    inc++;
		    }
		return e;
	}
	private double createD(int e)
	{
		double d = 0;
		SquareMultiplier sm = new SquareMultiplier(e,fee,feeOffee);
		d = sm.multiplyForD();
		
		System.out.println("our 'd' = "+d);
		return d;
	}
	public void keyTest()
	{
		long ee = 0; long dd = 0 ; long f = fee;
		ee = e; dd = (long) d; 
		
		keyOK = ee*dd%f;
		
		System.out.println("key OK status = "+ keyOK);
		   if (keyOK == 1){
			   System.out.println("Key checks out");
		       fi = (int) f; pe = (int) ee; pd = (int) dd;
		       pn = n;		       
		   }
		   else
		   {
			   System.out.println("Not a good key try again");
		   }
	}
	
}
