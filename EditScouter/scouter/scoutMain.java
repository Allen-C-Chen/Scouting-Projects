package scouter;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import global.Debug_Vars;

import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JMenuBar;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.Dimension;

public class scoutMain extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6816940896247221899L;
	private String name;//name of scouter
	private JPanel contentPane;
	private scoutMainMenu menu;
	private scoutVar var=new scoutVar();
	private String configuration;
	private Debug_Vars debugging;
	
	private scoutPrep prep;
	private scoutGame game;
	private scoutScore score;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					scoutMain frame = new scoutMain();
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
	
	public scoutMain() {
		//one without for testing to see how to layout the design
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		Component VG_L = Box.createVerticalGlue();
		GridBagConstraints gbc_VG_L = new GridBagConstraints();
		gbc_VG_L.insets = new Insets(0, 0, 5, 5);
		gbc_VG_L.gridx = 0;
		gbc_VG_L.gridy = 0;
		contentPane.add(VG_L, gbc_VG_L);
		
		JTabbedPane TP_Main = new JTabbedPane(JTabbedPane.TOP);
		GridBagConstraints gbc_TP_Main = new GridBagConstraints();
		gbc_TP_Main.weighty = 10.0;
		gbc_TP_Main.insets = new Insets(0, 0, 5, 5);
		gbc_TP_Main.fill = GridBagConstraints.BOTH;
		gbc_TP_Main.gridx = 2;
		gbc_TP_Main.gridy = 0;
		contentPane.add(TP_Main, gbc_TP_Main);
		
		JPanel panel = new JPanel();
		TP_Main.addTab("New tab", null, panel, null);
		
		JPanel panel_1 = new JPanel();
		TP_Main.addTab("Test", null, panel_1, null);
		
		Component VG_R = Box.createVerticalGlue();
		GridBagConstraints gbc_VG_R = new GridBagConstraints();
		gbc_VG_R.insets = new Insets(0, 0, 5, 5);
		gbc_VG_R.gridx = 3;
		gbc_VG_R.gridy = 0;
		contentPane.add(VG_R, gbc_VG_R);
		
		Component VS_L = Box.createVerticalStrut(40);
		GridBagConstraints gbc_VS_L = new GridBagConstraints();
		gbc_VS_L.insets = new Insets(0, 0, 0, 5);
		gbc_VS_L.gridx = 1;
		gbc_VS_L.gridy = 1;
		contentPane.add(VS_L, gbc_VS_L);
		
		JScrollPane Sp_Output = new JScrollPane();
		Sp_Output.setMaximumSize(new Dimension(32767, 30));
		GridBagConstraints gbc_Sp_Output = new GridBagConstraints();
		gbc_Sp_Output.insets = new Insets(0, 0, 0, 5);
		gbc_Sp_Output.weighty = 0.1;
		gbc_Sp_Output.fill = GridBagConstraints.BOTH;
		gbc_Sp_Output.gridx = 2;
		gbc_Sp_Output.gridy = 1;
		contentPane.add(Sp_Output, gbc_Sp_Output);
		
		JTextArea TA_Output = new JTextArea();
		Sp_Output.setViewportView(TA_Output);
		
		Component VS_R = Box.createVerticalStrut(40);
		GridBagConstraints gbc_VS_R = new GridBagConstraints();
		gbc_VS_R.gridx = 4;
		gbc_VS_R.gridy = 1;
		contentPane.add(VS_R, gbc_VS_R);
	}
	public scoutMain(String data) {//one that is launched when it is actually done
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				System.out.println("Size"+getSize().getWidth()+"/"+getSize().getHeight());
			}
		});
		
		configuration=data;
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		var.decode(data);//load data
		menu=new scoutMainMenu(var,this);
		setJMenuBar(menu);
		
		game=new scoutGame(var);
		prep=new scoutPrep(var);
		score=new scoutScore(var);
		setBounds(var.getWinLocation().getX(), var.getWinLocation().getY(),var.getWinLocation().getW(), var.getWinLocation().getH());
		setTitle(var.getWinTitle());
		
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		if(var.getBooleanOutput()){
			JScrollPane Sp_Output = new JScrollPane();
			Sp_Output.setMaximumSize(new Dimension(32767, var.getOutH()));
			Sp_Output.setMinimumSize(new Dimension(0,var.getOutH()));
			GridBagConstraints gbc_Sp_Output = new GridBagConstraints();
			gbc_Sp_Output.insets = new Insets(0, 0, 0, 5);
			gbc_Sp_Output.weighty = 0.1;
			gbc_Sp_Output.fill = GridBagConstraints.BOTH;
			gbc_Sp_Output.gridx = 2;
			gbc_Sp_Output.gridy = 1;
			contentPane.add(Sp_Output, gbc_Sp_Output);
			
			JTextArea TA_Output = var.getOutput();
			Sp_Output.setViewportView(TA_Output);
			
			Component VS_L = Box.createVerticalStrut(var.getOutH());
			GridBagConstraints gbc_VS_L = new GridBagConstraints();
			gbc_VS_L.weighty=0.1;
			gbc_VS_L.insets = new Insets(0, 0, 0, 5);
			gbc_VS_L.gridx = 1;
			gbc_VS_L.gridy = 1;
			contentPane.add(VS_L, gbc_VS_L);
			
			Component VS_R = Box.createVerticalStrut(var.getOutH());
			GridBagConstraints gbc_VS_R = new GridBagConstraints();
			gbc_VS_R.weighty=0.1;
			gbc_VS_R.gridx = 4;
			gbc_VS_R.gridy = 1;
			contentPane.add(VS_R, gbc_VS_R);
		}
		
		
		
		Component VG_L = Box.createVerticalGlue();
		GridBagConstraints gbc_VG_L = new GridBagConstraints();
		gbc_VG_L.weighty = 10.0;
		gbc_VG_L.insets = new Insets(0, 0, 5, 5);
		gbc_VG_L.gridx = 0;
		gbc_VG_L.gridy = 0;
		contentPane.add(VG_L, gbc_VG_L);
		
		JTabbedPane TP_Main = new JTabbedPane(JTabbedPane.TOP);
		GridBagConstraints gbc_TP_Main = new GridBagConstraints();
		gbc_TP_Main.weighty = 10.0;
		gbc_TP_Main.insets = new Insets(0, 0, 5, 5);
		gbc_TP_Main.fill = GridBagConstraints.BOTH;
		gbc_TP_Main.gridx = 2;
		gbc_TP_Main.gridy = 0;
		contentPane.add(TP_Main, gbc_TP_Main);
		
		Component VG_R = Box.createVerticalGlue();
		GridBagConstraints gbc_VG_R = new GridBagConstraints();
		gbc_VG_R.weighty = 10.0;
		gbc_VG_R.insets = new Insets(0, 0, 5, 5);
		gbc_VG_R.gridx = 3;
		gbc_VG_R.gridy = 0;
		contentPane.add(VG_R, gbc_VG_R);
		
		
		
		JPanel P_prep = prep.getP_Prep();
		TP_Main.addTab("Preparation", null, P_prep, null);
		
		JPanel P_Game= game.getP_Game();
		TP_Main.addTab("Game", null, P_Game,null);
		
		JPanel P_Score=score.getP_Score();
		TP_Main.addTab("Scoring", null, P_Score, null);
		
	}
	public void setScouter(String name){
		this.name=name;
		var.addComment("Scouted by:"+name);
	}
	public void addpresetTeams(String presetTeams){
		String[] teams=presetTeams.split(":");
		for(String team:teams){
			String[] teamNums=team.split(",");
			int[] teamNum=new int[6];
			for(int i=0;i<6;i++){
				try{
					teamNum[i]=Integer.parseInt(teamNums[i]);
				}catch(NumberFormatException e){
					teamNum[i]=0;
				}
			}
			prep.addPresetTeam(teamNum);
		}
	}
	public void changeDefaultGameTime(){
		prep.changeDefaultGameTime();
	}
	public void startGame(){
		game.startGame();
	}
	public String getConfig(){
		return configuration;
	}
	public Debug_Vars getDebugFrame(){
		if(debugging==null){
			debugging=new Debug_Vars();
			debugging.setVisible(true);	
		}
		else if(!debugging.isShowing()){
			debugging=new Debug_Vars();
			debugging.setVisible(true);
		}
		return debugging;
	}
	public void reset(){
		prep.reset();
		game.reset();
		score.reset();
		var.resetGameData();
		var.setState("PREP");
		var.addComment("Scouted by:"+name);
	}
	public void resetFoul(){
		var.saveMatch(true);
		game.reset();
		score.reset();
		var.resetGameData();
		var.setState("PREP");
		var.addComment("Scouted by:"+name);
	}
	public void newConfig(String config){
		scoutMain main=new scoutMain(config);
		main.setVisible(true);
		this.dispose();
	}
	

}
