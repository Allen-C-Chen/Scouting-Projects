package global;

import util.Lib;

/**
 * to change what was a row to a column
 * @author Allen
 *
 */
public class ColumnToRows {
	public static String columnToRows(String in){
		String[] rows=in.split("\n");
		
		String[][] newRow=new String[Lib.getOccurances(rows[0],"\t")+1][rows.length];
		
		for(int i=0;i<rows.length;i++){
			String[] data=rows[i].split("\t");
			for(int c=0;c<data.length;c++){
				newRow[c][i]=data[c];
			}
		}
		String s="";
		for(int i=0;i<newRow.length;i++){
			String rowString="";
			for(String data:newRow[i]){
				rowString+="\t"+data;
			}
			rowString=rowString.substring(1);
			s+="\n"+rowString;
		}
		return s.substring(1);
	}
	public static void main(String[] args){
		System.out.println(ColumnToRows.columnToRows(args[0]));
	}
}
