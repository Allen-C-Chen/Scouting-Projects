package util;

import java.util.HashMap;
import java.util.Set;
import java.util.Vector;

public class BooleanData extends GenericData {
	
	private HashMap<String,Boolean> values=new HashMap<String,Boolean>();
	private boolean defaultCase;
	private Vector<String> trueCase=new Vector<String>();
	private Vector<String> falseCase=new Vector<String>();
	public BooleanData(String name) {
		super(name);
	}
	@Override
	public String getValue(String match) {
		return ""+values.get(match);
	}

	@Override
	public String getAveragedValues() {
		int count=0;
		int trues=0;
		for(String s:values.keySet()){
			if(values.get(s)){
				trues++;
			}
			count++;
		}
		return ""+((trues*1.00/count)*100.00);
	}

	@Override
	public void setValue(String match, String value) {
		for(String s:trueCase){
			if(s.toLowerCase().equals(value.toLowerCase())){
				values.put(match, true);
				return;
			}
		}
		for(String s:falseCase){
			if(s.toLowerCase().equals(value.toLowerCase())){
				values.put(match, false);
				return;
			}
		}
		if(value.equals("")){
			return;
		}
		values.put(match, defaultCase);

	}
	public void setDefault(boolean defaultcase){
		this.defaultCase=defaultcase;
	}
	public Vector<String> getTrueCases(){
		return trueCase;
	}
	public Vector<String> getFalseCases(){
		return falseCase;
	}
	public BooleanData addTrue(String add){
		trueCase.add(add);
		return this;
	}
	public BooleanData addFalse(String add){
		falseCase.add(add);
		return this;
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
		BooleanData cloned=new BooleanData(getName());
		for(String s:trueCase){
			cloned.addTrue(s);
		}
		for(String s:falseCase){
			cloned.addFalse(s);
		}
		return cloned;
	}
	
}
