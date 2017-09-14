package scouter;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import scouter.files;
//this class is used to organize all the code based calculations and variables into a new class
//to make things cleaner and easier to understand
public class scout{
	private int[] red=new int[3];
	private int[] blue=new int[3];
	private int time;
	private int matchTime;//used to determining how much time(in secounds) there is in a match
	private String match;
	private String printout="";//what is saved
	private int[] Rscore=new int[5];//0=score,1=totes,2=containers,3=noodles,4=penalties
	private int[] Bscore=new int[5];//0=score,1=totes,2=containers,3=noodles,4=penalties
	private String state="Prep";//states > Prep=before game > game=during game > end=after game
    private JTextField timeout;//timing output
    private Thread t;//for thread handling
    private boolean wait;//for stopping timer
    private JTextArea output;//used to output whatever's needed
    private files file=new files();//used for saving
    private boolean reset=false;//used to reset the game time
	public scout(JTextArea output){
		this.output=output;//creates a the object with the textarea to output status reports
	}
	//setters and getters for match variable
	public void setMatch(String match){
		this.match=match;
		output.append("[SYS]:Match Number set\n");
	}
	public String getMatch(){
		return match;
	}
	//sets teams
	public void setRed(int Red_1,int Red_2,int Red_3){
		red[0]=Red_1;
		red[1]=Red_2;
		red[2]=Red_3;
		output.append("[SYS]:Red Team set\n");
	}
	public void setBlue(int Blue_1,int Blue_2,int Blue_3){
		blue[0]=Blue_1;
		blue[1]=Blue_2;
		blue[2]=Blue_3;
		output.append("[SYS]:Blue Team set\n");
	}
	public void setRed(int index,int value){
		red[index]=value;
	}
	public void setBlue(int index,int value){
		blue[index]=value;
	}
	//sets state of system prep,game or end
	public void setState(String state){
		this.state=state;
	}
	//sets red scores
	public void setRscore(int score,int totes,int con,int noodles,int penalties){
		Rscore[0]=score;
		Rscore[1]=totes;
		Rscore[2]=con;
		Rscore[3]=noodles;
		Rscore[4]=penalties;
	}
	public void setRscore(int index,int value){
		Rscore[index]=value;
	}
	//sets blue scores
	public void setBscore(int score,int totes,int con,int noodles, int penalties){
		Bscore[0]=score;
		Bscore[1]=totes;
		Bscore[2]=con;
		Bscore[3]=noodles;
		Bscore[4]=penalties;
	}
	public void setBscore(int index,int value){
		Bscore[index]=value;
	}
	//gets state for start game menu item
	public String getState(){
		return state;
	}
	//executed whenever a input from the comments in taken in
	public String textState(String in){
		if(!state.equals("game")){
			in="["+state+"]:"+in;
			printout+=in+"::";
			return in;
		}
		else{
			in="["+(int) Math.floor(time/60)+"."+time%60+"]:"+in;
			printout+=in+"::";
			return in;
		}
	}
	//timer thread
	public synchronized void startTime(JTextField timeout,int matchTime,Thread t){
		output.append("[SYS]:Game has started\n");
		synchronized(this){
			System.out.println("Starting timer");
			this.timeout=timeout;
			this.matchTime=matchTime;
			time=0;
			reset=false;
			//loop to count the secounds passed
			for(time=0;time<this.matchTime;time++){
				System.out.println("loop started");
				//time++;
				//sets the time display
				timeout.setText(((int)Math.floor(time/60))+":"+time%60);
				try{
					wait(1000);
					//wait boolean used to pause timer
					if(wait){
						wait();
						wait=false;
					}
					//reset boolean to completely end timer
					if(reset){
						System.out.println("reset");//debug
						reset=false;
						setState("end");
						break;
					}
				}
				catch(Exception e){
					e.printStackTrace();
				}
				System.out.println("timer while looped");
			}
			//ending stuff
			//output.append("[SYS]:Game has ended\n");//TODO find out why this is causing system to lock(scrolling??)
			setState("end");
		}
		//in the case the game was reset, terminating loop, the next time it enters the loop it won't automatically reset
		reset=false;
		
	}
	//stops timer and resumes timer
	public void stopTime(){
		wait=true;
	}
	public void resumeTime(){
		synchronized(this){
			notify();
		}
	}
	//makes sure inputs are valid
	public boolean checkIn(String input,String varChk){
		if(input.equals("")){
			output.append("[ERR]:"+varChk+"is not entered\n");
			return false;
		}
		else{
			return true;
		}
	}
	//same thing for numbers
	public boolean checkInNum(String input,String varChk){
	    try{
	    	if(input.equals("")){
	    		NullPointerException e=new NullPointerException();
	    		throw e;
	    	}
		    Integer.parseInt(input);
		    return true;
	    }
	    catch(NullPointerException e){
		    output.append("[ERR]:"+varChk+" is not filled\n");
		    return false;
	    }
	    catch(NumberFormatException e){
	    	output.append("[ERR]:"+varChk+" is not a number\n");
	    	return false;
	    }
	    catch(Exception e){
	    	e.printStackTrace();
	    	output.append("[ERR]:an error occured with " +varChk+"\n");
	    	return false;
	    }
	
    }
	//sends game data tow output
	public void sendDat(String team, boolean con,boolean nood,int adT, int exT,String gCom){
		//initiates the strings, which will remain blank,not null when it is later put together
		String icon="";
		String iadT="";
		String iexT="";
		//if the team had added a container
		if(con){
			icon=" had added a container";
			if(nood){//noodle text only added with the presence of a container
				icon+=" with a noodle";
			}
		}
		else if(adT>0){//the added totes String without a container
			iadT=" had added "+adT+" Totes";
		}
		if(con&&adT>0){//in the case both are present, text is slightly different
			iadT=" and "+adT+" Totes";
		}
		if(exT>0&&con||adT>0&&exT>0){//will only write if there was a container or totes that were put ontop
			iexT=" ontop of "+exT+" Totes.";
		}
		if(!gCom.equals("")){//adds any text in the comments afterwards
			gCom=" "+gCom;
		}
		String in=team+icon+iadT+iexT+gCom;//puts everything together
		output.append(textState(in)+"\n");//to add state tag in front
	}
	//saves everything
	public void save(){
		String print="";
		for(int i=0;i<3;i++){
			print+=red[i]+":";
		}
		print+=":";
		for(int i=0;i<3;i++){
			print+=blue[i]+":";
		}
		print+=":";
		for(int i=0;i<5;i++){
			print+=Rscore[i]+":";
		}
		print+=":";
		for(int i=0;i<5;i++){
			print+=Bscore[i]+":";
		}
		print+=":";
		printout=print+printout+"end";
		file.deleteFile("Match"+match+".txt");
		file.createFile("Match"+match+".txt");
		file.writeToFile("Match"+match+".txt", printout);
		output.append("[SYS]:The game has been saved\n");
	}
	//stops game
	public void stopGame(){
		timeout.setText("0");
		reset=true;
	}
	
	//resets printout, when game is reset
	public void clearPrint(){
		printout="";
	}
	
}
