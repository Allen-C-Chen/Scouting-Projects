package sheet;

import java.util.ArrayList;
import java.util.HashMap;

import XML.Attribute;
import XML.Elements;
import util.BooleanData;
import util.GenericData;
import util.IntegerData;
import util.StringData;
/**
 * Contains the team data, Also parses the team data into a new set of rows/columns
 * @author Allen
 *
 */
public class Data {
	private HashMap<String, function> functions=new HashMap<String,function>();
	private HashMap<String, Team> teams=new HashMap<String,Team>();
	private String name;
	private String[] colFormulas;
	public Data(Elements root){
		name=root.getAttribute("name").getValue();
		ArrayList<Elements> cols=root.getChilds("formula");
		colFormulas=new String[cols.size()];
		for(Elements e:cols){
			colFormulas[Integer.parseInt(e.getAttribute("column").getValue())]=e.getText();
		}
		constructFunctions();
	}
	public Data(String name){
		this.name=name;
		constructFunctions();
	}
	public Team getTeam(String team){
		return teams.get(team);
	}
	public boolean hasTeam(String team){
		return teams.containsKey(team);
	}
	public void addTeam(String team, Team theTeam){
		teams.put(team, theTeam);
	}
	public String parseCell(String formula, Team team){
		String out="";//output number/whatever
		String buffer="";//stores the buffer of what's currently being parsed;
		String op="";//math operation
		String[] process=formula.split("(?!^)");
		String func="";
		String tmpvar="";//holds temp variables
		Flag flag=Flag.var;//flags basically states changes based on whatever
		for(String s:process){
			if(tokens.mathop.isThis(s)&&flag==Flag.var){//token check
				if(buffer.equals("")){
					tmpvar=out;
				}
				else{
					tmpvar=buffer;
				}
				op=s;
				buffer="";
				flag=Flag.math;
			}
			else if(containsFunction(buffer)&&tokens.openbrac.isThis(s)){//function check
				if(flag==Flag.math){
					flag=Flag.mfunction;
				}
				else if(flag==Flag.var){
					flag=Flag.function;
				}
				func=buffer;
				buffer="";
			}
			else if(tokens.closebrac.isThis(s)&&flag==Flag.function){
				stringAdd(out,functions.get(func).execute(buffer.split(","), team));
				flag=Flag.var;
			}
			else if(tokens.closebrac.isThis(s)&&flag==Flag.mfunction){
				//does function then does math then adds it to output
				stringAdd(out,doMath(op,getVar(tmpvar,team),getVar(functions.get(func).execute(buffer.split(","), team),team)));
				flag=Flag.var;
			}
			else{//build buffer
				buffer+=s;
			}
		}
		//end processes stuff done at end based on what flags etc
		if(flag==Flag.var&&out.equals("")){//if no flags triggered just return the buffered var
			out=getVar(buffer,team).getAveragedValues();
		}
		else if(flag==Flag.math&&!buffer.equals("")){
			out=doMath(op,getVar(tmpvar,team),getVar(buffer,team));
		}
		else if(flag==Flag.math){
			System.out.println("error, operation symbol at end of formula");
		}
		else if(flag==Flag.function||flag==Flag.mfunction){
			System.out.println("error missing close bracket for function "+func);
		}
		
		return out;
	}
	public GenericData getVar(String var,Team team){
		GenericData data=team.getData(var);
		if(data==null){
			try{
				int i=Integer.parseInt(var);
				IntegerData datai=new IntegerData(var);
				datai.setValue("value", ""+i);
				return datai;
			}catch(Exception e){
				StringData datas=new StringData(var);
				datas.setValue("", var);
				return datas;
			}
		}
		else{
			return data;
		}
	}
	/**
	 * math operation done with floats to increase accuracy when dealing with decimals returned by boolean data
	 * @param operation math operation to be done to the two variables
	 * @param var1 first variable
	 * @param var2 second variable
	 * @return
	 */
	public String doMath(String operation,GenericData var1, GenericData var2){
		if(var1 instanceof StringData||var2 instanceof StringData){//special case for strings, other two retrun numeric stuff
			if(operation.equals("+")){
				return var1.getAveragedValues()+var2.getAveragedValues();
			}
			else{
				return "strings can only be added together";
			}
		}
		else{
			float v1=0.0f;
			float v2=0.0f;
			try{
				v1=Float.parseFloat(var1.getAveragedValues());
				v2=Float.parseFloat(var2.getAveragedValues());
			}catch(Exception e){
				return "error parsing to float";
			}
			if(operation.equals("+")){
				return ""+(v1+v2);
			}
			else if(operation.equals("-")){
				return ""+(v1-v2);
			}
			else if(operation.equals("/")){
				return ""+(v1/v2);
			}
			else if(operation.equals("*")){
				return ""+(v1*v2);
			}
			else if(operation.equals("%")){
				return ""+(v1*v2);
			}
		}
		return "";
	}
	public boolean containsFunction(String func){
		if(functions.containsKey(func)) return true;
		return false;
	}
	public Elements parseToElements(){
		Elements root=new Elements("data");
		root.addAttribute(new Attribute("name",name));
		
		Elements col;
		for(int i=0;i<colFormulas.length;i++){
			col=new Elements("formula");
			col.addAttribute(new Attribute("column",""+i));
			col.setText(colFormulas[i]);
			root.add(col);
		}
		return root;
	}
	//functions
	public String getAsBoolean(GenericData var){
		if(var instanceof BooleanData){
			float value=Float.parseFloat(((BooleanData) var).getAveragedValues());
			if(value>0.5f){//average values is based on whether it's generally true/false, which is used to convert to boolean value
				return "true";
			}
			else{
				return "false";
			}
		}
		else{
			return "error variable not boolean";
		}
	}
	private String stringAdd(String var1, String var2){
		try{
			Float float1=Float.parseFloat(var1);
			Float float2=Float.parseFloat(var2);
			return ""+(float1+float2);
		}catch(Exception e){
			return var1+var2;
		}
	}
	private void constructFunctions(){
		function tmp= new function("asBoolean"){
			public String execute(String[] parameters,Team team){
				return getAsBoolean(getVar(parameters[0],team));
			}
		};
		functions.put("asBoolean", tmp);
		
	}
	private abstract class function{
		String name;
		//name for function i.e. math() or otherwise, though math is automatically calculated
		public function(String name){
			this.name=name;
		}
		public abstract String execute(String[] parameters, Team team);
	}
	private enum Flag{
		var,//basic state, buffer is a variable if nonempty
		math,//math state, op filled, tmpvar filled, buffer is second var
		mfunction,//function w/ math operator ahead of it, does math operation after function value calculated
		function,//function // openbracket needs to be next
	}
	private enum tokens{
		var(new String[]{""}),
		mathop(new String[]{"+","-","*","/","%"}),
		openbrac(new String[]{"(","{","["}),
		closebrac(new String[]{")","}"}),
		quote(new String[]{"\'","\""});
		public String[] tags;
		tokens(String[] tags){
			this.tags=tags;
		}
		public boolean isThis(String character){
			for(String s:tags){
				if(character.equals(s)){
					return true;
				}
			}
			return false;
		}
	}
}

