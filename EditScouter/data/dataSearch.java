package data;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JTextField;
import java.awt.GridBagConstraints;
import javax.swing.JButton;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JRadioButton;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JSpinner;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.AbstractListModel;
import javax.swing.ButtonGroup;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileFilter;

import global.Match;

import javax.swing.event.ChangeEvent;
import javax.swing.JScrollPane;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class dataSearch extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8193215261800650868L;
	
	private dataVar var;
	private JFileChooser fc=new JFileChooser();
	private final JList<String> L_toSearch;
	private final JList<String> L_Matches;
	private JSpinner Sp_Search;
	private JSpinner Sp_MinSearch;
	private JSpinner Sp_MaxSearch;
	private final JCheckBox CB_Foul;
	
	private ArrayList<Integer> directoryHash= new ArrayList<Integer>();//hidden hash value representing the directory 
	private ArrayList<String> toSearch=new ArrayList<String>();//ArrayList Representing the matches that'll be 
	private ArrayList<String> searched=new ArrayList<String>();
	private MatchTypes type=MatchTypes.Qualifier;
	private JTextField TF_Directory;
	
	/**
	 * Create the panel.
	 */
	public dataSearch(final dataVar var) {
		
		this.var=var;
		fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblMatchNumber = new JLabel("Match Number");
		GridBagConstraints gbc_lblMatchNumber = new GridBagConstraints();
		gbc_lblMatchNumber.insets = new Insets(0, 0, 5, 5);
		gbc_lblMatchNumber.gridx = 0;
		gbc_lblMatchNumber.gridy = 0;
		add(lblMatchNumber, gbc_lblMatchNumber);
		
		JLabel lblMatchNumberRangeminmax = new JLabel("Match Number Range(Min-Max)");
		GridBagConstraints gbc_lblMatchNumberRangeminmax = new GridBagConstraints();
		gbc_lblMatchNumberRangeminmax.gridwidth = 2;
		gbc_lblMatchNumberRangeminmax.insets = new Insets(0, 0, 5, 0);
		gbc_lblMatchNumberRangeminmax.gridx = 1;
		gbc_lblMatchNumberRangeminmax.gridy = 0;
		add(lblMatchNumberRangeminmax, gbc_lblMatchNumberRangeminmax);
		
		Sp_MinSearch = new JSpinner();
		GridBagConstraints gbc_Sp_MinSearch = new GridBagConstraints();
		gbc_Sp_MinSearch.insets = new Insets(0, 0, 5, 5);
		gbc_Sp_MinSearch.fill = GridBagConstraints.HORIZONTAL;
		gbc_Sp_MinSearch.gridx = 1;
		gbc_Sp_MinSearch.gridy = 1;
		add(Sp_MinSearch, gbc_Sp_MinSearch);
		
		Sp_Search = new JSpinner();
		GridBagConstraints gbc_Sp_Search = new GridBagConstraints();
		gbc_Sp_Search.insets = new Insets(0, 0, 5, 5);
		gbc_Sp_Search.fill = GridBagConstraints.HORIZONTAL;
		gbc_Sp_Search.gridx = 0;
		gbc_Sp_Search.gridy = 1;
		add(Sp_Search, gbc_Sp_Search);
		
		JButton B_Add = new JButton("Add to Search");
		GridBagConstraints gbc_B_Add = new GridBagConstraints();
		gbc_B_Add.insets = new Insets(0, 0, 5, 5);
		gbc_B_Add.gridx = 0;
		gbc_B_Add.gridy = 2;
		add(B_Add, gbc_B_Add);
		
		Sp_MaxSearch = new JSpinner();
		GridBagConstraints gbc_Sp_MaxSearch = new GridBagConstraints();
		gbc_Sp_MaxSearch.insets = new Insets(0, 0, 5, 0);
		gbc_Sp_MaxSearch.fill = GridBagConstraints.HORIZONTAL;
		gbc_Sp_MaxSearch.gridx = 2;
		gbc_Sp_MaxSearch.gridy = 1;
		add(Sp_MaxSearch, gbc_Sp_MaxSearch);
		
		JButton B_AddRange = new JButton("Add to Search");
		
		GridBagConstraints gbc_B_AddRange = new GridBagConstraints();
		gbc_B_AddRange.gridwidth = 2;
		gbc_B_AddRange.insets = new Insets(0, 0, 5, 0);
		gbc_B_AddRange.gridx = 1;
		gbc_B_AddRange.gridy = 2;
		add(B_AddRange, gbc_B_AddRange);
		
		final JButton B_Directory = new JButton("Set Directory");
		GridBagConstraints gbc_B_Directory = new GridBagConstraints();
		gbc_B_Directory.insets = new Insets(0, 0, 5, 5);
		gbc_B_Directory.gridx = 0;
		gbc_B_Directory.gridy = 3;
		add(B_Directory, gbc_B_Directory);
		
		TF_Directory = new JTextField();
		GridBagConstraints gbc_TF_Directory = new GridBagConstraints();
		gbc_TF_Directory.gridwidth = 2;
		gbc_TF_Directory.insets = new Insets(0, 0, 5, 5);
		gbc_TF_Directory.fill = GridBagConstraints.HORIZONTAL;
		gbc_TF_Directory.gridx = 1;
		gbc_TF_Directory.gridy = 3;
		add(TF_Directory, gbc_TF_Directory);
		TF_Directory.setColumns(10);
		
		JButton B_Search = new JButton("Search Matches");
		GridBagConstraints gbc_B_Search = new GridBagConstraints();
		gbc_B_Search.insets = new Insets(0, 0, 5, 5);
		gbc_B_Search.gridx = 0;
		gbc_B_Search.gridy = 4;
		add(B_Search, gbc_B_Search);
		
		JLabel lblToSearch = new JLabel("To Search");
		GridBagConstraints gbc_lblToSearch = new GridBagConstraints();
		gbc_lblToSearch.insets = new Insets(0, 0, 5, 5);
		gbc_lblToSearch.gridx = 1;
		gbc_lblToSearch.gridy = 4;
		add(lblToSearch, gbc_lblToSearch);
		
		JLabel lblFoundMatches = new JLabel("Found Matches");
		GridBagConstraints gbc_lblFoundMatches = new GridBagConstraints();
		gbc_lblFoundMatches.insets = new Insets(0, 0, 5, 0);
		gbc_lblFoundMatches.gridx = 2;
		gbc_lblFoundMatches.gridy = 4;
		add(lblFoundMatches, gbc_lblFoundMatches);
		
		JPanel P_Type = new JPanel();
		GridBagConstraints gbc_P_Type = new GridBagConstraints();
		gbc_P_Type.insets = new Insets(0, 0, 5, 5);
		gbc_P_Type.fill = GridBagConstraints.BOTH;
		gbc_P_Type.gridx = 0;
		gbc_P_Type.gridy = 5;
		add(P_Type, gbc_P_Type);
		P_Type.setLayout(new GridLayout(4, 1, 0, 0));
		
		JRadioButton RB_Qual = new JRadioButton("Qualifiers");
		RB_Qual.setSelected(true);
		P_Type.add(RB_Qual);
		
		JRadioButton RB_Quarter = new JRadioButton("Quarter");
		P_Type.add(RB_Quarter);
		
		JRadioButton RB_Semi = new JRadioButton("Semi-Final");
		P_Type.add(RB_Semi);
		
		JRadioButton RB_Final = new JRadioButton("Finals");
		P_Type.add(RB_Final);
		
		JScrollPane SP_toSearch = new JScrollPane();
		GridBagConstraints gbc_SP_toSearch = new GridBagConstraints();
		gbc_SP_toSearch.fill = GridBagConstraints.BOTH;
		gbc_SP_toSearch.insets = new Insets(0, 0, 5, 5);
		gbc_SP_toSearch.gridx = 1;
		gbc_SP_toSearch.gridy = 5;
		add(SP_toSearch, gbc_SP_toSearch);
		
		L_toSearch = new JList<String>();
		SP_toSearch.setViewportView(L_toSearch);
		L_toSearch.setModel(new AbstractListModel<String>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 6849202298674475045L;
			public int getSize() {
				return toSearch.size();
			}
			public String getElementAt(int index) {
				return toSearch.get(index);
			}
		});
		
		JScrollPane SP_Match = new JScrollPane();
		GridBagConstraints gbc_SP_Match = new GridBagConstraints();
		gbc_SP_Match.fill = GridBagConstraints.BOTH;
		gbc_SP_Match.insets = new Insets(0, 0, 5, 0);
		gbc_SP_Match.gridx = 2;
		gbc_SP_Match.gridy = 5;
		add(SP_Match, gbc_SP_Match);
		
		L_Matches = new JList<String>();
		SP_Match.setViewportView(L_Matches);
		L_Matches.setModel(new AbstractListModel<String>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 6849202293674475045L;
			ArrayList<String> values = searched;
			public int getSize() {
				return values.size();
			}
			public String getElementAt(int index) {
				return values.get(index);
			}
		});
		
		JButton B_Clear = new JButton("Clear Matches");
		GridBagConstraints gbc_B_Clear = new GridBagConstraints();
		gbc_B_Clear.insets = new Insets(0, 0, 0, 5);
		gbc_B_Clear.gridx = 0;
		gbc_B_Clear.gridy = 6;
		add(B_Clear, gbc_B_Clear);
		
		CB_Foul= new JCheckBox("Include Fouls?");
		GridBagConstraints gbc_CB_Foul = new GridBagConstraints();
		gbc_CB_Foul.insets = new Insets(0, 0, 0, 5);
		gbc_CB_Foul.gridx = 1;
		gbc_CB_Foul.gridy = 6;
		add(CB_Foul, gbc_CB_Foul);
		
		JButton B_Delete = new JButton("Delete Selected");
		GridBagConstraints gbc_B_Delete = new GridBagConstraints();
		gbc_B_Delete.gridx = 2;
		gbc_B_Delete.gridy = 6;
		add(B_Delete, gbc_B_Delete);
		
		ButtonGroup matchTypes= new ButtonGroup();
		matchTypes.add(RB_Qual);
		matchTypes.add(RB_Quarter);
		matchTypes.add(RB_Semi);
		matchTypes.add(RB_Final);
		
		RB_Qual.setActionCommand("Qual");
		RB_Quarter.setActionCommand("Quarter");
		RB_Semi.setActionCommand("Semi");
		RB_Final.setActionCommand("Final");
		
		
		
		//Action Listeners
		ActionListener listen=new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(e.getActionCommand().equals("Quarter")){
					type=MatchTypes.Quarter;
				}
				else if(e.getActionCommand().equals("Semi")){
					type=MatchTypes.Semi;
				}
				else if(e.getActionCommand().equals("Final")){
					type=MatchTypes.Final;
				}
				else{//qualifiers as default as it is the most likely one to be recorded.
					type=MatchTypes.Qualifier;
				}
				
			}
		};
		RB_Qual.addActionListener(listen);
		RB_Quarter.addActionListener(listen);
		RB_Semi.addActionListener(listen);
		RB_Final.addActionListener(listen);
		
		//prevents negatives
		Sp_Search.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if((int)Sp_Search.getValue()<0){
					Sp_Search.setValue(0);
					
				}
			}
		});
		//prevents negatives and the max/min from surpassing each other
		Sp_MinSearch.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if((int)Sp_MinSearch.getValue()<0){
					Sp_MinSearch.setValue(0);
				}
				if((int)Sp_MinSearch.getValue()>(int)Sp_MaxSearch.getValue()){
					Sp_MinSearch.setValue(Sp_MaxSearch.getValue());
				}
			}
		});
		Sp_MaxSearch.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if((int)Sp_MaxSearch.getValue()<(int)Sp_MinSearch.getValue()){
					Sp_MaxSearch.setValue(Sp_MinSearch.getValue());
				}
			}
		});
		//buttons to add matches to search
		B_Add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddToSearch(type.value()+Sp_Search.getValue());
				if(CB_Foul.isSelected()){//if there's also a foul also adds a match with foul
					AddToSearch(type.value()+Sp_Search.getValue()+"(foul)");
				}
				L_toSearch.updateUI();
			}
		});
		B_AddRange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(int i=(int)Sp_MinSearch.getValue();i<=(int)Sp_MaxSearch.getValue();i++){
					AddToSearch(type.value()+i);
					if(CB_Foul.isSelected()){//if there's also a foul also adds a match with foul
						AddToSearch(type.value()+i+"(foul)");
					}
				}
				L_toSearch.updateUI();
			}
		});
		B_Directory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int returnVal= fc.showOpenDialog(B_Directory);
				if(returnVal==JFileChooser.APPROVE_OPTION){//if approved
					TF_Directory.setText(""+fc.getSelectedFile()+"/");
					TF_Directory.setToolTipText(""+fc.getSelectedFile()+"/");//if the directory is too long
					var.setDirectory(fc.getSelectedFile()+"/");
				}
			}
		});
		TF_Directory.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				var.setDirectory(TF_Directory.getText());
			}
		});
		
		B_Search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(int i=0;i<toSearch.size();i++){
					if(!existsInMatch(toSearch.get(i))){
						String fileData="";
						File file= new File(TF_Directory.getText()+toSearch.get(i));
						try{
						      BufferedReader in = new BufferedReader(new FileReader(file));
						      String str;
						      while((str=in.readLine())!=null){
						    	  fileData=str;
						      }
						      in.close();
						    }
						    catch(IOException s){
						      System.out.println(TF_Directory.getText()+toSearch.get(i)+" not found");
						    }
						System.out.println(fileData);
						if(!fileData.equals("")){//a simple check to see if a text file has been read
							try {
								var.getMatches().add(new Match(fileData));
								var.getMatchNum().add(toSearch.get(i));
								searched.add(toSearch.get(i));
								directoryHash.add(TF_Directory.getText().hashCode());
							} catch (Exception e1) {}//try catch in case the file doesn't fit the format
						}
					}
				}
				toSearch.clear();
				L_toSearch.updateUI();
				L_Matches.updateUI();
			}
		});
		
		B_Clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searched.clear();
				directoryHash.clear();
				var.getMatches().clear();//clears it in the var where it is stored
				L_Matches.updateUI();
			}
		});
		B_Delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(L_Matches.getSelectedIndex()>=0){
					searched.remove(L_Matches.getSelectedIndex());
					directoryHash.remove(L_Matches.getSelectedIndex());
					var.getMatches().remove(L_Matches.getSelectedIndex());
				}
				L_Matches.updateUI();
			}
		});
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				TF_Directory.setText(var.getDirectory());
			}
		});
		
		

	}
	private void AddToSearch(String match){
		boolean add=true;
		for(int i=0;i<toSearch.size();i++){
			if(toSearch.get(i).equals(match)){
				add=false;
			}
		}
		if(add){
			toSearch.add(match);
		}
	}
	private boolean existsInMatch(String match){
		for(int i=0;i<searched.size();i++){
			if(searched.get(i).equals(match)&&directoryHash.get(i).equals(TF_Directory.getText().hashCode())){
				return true;
			}
		}
		return false;
	}
	public void reset(){
		Sp_Search.setValue(0);
		Sp_MaxSearch.setValue(0);
		Sp_MinSearch.setValue(0);
		directoryHash.clear();
		toSearch.clear();
		searched.clear();
		TF_Directory.setText("");
		CB_Foul.setSelected(false);
		L_toSearch.updateUI();
		L_Matches.updateUI();
	}
	public JPanel getP_Search(){
		return this;
	}

}
