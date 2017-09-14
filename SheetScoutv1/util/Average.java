package util;

import java.util.Vector;

public class Average {
	private Vector<Integer> ints=new Vector<Integer>();
	private Vector<Boolean> boos=new Vector<Boolean>();
	private int count=0;
	public Average(){}
	public void addInt(int add){
		count++;
		ints.add(add);	
	}
	public void addBoolean(boolean add){
		count++;
		boos.add(add);
	}
	private double getTotalValue(){
		int value=0;
		for(Integer i:ints){
			value+=i;
		}
		for(Boolean b:boos){
			if(b)value++;
		}
		return value;
	}
	public double getValue(){
		if(count>0){
		return getTotalValue()/count;
		}
		return 0;
	}
	public String toString(){
		return Double.toString(getValue());
	}
}
