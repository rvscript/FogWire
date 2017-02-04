package readwriteFile;
import java.io.*;
public class RWfull {
	//*************************************************************
	//Write the proper extensions here to the file. 
	//IF the File does not exist it will be created!!!!
	//MAKE SURE THE EXTENSTIONS MATCH THE FILE FROM THE RUN CLASS
	//FILE.EXT EXTS MUST MATCH
	String firstCopy = "outagain.jpg";
	String EncryptTestCopy = "binary.jpg";
	//^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
	FileInputStream in=null;
	FileInputStream in2=null;
    FileOutputStream out=null;
    FileOutputStream out2=null;
    File f;
    File g=new File(firstCopy);
    String theFile="", scfull="";
    String temp="";
    String key = "10001111010111000001111111100011";
    double bc=0;
    private final static int padlength=32;
    private static String padamount(String st)
	{
		//method to pad binary value 
		//nested method works for sBox method
		String stemp="";
		if(st.length()<=padlength){
        	int padamount =0;
        	padamount = padlength-st.length();
        	
        	for(int i=0; i<padamount;i++){
        		stemp=0+stemp;
        	}
        	stemp=stemp+st;
        }
		return stemp;
	}   
  public RWfull(String theFile)throws IOException{
	  this.f=new File(theFile);	 
	  checkFile();
	  createFile(firstCopy);
	  System.out.println("complete now check outagain."+"doc/jpg/txt/pdf");
  }
  private void checkFile()throws IOException{
	if(!f.exists()){
	    f.createNewFile();
	    System.out.println(
	    "New file " +f.toString()+
	    " has been created to the current directory");
	} 
	        
	if(!g.exists()){
		g.createNewFile();
		System.out.println(
		"New file "+ theFile+" has been created to the current directory");
	}   
  }
 
  private void createFile(String file)throws IOException{  
    String file2=EncryptTestCopy;
    
    try{
    	in = new FileInputStream(f);
    	out = new FileOutputStream(file);
    	in2= new FileInputStream(f);
    	out2 =new FileOutputStream(file2);
    	int c;
    	while((c = in.read())!=-1){
    		//String sc = Integer.toBinaryString(c);
    		out.write(c); 
    	}
    	while((c = in2.read())!=-1){
    		
    		//System.out.println(c+" is the character int read from file "+ file);
    		temp = int2binary(c);
    		c=EncryptBinary(temp);
    		
    		temp = int2binary(c);
    		c=DecryptBinary(temp);
    		
    		out2.write(c); 
    	}   
    }
    finally{
    	if(in !=null){
    		in.close();
    	}
    	if(in2 !=null){
    		in2.close();
    	}
    	if(out !=null){
    		out.close();
    	}
    	if(out2 !=null){
    		out2.close();
    	}
    System.out.println("Make sure the extension is correct and open binary.xxx");	
    }
}

private String int2binary(int cc){
	String scc =Integer.toBinaryString(cc);
	scc=padamount(scc);
	return scc;
}

private int EncryptBinary(String scc){
	String escc = scc;
	int esccint = 0;
	String Wescc = Xoar(escc,key);
	esccint = (int)binToDouble(Wescc);
	return esccint;
}
private int DecryptBinary(String scc){
	String dscc = scc;
	int dsccint =0;
	String Wdscc = Xoar(dscc,key);
	dsccint = (int)binToDouble(Wdscc);
	return dsccint;
}
private String Xoar(String m, String k)
{
	String xoar = "XOR=";
	int ml = m.length();
	int kl = k.length();
	System.out.println("start string length "+ml
			+" second string length "+kl);
	System.out.println("m = "+m+"\n"+"k = "+k);
	System.out.println("-----------------------------------------------------------------------------");
	char []mc = m.toCharArray();
	char []kc = k.toCharArray();
	int  []mci= new int[padlength];
	int  []kci= new int[padlength];
	int []mktemp= new int[padlength];
	
	for (int i =0; i<ml; i++){
		if(mc[i]=='1'){
			mci[i]=1;
		}
		else if(mc[i]=='0'){
			mci[i]=0;
		}
		else
			System.out.println("not a bit! m string");
		if(kc[i]=='1'){
			kci[i]=1;
		}
		else if(kc[i]=='0'){
			kci[i]=0;
		}
		else
			System.out.println("not a bit! k string");
	}
	for (int j = 0; j< kl; j++){
		mktemp[j]=mci[j]^kci[j];
		xoar = xoar+mktemp[j];
		//System.out.print(mktemp[j]);
	}
	
	return xoar;
}
private double binToDouble(String scc){
	String b=scc;
	int c=0;
	int j = b.length();
	double bc=0;
	for(int i=0;i<j;i++){			
	   c = b.charAt(j-1);
	   System.out.print(c+" ");
	   if(c==49){
		bc=bc+Math.pow(2.0, (double)i);   
	   }
	   j--;
	}
	//System.out.println("--------");
	System.out.println(bc+ " is the number created from binary"); 
	//System.out.println((char)bc+" is the ascii rep of bc");
return bc;
} 
public String toString()
{
	String file ="";
	file = out.toString();
	return file;
}
}

