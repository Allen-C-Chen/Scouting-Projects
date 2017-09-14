package scouterEdit;
//class holding the various field in which is used on the team panel which are used to compare and judge teams i.e. drive train or team #
//All fields will be displayed in a table in the scouting program.
public class TeamField {
	private String type;//TEXT/CHECK/NUM
	private String name;
	private String model;
	private int colWidth=0;
	private String tooltip;
	
	public TeamField(String name){
		this.name=name;
		type="TEXT";
	}
	
	//setters
	public void setType(String type){
		this.type=type;
	}
	public void setName(String name){
		this.name=name;
	}
	public void setTooltip(String tooltip){
		this.tooltip=tooltip;
	}
	public void setModel(String model){
		this.model=model;
	}
	public void setColWidth(int width){
		this.colWidth=width;
	}
	//getters
	public String getType(){
		return type;
	}
	public String getName(){
		return name;
	}
	public String getTooltip(){
		return tooltip;
	}
	public String getModel(){
		return model;
	}
	public int getColWidth(){
		return colWidth;
	}
	public String getStringRepresentation(){
		String rep=type+":"+name+":"+tooltip+":"+model+":"+colWidth;
		return rep;
	}
}
