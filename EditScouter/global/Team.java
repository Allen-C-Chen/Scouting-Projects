package global;

import java.util.ArrayList;

import scouterEdit.TeamField;

public class Team {
	
	private ArrayList<Match> matches= new ArrayList<Match>();
	private ArrayList<Integer> matchHash= new ArrayList<Integer>();//used to id the matches
	private int teamNum;
	private ArrayList<TeamField> fields= new ArrayList<TeamField>();
	private ArrayList<String> fieldData= new ArrayList<String>();
	private String notes="null";
	
	public Team(int teamNum,ArrayList<TeamField> fields){
		this.teamNum=teamNum;
		this.fields=fields;
		for(int i=0;i<fields.size();i++){
			fieldData.add("");
		}
	}
	/**
	 * Creates the team variable using the values read from the file
	 * @param data String data for the team
	 * @param fields Team Fields used by the data, used to verify matching configs
	 */
	public Team(String data,ArrayList<TeamField> fields){
		this.fields=fields;
		String[] values=data.split("!");
		String[] info =values[0].split("/");
		String[] stuff= info[0].split(":");
		teamNum=Integer.parseInt(stuff[1]);
		notes=stuff[2];
		stuff= info[1].split(":");
		for(int i=0;i<stuff.length;i++){
			fieldData.add(stuff[i]);
		}
	}
	//setters
	public void setMatches(ArrayList<Match> matches){
		this.matches=matches;
	}
	public void setTeamNum(int teamNum){
		this.teamNum=teamNum;
	}
	public void setFields(ArrayList<TeamField> fields){
		this.fields=fields;
	}
	public void setFieldData(ArrayList<String> fieldData){
		this.fieldData=fieldData;
	}
	public void setNotes(String notes){
		this.notes=notes;
	}
	//getters
	public ArrayList<Match> getMatches(){
		return matches;
	}
	public int getTeamNum(){
		return teamNum;
	}
	public ArrayList<TeamField> getFields(){
		return fields;
	}
	public ArrayList<String> getFieldData(){
		return fieldData;
	}
	public String getNotes(){
		return notes;
	}
	public void addMatches(ArrayList<Match> matches){
		for(int i=0;i<matches.size();i++){
			if(!matchHash.contains(matches.get(i).hashCode())){
				if (matches.get(i).containsTeamRed(teamNum)||matches.get(i).containsTeamBlue(teamNum)) {
					this.matches.add(matches.get(i));
					matchHash.add(matches.get(i).hashCode());
				}
			}
		}
	}
	public void decodeData(String data){
		String[] values=data.split("/");
		for(int i=0;i<values.length;i++){
			fieldData.add(values[i]);
		}
	}
	public String toString(){//hash code used to identify the fields used for the team
		String s= fields.toString()+":"+teamNum+":"+notes+"/";
		for(int i=0;i<fieldData.size();i++){
			s+=fieldData.get(i)+":";
		}
		s=s.substring(0, s.length()-1);//remove last :
		s+="!";
		for(int i=0;i<matches.size();i++){
			s+=matches.toString()+"!";
		}
		s=s.substring(0, s.length()-1);//remove last !
		return s;
	}
	

}
