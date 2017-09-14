package scouter;

import java.awt.EventQueue;
import javax.swing.JScrollBar;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTabbedPane;
import javax.swing.JMenuBar;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.table.DefaultTableModel;
import scouter.matchTablem;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
/**
 * main class used to handle the frame and other visual aspects of viewing the data collected by the scouter
 */
public class DataMain extends JFrame {
	//table models, passed to data class for further processing
	private matchTablem tm;
	private matchdTablem tdm;
	private scoreTablem sm;
	private scoreTTablem tsm;
	private teamTablem ttm;
	//used for further calculations
	private data d=new data();
	//search strings? dunno
	private String matches="";
	private String teams="";
	private String team="";
	//swing elements
	private JPanel contentPane;
	private JTextField TF_matchNum;
	private JTable T_matchd;
	private JTable T_match;
	private JTextField TF_minRS;
	private JTextField TF_maxRS;
	private JTextField TF_S;
	private JTable T_score;
	private JTextField TF_teamS;
	private JTable T_Tscore;
	private JTextField TF_minS;
	private JTextField TF_maxS;
	private JTextField TF_addMT;
	private JTextField TF_team;
	private JTable T_team;
	

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
					DataMain frame = new DataMain();
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
	public DataMain() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 715, 445);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenu mnMatch = new JMenu("Match");
		menuBar.add(mnMatch);
		
		JMenuItem mntmDisplaySearchteams = new JMenuItem("Display searchTeams");
		mnMatch.add(mntmDisplaySearchteams);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane SP_out = new JScrollPane();
		SP_out.setBounds(10, 301, 677, 81);
		contentPane.add(SP_out);
		final JScrollBar SB_outV=SP_out.getVerticalScrollBar();
		
		final JTextArea output = new JTextArea();
		output.setLineWrap(true);
		output.setEditable(false);
		SP_out.setViewportView(output);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 677, 279);
		contentPane.add(tabbedPane);
		
		JPanel P_match = new JPanel();
		tabbedPane.addTab("Match Data", null, P_match, null);
		P_match.setLayout(null);
		
		TF_matchNum = new JTextField();
		TF_matchNum.setBounds(10, 22, 86, 20);
		P_match.add(TF_matchNum);
		TF_matchNum.setColumns(10);
		
		JLabel lblMatchToRead = new JLabel("Match# to read");
		lblMatchToRead.setBounds(10, 7, 86, 14);
		P_match.add(lblMatchToRead);
		
		JScrollPane SP_matchD = new JScrollPane();
		SP_matchD.setBounds(10, 100, 652, 140);
		P_match.add(SP_matchD);
		
		T_matchd = new JTable();
		SP_matchD.setViewportView(T_matchd);
		T_matchd.setModel(tdm = new matchdTablem());
		T_matchd.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		T_matchd.getColumnModel().getColumn(0).setMaxWidth(75);
		
		JScrollPane SP_matchS = new JScrollPane();
		SP_matchS.setBounds(179, 0, 483, 89);
		P_match.add(SP_matchS);
		
		T_match = new JTable();
		T_match.setToolTipText("Displays the scores of the match,values are repeated for the each team");
		SP_matchS.setViewportView(T_match);
		T_match.setModel(tm = new matchTablem());
		T_match.getColumnModel().getColumn(0).setPreferredWidth(92);
		T_match.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		
		JButton B_ReadMD = new JButton("Read Data");
		B_ReadMD.setBounds(10, 42, 89, 23);
		P_match.add(B_ReadMD);
		
		
		
		
		
		
		JButton B_saveEditM = new JButton("Save Edits");
		B_saveEditM.setBounds(10, 76, 89, 23);
		P_match.add(B_saveEditM);
		
		JButton B_mNext = new JButton("Next");
		B_mNext.setBounds(109, 42, 72, 23);
		P_match.add(B_mNext);
		
		JButton B_mPrev = new JButton("Prev");
		B_mPrev.setBounds(109, 21, 72, 23);
		P_match.add(B_mPrev);
		
		JPanel P_score = new JPanel();
		tabbedPane.addTab("Score Data", null, P_score, null);
		P_score.setLayout(null);
		
		TF_minRS = new JTextField();
		TF_minRS.setBounds(20, 25, 41, 20);
		P_score.add(TF_minRS);
		TF_minRS.setColumns(10);
		
		JLabel lblMatchRange = new JLabel("Match Range");
		lblMatchRange.setBounds(20, 0, 63, 14);
		P_score.add(lblMatchRange);
		
		TF_maxRS = new JTextField();
		TF_maxRS.setColumns(10);
		TF_maxRS.setBounds(71, 25, 41, 20);
		P_score.add(TF_maxRS);
		
		JButton B_addRS = new JButton("Add to search ");
		B_addRS.setBounds(20, 50, 103, 23);
		P_score.add(B_addRS);
		
		JButton B_addS = new JButton("Add to search ");
		B_addS.setBounds(133, 50, 103, 23);
		P_score.add(B_addS);
		
		TF_S = new JTextField();
		TF_S.setBounds(133, 25, 86, 20);
		P_score.add(TF_S);
		TF_S.setColumns(10);
		
		JLabel lblAdditionalMatches = new JLabel("Additional Matches");
		lblAdditionalMatches.setBounds(133, 0, 103, 14);
		P_score.add(lblAdditionalMatches);
		
		final JButton B_resetMS = new JButton("Reset Search");
		B_resetMS.setEnabled(false);
		B_resetMS.setBounds(246, 50, 103, 23);
		P_score.add(B_resetMS);
		
		JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_1.setBounds(10, 73, 652, 167);
		P_score.add(tabbedPane_1);
		
		JScrollPane SP_score = new JScrollPane();
		tabbedPane_1.addTab("Match", null, SP_score, null);
		
		T_score = new JTable();
		T_score.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		SP_score.setViewportView(T_score);
		T_score.setModel(sm = new scoreTablem());
		T_score.getColumnModel().getColumn(9).setMinWidth(55);
		T_score.getColumnModel().getColumn(11).setMinWidth(37);
		
		JScrollPane SP_teamS = new JScrollPane();
		tabbedPane_1.addTab("Team", null, SP_teamS, null);
		
		T_Tscore = new JTable();
		T_Tscore.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		SP_teamS.setViewportView(T_Tscore);
		T_Tscore.setModel(tsm = new scoreTTablem());
		
		final JButton B_SearchMS = new JButton("Search Matches");
		B_SearchMS.setEnabled(false);
		B_SearchMS.setBounds(229, 24, 120, 23);
		P_score.add(B_SearchMS);
		
		final JButton B_addTS = new JButton("Add to search ");
		B_addTS.setBounds(466, 50, 103, 23);
		P_score.add(B_addTS);
		
		TF_teamS = new JTextField();
		TF_teamS.setColumns(10);
		TF_teamS.setBounds(456, 25, 86, 20);
		P_score.add(TF_teamS);
		
		JLabel lblTeams = new JLabel("Teams");
		lblTeams.setBounds(456, 0, 103, 14);
		P_score.add(lblTeams);
		
		final JButton B_resetTS = new JButton("Reset Search");
		B_resetTS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				teams="";
				B_resetTS.setEnabled(false);
				output.append("[SYS]:Loaded teams reset");
			}
		});
		B_resetTS.setEnabled(false);
		B_resetTS.setBounds(569, 50, 103, 23);
		P_score.add(B_resetTS);
		
		final JButton B_searchTS = new JButton("Search Teams");
		B_searchTS.setEnabled(false);
		B_searchTS.setBounds(552, 24, 120, 23);
		P_score.add(B_searchTS);
		
		
		
		
		final JButton B_Rscore = new JButton("Red");
		B_Rscore.setBounds(357, 24, 89, 23);
		P_score.add(B_Rscore);
		
		final JButton B_Bscore = new JButton("Blue");
		B_Bscore.setBounds(359, 50, 89, 23);
		P_score.add(B_Bscore);
		
		JPanel P_Team = new JPanel();
		tabbedPane.addTab("Team Data", null, P_Team, null);
		P_Team.setLayout(null);
		
		TF_minS = new JTextField();
		TF_minS.setColumns(10);
		TF_minS.setBounds(10, 36, 41, 20);
		P_Team.add(TF_minS);
		
		JLabel label = new JLabel("Match Range");
		label.setBounds(10, 11, 63, 14);
		P_Team.add(label);
		
		TF_maxS = new JTextField();
		TF_maxS.setColumns(10);
		TF_maxS.setBounds(61, 36, 41, 20);
		P_Team.add(TF_maxS);
		
		JButton B_addRMT = new JButton("Add to search ");
		B_addRMT.setBounds(10, 61, 103, 23);
		P_Team.add(B_addRMT);
		
		JButton B_addMT = new JButton("Add to search ");
		B_addMT.setBounds(123, 61, 103, 23);
		P_Team.add(B_addMT);
		
		TF_addMT = new JTextField();
		TF_addMT.setColumns(10);
		TF_addMT.setBounds(123, 36, 86, 20);
		P_Team.add(TF_addMT);
		
		JLabel label_1 = new JLabel("Additional Matches");
		label_1.setBounds(123, 11, 103, 14);
		P_Team.add(label_1);
		
		final JButton B_resetMT = new JButton("Reset Search");
		B_resetMT.setEnabled(false);
		B_resetMT.setBounds(236, 61, 103, 23);
		P_Team.add(B_resetMT);
		
		final JButton B_searchMT = new JButton("Search Matches");
		B_searchMT.setEnabled(false);
		B_searchMT.setBounds(219, 35, 120, 23);
		P_Team.add(B_searchMT);
		
		TF_team = new JTextField();
		TF_team.setColumns(10);
		TF_team.setBounds(446, 36, 86, 20);
		P_Team.add(TF_team);
		
		JLabel lblTeam = new JLabel("Team");
		lblTeam.setBounds(446, 11, 103, 14);
		P_Team.add(lblTeam);
		
		final JButton B_searchTT = new JButton("Search Team");
		B_searchTT.setEnabled(false);
		B_searchTT.setBounds(542, 35, 120, 23);
		P_Team.add(B_searchTT);
		
		JScrollPane SP_team = new JScrollPane();
		SP_team.setBounds(10, 95, 652, 145);
		P_Team.add(SP_team);
		
		T_team = new JTable();
		SP_team.setViewportView(T_team);
		T_team.setModel(ttm = new teamTablem());
		T_team.getColumnModel().getColumn(0).setMaxWidth(57);
		T_team.getColumnModel().getColumn(1).setMaxWidth(51);
		
		//stuff that needs to be set after everything is initialized
		d.setDataV(output,T_match, T_matchd, tm, tdm,sm,tsm,T_score,T_Tscore,ttm);
		
		JPanel P_teamNotes = new JPanel();
		tabbedPane.addTab("Team Notes", null, P_teamNotes, null);
		P_teamNotes.setLayout(null);
		d.beginMatch();
		//various listeners
		//listeners for the match tab
		B_ReadMD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				d.setMatch(TF_matchNum.getText());
			}
		});
		//gets next match# on match data tab
		B_mNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int s=Integer.parseInt(TF_matchNum.getText())+1;
				TF_matchNum.setText(""+s);
				d.setMatch(TF_matchNum.getText());
			}
		});
		//gets previous match# on match data tab
		B_mPrev.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int s=Integer.parseInt(TF_matchNum.getText())-1;
				TF_matchNum.setText(""+s);
				d.setMatch(TF_matchNum.getText());
			}
		});
		
		//score tab
		//Adds to the list of matches to search
		B_addRS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean continues=false;
				
				for(int i=Integer.parseInt(TF_minRS.getText());i<=Integer.parseInt(TF_maxRS.getText());i++){
					if(!d.checkDup(matches, ""+i)){
						continues=true;
					}
				}
				if(continues){
					if(!matches.equals("")){
						matches+=",";
					}
					for(int i=Integer.parseInt(TF_minRS.getText());i<=Integer.parseInt(TF_maxRS.getText());i++){
						System.out.println("setting matches");
						if(!d.checkDup(matches, ""+i)){
							matches+=i;
							if(i<Integer.parseInt(TF_maxRS.getText())){
								matches+=",";
							}
						}

					}
					System.out.println("Matches searched"+matches);
					if(matches.endsWith(",")){
						System.out.println("Matches searched:"+matches);
						matches=matches.substring(0, matches.length()-1);
						
					}
					B_SearchMS.setEnabled(true);
					B_searchMT.setEnabled(true);
					System.out.println("Matches searched"+matches);
				}
				TF_maxRS.setText("");
				TF_minRS.setText("");
			}
		});
		//same
		B_addS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!d.checkDup(matches, TF_S.getText())){
					if(!matches.equals("")){
						matches+=",";
						System.out.println("comma added");
					}
					matches+=TF_S.getText();
					B_SearchMS.setEnabled(true);
					B_searchMT.setEnabled(true);
				}
				TF_S.setText("");
			}
		});
		//displays all matches that needed to be searched
		B_SearchMS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				d.setMatches(matches);
				d.displayScore();
				d.displayRscore();
				//enable/disable some buttons to make flow easier
				B_Rscore.setEnabled(false);
				B_Bscore.setEnabled(true);
				B_resetMS.setEnabled(true);
				B_searchTS.setEnabled(true);
				B_searchTT.setEnabled(true);
				B_resetMT.setEnabled(true);
			}
		});
		//changes all the scores to red scores
		B_Rscore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				B_Rscore.setEnabled(false);
				B_Bscore.setEnabled(true);
				d.displayRscore();
			}
		});
		B_Bscore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				B_Bscore.setEnabled(false);
				B_Rscore.setEnabled(true);
				d.displayBscore();
			}
		});
		//resets the matches in memory
		B_resetMS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				matches="";
				d.resetMatches();
				B_searchTS.setEnabled(false);
				B_resetMS.setEnabled(false);
				B_SearchMS.setEnabled(false);
				B_resetMT.setEnabled(false);
				B_searchTT.setEnabled(false);
			}
		});
		//adds a team to search
		B_addTS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!d.checkDup(teams, TF_teamS.getText())){
					if(!teams.equals("")){
						teams+=",";
					}
					teams+=TF_teamS.getText();
				}
				TF_teamS.setText("");
			}
		});
		//searches for teams
		B_searchTS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				d.setTeams(teams);
				d.displayTscore();
				B_resetTS.setEnabled(true);
			}
		});
		
		//teams tab
		//searches\ range of matches
		B_addRMT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean continues=false;
				for(int i=Integer.parseInt(TF_minS.getText());i<=Integer.parseInt(TF_maxS.getText());i++){
					if(!d.checkDup(matches, ""+i)){
						continues=true;
					}
				}
				if(continues){
					if(!matches.equals("")){
						matches+=",";
					}
					for(int i=Integer.parseInt(TF_minS.getText());i<=Integer.parseInt(TF_maxS.getText());i++){
						if(!d.checkDup(matches, ""+i)){
							matches+=i;
							if(i<Integer.parseInt(TF_maxS.getText())){
								matches+=",";
							}
						}

					}
					if(matches.endsWith(",")){
						System.out.println("Matches searched:"+matches);
						matches=matches.substring(0, matches.length()-1);
						
					}
					B_searchMT.setEnabled(true);
					B_SearchMS.setEnabled(true);
				}
				TF_maxS.setText("");
				TF_minS.setText("");
			}
		});
		//adds a match
		B_addMT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!d.checkDup(matches, TF_addMT.getText())){
					if(!matches.equals("")){
						matches+=",";
						System.out.println("comma added");
					}
					matches+=TF_addMT.getText();
					B_searchMT.setEnabled(true);
					B_SearchMS.setEnabled(true);
				}
				TF_addMT.setText("");
			}
		});
		//searches matches
		B_searchMT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				d.setMatches(matches);
				B_resetMT.setEnabled(true);
				B_searchTT.setEnabled(true);
				B_searchTS.setEnabled(true);
				B_resetMS.setEnabled(true);
			}
		});
		//resets the matches searched
		B_resetMT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				B_searchTS.setEnabled(false);
				B_resetMT.setEnabled(false);
				B_resetMS.setEnabled(false);
				B_searchTT.setEnabled(false);
				matches="";
				d.resetMatches();
			}
		});
		
		//sets teams
		
		B_searchTT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				d.searchTeam(TF_team.getText());
				d.searchCom();
				d.displayTeam();
			}
		});
		
		
		//to detect when the output changes and to autoscroll(likely the cause of thread locking issue)
		output.getDocument().addDocumentListener(new OutputDocListener(output,SB_outV));
		
	}
}
