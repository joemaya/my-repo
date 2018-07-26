package test.sample;

import java.io.CharArrayReader;
import java.io.IOException;

public class Chararrayinput {

	public static void main(String[] args) {
		String obj = "ijklmn";
		int len = obj.length();
		char [] x = new char[len];
		
		CharArrayReader inp1 = new CharArrayReader(x);
		CharArrayReader inp2 = new CharArrayReader(x, 0 , 3);
		
		int m;
		try {
			while((m = inp1.read()) !=  -1) {
				System.out.println((char)m);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
