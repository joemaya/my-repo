package test.problem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Three_3_sum {

	public static void findTriplets(int arr[])
	{
	    boolean found = false;
	 
	    // sort array elements
	    Arrays.sort(arr);
	    int n = arr.length;
	    for (int i=0; i<n-1; i++)
	    {
	        // initialize left and right
	        int l = i + 1;
	        int r = n - 1;
	        int x = arr[i];
	        while (l < r)
	        {
	            if (x + arr[l] + arr[r] == 0)
	            {
	                // print elements if it's sum is zero
	                System.out.println("Triplet : {" + x + " , " + arr[l] + " , " + arr[r] + " }");
	                l++;
	                r--;
	                found = true;
	            }
	 
	            // If sum of three elements is less
	            // than zero then increment in left
	            else if (x + arr[l] + arr[r] < 0)
	                l++;
	 
	            // if sum is greater than zero than
	            // decrement in right side
	            else
	                r--;
	        }
	    }
	 
	    if (found == false)
	        System.out.println(" No Triplet Found");
	}
	
	public static List<List<Integer>>  findTripletsList(int arr[])
	{
		List<List<Integer>> list = new ArrayList<List<Integer>>();
	    boolean found = false;
	 
	    // sort array elements
	    Arrays.sort(arr);
	    int n = arr.length;
	    for (int i=0; i<n-1; i++)
	    {
	        // initialize left and right
	        int l = i + 1;
	        int r = n - 1;
	        int x = arr[i];
	        while (l < r)
	        {
	            if (x + arr[l] + arr[r] == 0)
	            {
	            	List<Integer> intlist = new ArrayList<>();
	                // print elements if it's sum is zero
	            	//System.out.println("Triplet : {" + x + " , " + arr[l] + " , " + arr[r] + " }");
	                intlist.add(x);
	                intlist.add(arr[l]);
	                intlist.add(arr[r]);
	                list.add(intlist);
	                l++;
	                r--;
	                found = true;
	            }
	 
	            // If sum of three elements is less
	            // than zero then increment in left
	            else if (x + arr[l] + arr[r] < 0)
	                l++;
	 
	            // if sum is greater than zero than
	            // decrement in right side
	            else
	                r--;
	        }
	    }
	 
	    if (found == false)
	        System.out.println(" No Triplet Found");
	    
	    return list;
	}
	
	public static void main(String[] args) {
		int arr[] = {0, -1, 2, -3, 1};
//	    findTriplets(arr);
	    List<List<Integer>> list = findTripletsList(arr); 
	    System.out.println(list);
	}
}
