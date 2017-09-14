package util;

import java.util.HashMap;
import java.util.Set;

public class StringData extends GenericData {
	public StringData(String name) {
		super(name);
	}

	private HashMap<String,String> values=new HashMap<String,String>();
	@Override
	public String getValue(String match) {
		return values.get(match);
	}
	public String getFirst(){
		return values.get(values.keySet().iterator().next());
	}
	@Override
	public String getAveragedValues() {
		String out="";
		for(String s:values.keySet()){
			out+=" "+s+": "+values.get(s);
		}
		return out;
	}

	@Override
	public void setValue(String match, String value) {
		values.put(match, value);
	}

	@Override
	public Set<String> getMatches() {
		return values.keySet();
	}

	@Override
	public HashMap<String, ? extends Object> getData() {
		return values;
	}
	@Override
	public GenericData clone() {
		StringData cloned=new StringData(getName());
		return cloned;
	}

}
