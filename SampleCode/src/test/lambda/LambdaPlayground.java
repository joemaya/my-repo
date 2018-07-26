package test.lambda;

public class LambdaPlayground {

	interface MyString {
		String myStringFunction(String str);
	}
	
	interface MyGeneric<T> {
		T compute(T t);
	}

	public static void main (String args[]) {
//		stringReverse();
		generic();
	}

	private static void generic() {
		String var = "test string";
		
		MyGeneric<String> reverseString = (str) -> {
			String result = "";
			
			for(int i = str.length()-1; i >= 0; i--)
				result += str.charAt(i);
			return result;
		};
		
		MyGeneric<Integer> factorial = (a) -> {
			Integer result = 1;
			
			for(int i = 1; i <= a; i++)
				result *= i;
			return result;
		};
		
		System.out.println(factorial.compute(5));
		System.out.println(reverseString.compute("Pankaj"));
	}

	private static void stringReverse() {
		// Block lambda to reverse string
		MyString reverseStr = (str) -> {
			String result = "";
			
			for(int i = str.length()-1; i >= 0; i--)
				result += str.charAt(i);
			
			return result;
		};

		// Output: omeD adbmaL
		System.out.println(reverseStr.myStringFunction("Lambda Demo"));
	}
	
}
