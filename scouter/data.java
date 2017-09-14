package scouter;
import java.util.Vector;

import javax.swing.*;


public class data {
	//table models for the various talbes used
	private matchTablem tm;
	private matchdTablem tdm;
	private scoreTablem sm;
	private scoreTTablem tsm;
	private teamTablem ttm;
	//all sorts of swing variables
	private JTextArea output;
	private JTable match;
	private JTable matchd;
	private JTable score;
	private JTable scoret;
	private files file=new files();
	private Match m;//single match stored for match tab
	private Vector ma=new Vector();
	private Vector ta=new Vector();
	private Team team;//single team loaded for team data tab
	private String[] searchM;//contains the array of strings of match numbers that are to be searched
	private String[] searchT;//contains the array of strings teams that are searched
	private Vector tCom=new Vector();//contains the commentsfor the team?
	private Vector tMatch=new Vector();//contains the matches seperated by comma?for team 
	private Vector tCState=new Vector();//contains the states of the comment
	//default constructor
	public data(){
	}
	/**
	 * sets the values of the ones held in private to the ones in the main JPanel
	 * @param output
	 * @param match
	 * @param matchd
	 * @param tm
	 * @param tdm
	 * @param sm
	 * @param tsm
	 * @param score
	 * @param scoret
	 * @param ttm
	 */
	public void setDataV(JTextArea output,JTable match,JTable matchd,matchTablem tm,matchdTablem tdm,scoreTablem sm,scoreTTablem tsm,JTable score,JTable scoret,teamTablem ttm){//sets the variables needed by data after all of them have been initialized
		this.output=output;
		this.match=match;
		this.matchd=matchd;
		this.tm=tm;
		this.sm=sm;
		this.tdm=tdm;
		this.tsm=tsm;
		this.score=score;
		this.scoret=scoret;
		this.ttm=ttm;
	}
	
	//setters for various stuff
	//sets the basic score table data in match data
	public void beginMatch(){
		tm.setValueAt("Red", 0, 0);
		tm.setValueAt("Blue", 4, 0);
	}
	//sets a single match
	public void setMatch(String num){
		file.readFile("Match"+num+".txt");
		m=new Match(file.getFileData(),num);
		tdm.clearTable();
		//sets red nums
		for(int i=0;i<5;i++){
			tm.setValueAt(""+m.getRscore(i), 0, i+1);
		}
		//sets blue nums
		for(int i=0;i<5;i++){
			tm.setValueAt(""+m.getBscore(i), 4, i+1);
		}
		//sets red teams
		for(int i=0;i<3;i++){
			tm.setValueAt(""+m.getRed(i),i+1,0);
		}
		//sets blue teams
		for(int i=0;i<3;i++){
			tm.setValueAt(""+m.getBlue(i),i+5,0);
		}
		//sets comments
		for(int i=0;i<m.getCommentsA().length;i++){
			if(i>49){
				tdm.addRow();
			}//expands table if over limit
			tdm.setValueAt(m.getComStat(i), i, 0);
			tdm.setValueAt(m.getComments(i), i, 1);
		}
		output.append("[SYS]:Loaded Match"+num+"\n");
		
	}
	//sets an array of matches
	public void setMatches(String nums){
		ma=new Vector();
		searchM=nums.split(",");//an array containing all the numbers to search
		int blank=0;
		String printout="[SYS]:Matches found:";
		//sees whether the match has information or not/does the file exist
		boolean[] valid=new boolean[searchM.length];
		for(int i=0;i<searchM.length;i++){
			file.readFile("Match"+searchM[i]+".txt");	
			if(file.getFileData()!=null){
				ma.addElement(new Match(file.getFileData(),searchM[i]));
				printout+=searchM[i]+",";
			}
			file.setFileData(null);
			printout.substring(0, printout.length()-1);
		}
		output.append(printout+"\n");
		
	}
	//resets the Matches array;
	public void resetMatches(){
		ma=new Vector();
		output.append("[SYS]:Loaded matches reset");
	}
	//checks to see if team/match number has already been inputted
	public boolean checkDup(String nums,String num){
		boolean duplicate=false;
		if(!nums.equals("")){
			String[] data=(nums.split(","));
			for(int i=0;i<data.length;i++){
				if(data[i].equals(num)){
					duplicate=true;
				}
			}
		}
		return duplicate;
	}
	
