package scouter;

import java.util.Arrays;
/**
 * data on the matches
 * @author Allen
 *
 */
public class Match {
	//arrays on the team #s
	private int[] red=new int[3];
	private int[] blue=new int[3];
	//arrays on the scores, each value a differnet socre element
	private int[] Rscore=new int[5];
	private int[] Bscore=new int[5];
	private String[] comments;
	private String[] comStat;
	private String num;
	private boolean blank;
	//Initializes a match instance and sorts data into respective sections
	public Match(String data,String num){
		this.num=num;
		try {
			System.out.println(data);
			String[] dataParts=data.split("::");
			String[] redS=dataParts[0].split(":");//uses redS as a middle variable
			String[] blueS=dataParts[1].split(":");//same as above
			String[] RscoreS=dataParts[2].split(":");//same
			String[] BscoreS=dataParts[3].split(":");//same
			//sets all the arrays
			red[0]=Integer.parseInt(redS[0]);
			red[1]=Integer.parseInt(redS[1]);
			red[2]=Integer.parseInt(redS[2]);
			blue[0]=Integer.parseInt(blueS[0]);
			blue[1]=Integer.parseInt(blueS[1]);
			blue[2]=Integer.parseInt(blueS[2]);
			for(int i=0;i<5;i++){
			  Rscore[i]=Integer.parseInt(RscoreS[i]);
			}
			for(int i=0;i<5;i++){
				Bscore[i]=Integer.parseInt(BscoreS[i]);
			}
			comments=new String[dataParts.length-4];
			comStat=new String[dataParts.length-4];
			for(int i=0;i<dataParts.length-4;i++){
				String[] com=dataParts[i+4].split(":");
				
				comStat[i]=com[0];
				
				try {
					comments[i]=com[1];
				} catch (ArrayIndexOutOfBoundsException e) {
					System.out.println("End of Match File");
				}
			}
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			blank=true;
		}
		 
	}
	@Override
	public String toString() {
		return "Match [red=" + Arrays.toString(red) + ", blue="
				+ Arrays.toString(blue) + ", Rscore=" + Arrays.toString(Rscore)
				+ ", Bscore=" + Arrays.toString(Bscore) + ", comments="
				+ Arrays.toString(comments) + ", comStat="
				+ Arrays.toString(comStat) + ", num=" + num + ", blank="
				+ blank + "]";
	}
	//setters and getters for red team
	public int[] getRedA(){
		return red;
	}
	public int getRed(int index){
		return red[index];
	}
	public void setRedA(int[] red){
		this.red=red;
	}
	public void setRed(int index,int red){
		this.red[index]=red;
	}
	//setters and getters for blue team
	public int[] getBlueA(){
		return blue;
	}
	public int getBlue(int index){
		return blue[index];
	}
	public void setBlueA(int[] blue){
		this.blue=blue;
	}
	public void setBlue(int index,int blue){
		this.red[index]=blue;
	}
	//setters and getters for red scores
	public int[] getRscoreA(){
		return Rscore;
	}
	public int getRscore(int index){
		return Rscore[index];
	}
	public void setRscoreA(int[] Rscore){
		this.Rscore=Rscore;
	}
	public void setRscore(int index,int Rscore){
		this.Rscore[index]=Rscore;
	}
	//setters and getters for blue score
	public int[] getBscoreA(){
		return Bscore;
	}
	public int getBscore(int index){
		return Bscore[index];
	}
	public void setBscoreA(int[] Bscore){
		this.Bscore=Bscore;
	}
	public void setBscore(int index,int Bscore){
		this.Bscore[index]=Bscore;
	}
	//setters and getters for comments
	public String[] getCommentsA(){
		return comments;
	}
	public String getComments(int index){
		return comments[index];
	}
	public void setCommentsA(String[] comments){
		this.comments=comments;
	}
	public void setComments(int index,String comments){
		this.comments[index]=comments;
	}
	//setters and getters for comments status
	public String[] getComStatA(){
		return comStat;
	}
	public String getComStat(int index){
		return comStat[index];
	}
	public void setComStatA(String[] comStat){
		this.comStat=comStat;
	}
	public void setComStat(int index,String comStat){
		this.comStat[index]=comStat;
	}
	//setters and getters for match number
	public String getNum(){
		return num;
	}
	public void setNum(String num){
		this.num=num;
	}
	
}
