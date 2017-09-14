package scouterEdit;

public class ScoreField {
	private String name;
	private boolean addToScore=true;
	private int Rvalue=0;//to auto calculate and fillscore based on what happened during game...if I ever get that far
	private int Bvalue=0;
	private int width=0;
	private String tooltip;
	
	public ScoreField(String name){
		this.name=name;
		tooltip="null";
	}
	//setters
	public void setName(String name){
		this.name=name;
	}
	public void setAddToScore(boolean addToScore){
		this.addToScore=addToScore;
	}
	public void setRValue(int value){
		this.Rvalue=value;
	}
	public void setBValue(int value){
		this.Bvalue=value;
	}
	public void setValue(String values){
		String[] value= values.split(".");
		try {
			Rvalue=Integer.parseInt(value[0]);
		} catch (Exception e1) {}
		try {
			Bvalue=Integer.parseInt(value[1]);
		} catch (Exception e) {}
	}
	public void setWidth(int width){
		this.width=width;
	}
	public void setTooltip(String tooltip){
		this.tooltip=tooltip;
	}
	//getters
	public String getName(){
		return name;
	}
	public boolean getAddToScore(){
		return addToScore;
	}
	public int getRValue(){
		return Rvalue;
	}
	public int getBValue(){
		return Bvalue;
	}
	public int getWidth(){
		return width;
	}
	public String getTooltip(){
		return tooltip;
	}
	public String getStringRepresentation(){
		String rep=name+":"+addToScore+":"+Rvalue+"."+Bvalue+":"+tooltip+":"+width;
		return rep;
	}
}
