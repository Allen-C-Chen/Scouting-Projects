package global;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Scanner;

import global.ArgumentParser.ArgContainer;
import scout.StandProcess;
import scout.StandRef;
import scout.StandTeam;
import scout.StandValues;
import util.Lib;

public class Launch {
	private static String input;
	private static HashMap<StandValues,StandRef> standRefMap=new HashMap<StandValues,StandRef>();//used to be for setting specific indexes, generally deprecatd
	private static Scanner scan;//read additional inputs
	private static StandProcess standP=new StandProcess();
	public static void main(String args[]){
		scan=new Scanner(System.in);
		processCycle(args);
		while(true){
			args=ArgumentParser.stringToArgs(scan.nextLine());
			processCycle(args);
		}
	}
	
	public static void processCycle(String[] args){
		ArgContainer values=ArgumentParser.handleArguments(args);
		if(values.hasArgument("in")){
			input=values.getArgumentValues("in")[0];
		}
		if(values.hasArgument("file")){
			try{
				BufferedReader in=new BufferedReader(new FileReader(values.getArgumentValues("file")[0]));
				input="";
				while(in.ready()){
					input+="\n"+in.readLine();
				}
				in.close();
				input=input.substring(input.length()>0?1:0);
				System.out.println("file read");
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		if(values.args.containsKey("swap")){
			if(input.equals("null")){
				System.out.println(ColumnToRows.columnToRows(values.args.get("swap")[0]));
			}
			else{
				System.out.println(ColumnToRows.columnToRows(input));
			}
		}
		if(values.hasArgument("standref")){
			try{
				StandValues key=StandValues.valueOf(values.getArgumentValues("standref")[0]);
			standRefMap.put(key,new StandRef(key).setIndex(Lib.extractNumber(values.getArgumentValues("standref")[1])));
			}catch(Exception e){};
		}
		if(values.hasArgument("standvalues")){
			System.out.println(StandValues.listValues());
		}
		if(values.hasArgument("standprocess")){
			if(values.getArgumentValues("standprocess")[0].equals("form")){
				standP.addData(input, StandTeam.FormFormat());
				System.out.println("processed");
			}
			else if(values.getArgumentValues("standprocess")[0].equals("template")){
				standP.addData(input, StandTeam.GenericSheetFormat());
				System.out.println("processed");
			}
		}
		if(values.hasArgument("standprint")){
			try{
			BufferedWriter write=new BufferedWriter(new FileWriter(values.getArgumentValues("standprint")[0]));
			write.write(standP.teamData());
			write.close();
			System.out.println("success");
			}catch(Exception e){};
		}
		if(values.hasArgument("exit")){
			System.exit(0);
		}
	}
}
