package test.string;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SubstringInString {
	
	public static final String str = "phelloslkhellodjladfjhelloppphellohelloa";
	public static final String findStr = "hello";
	public static final String strWithSpecialChars = "hel+loslkhel+lodjladfjhel+lo";
	public static final String findStrWithSpecialChars = "hel+lo";
	
	// OC = Overlapping characters 
	public static final String strOC = "2222";
 	public static final String findStrOC = "22";
	public static void main(String[] args) {
		
		int numberOfOccurences = 0; 
		
//		numberOfOccurences = bruteForce();
//		numberOfOccurences = intelligent();
//		numberOfOccurences = anotherWay();
//		numberOfOccurences = oneMoreIntelligentWay();
//		numberOfOccurences = regex();
//		numberOfOccurences = regexWithSpecialChars();
		numberOfOccurences = forOverLappingChars();
		
		System.out.println("number of occurences : " + numberOfOccurences);
	}

	private static int forOverLappingChars() {
		Pattern pattern = Pattern.compile(Pattern.quote(findStrOC));
		Matcher m = pattern.matcher(strOC);
		int count = 0;
		int pos =0;
		while(m.find(pos)) {
			count++;
			pos = m.start()+1;
		}
		return count;	
	}

	private static int regexWithSpecialChars() {
		Pattern pattern = Pattern.compile(Pattern.quote(findStrWithSpecialChars));
		Matcher m = pattern.matcher(strWithSpecialChars);
		int count = 0;
		while(m.find())
			count++;
		return count;
	}

	private static int regex() {
		Pattern pattern = Pattern.compile(findStr);
		Matcher m = pattern.matcher(str);
		int count = 0;
		while(m.find())
			count++;
		return count;
	}

	/**
	 * Splits this string around matches of the given regular expression.
The array returned by this method contains each substring of this string that is terminated by another substring that matches the given expression or is terminated by the end of the string. The substrings in the array are in the order in which they occur in this string. If the expression does not match any part of the input then the resulting array has just one element, namely this string.

When there is a positive-width match at the beginning of this string then an empty leading substring is included at the beginning of the resulting array. A zero-width match at the beginning however never produces such empty leading substring.

The limit parameter controls the number of times the pattern is applied and therefore affects the length of the resulting array. If the limit n is greater than zero then the pattern will be applied at most n - 1 times, the array's length will be no greater than n, and the array's last entry will contain all input beyond the last matched delimiter. If n is non-positive then the pattern will be applied as many times as possible and the array can have any length. If n is zero then the pattern will be applied as many times as possible, the array can have any length, and trailing empty strings will be discarded.
The string "boo:and:foo", for example, yields the following results with these parameters:

Regex	Limit	Result
:	2	{ "boo", "and:foo" }}
:	5	{ "boo", "and", "foo" }}
:	-2	{ "boo", "and", "foo" }}
o	5	{ "b", "", ":and:f", "", "" }}
o	-2	{ "b", "", ":and:f", "", "" }}
o	0	{ "b", "", ":and:f" }}
An invocation of this method of the form str.split(regex, n) yields the same result as the expression

java.util.regex.Pattern.compile(regex).split(str, n)

	 * @return
	 */
	private static int oneMoreIntelligentWay() {
		String [] subs = str.split(findStr, -1);
		int count = subs.length -1;
		return count;
	}

	private static int anotherWay() {
		int start = 0;
		int count = 0;
		int index = 0;
		while(index != -1) {
			index = str.indexOf(findStr, start);
			if(index != -1) {
				count++;
				start = index + findStr.length();
			}
		}
		return count;
	}

	private static int intelligent() {
		String newStr = str.replace(findStr, "");
		int differenceInLength = str.length() - newStr.length();
		int numberOfReplacements = differenceInLength/findStr.length();
		return numberOfReplacements;
	}

	private static int bruteForce() {
		int length = str.length() ;
		int counter = 0;
		int num = 0;
		int i=0;
		while (length > 0) {
			
			
			if(str.charAt(i) == findStr.charAt(counter)) {
				counter++;
			} else {
				counter =0;
			}
			if(counter == findStr.length()) {
				counter =0;
				num++;
			}
			i++;
			length--;
			if(length ==0) {
				break;
			}
		}
		return num;
	}
}
