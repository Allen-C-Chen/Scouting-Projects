package scouter;
//TODO test to see why reseting after lock and save, results in timer lock,seems to only come after the game data has been saved??
//TOOD add shortcut way to start the game
import java.awt.EventQueue;

import javax.swing.JScrollBar;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JSlider;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import java.awt.Component;

import javax.swing.JScrollPane;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.DocumentEvent;

import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JCheckBoxMenuItem;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

import javax.swing.event.CaretListener;
import javax.swing.event.CaretEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.Color;
import javax.swing.DefaultListCellRenderer;
import java.awt.Graphics;
import javax.swing.KeyStroke;
/**
 * main class for scouter
 * @author Allen
 *
 */
public class ScoutMain extends JFrame {
    //start thread for countdown timer?
	private s1 sT=new s1("time",true);
	//used to do more of the calculations thing
	private scout s;
	//mass of swing elements(very messy)
	private JPanel contentPane;
	private JTextField comIn;
	private JTextField Red_3;
	private JTextField Red_2;
	private JTextField Red_1;
	private JTextField Blue_3;
	private JTextField Blue_2;
	private JTextField Blue_1;
	private JTextField TF_Time;
	private JTextField TF_Match;
	private JTextField TF_gTime;
	private JTextField TF_RCon;
	private JTextField TF_RTote;
	private JTextField TF_RScore;
	private JTextField TF_RNood;
	private JTextField TF_RPen;
	private JTextField TF_BScore;
	private JTextField TF_BTote;
	private JTextField TF_BCon;
	private JTextField TF_BNood;
	private JTextField TF_BPen;
	private JTextField TF_adTotes;
	private JTextField TF_exTotes;
	private JTextField TF_gCom;
	private JTextField TF_shortcut;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					  javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
					  //tries to set look and feel to windows(the way it was designed to eliminate chances of theings going wrong)
					} catch(Exception e) {
					  System.out.println("Error setting native LAF: " + e);
					  try{
						  javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
						  //if there's an error setting windows look and feel, defaults to system look and feel
					  }
					  catch(Exception ex){
					    ex.printStackTrace();
					  }
				}
				try {
					ScoutMain frame = new ScoutMain();
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
	public ScoutMain() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 327);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFiles = new JMenu("Files");
		menuBar.add(mnFiles);
		
		JMenuItem mReset = new JMenuItem("Reset");
		mnFiles.add(mReset);
		mReset.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R,ActionEvent.CTRL_MASK));
		
		JMenuItem mOpenData = new JMenuItem("Open Data Viewer");
		mnFiles.add(mOpenData);
		
		JMenu mnEdit = new JMenu("Edit");
		menuBar.add(mnEdit);
		
		JMenu mnEditP = new JMenu("Edit Prep");
		mnEdit.add(mnEditP);
		
		JMenuItem mRed_1 = new JMenuItem("Red_1");
		mnEditP.add(mRed_1);
		
		JMenuItem mRed_2 = new JMenuItem("Red_2");
		mnEditP.add(mRed_2);
		
		JMenuItem mRed_3 = new JMenuItem("Red_3");
		mnEditP.add(mRed_3);
		
		JMenuItem mBlue_1 = new JMenuItem("Blue_1");
		mnEditP.add(mBlue_1);
		
		JMenuItem mBlue_2 = new JMenuItem("Blue_2");
		mnEditP.add(mBlue_2);
		
		JMenuItem mBlue_3 = new JMenuItem("Blue_3");
		mnEditP.add(mBlue_3);
		
		JMenuItem mMatch = new JMenuItem("Match");
		mnEditP.add(mMatch);
		
		JMenuItem mntmGameTime = new JMenuItem("Game Time");
		mnEditP.add(mntmGameTime);
		
		JMenu mnEditScore = new JMenu("Edit Score");
		mnEdit.add(mnEditScore);
		
		JMenu mnRed = new JMenu("Red");
		mnEditScore.add(mnRed);
		
		JMenuItem mntmRScore = new JMenuItem("Score");
		mnRed.add(mntmRScore);
		
		JMenuItem mntmRTotes = new JMenuItem("Totes");
		mnRed.add(mntmRTotes);
		
		JMenuItem mntmRContainer = new JMenuItem("Container");
		mnRed.add(mntmRContainer);
		
		JMenuItem mntmRNoodles = new JMenuItem("Noodles");
		mnRed.add(mntmRNoodles);
		
		JMenuItem mntmRPenalties = new JMenuItem("Penalties");
		mnRed.add(mntmRPenalties);
		
		JMenu mnBlue = new JMenu("Blue");
		mnEditScore.add(mnBlue);
		
		JMenuItem mntmBScore = new JMenuItem("Score");
		mnBlue.add(mntmBScore);
		
		JMenuItem mntmBTotes = new JMenuItem("Totes");
		mnBlue.add(mntmBTotes);
		
		JMenuItem mntmBContainer = new JMenuItem("Container");
		mnBlue.add(mntmBContainer);
		
		JMenuItem mntmBNoodles = new JMenuItem("Noodles");
		mnBlue.add(mntmBNoodles);
		
		JMenuItem mntmPenalties = new JMenuItem("Penalties");
		mnBlue.add(mntmPenalties);
		
		JMenu mnSavePrep = new JMenu("Save Prep");
		mnEdit.add(mnSavePrep);
		
		JMenuItem mSRed_1 = new JMenuItem("Red_1");
		mnSavePrep.add(mSRed_1);
		
		JMenuItem mSRed_2 = new JMenuItem("Red_2");
		mnSavePrep.add(mSRed_2);
		
		JMenuItem mSRed_3 = new JMenuItem("Red_3");
		mnSavePrep.add(mSRed_3);
		
		JMenuItem mSBlue_1 = new JMenuItem("Blue_1");
		mnSavePrep.add(mSBlue_1);
		
		JMenuItem mSBlue_2 = new JMenuItem("Blue_2");
		mnSavePrep.add(mSBlue_2);
		
		JMenuItem mSBlue_3 = new JMenuItem(" Blue_3");
		mnSavePrep.add(mSBlue_3);
		
		JMenuItem mSMatch = new JMenuItem("Match");
		mnSavePrep.add(mSMatch);
		
		JMenuItem mSgTime = new JMenuItem("Game Time");
		mnSavePrep.add(mSgTime);
		
		JMenu mnSaveScore = new JMenu("Save Score");
		mnEdit.add(mnSaveScore);
		
		JMenu mnRed_1 = new JMenu("Red");
		mnSaveScore.add(mnRed_1);
		
		JMenuItem mSRScore = new JMenuItem("Score");
		mnRed_1.add(mSRScore);
		
		JMenuItem mSRTotes = new JMenuItem("Totes");
		mnRed_1.add(mSRTotes);
		
		JMenuItem mSRContainer = new JMenuItem("Container");
		mnRed_1.add(mSRContainer);
		
		JMenuItem mSRNoodles = new JMenuItem("Noodles");
		mnRed_1.add(mSRNoodles);
		
		JMenuItem mSRPenalties = new JMenuItem("Penalties");
		mnRed_1.add(mSRPenalties);
		
		JMenu mnBlue_1 = new JMenu("Blue");
		mnSaveScore.add(mnBlue_1);
		
		JMenuItem mSBScore = new JMenuItem("Score");
		mnBlue_1.add(mSBScore);
		
		JMenuItem mSBTotes = new JMenuItem("Totes");
		mnBlue_1.add(mSBTotes);
		
		JMenuItem mSBContainer = new JMenuItem("Container");
		mnBlue_1.add(mSBContainer);
		
		JMenuItem mSBNoodles = new JMenuItem("Noodles");
		mnBlue_1.add(mSBNoodles);
		
		JMenuItem mSBPenalties = new JMenuItem("Penalties");
		mnBlue_1.add(mSBPenalties);
		
		JMenu mnGame = new JMenu("Game");
		menuBar.add(mnGame);
		
		JMenuItem mStartg = new JMenuItem("Start Game");
		mnGame.add(mStartg);
		mStartg.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G,ActionEvent.CTRL_MASK));
		
		JMenuItem mStopTime = new JMenuItem("Stop Timer");
		mnGame.add(mStopTime);
		
		JMenuItem mntmResumeTimer = new JMenuItem("Resume Timer");
		mnGame.add(mntmResumeTimer);
		
		JMenuItem mntmSetMatchAs = new JMenuItem("Set Match as Foul");
		mnGame.add(mntmSetMatchAs);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 0, 414, 154);
		contentPane.add(tabbedPane);
		
		JPanel P_prep = new JPanel();
		tabbedPane.addTab("Preparation", null, P_prep, null);
		P_prep.setLayout(null);
		
		Red_3 = new JTextField();
		Red_3.setBounds(10, 95, 86, 20);
		P_prep.add(Red_3);
		Red_3.setColumns(10);
		
		Red_2 = new JTextField();
		Red_2.setBounds(10, 65, 86, 20);
		P_prep.add(Red_2);
		Red_2.setColumns(10);
		
		Red_1 = new JTextField();
		Red_1.setBounds(10, 33, 86, 20);
		P_prep.add(Red_1);
		Red_1.setColumns(10);
		
		JLabel lblRedAlliance = new JLabel("Red Alliance");
		lblRedAlliance.setBounds(10, 11, 86, 14);
		P_prep.add(lblRedAlliance);
		
		Blue_3 = new JTextField();
		Blue_3.setColumns(10);
		Blue_3.setBounds(106, 95, 86, 20);
		P_prep.add(Blue_3);
		
		Blue_2 = new JTextField();
		Blue_2.setColumns(10);
		Blue_2.setBounds(106, 65, 86, 20);
		P_prep.add(Blue_2);
		
		Blue_1 = new JTextField();
		Blue_1.setColumns(10);
		Blue_1.setBounds(106, 33, 86, 20);
		P_prep.add(Blue_1);
		
		JLabel lblBlueAlliance = new JLabel("Blue Alliance");
		lblBlueAlliance.setBounds(106, 11, 86, 14);
		P_prep.add(lblBlueAlliance);
		
		TF_Time = new JTextField();
		TF_Time.setBounds(313, 95, 86, 20);
		P_prep.add(TF_Time);
		TF_Time.setColumns(10);
		
		TF_Match = new JTextField();
		TF_Match.setBounds(214, 95, 86, 20);
		P_prep.add(TF_Match);
		TF_Match.setColumns(10);
		
		JLabel lblMatch = new JLabel("Match#");
		lblMatch.setBounds(214, 68, 46, 14);
		P_prep.add(lblMatch);
		
		JLabel lblGameTime = new JLabel("Game Time");
		lblGameTime.setBounds(313, 68, 74, 14);
		P_prep.add(lblGameTime);
		
		final JButton B_SLockPrep = new JButton("Lock and Save");
		B_SLockPrep.setBounds(301, 7, 101, 23);
		P_prep.add(B_SLockPrep);
		P_prep.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{Red_1, Red_2, Red_3, Blue_1, Blue_2, Blue_3, TF_Match, TF_Time, lblRedAlliance, lblBlueAlliance, lblMatch, lblGameTime}));
		
		JPanel P_game = new JPanel();
		tabbedPane.addTab("Game", null, P_game, null);
		P_game.setLayout(null);
		
		final JButton B_StartGame = new JButton("Start Game");
		B_StartGame.setBounds(199, 92, 89, 23);
		P_game.add(B_StartGame);
		
		final JSlider exTotes = new JSlider();
		exTotes.setMinorTickSpacing(1);
		exTotes.setMajorTickSpacing(6);
		exTotes.setMaximum(6);
		exTotes.setPaintTicks(true);
		exTotes.setSnapToTicks(true);
		exTotes.setBounds(10, 92, 142, 34);
		P_game.add(exTotes);
		exTotes.setValue(0);
		
		final JSlider adTotes = new JSlider();
		adTotes.setMinorTickSpacing(1);
		adTotes.setMajorTickSpacing(6);
		adTotes.setSnapToTicks(true);
		adTotes.setPaintTicks(true);
		adTotes.setMaximum(6);
		adTotes.setBounds(10, 37, 142, 36);
		P_game.add(adTotes);
		adTotes.setValue(0);
		
		final JCheckBox CB_Con = new JCheckBox("Container added?");
		CB_Con.setBounds(20, 0, 130, 23);
		P_game.add(CB_Con);
		
		JLabel lblTotesAdded = new JLabel("Totes added");
		lblTotesAdded.setBounds(10, 25, 74, 14);
		P_game.add(lblTotesAdded);
		
		JLabel lblExistingTotes = new JLabel("Existing Totes");
		lblExistingTotes.setBounds(10, 70, 74, 14);
		P_game.add(lblExistingTotes);
		
		final JComboBox CB_Team = new JComboBox();
		CB_Team.setModel(new DefaultComboBoxModel(new String[] {"Select Team", "Red_1", "Red_2", "Red_3", "Blue_1", "Blue_2", "Blue_3", "Red Alliance", "Blue Alliance"}));
		CB_Team.setSelectedIndex(0);
		CB_Team.setBounds(310, 64, 89, 20);
		P_game.add(CB_Team);
		
		
		JButton B_Send = new JButton("Send Data");
		B_Send.setBounds(310, 92, 89, 23);
		P_game.add(B_Send);
		
		TF_gTime = new JTextField();
		TF_gTime.setEditable(false);
		TF_gTime.setBounds(202, 64, 86, 20);
		P_game.add(TF_gTime);
		TF_gTime.setColumns(10);
		
		JLabel lblGameTime_1 = new JLabel("Game Time");
		lblGameTime_1.setBounds(202, 45, 86, 14);
		P_game.add(lblGameTime_1);
		
		TF_adTotes = new JTextField();
		TF_adTotes.setBounds(161, 45, 29, 22);
		P_game.add(TF_adTotes);
		TF_adTotes.setColumns(10);
		
		TF_exTotes = new JTextField();
		TF_exTotes.setColumns(10);
		TF_exTotes.setBounds(160, 92, 29, 22);
		P_game.add(TF_exTotes);
		
		TF_gCom = new JTextField();
		TF_gCom.setBounds(257, 23, 142, 20);
		P_game.add(TF_gCom);
		TF_gCom.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Game Comments");
		lblNewLabel.setBounds(310, 4, 89, 14);
		P_game.add(lblNewLabel);
		
		final JCheckBox CB_Nood = new JCheckBox("Noodle in Container?");
		CB_Nood.setBounds(158, 0, 130, 23);
		P_game.add(CB_Nood);
		
		TF_shortcut = new JTextField();
		TF_shortcut.setBounds(232, 23, 15, 20);
		P_game.add(TF_shortcut);
		TF_shortcut.setColumns(10);
		
		JLabel lblShortcut = new JLabel("Shortcut");
		lblShortcut.setBounds(187, 26, 46, 14);
		P_game.add(lblShortcut);
		
		JPanel P_end = new JPanel();
		tabbedPane.addTab("Scoring", null, P_end, null);
		P_end.setLayout(null);
		
		final JButton B_Save = new JButton("Save Data");
		B_Save.setBounds(163, 94, 89, 23);
		P_end.add(B_Save);
		
		TF_RCon = new JTextField();
		TF_RCon.setColumns(10);
		TF_RCon.setBounds(10, 95, 86, 20);
		P_end.add(TF_RCon);
		
		JLabel lblRedContainer = new JLabel("Red Container");
		lblRedContainer.setBounds(10, 81, 86, 14);
		P_end.add(lblRedContainer);
		
		TF_RTote = new JTextField();
		TF_RTote.setColumns(10);
		TF_RTote.setBounds(10, 61, 86, 20);
		P_end.add(TF_RTote);
		
		JLabel lblRedTotes = new JLabel("Red Totes");
		lblRedTotes.setBounds(10, 47, 86, 14);
		P_end.add(lblRedTotes);
		
		TF_RScore = new JTextField();
		TF_RScore.setColumns(10);
		TF_RScore.setBounds(10, 25, 86, 20);
		P_end.add(TF_RScore);
		
		JLabel label_2 = new JLabel("Red Score");
		label_2.setBounds(10, 11, 86, 14);
		P_end.add(label_2);
		
		TF_RNood = new JTextField();
		TF_RNood.setColumns(10);
		TF_RNood.setBounds(106, 25, 86, 20);
		P_end.add(TF_RNood);
		
		JLabel lblRedNoodles = new JLabel("Red Noodles");
		lblRedNoodles.setBounds(106, 11, 86, 14);
		P_end.add(lblRedNoodles);
		
		TF_RPen = new JTextField();
		TF_RPen.setColumns(10);
		TF_RPen.setBounds(106, 61, 86, 20);
		P_end.add(TF_RPen);
		
		JLabel lblRedPenalties = new JLabel("Red Penalties");
		lblRedPenalties.setBounds(106, 47, 86, 14);
		P_end.add(lblRedPenalties);
		
		TF_BScore = new JTextField();
		TF_BScore.setColumns(10);
		TF_BScore.setBounds(313, 25, 86, 20);
		P_end.add(TF_BScore);
		
		JLabel lblBlueScore = new JLabel("Blue Score");
		lblBlueScore.setBounds(313, 11, 86, 14);
		P_end.add(lblBlueScore);
		
		TF_BTote = new JTextField();
		TF_BTote.setColumns(10);
		TF_BTote.setBounds(313, 61, 86, 20);
		P_end.add(TF_BTote);
		
		JLabel lblBlueTotes = new JLabel("Blue Totes");
		lblBlueTotes.setBounds(313, 47, 86, 14);
		P_end.add(lblBlueTotes);
		
		TF_BCon = new JTextField();
		TF_BCon.setColumns(10);
		TF_BCon.setBounds(313, 94, 86, 20);
		P_end.add(TF_BCon);
		
		JLabel lblBlueContainer = new JLabel("Blue Container");
		lblBlueContainer.setBounds(313, 80, 86, 14);
		P_end.add(lblBlueContainer);
		
		TF_BNood = new JTextField();
		TF_BNood.setColumns(10);
		TF_BNood.setBounds(217, 25, 86, 20);
		P_end.add(TF_BNood);
		
		JLabel lblBlueNoodles = new JLabel("Blue Noodles");
		lblBlueNoodles.setBounds(217, 11, 86, 14);
		P_end.add(lblBlueNoodles);
		
		TF_BPen = new JTextField();
		TF_BPen.setColumns(10);
		TF_BPen.setBounds(217, 60, 86, 20);
		P_end.add(TF_BPen);
		
		JLabel lblBluePenaltes = new JLabel("Blue Penaltes");
		lblBluePenaltes.setBounds(217, 46, 86, 14);
		P_end.add(lblBluePenaltes);
		P_end.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{label_2, TF_RScore, lblRedTotes, TF_RTote, lblRedContainer, TF_RCon, lblRedNoodles, TF_RNood, lblRedPenalties, TF_RPen, lblBlueScore, TF_BScore, lblBlueTotes, TF_BTote, lblBlueContainer, TF_BCon, lblBlueNoodles, TF_BNood, lblBluePenaltes, TF_BPen, B_Save}));
		
		comIn = new JTextField();
		comIn.setBounds(213, 155, 211, 20);
		contentPane.add(comIn);
		comIn.setColumns(10);
		
		JLabel lblComments = new JLabel("Comments");
		lblComments.setBounds(157, 158, 57, 14);
		contentPane.add(lblComments);
		
		JScrollPane SP_out = new JScrollPane();
		SP_out.setBounds(10, 177, 414, 85);
		contentPane.add(SP_out);
		final JScrollBar SB_outV=SP_out.getVerticalScrollBar();
		final JTextArea output = new JTextArea();
		output.setEditable(false);
		SP_out.setViewportView(output);
		output.setLineWrap(true);
		
		JLabel lblOutput = new JLabel("Output");
		lblOutput.setBounds(10, 158, 46, 14);
		contentPane.add(lblOutput);
		
		s=new scout(output);
		
		//action listeners,added at end to have all variable initialized
		//button actions
		//an action listener for when something is entered into comments section
		comIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!comIn.getText().equals("")){
					output.append(s.textState(comIn.getText())+"\n");//sends out text with new line 
					comIn.setText(null);//clears comments text field, so you can type other things in
				}
			}
		});
		//an action listener for when you press the button to start the game
		B_StartGame.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent startG){
				boolean continues=true;//used to see if all requirements for action is met
				boolean[] checks=new boolean[8];
				checks[0]=s.checkInNum(Red_1.getText(), "Red 1");
				checks[1]=s.checkInNum(Red_2.getText(), "Red 2");
				checks[2]=s.checkInNum(Red_3.getText(), "Red 3");
				checks[3]=s.checkInNum(Blue_1.getText(), "Blue 1");
				checks[4]=s.checkInNum(Blue_2.getText(), "Blue 2");
				checks[5]=s.checkInNum(Blue_3.getText(), "Blue 3");
				checks[6]=s.checkIn(TF_Match.getText(),"Match#");
				checks[7]=s.checkInNum(TF_Time.getText(), "Game Time");
				//checks to see if all inputs have been entered
				for(int i=0;i<checks.length;i++){
					if(!checks[i]){
						continues=false;
					}
				}
				//if everything has been entered the system starts the game
				if(continues){
					s.setMatch(TF_Match.getText());
					s.setRed(Integer.parseInt(Red_1.getText()), Integer.parseInt(Red_2.getText()), Integer.parseInt(Red_3.getText()));
					s.setBlue(Integer.parseInt(Blue_1.getText()), Integer.parseInt(Blue_2.getText()), Integer.parseInt(Blue_3.getText()));
					sT.start(TF_gTime,Integer.parseInt(TF_Time.getText()),s);//starts the timer
					s.setState("game");
					CB_Team.setModel(new DefaultComboBoxModel(new String[] {"Select Team",Red_1.getText(), Red_2.getText(), Red_3.getText(), Blue_1.getText(), Blue_2.getText(), Blue_3.getText(), "Red Alliance", "Blue Alliance"}));
					B_StartGame.setEnabled(false);//disables button, so that you cannot accidently start the game again
					Red_1.setEditable(false);
					Red_2.setEditable(false);
					Red_3.setEditable(false);
					Blue_1.setEditable(false);
					Blue_2.setEditable(false);
					Blue_3.setEditable(false);
					TF_Time.setEditable(false);
					TF_Match.setEditable(false);
					//disables all text fields temporarily so that they can't be changed mid game, and indicates that current values cannot be changed
					
				}
			}
		});
		//sends game data
		B_Send.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String team;
				switch (CB_Team.getSelectedIndex()){
				case 1:team=Red_1.getText();
				break;
				case 2:team=Red_2.getText();
				break;
				case 3:team=Red_3.getText();
				break;
				case 4:team=Blue_1.getText();
				break;
				case 5:team=Blue_2.getText();
				break;
				case 6:team=Blue_3.getText();
				break;
				case 7:team="Red Alliance";
				break;
				case 8:team="Blue Alliance";
				break;
				default:team="A team";
				}
				s.sendDat(team, CB_Con.isSelected(),CB_Nood.isSelected(), adTotes.getValue(), exTotes.getValue(),TF_gCom.getText());
			    TF_gCom.setText("");
			    CB_Con.setSelected(false);
			    CB_Nood.setSelected(false);
			    adTotes.setValue(0);
			    exTotes.setValue(0);
			}
		});
		//sets score variables;
		B_Save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean continues=true;
				boolean[] checks=new boolean[10];
				checks[0]=s.checkInNum(TF_RScore.getText(), "Red Score");
				checks[1]=s.checkInNum(TF_RTote.getText(), "Red Totes");
				checks[2]=s.checkInNum(TF_RCon.getText(),"Red Containers");
				checks[3]=s.checkInNum(TF_RNood.getText(), "Red Noodles");
				checks[4]=s.checkInNum(TF_RPen.getText(), "Red Penalties");
				checks[5]=s.checkInNum(TF_BScore.getText(), "Blue Score");
				checks[6]=s.checkInNum(TF_BTote.getText(),"Blue Totes");
				checks[7]=s.checkInNum(TF_BCon.getText(), "Blue Containers");
				checks[8]=s.checkInNum(TF_BNood.getText(), "Blue Noodles");
				checks[9]=s.checkInNum(TF_BPen.getText(), "Blue Penalties");
				
				for(int i=0;i<checks.length;i++){
					if(!checks[i]){
						continues=false;
					}
				}
				if(continues){
					s.setRscore(Integer.parseInt(TF_RScore.getText()), Integer.parseInt(TF_RTote.getText()), Integer.parseInt(TF_RCon.getText()), Integer.parseInt(TF_RNood.getText()), Integer.parseInt(TF_RPen.getText()));
					s.setBscore(Integer.parseInt(TF_BScore.getText()), Integer.parseInt(TF_BTote.getText()), Integer.parseInt(TF_BCon.getText()), Integer.parseInt(TF_BNood.getText()), Integer.parseInt(TF_BPen.getText()));
					s.save();
					//Disable score items
					TF_RScore.setEditable(false);
					TF_RTote.setEditable(false);
					TF_RCon.setEditable(false);
					TF_RNood.setEditable(false);
					TF_RPen.setEditable(false);
					TF_BScore.setEditable(false);
					TF_BTote.setEditable(false);
					TF_BCon.setEditable(false);
					TF_BNood.setEditable(false);
					TF_BPen.setEditable(false);
				}
				
			}
		});
		//Only enters game comments
		TF_gCom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String team;
				switch (CB_Team.getSelectedIndex()){
				case 1:team=Red_1.getText();
				break;
				case 2:team=Red_2.getText();
				break;
				case 3:team=Red_3.getText();
				break;
				case 4:team=Blue_1.getText();
				break;
				case 5:team=Blue_2.getText();
				break;
				case 6:team=Blue_3.getText();
				break;
				case 7:team="Red Alliance";
				break;
				case 8:team="Blue Alliance";
				break;
				default:team="A team";
				}
				
				output.append(s.textState(team+" "+TF_gCom.getText())+"\n");
				TF_gCom.setText("");
			}
		});
		
		//game processes		
		//automatically adjusts sliders and text fields when something changes
		exTotes.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				TF_exTotes.setText(""+exTotes.getValue());
				adTotes.setMaximum(6-exTotes.getValue());
				adTotes.setMajorTickSpacing(6-exTotes.getValue());
			}
		});
		//same thing with other slider
		adTotes.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				TF_adTotes.setText(""+adTotes.getValue());
				exTotes.setMaximum(6-adTotes.getValue());
				exTotes.setMajorTickSpacing(6-adTotes.getValue());
			}
		});
		//when value entered, adjusts slider
		TF_exTotes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if(Integer.parseInt(TF_exTotes.getText())>=0&&Integer.parseInt(TF_exTotes.getText())<=exTotes.getMaximum()){
						exTotes.setValue(Integer.parseInt(TF_exTotes.getText()));
					}
				} catch (NumberFormatException e) {
					System.out.println("NaN,exTotes");
				}
			}
		});
		//same thing with other slider
		TF_adTotes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if(Integer.parseInt(TF_adTotes.getText())>=0&&Integer.parseInt(TF_adTotes.getText())<=adTotes.getMaximum()){
						adTotes.setValue(Integer.parseInt(TF_adTotes.getText()));
					}
				} catch (NumberFormatException e) {
					System.out.println("NaN,adTotes");
				}
			}
		});
		//shortcuts action to be done after a key is pressed
		TF_shortcut.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent arg0) {
				if(!TF_shortcut.getText().equals("")){//makes sure that it isn't blank
					if(TF_shortcut.getText().equals("e")){//adds to existing
						if(exTotes.getMaximum()>exTotes.getValue()){
							exTotes.setValue(exTotes.getValue()+1);
						}
					}
					else if(TF_shortcut.getText().equals("q")){//adds to added totes
						if(adTotes.getMaximum()>adTotes.getValue()){
							adTotes.setValue(adTotes.getValue()+1);
						}
					}
					else if(TF_shortcut.getText().equals("w")){//trues container
						CB_Con.setSelected(true);
					}
					else if(TF_shortcut.getText().equals("s")){//trues noodle
						CB_Nood.setSelected(true);
					}
					else if(TF_shortcut.getText().equals("d")){//scrolls down on team list
						if(CB_Team.getSelectedIndex()<CB_Team.getMaximumRowCount()){
							CB_Team.setSelectedIndex(CB_Team.getSelectedIndex()+1);
						}
					}
					else if(TF_shortcut.getText().equals("a")){//scrolls up on team list
						if(CB_Team.getSelectedIndex()>0){
							CB_Team.setSelectedIndex(CB_Team.getSelectedIndex()-1);
						}
					}
				}
			}
		});
		//clears the previous char in textfield as soon as next key is pressed before it is inputed into JTextField
		TF_shortcut.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				TF_shortcut.setText("");
			}
		});
		//when enter is pressed, does the same thing as send data button
		TF_shortcut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String team;
				switch (CB_Team.getSelectedIndex()){
				case 1:team=Red_1.getText();
				break;
				case 2:team=Red_2.getText();
				break;
				case 3:team=Red_3.getText();
				break;
				case 4:team=Blue_1.getText();
				break;
				case 5:team=Blue_2.getText();
				break;
				case 6:team=Blue_3.getText();
				break;
				case 7:team="Red Alliance";
				break;
				case 8:team="Blue Alliance";
				break;
				default:team="A team";
				}
				s.sendDat(team, CB_Con.isSelected(),CB_Nood.isSelected(), adTotes.getValue(), exTotes.getValue(),TF_gCom.getText());
			    TF_gCom.setText("");
			    CB_Con.setSelected(false);
			    CB_Nood.setSelected(false);
			    adTotes.setValue(0);
			    exTotes.setValue(0);
			}
		});
		CB_Team.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				//change color based on whether it's a red team or blue
				if(CB_Team.getSelectedIndex()>0&&CB_Team.getSelectedIndex()<4||CB_Team.getSelectedIndex()==7){
					CB_Team.setRenderer(new DefaultListCellRenderer(){
						private static final long serialVersionUID = 7160090250079376197L;

						public void paint(Graphics g){
							setBackground(Color.RED);
							setForeground(Color.WHITE);
							super.paint(g);
						}
					});
				}
				else if(CB_Team.getSelectedIndex()>3&&CB_Team.getSelectedIndex()<7||CB_Team.getSelectedIndex()==8){
					CB_Team.setRenderer(new DefaultListCellRenderer(){
						private static final long serialVersionUID = -4800002338219599908L;

						public void paint(final Graphics g){
							setBackground(Color.BLUE);
							setForeground(Color.WHITE);
							super.paint(g);
						}
					});
				}
			}
		});
		
		
		//menu actions
		//another way to start game
		mStartg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					boolean continues=true;//used to see if all requirements for action is met
					boolean[] checks=new boolean[8];
					checks[0]=s.checkInNum(Red_1.getText(), "Red 1");
					checks[1]=s.checkInNum(Red_2.getText(), "Red 2");
					checks[2]=s.checkInNum(Red_3.getText(), "Red 3");
					checks[3]=s.checkInNum(Blue_1.getText(), "Blue 1");
					checks[4]=s.checkInNum(Blue_2.getText(), "Blue 2");
					checks[5]=s.checkInNum(Blue_3.getText(), "Blue 3");
					checks[6]=s.checkIn(TF_Match.getText(),"Match#");
					checks[7]=s.checkInNum(TF_Time.getText(), "Game Time");
					//checks to see if all inputs have been entered
					for(int i=0;i<checks.length;i++){
						if(!checks[i]){
							continues=false;
						}
					}
					//if everything has been entered the system starts the game
					if(continues){
						s.setMatch(TF_Match.getText());
						s.setRed(Integer.parseInt(Red_1.getText()), Integer.parseInt(Red_2.getText()), Integer.parseInt(Red_3.getText()));
						s.setBlue(Integer.parseInt(Blue_1.getText()), Integer.parseInt(Blue_2.getText()), Integer.parseInt(Blue_3.getText()));
						sT.start(TF_gTime,Integer.parseInt(TF_Time.getText()),s);//starts the timer
						s.setState("game");
						CB_Team.setModel(new DefaultComboBoxModel(new String[] {"Select Team",Red_1.getText(), Red_2.getText(), Red_3.getText(), Blue_1.getText(), Blue_2.getText(), Blue_3.getText(), "Red Alliance", "Blue Alliance"}));
						B_StartGame.setEnabled(false);//disables button, so that you cannot accidently start the game again
						Red_1.setEditable(false);
						Red_2.setEditable(false);
						Red_3.setEditable(false);
						Blue_1.setEditable(false);
						Blue_2.setEditable(false);
						Blue_3.setEditable(false);
						TF_Time.setEditable(false);
						TF_Match.setEditable(false);
						//disables all text fields temporarily so that they can't be changed mid game, and indicates that current values cannot be changed

					}
				
			}
		});
		//saves and locks all the prep variables
		B_SLockPrep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean continues=true;//used to see if all requirements for action is met
				boolean[] checks=new boolean[8];
				checks[0]=s.checkInNum(Red_1.getText(), "Red 1");
				checks[1]=s.checkInNum(Red_2.getText(), "Red 2");
				checks[2]=s.checkInNum(Red_3.getText(), "Red 3");
				checks[3]=s.checkInNum(Blue_1.getText(), "Blue 1");
				checks[4]=s.checkInNum(Blue_2.getText(), "Blue 2");
				checks[5]=s.checkInNum(Blue_3.getText(), "Blue 3");
				checks[6]=s.checkIn(TF_Match.getText(),"Match#");
				checks[7]=s.checkInNum(TF_Time.getText(), "Game Time");
				//checks to see if all inputs have been entered
				for(int i=0;i<checks.length;i++){
					if(!checks[i]){
						continues=false;
					}
				}
				//if everything has been entered the system saves and locks the data
				if(continues){
					s.setMatch(TF_Match.getText());
					s.setRed(Integer.parseInt(Red_1.getText()), Integer.parseInt(Red_2.getText()), Integer.parseInt(Red_3.getText()));
					s.setBlue(Integer.parseInt(Blue_1.getText()), Integer.parseInt(Blue_2.getText()), Integer.parseInt(Blue_3.getText()));						
					B_SLockPrep.setEnabled(false);//disables button, so that you cannot accidently lock and save the data again
					CB_Team.setModel(new DefaultComboBoxModel(new String[] {"Select Team",Red_1.getText(), Red_2.getText(), Red_3.getText(), Blue_1.getText(), Blue_2.getText(), Blue_3.getText(), "Red Alliance", "Blue Alliance"}));
					Red_1.setEditable(false);
					Red_2.setEditable(false);
					Red_3.setEditable(false);
					Blue_1.setEditable(false);
					Blue_2.setEditable(false);
					Blue_3.setEditable(false);
					TF_Time.setEditable(false);
					TF_Match.setEditable(false);
					//disables all text fields temporarily so that they can't be changed mid game, and indicates that current values cannot be changed

				}

			}
		});
		//opens in a new window the data viewer
		mOpenData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							  javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
							  //tries to set look and feel to windows(the way it was designed to eliminate chances of theings going wrong)
							} catch(Exception e) {
							  System.out.println("Error setting native LAF: " + e);
							  try{
								  javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
								  //if there's an error setting windows look and feel, defaults to system look and feel
							  }
							  catch(Exception ex){
							    ex.printStackTrace();
							  }
						}
						try {
							DataMain frame = new DataMain();
							frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		//resets everything for new game
		mReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//enable all the prep text fields and set their text to nothing.
				Red_1.setEditable(true);
				Red_2.setEditable(true);
				Red_3.setEditable(true);
				Blue_1.setEditable(true);
				Blue_2.setEditable(true);
				Blue_3.setEditable(true);
				TF_Time.setEditable(true);
				TF_Match.setEditable(true);
				Red_1.setText("");
				Red_2.setText("");
				Red_3.setText("");
				Blue_1.setText("");
				Blue_2.setText("");
				Blue_3.setText("");
				TF_Time.setText("");
				TF_Match.setText("");
				B_SLockPrep.setEnabled(true);
				//Reset everything in the game tab
				TF_gCom.setText("");
			    CB_Con.setSelected(false);
			    CB_Nood.setSelected(false);
			    adTotes.setValue(0);
			    exTotes.setValue(0);
			    //resets all score items
			    TF_RScore.setText("");
				TF_RTote.setText("");
				TF_RCon.setText("");
				TF_RNood.setText("");
				TF_RPen.setText("");
				TF_BScore.setText("");
				TF_BTote.setText("");
				TF_BCon.setText("");
				TF_BNood.setText("");
				TF_BPen.setText("");
				TF_RScore.setEditable(true);
				TF_RTote.setEditable(true);
				TF_RCon.setEditable(true);
				TF_RNood.setEditable(true);
				TF_RPen.setEditable(true);
				TF_BScore.setEditable(true);
				TF_BTote.setEditable(true);
				TF_BCon.setEditable(true);
				TF_BNood.setEditable(true);
				TF_BPen.setEditable(true);
				//stops game if there's an active game
				s.stopGame();
				B_StartGame.setEnabled(true);//reenables game button
				s.setState("Prep");
				s.clearPrint();
				output.setText("");//clears output(to clean things up)
			}
		});
		//timer stop and resume
		mStopTime.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				s.stopTime();
			}
		});
		mntmResumeTimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				s.resumeTime();
			}
		});
		//in the case a foul occurs and game reset is called the game is saved without score values and everything 
		mntmSetMatchAs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//sets scores as 0 as match has not finished
				s.setRscore(0,0,0,0,0);
				s.setBscore(0,0,0,0,0);
				//adds foul tag to the match
				s.setMatch(s.getMatch()+"(foul)");
				//saves all the current data
				s.save();
				//runs the reset code to prepare for a new game
				//enable all the prep text fields and set their text to nothing.
				Red_1.setEditable(true);
				Red_2.setEditable(true);
				Red_3.setEditable(true);
				Blue_1.setEditable(true);
				Blue_2.setEditable(true);
				Blue_3.setEditable(true);
				TF_Time.setEditable(true);
				TF_Match.setEditable(true);
				Red_1.setText("");
				Red_2.setText("");
				Red_3.setText("");
				Blue_1.setText("");
				Blue_2.setText("");
				Blue_3.setText("");
				TF_Time.setText("");
				TF_Match.setText("");
				B_SLockPrep.setEnabled(true);
				//Reset everything in the game tab
				TF_gCom.setText("");
			    CB_Con.setSelected(false);
			    CB_Nood.setSelected(false);
			    adTotes.setValue(0);
			    exTotes.setValue(0);
			    //resets all score items
			    TF_RScore.setText("");
				TF_RTote.setText("");
				TF_RCon.setText("");
				TF_RNood.setText("");
				TF_RPen.setText("");
				TF_BScore.setText("");
				TF_BTote.setText("");
				TF_BCon.setText("");
				TF_BNood.setText("");
				TF_BPen.setText("");
				TF_RScore.setEditable(true);
				TF_RTote.setEditable(true);
				TF_RCon.setEditable(true);
				TF_RNood.setEditable(true);
				TF_RPen.setEditable(true);
				TF_BScore.setEditable(true);
				TF_BTote.setEditable(true);
				TF_BCon.setEditable(true);
				TF_BNood.setEditable(true);
				TF_BPen.setEditable(true);
				//stops game if there's an active game
				s.stopGame();
				B_StartGame.setEnabled(true);//reenables game button
				s.setState("Prep");
				s.clearPrint();
				output.setText("");//clears output(to clean things up)
			}
		});
		
		
		//actions for editing various variables
		mRed_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Red_1.setEditable(true);
				B_SLockPrep.setEnabled(true);
			}
		});
		mRed_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Red_2.setEditable(true);
				B_SLockPrep.setEnabled(true);
			}
		});
		mRed_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Red_3.setEditable(true);
				B_SLockPrep.setEnabled(true);
			}
		});
		mBlue_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Blue_1.setEditable(true);
				B_SLockPrep.setEnabled(true);
			}
		});
		mBlue_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Blue_2.setEditable(true);
				B_SLockPrep.setEnabled(true);
			}
		});
		mBlue_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Blue_3.setEditable(true);
				B_SLockPrep.setEnabled(true);
			}
		});
		mMatch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TF_Match.setEditable(true);
				B_SLockPrep.setEnabled(true);
			}
		});
		mntmGameTime.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TF_Time.setEditable(true);
				B_SLockPrep.setEnabled(true);
			}
		});
		mntmRScore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TF_RScore.setEditable(true);
				B_Save.setEnabled(true);
			}
		});
		mntmRTotes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TF_RTote.setEditable(true);
				B_Save.setEnabled(true);
			}
		});
		mntmRContainer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TF_RCon.setEditable(true);
				B_Save.setEnabled(true);
			}
		});
		mntmRNoodles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TF_RNood.setEditable(true);
				B_Save.setEnabled(true);
			}
		});
		mntmRPenalties.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TF_RPen.setEditable(true);
				B_Save.setEnabled(true);
			}
		});
		mntmBScore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TF_BScore.setEditable(true);
				B_Save.setEnabled(true);
			}
		});
		mntmBTotes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TF_BTote.setEditable(true);
				B_Save.setEnabled(true);
			}
		});
		mntmBContainer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TF_BCon.setEditable(true);
				B_Save.setEnabled(true);
			}
		});
		mntmBNoodles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TF_BNood.setEditable(true);
				B_Save.setEnabled(true);
			}
		});
		mntmPenalties.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TF_BPen.setEditable(true);
				B_Save.setEnabled(true);
			}
		});
		
		//actions for saving various variable
		mSRed_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(s.checkInNum(Red_1.getText(), "Red_1")){
					Red_1.setEditable(false);
					s.setRed(0,Integer.parseInt(Red_1.getText()));
				}
			}
		});
		mSRed_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(s.checkInNum(Red_2.getText(), "Red_2")){
					Red_2.setEditable(false);
					s.setRed(1,Integer.parseInt(Red_2.getText()));
				}
			}
		});
		mSRed_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(s.checkInNum(Red_3.getText(), "Red_3")){
					Red_3.setEditable(false);
					s.setRed(2,Integer.parseInt(Red_3.getText()));
				}
			}
		});
		mSBlue_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(s.checkInNum(Blue_1.getText(), "Blue_1")){
					Blue_1.setEditable(false);
					s.setBlue(0,Integer.parseInt(Blue_1.getText()));
				}
			}
		});
		mSBlue_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(s.checkInNum(Blue_2.getText(), "Blue_2")){
					Blue_2.setEditable(false);
					s.setBlue(1,Integer.parseInt(Blue_2.getText()));
				}
			}
		});
		mSBlue_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(s.checkInNum(Blue_3.getText(), "Blue_3")){
					Blue_3.setEditable(false);
					s.setBlue(2,Integer.parseInt(Blue_3.getText()));
				}
			}
		});
		mSMatch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(s.checkIn(TF_Match.getText(), "Match#")){
					TF_Match.setEditable(false);
					s.setMatch(TF_Match.getText());
				}
			}
		});
		mSgTime.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(s.checkIn(TF_Time.getText(), "Game Time")){
					TF_Time.setEditable(false);
				}
			}
		});
		mSRScore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(s.checkInNum(TF_RScore.getText(), "Red Score")){
					TF_RScore.setEditable(false);
					s.setRscore(0,Integer.parseInt(TF_RScore.getText()));
				}
			}
		});
		mSRTotes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(s.checkInNum(TF_RTote.getText(), "Red Totes")){
					TF_RTote.setEditable(false);
					s.setRscore(1,Integer.parseInt(TF_RTote.getText()));
				}
			}
		});
		mSRContainer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(s.checkInNum(TF_RCon.getText(), "Red Containers")){
					TF_RCon.setEditable(false);
					s.setRscore(2,Integer.parseInt(TF_RCon.getText()));
				}
			}
		});
		mSRNoodles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(s.checkInNum(TF_RNood.getText(), "Red Noodles")){
					TF_RNood.setEditable(false);
					s.setRscore(3,Integer.parseInt(TF_RNood.getText()));
				}
			}
		});
		mSRPenalties.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(s.checkInNum(TF_RPen.getText(), "Red Penalties")){
					TF_RPen.setEditable(false);
					s.setRscore(4,Integer.parseInt(TF_RPen.getText()));
				}
			}
		});
		mSBScore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(s.checkInNum(TF_BScore.getText(), "Blue Score")){
					TF_BScore.setEditable(false);
					s.setBscore(0,Integer.parseInt(TF_BScore.getText()));
				}
			}
		});
		mSBTotes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(s.checkInNum(TF_BTote.getText(), "Blue Totes")){
					TF_BTote.setEditable(false);
					s.setBscore(1,Integer.parseInt(TF_BTote.getText()));
				}
			}
		});
		mSBContainer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(s.checkInNum(TF_BCon.getText(), "Blue Container")){
					TF_BCon.setEditable(false);
					s.setBscore(2,Integer.parseInt(TF_BCon.getText()));
				}
			}
		});
		mSBNoodles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(s.checkInNum(TF_BNood.getText(), "Blue Noodles")){
					TF_BNood.setEditable(false);
					s.setBscore(3,Integer.parseInt(TF_BNood.getText()));
				}
			}
		});
		mSBPenalties.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(s.checkInNum(TF_BPen.getText(), "Blue Penatlies")){
					TF_BPen.setEditable(false);
					s.setBscore(4,Integer.parseInt(TF_BPen.getText()));
				}
			}
		});
		
		
		
		//output text area action autoscrolls
		output.getDocument().addDocumentListener(new OutputDocListener(output,SB_outV));
		
		
		
	}
}
