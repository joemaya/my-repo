package test.sort;

public class InsertionSort {

	/**
	 * sorts in ascending order
	 * 
	 * @param a
	 * @return
	 */
	public int [] sort (int arr[]) {
		
		int i, key, j;
		int n = arr.length;
		   for (i = 1; i < n; i++)
		   {
		       key = arr[i];
		       j = i-1;
		 
		       /* Move elements of arr[0..i-1], that are
		          greater than key, to one position ahead
		          of their current position */
		       while (j >= 0 && arr[j] > key)
		       {
		           arr[j+1] = arr[j];
		           j = j-1;
		       }
		       arr[j+1] = key;
		   }
		   return arr;
	}
	

    /* A utility function to print array of size n*/
    static void printArray(int arr[])
    {
        int n = arr.length;
        for (int i=0; i<n; ++i)
            System.out.print(arr[i] + " ");
 
        System.out.println();
    }
 
    // Driver method
    public static void main(String args[])
    {        
        int arr[] = {12, 11, 13, 5, 6};
 
        InsertionSort ob = new InsertionSort();        
        arr = ob.sort(arr);
         
        printArray(arr);
    }
}
