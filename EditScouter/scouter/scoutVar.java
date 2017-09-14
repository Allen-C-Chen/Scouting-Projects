package scouter;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Vector;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;

import global.Components;
import global.Location;
import global.Logger;
import global.Vars;
import scouterEdit.ComType;
import scouterEdit.TeamField;
import scouterEdit.ScoreField;

public class scoutVar extends Vars implements Runnable{
	//variables for the game
	//teams
	private int red1;
	private int red2;
	private int red3;
	private int blue1;
	private int blue2;
	private int blue3;
	private Thread t;
	private boolean end=false;
	private int gameTime;
	private String state="PREP";

	//game variable
	//for different component types
	private Vector<String> teamNums=new Vector<String>();
	private ArrayList<Shortcut> shortcuts=new ArrayList<Shortcut>();
	private ArrayList<JTextField> textField=new ArrayList<JTextField>();
	private ArrayList<JButton> button=new ArrayList<JButton>();
	private ArrayList<JCheckBox> checkBox=new ArrayList<JCheckBox>();
	private ArrayList<JComboBox<String>> comboBox=new ArrayList<JComboBox<String>>();
	private ArrayList<Vector<String>> comboModels=new ArrayList<Vector<String>>();//the model used for the comboBoxes//used to store the models
	private ArrayList<JRadioButton> radioButton=new ArrayList<JRadioButton>();
	private HashMap<String,ButtonGroup> buttonGroups= new HashMap<String,ButtonGroup>();
	private ArrayList<JToggleButton> toggleButton=new ArrayList<JToggleButton>();
	private ArrayList<JTextArea> textArea=new ArrayList<JTextArea>();
	private ArrayList<JTextPane> textPane=new ArrayList<JTextPane>();
	private ArrayList<JSpinner> spinner=new ArrayList<JSpinner>();
	private ArrayList<JList<String>> list=new ArrayList<JList<String>>();
	private ArrayList<Vector<String>> listModels=new ArrayList<Vector<String>>();
	private ArrayList<JSlider> slider=new ArrayList<JSlider>();
	private ArrayList<JLabel> label= new ArrayList<JLabel>();
	private ArrayList<Logger> logs= new ArrayList<Logger>();//logs all actions done in game
	//other things
	private String sPrintOut="";//printout for the scouter
	private ArrayList<String> comState=new ArrayList<String>();//contains the state for the comments
	private ArrayList<String> comment= new ArrayList<String>();
	private String matchType="Qual";//determines if match is a qualifier, practice, semifinal, final etc. used in naming
	private int matchNum=0;
	private String directory;
	private final JFileChooser fc=new JFileChooser();

