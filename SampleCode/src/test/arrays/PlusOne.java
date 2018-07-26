package test.arrays;

/**
 * 
 * 
 * Given a non-empty array of digits representing a non-negative integer, plus one to the integer.

The digits are stored such that the most significant digit is at the head of the list, and each element in the array contain a single digit.

You may assume the integer does not contain any leading zero, except the number 0 itself.

Example 1:

Input: [1,2,3]
Output: [1,2,4]
Explanation: The array represents the integer 123.

Input:
[9]
Output:
[10]
 * 
 * @author Pankaj
 *
 */
public class PlusOne {

	public int[] plusOne(int[] digits) {
		int [] result = new int [digits.length+1];
		int sum = 0;
		int carry = 0;
		int count = 0;
		
		
		for (int i=digits.length -1; i>=0;i--) {
        	
        	if(count == 0) {
        		sum = digits[i]+1;	
        	}else {
        		sum = digits[i] + carry;
        		carry =0;
        	}
        	
        	if(sum>9) {
        		sum=0;
        		carry=1;
        	}
        	result[digits.length - count] = sum;
        	
        	count++;
        	if(count == digits.length) {
        		result[0]=carry;
        	}
        }
		if(result[0] ==0 ) {
			int [] result1 = new int [digits.length];
			for(int i=0;i<digits.length;i++) {
				result1[i] = result[i+1];
			}
			return result1;
		}
        return result;
		
		
    }
	
	public static void main(String[] args) {
		PlusOne p = new PlusOne();
		int [] digits = {4,3,2,9};
		int [] digits1 = {9};
		int [] digits2 = {9,9,9,9};
		int [] digits3 = {4,3,2,1};
		int [] result = p.plusOne(digits3);
		for(int i : result) {
			System.out.print(i);
		}
		System.out.println();
	}
}
