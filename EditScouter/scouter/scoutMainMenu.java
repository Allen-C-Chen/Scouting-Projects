package scouter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.ButtonGroup;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileFilter;

import data.dataMain;
import global.HelpFrame;

public class scoutMainMenu extends JMenuBar {
	/**
	 * 
	 */
	private static final long serialVersionUID = -259968698314335676L;
	private final JFileChooser fc=new JFileChooser();
	private scoutVar var;
	private scoutMain main;

	public scoutMainMenu(final scoutVar var,final scoutMain main){
		this.var=var;
		this.main=main;
		//setting the file chooser
		fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fc.setFileFilter(new FileFilter()
		{
			@Override
			public boolean accept(File file)
			{
				return file.getName().toUpperCase().endsWith(".TXT");
			}

			@Override
			public String getDescription()
			{
				return ".txt files";
			}
		});
		
		JMenu M_File= new JMenu("File");
		add(M_File);
		
		final JMenuItem MI_Open= new JMenuItem("Open");
		M_File.add(MI_Open);
		
		JMenuItem MI_Reset= new JMenuItem("Reset");
		M_File.add(MI_Reset);
		
		JMenuItem MI_Save= new JMenuItem("Save");
		M_File.add(MI_Save);
		
		JMenuItem MI_Data= new JMenuItem("Open Data");
		M_File.add(MI_Data);
		
		JMenu M_Match= new JMenu("Match");
		add(M_Match);
		
		JRadioButtonMenuItem MRBI_Qual= new JRadioButtonMenuItem("Qualifiers");
		M_Match.add(MRBI_Qual);
		MRBI_Qual.setSelected(true);
		
		JRadioButtonMenuItem MRBI_Quarter= new JRadioButtonMenuItem("Quarter Finals");
		M_Match.add(MRBI_Quarter);
		
		JRadioButtonMenuItem MRBI_Semi= new JRadioButtonMenuItem("Semi Finals");
		M_Match.add(MRBI_Semi);
		
		JRadioButtonMenuItem MRBI_Final= new JRadioButtonMenuItem("Finals");
		M_Match.add(MRBI_Final);
		
		M_Match.addSeparator();
		
		JMenuItem MI_Foul= new JMenuItem("Foul Game");
		M_Match.add(MI_Foul);
		
		JMenuItem MI_TPresets= new JMenuItem("Team Presets");
		M_Match.add(MI_TPresets);
		
		JMenuItem MI_CGTime= new JMenuItem("Change Game Time");
		M_Match.add(MI_CGTime);
		
		JMenuItem MI_Start=new JMenuItem("Start Game");
		MI_Start.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G,ActionEvent.CTRL_MASK));
		M_Match.add(MI_Start);
		
		JMenuItem MI_Start2=new JMenuItem("Start Game");
		MI_Start2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G,ActionEvent.META_MASK));
		M_Match.add(MI_Start2);
		
		JMenu M_Debug= new JMenu("Debug");
		add(M_Debug);
		
		JMenuItem MI_Prep= new JMenuItem("Prep");
		M_Debug.add(MI_Prep);
		
		JMenuItem MI_GameLogs= new JMenuItem("Game Logs");
		M_Debug.add(MI_GameLogs);
		
		JMenuItem MI_Score= new JMenuItem("Score");
		M_Debug.add(MI_Score);
		
		JMenuItem MI_Comments= new JMenuItem("Comments");
		M_Debug.add(MI_Comments);
		
		JMenu M_Help=new JMenu("Help");
		add(M_Help);
		
		JMenuItem MI_Help=new JMenuItem("Help Index");
		M_Help.add(MI_Help);
		
		ButtonGroup matchType= new ButtonGroup();
		matchType.add(MRBI_Qual);
		matchType.add(MRBI_Quarter);
		matchType.add(MRBI_Semi);
		matchType.add(MRBI_Final);
		
		MRBI_Qual.setActionCommand("Qual");
		MRBI_Quarter.setActionCommand("Quarter");
		MRBI_Semi.setActionCommand("Semi");
		MRBI_Final.setActionCommand("Final");
		
		
		//Action listeners after everything has been initialized
		
		ActionListener listen=new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(e.getActionCommand().equals("Quarter")){
					var.setMatchType("Quarter");
				}
				else if(e.getActionCommand().equals("Semi")){
					var.setMatchType("Semi");
				}
				else if(e.getActionCommand().equals("Final")){
					var.setMatchType("Final");
				}
				else{//qualifiers as default as it is the most likely one to be recorded.
					var.setMatchType("Qual");
				}
				
			}
		};
		
		
		MRBI_Qual.addActionListener(listen);
		MRBI_Quarter.addActionListener(listen);
		MRBI_Semi.addActionListener(listen);
		MRBI_Final.addActionListener(listen);
		
		MI_Open.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				int returnVal= fc.showOpenDialog(MI_Open);
				if(returnVal==JFileChooser.APPROVE_OPTION){//if approved
					File file=fc.getSelectedFile();
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
					if(!fileData.equals("")){//a simple check to see if a text file has been read
						main.newConfig(fileData);
					}
				}
			}
		});
		
		MI_Reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				main.reset();
			}
		});
		MI_Save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				var.saveMatch(false);
			}
		});
		MI_Data.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				dataMain data= new dataMain(main.getConfig());
				data.setVisible(true);
			}
		});
		
		MI_Foul.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				main.resetFoul();
			}
		});
		MI_TPresets.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				int returnVal= fc.showOpenDialog(MI_Open);
				if(returnVal==JFileChooser.APPROVE_OPTION){//if approved
					File file=fc.getSelectedFile();
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
					if(!fileData.equals("")){//a simple check to see if a text file has been read
						main.addpresetTeams(fileData);
					}
				}
			}
		});
		MI_CGTime.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				main.changeDefaultGameTime();
			}
		});
		MI_Start.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				main.startGame();
			}
		});
		MI_Start2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				main.startGame();
			}
		});
		MI_Prep.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				main.getDebugFrame().addData("Preparation Data", var.debugPrep());				
			}
			
		});
		MI_GameLogs.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				for(int i=0;i<var.getLogs().size();i++){
					main.getDebugFrame().addData(var.getLogs().get(i).getName(), var.getLogs().get(i).getLog());
				}
			}
		});
		MI_Score.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				main.getDebugFrame().addData("Scores", var.debugScore());
			}
			
		});
		MI_Comments.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				main.getDebugFrame().addData("Comments", var.debugComments());
			}
		});
		MI_Help.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				HelpFrame help=new HelpFrame("");
				help.setVisible(true);
			}
		});
	}
	
	public JMenuBar getM_Scout(){
		return this;
	}

}