	//sets class, does nothing, 
	public scoutVar(){
		output.setWrapStyleWord(true);
	}
	//setters
	public void setRed1(int red1){
		this.red1=red1;
	}
	public void setRed2(int red2){
		this.red2=red2;
	}
	public void setRed3(int red3){
		this.red3=red3;
	}
	public void setBlue1(int blue1){
		this.blue1=blue1;
	}
	public void setBlue2(int blue2){
		this.blue2=blue2;
	}
	public void setBlue3(int blue3){
		this.blue3=blue3;
	}
	public void setGameTime(int gameTime){
		this.gameTime=gameTime;
	}
	public void setTeamNums(Vector<String> teamNums){
		this.teamNums=teamNums;
	}
	public void addShortcut(Shortcut shortcut){
		shortcuts.add(shortcut);
	}
	public void setShortcuts(ArrayList<Shortcut> shortcuts){
		this.shortcuts=shortcuts;
	}
	public void setMatchType(String matchType){
		this.matchType=matchType;
	}
	public void setMatchNum(int matchNum){
		this.matchNum=matchNum;
	}
	public void setDirectory(String directory){
		this.directory=directory;
	}
	public void setState(String state){
		this.state=state;
	}
	//getters
	public Components getGameComponent(String name){
		for(int i=0;i<gameComponents.size();i++){
			if(gameComponents.get(i).getName().equals(name)){
				return gameComponents.get(i);
			}
		}
		return null;
	}
	public ScoreField getScoreField(String name){
		for(int i=0;i<scoreFields.size();i++){
			if(scoreFields.get(i).getName().equals(name)){
				return scoreFields.get(i);
			}
		}
		return null;
	}
	public int getRed1(){
		return red1;
	}
	public int getRed2(){
		return red2;
	}
	public int getRed3(){
		return red3;
	}
	public int getBlue1(){
		return blue1;
	}
	public int getBlue2(){
		return blue2;
	}
	public int getBlue3(){
		return blue3;
	}
	public int getGameTime(){
		return gameTime;
	}
	public Vector<String> getTeamNums(){
		return teamNums;
	}
	public ArrayList<Shortcut> getShortcuts(){
		return shortcuts;
	}
	public String getMatchType(){
		return matchType;
	}
	public int getMatchNum(){
		return matchNum;
	}
	public String getDirectory(){
		return directory;
	}
	//Everything to do with the ArrayLists holding the game components
	//adders
	public void addTextField(JTextField text){
		textField.add(text);
	}
	public void addButton(JButton button){
		this.button.add(button);
	}
	public void addCheckBox(JCheckBox check){
		checkBox.add(check);
	}
	public void addComboBox(JComboBox<String> combo){
		comboBox.add(combo);
	}
	public void addComboModel(Vector<String> model){
		comboModels.add(model);
	}
	public void addRadioButton(JRadioButton radio){
		radioButton.add(radio);
	}
	public void addButtonGroup(ButtonGroup group,String name){
		buttonGroups.put(name, group);
	}
	public void addToggleButton(JToggleButton toggle){
		toggleButton.add(toggle);
	}
	public void addTextArea(JTextArea area){
		textArea.add(area);
	}
	public void addTextPane(JTextPane pane){
		textPane.add(pane);
	}
	public void addSpinner(JSpinner spin){
		spinner.add(spin);
	}
	public void addList(JList<String> list){
		this.list.add(list);
	}
	public void addListModel(Vector<String> model){
		listModels.add(model);
	}
	public void addSlider(JSlider slider){
		this.slider.add(slider);
	}
	public void addLabel(JLabel label){
		this.label.add(label);
	}
	public void addLog(Logger log){
		logs.add(log);
	}
	public JTextField getTextField(String name){
		for(int i=0;i<textField.size();i++){
			if(textField.get(i).getName().equals(name)){
				return textField.get(i);
			}
		}
		return null;
	}
	public JButton getButton(String name){
		for(int i=0;i<button.size();i++){
			if(button.get(i).getName().equals(name)){
				return button.get(i);
			}
		}
		return null;
	}
	public JCheckBox getCheckBox(String name){
		for(int i=0;i<checkBox.size();i++){
			if(checkBox.get(i).getName().equals(name)){
				return checkBox.get(i);
			}
		}
		return null;
	}
	public JComboBox<String> getComboBox(String name){
		for(int i=0;i<comboBox.size();i++){
			if(comboBox.get(i).getName().equals(name)){
				return comboBox.get(i);
			}
		}
		return null;
	}
	public Vector<String> getComboModel(String name){
		int index=0;
		for(int i=0;i<comboBox.size();i++){
			if(comboBox.get(i).getName().equals(name)){
				index=i;
			}
		}
		return comboModels.get(index);
	}
	public JRadioButton getRadioButton(String name){
		for(int i=0;i<radioButton.size();i++){
			if(radioButton.get(i).getName().equals(name)){
				return radioButton.get(i);
			}
		}
		return null;
	}
	public ButtonGroup getButtonGroup(String name){
		return buttonGroups.get(name);
	}
	public JToggleButton getToggleButton(String name){
		for(int i=0;i<toggleButton.size();i++){
			if(toggleButton.get(i).getName().equals(name)){
				return toggleButton.get(i);
			}
		}
		return null;
	}
	public JTextArea getTextArea(String name){
		for(int i=0;i<textArea.size();i++){
			if(textArea.get(i).getName().equals(name)){
				return textArea.get(i);
			}
		}
		return null;
	}
	public JTextPane getTextPane(String name){
		for(int i=0;i<textPane.size();i++){
			if(textPane.get(i).getName().equals(name)){
				return textPane.get(i);
			}
		}
		return null;
	}
	public JSpinner getSpinner(String name){
		for(int i=0;i<spinner.size();i++){
			if(spinner.get(i).getName().equals(name)){
				return spinner.get(i);
			}
		}
		return null;
	}
	public JList<String> getList(String name){
		for(int i=0;i<list.size();i++){
			if(list.get(i).getName().equals(name)){
				return list.get(i);
			}
		}
		return null;
	}
	public Vector<String> getListModel(String name){
		int index=0;
		for(int i=0;i<list.size();i++){
			if(list.get(i).getName().equals(name)){
				index=i;
			}
		}
		return listModels.get(index);
	}
	public JSlider getSlider(String name){
		for(int i=0;i<slider.size();i++){
			if(slider.get(i).getName().equals(name)){
				return slider.get(i);
			}
		}
		return null;
	}
	public JLabel getLabel(String name){
		for(int i=0;i<label.size();i++){
			if(label.get(i).getName().equals(name)){
				return label.get(i);
			}
		}
		return null;
	}
	public ArrayList<Logger> getLogs(){
		return logs;
	}
	public void addComment(String comment){//method used to add stuff to output(non system messages) that are saved
		if(!comment.equals("")){
			this.comment.add(comment);
			comState.add(getCurrentState());
			output.append("["+state+"]:"+comment+"\n");
		}
	}
	
