package data;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JButton;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.event.ChangeListener;

import global.Match;
import global.Team;

import javax.swing.event.ChangeEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class dataTeamScore extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -694631902108663798L;
	private JSpinner Sp_TeamNum;
	private ScoreTeamm scoreTeamm;
	private JTable T_TeamScore;
	private dataVar var;
	
	/**
	 * Create the panel.
	 */
	public dataTeamScore(final dataVar var) {
		this.var=var;
		scoreTeamm=new ScoreTeamm(var.getScoreFields());
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		Sp_TeamNum = new JSpinner();
		GridBagConstraints gbc_Sp_TeamNum = new GridBagConstraints();
		gbc_Sp_TeamNum.insets = new Insets(0, 0, 5, 5);
		gbc_Sp_TeamNum.fill = GridBagConstraints.HORIZONTAL;
		gbc_Sp_TeamNum.gridx = 0;
		gbc_Sp_TeamNum.gridy = 0;
		add(Sp_TeamNum, gbc_Sp_TeamNum);
		
		JButton B_Search = new JButton("Search Team");
		GridBagConstraints gbc_B_Search = new GridBagConstraints();
		gbc_B_Search.insets = new Insets(0, 0, 5, 0);
		gbc_B_Search.gridx = 1;
		gbc_B_Search.gridy = 0;
		add(B_Search, gbc_B_Search);
		
		JButton B_Reset = new JButton("Reset");
		GridBagConstraints gbc_B_Reset = new GridBagConstraints();
		gbc_B_Reset.insets = new Insets(0, 0, 5, 0);
		gbc_B_Reset.gridx = 1;
		gbc_B_Reset.gridy = 1;
		add(B_Reset, gbc_B_Reset);
		
		JScrollPane SP_TeamScore = new JScrollPane();
		GridBagConstraints gbc_SP_TeamScore = new GridBagConstraints();
		gbc_SP_TeamScore.fill = GridBagConstraints.BOTH;
		gbc_SP_TeamScore.gridwidth = 2;
		gbc_SP_TeamScore.gridx = 0;
		gbc_SP_TeamScore.gridy = 2;
		add(SP_TeamScore, gbc_SP_TeamScore);
		
		T_TeamScore = new JTable(){
			/**
			 * 
			 */
			private static final long serialVersionUID = -183861494167930082L;
			public boolean getScrollableTracksViewportWidth(){
				return getPreferredSize().width < getParent().getWidth();
			}
		};
		T_TeamScore.setModel(scoreTeamm);
		T_TeamScore.updateUI();
		T_TeamScore.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		T_TeamScore.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		T_TeamScore.setCellSelectionEnabled(true);
		SP_TeamScore.setViewportView(T_TeamScore);
		T_TeamScore.getColumnModel().getColumn(0).setPreferredWidth(50);
		T_TeamScore.getColumnModel().getColumn(1).setPreferredWidth(50);
		T_TeamScore.getColumnModel().getColumn(2).setPreferredWidth(50);
		for(int i=0;i<var.getScoreFields().size();i++){
			if(var.getScoreFields().get(i).getWidth()!=0){
				T_TeamScore.getColumnModel().getColumn(i+3).setPreferredWidth(var.getScoreFields().get(i).getWidth());
			}
		}
		
		//Action Listeners after everything so everything's initiallized
		Sp_TeamNum.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if((int)Sp_TeamNum.getValue()<0){
					Sp_TeamNum.setValue(0);
				}
			}
		});
		B_Search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Team team=new Team((int)Sp_TeamNum.getValue(),var.getTeamFields());
				team.addMatches(var.getMatches());
				int total=0;
				int average=0;
				ArrayList<Integer> scores= new ArrayList<Integer>();
				for(int i=0;i<var.getScoreFields().size();i++){
					scores.add(0);
				}
				for(int i=0;i<team.getMatches().size();i++){
					Match match=team.getMatches().get(i);
					boolean RedBlue=match.containsTeamRed(team.getTeamNum());
					if(RedBlue){
						total+=match.getRScore().get(0);
						for(int c=1;c<match.getRScore().size();c++){
							scores.set(c-1, scores.get(c-1)+match.getRScore().get(c));
						}
					}
					else{
						total+=match.getBScore().get(0);
						for(int c=1;c<match.getBScore().size();c++){
							scores.set(c-1, scores.get(c-1)+match.getBScore().get(c));
						}
					}
				}
				if(team.getMatches().size()>0){
					average=total/team.getMatches().size();
				}
				scoreTeamm.addRow(team.getTeamNum(), total, average, scores);
				T_TeamScore.updateUI();
			}
		});
		
		B_Reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				scoreTeamm.clearTable();
				T_TeamScore.updateUI();
			}
		});
		
		

	}
	public void reset(){
		Sp_TeamNum.setValue(0);
		scoreTeamm.clearTable();
		T_TeamScore.updateUI();
	}
	
	public JPanel getP_TeamScore(){
		return this;
	}

}
