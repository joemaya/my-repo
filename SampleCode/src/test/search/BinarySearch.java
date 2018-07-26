package test.search;

public class BinarySearch {

	private int n;
	
	public int binarySearch(int a[], int low, int high) {
		int sum = low+high;
		int mid = 0;
		if(sum%2 ==0) {
			mid = (low+high)/2;
		} else {
			mid = (low+high-1)/2;
		}
		
		if(a[mid] == n) {
			return mid;
		} else if(a[mid] > n) {
			return binarySearch(a, low, mid);
		} else if(a[mid] < n) {
			return binarySearch(a, mid, high);
		}
		return -1;
	}
	
	public static void main(String[] args) {
		int []a = {1,2,3,4,5,6,7,8};
		BinarySearch bs = new BinarySearch();
		bs.setN(7);
		System.out.println(bs.binarySearch(a, 0, a.length-1)+1);
	}

	public int getN() {
		return n;
	}

	public void setN(int n) {
		this.n = n;
	}
}
