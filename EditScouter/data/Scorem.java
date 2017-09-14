package data;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import global.Match;
import scouterEdit.ScoreField;

public class Scorem extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9048697224805547964L;
	private ArrayList<String> columnNames =new ArrayList<String>();
	private ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();//height/length
	/**
	 * 
	 * @param score score loads the names for the scores WARNING if the score ArrayList is not larger than the ArrayList for other
	 */
	public Scorem(ArrayList<ScoreField> score){
		columnNames.add("Match#");
		columnNames.add("Red 1");
		columnNames.add("Red 2");
		columnNames.add("Red 3");
		columnNames.add("Blue 1");
		columnNames.add("Blue 2");
		columnNames.add("Blue 3");
		columnNames.add("Total");
		for(int i=0;i<score.size();i++){
			columnNames.add(score.get(i).getName());
		}
	}
	
	public int getColumnCount() {
		return columnNames.size();
	}

	public int getRowCount() {
		return data.size();
	}

	public String getColumnName(int col) {
		return columnNames.get(col);
	}

	public Object getValueAt(int row, int col) {
		return data.get(row).get(col);
	}


	/*
	 * Don't need to implement this method unless your table's
	 * editable.
	 */
	public boolean isCellEditable(int row, int col) {
		//Note that the data/cell address is constant,
		//no matter where the cell appears onscreen.
		if (col < 2) {
			return false;
		} else {
			return true;
		}
	}
	//adds a row, for intiallizing the matches
	/**
	 * 
	 * @param match match, will read data from it and create row
	 * @param matchNum match number which is not supplied by match
	 */
	public void addRow(Match match,String matchNum){
		ArrayList<String> dataVec=new ArrayList<String>();
		dataVec.add(""+matchNum);
		dataVec.add(""+match.getRed1());
		dataVec.add(""+match.getRed2());
		dataVec.add(""+match.getRed3());
		dataVec.add(""+match.getBlue1());
		dataVec.add(""+match.getBlue2());
		dataVec.add(""+match.getBlue3());
		for(int i=0;i<match.getRScore().size();i++){
			dataVec.add(""+match.getRScore().get(i));
		}
		data.add(dataVec);
		
	}
	//loads new scores in dependign on which ArrayList is given
	/**
	 * 
	 * @param scores values for the scores
	 * @param row which row of data is to be changed// cannot exceed max
	 */
			
	public void changeScores(ArrayList<Integer> scores,int row){
		for(int i=0;i<scores.size();i++){
			data.get(row).set(i+7, ""+scores.get(i));
		}
	}
	public void clear(){
		data.clear();
	}

}
