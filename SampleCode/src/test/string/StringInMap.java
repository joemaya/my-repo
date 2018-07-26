package test.string;

import java.util.HashMap;
import java.util.Map;

public class StringInMap {

	public static void main(String[] args) {
		Map<String, String> map = new HashMap<>();
		String s = "Reema";
		String s1 = new String("Reema");
		map.put(s, "Pankaj");
		map.put(s1, "Cutiepie");
		
		System.out.println(map.values());
	}
}
