package global;

import java.util.HashMap;
import java.util.Vector;

import global.record.Settings;
import util.Lib;

public class ArgumentParser {
	public static ArgContainer handleArguments(String raw){
		return handleArguments(raw);
	}
	public static ArgContainer handleArguments(String[] raw){
		HashMap<String,String[]> args=new HashMap<String,String[]>();
		String arg="";
		String values="";
		String command="";
		for(String s:raw){
			if(s.startsWith(Settings.overrideArg)){
				if(!arg.equals("")){
					args.put(arg, values.substring(0, (values.length()>0?values.length()-1:0)).split(","));
				}
				arg=s.substring(1);
				values="";
			}
			else{
				values+=s+",";
			}
		}
		args.put(arg, values.substring(0, (values.length()>0?values.length()-1:0)).split(","));
		return new ArgContainer(args,raw,command);
	}
	public static String[] stringToArgs(String s){
		Vector<String> args=new Vector<String>();
		if(s.contains("\"")){
			int currentI=-1;
			int previous=0;
			while(s.indexOf("\"", currentI+1)!=-1){
				currentI=s.indexOf("\"",currentI+1);
				if(!s.substring(previous,currentI).equals("")){
					String[] toAdd=s.substring(previous,currentI).split(" ");
					previous=currentI+1;
					for(String str:toAdd){
						args.add(str);
					}
				}
				else{
					previous++;
				}
				currentI=s.indexOf("\"",currentI+1);
				args.add(s.substring(previous,currentI));
				previous=currentI+2;
			}
			if(previous<s.length()){
				String[] toAdd=s.substring(previous,s.length()).split(" ");
				for(String str:toAdd){
					args.add(str);
				}
			}
			String[] out=new String[args.size()];
			for(int i=0;i<args.size();i++){
				out[i]=args.get(i);
			}
			return out;
		}
		else{
			return s.split(" ");
		}
	}
	public static class ArgContainer{
		public String command;
		public String[] raw;
		public String rawString;
		public HashMap<String,String[]> args=new HashMap<String,String[]>();
		ArgContainer(HashMap<String,String[]> args, String[] raw, String command){
			this.command=command;
			this.raw=raw;
			this.args=args;
			rawString=Lib.arrayToString(raw);
		}
		public boolean hasArgument(String arg){
			if(args.containsKey(arg)){
				return true;
			}
			return false;
		}
		public String[] getArgumentValues(String arg){
			return args.get(arg);
		}
	}
}
