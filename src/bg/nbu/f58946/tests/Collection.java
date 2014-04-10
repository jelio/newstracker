package bg.nbu.f58946.tests;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import bg.nbu.f58946.utils.Utils;

public class Collection implements Runnable {
	Map<String,String> mapA = new HashMap<String,String>();
	
	@Override
	public void run() {
		
		String[] myLinks = {"test asdsadsad ","test 1sa dsad"} ; 
		
		ArrayList<String> wordsArray = Utils.toWords("jelio e super! ASD@#$@# ") ;
		
		
		for (String string : myLinks) {
			mapA.put(Utils.getMD5(string), string) ; 
		}
		
		System.out.println(wordsArray);
		System.out.println(mapA);
		
	}

}
