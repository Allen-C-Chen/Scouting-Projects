package sheet;

public class Sequence {
	public static int averageType=0;
	public static int triggerType=1;
	public static int intMax=2;
	public static int intMin=3;
	public static int stringfirst=4;
	public String name;
	public int type;
	public int index;//seems to be useless
	public Sequence(String name, int type){
		this.name=name;
		this.type=type;
	}
}
