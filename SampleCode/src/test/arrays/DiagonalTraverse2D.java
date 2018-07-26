package test.arrays;


/**
 * 
 * Given a matrix of M x N elements (M rows, N columns), return all elements of the matrix in diagonal order as shown in the below image.

Example:
Input:
[
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
]
Output:  [1,2,4,7,5,3,6,8,9]
Explanation:
 * 
 * @author Pankaj
 *
 */
public class DiagonalTraverse2D {
	
	public int[] findDiagonalOrder(int[][] matrix) {
		int row = matrix.length;
		if(row == 0 ) {
			return new int[0];
		}
		int col	= matrix[0].length;
		if(col == 1) {
			int [] result = new int[row];
			for(int i=0;i<row;i++) {
				result[i] =matrix[i][0];
			}
			return result;
			
		}
		int [] result = new int[row*col];
		int x=0;
		int y=1;
		result[0] = matrix[0][0];
		boolean up = false ;
		
		for (int i=1;i<row*col;i++) {
			result[i] = matrix[x][y];
			if(!up) {
				if(y>0 && x != row-1) {
					x++;
					y--;
				} else {
					if(y ==0 || x == row-1) {
						up = !up;
					}
					if(x < row-1) {
						x++;
					} else if( x == row-1) {
						y++;
					}
				}
			} else {
				if(x>0 && y != col-1) {
					x--;
					y++;
				} else {
					if(x ==0 ||  y == col-1) {
						up = !up;
					}
					if(y < col-1) {
						y++;
					} else if( y == col-1) {
						x++;
					}
				}
			}
		}
			
		return result;
    }
	
	public void printsize(int[][] matrix) {
		int rows = matrix.length;
		int col	= matrix[0].length;
		System.out.println("rows " + rows + " columns : " + col);
	}
	
	public void print(int[] matrix) {
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
		
		DiagonalTraverse2D dt = new DiagonalTraverse2D();
		dt.print(dt.findDiagonalOrder(input4));
	}
}
