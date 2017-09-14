package scouter;
import java.util.Vector;
import javax.swing.*;

/**
 * copies in case stuff got messed up
 * @author Allen
 *
 */
public class CopyOfdata {
	private matchTablem tm;
	private matchdTablem tdm;
	private scoreTablem sm;
	private scoreTTablem tsm;
	private teamTablem ttm;
	private JTextArea output;
	private JTable match;
	private JTable matchd;
	private JTable score;
	private JTable scoret;
	private files file=new files();
	private Match m;//single match stored for match tab
	private Match[] ma;//TODO convert the array of matches stored to vector
	private Team[] ta;//TODO convert the array of teams stored to vector
	private Team team;//single team loaded for team data tab
	private String[] searchM;//contains the array of strings of match numbers that are to be searched
	private String[] searchT;//contains the array of strings teams that are searched
	private String[] tCom;//contains the commentsfor the team?
	private String[] tMatch;//contains the matches seperated by comma?for each? team 
	private String[] tCState;//contains the states fo the comment
	//default constructor
	public CopyOfdata(){
	}
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
		
	}
	//sets an array of matches
	public void setMatches(String nums){
		searchM=nums.split(",");//an array containing all the numbers to search
		int blank=0;
		//sees whether the match has information or not/does the file exist
		boolean[] valid=new boolean[searchM.length];
		//TODO set it up so that the matches are immediately appended to vector if it is valid
		for(int i=0;i<searchM.length;i++){
			file.readFile("Match"+searchM[i]+".txt");	
			if(file.getFileData()==null){
				valid[i]=false;
				blank++;
				System.out.println("setnull"+i);
			}
			else{
				valid[i]=true;
				file.setFileData(null);
				System.out.println("setvalid"+i);
			}
		}
		//TODO delete the following 
		System.out.println(searchM.length-blank);
		int a=searchM.length-blank;
		ma=new Match[a];
		int d=0;
		for(int i=0;i<searchM.length;i++){
			if(valid[i]){
				file.readFile("Match"+searchM[i]+".txt");
				System.out.println("file data for match "+searchM[i]+file.getFileData());
				ma[d]=new Match(file.getFileData(),searchM[i]);
				file.setFileData("");
				d++;
			}
		}
		for(int i=0;i<ma.length;i++){
			System.out.println("Match found"+i+ma[i]);
		}
	}
	//resets the Matches array;
	public void resetMatches(){
		ma=null;//TODO reset the vector containing the matches by setting to new Vector()
	}
	//checks to see if team/match number has already been inputted
	public boolean checkDup(String nums,String num){
		boolean duplicate=false;
		String[] data=(nums.split(","));
		for(int i=0;i<data.length;i++){
			if(data[i].equals(num)){
				duplicate=true;
			}
		}
		return duplicate;
	}
	
	//displays everything in score table
	public void displayScore(){
		//TODO convert all array systems to vector, .length>size()
		for(int i=0;i<ma.length;i++){
			//sets match num
			if(i>49){
				sm.addRow();
			}
			sm.setValueAt(ma[i].getNum(), i, 0);
			//sets red
			sm.setValueAt(""+ma[i].getRed(0), i, 1);
			sm.setValueAt(""+ma[i].getRed(1), i, 2);
			sm.setValueAt(""+ma[i].getRed(2), i, 3);
			//sets blue
			sm.setValueAt(""+ma[i].getBlue(0), i, 4);
			sm.setValueAt(""+ma[i].getBlue(1), i, 5);
			sm.setValueAt(""+ma[i].getBlue(2), i, 6);
		}
	}
	public void displayRscore(){
		//TODO convert all array methods to vector methods (.length>size(),[index]>(castObj)vector.get(index))...
		for(int i=0;i<ma.length;i++){
			//sets score
			sm.setValueAt(""+ma[i].getRscore(0), i, 7);
			sm.setValueAt(""+ma[i].getRscore(1), i, 8);
			sm.setValueAt(""+ma[i].getRscore(2), i, 9);
			sm.setValueAt(""+ma[i].getRscore(3), i, 10);
			sm.setValueAt(""+ma[i].getRscore(4), i, 11);
		}
	}
	public void displayBscore(){
		//TODO convert all array methods into vector methods
		for(int i=0;i<ma.length;i++){
			//sets score
			sm.setValueAt(""+ma[i].getBscore(0), i, 7);
			sm.setValueAt(""+ma[i].getBscore(1), i, 8);
			sm.setValueAt(""+ma[i].getBscore(2), i, 9);
			sm.setValueAt(""+ma[i].getBscore(3), i, 10);
			sm.setValueAt(""+ma[i].getBscore(4), i, 11);
		}
	}
	//Teams part of scoring table
	public void setTeams(String num){
		searchT=num.split(",");
		ta=new Team[searchT.length];//TODO remove useless initiations
		for(int t=0;t<searchT.length;t++){
			ta[t]=new Team();
		}
		//TODO remove all arrays that are useless
		//a for loop to search for each team
		boolean[][] in=new boolean[searchT.length][100];//used to find matches
		int[] ins=new int[searchT.length];//used to get length of index
		int[][] matchI=new int[searchT.length][100];//used to index matches into match array
		boolean[][] rb=new boolean[searchT.length][100];
		//TODO add the valid matches to Vector ta
		for(int t=0;t<searchT.length;t++){
			//to search through the matches
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
				
			}
			output.append("[SYS]:Valid matches found for team:"+searchT[t]+" is: "+ins[t]+"\n");
		}
		//TODO delete the following which is no longer needed
		//above bit works
		//to search through teams to set which matches they're in
		for(int t=0;t<searchT.length;t++){
			//to search through the matches
			Match[] inMatch=new Match[ins[t]];
			String[] inMatches=new String[ins[t]];
			boolean[] rbt=new boolean[ins[t]];
			for(int m=0;m<ma.length;m++){
				if(in[t][m]){
					inMatches[matchI[t][m]]=ma[m].getNum();
					inMatch[matchI[t][m]]=ma[m];
					rbt[matchI[t][m]]=rb[t][m];
				}
			}
			ta[t].setInMatchesA(inMatches);
			ta[t].setMatchesA(inMatch);
			ta[t].setRBA(rbt);
			ta[t].setNum(searchT[t]);
		}
	}
	//displays teams scoring in score section
	public void displayTscore(){
		//loop for the teams
		//TODO convert array methods to vector methods
		for(int t=0;t<ta.length;t++){
			if(t>49){
				tsm.addRow();
			}
			tsm.setValueAt(""+ta[t].getNum(), t,0);
			int tscore=0;
			int ttote=0;
			int tcon=0;
			int tnood=0;
			int tpen=0;
			for(int i=0;i<ta[t].getInMatchesA().length;i++){
				if(ta[t].getRB(i)){//TODO change to cast vectors to Team variable
					tscore+=ta[t].getMatches(i).getRscore(0);
					ttote+=ta[t].getMatches(i).getRscore(1);
					tcon+=ta[t].getMatches(i).getRscore(2);
					tnood+=ta[t].getMatches(i).getRscore(3);
					tpen+=ta[t].getMatches(i).getRscore(4);
				}
				else{
					tscore+=ta[t].getMatches(i).getBscore(0);
					ttote+=ta[t].getMatches(i).getBscore(1);
					tcon+=ta[t].getMatches(i).getBscore(2);
					tnood+=ta[t].getMatches(i).getBscore(3);
					tpen+=ta[t].getMatches(i).getBscore(4);
				}
			}
			tsm.setValueAt(""+tscore,t,1);
			tsm.setValueAt(""+ttote,t,2);
			tsm.setValueAt(""+tcon,t,3);
			tsm.setValueAt(""+tnood,t,4);
			tsm.setValueAt(""+tpen,t,5);
			try {
				tscore=tscore/ta[t].getMatchesA().length;
			} catch (ArithmeticException e) {
				tscore=0;
				e.printStackTrace();
			}
			tsm.setValueAt(""+tscore,t,6);
		}
	}
	
	//searches for a single team in team tab
	public void searchTeam(String num){
		team=new Team();
		boolean[] valid=new boolean[ma.length];//boolean to determine if a team is in a match
		int ins = 0;//used to initialize match array
		for(int i=0;i<valid.length;i++){
			valid[i]=false;
		}
		
		//finds the valid matches
		for(int m=0;m<ma.length;m++){
			for(int i=0;i<3;i++){
				String s=""+ma[m].getRed(i);
				if(s.startsWith(num)&&valid[m]!=true){//to ake sure taht arrays don't oversize in testing
					ins++;
					valid[m]=true;
				}
			}
			for(int i=0;i<3;i++){
				String s=""+ma[m].getBlue(i);
				if(s.startsWith(num)&&valid[m]!=true){//to make sure that arrays doesn't oversize in testing
					ins++;
					valid[m]=true;
				}
			}
		}
		int i=0;//integer to index the new match array
		Match[] inMatch=new Match[ins];//array used to set the matches in the team array
		for(int m=0;m<ma.length;m++){
			if(valid[m]){//when one of the matches is marked valid it is copied into new match array
				inMatch[i]=ma[m];
				i++;//increments for new index of array
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
		for(int m=0;m<team.getMatchesA().length;m++){//TODO change so it gets vector of matches
			System.out.println("searchcom"+team.getMatches(m));
			System.out.println(team);
			team.getMatches(m).getCommentsA();
			for(int c=0;c<team.getMatches(m).getCommentsA().length-1;c++){

				if(team.getMatches(m).getComments(c).startsWith(team.getNum())){
					System.out.println("setcom"+team.getNum());
					System.out.println("setcom"+team.getMatches(m));
					System.out.println("setcom"+team.getMatches(m).getComments(c));
					System.out.println("setcom valid");
					i++;
				}
			}
		}
		//TODO delete following portion
		int d=0;
		tCom=new String[i];
		tCState=new String[i];
		tMatch=new String[i];
		System.out.println(i);
		for(int m=0;m<team.getMatchesA().length;m++){
			for(int c=0;c<team.getMatches(m).getCommentsA().length-1;c++){
				if(team.getMatches(m).getComments(c).startsWith(team.getNum())){
					System.out.println(team.getMatches(m).getComments(c));
					System.out.println(team.getMatches(m).getComStat(c));
					System.out.println(team.getMatches(m).getNum());
					tCom[d]=team.getMatches(m).getComments(c);
					tCState[d]=team.getMatches(m).getComStat(c);
					tMatch[d]=team.getMatches(m).getNum();
					d++;
				}
			}


		}
	}
	//displays a team comments on team tab
	public void displayTeam(){
		System.out.println("starting display set"+tCom.length);//TODO change things to vector(tCom,tMatch,tCState)
		for(int i=0;i<tCom.length;i++){
			if(i>49){
				ttm.addRow();
			}
			System.out.println("setting display"+i);
			System.out.println(tMatch[i]);
			ttm.setValueAt(tMatch[i], i, 0);
			ttm.setValueAt(tCState[i],i,1);
			ttm.setValueAt(tCom[i], i, 2);
		}
		
	}
	public void setTeam(Team team){
		this.team=team;
	}
	
}
