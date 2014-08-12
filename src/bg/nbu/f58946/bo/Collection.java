package bg.nbu.f58946.bo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import bg.nbu.f58946.utils.MyUtils;

public class Collection implements Runnable {
	Map<String,String> mapA = new HashMap<String,String>();
	
	@Override
	public void run() {
		
		String[] myLinks = {"test asdsadsad ","test 1sa dsad"} ; 
		
		ArrayList<String> wordsArray = MyUtils.toWords("jelio e super! ASD@#$@# ") ;
		
		
		for (String string : myLinks) {
			mapA.put(MyUtils.getMD5(string), string) ; 
		}
		
		System.out.println(wordsArray);
		System.out.println(mapA);
		
	}

}