	//displays everything in score table
	public void displayScore(){
		sm.clearTable();
		for(int i=0;i<ma.size();i++){
			//sets match num
			if(i>49){
				sm.addRow();
			}
			sm.setValueAt(((Match)ma.get(i)).getNum(), i, 0);
			//sets red
			sm.setValueAt(""+((Match)ma.get(i)).getRed(0), i, 1);
			sm.setValueAt(""+((Match)ma.get(i)).getRed(1), i, 2);
			sm.setValueAt(""+((Match)ma.get(i)).getRed(2), i, 3);
			//sets blue
			sm.setValueAt(""+((Match)ma.get(i)).getBlue(0), i, 4);
			sm.setValueAt(""+((Match)ma.get(i)).getBlue(1), i, 5);
			sm.setValueAt(""+((Match)ma.get(i)).getBlue(2), i, 6);
		}
	}
	public void displayRscore(){

		for(int i=0;i<ma.size();i++){
			//sets score
			sm.setValueAt(""+((Match)ma.get(i)).getRscore(0), i, 7);
			sm.setValueAt(""+((Match)ma.get(i)).getRscore(1), i, 8);
			sm.setValueAt(""+((Match)ma.get(i)).getRscore(2), i, 9);
			sm.setValueAt(""+((Match)ma.get(i)).getRscore(3), i, 10);
			sm.setValueAt(""+((Match)ma.get(i)).getRscore(4), i, 11);
		}
	}
	public void displayBscore(){
		
		for(int i=0;i<ma.size();i++){
			//sets score
			sm.setValueAt(""+((Match)ma.get(i)).getBscore(0), i, 7);
			sm.setValueAt(""+((Match)ma.get(i)).getBscore(1), i, 8);
			sm.setValueAt(""+((Match)ma.get(i)).getBscore(2), i, 9);
			sm.setValueAt(""+((Match)ma.get(i)).getBscore(3), i, 10);
			sm.setValueAt(""+((Match)ma.get(i)).getBscore(4), i, 11);
		}
	}
	//Teams part of scoring table
	public void setTeams(String num){
		searchT=num.split(",");
		ta=new Vector();
		for(int t=0;t<searchT.length;t++){
			ta.addElement(new Team());//sets the team and its number
			((Team)ta.get(ta.size()-1)).setNum(searchT[t]);
			//to search through the matches
			for(int m=0;m<ma.size();m++){
				boolean repeat=true;//to see if team is repeated during a single match(for testing reasons)
				for(int i=0;i<3;i++){
					//searches through red
					String s=""+((Match)ma.get(m)).getRed(i);
					if(s.startsWith(searchT[t])&&repeat){
						repeat=false;
						((Team)ta.get(t)).addMatches(((Match)ma.get(m)));
						((Team)ta.get(t)).addRB(true);
					}
					//searches through blue
					s=""+((Match)ma.get(m)).getBlue(i);
					if(s.startsWith(searchT[t])&&repeat){
						repeat=false;
						((Team)ta.get(t)).addMatches(((Match)ma.get(m)));
						((Team)ta.get(t)).addRB(false);
					}
				}
			}
			System.out.println("Team found:"+((Team)ta.get(t)));
		}
	}
	//displays teams scoring in score section
	public void displayTscore(){
		tsm.clearTable();
		//loop for the teams
		for(int t=0;t<ta.size();t++){
			if(t>49){
				tsm.addRow();
			}
			tsm.setValueAt(""+((Team)ta.get(t)).getNum(), t,0);
			int tscore=0;
			int ttote=0;
			int tcon=0;
			int tnood=0;
			int tpen=0;
			for(int i=0;i<((Team)ta.get(t)).getMatchesA().size();i++){
				if(((Team)ta.get(t)).getRB(i)){
					tscore+=((Team)ta.get(t)).getMatches(i).getRscore(0);
					ttote+=((Team)ta.get(t)).getMatches(i).getRscore(1);
					tcon+=((Team)ta.get(t)).getMatches(i).getRscore(2);
					tnood+=((Team)ta.get(t)).getMatches(i).getRscore(3);
					tpen+=((Team)ta.get(t)).getMatches(i).getRscore(4);
				}
				else{
					tscore+=((Team)ta.get(t)).getMatches(i).getBscore(0);
					ttote+=((Team)ta.get(t)).getMatches(i).getBscore(1);
					tcon+=((Team)ta.get(t)).getMatches(i).getBscore(2);
					tnood+=((Team)ta.get(t)).getMatches(i).getBscore(3);
					tpen+=((Team)ta.get(t)).getMatches(i).getBscore(4);
				}
			}
			tsm.setValueAt(""+tscore,t,1);
			tsm.setValueAt(""+ttote,t,2);
			tsm.setValueAt(""+tcon,t,3);
			tsm.setValueAt(""+tnood,t,4);
			tsm.setValueAt(""+tpen,t,5);
			try {
				tscore=tscore/((Team)ta.get(t)).getMatchesA().size();
			} catch (ArithmeticException e) {
				tscore=0;
			}
			tsm.setValueAt(""+tscore,t,6);
		}
	}
	
