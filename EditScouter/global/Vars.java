package global;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JLabel;
import javax.swing.JTextArea;

import scouterEdit.ComType;
import scouterEdit.ScoreField;
import scouterEdit.TeamField;

public class Vars {
	//main data variables
	protected ArrayList<Components> gameComponents=new ArrayList<Components>();
	protected ArrayList<TeamField> teamFields=new ArrayList<TeamField>();
	protected ArrayList<ScoreField> scoreFields=new ArrayList<ScoreField>();
	//variable of what's saved into the text document
	protected String printOut="null!null!null!null";//game/window/team/score

	protected JTextArea output=new JTextArea();//output text area stored globally so that all classes can access to output messages.
	protected JLabel lblOutput=new JLabel();//label for that text area

	//variables for main window
	protected Location WinLocation=new Location();
	protected String WinTitle;
	protected int OutH;//determines fixed height of the output box
	protected boolean Output;//determines whether output box is created upon startup to display information;
	
	//setters
	public void setGameComponents(ArrayList<Components> components){
		this.gameComponents=components;
	}
	public void setTeamFields(ArrayList<TeamField> teamFields){
		this.teamFields=teamFields;
	}
	public void setScoreFields(ArrayList<ScoreField> scoreFields){
		this.scoreFields=scoreFields;
	}
	public void setOutput(JTextArea output){
		this.output=output;
	}
	public void setlblOutput(JLabel lblOutput){
		this.lblOutput=lblOutput;
	}
	public void setWinLocation(Location WinLocation){
		this.WinLocation=WinLocation;
	}
	public void setWinTitle(String WinTitle){
		this.WinTitle=WinTitle;
	}
	public void setOutH(int OutH){
		this.OutH=OutH;
	}
	public void setBooleanOutput(boolean Output){
		this.Output=Output;
	}
	//getters
	public ArrayList<Components> getGameComponents(){
		return gameComponents;
	}
	public ArrayList<TeamField> getTeamFields(){
		return teamFields;
	}
	public ArrayList<ScoreField> getScoreFields(){
		return scoreFields;
	}
	public JTextArea getOutput(){
		return output;
	}
	public JLabel getlblOutput(){
		return lblOutput;
	}
	public Location getWinLocation(){
		return WinLocation;
	}
	public String getWinTitle(){
		return WinTitle;
	}
	public int getOutH(){
		return OutH;
	}
	public boolean getBooleanOutput(){
		return Output;
	}
	public String getPrintOut(){
		return printOut;
	}
	//saves the various components
	public void saveGameComponents(){
		String gameCom[]=printOut.split("!");
		gameCom[0]="";
		for(int i=0;i<gameComponents.size();i++){
			gameCom[0]+=gameComponents.get(i).getStringRepresentation()+"::";
		}
		if(gameCom[0]==null||gameCom[0].equals("")){
			gameCom[0]="null";
		}
		else{
			gameCom[0]=gameCom[0].substring(0, gameCom[0].length()-2);
		}
		System.out.println("Saved game"+gameCom[0]);
		printOut="";
		for(int i=0;i<gameCom.length;i++){
			printOut+=gameCom[i]+"!";
		}
		printOut=printOut.substring(0,printOut.length()-1);
	}
	public void saveWindow(){
		String window[]=printOut.split("!");
		window[1]=WinTitle+":"+OutH+":"+Output+":"+WinLocation.getX()+":"+WinLocation.getY()+":"+WinLocation.getW()+":"+WinLocation.getH();
		System.out.println("Saved window"+window[1]);
		printOut="";
		for(int i=0;i<window.length;i++){
			printOut+=window[i]+"!";
		}
		printOut=printOut.substring(0, printOut.length()-1);
	}

