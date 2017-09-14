package data;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import scouterEdit.ScoreField;

public class ScoreTeamm extends AbstractTableModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4694068853375938962L;
	private ArrayList<String> columnNames =new ArrayList<String>();
	private ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();//height/length
	
	public ScoreTeamm(ArrayList<ScoreField> score){
		columnNames.add("Team#");
		columnNames.add("Total Score");
		columnNames.add("Average");
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
	public void addRow(int teamNum, int total, int average, ArrayList<Integer> values){
		ArrayList<String> dataVec= new ArrayList<String>();
		dataVec.add(""+teamNum);
		dataVec.add(""+total);
		dataVec.add(""+average);
		for(int i=0;i<values.size();i++){
			dataVec.add(""+values.get(i));
		}
		data.add(dataVec);
		
	}
	public void clearTable(){
		data.clear();
	}
	

}
