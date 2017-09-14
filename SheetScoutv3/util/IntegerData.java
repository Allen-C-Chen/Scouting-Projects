package util;

import java.util.HashMap;
import java.util.Set;

public class IntegerData extends GenericData{
	public IntegerData(String name) {
		super(name);
	}

	private HashMap<String,Integer> values=new HashMap<String,Integer>();
	@Override
	public String getValue(String match) {
		return ""+values.get(match);
	}

	@Override
	public String getAveragedValues() {
		int total=0;
		int count=0;
		for(String s:values.keySet()){
			total+=values.get(s);
			count++;
		}
		if(count==0){
			return "0";
		}
		return ""+(total/count*1.00);
	}

	@Override
	public void setValue(String match, String value) {
		try{
		int in=Integer.parseInt(value);
		values.put(match, in);
		}catch(Exception e){}
	}

	@Override
	public Set<String> getMatches() {
		return values.keySet();
	}

	@Override
	public HashMap<String, ? extends Object> getData() {
		return values;
	}
	public String getMaxValue(){
		int max=Integer.MIN_VALUE;
		for(String s:values.keySet()){
			if(values.get(s)>max){
				max=values.get(s);
			}
		}
		return ""+max;
	}
	public String getMinValue(){
		int min=Integer.MAX_VALUE;
		for(String s:values.keySet()){
			if(values.get(s)<min){
				min=values.get(s);
			}
		}
		return ""+min;
	}

	@Override
	public GenericData clone() {
		IntegerData cloned=new IntegerData(getName());
		return cloned;
	}
}
