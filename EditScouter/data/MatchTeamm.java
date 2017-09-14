package data;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class MatchTeamm extends AbstractTableModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 683132490682937940L;
	private ArrayList<String> columnNames =new ArrayList<String>();
	private ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();//height/length
	
	public MatchTeamm(){
		columnNames.add("Match#");
		columnNames.add("State");
		columnNames.add("Comment");
		
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
	public void clearTable(){
		data.clear();
	}
	public void addRow(String matchNum, String state, String comment){
		ArrayList<String> dataVec= new ArrayList<String>();
		dataVec.add(""+matchNum);
		dataVec.add(""+state);
		dataVec.add(""+comment);
		data.add(dataVec);
	}

}
