package data;

public enum MatchTypes {//A basic Enum to hold the different match types
	Qualifier ("Qual"),
	Quarter ("Quarter"),
	Semi ("Semi"),
	Final ("Final");
	
	private final String value;
	
	MatchTypes(String value){
		this.value=value;
	}
	public String value(){return value;}
}