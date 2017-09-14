package sheet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Vector;

import exceptions.InvalidFormatException;
import global.ParameterParser;
import global.ParameterParser.ParameterContainer;
import util.GenericData;
import util.IntegerData;
import util.StringData;
import util.trigger.BooleanTrigger;
import util.trigger.ComparisonTrigger;
import util.trigger.Trigger;

/**
 * class to sequence the data being printed
 * @author Allen
 *
 */
public class SheetSequencer {
	private Vector<Sequence> sequence=new Vector<Sequence>();
	private HashMap<String,Trigger> triggers=new HashMap<String,Trigger>();
	public SheetSequencer(){
	}
	public SheetSequencer addTrigger(Trigger trigger,String name){
		triggers.put(name, trigger);
		return this;
	}
	public SheetSequencer addSequence(Sequence sequence){
		this.sequence.add(sequence);
		return this;
	}
	public String sequence(Vector<GenericData> data){
		HashMap<String,Integer> refs=new HashMap<String,Integer>();
		for(int i=0;i<data.size();i++){
			refs.put(data.get(i).getName(), i);
		}
		String s="";
		for(int i=0;i<sequence.size();i++){
			String next="";
			Sequence seq=sequence.get(i);
			switch(seq.type){
			case 0:next=data.get(refs.get(seq.name)).getAveragedValues();
			break;
			case 1:
				try {
					next=""+triggers.get(seq.name).pushData(data.get(refs.get(seq.name)).getData()).isTriggered();
				} catch (InvalidFormatException e) {
					next="error";
				}
				break;
			case 2:
				if(data.get(refs.get(seq.name))instanceof IntegerData){
					next = ((IntegerData)data.get(refs.get(seq.name))).getMaxValue();
				}
				else{
					next="error";
				}
				break;
			case 3:
				if(data.get(refs.get(seq.name))instanceof IntegerData){
					next = ((IntegerData)data.get(refs.get(seq.name))).getMinValue();
				}
				else{
					next="error";
				}
				break;
			case 4:
				if(data.get(refs.get(seq.name))instanceof StringData){
					next=((StringData)data.get(refs.get(seq.name))).getFirst();
				}
			default:
				break;
			}
			s+="\t"+next;
		}
		return s.substring(1);
	}
	public static SheetSequencer read(File file) throws IOException{
		BufferedReader in =new BufferedReader(new FileReader(file));
		SheetSequencer sheet=new SheetSequencer();
		Vector<String> data=new Vector<String>();
		while(in.ready()){
			data.add(in.readLine());
		}
		for(int i=0;i<data.size();i++){
			ParameterContainer params=ParameterParser.parse(data.get(i));
			if(params.type.equals("sequence")){
				Sequence sequence=new Sequence(params.name, Integer.parseInt(params.getParameter("type").value));
				sheet.addSequence(sequence);
			}
			else if(params.type.equals("BooleanTrigger")){
				BooleanTrigger trig=new BooleanTrigger(params.getParameter("trigger")==null?true:params.getParameter("trigger").value.equals("true")?true:false);
				sheet.addTrigger(trig, params.name);
			}
			else if(params.type.equals("ComparisonTrigger")){
				ComparisonTrigger trig=new ComparisonTrigger(Integer.parseInt(params.getParameter("base").value), Integer.parseInt(params.getParameter("type").value));
				sheet.addTrigger(trig, params.name);
			}
		}
		in.close();
		return sheet;
	}
	public static void main(String[] args) throws IOException{
		SheetSequencer.read(new File("/Users/Allen/Desktop/null copy 2.txt"));
	}

}
