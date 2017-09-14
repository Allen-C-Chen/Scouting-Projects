package scouter;
import javax.swing.*;
//This class is mainly used to start the timer thread
public class s1 extends Thread {
	private Thread t;
	private boolean debug;
	private String name;
	private int matchTime;
	private JTextField timeout;
	private scout s;
	//basic setup of thread,gets name and whether debugging or not
	public s1(String name,boolean debug){
		this.name=name;
		this.debug=debug;
		if(debug){
			System.out.println("Creating: "+name);
		}
	}
	//starts thread
	public void start(JTextField timeout,int matchTime,scout s){
		this.timeout=timeout;
		this.matchTime=matchTime;
		this.s=s;
		if(debug){
			System.out.println("Starting: "+name);
		}
		t=new Thread(this,name);
		t.start();
	}
	//executes right after thread starts
	public void run(){
	   if(debug){
		   System.out.println("Running: "+name);
	   }
	   s.startTime(timeout, matchTime, t);
	   /* commented out from previous tests,replaced with above line
	   synchronized(t){
			timeout.setText("started");
			time=0;
			while(time<matchTime){
				time++;
				timeout.setText(""+time);
				try{
				  t.wait(1000);
				}
				catch(Exception e){
					e.printStackTrace();
				}
			}
			}
			*/
	}

}
