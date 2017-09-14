package global;

import java.util.ArrayList;

import data.MatchTypes;


public class Match {
	private ArrayList<String> comState=new ArrayList<String>();//contains the state for the comments
	private ArrayList<String> comment= new ArrayList<String>();
	private ArrayList<Integer> RScore= new ArrayList<Integer>();
	private ArrayList<Integer> BScore= new ArrayList<Integer>();
	private String matchNum;
	private int red1;
	private int red2;
	private int red3;
	private int blue1;
	private int blue2;
	private int blue3;
	private MatchTypes type=MatchTypes.Qualifier;//TODO see if needed
	
	public Match(String data){
		String[] values=data.split("::");
		String[] info=values[0].split(":");
		red1=Integer.parseInt(info[0]);
		red2=Integer.parseInt(info[1]);
		red3=Integer.parseInt(info[2]);
		blue1=Integer.parseInt(info[3]);
		blue2=Integer.parseInt(info[4]);
		blue3=Integer.parseInt(info[5]);
		matchNum=info[6];
		info=values[1].split(":");
		for(int i=0;i<info.length;i++){
			RScore.add(Integer.parseInt(info[i]));
		}
		info=values[2].split(":");
		for(int i=0;i<info.length;i++){
			BScore.add(Integer.parseInt(info[i]));
		}
		for(int i=3;i<values.length;i++){
			info=values[i].split(":");
			comState.add(info[0]);
			comment.add(info[1]);
		}
	}
	//setters and adders
	public void addComState(String state){
		comState.add(state);
	}
	public void addcomment(String comment){
		this.comment.add(comment);
	}
	public void addRScore(int score){
		RScore.add(score);
	}
	public void addBScore(int score){
		BScore.add(score);
	}
	public void setRed1(int red1){
		this.red1=red1;
	}
	public void setRed2(int red2){
		this.red2=red2;
	}
	public void setRed3(int red3){
		this.red3=red3;
	}
	public void setBlue1(int blue1){
		this.blue1=blue1;
	}
	public void setBlue2(int blue2){
		this.blue2=blue2;
	}
	public void setBlue3(int blue3){
		this.blue3=blue3;
	}
	public void setType(MatchTypes type){
		this.type=type;
	}
	public void setMatchNum(String matchNum){
		this.matchNum=matchNum;
	}
	//getters
	public ArrayList<String> getComState(){
		return comState;
	}
	public ArrayList<String> getComment(){
		return comment;
	}
	public ArrayList<Integer> getRScore(){
		return RScore;
	}
	public ArrayList<Integer> getBScore(){
		return BScore;
	}
	public int getRed1(){
		return red1;
	}
	public int getRed2(){
		return red2;
	}
	public int getRed3(){
		return red3;
	}
	public int getBlue1(){
		return blue1;
	}
	public int getBlue2(){
		return blue2;
	}
	public int getBlue3(){
		return blue3;
	}
	public MatchTypes getTypes(){
		return type;
	}
	public String getMatchNum(){
		return matchNum;
	}
	public boolean containsTeamRed(int team){
		if(red1==team){
			return true;
		}
		else if(red2==team){
			return true;
		}
		else if(red3==team){
			return true;
		}
		
		return false;
	}
	public boolean containsTeamBlue(int team){
		if(blue1==team){
			return true;
		}
		else if(blue2==team){
			return true;
		}
		else if(blue3==team){
			return true;
		}
		return false;
	}
	@Override
	public String toString(){
		String s=red1+":"+red2+":"+red3+":"+blue1+":"+blue2+":"+blue3+":"+matchNum+"::";
		for(int i=0;i<RScore.size();i++){
			s+=RScore.get(i)+":";
		}
		s+=":";//to get double :: between score values
		for(int i=0;i<BScore.size();i++){
			s+=BScore.get(i)+":";
		}
		s+=":";
		for(int i=0;i<comment.size();i++){
			s+=comState.get(i)+":"+comment.get(i)+"::";
		}
		s=s.substring(0, s.length()-2);
				
		return s;
	}
}
