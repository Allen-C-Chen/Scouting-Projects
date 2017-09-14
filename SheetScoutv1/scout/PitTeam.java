package scout;

import util.Lib;

public class PitTeam {
	String timestamp;
	int TeamNum;
	int autoGear=0;
	int teleopGear=0;
	boolean left;
	boolean right;
	boolean center;
	int high;
	int low;
	int speed;
	boolean climb;
	int climbSpeed;
	boolean defense;
	int defenseRate;
	public PitTeam(String info){
		String[] data=info.split("\t");
		timestamp=data[0];
		try{
			TeamNum=Integer.parseInt(data[1]);
		}
		catch(NumberFormatException e){
			TeamNum=-1;//error number
		}
		if(data[2].equals("Yes")){
			autoGear=1;
		}
		else if(data[2].equals("Maybe")){
			autoGear=2;
		}
		if(data[3].toLowerCase().contains("left")){
			left=true;
		}
		if(data[3].toLowerCase().contains("right")){
			right=true;
		}
		if(data[3].toLowerCase().contains("center")){
			center=true;
		}
		try{
			high=Integer.parseInt(data[4]);
		}
		catch(NumberFormatException e){
			high=-1;//error number
		}
		try{
			low=Integer.parseInt(data[5]);
		}
		catch(NumberFormatException e){
			low=-1;//error number
		}
		try{
			speed=Integer.parseInt(data[9]);
		}
		catch(NumberFormatException e){
			speed=-1;//error number
		}
		if(data[7].equals("Yes")){
			climb=true;
		}
		climbSpeed=Lib.grabArrayInt(data, 8);
		if(data[9].equals("Yes")){
			defense=true;
		}
		defenseRate=Lib.grabArrayInt(data, 10);
		teleopGear=Lib.grabArrayInt(data, 11);
	}
}
