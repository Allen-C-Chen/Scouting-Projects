package data;

import java.util.ArrayList;
import javax.swing.JTextArea;

import global.Components;
import global.Location;
import global.Match;
import global.Vars;
import scouterEdit.ComType;
import scouterEdit.ScoreField;
import scouterEdit.TeamField;

public class dataVar extends Vars{
		private ArrayList<Match> matches= new ArrayList<Match>();
		private ArrayList<String> matchNum= new ArrayList<String>();//holds match number of the match
		
		//variables in relation to reading matches
		private MatchTypes type=MatchTypes.Qualifier;//types used to read for match
		private String directory="";
		
		public dataVar(){
			output.setWrapStyleWord(true);
		}
		//setters
		public void setMatches(ArrayList<Match> matches){
			this.matches=matches;
		}
		public void setMatchNum(ArrayList<String> matchNum){
			this.matchNum=matchNum;
		}
		public void setTypes(MatchTypes type){
			this.type=type;
		}
		public void setDirectory(String directory){
			this.directory=directory;
		}
		//getters
		public Components getGameComponent(String name){
			for(int i=0;i<gameComponents.size();i++){
				if(gameComponents.get(i).getName().equals(name)){
					return gameComponents.get(i);
				}
			}
			return null;
		}
		public ArrayList<Match> getMatches(){
			return matches;
		}
		public ArrayList<String> getMatchNum(){
			return matchNum;
		}
		public MatchTypes getTypes(){
			return type;
		}
		public String getDirectory(){
			return directory;
		}
}
