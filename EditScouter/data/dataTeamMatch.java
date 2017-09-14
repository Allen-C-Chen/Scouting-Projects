package data;

import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import javax.swing.JSpinner;
import java.awt.GridBagConstraints;
import javax.swing.JButton;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.event.ChangeListener;
import javax.swing.table.TableModel;

import global.Match;
import global.Team;

import javax.swing.event.ChangeEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.AbstractListModel;

public class dataTeamMatch extends JPanel {
	private JTable T_Data;
	private MatchTeamm matchTeamm=new MatchTeamm();
	private ArrayList<String> matches= new ArrayList<String>();
	private final JList<String> L_Matches;
	private dataVar var;
	private final JSpinner Sp_teamNum;
	
	/**
	 * Create the panel.
	 */
	public dataTeamMatch(final dataVar var) {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		Sp_teamNum = new JSpinner();
		GridBagConstraints gbc_Sp_teamNum = new GridBagConstraints();
		gbc_Sp_teamNum.fill = GridBagConstraints.HORIZONTAL;
		gbc_Sp_teamNum.insets = new Insets(0, 0, 5, 5);
		gbc_Sp_teamNum.gridx = 0;
		gbc_Sp_teamNum.gridy = 0;
		add(Sp_teamNum, gbc_Sp_teamNum);
		
		JLabel lblMatches = new JLabel("Matches");
		GridBagConstraints gbc_lblMatches = new GridBagConstraints();
		gbc_lblMatches.insets = new Insets(0, 0, 5, 0);
		gbc_lblMatches.gridx = 1;
		gbc_lblMatches.gridy = 0;
		add(lblMatches, gbc_lblMatches);
		
		JScrollPane SP_Matches = new JScrollPane();
		GridBagConstraints gbc_SP_Matches = new GridBagConstraints();
		gbc_SP_Matches.fill = GridBagConstraints.BOTH;
		gbc_SP_Matches.gridheight = 3;
		gbc_SP_Matches.insets = new Insets(0, 0, 5, 0);
		gbc_SP_Matches.gridx = 1;
		gbc_SP_Matches.gridy = 1;
		add(SP_Matches, gbc_SP_Matches);
		
		L_Matches = new JList<String>();
		L_Matches.setModel(new AbstractListModel<String>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = -8559798552892774078L;
			public int getSize() {
				return matches.size();
			}
			public String getElementAt(int index) {
				return matches.get(index);
			}
		});
		SP_Matches.setViewportView(L_Matches);
		
		JButton B_Search = new JButton("Search Team");
		GridBagConstraints gbc_B_Search = new GridBagConstraints();
		gbc_B_Search.insets = new Insets(0, 0, 5, 5);
		gbc_B_Search.gridx = 0;
		gbc_B_Search.gridy = 1;
		add(B_Search, gbc_B_Search);
		
		JLabel lblMatchData = new JLabel("Match Data");
		GridBagConstraints gbc_lblMatchData = new GridBagConstraints();
		gbc_lblMatchData.insets = new Insets(0, 0, 5, 5);
		gbc_lblMatchData.gridx = 0;
		gbc_lblMatchData.gridy = 2;
		add(lblMatchData, gbc_lblMatchData);
		
		JScrollPane SP_Data = new JScrollPane();
		GridBagConstraints gbc_SP_Data = new GridBagConstraints();
		gbc_SP_Data.weightx = 1.0;
		gbc_SP_Data.weighty = 100.0;
		gbc_SP_Data.fill = GridBagConstraints.BOTH;
		gbc_SP_Data.insets = new Insets(0, 0, 0, 5);
		gbc_SP_Data.gridx = 0;
		gbc_SP_Data.gridy = 3;
		add(SP_Data, gbc_SP_Data);
		
		T_Data = new JTable(){
			/**
			 * 
			 */
			private static final long serialVersionUID = 3945759716348736609L;
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
		T_Data.setModel(matchTeamm);
		T_Data.getColumnModel().getColumn(0).setMaxWidth(70);
		T_Data.getColumnModel().getColumn(1).setMaxWidth(70);
		SP_Data.setViewportView(T_Data);
		
		//Action Listeners after everything so that everything's initiallized
		Sp_teamNum.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if((int)Sp_teamNum.getValue()<0){
					Sp_teamNum.setValue(0);
				}
			}
		});
		B_Search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				matchTeamm.clearTable();
				Team team= new Team((int)Sp_teamNum.getValue(),var.getTeamFields());
				team.addMatches(var.getMatches());
				for(int i=0;i<team.getMatches().size();i++){
					Match match=team.getMatches().get(i);
					for(int c=0;c<match.getComment().size();c++){
						if(containsTeam(match.getComment().get(c),team.getTeamNum())){
							matchTeamm.addRow(match.getMatchNum(), match.getComState().get(c), match.getComment().get(c));
						}
					}
				}
				matches.clear();
				for(int i=0;i<team.getMatches().size();i++){
					matches.add(team.getMatches().get(i).getMatchNum());
				}
				L_Matches.updateUI();
				T_Data.updateUI();
			}
		});
	}
	/**
	 * 
	 * @param comment comment to check for team number
	 * @param teamNum Number of the team to check
	 * @return whether the team number is in it, will not return teamNumbers that contains the given teamNumbers i.e. given team 32, will not return team 3223
	 */
	private boolean containsTeam(String comment,int teamNum){
		String[] data=comment.split("(?!^)");
		String[] num=(""+teamNum).split("(?!^)");
		int index=0;
		for(int i=0;i<data.length;i++){
			if(data[i].equals(num[index])){
				if(index+1<num.length){
					index++;
				}
				else{
					try{
						Integer.parseInt(data[i+1]);
						return false;
					}
					catch(Exception e){
						return true;
					}
				}
				
			}
		}
		return false;
		
	}
	public void reset(){
		matches.clear();
		matchTeamm.clearTable();
		Sp_teamNum.setValue(0);
		L_Matches.updateUI();
		T_Data.updateUI();
		
		
	}
	public JPanel getP_TeamMatch(){
		return this;
	}

}
