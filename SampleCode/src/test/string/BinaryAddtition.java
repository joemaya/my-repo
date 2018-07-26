package test.string;

public class BinaryAddtition {

	public String binarySum (String a, String b) {
		if(a==null && b==null) {
			return null;
		}
		
		if(a.length() ==0 && b.length() ==0) {
			return "";
		}
		if((a==null || a.length() ==0) && b!=null) {
			return b;
		}
		if(a!=null && (b==null || a.length() ==0)) {
			return a;
		}
		
		int count = (a.length() > b.length()) ? a.length():b.length();
		int carry =0;
		int sum =0;
		StringBuilder sb= new StringBuilder();
		for(int i=a.length()-1,j=b.length()-1; count >0;i--,j--, count--) {
			int p =0,q=0;
			if(i>=0) {
				p = Integer.parseInt(a.substring(i, i+1)); 
			}
			if(j>=0) {
				q = Integer.parseInt(b.substring(j, j+1));
			}
			sum = p + q + carry;
			if (sum ==2) {
				sum =0; 
				carry = 1;
			} else if (sum ==3) {
				sum =1; 
				carry = 1;
			} else {
				carry = 0;
			}
			sb.append(sum);
		}
		if(carry != 0) {
			sb.append(carry);
		}
		StringBuilder result = new StringBuilder();

		for(int i =sb.length()-1;i>=0;i--) {
			result.append(sb.charAt(i));
		}
		
		return result.toString();
	}
	public static void main(String[] args) {
		BinaryAddtition ba = new BinaryAddtition();
		String i1 = new String("1111");
		String i2 = new String("1111");
		System.out.println(ba.binarySum(i1, i2));
	}
}
