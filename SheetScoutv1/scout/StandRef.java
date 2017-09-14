package scout;

public class StandRef {
	public StandValues name;
	public int index;
	public String value;
	public StandRef(StandValues name){
		this.name=name;
	}
	public StandRef(StandValues name, String value){
		this.value=value;
		this.name=name;
	}
	public StandRef setIndex(int index){
		this.index=index;
		return this;
	}
}
