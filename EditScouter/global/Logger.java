package global;

public class Logger {
	//class that logs actions
	private String log="";
	private String name;
	public Logger(String name) {
		this.name=name;
	}
	public void addLog(String log){
		this.log+=log+"\n";
	}
	public String getLog(){
		return log;
	}
	public String getName(){
		return name;
	}

}
