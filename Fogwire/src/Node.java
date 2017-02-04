/**
* FogWire
* Personal Encryption Software
* (c)5dz Productions 2009
* @version 1.0
* @authors Ricardo Viera, Dmitry Sharlot, Joseph Everett, Anthony Sinatra
*/

import java.util.ArrayList;

public class Node implements PrimeArray{
	double fee;
	double sqrtfee;
	boolean set = true, set1 = true;
	ArrayList <Double> afactor = new ArrayList();
	ArrayList <Double> acomposite = new ArrayList();
	public Node(double f){
		
		this.fee=f;
		
		sqrtfee = Math.sqrt((double)fee);		
		findFactors();
	}
	public void findFactors()
	{
		//System.out.println("Node value = "+fee);
		int i = 0;
		while(set)
		{
		    if(fee%(double)primes[i]==0)
		    {
		    	afactor.add((double)primes[i]);	
		    	
		    	for(int j =0; j<primes.length; j++)
		    	{
		    		if(fee/primes[i]== primes[j])
		    		{
		    			afactor.add(fee/primes[i]);
		    			set1 = false;
		    			j=1900;	
		    		}		    					    		
		    	}	    	
		    	if (set1)
		    	{
		    		acomposite.add(fee/primes[i]);
		    	}
		    	set = false;
		    }
		    i++;
		}
		/*
		for(double a: afactor)
		{
			if(a != 0)
			{
			   System.out.println("afactor  =  "+a);			   
			}
		}
		
		for(double a: acomposite)
		{
			if(a != 0)
			{
			   System.out.println("acomposite  =  "+a);			   
			}
		}
		*/
	}
	
}