	public void saveTeam(){
		String team[]=printOut.split("!");
		team[2]="";
		for(int i=0;i<teamFields.size();i++){
			team[2]+=teamFields.get(i).getStringRepresentation()+"::";
		}
		if(team[2].equals("")||team[2]==null){
			team[2]="null";
		}
		else{
			team[2]=team[2].substring(0,team[2].length()-2);
		}
		System.out.println("Saved Team"+team[2]);
		printOut="";
		for(int i=0;i<team.length;i++){
			printOut+=team[i]+"!";
		}
		printOut=printOut.substring(0,printOut.length()-1);
	}
	public void saveScore(){
		String score[]=printOut.split("!");
		score[3]="";
		for(int i=0;i<scoreFields.size();i++){
			score[3]+=scoreFields.get(i).getStringRepresentation()+"::";
		}
		if(score[3].equals("")||score[3]==null){
			score[3]="null";
		}
		else{
			score[3]=score[3].substring(0, score[3].length()-2);
		}
		System.out.println("Saved Score:"+score[3]);
		printOut="";
		for(int i=0;i<score.length;i++){
			printOut+=score[i]+"!";
		}
		printOut=printOut.substring(0, printOut.length()-1);
	}
	public void savePrintout(File file){
		try{
			BufferedWriter out = new BufferedWriter(new FileWriter(file));
			out.write(printOut);
			out.close();
		}
		catch(IOException e){
			System.out.println("Write in file error");
		}
	}
	public String debugWindow(){
		String s="Title:"+WinTitle+
				"\nOutput box Enabled:"+Output+
				"\nOutput box Height:"+OutH+
				"\n\nLocation"+
				"\nX:"+WinLocation.getX()+
				"\nY:"+WinLocation.getY()+
				"\nW:"+WinLocation.getW()+
				"\nH:"+WinLocation.getH()+
				"\nend";

		return s;
	}
	public String debugGame(int index){
		Components test=gameComponents.get(index);
		String s="Name:"+test.getName()+
				"\nType:"+test.getType()+
				"\nReturn Type:"+test.getReturnType()+
				"\nLabel:"+test.getLabel()+
				"\nMax:"+test.getMax()+
				"\nMin:"+test.getMin()+
				"\nInterval:"+test.getInterval()+
				"\nGrouping:"+test.getGrouping()+
				"\nLabel:"+test.getLabel()+
				"\nAction:"+test.getAction()+
				"\nTooltip:"+test.getTooltip()+
				"\nDefault Value:"+test.getDefaultVar()+
				"\n\nLocation"+
				"\nX:"+test.getLocation().getX()+
				"\nY:"+test.getLocation().getY()+
				"\nW:"+test.getLocation().getW()+
				"\nH:"+test.getLocation().getH();
		return s;
	}
	public String debugTeam(int index){
		TeamField test=teamFields.get(index);
		String s="Name:"+test.getName()+
				"\nType:"+test.getType()+
				"\nTooltip:"+test.getTooltip()+
				"\nModel:"+test.getModel();
		return s;
	}
	public void resetData(){
		printOut="null!null!null!null";
		WinLocation=new Location();
		WinTitle="";
		OutH=0;
		Output=false;
		gameComponents=new ArrayList<Components>();
		teamFields=new ArrayList<TeamField>();
		scoreFields= new ArrayList<ScoreField>();
	}
	public void decode(String data){
		String[] values=data.split("!");
		decodeGame(values[0].split("::"));//splits the data into the components
		decodeWindow(values[1].split(":"));
		decodeTeam(values[2].split("::"));
		decodeScore(values[3].split("::"));
	}
	public void decodeGame(String[] data){
		gameComponents.clear();
		if(!data[0].equals("null")){
			for(int i=0;i<data.length;i++){
				String[] info=data[i].split(",");//separates all the info from the components to its separate parts.
				System.out.println(i+"index"+Arrays.toString(info));
				//sets location based on values given, parses it to int
				Location loc=new Location(Integer.parseInt(info[4]),Integer.parseInt(info[5]),Integer.parseInt(info[6]),Integer.parseInt(info[7]));
				gameComponents.add(new Components(info[0],ComType.getType(info[1]),loc,info[8],info[9]));
				if(!info[2].equals("null")){//cause if nothing was typed it's saved as null
					gameComponents.get(i).setTooltip(info[2]);//sets tooltip which is not included in contructor
				}
				gameComponents.get(i).setAction(info[3]);//sets action which is not included in constructor
				gameComponents.get(i).setDefaultVar(info[10]);

			}
		}
	}

	public void decodeWindow(String[] data){
		WinTitle=data[0];
		OutH=Integer.parseInt(data[1]);
		if(data[2].equals("true")){
			Output=true;
		}
		else{
			Output=false;
		}
		WinLocation.setX(Integer.parseInt(data[3]));
		WinLocation.setY(Integer.parseInt(data[4]));
		WinLocation.setW(Integer.parseInt(data[5]));
		WinLocation.setH(Integer.parseInt(data[6]));
	}
	public void decodeTeam(String[] data){
		teamFields.clear();
		if(!data[0].equals("null")){
			for(int i=0;i<data.length;i++){
				String[] info=data[i].split(":");
				teamFields.add(new TeamField(info[1]));
				teamFields.get(i).setType(info[0]);
				if(!info[2].equals("null")){//cause if there's no info it's saved as null
					teamFields.get(i).setTooltip(info[2]);
				}
				if(!info[3].equals("null")){
					teamFields.get(i).setModel(info[3]);
				}
				try {
					teamFields.get(i).setColWidth(Integer.parseInt(info[4]));
				} catch (NumberFormatException e) {}
			}
		}
	}
	public void decodeScore(String[] data){
		scoreFields.clear();
		if(!data[0].equals("null")){
			for(int i=0;i<data.length;i++){
				String[] info=data[i].split(":");
				scoreFields.add(new ScoreField(info[0]));
				if(!info[2].equals("null")){
					scoreFields.get(i).setValue(info[2]);
				}
				if(!info[3].equals("null")){
					scoreFields.get(i).setTooltip(info[3]);
				}
				if(info[3].equals("false")){
					scoreFields.get(i).setAddToScore(false);
				}
				try{
					scoreFields.get(i).setWidth(Integer.parseInt(info[4]));
				}catch(Exception e){}
			}
		}
	}
}
