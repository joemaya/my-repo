package test.problem;

public class TwoSum {

	public int[] twoSum(int[] nums, int target) {
        int [] emptyarray = new int[0];
        int [] resultarray = new int[2];
        
        if(nums != null && nums.length > 0) {
            for(int i=0;i<nums.length;i++) {
            	int num = nums[i];
            	int value = target - num;
            		for(int j=i+1;j<nums.length;j++) {
            			if(nums[j] == value) {
            				resultarray[0] = i;
            				resultarray[1] = j;
            				return resultarray;
            			}
            		}
            }
        }
        return emptyarray;
    }
}
