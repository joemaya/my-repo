package test.string;

/**
 * 
 * 
 * Write a function to find the longest common prefix string amongst an array of
 * strings.
 * 
 * If there is no common prefix, return an empty string "".
 * 
 * Example 1:
 * 
 * Input: ["flower","flow","flight"] Output: "fl" Example 2:
 * 
 * Input: ["dog","racecar","car"] Output: "" Explanation: There is no common
 * prefix among the input strings. Note:
 * 
 * All given inputs are in lowercase letters a-z.
 * 
 * @author Pankaj
 *
 */
public class LongestCommonPrefix {

	public String longestCommonPrefix(String[] strs) {
		if (strs != null && strs.length > 0) {
			String shortest = strs[0];
			int pos = 0;
			for (int i = 0; i < strs.length; i++) {
				String str = strs[i];
				if (!str.equals(shortest)) {
					if (str.length() < shortest.length()) {
						shortest = str;
						pos = i;
					}
				}
			}

			System.out.println("Shortest string :" + shortest);

			int count = shortest.length();
			int i = 0, j = 0;

			while (j < count) {
				if (strs[i].charAt(j) != shortest.charAt(j)) {

					break;
				}

				if (i == strs.length - 1) {
					j++;
					i = 0;
				} else {
					i++;
				}
			}
			if (j == 0) {
				System.out.println("No match found");
				return "";
			} else {
				String match = "";
				match = shortest.substring(0, j);
//				if(i == 0) {
//					match = shortest.substring(0, j-1);
//				} else  {
//					match = shortest.substring(0, j);
//				}
				
				System.out.println("Longest match : " + match);
				return match;
			}

		}
		return "";
	}
	
	public String longestCommonPrefix1(String[] strs) {
	    if (strs.length == 0) return "";
	    String prefix = strs[0];
	    for (int i = 1; i < strs.length; i++)
	        while (strs[i].indexOf(prefix) == -1) {
	            prefix = prefix.substring(0, prefix.length() - 1);
	            if (prefix.isEmpty()) return "";
	        }        
	    return prefix;
	}

	public static void main(String[] args) {
		LongestCommonPrefix prefix = new LongestCommonPrefix();
		String[] strs = { "flower", "flow", "flight" };
		String strs1[] = { "geeksforgeeks", "geeks", "geek", "geezer" };
		String strs2 [] = {"aac","ab"};
		System.out.println(prefix.longestCommonPrefix1(strs1));

	}
}
