package bg.nbu.f58946.utils;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Scanner;

import com.twmacinta.util.MD5;

public class Utils {
	public static String getMD5(String content){
		
		MD5 md5 = new MD5();
		try {
			md5.Update(content, "UTF8");			
		} catch (UnsupportedEncodingException e) {
			md5.Update(content);
		}
		
		return md5.asHex().toUpperCase() ;
		
	}
	
	public static ArrayList<String> toWords(String content){
		Scanner s = new Scanner(content) ; 
		s.useDelimiter("\\s") ; 
		
		ArrayList<String> wordsArray = new ArrayList<>() ; 
		
		while(s.hasNext()){
			wordsArray.add(s.next());
		}
		
		s.close();
		
		return wordsArray ; 
	}
}