	public synchronized String getCurrentState(){
		return state;
	}
	public String debugPrep(){
		String s="Red 1:"+red1+
				"\nRed 2:"+red2+
				"\nRed 3:"+red3+
				"\nBlue 1:"+blue1+
				"\nBlue 2:"+blue2+
				"\nBlue 3:"+blue3+
				"\nMatch Number:"+matchNum+
				"\nGame Time:"+gameTime+
				"\nMatch Type:"+matchType;
		return s;
	}
	public String debugScore(){
		String s="Red Scores";
		s+="Total:"+scoreFields.get(0).getRValue()+"\n";
		for(int i=1;i<scoreFields.size();i++){
			s+=scoreFields.get(i).getName()+":"+scoreFields.get(i).getRValue()+"\n";
		}
		s+="Blue Scores";
		s+="Total:"+scoreFields.get(0).getBValue()+"\n";
		for(int i=1;i<scoreFields.size();i++){
			s+=scoreFields.get(i).getName()+":"+scoreFields.get(i).getBValue()+"\n";
		}
		return s;
	}
	public String debugComments(){
		String s="";
		for(int i=0;i<comment.size();i++){
			s+=comState.get(i)+":"+comment.get(i)+"\n";
		}
		return s;
	}
	//used to reset the data in the match for a new match
	public void resetGameData(){
		red1=0;
		red2=0;
		red3=0;
		blue1=0;
		blue2=0;
		blue3=0;
		matchNum=0;
		comState.clear();
		comment.clear();
		output.setText("");
		end=true;
	}
	//saves matches
	public void saveMatch(boolean foul){
		sPrintOut=red1+":"+red2+":"+red3+":"+blue1+":"+blue2+":"+blue3+":"+matchType+matchNum+"::";
		for(int i=0;i<scoreFields.size();i++){
			sPrintOut+=scoreFields.get(i).getRValue()+":";
		}
		sPrintOut+=":";//to get double :: between score values
		for(int i=0;i<scoreFields.size();i++){
			sPrintOut+=scoreFields.get(i).getBValue()+":";
		}
		sPrintOut+=":";
		for(int i=0;i<comment.size();i++){
			sPrintOut+=comState.get(i)+":"+comment.get(i)+"::";
		}
		sPrintOut=sPrintOut.substring(0, sPrintOut.length()-2);
		fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
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
		int returnVal= fc.showSaveDialog(fc);
		if(returnVal==JFileChooser.APPROVE_OPTION){
			File file;
			if(foul){
				file=new File(fc.getCurrentDirectory(), matchType+matchNum+"(foul)");
			}
			else{
				file=new File(fc.getCurrentDirectory(), matchType+matchNum);
			}
			savePrintOut(file);

		}


	}
	public void savePrintOut(File file){
		try{
			BufferedWriter out = new BufferedWriter(new FileWriter(file));
			out.write(sPrintOut);
			out.close();
		}
		catch(IOException e){
			System.out.println("Write in file error");
		}
	}
	//setting scores used by score panel
	public void setRScore(int index, int score){
		scoreFields.get(index).setRValue(score);
	}
	public void setBScore(int index,int score){
		scoreFields.get(index).setBValue(score);
	}
	//sets the ArrayList used by the combobox for the teams
	public void setTeamComboBox(){
		teamNums.clear();
		teamNums.add(""+red1);
		teamNums.add(""+red2);
		teamNums.add(""+red3);
		teamNums.add(""+blue1);
		teamNums.add(""+blue2);
		teamNums.add(""+blue3);
		teamNums.add("Red Alliance");
		teamNums.add("Blue Alliance");
	}
	public void setTeamSelect(final JComboBox<String> select){
		select.setModel(new DefaultComboBoxModel<String>(teamNums));
		select.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(select.getSelectedIndex()>=0&&select.getSelectedIndex()<3||select.getSelectedIndex()==6){
					select.setRenderer(new DefaultListCellRenderer(){
						private static final long serialVersionUID = 7160090250079376197L;

						public void paint(Graphics g){
							setBackground(Color.RED);
							setForeground(Color.WHITE);
							super.paint(g);
						}
					});
				}
				else if(select.getSelectedIndex()>2&&select.getSelectedIndex()<6||select.getSelectedIndex()==7){
					select.setRenderer(new DefaultListCellRenderer(){
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
		teamNums.add("");
	}
	public void decode(String data){
		String[] values=data.split("!");
		decodeGame(values[0].split("::"));//splits the data into the components
		decodeWindow(values[1].split(":"));
		decodeTeam(values[2].split("::"));
		decodeScore(values[3].split("::"));
		ArrayList<ScoreField> temp=new ArrayList<ScoreField>();
		for(ScoreField s:scoreFields){
			temp.add(s);
		}
		//specialized bit added after typicall decode from Vars to make space for the total score variable which is always included
		scoreFields.clear();
		scoreFields.add(new ScoreField("Total"));
		scoreFields.get(0).setTooltip("Total score of the alliance");
		for(ScoreField s:temp){
			scoreFields.add(s);
		}
	}
	public void startGame(){
			t=new Thread(this);
			t.start();
			output.append("[SYS]:Game started");
			end=false;
		
	}
	//starts timer(only thing thread does)
	public synchronized void startTimer(){
		for(int time=0;time<gameTime;time++){
			state=(int)Math.floor(time/60)+"."+time%60;
			getTextField("Timer").setText(state);
			try{
				this.wait(1000);
			}
			catch(Exception e){
			}
			//kills thread by allowing it to end if the case is that the game is reset and the timer was still running
			if (end==true){
				break;
			}
		}
		if(!end){
			state="end";
		}
		getTextField("Timer").setText("0");
	}
	//thread start
	public void run(){
		startTimer();
	}
	
}