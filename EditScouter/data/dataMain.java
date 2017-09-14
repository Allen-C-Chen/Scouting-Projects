package data;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JTabbedPane;
import java.awt.GridBagConstraints;
import javax.swing.JTextArea;
import java.awt.Insets;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JScrollPane;

public class dataMain extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8116051769039776435L;
	
	private JPanel contentPane;
	private dataVar var;
	private dataMatch match;
	private dataSearch search;
	private dataScore score;
	private dataTeamScore teamScore;
	private dataTeamMatch teamMatch;
	private dataTeam team;
	private dataMainMenu menu;
	private String configuration;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					dataMain frame = new dataMain("null!null:0:false:0:0:0:0!null!null");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param configuration String of text containing the properties of the scouter
	 */
	public dataMain(String configuration) {
		this.configuration=configuration;
		var=new dataVar();
		var.decode(configuration);
		match=new dataMatch(var);
		search=new dataSearch(var);
		score= new dataScore(var);
		teamScore= new dataTeamScore(var);
		teamMatch= new dataTeamMatch(var);
		team= new dataTeam(var);
		menu= new dataMainMenu(this,var);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 672, 447);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		
		contentPane.setLayout(gbl_contentPane);
		
		JTabbedPane TP_Main = new JTabbedPane(JTabbedPane.TOP);
		GridBagConstraints gbc_TP_Main = new GridBagConstraints();
		gbc_TP_Main.weightx = 1.0;
		gbc_TP_Main.weighty = 10.0;
		gbc_TP_Main.insets = new Insets(0, 0, 5, 5);
		gbc_TP_Main.fill = GridBagConstraints.BOTH;
		gbc_TP_Main.gridx = 1;
		gbc_TP_Main.gridy = 0;
		contentPane.add(TP_Main, gbc_TP_Main);
		
		Component VG_R = Box.createVerticalGlue();
		GridBagConstraints gbc_VG_R = new GridBagConstraints();
		gbc_VG_R.fill = GridBagConstraints.VERTICAL;
		gbc_VG_R.insets = new Insets(0, 0, 5, 5);
		gbc_VG_R.gridx = 2;
		gbc_VG_R.gridy = 0;
		contentPane.add(VG_R, gbc_VG_R);
		
		Component VG_L = Box.createVerticalGlue();
		GridBagConstraints gbc_VG_L = new GridBagConstraints();
		gbc_VG_L.insets = new Insets(0, 0, 5, 5);
		gbc_VG_L.gridx = 0;
		gbc_VG_L.gridy = 0;
		contentPane.add(VG_L, gbc_VG_L);
		
		Component VS_R = Box.createVerticalStrut(40);
		GridBagConstraints gbc_VS_R = new GridBagConstraints();
		gbc_VS_R.insets = new Insets(0, 0, 0, 5);
		gbc_VS_R.gridx = 0;
		gbc_VS_R.gridy = 1;
		contentPane.add(VS_R, gbc_VS_R);
		
		JScrollPane SP_Output = new JScrollPane();
		GridBagConstraints gbc_SP_Output = new GridBagConstraints();
		gbc_SP_Output.fill = GridBagConstraints.BOTH;
		gbc_SP_Output.insets = new Insets(0, 0, 0, 5);
		gbc_SP_Output.gridx = 1;
		gbc_SP_Output.gridy = 1;
		contentPane.add(SP_Output, gbc_SP_Output);
		
		JTextArea TA_Output = var.getOutput();
		SP_Output.setViewportView(TA_Output);
		
		JPanel P_Match = match.getP_Match();
		TP_Main.addTab("Match", null, P_Match, null);
		
		JPanel P_Search= search.getP_Search();
		TP_Main.addTab("Search", null, P_Search,null);
		
		JPanel P_Score=score.getP_Score();
		TP_Main.addTab("Scores",null, P_Score,null);
		
		JPanel P_TeamScore=teamScore.getP_TeamScore();
		TP_Main.addTab("Team Scores",null, P_TeamScore,null);

		JPanel P_TeamMatch=teamMatch.getP_TeamMatch();
		TP_Main.addTab("Team Matches",null, P_TeamMatch,null);
		
		JPanel P_Team=team.getP_Team();
		TP_Main.addTab("Team",null, P_Team,null);
		
		Component VS_L = Box.createVerticalStrut(40);
		GridBagConstraints gbc_VS_L = new GridBagConstraints();
		gbc_VS_L.gridx = 3;
		gbc_VS_L.gridy = 1;
		contentPane.add(VS_L, gbc_VS_L);
		
		this.setJMenuBar(menu.getMenuBar());
		
	}
	public String getConfig(){
		return configuration;
	}
	public void reset(){
		match.reset();
		search.reset();
		score.reset();
		teamScore.reset();
		teamMatch.reset();
		team.reset();
		var.setDirectory("");
	}
	public void newConfig(String config){
		dataMain main= new dataMain(config);
		main.setVisible(true);
		this.dispose();
	}
}
