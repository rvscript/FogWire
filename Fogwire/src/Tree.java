/**
* FogWire
* Personal Encryption Software
* (c)5dz Productions 2009
* @version 1.0
* @authors Ricardo Viera, Dmitry Sharlot, Joseph Everett, Anthony Sinatra
*/

import java.util.ArrayList;

public class Tree implements PrimeArray{
	ArrayList <Double>gfactors = new ArrayList<Double>();
	ArrayList <Double>primeFactors = new ArrayList<Double>();
	int fee;
	Node n;
	
   public Tree(int f)
   {
	   this.fee = f;
	   n = new Node(f);
	   System.out.println("The Prime Factorization of \n"+fee+" = ");
	   setGfactors();
	   setPrimeFactors();
	   createChildren();
	   DisplayPrimeFactors();
   }
   public void createChildren()
   {	
	 for(int i = 0; i< gfactors.size(); i++)
	  {
		 if(gfactors.get(i)!=0)
		 {
		   //System.out.print(gfactors.get(i)+" ");
		   n = new Node(gfactors.get(i));
		   setGfactors();
		   setPrimeFactors();
		 }
	  }
	 //System.out.println();
   }
   public void setGfactors()
   {
	   for(double a: n.acomposite)
	   {
		   gfactors.add(a);
	   }
   }
   public void setPrimeFactors()
   {
	   for(double a: n.afactor)
	   {
		   primeFactors.add(a);
	   }
   }
   public void DisplayPrimeFactors()
   {
	   for(double a: primeFactors)
		   System.out.println("afactor "+a);
   }
}
