package scouter;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JSpinner;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class scoutPrep extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int defaultGameTime=150;
	private JSpinner Sp_R1;
	private JSpinner Sp_R2;
	private JSpinner Sp_R3;
	private JSpinner Sp_B1;
	private JSpinner Sp_B2;
	private JSpinner Sp_B3;
	private JSpinner Sp_MatchNum;
	private ArrayList<int[]> presetTeams= new ArrayList<int[]>();
	private final JSpinner Sp_gameTime;
	private scoutVar var;
	
	
	/**
	 * Create the panel.
	 */
	public scoutPrep(final scoutVar var) {
		this.var=var;
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblRedAlliance = new JLabel("Red Alliance");
		GridBagConstraints gbc_lblRedAlliance = new GridBagConstraints();
		gbc_lblRedAlliance.weighty = 0.3;
		gbc_lblRedAlliance.weightx = 1.0;
		gbc_lblRedAlliance.insets = new Insets(0, 0, 5, 5);
		gbc_lblRedAlliance.gridx = 0;
		gbc_lblRedAlliance.gridy = 0;
		add(lblRedAlliance, gbc_lblRedAlliance);
		
		JLabel lblBlueAlliance = new JLabel("Blue Alliance");
		GridBagConstraints gbc_lblBlueAlliance = new GridBagConstraints();
		gbc_lblBlueAlliance.weightx = 1.0;
		gbc_lblBlueAlliance.weighty = 0.3;
		gbc_lblBlueAlliance.insets = new Insets(0, 0, 5, 5);
		gbc_lblBlueAlliance.gridx = 1;
		gbc_lblBlueAlliance.gridy = 0;
		add(lblBlueAlliance, gbc_lblBlueAlliance);

		
		Sp_R1 = new JSpinner();
		GridBagConstraints gbc_Sp_R1 = new GridBagConstraints();
		gbc_Sp_R1.weightx = 1.0;
		gbc_Sp_R1.weighty = 1.0;
		gbc_Sp_R1.insets = new Insets(0, 0, 5, 5);
		gbc_Sp_R1.fill = GridBagConstraints.HORIZONTAL;
		gbc_Sp_R1.gridx = 0;
		gbc_Sp_R1.gridy = 1;
		add(Sp_R1, gbc_Sp_R1);
		
		Sp_B1 = new JSpinner();
		GridBagConstraints gbc_Sp_B1 = new GridBagConstraints();
		gbc_Sp_B1.weighty = 1.0;
		gbc_Sp_B1.weightx = 1.0;
		gbc_Sp_B1.insets = new Insets(0, 0, 5, 5);
		gbc_Sp_B1.fill = GridBagConstraints.HORIZONTAL;
		gbc_Sp_B1.gridx = 1;
		gbc_Sp_B1.gridy = 1;
		add(Sp_B1, gbc_Sp_B1);
		
		Sp_R2 = new JSpinner();
		GridBagConstraints gbc_Sp_R2 = new GridBagConstraints();
		gbc_Sp_R2.weighty = 1.0;
		gbc_Sp_R2.weightx = 1.0;
		gbc_Sp_R2.insets = new Insets(0, 0, 5, 5);
		gbc_Sp_R2.fill = GridBagConstraints.HORIZONTAL;
		gbc_Sp_R2.gridx = 0;
		gbc_Sp_R2.gridy = 2;
		add(Sp_R2, gbc_Sp_R2);
		
		Sp_B2 = new JSpinner();
		GridBagConstraints gbc_Sp_B2 = new GridBagConstraints();
		gbc_Sp_B2.weightx = 1.0;
		gbc_Sp_B2.weighty = 1.0;
		gbc_Sp_B2.insets = new Insets(0, 0, 5, 5);
		gbc_Sp_B2.fill = GridBagConstraints.HORIZONTAL;
		gbc_Sp_B2.gridx = 1;
		gbc_Sp_B2.gridy = 2;
		add(Sp_B2, gbc_Sp_B2);
		
		JLabel lblMatch = new JLabel("Match#");
		GridBagConstraints gbc_lblMatch = new GridBagConstraints();
		gbc_lblMatch.insets = new Insets(0, 0, 5, 5);
		gbc_lblMatch.gridx = 3;
		gbc_lblMatch.gridy = 2;
		add(lblMatch, gbc_lblMatch);
		
		JLabel lblGameTime = new JLabel("Game Time");
		GridBagConstraints gbc_lblGameTime = new GridBagConstraints();
		gbc_lblGameTime.insets = new Insets(0, 0, 5, 0);
		gbc_lblGameTime.gridx = 2;
		gbc_lblGameTime.gridy = 2;
		add(lblGameTime, gbc_lblGameTime);
		
		Sp_R3 = new JSpinner();
		GridBagConstraints gbc_Sp_R3 = new GridBagConstraints();
		gbc_Sp_R3.weightx = 1.0;
		gbc_Sp_R3.weighty = 1.0;
		gbc_Sp_R3.insets = new Insets(0, 0, 5, 5);
		gbc_Sp_R3.fill = GridBagConstraints.HORIZONTAL;
		gbc_Sp_R3.gridx = 0;
		gbc_Sp_R3.gridy = 3;
		add(Sp_R3, gbc_Sp_R3);
		
		Sp_B3 = new JSpinner();
		GridBagConstraints gbc_Sp_B3 = new GridBagConstraints();
		gbc_Sp_B3.insets = new Insets(0, 0, 5, 5);
		gbc_Sp_B3.fill = GridBagConstraints.HORIZONTAL;
		gbc_Sp_B3.gridx = 1;
		gbc_Sp_B3.gridy = 3;
		add(Sp_B3, gbc_Sp_B3);
		
		Sp_MatchNum = new JSpinner();
		GridBagConstraints gbc_Sp_MatchNum = new GridBagConstraints();
		gbc_Sp_MatchNum.insets = new Insets(0, 0, 5, 5);
		gbc_Sp_MatchNum.fill = GridBagConstraints.HORIZONTAL;
		gbc_Sp_MatchNum.gridx = 3;
		gbc_Sp_MatchNum.gridy = 3;
		add(Sp_MatchNum, gbc_Sp_MatchNum);
		
		Sp_gameTime = new JSpinner();
		Sp_gameTime.setValue(defaultGameTime);
		var.setGameTime(defaultGameTime);
		GridBagConstraints gbc_Sp_gameTime = new GridBagConstraints();
		gbc_Sp_gameTime.fill = GridBagConstraints.HORIZONTAL;
		gbc_Sp_gameTime.insets = new Insets(0, 0, 5, 0);
		gbc_Sp_gameTime.gridx = 2;
		gbc_Sp_gameTime.gridy = 3;
		add(Sp_gameTime, gbc_Sp_gameTime);
		//action listeners after everything
		Sp_R1.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if((int)Sp_R1.getValue()>=0){
					var.setRed1((int)Sp_R1.getValue());
					var.setTeamComboBox();
				}
				else{
					Sp_R1.setValue(0);
				}
			}
		});
		Sp_R2.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if((int)Sp_R2.getValue()>=0){
					var.setRed2((int)Sp_R2.getValue());
					var.setTeamComboBox();
				}
				else{
					Sp_R2.setValue(0);
				}
			}
		});
		Sp_R3.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if((int)Sp_R3.getValue()>=0){
					var.setRed3((int)Sp_R3.getValue());
					var.setTeamComboBox();
				}
				else{
					Sp_R3.setValue(0);
				}
			}
		});
		Sp_B1.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if((int)Sp_B1.getValue()>=0){
					var.setBlue1((int)Sp_B1.getValue());
					var.setTeamComboBox();
				}
				else{
					Sp_B1.setValue(0);
				}
			}
		});
		Sp_B2.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if((int)Sp_B2.getValue()>=0){
					var.setBlue2((int)Sp_B2.getValue());
					var.setTeamComboBox();
				}
				else{
					Sp_B2.setValue(0);
				}
			}
		});
		Sp_B3.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if((int)Sp_B3.getValue()>=0){
					var.setBlue3((int)Sp_B3.getValue());
					var.setTeamComboBox();
				}
				else{
					Sp_B3.setValue(0);
				}
			}
		});
		Sp_gameTime.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if((int)Sp_gameTime.getValue()>=0){
					var.setGameTime((int)Sp_gameTime.getValue());
				}
				else{
					Sp_gameTime.setValue(0);
				}
			}
		});
		Sp_MatchNum.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if((int)Sp_MatchNum.getValue()>=1){
					var.setMatchNum((int)Sp_MatchNum.getValue());
					if(presetTeams.size()>=(int)Sp_MatchNum.getValue()&&presetTeams.size()>0){
						int index=(int)Sp_MatchNum.getValue()-1;
						Sp_R1.setValue(presetTeams.get(index)[0]);
						Sp_R2.setValue(presetTeams.get(index)[1]);
						Sp_R3.setValue(presetTeams.get(index)[2]);
						Sp_B1.setValue(presetTeams.get(index)[3]);
						Sp_B2.setValue(presetTeams.get(index)[4]);
						Sp_B3.setValue(presetTeams.get(index)[5]);
					}
				}
				else{
					Sp_MatchNum.setValue(1);
				}
			}
		});
		
		

	}
	//checks if value is filled 
	private boolean isFilled(Object value,String name){
		try {
			if(!(((int)value)==0)){
				return true;
			}
		} catch (Exception e) {
			var.getOutput().append("[ERR]:"+name+" is not filled");
			return false;
		}
		var.getOutput().append("[ERR]:"+name+" is not filled");
		return false;
	}
	public JPanel getP_Prep(){
		return this;
	}
	public void addPresetTeam(int[] teams){
		presetTeams.add(teams);
	}
	public void changeDefaultGameTime(){
		defaultGameTime=(int) Sp_gameTime.getValue();
	}
	public void reset(){
		Sp_R1.setValue(0);
		Sp_R2.setValue(0);
		Sp_R3.setValue(0);
		Sp_B1.setValue(0);
		Sp_B2.setValue(0);
		Sp_B3.setValue(0);
		Sp_MatchNum.setValue(0);//game time is not reset as it's assuming same game
		Sp_R1.setEnabled(true);
		Sp_R2.setEnabled(true);
		Sp_R3.setEnabled(true);
		Sp_B1.setEnabled(true);
		Sp_B2.setEnabled(true);
		Sp_B3.setEnabled(true);
		Sp_MatchNum.setEnabled(true);
		Sp_gameTime.setEnabled(true);
		Sp_gameTime.setValue(defaultGameTime);
		var.setGameTime(defaultGameTime);
	}
	

}
