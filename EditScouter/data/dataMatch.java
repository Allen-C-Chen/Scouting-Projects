package data;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;

import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.TableModel;

import global.Match;

import javax.swing.event.ChangeEvent;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.DefaultComboBoxModel;

public class dataMatch extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -9211758646672087779L;
	private dataVar var;
	private JSpinner Sp_MatchNum;
	private JTable T_Scoring;
	private JTable T_Match;
	private Match match;
	private final JComboBox<MatchTypes> CB_Type;
	private MatchScoringm matchScorem;
	private Matchm matchm;
	private JFileChooser fc= new JFileChooser();
	/**
	 * Create the panel.
	 */
	public dataMatch(final dataVar var) {
		fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		this.var=var;
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblMatchNum = new JLabel("Match# to read");
		GridBagConstraints gbc_lblMatchNum = new GridBagConstraints();
		gbc_lblMatchNum.weightx = 1.0;
		gbc_lblMatchNum.insets = new Insets(0, 0, 5, 5);
		gbc_lblMatchNum.gridx = 0;
		gbc_lblMatchNum.gridy = 0;
		add(lblMatchNum, gbc_lblMatchNum);
		
		final JButton B_Directory = new JButton("Set Directory");
		GridBagConstraints gbc_B_Directory = new GridBagConstraints();
		gbc_B_Directory.insets = new Insets(0, 0, 5, 5);
		gbc_B_Directory.gridx = 1;
		gbc_B_Directory.gridy = 0;
		add(B_Directory, gbc_B_Directory);
		
		final JScrollPane SP_Scoring = new JScrollPane();
		GridBagConstraints gbc_SP_Scoring = new GridBagConstraints();
		gbc_SP_Scoring.insets = new Insets(0, 0, 5, 0);
		gbc_SP_Scoring.weighty = 2.0;
		gbc_SP_Scoring.weightx = 100.0;
		gbc_SP_Scoring.fill = GridBagConstraints.BOTH;
		gbc_SP_Scoring.gridheight = 4;
		gbc_SP_Scoring.gridx = 2;
		gbc_SP_Scoring.gridy = 0;
		add(SP_Scoring, gbc_SP_Scoring);
		
		T_Scoring = new JTable(){
			/**
			 * 
			 */
			private static final long serialVersionUID = -7250280271203515060L;
			public boolean getScrollableTracksViewportWidth(){
				return getPreferredSize().width < getParent().getWidth();
			}
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
		matchScorem=new MatchScoringm(var.getScoreFields());
		matchScorem.setValueAt("red", 0, 0);
		matchScorem.setValueAt("blue", 4, 0);
		T_Scoring.setModel(matchScorem);
		SP_Scoring.setViewportView(T_Scoring);
		
		Sp_MatchNum = new JSpinner();
		GridBagConstraints gbc_Sp_MatchNum = new GridBagConstraints();
		gbc_Sp_MatchNum.weightx = 1.0;
		gbc_Sp_MatchNum.insets = new Insets(0, 0, 5, 5);
		gbc_Sp_MatchNum.fill = GridBagConstraints.HORIZONTAL;
		gbc_Sp_MatchNum.gridx = 0;
		gbc_Sp_MatchNum.gridy = 1;
		add(Sp_MatchNum, gbc_Sp_MatchNum);
		
		CB_Type = new JComboBox<MatchTypes>();
		CB_Type.setModel(new DefaultComboBoxModel<MatchTypes>(MatchTypes.values()));
		GridBagConstraints gbc_CB_Type = new GridBagConstraints();
		gbc_CB_Type.insets = new Insets(0, 0, 5, 5);
		gbc_CB_Type.fill = GridBagConstraints.HORIZONTAL;
		gbc_CB_Type.gridx = 1;
		gbc_CB_Type.gridy = 1;
		add(CB_Type, gbc_CB_Type);
		

		
		JButton B_Prev = new JButton("Previous");
		GridBagConstraints gbc_B_Prev = new GridBagConstraints();
		gbc_B_Prev.weightx = 1.0;
		gbc_B_Prev.insets = new Insets(0, 0, 5, 5);
		gbc_B_Prev.gridx = 1;
		gbc_B_Prev.gridy = 2;
		add(B_Prev, gbc_B_Prev);
		
		final JButton B_Read = new JButton("Read Data");
		GridBagConstraints gbc_B_Read = new GridBagConstraints();
		gbc_B_Read.weightx = 1.0;
		gbc_B_Read.insets = new Insets(0, 0, 5, 5);
		gbc_B_Read.gridx = 0;
		gbc_B_Read.gridy = 2;
		add(B_Read, gbc_B_Read);
		
		JButton B_Next = new JButton("Next");
		GridBagConstraints gbc_B_Next = new GridBagConstraints();
		gbc_B_Next.weightx = 1.0;
		gbc_B_Next.insets = new Insets(0, 0, 5, 5);
		gbc_B_Next.gridx = 1;
		gbc_B_Next.gridy = 3;
		add(B_Next, gbc_B_Next);
		
		JButton B_Save = new JButton("Save Edits");
		GridBagConstraints gbc_B_Save = new GridBagConstraints();
		gbc_B_Save.insets = new Insets(0, 0, 5, 5);
		gbc_B_Save.gridx = 0;
		gbc_B_Save.gridy = 3;
		add(B_Save, gbc_B_Save);
		
		final JScrollPane SP_Match = new JScrollPane();
		GridBagConstraints gbc_SP_Match = new GridBagConstraints();
		gbc_SP_Match.weighty = 100.0;
		gbc_SP_Match.fill = GridBagConstraints.BOTH;
		gbc_SP_Match.gridwidth = 3;
		gbc_SP_Match.gridx = 0;
		gbc_SP_Match.gridy = 4;
		add(SP_Match, gbc_SP_Match);
		
		T_Match = new JTable();
		matchm=new Matchm();
		T_Match.setModel(matchm);
		T_Match.getColumnModel().getColumn(0).setMaxWidth(75);
		SP_Match.setViewportView(T_Match);
		
		//Action Listeners
		B_Directory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int returnVal= fc.showOpenDialog(B_Directory);
				if(returnVal==JFileChooser.APPROVE_OPTION){
					var.setDirectory(fc.getSelectedFile()+"/");
				}
			}
		});
		
		
		B_Read.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					File file= new File(var.getDirectory()+var.getTypes().value()+Sp_MatchNum.getValue());
					readFile(file);
			}
		});
		B_Prev.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Sp_MatchNum.setValue((int)Sp_MatchNum.getValue()-1);
				B_Read.doClick();
			}
		});
		B_Next.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Sp_MatchNum.setValue((int)Sp_MatchNum.getValue()+1);
				B_Read.doClick();
			}
		});
		
		Sp_MatchNum.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if((int)Sp_MatchNum.getValue()<0){
					Sp_MatchNum.setValue(0);
				}
			}
		});
		CB_Type.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				var.setTypes((MatchTypes)CB_Type.getSelectedItem());
			}
		});
		B_Save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveEdit();
			}
		});
		

	}
	public void readFile(File file){
		String fileData="";
		try{
		      BufferedReader in = new BufferedReader(new FileReader(file));
		      String str;
		      while((str=in.readLine())!=null){
		    	  fileData=str;
		      }
		      in.close();
		    }
		    catch(IOException s){
		      System.out.println("Error reading file to open for editor");
		    }
		if(fileData!=null){//a simple check to see if a text file has been read
			showMatch(fileData);
		}
	}
	public void saveFile(File file,String data){
		try{
		      BufferedWriter out = new BufferedWriter(new FileWriter(file));
		      System.out.println(data);
		      out.write(data);
		      out.close();
		    }
		    catch(IOException e){
		      System.out.println("Write in file error");
		    }
	}
	public void showMatch(String data){
		match =new Match(data);
		matchScorem.setValueAt(""+match.getRed1(), 1, 0);
		matchScorem.setValueAt(""+match.getRed2(), 2, 0);
		matchScorem.setValueAt(""+match.getRed3(), 3, 0);
		matchScorem.setValueAt(""+match.getBlue1(), 5, 0);
		matchScorem.setValueAt(""+match.getBlue2(), 6, 0);
		matchScorem.setValueAt(""+match.getBlue3(), 7, 0);
		for(int i=0;i<match.getRScore().size();i++){
			matchScorem.setValueAt(""+match.getRScore().get(i), 0, i+1);
		}
		for(int i=0;i<match.getBScore().size();i++){
			matchScorem.setValueAt(""+match.getBScore().get(i), 4, i+1);
		}
		matchm.clearTable();
		for(int i=0;i<match.getComment().size();i++){
			matchm.addRow(match.getComState().get(i)+"", ""+match.getComment().get(i));
		}
		T_Match.updateUI();//needed to show the stuff
	}
	public void saveEdit(){
		match.getComment().clear();
		match.getComState().clear();
		for(int i=0;i<matchm.getRowCount();i++){
			match.getComState().add(""+matchm.getValueAt(i, 0));
			match.getComment().add(""+matchm.getValueAt(i, 1));
		}
		File file= new File(var.getDirectory()+var.getTypes().value()+Sp_MatchNum.getValue());
		saveFile(file,match.toString());
	}
	public void reset(){
		matchm.clearTable();
		matchScorem.clearTable();
		Sp_MatchNum.setValue(0);
		CB_Type.setSelectedIndex(0);
	}
	public JPanel getP_Match(){
		return this;
	}

}
