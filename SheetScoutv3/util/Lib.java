package util;

public class Lib {
	public static String arrayToString(Object[] array){
		String s="";
		for(Object o:array){
			s+=" "+o.toString();
		}
		if(s.length()>0){
			s=s.substring(1);
		}
		return s;
	}
}
