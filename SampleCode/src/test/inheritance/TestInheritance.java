package test.inheritance;

public class TestInheritance {

	public static void main(String[] args) {
		Y obj = new Y();
		obj.m = 1;
		obj.n = 2;
		
		X r;
		r = obj;
		r.display();
	}
}
