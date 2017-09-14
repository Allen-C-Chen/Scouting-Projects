package sheet;

import java.util.Vector;

import util.GenericData;

/**
 * represents a team, contains the sequence representing the data for the team
 * @author Allen
 *
 */
public class Team {
	private Vector<GenericData> data=new Vector<GenericData>();
	private int number;
	public Team(int number,Sequence sequence){
		this.number=number;
		sequence.sequence(data);//builds the data array
	}
	public int getNumber(){
		return number;
	}
	public GenericData getData(String name){
		for(int i=0;i<data.size();i++){
			if(data.get(i).getName().equals(name)){
				return data.get(i);
			}
		}
		return null;
	}
	public void setData(String name,String match,String value){
		for(int i=0;i<data.size();i++){
			if(data.get(i).getName().equals(name)){
				data.get(i).setValue(match, value);
			}
		}
	}
	
}
