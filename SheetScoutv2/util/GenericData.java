package util;

import java.util.HashMap;
import java.util.Set;
import java.util.Vector;

/**
 * A generic representation of the different data types
 * @author Allen
 *
 */
public abstract class GenericData {
	/**
	 * stores the matches for which there are data for
	 */
	private Vector<String> matches=new Vector<String>();
	private String name;
	private String description;
	protected GenericData(String name){
		this.name=name;
	}
	public GenericData setDescription(String description){
		this.description=description;
		return this;
	}
	public String getDescription(){
		return description;
	}
	public String getName(){
		return name;
	}
	/**
	 * get value for data piece for selected match
	 * @return
	 */
	public abstract String getValue(String match);
	public abstract Set<String> getMatches();
	public abstract HashMap<String,? extends Object> getData();
	public abstract GenericData clone();
	/**
	 * get the average of all values recorded
	 * @return
	 */
	public abstract String getAveragedValues();
	/**
	 * sets value for match
	 * @param match match to set value for
	 * @param value value(assuming it is in proper format to be parsed)
	 */
	public abstract void setValue(String match, String value);
	/**
	 * check if match has data
	 * @param match match to look for
	 * @return
	 */
	public boolean hasMatch(String match){
		for(String s:matches){
			if(s.equals(match)){
				return true;
			}
		}
		return false;
	}
}
