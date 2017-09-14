package sheet;
/**
 * Generic sheet interface, used to interact with the various sheets that are available
 * @author Allen
 *
 */
public interface Sheet {
	public int getRows();
	public int getHeadRows();//number of rows the headers take up
	public int getColumns();
	public int get(int row, int column);
	public String[] get(int row);
	public Sheet set(int row, int columns,String value);
	public Sheet set(int row, String[] values);
	/**
	 * Push the values to the sheet source i.e. google sheet or excel document etc
	 */
	public void updateSheet();
}
