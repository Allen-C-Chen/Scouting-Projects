package scout;

import java.util.Vector;

import util.Average;

public class TeamData {
	Vector<StandTeam> scoutedData=new Vector<StandTeam>();//data from stand scouters
	Vector<PitTeam> talkedData=new Vector<PitTeam>();//data from pit scouters
	Average auto=new Average();
	Average autoGears=new Average();
	Average autoPull=new Average();
	Average teleGears=new Average();
	int maxTeleGears;
	String gearPickup="";
	boolean shoot;
	String shootLocation="";
	String teleGearPickUp="";
	String highRate="";
	String lowRate="";
	Average ASpeed=new Average();
	int maxSpeed;
	boolean climb;
	Average climbTime=new Average();
	Average climbRate=new Average();
	Average defenseRate=new Average();
	int maxRate;
	Average foulRate=new Average();
	int maxFoul;
	String notes="";
	int number=-1;
	public TeamData(StandTeam team){
		scoutedData.add(team);
		number=team.TeamNum;
	}
	public TeamData(PitTeam team){
		talkedData.add(team);
		number=team.TeamNum;
	}
	public TeamData addStand(StandTeam team){
		scoutedData.add(team);
		return this;
	}
	public TeamData addPit(PitTeam team){
		talkedData.add(team);
		return this;
	}
	public void processData(){
		for(StandTeam t:scoutedData){
			if(t.valuesUsed.contains(StandValues.AutoB))auto.addBoolean(t.auto);
			if(t.valuesUsed.contains(StandValues.AutoGearB))autoGears.addBoolean(t.autoGear);
			if(t.valuesUsed.contains(StandValues.AutoPullB))autoPull.addBoolean(t.autoGearPull);
			if(t.valuesUsed.contains(StandValues.TeleGearNumI))teleGears.addInt(t.teleopGear);
			if(t.valuesUsed.contains(StandValues.TeleGearNumI))if(t.teleopGear>maxTeleGears)maxTeleGears=t.teleopGear;
			if(t.valuesUsed.contains(StandValues.AutoLocationS))gearPickup+=t.gearPickUp;
			if(t.ballshoot||t.autoShoot)shoot=true;
			if(t.valuesUsed.contains(StandValues.TeleShootLocationS))shootLocation+=t.ballLocation;
			if(t.valuesUsed.contains(StandValues.TelePickS))gearPickup+=t.teleGearPickUp;
			if(t.valuesUsed.contains(StandValues.TeleHighRateS))highRate+=t.highRate;
			if(t.valuesUsed.contains(StandValues.TeleLowRateS))lowRate+=t.lowRate;
			if(t.valuesUsed.contains(StandValues.SpeedI))ASpeed.addInt(t.speed);
			if(t.speed>maxSpeed)maxSpeed=t.speed;
			if(t.climbed)climb=true;
			if(t.valuesUsed.contains(StandValues.ClimbTimeI))climbTime.addInt(t.climbTime);
			if(t.valuesUsed.contains(StandValues.ClimbSuccessB))climbRate.addBoolean(t.climbed);
			if(t.valuesUsed.contains(StandValues.DefenseI))defenseRate.addInt(t.defenseRate);
			if(t.defenseRate>maxRate)maxRate=t.defenseRate;
			if(t.valuesUsed.contains(StandValues.FoulI))foulRate.addInt(t.foul);
			if(t.foul>maxFoul)maxFoul=t.foul;
			if(t.valuesUsed.contains(StandValues.NoteS))notes+=t.match+":"+t.notes;
		}
	}
	public String getSheetLine(){
		String s=number+"\t"
				+auto+ "\t"
				+autoPull+ "\t"
				+teleGears+"\t"
				+ maxTeleGears+"\t"
				+ gearPickup+"\t"
				+ shoot+"\t"
				+ highRate+"\t"
				+ lowRate+"\t"
				+ ASpeed+"\t"
				+ maxSpeed+"\t"
				+ climb+"\t"
				+ climbTime+"\t"
				+ climbRate+"\t"
				+ defenseRate+"\t"
				+ maxRate+"\t"
				+foulRate+"\t"
				+maxFoul+"\t"
				+notes;
		return s;
	}
}
