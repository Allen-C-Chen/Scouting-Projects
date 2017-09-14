package scouter;

import java.util.Arrays;
import java.util.Vector;

public class Team {
	private Vector m=new Vector();//TODO change to vector
	private Vector rb=new Vector();
	private String num;
	private Vector inMatches=new Vector();//the matches in which the team is found in TODO change to vector
	public Team(){
	} 
	//setters and getters
	public Vector getInMatchesA(){//TODO change to vector
		return inMatches;
	}
	public String getInMatches(int index){
		return ((String)inMatches.get(index));
	}
	public void setInMatchesA(Vector inMatches){//TODO change to vector
		this.inMatches=inMatches;
	}
	public void setInMatches(int index,String inMatches){//TODO change ot vector
		this.inMatches.set(index, inMatches);
	}
	//setters and getters
	public Vector getMatchesA(){//TODO change to vector
		return m;
	}
	public Match getMatches(int index){//TODO change to vector
		return ((Match)m.get(index));
	}
	public void setMatchesA(Vector m){//TODO change to vector
		this.m=m;
	}
	public void setMatches(int index,Match m){//TODO change to vector
		this.m.set(index, m);
	}
	public void addMatches(Match m){
		this.m.add(m);
	}
	
	//setters and getters
	public String getNum(){
		return num;
	}
	public void setNum(String num){
		this.num=num;
	}

	public void setRBA(Vector rb){
		this.rb=rb;
	}
	public boolean getRB(int index){
		return ((boolean)rb.get(index));
	}
	public void addRB(boolean RB){
		rb.addElement(RB);
	}
	@Override
	public String toString() {
		return "Team [m=" + m + ", rb=" + rb + ", num=" + num + ", inMatches="
				+ inMatches + "]";
	}
	
}