	//searches for a single team in team tab
	public void searchTeam(String num){
		team=new Team();
		boolean[] valid=new boolean[ma.size()];//boolean to determine if a team is in a match
		int ins = 0;//used to initialize match array
		for(int i=0;i<valid.length;i++){
			valid[i]=false;
		}
		Vector inMatch=new Vector();
		//finds the valid matches
		for(int m=0;m<ma.size();m++){
			for(int i=0;i<3;i++){
				String s=""+((Match)ma.get(m)).getRed(i);
				if(s.startsWith(num)&&valid[m]!=true){//to ake sure taht arrays don't oversize in testing
					inMatch.addElement(((Match)ma.get(m)));
					valid[m]=true;
				}
			}
			for(int i=0;i<3;i++){
				String s=""+((Match)ma.get(m)).getBlue(i);
				if(s.startsWith(num)&&valid[m]!=true){//to make sure that arrays doesn't oversize in testing
					inMatch.addElement(((Match)ma.get(m)));
					valid[m]=true;
				}
			}
		}
		team.setMatchesA(inMatch);//copies newly created array into the team
		team.setNum(num);
		/*
		searchT=new String[1];
		searchT[0]=num;
		team=new Team();
		team.setNum(num);
		//a for loop to search for each team
		boolean[][] in=new boolean[searchT.length][100];//used to find matches
		int[] ins=new int[searchT.length];//used to get length of index
		int[][] matchI=new int[searchT.length][100];//used to index matches into match array
		boolean[][] rb=new boolean[searchT.length][100];
		//to search through the matches
		int t=0;
		for(int m=0;m<ma.length;m++){

			for(int i=0;i<3;i++){
				//searches through red
				String s=""+ma[m].getRed(i);
				if(s.startsWith(searchT[t])){
					in[t][m]=true;
					matchI[t][m]=ins[t];
					ins[t]++;
					System.out.println(t+"in"+m);
					rb[t][m]=true;
				}
				//searches through blue
				s=""+ma[m].getBlue(i);
				if(s.startsWith(searchT[t])){
					in[t][m]=true;
					matchI[t][m]=ins[t];
					ins[t]++;
					System.out.println(t+"in"+m);
					rb[t][m]=false;
				}
			}

		}'
		Match[] matchIn=new Match[ins[t]];
		for(int m=0;m<ins[t];m++){g
			if(in[t][m]){
				matchIn[matchI[t][m]]=ma[m];
			}
		}
		team.setMatchesA(matchIn);
*/
	}
	//finds all comments on the team
	public void searchCom(){//TODO change comments to vector formats
		int i=0;
		tCom=new Vector();
		tCState=new Vector();
		tMatch=new Vector();
		for(int m=0;m<team.getMatchesA().size();m++){//TODO change so it gets vector of matches
			System.out.println("searchcom"+team.getMatches(m));
			System.out.println(team);
			team.getMatches(m).getCommentsA();
			for(int c=0;c<team.getMatches(m).getCommentsA().length-1;c++){

				try {
					if(team.getMatches(m).getComments(c).startsWith(team.getNum())){
							tCom.addElement(team.getMatches(m).getComments(c));
							tCState.addElement(team.getMatches(m).getComStat(c));
							tMatch.addElement(team.getMatches(m).getNum());
					}
				} catch (NullPointerException e) {
					System.out.println("reached end of team/match file");
				}
			}
		}
	}
	//displays a team comments on team tab
	public void displayTeam(){
		ttm.clearTable();
		System.out.println("starting display set"+tCom.size());
		for(int i=0;i<tCom.size();i++){
			if(i>49){
				ttm.addRow();
			}
			System.out.println("setting display"+i);
			ttm.setValueAt(((String)tMatch.get(i)), i, 0);
			ttm.setValueAt(((String)tCState.get(i)),i,1);
			ttm.setValueAt(((String)tCom.get(i)), i, 2);
		}
		
	}
	/**
	 * set the team to search to this
	 * @param team
	 */
	public void setTeam(Team team){
		this.team=team;
	}
	
}
