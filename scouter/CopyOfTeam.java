package scouter;

import java.util.Arrays;
/**
 * copies in case stuff got messed up
 * @author Allen
 *
 */
public class CopyOfTeam {
	private Match[] m;//TODO change to vector
	private boolean[] rb;
	private String num;
	private String[] inMatches=new String[100];//the matches in which the team is found in TODO change to vector
	public CopyOfTeam(){
	} 
	//setters and getters
	public String[] getInMatchesA(){//TODO change to vector
		return inMatches;
	}
	public String getInMatches(int index){//TODO change to vector
		return inMatches[index];
	}
	public void setInMatchesA(String[] inMatches){//TODO change to vector
		this.inMatches=inMatches;
	}
	public void setInMatches(int index,String inMatches){//TODO change ot vector
		this.inMatches[index]=inMatches;
	}
	@Override
	public String toString() {
		return "Team [m=" + Arrays.toString(m) + ", inMatches="
				+ Arrays.toString(inMatches) + "]";
	}
	//setters and getters
	public Match[] getMatchesA(){//TODO change to vector
		return m;
	}
	public Match getMatches(int index){//TODO change to vector
		return m[index];
	}
	public void setMatchesA(Match[] m){//TODO change to vector
		this.m=m;
	}
	public void setMatches(int index,Match m){//TODO change to vector
		this.m[index]=m;
	}
	//setters and getters
	public String getNum(){
		return num;
	}
	public void setNum(String num){
		this.num=num;
	}

	public void setRBA(boolean[] rb){
		this.rb=rb;
	}
	public boolean getRB(int index){
		return rb[index];
	}
}
