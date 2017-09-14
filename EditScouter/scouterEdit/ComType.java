package scouterEdit;

public enum ComType{
	TextField("String","String"),
	Button("boolean","boolean"),
	CheckBox("boolean","boolean"),
	ComboBox("int","String"),
	RadioButton("boolean","boolean"),
	ToggleButton("boolean","boolean"),
	TextArea("String","String"),
	TextPane("String","String"),
	Spinner("int","int"),
	List("int","String"),
	Slider("int","int"),
	Label("String","String"),
	Null("null","null");
	
	public String setType;
	public String returnType;
	
	private ComType(String setType, String returnType){
		this.setType=setType;
		this.returnType=returnType;
	}
	public static ComType getType(String type){
		if(type.equals("TextField")||type.equals("Text Field")){
			return ComType.TextField;
		}
		else if(type.equals("Button")){
			return ComType.Button;
		}
		else if(type.equals("CheckBox")||type.equals("Check Box")){
			return ComType.CheckBox;
		}
		else if(type.equals("ComboBox")||type.equals("Combo Box")){
			return ComType.ComboBox;
		}
		else if(type.equals("RadioButton")||type.equals("Radio Button")){
			return ComType.RadioButton;
		}
		else if(type.equals("ToggleButton")||type.equals("Toggle Button")){
			return ComType.ToggleButton;
		}
		else if(type.equals("TextArea")||type.equals("Text Area")){
			return ComType.TextArea;
		}
		else if(type.equals("TextPane")||type.equals("Text Pane")){
			return ComType.TextPane;
		}
		else if(type.equals("Spinner")){
			return ComType.Spinner;
		}
		else if(type.equals("List")){
			return ComType.List;
		}
		else if(type.equals("Slider")){
			return ComType.Slider;
		}
		else if(type.equals("Label")){
			return ComType.Label;
		}
		else{
			return ComType.Null;
		}
	}
}
