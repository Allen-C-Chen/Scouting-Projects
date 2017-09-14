package scouter;

import javax.swing.table.AbstractTableModel;
import java.util.Vector;
import javax.swing.event.TableModelEvent;
public class scoreTablem extends AbstractTableModel {
	int rows=100;
	public scoreTablem(){
		for(int i=0;i<50;i++){
			Vector dataS=new Vector();
			for(int c=0;c<12;c++){
				dataS.addElement("");
			}
			data.addElement(dataS);
		}
	}

	private String[] columnNames =new String[]{"Match","Red_1","Red_2","Red_3","Blue_1","Blue_2","Blue_3","Score","Totes","Containers","Litter","Penalties"};
	private Vector data = new Vector();//height/length

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
		Object obj=((Vector) data.get(row)).get(col);
		return obj;
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

	/*
	 * Don't need to implement this method unless your table's
	 * data can change.
	 */
	public void setValueAt(String value, int row, int col) {
		((Vector)data.get(row)).set(col, value);
		fireTableCellUpdated(row, col);
	}
	public void addRow(){
		Vector dataS=new Vector();
		for(int i=0;i<12;i++){
			dataS.addElement("");
		}
		data.addElement(dataS);
	}
	public void clearTable(){
		data=new Vector();
		for(int i=0;i<50;i++){
			Vector dataS=new Vector();
			for(int c=0;c<12;c++){
				dataS.add("");
			}
			data.add(dataS);
		}
	}
}
