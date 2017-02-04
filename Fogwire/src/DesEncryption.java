/**
* FogWire
* Personal Encryption Software
* (c)5dz Productions 2009
* @version 1.0
* @authors Ricardo Viera, Dmitry Sharlot, Joseph Everett, Anthony Sinatra
*/

public class DesEncryption {
	int c, ctemp;
	int fi;
	int lI, ltemp;
	int rI;
	
	//new fogCode Updated by Dmitry 12/08/09
	FWProfile fogCode = new FWProfile();
	int key = fogCode.getFogcode();
	
	
	//int key=41477;
	//int key = (int) 2.405179363E9;
	
	InnerFunction func = new InnerFunction();
	DesKeyGen dkg = new DesKeyGen();
	public DesEncryption(){
	
	}
	public int DesEncryptionRounds(int c){
	
		int ctemp = 0;
		for(int i=0; i<2; i++)
		   ctemp = desEncryption(c);
		
		c = ctemp;
		return c;
	}
	private int desEncryption(int c){
		this.c=c;		
		int k3 = getKey();
		setLeftInt(c);
		setRightInt(c);
		int right = getRightInt();
		int left  = getLeftInt();
		fi = func.IFunction(right, k3);
		
		fi = Integer.rotateLeft(fi, 16);
		
	    ltemp = left ^ fi ;
	    ctemp = ltemp | right;
	    ctemp = Integer.rotateLeft(ctemp, 16);
	    
	    c = ctemp;
	    System.out.println("Encrypted C = "+Integer.toBinaryString(c));
	    setKey(k3);
	    System.out.println("our key = "+key);
		return c;
	}
	
	private void setLeftInt(int cc){
		this.lI = cc & 0xffff0000;
	}
	private void setRightInt(int cc){
		this.rI = cc & 0x0000ffff;
	}
	private int getLeftInt(){
		return lI;
	}
    private int getRightInt(){
		return rI;
	}
    private void setKey(int kk){
	int kkt;
    	kkt = dkg.genDesKey(kk);
    	this.key = kkt;
//	this.key = key;
    }
    private int getKey(){
    	return this.key;
    }
	
}
