package data;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JSpinner;
import java.awt.GridBagConstraints;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;

import java.awt.Insets;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableModel;

import global.Team;

import javax.swing.event.ChangeEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.ListSelectionModel;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseEvent;

import javax.swing.event.CaretListener;
import javax.swing.event.CaretEvent;

public class dataTeam extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -787600566223117560L;
	private JTable T_Data;
	private JFileChooser fc= new JFileChooser();
	private dataVar var;
	private ArrayList<Team> teams= new ArrayList<Team>();
	private Teamm teamm;
	private JTextArea TA_Notes;
	private JButton B_Reset;
	private final JSpinner Sp_TeamNum;

	/**
	 * Create the panel.
	 */
	public dataTeam(final dataVar var) {
		this.var=var;
		teamm= new Teamm(var.getTeamFields());
		
		fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		Sp_TeamNum = new JSpinner();
		GridBagConstraints gbc_Sp_TeamNum = new GridBagConstraints();
		gbc_Sp_TeamNum.weighty = 0.1;
		gbc_Sp_TeamNum.fill = GridBagConstraints.HORIZONTAL;
		gbc_Sp_TeamNum.insets = new Insets(0, 0, 5, 5);
		gbc_Sp_TeamNum.gridx = 0;
		gbc_Sp_TeamNum.gridy = 0;
		add(Sp_TeamNum, gbc_Sp_TeamNum);
		
		final JButton B_Directory = new JButton("Set Directory");
		GridBagConstraints gbc_B_Directory = new GridBagConstraints();
		gbc_B_Directory.weighty = 0.1;
		gbc_B_Directory.insets = new Insets(0, 0, 5, 5);
		gbc_B_Directory.gridx = 1;
		gbc_B_Directory.gridy = 0;
		add(B_Directory, gbc_B_Directory);
		
		JButton B_Add = new JButton("Add Team");
		GridBagConstraints gbc_B_Add = new GridBagConstraints();
		gbc_B_Add.insets = new Insets(0, 0, 5, 5);
		gbc_B_Add.gridx = 0;
		gbc_B_Add.gridy = 1;
		add(B_Add, gbc_B_Add);
		
		JButton B_LoadDir = new JButton("Load From Directory");
		GridBagConstraints gbc_B_LoadDir = new GridBagConstraints();
		gbc_B_LoadDir.insets = new Insets(0, 0, 5, 5);
		gbc_B_LoadDir.gridx = 1;
		gbc_B_LoadDir.gridy = 1;
		add(B_LoadDir, gbc_B_LoadDir);
		
		B_Reset = new JButton("Reset");
		GridBagConstraints gbc_B_Reset = new GridBagConstraints();
		gbc_B_Reset.insets = new Insets(0, 0, 5, 5);
		gbc_B_Reset.gridx = 0;
		gbc_B_Reset.gridy = 2;
		add(B_Reset, gbc_B_Reset);
		
		JScrollPane SP_Notes = new JScrollPane();
		GridBagConstraints gbc_SP_Notes = new GridBagConstraints();
		gbc_SP_Notes.gridwidth = 2;
		gbc_SP_Notes.fill = GridBagConstraints.BOTH;
		gbc_SP_Notes.gridheight = 2;
		gbc_SP_Notes.insets = new Insets(0, 0, 5, 0);
		gbc_SP_Notes.gridx = 2;
		gbc_SP_Notes.gridy = 1;
		add(SP_Notes, gbc_SP_Notes);
		
		TA_Notes = new JTextArea();
		SP_Notes.setViewportView(TA_Notes);
		
		
		JLabel lblNotes = new JLabel("Notes");
		GridBagConstraints gbc_lblNotes = new GridBagConstraints();
		gbc_lblNotes.weighty = 0.1;
		gbc_lblNotes.insets = new Insets(0, 0, 5, 0);
		gbc_lblNotes.gridx = 3;
		gbc_lblNotes.gridy = 0;
		add(lblNotes, gbc_lblNotes);
		
		final JButton B_Save = new JButton("Save to Directory");
		GridBagConstraints gbc_B_Save = new GridBagConstraints();
		gbc_B_Save.weighty = 0.1;
		gbc_B_Save.insets = new Insets(0, 0, 5, 5);
		gbc_B_Save.gridx = 2;
		gbc_B_Save.gridy = 0;
		add(B_Save, gbc_B_Save);
		
		JScrollPane SP_Data = new JScrollPane();
		GridBagConstraints gbc_SP_Data = new GridBagConstraints();
		gbc_SP_Data.weighty = 10.0;
		gbc_SP_Data.fill = GridBagConstraints.BOTH;
		gbc_SP_Data.gridwidth = 4;
		gbc_SP_Data.gridx = 0;
		gbc_SP_Data.gridy = 3;
		add(SP_Data, gbc_SP_Data);
		//all the stuff that needs to be done to set up the table tooltips
		final ArrayList<String> tooltips= new ArrayList<String>();
		tooltips.add("Team number of the team");
		for(int i=0;i<var.getTeamFields().size();i++){
			tooltips.add(var.getTeamFields().get(i).getTooltip());
		}
		T_Data = new JTable(){
			private static final long serialVersionUID = 3813125731046115266L;
			public boolean getScrollableTracksViewportWidth()
			{
				return getPreferredSize().width < getParent().getWidth();
			}
			//sets the header to the custom description retrieved from the teamFields
			protected JTableHeader createDefaultTableHeader() {
				return new JTableHeader(columnModel) {
					private static final long serialVersionUID = 1431703457391906343L;
					public String getToolTipText(MouseEvent e) {
						java.awt.Point p = e.getPoint();
						int index = columnModel.getColumnIndexAtX(p.x);
						int realIndex = 
								columnModel.getColumn(index).getModelIndex();
						return tooltips.get(realIndex);
					}
				};
			}
			//sets the tooltip of each cell to the item in the cell
			public String getToolTipText(MouseEvent e) {
				String tip = null;
				java.awt.Point p = e.getPoint();
				int rowIndex = rowAtPoint(p);
				int colIndex = columnAtPoint(p);
				int realColumnIndex = convertColumnIndexToModel(colIndex);
				TableModel model = getModel();
				tip=""+model.getValueAt(rowIndex, realColumnIndex);//simply sets the tooltip to what's in the cell//to simplify things
				return tip;
			}
		};
		T_Data.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		T_Data.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		T_Data.setCellSelectionEnabled(true);
		T_Data.setModel(teamm);
		T_Data.updateUI();
		SP_Data.setViewportView(T_Data);
		//set up column widths
		T_Data.getColumnModel().getColumn(0).setPreferredWidth(55);
		for(int i=0;i<var.getTeamFields().size();i++){
			if(var.getTeamFields().get(i).getColWidth()>0){
				T_Data.getColumnModel().getColumn(i+1).setPreferredWidth(var.getTeamFields().get(i).getColWidth());	
			}
			if(var.getTeamFields().get(i).getType().equals("COMBO")){
				JComboBox<String> combo= new JComboBox<String>();
				String[] model= var.getTeamFields().get(i).getModel().split(",");
				for(String s:model){
					combo.addItem(s);
				}
				T_Data.getColumnModel().getColumn(i+1).setCellEditor(new DefaultCellEditor(combo));
			}
		}
			
		JButton B_Load = new JButton("Load Data");
		GridBagConstraints gbc_B_Load = new GridBagConstraints();
		gbc_B_Load.insets = new Insets(0, 0, 5, 5);
		gbc_B_Load.gridx = 1;
		gbc_B_Load.gridy = 2;
		add(B_Load, gbc_B_Load);
		
		//Action Listeners after everything so everything's initialized
		Sp_TeamNum.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if((int)Sp_TeamNum.getValue()<0){
					Sp_TeamNum.setValue(0);
				}
			}
		});
		//sets directory to load and save teams
		B_Directory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int returnVal= fc.showOpenDialog(B_Directory);
				if(returnVal==JFileChooser.APPROVE_OPTION){//if approved
					var.setDirectory(fc.getSelectedFile()+"/");
				}
			}
		});
		//adds a new team#
		B_Add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				teams.add(new Team((int)Sp_TeamNum.getValue(),var.getTeamFields()));
				setDisplay();
			}
		});
		//loads the team number from file
		B_Load.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				File file=new File(var.getDirectory()+Sp_TeamNum.getValue());
				String fileData="";
				if(file.exists()){//additional check, should prevent most errors, exception of interruptions of file reader
					try{
						BufferedReader in = new BufferedReader(new FileReader(file));
						String str;
						while((str=in.readLine())!=null){
							fileData=str;
						}
						in.close();
					}
					catch(IOException s){
						System.out.println("Team not Loaded");
					}
				}
				if(!fileData.equals("")){//a simple check to see if a text file has been read
					teams.add(new Team(fileData,var.getTeamFields()));
					setDisplay();
				}

			}
		});
		B_LoadDir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				teams.clear();
				File file= new File(var.getDirectory());
				File[] files= file.listFiles();
				if(files!=null&&files.length>0){//checks to make sure there's a selected directory
					for(File f: files){
						System.out.println(f.getName());
						if(isNumber(f.getName())){
							String fileData="";
							try{
								BufferedReader in = new BufferedReader(new FileReader(f));
								String str;
								while((str=in.readLine())!=null){
									fileData+=str;
								}
								in.close();
							}
							catch(IOException s){
								System.out.println("Team not Loaded");
							}
							if(!fileData.equals("")){
								System.out.println("loaded "+f.getName()+":"+new Team(fileData,var.getTeamFields()));
								teams.add(new Team(fileData,var.getTeamFields()));
							}
						}
					}
				}
				setDisplay();
			}
		});
		B_Save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(int i=0;i<teams.size();i++){
					File file= new File(var.getDirectory()+teams.get(i).getTeamNum());
					if(file.exists()){
						System.out.println("file Exists");

						int value=JOptionPane.showConfirmDialog(B_Save, "Overwrite?");
						if(value==JOptionPane.YES_OPTION){
							try{
								BufferedWriter out = new BufferedWriter(new FileWriter(file));
								out.write(teams.get(i).toString());
								out.close();
								System.out.println("file saved"+teams.get(i).getTeamNum());
							}
							catch(IOException e1){
								System.out.println("Write in file error"+teams.get(i).getTeamNum());
							}
						}
					}
					else{
						try{
							BufferedWriter out = new BufferedWriter(new FileWriter(file));
							out.write(teams.get(i).toString());
							out.close();
							System.out.println("file saved"+teams.get(i).getTeamNum());
						}
						catch(IOException e1){
							System.out.println("Write in file error"+teams.get(i).getTeamNum());
						}
					}
				}
			}
		});
		B_Reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				teams.clear();
				setDisplay();
			}
		});
		//sets the notes for the selected team when something is typed
		TA_Notes.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				try {
					if(T_Data.getSelectedRow()>-1){
						if (!TA_Notes.getText().equals("")) {//prevents there from being "" which can cause problems in the saving
							teams.get(T_Data.getSelectedRow()).setNotes(TA_Notes.getText());
						}
					}
				} catch (Exception e1) {}
			}
		});
		
		//sets the data for the team when focus is lost
		T_Data.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				for(int i=0;i<teams.size();i++){
					teams.get(i).setFieldData(teamm.getRow(i));
					System.out.println(teams.get(i).toString());
				}
			}
		});
		//adds listener that'll update notes when row is changed
		T_Data.getSelectionModel().addListSelectionListener(new SharedListSelectionHandler());
	}
	//sets most display stuff
	private void setDisplay(){
		teamm.clearTable();
		for(int i=0;i<teams.size();i++){
			teamm.addRow(teams.get(i));
		}
		T_Data.updateUI();
	}
	private boolean isNumber(String num){
		char[] nums=num.toCharArray();
		for(char c:nums){
			if(!Character.isDigit(c)){
				return false;
			}
		}
		return true;
	}
	public JPanel getP_Team(){
		return this;
	}
	public void reset(){
		B_Reset.doClick();
		Sp_TeamNum.setValue(0);
	}
	// Custom listener class that'll change the notes text area when something changes with the row selection
	class SharedListSelectionHandler implements ListSelectionListener {
	    public void valueChanged(ListSelectionEvent e) {
	       TA_Notes.setText(teams.get(T_Data.getSelectedRow()).getNotes());
	    }
	}
}
