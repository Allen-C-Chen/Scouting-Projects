package data;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import global.Team;
import scouterEdit.TeamField;

public class Teamm extends AbstractTableModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7201602369143891655L;
	private ArrayList<String> columnNames =new ArrayList<String>();
	private ArrayList<ArrayList<Object>> data = new ArrayList<ArrayList<Object>>();//height/length
	private ArrayList<TeamField> fields;
	
	public Teamm(ArrayList<TeamField> fields){
		this.fields=fields;
		columnNames.add("Team#");
		for(int i=0;i<fields.size();i++){
			columnNames.add(fields.get(i).getName());
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
		if(col<1){
			return false;
		}
		else{
			return true;
		}
	}
	public void setValueAt(Object value, int row, int col) {
		data.get(row).set(col, value);
		fireTableCellUpdated(row, col);
	}
	public Class<?> getColumnClass(int c) {
		if(c>0){
			String type=fields.get(c-1).getType();
			if(type.equals("CHECK")){
				return Boolean.class;
			}
			else if(type.equals("NUM")){
				return Integer.class;
			}
			else{
				return String.class;
			}
		}
		else{
			return Object.class;
		}
	}
	public void addRow(Team team){
		ArrayList<Object> dataVec= new ArrayList<Object>();
		dataVec.add(team.getTeamNum());
		for(int i=0;i<team.getFieldData().size();i++){
			if(team.getFields().get(i).getType().equals("NUM")){
				if(!team.getFieldData().get(i).equals("")){
					try {
						dataVec.add(Integer.parseInt(team.getFieldData().get(i)));
					} catch (NumberFormatException e) {
						dataVec.add(0);
					}
				}
				else{
					dataVec.add(0);
				}
			}
			else if(team.getFields().get(i).getType().equals("CHECK")){
				if(team.getFieldData().get(i).equals("true")){
					dataVec.add(true);
				}
				else{
					dataVec.add(false);
				}
			}
			else{
				dataVec.add(team.getFieldData().get(i));
			}
		}
		data.add(dataVec);
	}
	public ArrayList<String> getRow(int index){
		ArrayList<String> returnVec= new ArrayList<String>();
		for(int i=1;i<data.get(index).size();i++){
			returnVec.add(""+data.get(index).get(i));
		}
		return returnVec;
	}
	public void clearTable(){
		data.clear();
	}

}
