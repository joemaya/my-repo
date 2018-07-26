package test.arrays;

import java.util.ArrayList;
import java.util.List;

public class SpiralMatrix {

	public List<Integer> spiralOrder(int[][] matrix) {

		List<Integer> list = new ArrayList<>();
		int row = matrix.length;
		if(row == 0 ) {
			return list;
		}
		int col	= matrix[0].length;
		if(col == 1) {
			for(int i=0;i<row;i++) {
				list.add(matrix[i][0]);
			}
			return list;
			
		}
		int x=0;
		int y=0;
		int right=row-1;
		int left=0;
		int upper=0;
		int lower=col-1;
		
		boolean up = false ;
		list.add(matrix[x][y]);
		for (int i=1;i<row*col;i++) {
			if (x > left && x < right) {
				x++;
			}
		}
			
		return list;
    
    }
	
	public void print(List<Integer> matrix) {
		System.out.print("[");
		for(int i : matrix) {
			System.out.print(i + " ");
		}
		System.out.print("]");
	}
	
	public static void main(String[] args) {
		int [][] input1 = {
				{ 1, 2, 3 },
				{ 4, 5, 6 },
				{ 7, 8, 9 }
				};
		
		int [][] input2 = {
				{ 1, 2, 3, 4},
				{ 5, 6 ,7, 8},
				{ 9, 10, 11, 12 },
				{ 13, 14, 15, 16 }
				};
		int [][] input3 = {{3},{4}};
		int [][] input4 = {{2,3,4},{5,6,7},{8,9,10},{11,12,13},{14,15,16}}; 
		SpiralMatrix sp = new SpiralMatrix();
		
		sp.print(sp.spiralOrder(input1));
	}
}
