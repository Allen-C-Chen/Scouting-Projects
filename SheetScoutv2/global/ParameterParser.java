package global;

import java.util.Vector;

public class ParameterParser {
	public static ParameterContainer parse(String s){
		String[] raw=s.split(" ");
		String[] head=raw[0].split("=");
		String type=head[0];
		String name=head[1];
		Vector<Parameter> params=new Vector<Parameter>();
		for(int i=1;i<raw.length;i++){
			try{
			String[] param=raw[i].split("=");
			params.add(new Parameter(param[0],param[1]));
			}
			catch(Exception e){
				System.out.println("invalid parameter, likely not setup correctly");
			}	
		}
		return new ParameterContainer(type,name,params);
	}
	public static class ParameterContainer{
		public String type;
		public String name;
		public Vector<Parameter> parameters;
		public ParameterContainer(String type,String name,Vector<Parameter> parameters){
			this.type=type;
			this.name=name;
			this.parameters=parameters;
		}
		public Parameter getParameter(String name){
			for(int i=0;i<parameters.size();i++){
				if(parameters.get(i).name.equals(name)){
					return parameters.get(i);
				}
			}
			return null;
		}
	}
	public static class Parameter{
		public String name;
		public String value;
		public Parameter(String name,String value){
			this.name=name;
			this.value=value;
		}
	}
}
