package scout;

import java.util.Arrays;
import java.util.Vector;

import global.record.Settings;
import util.Lib;

/**
 * Data from stands form to process
 * @author Allen
 *
 */
public class StandTeam {
	public Vector<StandValues> valuesUsed=new Vector<StandValues>();
	public String[] data;
	/**
	 * Time at which the response was recorded
	 */
	String timestamp;
	/**
	 * Team number for which this response is for
	 */
	int TeamNum;
	String match;
	/**
	 * Number representing number of gears placed by team in teleop
	 */
	int teleopGear;
	/**
	 * whether team has placed gear in autonomous
	 */
	int teleopGearPull;
	int teleopGearAttempts;
	boolean autoGear;
	boolean autoGearPull;
	String gearPlace;
	String gearPickUp="";
	boolean gearUp;
	/**
	 * whether a team has a significant auto or not
	 */
	boolean auto;
	String autoShootRate;
	boolean autoLine;
	boolean autoShoot;
	String highRate="";
	String lowRate="";
	/**
	 * whether a team can shoot at all
	 */
	boolean ballshoot;
	/**
	 * Location where they pickup balls;
	 */
	String shootRate;
	String teleGearPickUp;
	String ballLocation;
	/**
	 * if the team can shoot high
	 */
	boolean low;
	/**
	 * if the team can shoot low
	 */
	boolean high;
	/**
	 * speed of the bot subjective based on the person
	 */
	int speed;
	boolean defense=false;
	int defenseRate;
	boolean climb;
	boolean climbed;
	int climbTime;
	int foul;
	String notes="";
	public StandTeam(String info){
		String[] data=info.split("\t");
		System.out.println(Arrays.toString(data));
		this.data=data;
//		timestamp=data[0];
//		try{
//			TeamNum=Integer.parseInt(data[1]);
//		}
//		catch(NumberFormatException e){
//			TeamNum=-1;//error number
//		}
//		try{
//			teleopGear=Integer.parseInt(data[2]);
//		}
//		catch(NumberFormatException e){
//			teleopGear=-1;//error number
//		}
//		if(data[3].equals("Yes")){
//			autoGear=true;
//		}
//		if(data[4].equals("Yes")){
//			auto=true;
//		}
//		if(data[5].equals("Yes")){
//			ballshoot=true;
//		}
//		if(data[6].toLowerCase().contains("low")){
//			low=true;
//		}
//		if(data[6].toLowerCase().contains("high")){
//			high=true;
//		}
//		try{
//			speed=Integer.parseInt(data[7]);
//		}
//		catch(NumberFormatException e){
//			speed=-1;//error number
//		}
//		if(data[8].equals("yes")){
//			defense=true;
//		}
//		try{
//			defenseRate=Integer.parseInt(data[9]);
//		}
//		catch(NumberFormatException e){
//			defenseRate=-1;//error number
//		}
//		if(data[9].equals("Yes")){
//			climb=true;
//		}
//		try{
//			climbTime=Integer.parseInt(data[10]);
//		}
//		catch(NumberFormatException e){
//			climbTime=-1;//error number
//		}
//		gearPlace=data[11];
//		try{
//			foul=Integer.parseInt(data[12]);
//		}
//		catch(NumberFormatException e){
//			foul=-1;//error number
//		}
//		match=data[13];
	}
	/**
	 * Sets some stuff that might be missed etc.
	 */
	public void cleanup(){
		if(climbed){
			climb=true;
			valuesUsed.add(StandValues.DoClimbB);
		}
		if(autoGearPull){
			autoGear=true;
			valuesUsed.add(StandValues.AutoGearB);
		}
		if(defenseRate>0){
			defense=true;
			valuesUsed.add(StandValues.DoDefenseB);
		}
		if(autoGear||autoLine||autoShoot){
			auto=true;
			valuesUsed.add(StandValues.AutoB);
		}
	}
	public static StandRef[] FormFormat(){
		StandRef[] ref=new StandRef[]{new StandRef(StandValues.TimestampS).setIndex(0),
				new StandRef(StandValues.TeamNumI).setIndex(1),
				new StandRef(StandValues.TeleGearNumI).setIndex(2),
				new StandRef(StandValues.AutoGearB).setIndex(3),
				new StandRef(StandValues.AutoB).setIndex(4),
				new StandRef(StandValues.TeleShootB).setIndex(5),
				new StandRef(StandValues.TeleShootLocationS).setIndex(6),
				new StandRef(StandValues.SpeedI).setIndex(7),
				new StandRef(StandValues.DoDefenseB).setIndex(8),
				new StandRef(StandValues.DefenseI).setIndex(9),
				new StandRef(StandValues.ClimbSuccessB).setIndex(10),
				new StandRef(StandValues.ClimbTimeI).setIndex(11),
				new StandRef(StandValues.AutoLocationS).setIndex(12),
				new StandRef(StandValues.FoulI).setIndex(13),
				new StandRef(StandValues.MatchNumS).setIndex(14)
				
		};
		return ref;
	}
	public static StandRef[] GenericSheetFormat(){
		int autoStart=2;
		int teleStart=9;
		int endGame=19;
		StandRef[] ref=new StandRef[]{
				new StandRef(StandValues.TeamNumI).setIndex(0),
				new StandRef(StandValues.MatchNumS).setIndex(1),
				new StandRef(StandValues.AutoCrossB).setIndex(autoStart),
				new StandRef(StandValues.AutoGearB).setIndex(autoStart+1),
				new StandRef(StandValues.AutoPullB).setIndex(autoStart+2),
				new StandRef(StandValues.TeleShootRateS).setIndex(autoStart+3),
				new StandRef(StandValues.AutoShootB).setIndex(autoStart+4),
				new StandRef(StandValues.AutoLocationS).setIndex(autoStart+5),
				new StandRef(StandValues.AutoShootRateS).setIndex(autoStart+6),
				new StandRef(StandValues.TeleGearNumI).setIndex(teleStart),
				new StandRef(StandValues.TeleGearAttemptI).setIndex(teleStart+1),
				new StandRef(StandValues.TeleGearPullI).setIndex(teleStart+2),
				new StandRef(StandValues.TelePickS).setIndex(teleStart+3),
				new StandRef(StandValues.TeleShootB).setIndex(teleStart+4),
				new StandRef(StandValues.TeleShootLocationS).setIndex(teleStart+5),
				new StandRef(StandValues.TeleHighRateS).setIndex(teleStart+6),
				new StandRef(StandValues.TeleLowRateS).setIndex(teleStart+7),
				new StandRef(StandValues.SpeedI).setIndex(teleStart+8),
				new StandRef(StandValues.DefenseI).setIndex(teleStart+9),
				new StandRef(StandValues.DoClimbB).setIndex(endGame),
				new StandRef(StandValues.ClimbSuccessB).setIndex(endGame+1),
				new StandRef(StandValues.ClimbTimeI).setIndex(endGame+2),
				new StandRef(StandValues.FoulI).setIndex(endGame+3),
				new StandRef(StandValues.NoteS).setIndex(endGame+4)
		};
		return ref;
	}
	@Override
	public String toString() {
		return "StandTeam [data=" + Arrays.toString(data) + ", timestamp=" + timestamp + ", TeamNum=" + TeamNum
				+ ", match=" + match + ", teleopGear=" + teleopGear + ", autoGear=" + autoGear + ", gearPlace="
				+ gearPlace + ", gearPickUp=" + gearPickUp + ", gearUp=" + gearUp + ", auto=" + auto + ", autoLine="
				+ autoLine + ", autoShoot=" + autoShoot + ", ballshoot=" + ballshoot + ", ballPickUp=" + teleGearPickUp
				+ ", ballLocation=" + ballLocation + ", low=" + low + ", high=" + high + ", speed=" + speed
				+ ", defense=" + defense + ", defenseRate=" + defenseRate + ", climb=" + climb + ", climbed=" + climbed
				+ ", climbTime=" + climbTime + ", foul=" + foul + ", notes=" + notes + "]";
	}
	public StandTeam process(StandRef[] format){
		for(StandRef ref:format){
			setValue(ref);
		}
		return this;
	}
	public void setValue(StandRef reference){
		if(!data[reference.index].equals(""))valuesUsed.add(reference.name);
		switch(reference.name){
		case AutoCrossB:autoLine=Lib.grabArrayBoolean(data, reference.index, Settings.validBoolean);
			break;
		case AutoGearB:autoGear=Lib.grabArrayBoolean(data, reference.index, Settings.validBoolean);
			break;
		case AutoHighRateS:highRate+="A:"+data[reference.index];
			break;
		case AutoLocationS:gearPickUp+=data[reference.index];
			break;
		case AutoLowRateS:lowRate+="A:"+data[reference.index];
			break;
		case AutoShootB:autoShoot=Lib.grabArrayBoolean(data, reference.index, Settings.validBoolean);
			break;
		case ClimbSuccessB:climbed=Lib.grabArrayBoolean(data, reference.index, Settings.validBoolean);
			break;
		case ClimbTimeI:climbTime=Lib.grabArrayInt(data, reference.index);
			break;
		case DefenseI:defenseRate=Lib.grabArrayInt(data, reference.index);
			break;
		case DoClimbB:climb=Lib.grabArrayBoolean(data, reference.index, Settings.validBoolean);
			break;
		case DoDefenseB:defense=Lib.grabArrayBoolean(data, reference.index, Settings.validBoolean);
			break;
		case FoulI:foul=Lib.grabArrayInt(data, reference.index);
			break;
		case MatchNumS:match=data[reference.index];
			break;
		case SpeedI:speed=Lib.grabArrayInt(data, reference.index);
			break;
		case TeamNumI:TeamNum=Lib.grabArrayInt(data, reference.index);
			break;
		case TeleGearNumI:teleopGear=Lib.grabArrayInt(data, reference.index);
			break;
		case TeleHighRateS:highRate+=data[reference.index];
			break;
		case TeleShootLocationS:ballLocation=data[reference.index];
			break;
		case TeleLowRateS:lowRate+=data[reference.index];
			break;
		case TelePickS:teleGearPickUp=data[reference.index];
			break;
		case TeleShootB:ballshoot=Lib.grabArrayBoolean(data, reference.index, Settings.validBoolean);
			break;
		case AutoB:auto=Lib.grabArrayBoolean(data, reference.index, Settings.validBoolean);
			break;
		case TimestampS:timestamp=data[reference.index];
			break;
		case AutoPullB:autoGearPull=Lib.grabArrayBoolean(data, reference.index, Settings.validBoolean);
			break;
		case AutoShootRateS:autoShootRate=data[reference.index];
			break;
		case TeleGearPullI:teleopGearPull=Lib.grabArrayInt(data, reference.index);
			break;
		case TeleShootRateS:shootRate=data[reference.index];
			break;
		case NoteS:notes=data[reference.index];
			break;
		default:
			break;
		
		}
	}
}
