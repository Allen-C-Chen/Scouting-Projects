package scouterEdit;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JSpinner;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.event.ChangeEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class TeamPresetEdit extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 221104918988393069L;
	private JPanel contentPane;
	private JFileChooser fc= new JFileChooser();
	private ArrayList<ArrayList<Integer>> teamPresets= new ArrayList<ArrayList<Integer>>();
	private JSpinner Sp_R1;
	private JSpinner Sp_B1;
	private JSpinner Sp_R2;
	private JSpinner Sp_B2;
	private JSpinner Sp_MatchNum;
	private JSpinner Sp_R3;
	private JSpinner Sp_B3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TeamPresetEdit frame = new TeamPresetEdit();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TeamPresetEdit() {
		fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblRed = new JLabel("Red 1");
		GridBagConstraints gbc_lblRed = new GridBagConstraints();
		gbc_lblRed.weighty = 0.5;
		gbc_lblRed.weightx = 1.0;
		gbc_lblRed.insets = new Insets(0, 0, 5, 5);
		gbc_lblRed.gridx = 0;
		gbc_lblRed.gridy = 0;
		contentPane.add(lblRed, gbc_lblRed);
		
		JLabel lblBlue = new JLabel("Blue 1");
		GridBagConstraints gbc_lblBlue = new GridBagConstraints();
		gbc_lblBlue.weightx = 1.0;
		gbc_lblBlue.weighty = 0.5;
		gbc_lblBlue.insets = new Insets(0, 0, 5, 5);
		gbc_lblBlue.gridx = 1;
		gbc_lblBlue.gridy = 0;
		contentPane.add(lblBlue, gbc_lblBlue);
		
		Sp_R1 = new JSpinner();
		GridBagConstraints gbc_Sp_R1 = new GridBagConstraints();
		gbc_Sp_R1.weighty = 1.0;
		gbc_Sp_R1.weightx = 1.0;
		gbc_Sp_R1.fill = GridBagConstraints.HORIZONTAL;
		gbc_Sp_R1.insets = new Insets(0, 0, 5, 5);
		gbc_Sp_R1.gridx = 0;
		gbc_Sp_R1.gridy = 1;
		contentPane.add(Sp_R1, gbc_Sp_R1);
		
		Sp_B1 = new JSpinner();
		GridBagConstraints gbc_Sp_B1 = new GridBagConstraints();
		gbc_Sp_B1.fill = GridBagConstraints.HORIZONTAL;
		gbc_Sp_B1.weightx = 1.0;
		gbc_Sp_B1.weighty = 1.0;
		gbc_Sp_B1.insets = new Insets(0, 0, 5, 5);
		gbc_Sp_B1.gridx = 1;
		gbc_Sp_B1.gridy = 1;
		contentPane.add(Sp_B1, gbc_Sp_B1);
		
		JLabel lblRed_1 = new JLabel("Red 2");
		GridBagConstraints gbc_lblRed_1 = new GridBagConstraints();
		gbc_lblRed_1.weighty = 0.5;
		gbc_lblRed_1.weightx = 1.0;
		gbc_lblRed_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblRed_1.gridx = 0;
		gbc_lblRed_1.gridy = 2;
		contentPane.add(lblRed_1, gbc_lblRed_1);
		
		JLabel lblBlue_1 = new JLabel("Blue 2");
		GridBagConstraints gbc_lblBlue_1 = new GridBagConstraints();
		gbc_lblBlue_1.weighty = 0.5;
		gbc_lblBlue_1.weightx = 1.0;
		gbc_lblBlue_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblBlue_1.gridx = 1;
		gbc_lblBlue_1.gridy = 2;
		contentPane.add(lblBlue_1, gbc_lblBlue_1);
		
		JLabel lblMatch = new JLabel("Match#");
		GridBagConstraints gbc_lblMatch = new GridBagConstraints();
		gbc_lblMatch.weightx = 0.1;
		gbc_lblMatch.insets = new Insets(0, 0, 5, 0);
		gbc_lblMatch.gridx = 2;
		gbc_lblMatch.gridy = 2;
		contentPane.add(lblMatch, gbc_lblMatch);
		
		Sp_R2 = new JSpinner();
		GridBagConstraints gbc_Sp_R2 = new GridBagConstraints();
		gbc_Sp_R2.fill = GridBagConstraints.HORIZONTAL;
		gbc_Sp_R2.weightx = 1.0;
		gbc_Sp_R2.weighty = 1.0;
		gbc_Sp_R2.insets = new Insets(0, 0, 5, 5);
		gbc_Sp_R2.gridx = 0;
		gbc_Sp_R2.gridy = 3;
		contentPane.add(Sp_R2, gbc_Sp_R2);
		
		Sp_B2 = new JSpinner();
		GridBagConstraints gbc_Sp_B2 = new GridBagConstraints();
		gbc_Sp_B2.fill = GridBagConstraints.HORIZONTAL;
		gbc_Sp_B2.weighty = 1.0;
		gbc_Sp_B2.weightx = 1.0;
		gbc_Sp_B2.insets = new Insets(0, 0, 5, 5);
		gbc_Sp_B2.gridx = 1;
		gbc_Sp_B2.gridy = 3;
		contentPane.add(Sp_B2, gbc_Sp_B2);
		
		Sp_MatchNum = new JSpinner();
		GridBagConstraints gbc_Sp_MatchNum = new GridBagConstraints();
		gbc_Sp_MatchNum.fill = GridBagConstraints.HORIZONTAL;
		gbc_Sp_MatchNum.insets = new Insets(0, 0, 5, 0);
		gbc_Sp_MatchNum.gridx = 2;
		gbc_Sp_MatchNum.gridy = 3;
		contentPane.add(Sp_MatchNum, gbc_Sp_MatchNum);
		Sp_MatchNum.setValue(1);
		
		JLabel lblRed_2 = new JLabel("Red 3");
		GridBagConstraints gbc_lblRed_2 = new GridBagConstraints();
		gbc_lblRed_2.weightx = 1.0;
		gbc_lblRed_2.weighty = 0.5;
		gbc_lblRed_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblRed_2.gridx = 0;
		gbc_lblRed_2.gridy = 4;
		contentPane.add(lblRed_2, gbc_lblRed_2);
		
		JLabel lblBlue_2 = new JLabel("Blue 3");
		GridBagConstraints gbc_lblBlue_2 = new GridBagConstraints();
		gbc_lblBlue_2.weighty = 0.5;
		gbc_lblBlue_2.weightx = 1.0;
		gbc_lblBlue_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblBlue_2.gridx = 1;
		gbc_lblBlue_2.gridy = 4;
		contentPane.add(lblBlue_2, gbc_lblBlue_2);
		
		Sp_R3 = new JSpinner();
		GridBagConstraints gbc_Sp_R3 = new GridBagConstraints();
		gbc_Sp_R3.fill = GridBagConstraints.HORIZONTAL;
		gbc_Sp_R3.weightx = 1.0;
		gbc_Sp_R3.weighty = 1.0;
		gbc_Sp_R3.insets = new Insets(0, 0, 5, 5);
		gbc_Sp_R3.gridx = 0;
		gbc_Sp_R3.gridy = 5;
		contentPane.add(Sp_R3, gbc_Sp_R3);
		
		Sp_B3 = new JSpinner();
		GridBagConstraints gbc_Sp_B3 = new GridBagConstraints();
		gbc_Sp_B3.fill = GridBagConstraints.HORIZONTAL;
		gbc_Sp_B3.weightx = 1.0;
		gbc_Sp_B3.weighty = 1.0;
		gbc_Sp_B3.insets = new Insets(0, 0, 5, 5);
		gbc_Sp_B3.gridx = 1;
		gbc_Sp_B3.gridy = 5;
		contentPane.add(Sp_B3, gbc_Sp_B3);
		
		JButton B_Save = new JButton("Save");
		GridBagConstraints gbc_B_Save = new GridBagConstraints();
		gbc_B_Save.insets = new Insets(0, 0, 5, 0);
		gbc_B_Save.gridx = 2;
		gbc_B_Save.gridy = 5;
		contentPane.add(B_Save, gbc_B_Save);
		//action listeners
		Sp_R1.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if(!((int)Sp_MatchNum.getValue()<teamPresets.size())){
					System.out.println("adding");
					addUntilIndex((int)Sp_MatchNum.getValue());
				}
				teamPresets.get((int)Sp_MatchNum.getValue()-1).set(0,(int)Sp_R1.getValue());
			}
		});
		Sp_R2.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if(!((int)Sp_MatchNum.getValue()<teamPresets.size())){
					addUntilIndex((int)Sp_MatchNum.getValue());
				}
				teamPresets.get((int)Sp_MatchNum.getValue()-1).set(1,(int)Sp_R2.getValue());
			}
		});
		Sp_R3.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if(!((int)Sp_MatchNum.getValue()<teamPresets.size())){
					addUntilIndex((int)Sp_MatchNum.getValue());
				}
				teamPresets.get((int)Sp_MatchNum.getValue()-1).set(2,(int)Sp_R3.getValue());
			}
		});
		Sp_B1.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if(!((int)Sp_MatchNum.getValue()<teamPresets.size())){
					addUntilIndex((int)Sp_MatchNum.getValue());
				}
				teamPresets.get((int)Sp_MatchNum.getValue()-1).set(3,(int)Sp_B1.getValue());
			}
		});
		Sp_B2.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if(!((int)Sp_MatchNum.getValue()<teamPresets.size())){
					addUntilIndex((int)Sp_MatchNum.getValue());
				}
				teamPresets.get((int)Sp_MatchNum.getValue()-1).set(4,(int)Sp_B2.getValue());
			}
		});
		Sp_B3.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if(!((int)Sp_MatchNum.getValue()<teamPresets.size())){
					addUntilIndex((int)Sp_MatchNum.getValue());
				}
				teamPresets.get((int)Sp_MatchNum.getValue()-1).set(5,(int)Sp_B3.getValue());
			}
		});
		Sp_MatchNum.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if((int)Sp_MatchNum.getValue()>=1){
					setValues();
				}
				else{
					Sp_MatchNum.setValue(1);
				}
			}
		});
		B_Save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int returnVal= fc.showSaveDialog(fc);
				if(returnVal==JFileChooser.APPROVE_OPTION){
					File file=fc.getSelectedFile();
					if(!file.getName().toUpperCase().endsWith(".TXT")){
						file.renameTo(new File(file.getName()+".txt"));
					}
					try{
						BufferedWriter out = new BufferedWriter(new FileWriter(file));
						out.write(getPrintOut());
						out.close();
					}
					catch(IOException e1){
						System.out.println("Write in file error");
					}
				}
			}
		});
		
		
	}
	private void setValues(){
		if(teamPresets.size()>=(int)Sp_MatchNum.getValue()){
			int index=(int)Sp_MatchNum.getValue()-1;
			Sp_R1.setValue(teamPresets.get(index).get(0));
			Sp_R2.setValue(teamPresets.get(index).get(1));
			Sp_R3.setValue(teamPresets.get(index).get(2));
			Sp_B1.setValue(teamPresets.get(index).get(3));
			Sp_B2.setValue(teamPresets.get(index).get(4));
			Sp_B3.setValue(teamPresets.get(index).get(5));
		}
	}
	public void addUntilIndex(int index){
		for(int i=teamPresets.size();i<index;i++){
			System.out.println("adding nullvec");
			ArrayList<Integer> nullVec= new ArrayList<Integer>();
			for(int c=0;c<6;c++){
				nullVec.add(0);
			}
			teamPresets.add(nullVec);//adds null values until index
		}
	}
	private String getPrintOut(){
		String printOut="";
		for(ArrayList<Integer> teams:teamPresets){
			for(int teamNum:teams){
				printOut+=teamNum+",";
			}
			printOut=printOut.substring(0, printOut.length()-1);
			printOut+=":";
		}
		if(printOut.length()>0){
			printOut=printOut.substring(0, printOut.length()-1);
		}
		return printOut;
	}

}
