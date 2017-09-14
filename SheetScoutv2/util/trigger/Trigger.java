package util.trigger;

import java.util.HashMap;

import exceptions.InvalidFormatException;

public interface Trigger {
	/**
	 * get whether trigger has been triggered
	 * @return
	 */
	public boolean isTriggered();
	/**
	 * push data from matches to the trigger
	 * @param hashMap
	 * @throws InvalidFormatException 
	 */
	public Trigger pushData(HashMap<String, ? extends Object> hashMap) throws InvalidFormatException;
}
