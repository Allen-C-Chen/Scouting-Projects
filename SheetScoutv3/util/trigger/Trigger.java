package util.trigger;

import java.util.HashMap;

import exceptions.InvalidFormatException;
/**
 * Triggers are given the hashmap of a data object and scans it to determine if some parameter has occured i.e. no true value or otherwise
 * @author Allen
 *
 */
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
