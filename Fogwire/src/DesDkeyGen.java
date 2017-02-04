/**
* FogWire
* Personal Encryption Software
* (c)5dz Productions 2009
* @version 1.0
* @authors Ricardo Viera, Dmitry Sharlot, Joseph Everett, Anthony Sinatra
*/

public class DesDkeyGen {
	int key;
	Ecomponents ec = new Ecomponents();
	public DesDkeyGen(){
		
	}
	public int genDesDKey(int kk){
		setKey(kk);	
		int kkL = key & 0x0000ff00;
		int kkR = key & 0x000000ff;
		int kkLbit = Integer.rotateLeft((kkL & 0x00008000),25);
		
		System.out.println("left = "+Integer.toBinaryString(kkL)+" Right = "+Integer.toBinaryString(kkR));
		System.out.println("initial key value = "+Integer.toBinaryString(key)+
				"\n key value = " + key);
		
		kkL = kkL<<1;
		kkL = kkL & 0x0000ff00;
		kkL = kkL | kkLbit;
		kkR = Integer.rotateLeft(kkR, 1);
		System.out.println("left = "+Integer.toBinaryString(kkL)+" Right = "+Integer.toBinaryString(kkR));
		
		key = kkL |kkR;	
		
		System.out.println("bits after joining L and R = "+Integer.toBinaryString(key)+
				"\n key value = " + key);
		ec.pBoxDecrypt(key);
		return key;
	}
    public void setKey(int kk)
    {
    	this.key = kk;
    }
    public int getKey(){
    	return key;
    }
}
