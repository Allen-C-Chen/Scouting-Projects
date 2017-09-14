package util.trigger;

import java.util.HashMap;

import exceptions.InvalidFormatException;

public class BooleanTrigger implements Trigger{
	boolean triggered=false;
	boolean trigger;
	public BooleanTrigger(boolean trigger){
		this.trigger=trigger;
	}
	@Override
	public boolean isTriggered() {
		return triggered;
	}

	@Override
	public Trigger pushData(HashMap<String,? extends Object> values) throws InvalidFormatException {
		triggered=false;
		for(String s:values.keySet()){
			Object obj=values.get(s);
			if(obj instanceof Boolean){
				if(trigger&&(boolean)obj||!trigger&&!(boolean)obj){
					triggered=true;
				}
			}
			else{
				throw new InvalidFormatException("non boolean value");
			}
		}
		return this;
		
	}

}
