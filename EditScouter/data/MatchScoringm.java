package data;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import scouterEdit.ScoreField;

public class MatchScoringm extends AbstractTableModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6133882019679728233L;
	
	//tabled used to display scoring and team data in the match tab
	private ArrayList<String> columnNames =new ArrayList<String>();
	private ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();//height/length
	

	public MatchScoringm(ArrayList<ScoreField> score){
		columnNames.add("Alliance");
		columnNames.add("Score");
		for(int i=0;i<score.size();i++){
			columnNames.add(score.get(i).getName());
		}
		for(int i=0;i<8;i++){
			ArrayList<String> dataVec=new ArrayList<String>();
			for(int c=0;c<2+score.size();c++){
				dataVec.add("");
			}
			data.add(dataVec);
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
		return false;
	}
	public void clearTable(){
		data.clear();
	}

	/*
	 * Don't need to implement this method unless your table's
	 * data can change.
	 */
	public void setValueAt(String value, int row, int col) {
		data.get(row).set(col, value);
		fireTableCellUpdated(row, col);
	}

}
