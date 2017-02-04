/**
* FogWire
* Personal Encryption Software
* (c)5dz Productions 2009
* @version 1.0
* @authors Ricardo Viera, Dmitry Sharlot, Joseph Everett, Anthony Sinatra
*/

public class Ecomponents {
	String temp, temp2;
	public Ecomponents(){
		
	}
	public int SwapperL(int c) {
		int s = c;
		int sleft = 0;
		sleft = Integer.rotateLeft(s, 16);
		System.out.println("sleft :" + sleft);
		return s;
	}

	public int SwapperR(int c) {
		int s = c;
		int sright = 0;
		sright = Integer.rotateRight(s, 16);
		System.out.println("sRight :" + sright);
		return s;
	}

	public int xOR(int c, int x) {
		int cc = c;
		int xx = x;
		cc = cc ^ xx;
		return cc;
	}

	public int pBox(int c) {
		System.out.println("********************************");
		int s = c;
		
		System.out.println("initial int = " + s);
		int msb0 = s & 0x00000001;
		int msb1 = s & 0x00000002;
		int msb2 = s & 0x00000004;
		int msb3 = s & 0x00000008;
		int msb4 = s & 0x00000010;
		int msb5 = s & 0x00000020;
		int msb6 = s & 0x00000040;
		int msb7 = s & 0x00000080;
		int msb8 = s & 0x00000100;
		int msb9 = s & 0x00000200;
		int msba = s & 0x00000400;
		int msbb = s & 0x00000800;
		int msbc = s & 0x00001000;
		int msbd = s & 0x00002000;
		int msbe = s & 0x00004000;
		int msbf = s & 0x00008000;

		s = s & 0xfffffff6;
		
		s = s | Integer.rotateLeft(msb0, 3);
		s = s | Integer.rotateRight(msb3, 3);
		
        s = s & 0xffffff9f;	
        
		s = s | Integer.rotateLeft(msb5, 1);
		s = s | Integer.rotateRight(msb6, 1);  
		
        s = s & 0xfffffbfb;	
        
		s = s | Integer.rotateLeft(msb2, 8);
		s = s | Integer.rotateRight(msba, 8);  
		
		temp2 = Integer.toBinaryString(s);
		System.out.println("Value of INT           = " + temp2);
		System.out.println("int = " + s);
		return s;
	}

	public int pBoxDecrypt(int c) {
		int s = c;
		
		int msb0 = s & 0x00000001;
		int msb1 = s & 0x00000002;
		int msb2 = s & 0x00000004;
		int msb3 = s & 0x00000008;
		int msb4 = s & 0x00000010;
		int msb5 = s & 0x00000020;
		int msb6 = s & 0x00000040;
		int msb7 = s & 0x00000080;
		int msb8 = s & 0x00000100;
		int msb9 = s & 0x00000200;
		int msba = s & 0x00000400;
		int msbb = s & 0x00000800;
		int msbc = s & 0x00001000;
		int msbd = s & 0x00002000;
		int msbe = s & 0x00004000;
		int msbf = s & 0x00008000;
		
		s = s & 0xfffffff6;
		
		s = s | Integer.rotateLeft(msb0, 3);
		s = s | Integer.rotateRight(msb3, 3);
		
        s = s & 0xffffff9f;	
        
		s = s | Integer.rotateLeft(msb5, 1);
		s = s | Integer.rotateRight(msb6, 1);    
		
        s = s & 0xfffffbfb;	
        
		s = s | Integer.rotateLeft(msb2, 8);
		s = s | Integer.rotateRight(msba, 8);
		
		temp2 = Integer.toBinaryString(s);
		System.out.println("DValue of INT           = " + temp2);
		System.out.println("int = " + s);
		System.out.println("----------------------------------");
		return s;
	}

