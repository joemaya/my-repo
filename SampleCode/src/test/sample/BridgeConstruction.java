package test.sample;

import java.io.BufferedInputStream;
import java.util.Arrays;
import java.util.Scanner;

public class BridgeConstruction {

	
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(new BufferedInputStream(System.in));
		int testCases = sc.nextInt();
		int [] input = new int[testCases];
		System.out.print("");
		for(int i=0;i<testCases;i++) {
			 input[i] = sc.nextInt();
		}
		int [] output = getJoinedPipes(input);
		for(int i=0;i<output.length;i++) {
			System.out.println(output[i]);
		}		
	}

	private static int[] getJoinedPipes(int[] input1) {
		if (input1 != null) {
			if (input1.length == 1) {
				return new int[] { 0 };
			}
			Arrays.sort(input1);
			int[] output = new int[input1.length - 1];
			int sum = input1[0];
			for (int i = 1; i < input1.length; i++) {
				sum = sum + input1[i];
				output[i - 1] = sum;
			}
			return output;
		}
		return null;

	}
}
