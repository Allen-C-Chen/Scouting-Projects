package scouter;

public class Shortcut {
	private String key;
	private String name;
	private String action;
	
	public Shortcut(String key,String name,String action){
		this.key=key;
		this.name=name;
		this.action=action;
	}
	public void setKey(String key){
		this.key=key;
	}
	public void setName(String name){
		this.name=name;
	}
	public void setAction(String action){
		this.action=action;
	}
	public String getKey(){
		return key;
	}
	public String getName(){
		return name;
	}
	public String getAction(){
		return action;
	}
}