	public int sBoxDecrypt(int c) {
		int s = c;
		int sb0, sb1, sb2, sb3, sb4, sb5, sb6, sb7;
		sb0 = s & 0x0000000f;
		sb1 = s & 0x000000f0;
		sb2 = s & 0x00000f00;
		sb3 = s & 0x0000f000;
		sb4 = s & 0x000f0000;
		sb5 = s & 0x00f00000;
		sb6 = s & 0x0f000000;
		sb7 = s & 0xf0000000;
		// Sbox 0
		switch (sb0) {
		case 0:
			sb0 = 0x00000004;
			break;
		case 1:
			sb0 = 0x00000002;
			break;
		case 2:
			sb0 = 0x00000005;
			break;
		case 3:
			sb0 = 0x00000006;
			break;
		case 4:
			sb0 = 0x00000008;
			break;
		case 5:
			sb0 = 0x00000003;
			break;
		case 6:
			sb0 = 0x0000000b;
			break;
		case 7:
			sb0 = 0x00000007;
			break;
		case 8:
			sb0 = 0x0000000c;
			break;
		case 9:
			sb0 = 0x0000000e;
			break;
		case 10:
			sb0 = 0x0000000a;
			break;
		case 11:
			sb0 = 0x00000009;
			break;
		case 12:
			sb0 = 0x00000001;
			break;
		case 13:
			sb0 = 0x0000000d;
			break;
		case 14:
			sb0 = 0x0000000f;
		case 15:
			sb0 = 0x00000000;
			break;
		default:
			System.out.println("ERROR 4 bit Hex value not Possible");
			System.out.println("Hex value " + sb0);
		}
		// Sbox 1
		switch (sb1) {
		case 0:
			sb1 = 0x000000c0;
			break;
		case 16:
			sb1 = 0x00000030;
			break;
		case 32:
			sb1 = 0x000000f0;
			break;
		case 48:
			sb1 = 0x00000000;
			break;
		case 64:
			sb1 = 0x000000a0;
			break;
		case 80:
			sb1 = 0x000000d0;
			break;
		case 96:
			sb1 = 0x00000020;
			break;
		case 112:
			sb1 = 0x00000090;
			break;
		case 128:
			sb1 = 0x000000e0;
			break;
		case 144:
			sb1 = 0x00000080;
			break;
		case 160:
			sb1 = 0x00000040;
			break;
		case 176:
			sb1 = 0x00000050;
			break;
		case 192:
			sb1 = 0x00000010;
			break;
		case 208:
			sb1 = 0x00000060;
			break;
		case 224:
			sb1 = 0x00000070;
			break;
		case 240:
			sb1 = 0x000000b0;
			break;
		default:
			System.out.println("ERROR 4 bit Hex value not Possible");
			System.out.println("Hex value " + sb1);
		}
		// sbox 2
		switch (sb2) {
		case 0:
			sb2 = 0x00000200;
			break;
		case 256:
			sb2 = 0x00000500;
			break;
		case 512:
			sb2 = 0x00000c00;
			break;
		case 768:
			sb2 = 0x00000f00;
			break;
		case 1024:
			sb2 = 0x00000300;
			break;
		case 1280:
			sb2 = 0x00000900;
			break;
		case 1536:
			sb2 = 0x00000600;
			break;
		case 1792:
			sb2 = 0x00000700;
			break;
		case 2048:
			sb2 = 0x00000b00;
			break;
		case 2304:
			sb2 = 0x00000a00;
			break;
		case 2560:
			sb2 = 0x00000e00;
			break;
		case 2816:
			sb2 = 0x00000d00;
			break;
		case 3072:
			sb2 = 0x00000000;
			break;
		case 3328:
			sb2 = 0x00000400;
			break;
		case 3584:
			sb2 = 0x00000100;
			break;
		case 3840:
			sb2 = 0x00000800;
			break;
		default:
			System.out.println("ERROR 4 bit Hex value not Possible");
			System.out.println("Hex value " + sb2);
		}
		// sbox 3
		switch (sb3) {
		case 0:
			sb3 = 0x00005000;
			break;
		case 4096:
			sb3 = 0x0000f000;
			break;
		case 8192:
			sb3 = 0x0000a000;
			break;
		case 12288:
			sb3 = 0x0000b000;
			break;
		case 16384:
			sb3 = 0x00000000;
			break;
		case 20480:
			sb3 = 0x00001000;
			break;
		case 24576:
			sb3 = 0x00004000;
			break;
		case 28672:
			sb3 = 0x00002000;
			break;
		case 32768:
			sb3 = 0x00006000;
			break;
		case 36864:
			sb3 = 0x00007000;
			break;
		case 40960:
			sb3 = 0x0000c000;
			break;
		case 45056:
			sb3 = 0x00008000;
			break;
		case 49152:
			sb3 = 0x0000f000;
			break;
		case 53248:
			sb3 = 0x00009000;
			break;
		case 57344:
			sb3 = 0x0000d000;
			break;
		case 61440:
			sb3 = 0x00003000;
			break;
		default:
			System.out.println("ERROR 4 bit Hex value not Possible");
			System.out.println("Hex value " + sb3);
		}
		/*
		// sbox4
		switch (sb4) {
		case 0:
			sb4 = 0x00090000;
			break;
		case 65536:
			sb4 = 0x00080000;
			break;
		case 131072:
			sb4 = 0x00040000;
			break;
		case 196608:
			sb4 = 0x00000000;
			break;
		case 262144:
			sb4 = 0x000f0000;
			break;
		case 327680:
			sb4 = 0x00030000;
			break;
		case 393216:
			sb4 = 0x000b0000;
			break;
		case 458752:
			sb4 = 0x00070000;
			break;
		case 524288:
			sb4 = 0x000a0000;
			break;
		case 589824:
			sb4 = 0x000e0000;
			break;
		case 655360:
			sb4 = 0x000d0000;
			break;
		case 720896:
			sb4 = 0x00050000;
			break;
		case 786432:
			sb4 = 0x00010000;
			break;
		case 851968:
			sb4 = 0x00060000;
			break;
		case 917504:
			sb4 = 0x00020000;
			break;
		case 983040:
			sb4 = 0x000c0000;
			break;
		default:
			System.out.println("ERROR 4 bit Hex value not Possible");
			System.out.println("Hex value " + sb4);
		}
		// sbox 5
		switch (sb5) {
		case 0:
			sb5 = 0x00c00000;
			break;
		case 1048576:
			sb5 = 0x00800000;
			break;
		case 2097152:
			sb5 = 0x00900000;
			break;
		case 3145728:
			sb5 = 0x00d00000;
			break;
		case 4194304:
			sb5 = 0x00a00000;
			break;
		case 5242880:
			sb5 = 0x00e00000;
			break;
		case 6291456:
			sb5 = 0x00400000;
			break;
		case 7340032:
			sb5 = 0x00500000;
			break;
		case 8388608:
			sb5 = 0x00000000;
			break;
		case 9437184:
			sb5 = 0x00100000;
			break;
		case 10485760:
			sb5 = 0x00200000;
			break;
		case 11534336:
			sb5 = 0x00300000;
			break;
		case 12582912:
			sb5 = 0x00600000;
			break;
		case 13631488:
			sb5 = 0x00700000;
			break;
		case 14680064:
			sb5 = 0x00a00000;
			break;
		case 15728640:
			sb5 = 0x00f00000;
			break;
		default:
			System.out.println("ERROR 4 bit Hex value not Possible");
			System.out.println("Hex value " + sb5);
		}
		// sbox 6
		switch (sb6) {
		case 0:
			sb6 = 0x03000000;
			break;
		case 16777216:
			sb6 = 0x07000000;
			break;
		case 33554432:
			sb6 = 0x0b000000;
			break;
		case 50331648:
			sb6 = 0x0a000000;
			break;
		case 67108864:
			sb6 = 0x06000000;
			break;
		case 83886080:
			sb6 = 0x02000000;
			break;
		case 100663296:
			sb6 = 0x01000000;
			break;
		case 117440512:
			sb6 = 0x05000000;
			break;
		case 134217728:
			sb6 = 0x09000000;
			break;
		case 150994944:
			sb6 = 0x08000000;
			break;
		case 167772160:
			sb6 = 0x04000000;
			break;
		case 184549376:
			sb6 = 0x00000000;
			break;
		case 201326592:
			sb6 = 0x0c000000;
			break;
		case 218103808:
			sb6 = 0x0d000000;
			break;
		case 234881024:
			sb6 = 0x0f000000;
			break;
		case 251658240:
			sb6 = 0x0e000000;
			break;
		default:
			System.out.println("ERROR 4 bit Hex value not Possible");
			System.out.println("Hex value " + sb6);
		}
		switch (sb7) {
		case 0:
			sb7 = 0xf0000000;
			break;
		case 268435456:
			sb7 = 0x00000000;
			break;
		case 536870912:
			sb7 = 0x50000000;
			break;
		case 805306368:
			sb7 = 0x10000000;
			break;
		case 1073741824:
			sb7 = 0x20000000;
			break;
		case 1342177280:
			sb7 = 0x30000000;
			break;
		case 1610612736:
			sb7 = 0xe0000000;
			break;
		case 1879048192:
			sb7 = 0xd0000000;
			break;
		case -2147483648:
			sb7 = 0x6000000;
			break;
		case -1879048192:
			sb7 = 0x70000000;
			break;
		case -1610612736:
			sb7 = 0x40000000;
			break;
		case -1342177280:
			sb7 = 0x80000000;
			break;
		case -1073741824:
			sb7 = 0x90000000;
			break;
		case -805306368:
			sb7 = 0xa0000000;
			break;
		case -536870912:
			sb7 = 0xb0000000;
			break;
		case -268435456:
			sb7 = 0xc0000000;
			break;
		
		default:
			System.out.println("ERROR 4 bit Hex value not Possible");
			System.out.println("Hex value " + sb7);
		}
		*/
		return s;
	}

