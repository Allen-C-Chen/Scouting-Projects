package util.trigger;

import java.util.HashMap;

import exceptions.InvalidFormatException;

public class ComparisonTrigger implements Trigger {
	public static final int greater=0;
	public static final int less=1;
	public static final int greaterequal=2;
	public static final int lessequal=3;
	public static final int equal=4;
	private int base;
	private int comparisonType;
	private boolean triggered=false;
	public ComparisonTrigger(int base, int comparisonType){
		this.base=base;
		this.comparisonType=comparisonType;
	}
	@Override
	public boolean isTriggered() {
		return triggered;
	}
	@Override
	public Trigger pushData(HashMap<String, ? extends Object> hashMap) throws InvalidFormatException {
		for(String s: hashMap.keySet()){
			Object obj=hashMap.get(s);
			if(obj instanceof Integer){
				switch(comparisonType){
				case 0:if((int)obj>base)triggered=true;
				break;
				case 1:if((int)obj<base)triggered=true;
				break;
				case 2:if((int)obj>=base)triggered=true;
				break;
				case 3:if((int)obj<=base)triggered=true;
				break;
				case 4:if((int)obj==base)triggered=true;
				}
			}
			else{
				throw new InvalidFormatException("not of integer class");
			}
		}
		return this;
	}

}
