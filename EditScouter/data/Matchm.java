package data;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class Matchm extends AbstractTableModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2864384616056266610L;
	
	//model used to display the match data in the matches tab
	private String[] columnNames =new String[]{"Status","Comment"};
	private ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();//height/length

	public int getColumnCount() {
		return columnNames.length;
	}

	public int getRowCount() {
		return data.size();
	}

	public String getColumnName(int col) {
		return columnNames[col];
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
		if (col < 1) {
			return false;
		} else {
			return true;
		}
	}

	/*
	 * Don't need to implement this method unless your table's
	 * data can change.
	 */
	public void setValueAt(String value, int row, int col) {
		data.get(row).set(col, value);
		fireTableCellUpdated(row, col);
	}
	public void setValueAt(Object value, int row, int col){
		data.get(row).set(col, ""+value);
		fireTableCellUpdated(row,col);
	}
	public void clearTable(){
		data.clear();
	}
	public void addRow(String state, String comment){
		ArrayList<String> dataS=new ArrayList<String>();
		dataS.add(state);
		dataS.add(comment);
		data.add(dataS);
		fireTableCellUpdated(data.size(),0);
		fireTableCellUpdated(data.size(),1);
	}

}