	// sbox decrypt
	public int sBox(int c) {
		int s = c;
		int sb0, sb1, sb2, sb3, sb4, sb5, sb6, sb7;
		sb0 = s & 0x0000000f;
		sb1 = s & 0x000000f0;
		sb2 = s & 0x00000f00;
		sb3 = s & 0x0000f000;
		sb4 = s & 0x000f0000;
		sb5 = s & 0x00f00000;
		sb6 = s & 0x0f000000;
		sb7 = s & 0xf0000000;
		// Sbox 0
		switch (sb0) {
		case 0:
			sb0 = 0x0000000f;
			break;
		case 1:
			sb0 = 0x0000000c;
			break;
		case 2:
			sb0 = 0x00000001;
			break;
		case 3:
			sb0 = 0x00000005;
			break;
		case 4:
			sb0 = 0x00000000;
			break;
		case 5:
			sb0 = 0x00000002;
			break;
		case 6:
			sb0 = 0x00000003;
			break;
		case 7:
			sb0 = 0x00000007;
			break;
		case 8:
			sb0 = 0x00000004;
			break;
		case 9:
			sb0 = 0x0000000b;
			break;
		case 10:
			sb0 = 0x0000000a;
			break;
		case 11:
			sb0 = 0x00000006;
			break;
		case 12:
			sb0 = 0x00000008;
			break;
		case 13:
			sb0 = 0x0000000d;
			break;
		case 14:
			sb0 = 0x00000009;
		case 15:
			sb0 = 0x0000000e;
			break;
		default:
			System.out.println("ERROR 4 bit Hex value not Possible");
			System.out.println("Hex value " + sb0);
		}
		// Sbox 1
		switch (sb1) {
		case 0:
			sb1 = 0x00000030;
			break;
		case 16:
			sb1 = 0x000000c0;
			break;
		case 32:
			sb1 = 0x00000060;
			break;
		case 48:
			sb1 = 0x00000010;
			break;
		case 64:
			sb1 = 0x000000a0;
			break;
		case 80:
			sb1 = 0x000000b0;
			break;
		case 96:
			sb1 = 0x000000d0;
			break;
		case 112:
			sb1 = 0x000000e0;
			break;
		case 128:
			sb1 = 0x00000090;
			break;
		case 144:
			sb1 = 0x00000070;
			break;
		case 160:
			sb1 = 0x00000040;
			break;
		case 176:
			sb1 = 0x000000f0;
			break;
		case 192:
			sb1 = 0x00000000;
			break;
		case 208:
			sb1 = 0x00000050;
			break;
		case 224:
			sb1 = 0x00000080;
			break;
		case 240:
			sb1 = 0x00000020;
			break;
		default:
			System.out.println("ERROR 4 bit Hex value not Possible");
			System.out.println("Hex value " + sb1);
		}
		// sbox 2
		switch (sb2) {
		case 0:
			sb2 = 0x00000c00;
			break;
		case 256:
			sb2 = 0x00000e00;
			break;
		case 512:
			sb2 = 0x00000000;
			break;
		case 768:
			sb2 = 0x00000400;
			break;
		case 1024:
			sb2 = 0x00000d00;
			break;
		case 1280:
			sb2 = 0x00000100;
			break;
		case 1536:
			sb2 = 0x00000600;
			break;
		case 1792:
			sb2 = 0x00000700;
			break;
		case 2048:
			sb2 = 0x00000f00;
			break;
		case 2304:
			sb2 = 0x00000500;
			break;
		case 2560:
			sb2 = 0x00000900;
			break;
		case 2816:
			sb2 = 0x00000800;
			break;
		case 3072:
			sb2 = 0x00000200;
			break;
		case 3328:
			sb2 = 0x00000b00;
			break;
		case 3584:
			sb2 = 0x00000a00;
			break;
		case 3840:
			sb2 = 0x00000300;
			break;
		default:
			System.out.println("ERROR 4 bit Hex value not Possible");
			System.out.println("Hex value " + sb2);
		}
		// sbox 3
		switch (sb3) {
		case 0:
			sb3 = 0x00004000;
			break;
		case 4096:
			sb3 = 0x00005000;
			break;
		case 8192:
			sb3 = 0x00007000;
			break;
		case 12288:
			sb3 = 0x0000f000;
			break;
		case 16384:
			sb3 = 0x00006000;
			break;
		case 20480:
			sb3 = 0x00000000;
			break;
		case 24576:
			sb3 = 0x00008000;
			break;
		case 28672:
			sb3 = 0x00009000;
			break;
		case 32768:
			sb3 = 0x0000b000;
			break;
		case 36864:
			sb3 = 0x0000d000;
			break;
		case 40960:
			sb3 = 0x00002000;
			break;
		case 45056:
			sb3 = 0x00003000;
			break;
		case 49152:
			sb3 = 0x0000a000;
			break;
		case 53248:
			sb3 = 0x0000e000;
			break;
		case 57344:
			sb3 = 0x00001000;
			break;
		case 61440:
			sb3 = 0x00002000;
			break;
		default:
			System.out.println("ERROR 4 bit Hex value not Possible");
			System.out.println("Hex value " + sb3);
		}
		/*
		// sbox4
		switch (sb4) {
		case 0:
			sb4 = 0x00030000;
			break;
		case 65536:
			sb4 = 0x000c0000;
			break;
		case 131072:
			sb4 = 0x000e0000;
			break;
		case 196608:
			sb4 = 0x00050000;
			break;
		case 262144:
			sb4 = 0x00020000;
			break;
		case 327680:
			sb4 = 0x000b0000;
			break;
		case 393216:
			sb4 = 0x000d0000;
			break;
		case 458752:
			sb4 = 0x00070000;
			break;
		case 524288:
			sb4 = 0x00010000;
			break;
		case 589824:
			sb4 = 0x00000000;
			break;
		case 655360:
			sb4 = 0x00080000;
			break;
		case 720896:
			sb4 = 0x00060000;
			break;
		case 786432:
			sb4 = 0x000f0000;
			break;
		case 851968:
			sb4 = 0x000a0000;
			break;
		case 917504:
			sb4 = 0x00090000;
			break;
		case 983040:
			sb4 = 0x00040000;
			break;
		default:
			System.out.println("ERROR 4 bit Hex value not Possible");
			System.out.println("Hex value " + sb4);
		}
		// sbox 5
		switch (sb5) {
		case 0:
			sb5 = 0x00800000;
			break;
		case 1048576:
			sb5 = 0x00900000;
			break;
		case 2097152:
			sb5 = 0x00a00000;
			break;
		case 3145728:
			sb5 = 0x00b00000;
			break;
		case 4194304:
			sb5 = 0x00600000;
			break;
		case 5242880:
			sb5 = 0x00700000;
			break;
		case 6291456:
			sb5 = 0x00c00000;
			break;
		case 7340032:
			sb5 = 0x00d00000;
			break;
		case 8388608:
			sb5 = 0x00100000;
			break;
		case 9437184:
			sb5 = 0x00200000;
			break;
		case 10485760:
			sb5 = 0x00400000;
			break;
		case 11534336:
			sb5 = 0x00d00000;
			break;
		case 12582912:
			sb5 = 0x00000000;
			break;
		case 13631488:
			sb5 = 0x00300000;
			break;
		case 14680064:
			sb5 = 0x00500000;
			break;
		case 15728640:
			sb5 = 0x00f00000;
			break;
		default:
			System.out.println("ERROR 4 bit Hex value not Possible");
			System.out.println("Hex value " + sb5);
		}
		// sbox 6
		switch (sb6) {
		case 0:
			sb6 = 0x0b000000;
			break;
		case 16777216:
			sb6 = 0x06000000;
			break;
		case 33554432:
			sb6 = 0x05000000;
			break;
		case 50331648:
			sb6 = 0x00000000;
			break;
		case 67108864:
			sb6 = 0x0b000000;
			break;
		case 83886080:
			sb6 = 0x07000000;
			break;
		case 100663296:
			sb6 = 0x04000000;
			break;
		case 117440512:
			sb6 = 0x01000000;
			break;
		case 134217728:
			sb6 = 0x09000000;
			break;
		case 150994944:
			sb6 = 0x08000000;
			break;
		case 167772160:
			sb6 = 0x03000000;
			break;
		case 184549376:
			sb6 = 0x02000000;
			break;
		case 201326592:
			sb6 = 0x0c000000;
			break;
		case 218103808:
			sb6 = 0x0d000000;
			break;
		case 234881024:
			sb6 = 0x0f000000;
			break;
		case 251658240:
			sb6 = 0x0e000000;
			break;
		default:
			System.out.println("ERROR 4 bit Hex value not Possible");
			System.out.println("Hex value " + sb6);
		}
		switch (sb7) {
		case 0:
			sb7 = 0x10000000;
			break;
		case 268435456:
			sb7 = 0x30000000;
			break;
		case 536870912:
			sb7 = 0x40000000;
			break;
		case 805306368:
			sb7 = 0x50000000;
			break;
		case 1073741824:
			sb7 = 0xa0000000;
			break;
		case 1342177280:
			sb7 = 0x20000000;
			break;
		case 1610612736:
			sb7 = 0x80000000;
			break;
		case 1879048192:
			sb7 = 0x90000000;
			break;
		case -2147483648:
			sb7 = 0xb000000;
			break;
		case -1879048192:
			sb7 = 0xc0000000;
			break;
		case -1610612736:
			sb7 = 0xd0000000;
			break;
		case -1342177280:
			sb7 = 0xe0000000;
			break;
		case -1073741824:
			sb7 = 0xf0000000;
			break;
		case -805306368:
			sb7 = 0x70000000;
			break;
		case -536870912:
			sb7 = 0x60000000;
			break;
		case -268435456:
			sb7 = 0x00000000;
			break;
		default:
			System.out.println("ERROR 4 bit Hex value not Possible");
			System.out.println("Hex value " + sb7);
		}
		*/
		return s;
	}
}


