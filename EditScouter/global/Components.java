package global;

import scouterEdit.ComType;

public class Components {
	private String name;
	private Location location;
	private ComType type;
	private String otherInfo="0:0:0:null";//Max,Min,Interval,Grouping //All other data specific to type saved as string so there's no useless variables
	private String returnType;
	private String label;
	private String Tooltip;
	private String action;
	private String defaultVar="null";
	private String[] resets;//for the presets? if component is preset will have preset name.//or extend component to have greater control over things.
	/* data for what is stored in the other info string
	 * >>TextBox 
	 * 
	 * 
	 * 
	 */
	public Components(String name, ComType type, Location location, String otherInfo,String label){
		this.name=name;
		this.type=type;
		this.location=location;
		this.otherInfo=otherInfo;
		//sets return type string based on given information on what component it is
		if(type.equals("Text Field")){
			this.returnType="String";
		}
		else if(type.equals("Button")){
			this.returnType="boolean";
		}
		else if(type.equals("Check Box")){
			this.returnType="boolean";
		}
		else if(type.equals("Combo Box")){
			this.returnType="String";
		}
		else if(type.equals("Radio Button")){
			this.returnType="String";
		}
		else if(type.equals("Toggle Button")){
			this.returnType="boolean";
		}
		else if(type.equals("Text Area")){
			this.returnType="String";
		}
		else if(type.equals("Text Pane")){
			this.returnType="String";
		}
		else if(type.equals("Spinner")){
			this.returnType="int";
		}
		else if(type.equals("List")){
			this.returnType="String";
		}
		else if(type.equals("Slider")){
			this.returnType="int";
		}
		else if(type.equals("Label")){
			this.returnType="String";
		}
		this.label=label;
	}
	
	//setters
	public void setName(String name){
		this.name=name;
	}
	public void setLocation(Location location){
		this.location=location;
	}
	public void setType(ComType type){
		this.type=type;
	}
	public void setOtherInfo(String otherInfo){
		this.otherInfo=otherInfo;
	}
	public void setReturnType(String returnType){
		this.returnType=returnType;
	}
	public void setLabel(String label){
		this.label=label;
	}
	public void setTooltip(String Tooltip){
		this.Tooltip=Tooltip;
	}
	public void setMax(int Max){
		String[] s=otherInfo.split(":");
		System.out.println("Max l "+s.length);
		otherInfo=""+Max+":";
		for(int i=1;i<s.length-1;i++){
			otherInfo=otherInfo+s[i]+":";
		}
		otherInfo=otherInfo+s[s.length-1];
		System.out.println("Max "+otherInfo);
	}
	public void setMin(int Min){
		String[] s=otherInfo.split(":");
		otherInfo=""+s[0]+":"+Min+":";
		for(int i=2;i<s.length-1;i++){
			otherInfo=otherInfo+s[i]+":";
		}
		otherInfo=otherInfo+s[s.length-1];
	}
	public void setInterval(int Interval){
		String[] s=otherInfo.split(":");
		System.out.println(s.length);
		otherInfo=""+s[0]+":"+s[1]+":"+Interval+":";
		for(int i=3;i<s.length-1;i++){
			otherInfo=otherInfo+s[i]+":";
		}
		otherInfo=otherInfo+s[s.length-1];
	}
	public void setGrouping(String Grouping){
		String[] s=otherInfo.split(":");
		otherInfo="";
		for(int i=0;i<3;i++){
			otherInfo=otherInfo+s[i]+":";
		}
		otherInfo=otherInfo+Grouping+":";
		for(int i=4;i<s.length-1;i++){
			
		}
	}
	public void setAction(String action){
		this.action=action;
	}
	public void setDefaultVar(String defaultVar){
		this.defaultVar=defaultVar;
	}
	public void setResets(String[] resets){
		this.resets=resets;
	}
	//getters
	public String getName(){
		return name;
	}
	public Location getLocation(){
		return location;
	}
	/**
	 * 
	 * @return returns the type
	 * 	Available types are 
	 * "Text Field"/"Button"/"Check Box"/"Combo Box"/"Radio Button"/"Toggle Button"/"Text Area"/"Text Pane"/"Spinner"/"List"/"Slider"/"Label"
	 */
	public ComType getType(){
		return type;
	}
	public String getOtherInfo(){
		return otherInfo;
	}
	public String getReturnType(){
		return returnType;
	}
	public String getLabel(){
		return label;
	}
	public String getTooltip(){
		return Tooltip;
	}
	public int getMax(){
		String[] s=otherInfo.split(":");
		try{
			return Integer.parseInt(s[0]);
		}
		catch(Exception e){
			return 0;
		}
	}
	public int getMin(){
		String[] s=otherInfo.split(":");
		try{
			return Integer.parseInt(s[1]);
		}
		catch(Exception e){
			return 0;
		}
	}
	public int getInterval(){
		String[] s=otherInfo.split(":");
		try{
			return Integer.parseInt(s[2]);
		}
		catch(Exception e){
			return 0;
		}
	}
	public String getGrouping(){
		String[] s=otherInfo.split(":");
		return s[3];
	}
	public String getAction(){
		return action;
	}
	public String getDefaultVar(){
		return defaultVar;
	}
	public String[] getResets(){
		return resets;
	}
	public static boolean isPresetName(String name){//TODO complete list
		String[] presetNames=new String[]{"Timer","ShortcutBox","CommentBox","StartButton","TeamSelect"};//Expand presets by adding names to here
		for(int i=0;i<presetNames.length;i++){
			if(name.equals(presetNames[i])){
				return true;
			}
		}
		return false;
	}
	public static ComType presetType(String name){
		if(name.equals("Timer")||name.equals("ShortcutBox")||name.equals("CommentBox")){
			return ComType.TextField;
		}
		else if(name.equals("StartButton")){
			return ComType.Button;
		}
		else if (name.equals("TeamSelect")){
			return ComType.ComboBox;
		}
		return null;
	}
	//returns a string representation of the values of the components, used in saving
	public String getStringRepresentation(){
		String Rep=""+name+","+type+","+Tooltip+","+action+","+location.getX()+","+location.getY()
		+","+location.getW()+","+location.getH()+","+otherInfo+","+label+","+defaultVar;
		return Rep;
	}
	
}
