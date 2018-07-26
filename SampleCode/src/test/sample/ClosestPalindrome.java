package test.sample;

import java.io.BufferedInputStream;
import java.util.Arrays;
import java.util.Scanner;

/**
 * 
 * Given a number N. our task is to find the closest Palindrome number whose absolute difference with given number is minimum.
 * 
 * 
 * 
 * 
 * solution :
 * 
 * Suppose, we have N digits. Let's find prefix of length N / 2. After that, you can construct 3 possible answers using 2 nearest prefixes, and find the nearest palindrome (you should think about corner cases here).

Example:

Number 123456789 has 1234 prefix of length 9/2 = 4. Nearest prefixes: 1233, 1235.

Then we just construct 3 candidates: 123353321, 123454321 and 123555321

abs(123456789 - 123353321) = 103468
abs(123456789 - 123454321) = 2468
abs(123456789 - 123555321) = 98532
So the nearest palindrom will be 123454321.

UPD: You have to try all possible middle digits also in this case.
 * @author Pankaj
 *
 */
public class ClosestPalindrome {

	public Long getNearestPalindrome1(Long n) {

		String number = String.valueOf(n);
		int length = number.length();
		
		if(length == 1 ) {
			return n;
		}
		
		int middle = length/2;
		
		for(int i = number.length()-1;i>=middle;i--) {
			number = number.replace(number.charAt(i), number.charAt(number.length()-1 -i));
		}
		return new Long(number);
	}
	
	
	public Long getNearestPalindrome(Long n) {

		String number = String.valueOf(n);
		int length = number.length();
		
		if(length == 1 ) {
			return n;
		}
		
		int middle = length/2;
		
		String firstHalf = "";
		
		if(middle%2==0) {
			firstHalf = number.substring(0, middle);
			
			Long firstHalfNum = new Long(firstHalf);
			Long firstHalfUpperNum = firstHalfNum+1;
			Long firstHalfLowerNum = firstHalfNum-1;
			
			firstHalf = String.valueOf(firstHalfNum) + new StringBuilder(firstHalf).reverse();
			String firstHalfUpper = String.valueOf(firstHalfUpperNum);
			firstHalfUpper = firstHalfUpper + new StringBuilder(firstHalfUpper).reverse();
			String firstHalfLower = String.valueOf(firstHalfLowerNum);
			firstHalfLower = firstHalfLower + new StringBuilder(firstHalfLower).reverse();
			
			firstHalfNum = Long.valueOf(firstHalf);
			firstHalfUpperNum = Long.valueOf(firstHalfUpper);
			firstHalfLowerNum = Long.valueOf(firstHalfLowerNum);
			
			Long diff1 = Math.abs(n - firstHalfNum);
			Long diff2 = Math.abs(n - firstHalfUpperNum);
			Long diff3 = Math.abs(n - firstHalfLowerNum);
			Long [] diff = new Long[] {diff1, diff2, diff3};
			Arrays.sort(diff);
			return isPalindrome(Math.abs(n-diff[0])) ? Math.abs(n-diff[0]) : Math.abs(n+diff[0]);
		} else {
			String mid = number.substring(middle, middle+1);
			
			Long midnum = new Long(mid);
			Long midUpperNum = midnum+1;
			Long midLowerNum = midnum-1;
			firstHalf = number.substring(0, middle);
			
			String rev = new StringBuilder(firstHalf).reverse().toString();
			
			String upper = firstHalf + midUpperNum + rev;
			String lower = firstHalf + midLowerNum + rev;
			String org = firstHalf + midnum + rev;
			
			
			
			midnum = Long.valueOf(org);
			midUpperNum = Long.valueOf(upper);
			midLowerNum = Long.valueOf(lower);
			
			Long diff1 = Math.abs(n - midnum);
			Long diff2 = Math.abs(n - midUpperNum);
			Long diff3 = Math.abs(n - midLowerNum);
			Long [] diff = new Long[] {diff1, diff2, diff3};
			Arrays.sort(diff);
			return isPalindrome(Math.abs(n-diff[0])) ? Math.abs(n-diff[0]) : Math.abs(n+diff[0]);
		}
		
		
	}
	
	
	public boolean isPalindrome(Long n) {
		String number = String.valueOf(n);
		for (int i=0;i<number.length()/2;i++) {
			if(number.charAt(i) != number.charAt(number.length() -i -1)) {
				return false;
			}
		}
		return true;
	}
	
	public static void main(String[] args) {
		ClosestPalindrome cp = new ClosestPalindrome();
		
		Scanner sc = new Scanner(new BufferedInputStream(System.in));
		int testCases = sc.nextInt();
		Long [] input = new Long[testCases];
		System.out.print("");
		for(int i=0;i<testCases;i++) {
			 input[i] = sc.nextLong();
		}
		for(int i=0;i<testCases;i++) {
			System.out.println(cp.getNearestPalindrome(input[i]));		
		}
	}
}
