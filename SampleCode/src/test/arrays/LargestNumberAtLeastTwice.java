package test.arrays;


/**
 * 
 * In a given integer array nums, there is always exactly one largest element.

Find whether the largest element in the array is at least twice as much as every other number in the array.

If it is, return the index of the largest element, otherwise return -1.

Example 1:

Input: nums = [3, 6, 1, 0]
Output: 1
Explanation: 6 is the largest integer, and for every other number in the array x,
6 is more than twice as big as x.  The index of value 6 is 1, so we return 1.
 * 
 * 
 * @author Pankaj
 *
 */
public class LargestNumberAtLeastTwice {

	public int dominantIndex(int[] nums) {

		int largest = 0;
		int pos =0;
		int count = 0;
		for(int i:nums) {
			if(i> largest) {
				largest =i;
				pos = count;
			}
			count++;
		}
		boolean result = true;
		for(int i:nums) {
			if(i!= largest) {
				if(largest >= 2*i) {
					continue;
				} else {
					result = false;
					break;
				}
			}
		}
		
		if(result) {
			return pos;
		}
		
		
		return -1;
	}
	
	public int dominantIndexBetter(int[] nums) {
		int max = nums[0];
		int secondMax = 0;
		int index = 0;

		for (int i=1; i<nums.length; i++) {
	        int n = nums[i];
	        if (n > max) {
	            index = i;
	            secondMax = max;
	            max = n;
	        } else if (n > secondMax) {
	            secondMax = n;
	        }
	        
	        
	    }
	    
	    return max >= 2 * secondMax ? index : -1;
	}
	
	
	public static void main(String[] args) {
		int []  nums = {3, 6, 1, 0};
		int []  nums1 =		{0,0,2,3};
		LargestNumberAtLeastTwice l = new LargestNumberAtLeastTwice();
		System.out.println(l.dominantIndexBetter(nums1));
	}
}
