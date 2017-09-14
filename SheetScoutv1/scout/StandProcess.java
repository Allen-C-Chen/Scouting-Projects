package scout;

import java.util.HashMap;


/**
 * Builds the data for teams
 * @author Allen
 *
 */
public class StandProcess {
	String[] dataLines;
	HashMap<Integer,TeamData> teams=new HashMap<Integer,TeamData>();
	public StandProcess(){}
	public StandProcess addData(String in,StandRef[] ref){
		dataLines=in.split("\n");
		StandTeam[] teams=new StandTeam[dataLines.length];
		for(int i=0;i<dataLines.length;i++){
			teams[i]=new StandTeam(dataLines[i]);
			teams[i].process(ref);
			teams[i].cleanup();
		}
		for(StandTeam team:teams){
			if(this.teams.containsKey(team.TeamNum)){
				this.teams.get(team.TeamNum).addStand(team);
			}
			else{
				this.teams.put(team.TeamNum, new TeamData(team));
			}
		}
		return this;
	}
	public static void main(String[] args){
		System.out.println(new StandProcess().addData(args[0],StandTeam.FormFormat()).teamData());
		//System.out.println(new StandTeam(args[0]).process(StandTeam.FormFormat()));
	}
	public String teamData(){
		String s="";
		for(Integer i:teams.keySet()){
			teams.get(i).processData();
			s+="\n"+teams.get(i).getSheetLine();
		}
		return s.substring(s.length()>0?1:0);
	}
	
}
