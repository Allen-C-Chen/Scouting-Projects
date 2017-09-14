package util;

public class Lib {
	public static int grabArrayInt(String[] data, int index){
		if(index>data.length-1){
			return -1;
		}
		try{
			return Integer.parseInt(data[index]);
		}catch(NumberFormatException e){}
		return -1;
	}
	public static boolean grabBoolean(String[] data, int index, String valid){
		return grabArrayBoolean(data,index,new String[]{valid});
	}
	public static boolean grabArrayBoolean(String[] data, int index, String[] valid){
		for(String arg:valid){
			if(data[index].contains(arg)){
				return true;
			}
		}
		return false;
	}
	public static int getOccurances(String text,String lookup){
		int times=0;
		int index=text.indexOf(lookup, 0);
		while(index!=-1){
			times++;
			index=text.indexOf(lookup, index)+1;
			if(index==text.length()){
				index=-1;
			}
		}
		return times;
	}
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
	/**
	 * makes sure that string is at least x length, padding it w/ spaces
	 * @param s string to pad
	 * @param pad minimum length of string
	 * @return padded string
	 */
	public static String pad(String s, int pad){
		for(int i=s.length();i<pad;i++){
			s=s+" ";
		}
		return s;
	}
	/**
	 * gets only number chars from a string
	 * @param s string to extract number from
	 * @return number with non digit characters removed
	 */
	public static int extractNumber(String s){
		String i="";
		for(char c:s.trim().toCharArray()){
			if(Character.isDigit(c)){
				i+=c;
			}
		}
		return Integer.parseInt(i);
	}
	
}
