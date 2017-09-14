package scout;

import util.Lib;

public enum StandValues {
	TimestampS,
	TeamNumI,
	MatchNumS,
	TeleGearNumI,
	TeleGearPullI,
	TeleGearAttemptI,
	TelePickS,
	TeleShootB,
	TeleShootRateS,
	TeleHighRateS,
	TeleLowRateS,
	TeleShootLocationS,
	SpeedI,
	DefenseI,
	DoDefenseB,
	DoClimbB,
	ClimbTimeI,
	ClimbSuccessB,
	FoulI,
	AutoB,
	AutoShootB,
	AutoShootRateS,
	AutoLowRateS,
	AutoHighRateS,
	AutoCrossB,
	AutoGearB,
	AutoPullB,
	AutoLocationS,
	NoteS;
	public static String listValues(){
		String out="";
		boolean column=false;;
		for(StandValues s:values()){
			if(column){
				column=false;
				out+="\t"+s.toString();
			}
			else{
				column=true;
				out+="\n"+Lib.pad(s.toString(),25);
			}
		}
		return out.substring(1);
	}
}
