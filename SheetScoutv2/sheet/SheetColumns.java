package sheet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;
import java.util.Vector;

import util.BooleanData;
import util.GenericData;
import util.IntegerData;
import util.StringData;

/**
 * to store information on which data piece belongs to which column for the specific sheet 
 * Also to note where the column for the match is
 * @author Allen
 *
 */
public class SheetColumns {
	@Override
	public String toString() {
		return "SheetColumns [matchCol=" + matchCol + ", teamCol=" + teamCol + ", data=" + data + ", columns=" + columns
				+ "]";
	}
	public static GenericData matchColumn=new StringData("match").setDescription("match recorded");
	public static GenericData teamColumn=new StringData("team").setDescription("team number");
	private int matchCol;
	private int teamCol;
	private HashMap<String,Vector<GenericData>> data=new HashMap<String,Vector<GenericData>>();
	private Vector<GenericData> columns=new Vector<GenericData>();//generic layout for columns passed to data hashmap
	public SheetColumns(){
	}
	public SheetColumns addColumn(GenericData column){
		columns.add(column);
		if(column.equals(matchColumn)){
			matchCol=columns.size()-1;
		}
		else if(column.equals(teamColumn)){
			teamCol=columns.size()-1;
		}
		return this;
	}
	public SheetColumns addMatchColumn(){
		columns.add(matchColumn);
		matchCol=columns.size()-1;
		return this;
	}
	public SheetColumns addTeamColumn(){
		columns.add(teamColumn);
		teamCol=columns.size()-1;
		return this;
	}
	public SheetColumns parse(String data) throws IllegalArgumentException{
		String[] dataV=data.split("\t");
		return parse(dataV);
	}
	public SheetColumns parse(String[] dataV) throws IllegalArgumentException{
		if(dataV.length<matchCol+1){
			throw new IllegalArgumentException("match column index beyond largest index");
		}
		else if(dataV.length<teamCol+1){
			throw new IllegalArgumentException("team column index beyond largest index");
		}
		try{
		String match=dataV[matchCol];
		String team=dataV[teamCol];
		Vector<GenericData> colData;
		if(data.containsKey(team)){
			colData=data.get(team);
		}
		else{
			colData=columnsClone();
		}
		for(int i=0;i<dataV.length;i++){
			colData.get(i).setValue(match, dataV[i]);
		}
		data.put(team, colData);
		}catch(Exception e){
			System.out.println("error parsing"+Arrays.toString(dataV));
		}
		return this;
	}
	public GenericData getData(String team, int index){
		return data.get(team).get(index);
	}
	public Vector<GenericData> getData(String team){
		return data.get(team);
	}
	public Set<String> getTeams(){
		return data.keySet();
	}
	public static SheetColumns read(File file) throws IOException{
		BufferedReader in=new BufferedReader(new FileReader(file));
		SheetColumns sheet=new SheetColumns();
		while(in.ready()){
			sheet.addColumn(interpret(in.readLine()));
		}
		in.close();
		return sheet;
	}
	private static GenericData interpret(String s){
		if(s.equals("match")){
			return matchColumn;
		}
		else if(s.equals("team")){
			return teamColumn;
		}
		else{
			String[] data=s.split("=");
			if(data[0].equals("boolean")){
				return new BooleanData(data[1]).addTrue("yes").addTrue("Y").addTrue("Yes").addTrue("y");
			}
			else if(data[0].equals("string")){
				return new StringData(data[1]);
			}
			else if(data[0].equals("int")){
				return new IntegerData(data[1]);
			}
		}
		return null;
	}
	private Vector<GenericData> columnsClone(){
		Vector<GenericData> clone=new Vector<GenericData>();
		for(int i=0;i<columns.size();i++){
			clone.add(columns.get(i).clone());
		}
		return clone;
	}
	public static void main(String[] args) throws IOException{
		System.out.println(SheetColumns.read(new File("/Users/Allen/Desktop/null copy.txt")));
	}
}
