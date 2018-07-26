package test.problem;

public class SampleCode {

		private String a;
		private int b;
		public String getA() {
			return a;
		}
		public void setA(String a) {
			this.a = a;
		}
		public int getB() {
			return b;
		}
		public void setB(int b) {
			this.b = b;
		}
	public static void main(String[] args) {
		SampleCode sc = new SampleCode();
		sc.setA("Test");
		sc.setB(20);
		
		System.out.println("sc - hashchode- " + sc.hashCode());
		System.out.println("sc - getClass().getName()- " + sc.getClass().getName());
		System.out.println("sc - getClass().getSimpleName()- " + sc.getClass().getSimpleName());
		System.out.println("sc - getClass().getCanonicalName()- " + sc.getClass().getCanonicalName());
		System.out.println("sc - Integer.toHexString(hashchode) - " + Integer.toHexString(sc.hashCode()));
		System.out.println("sc - toString - " + sc.toString());
		
	}
}
