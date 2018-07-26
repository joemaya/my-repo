package test.problem;

public class OverlappingRectangle {
	
	public static class Rectangle {
		
		public Rectangle(int x1, int y1, int x2, int y2) {
			this.setX1(x1);
			this.setX2(x2);
			this.setY1(y1);
			this.setY2(y2);
		}
		
		public int getX1() {
			return x1;
		}
		public void setX1(int x1) {
			this.x1 = x1;
		}

		public int getY1() {
			return y1;
		}

		public void setY1(int y1) {
			this.y1 = y1;
		}

		public int getX2() {
			return x2;
		}

		public void setX2(int x2) {
			this.x2 = x2;
		}

		public int getY2() {
			return y2;
		}

		public void setY2(int y2) {
			this.y2 = y2;
		}

		private int x1;
		private int y1;
		private int x2;
		private int y2;
	}

	private Rectangle r1;
	private Rectangle r2;
	
	public boolean isInBetween(int x1, int x2, int x3) {
		
		if(x1 < x2 && x2 < x3) {
			return true;
		} else 
			return false;
	}
	
	public boolean isOverLapping(Rectangle r1, Rectangle r2) {
		
		if(isInBetween(r1.getX1(), r2.getX1(), r1.getX2())) {
			if(isInBetween(r1.getY1(), r2.getY1(), r1.getY2()) || isInBetween(r1.getY1(), r2.getY2(), r1.getY2())) {
				
			}
		}
		return false;
	}
	
	
	/**
	 * Calculate the overlapping area of two rectangles.
	 */
	public int overlapArea(int A, int B, int C, int D,
	     			        int E, int F, int G, int H) {
		/* Check if there is indeed an overlap.
	     * e.g.  E >= C  i.e. the most left point of the rectangle (EFGH) is 
	     *       on the right side of the most right point of the rectangle (ABCD),
	     *       therefore there is no overlapping.
	     */
		if ( (E>=C) || (F>= D) || (A>=G) || (B >= H) )
			return 0;

		/* bottom left point of the overlapping area. */
		int bl_x = Math.max(A, E);
		int bl_y = Math.max(B, F);

		/* top right point of the overlapping area. */
		int tr_x = Math.min(C, G);
		int tr_y = Math.min(D, H);
		
		return (tr_x - bl_x) * (tr_y - bl_y);
	}

	/**
	 * Calculate the area of a single rectangle.
	 */
	public int computeArea(int A, int B, int C, int D) {
		return (C-A) * (D-B);
	}

	/**
	 * Find the total area covered by two rectilinear rectangles in a 2D plane.
	 * Each rectangle is defined by its bottom left corner and top right corner.
	 */
	public int computeArea(int A, int B, int C, int D,
						   int E, int F, int G, int H) {
		// The addition of area of the two rectangles minus the overlapping area.
		return computeArea(A, B, C, D) + computeArea(E, F, G, H) - 
			   overlapArea(A, B, C, D, E, F, G, H);
	}
	
	/**
	 * Calculate the overlapping area of two rectangles.
	 */
	public Rectangle getOverlappingRectangle(Rectangle r1, Rectangle r2) {
		
		
		int A = r1.getX1();
		int B = r1.getY1();
		int C = r1.getX2();
		int D = r1.getY2();
        int E = r2.getX1();
        int F = r2.getY1(); 
        int G = r2.getX2();
        int H = r2.getY2();
        
		/* Check if there is indeed an overlap.
	     * e.g.  E >= C  i.e. the most left point of the rectangle (EFGH) is 
	     *       on the right side of the most right point of the rectangle (ABCD),
	     *       therefore there is no overlapping.
	     */
		if ( (E>=C) || (F>= D) || (A>=G) || (B >= H) )
			return null;

		/* bottom left point of the overlapping area. */
		int bl_x = Math.max(A, E);
		int bl_y = Math.max(B, F);

		/* top right point of the overlapping area. */
		int tr_x = Math.min(C, G);
		int tr_y = Math.min(D, H);
		
		Rectangle i1 = new Rectangle(bl_x, bl_y, tr_x, tr_y);
		
		return i1;
		
	}
	
	public int getArea(Rectangle i1) {
		return (i1.getX2() -i1.getX1())*(i1.getY2() -i1.getY1());
	}
	
	public static void main(String[] args) {
		OverlappingRectangle or = new  OverlappingRectangle();
		Rectangle r1 = new Rectangle(1,4,4,6);
		Rectangle r2 = new Rectangle(3,3,6,6);
		Rectangle r3 = new Rectangle(2,2,5,5);
		
		int a12 = 0;
		int a13 = 0;
		int a23 = 0;
		int a123 = 0;
		
		int c1 = 1;
		int c2 = 2;
		int c3 = 3;
		
		Rectangle i1 = or.getOverlappingRectangle(r1, r2);
		if(i1 != null) {
			a12 = or.getArea(i1);
			System.out.println("area = " + a12);
		}
		
		Rectangle i2 = or.getOverlappingRectangle(r1, r3);
		if(i2 != null) {
			a13 = or.getArea(i2);
			System.out.println("area = " + a13);
		}
		
		Rectangle i3 = or.getOverlappingRectangle(r2, r3);
		if(i3 != null) {
			a23 = or.getArea(i3);
			System.out.println("area = " + a23);
		}
		
		if(i1 != null && i2 != null && i3 != null ) {
			int x1 = Math.max(Math.max(i1.getX1(), i2.getX1()), i3.getX1());
			int y1 = Math.max(Math.max(i1.getY1(), i2.getY1()), i3.getY1());
			
			int x2 = Math.min(Math.min(i1.getX2(), i2.getX2()), i3.getX2());
			int y2 = Math.min(Math.min(i1.getY2(), i2.getY2()), i3.getY2());
			
			//final overlapping rectangle:
			Rectangle o1 = new Rectangle(x1, y1, x2, y2);
			a123 = or.getArea(o1);
			System.out.println("overlapping area : " + a123);
		}
		
		int cost1 = (a12 + a13 - a123)*c1;
		int cost2 = (a12 + a23 - a123)*c2;
		int cost3 = (a23 + a13 - a123)*c3;
		
		int finalCompensation = cost1 + cost2 + cost3;
		
		System.out.println("compensation : " + finalCompensation);
		
		//System.out.println("Total Area : " + or.computeArea(r1.getX1(), r1.getY1(), r1.getX2(), r1.getY2(), r2.getX1(), r2.getY1(), r2.getX2(), r2.getY2()));
		System.out.println("Overlapping Area : " + or.overlapArea(r1.getX1(), r1.getY1(), r1.getX2(), r1.getY2(), r2.getX1(), r2.getY1(), r2.getX2(), r2.getY2()));
		
	}
	
	
}
