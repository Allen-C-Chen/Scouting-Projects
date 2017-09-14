package scouter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.AbstractListModel;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JToggleButton;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import global.Components;
import global.Logger;
import scouterEdit.ComType;
import scouterEdit.ScoreField;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class scoutGame extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5487579306452621840L;
	private scoutVar var;
	private Logger log;
	/**
	 * Create the panel.
	 */
	public scoutGame(scoutVar var) {
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				System.out.println("Size Game"+getSize().getWidth()+"/"+getSize().getHeight());
			}
		});
		this.var=var;
		setLayout(null);
		
		ArrayList<Components> game= var.getGameComponents();
		for(int i=0;i<game.size();i++){//for loop setting each of the components calling its create method
			if(game.get(i).getType()==ComType.TextField){
				createTextField(game.get(i));
			}
			else if(game.get(i).getType()==ComType.Button){
				createButton(game.get(i));
			}
			else if(game.get(i).getType()==ComType.CheckBox){
				createCheckBox(game.get(i));
			}
			else if(game.get(i).getType()==ComType.ComboBox){
				createComboBox(game.get(i));
			}
			else if(game.get(i).getType()==ComType.RadioButton){
				createRadioButton(game.get(i));
			}
			else if(game.get(i).getType()==ComType.ToggleButton){
				createToggleButton(game.get(i));
			}
			else if(game.get(i).getType()==ComType.TextArea){
				createTextArea(game.get(i));
			}
			else if(game.get(i).getType()==ComType.TextPane){
				createTextPane(game.get(i));
			}
			else if(game.get(i).getType()==ComType.Spinner){
				createSpinner(game.get(i));
			}
			else if(game.get(i).getType()==ComType.List){
				createList(game.get(i));
			}
			else if(game.get(i).getType()==ComType.Slider){
				createSlider(game.get(i));
			}
			else if(game.get(i).getType()==ComType.Label){
				createLabel(game.get(i));
			}
		}
		setActions();//sets what's found in the action part of things in all the components, after everything is created due to cross component interactions
		reset();

	}
	//methods used to create the separate components
	private JTextField createTextField(Components com){
		JTextField text=new JTextField();
		text.setBounds(com.getLocation().getX(), com.getLocation().getY(), com.getLocation().getW(), com.getLocation().getH());
		text.setToolTipText(com.getTooltip());
		text.setName(com.getName());
		add(text);
		var.addTextField(text);
		return text;
	}
	private JButton createButton(Components com){
		JButton button=new JButton(com.getName());
		button.setBounds(com.getLocation().getX(), com.getLocation().getY(), com.getLocation().getW(), com.getLocation().getH());
		button.setToolTipText(com.getTooltip());
		button.setName(com.getName());
		add(button);
		var.addButton(button);
		return button;
	}
	private JCheckBox createCheckBox(Components com){
		JCheckBox check=new JCheckBox(com.getName());
		check.setBounds(com.getLocation().getX(), com.getLocation().getY(), com.getLocation().getW(), com.getLocation().getH());
		check.setToolTipText(com.getTooltip());
		check.setName(com.getName());
		add(check);
		var.addCheckBox(check);
		return check;
	}
	private JComboBox<String> createComboBox(Components com){
		JComboBox<String> combo= new JComboBox<String>();
		combo.setBounds(com.getLocation().getX(), com.getLocation().getY(), com.getLocation().getW(), com.getLocation().getH());
		combo.setToolTipText(com.getTooltip());
		combo.setName(com.getName());
		add(combo);
		var.addComboModel(new Vector<String>());//adds a place holder modelArrayList that contains nothing//set in setActions
		var.addComboBox(combo);
		return combo;
	}
	private JRadioButton createRadioButton(Components com){
		JRadioButton radio= new JRadioButton(com.getName());
		radio.setBounds(com.getLocation().getX(), com.getLocation().getY(), com.getLocation().getW(), com.getLocation().getH());
		radio.setToolTipText(com.getTooltip());
		radio.setName(com.getName());
		add(radio);
		if (!com.getGrouping().equals("null")) {
			if (var.getButtonGroup(com.getGrouping()) != null) {
				var.getButtonGroup(com.getGrouping()).add(radio);
			} else {
				var.addButtonGroup(new ButtonGroup(), com.getGrouping());
				var.getButtonGroup(com.getGrouping()).add(radio);
			} 
		}
		var.addRadioButton(radio);
		return radio;
	}
	private JToggleButton createToggleButton(Components com){
		JToggleButton toggle= new JToggleButton(com.getName());
		toggle.setBounds(com.getLocation().getX(), com.getLocation().getY(), com.getLocation().getW(), com.getLocation().getH());
		toggle.setToolTipText(com.getTooltip());
		toggle.setName(com.getName());
		add(toggle);
		if (!com.getGrouping().equals("null")) {
			if (var.getButtonGroup(com.getGrouping()) != null) {
				var.getButtonGroup(com.getGrouping()).add(toggle);
			} else {
				var.addButtonGroup(new ButtonGroup(), com.getGrouping());
				var.getButtonGroup(com.getGrouping()).add(toggle);
			} 
		}
		var.addToggleButton(toggle);
		return toggle;
	}
	private JTextArea createTextArea(Components com){
		JTextArea area=new JTextArea();
		area.setBounds(com.getLocation().getX(), com.getLocation().getY(), com.getLocation().getW(), com.getLocation().getH());
		area.setToolTipText(com.getTooltip());
		area.setName(com.getName());
		add(area);
		var.addTextArea(area);
		return area;
	}
	private JTextPane createTextPane(Components com){
		JTextPane pane=new JTextPane();
		pane.setBounds(com.getLocation().getX(), com.getLocation().getY(), com.getLocation().getW(), com.getLocation().getH());
		pane.setToolTipText(com.getTooltip());
		pane.setName(com.getName());
		add(pane);
		var.addTextPane(pane);
		return pane;
	}
	private JSpinner createSpinner(final Components com){
		final JSpinner spin= new JSpinner();
		spin.setBounds(com.getLocation().getX(), com.getLocation().getY(), com.getLocation().getW(), com.getLocation().getH());
		spin.setToolTipText(com.getTooltip());
		spin.setName(com.getName());
		spin.addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent e){
				if(com.getMin()!=com.getMax()){
					if((int)spin.getValue()<com.getMin()){
						spin.setValue(com.getMin());
					}
					else if((int)spin.getValue()>com.getMax()){
						spin.setValue(com.getMax());
					}
				}
			}
		});
		add(spin);
		var.addSpinner(spin);
		return spin;
	}
	private JList<String> createList(Components com){
		JList<String> list=new JList<String>();
		list.setBounds(com.getLocation().getX(), com.getLocation().getY(), com.getLocation().getW(), com.getLocation().getH());
		list.setToolTipText(com.getTooltip());
		list.setName(com.getName());
		add(list);
		var.addListModel(new Vector<String>());//placeholder, so that things don't get messed up
		var.addList(list);
		return list;
	}
	private JSlider createSlider(Components com){
		JSlider slider=new JSlider();
		slider.setMaximum(com.getMax());
		slider.setMinimum(com.getMin());
		slider.setBounds(com.getLocation().getX(), com.getLocation().getY(), com.getLocation().getW(), com.getLocation().getH());
		slider.setToolTipText(com.getTooltip());
		slider.setName(com.getName());
		add(slider);
		var.addSlider(slider);
		return slider;
	}
	private JLabel createLabel(Components com){
		JLabel label= new JLabel(com.getName());
		label.setBounds(com.getLocation().getX(), com.getLocation().getY(), com.getLocation().getW(), com.getLocation().getH());
		label.setToolTipText(com.getTooltip());
		label.setName(com.getName());
		add(label);
		var.addLabel(label);
		return label;
	}
	//does everything in relation to the action part of the components class
	private void setActions(){
		ArrayList<Components> game=var.getGameComponents();
		for(int i=0;i<game.size();i++){
			if(!(game.get(i).getAction().equals(""))){//to see if there's an action set
				String action=game.get(i).getAction();
				try{
					if(action.indexOf("<model>")>-1){
						int[] models=getIndexesOf(action,"<model>");
						int[] modelEnd=getIndexesOf(action,"</model>");
						String model=action.substring(models[0]+7, modelEnd[0]);
						String[] modeler=model.split(":");
						final Vector<String> theModel=new Vector<String>();
						for(int c=0;c<modeler.length;c++){
							theModel.add(modeler[c]);
						}
						if(game.get(i).getType()==ComType.ComboBox){
							var.addComboModel(theModel);
							var.getComboBox(game.get(i).getName()).setModel(new DefaultComboBoxModel<String>(theModel));
							var.getComboModel(game.get(i).getName()).clear();
							var.getComboModel(game.get(i).getName()).addAll(theModel);
						}
						else if(game.get(i).getType()==ComType.List){
							var.addListModel(theModel);
							var.getList(game.get(i).getName()).setModel(new AbstractListModel<String>() {
								/**
								 * 
								 */
								private static final long serialVersionUID = 6849202298674475045L;
								public int getSize() {
									return theModel.size();
								}
								public String getElementAt(int index) {
									return theModel.get(index);
								}
							});
							var.getListModel(game.get(i).getName()).clear();
							var.getListModel(game.get(i).getName()).addAll(theModel);
							
						}
					}
				}
				catch(Exception e){}
				try{
					if(action.indexOf("<reset>")>-1){
						int[] resets=getIndexesOf(action,"<reset>");
						int[] resetEnd=getIndexesOf(action,"</reset>");
						String reset=action.substring(resets[0]+7, resetEnd[0]);
						String[] reseter=reset.split(",");
						game.get(i).setResets(reseter);
					}
				}
				catch(Exception e){}
				int[] actions=getIndexesOf(action,"<action>");
				int[] actionEnd=getIndexesOf(action,"</action>");
				
				try {
					if(game.get(i).getType().equals("Text Field")){
						for(int c=0;c<actions.length;c++){
							setAction(action.substring(actions[c]+8, actionEnd[c]),var.getTextField(game.get(i).getName()));
						}
					}
					else if(game.get(i).getType()==ComType.Button){
						for(int c=0;c<actions.length;c++){
							this.setAction(action.substring(actions[c]+8, actionEnd[c]),var.getButton(game.get(i).getName()));
						}
					}
					else if(game.get(i).getType()==ComType.ToggleButton){
						for(int c=0;c<actions.length;c++){
							setAction(action.substring(actions[c]+8, actionEnd[c]),var.getToggleButton(game.get(i).getName()));
						}
					}
				} catch (Exception e) {}//immediately quits if error occurs meaning there was no action found
				try{
					if(action.indexOf("<sync>")>-1){
						int[] syncs=getIndexesOf(action,"<sync>");
						int[] syncEnd=getIndexesOf(action,"</sync>");
						for(int c=0;c<syncs.length;c++){
							String sync= action.substring(syncs[c]+6,syncEnd[c]);
							if(game.get(i).getType()==ComType.TextField){
								textFieldSync(var.getTextField(game.get(i).getName()), var.getGameComponent(sync));
							}
							else if(game.get(i).getType()==ComType.TextArea){
								textAreaSync(var.getTextArea(game.get(i).getName()),var.getGameComponent(sync));
							}
							else if(game.get(i).getType()==ComType.TextPane){
								textPaneSync(var.getTextPane(game.get(i).getName()),var.getGameComponent(sync));
							}
							else if(game.get(i).getType()==ComType.Spinner){
								spinnerSync(var.getSpinner(game.get(i).getName()),var.getGameComponent(sync));
							}
							else if(game.get(i).getType()==ComType.ComboBox){
								comboBoxSync(var.getComboBox(game.get(i).getName()),var.getGameComponent(sync));
							}
							else if(game.get(i).getType()==ComType.List){
								listSync(var.getList(game.get(i).getName()),var.getGameComponent(sync));
							}
							else if(game.get(i).getType()==ComType.Slider){
								sliderSync(var.getSlider(game.get(i).getName()),var.getGameComponent(sync));
							}
						}
					}
				}
				catch(Exception e){}
				try {
					if(action.indexOf("<preset>")>-1){
						int[] presets=getIndexesOf(action,"<preset>");
						int[] presetEnd=getIndexesOf(action,"</preset>");
						String preset=action.substring(presets[0]+8, presetEnd[0]);//no loop cause there will only be one preset tag if any
						if(preset.equals("Timer")){
							JTextField timer=var.getTextField("Timer");
							timer.setEnabled(false);
							timer.setText("0");
						}
						else if(preset.equals("TeamSelect")){
							var.setTeamSelect(var.getComboBox("TeamSelect"));
						}
						else if(preset.equals("CommentBox")){
							game.get(i).setResets(new String[]{"CommentBox"});
							setAction("TeamSelect+\" \"+CommentBox",var.getTextField("CommentBox"));
							
						}
						else if(preset.equals("StartButton")){
							final JButton start=var.getButton("StartButton");
							start.addActionListener(new ActionListener(){
								public void actionPerformed(ActionEvent e){
									var.startGame();
									start.setEnabled(false);
								}
							});
						}
						else if(preset.equals("ShortcutBox")){
							final JTextField shortcut=var.getTextField("ShortcutBox");
							//updates whenever a new key is entered
							shortcut.addCaretListener(new CaretListener() {
								public void caretUpdate(CaretEvent arg0) {
									doShortcutActions(shortcut.getText());
								}
							});
							//clears the previous key, activates before caret listener and key press register
							shortcut.addKeyListener(new KeyAdapter() {
								public void keyPressed(KeyEvent e) {
									shortcut.setText("");
								}
							});
							
						}
					}
				} 
				catch (Exception e) {}
				//adds shortcuts
				try{
					if(action.indexOf("<clear>")>-1){
						int[] clears=getIndexesOf(action,"<clear>");
						int[] clearEnd=getIndexesOf(action,"</clear>");
						String clear=action.substring(clears[0]+7, clearEnd[0]);
						var.addShortcut(new Shortcut(clear,game.get(i).getName(),"clear"));
					}
				}
				catch(Exception e){}
				try{
					if(action.indexOf("<click>")>-1){
						int[] clicks=getIndexesOf(action,"<click>");
						int[] clickEnd=getIndexesOf(action,"</click>");
						String click=action.substring(clicks[0]+7, clickEnd[0]);
						var.addShortcut(new Shortcut(click,game.get(i).getName(),"click"));
					}
				}
				catch(Exception e){}
				try{
					if(action.indexOf("<select>")>-1){
						int[] selects=getIndexesOf(action,"<select>");
						int[] selectEnd=getIndexesOf(action,"</select>");
						String select=action.substring(selects[0]+8, selectEnd[0]);
						var.addShortcut(new Shortcut(select,game.get(i).getName(),"select"));
					}
				}
				catch(Exception e){}
				try{
					if(action.indexOf("<deselect>")>-1){
						int[] deselects=getIndexesOf(action,"<deselect>");
						int[] deselectEnd=getIndexesOf(action,"</deselect>");
						String deselect=action.substring(deselects[0]+10, deselectEnd[0]);
						var.addShortcut(new Shortcut(deselect,game.get(i).getName(),"deselect"));
					}
				}
				catch(Exception e){}
				try{
					if(action.indexOf("<increment>")>-1){
						int[] increments=getIndexesOf(action,"<increment>");
						int[] incrementEnd=getIndexesOf(action,"</increment>");
						String increment=action.substring(increments[0]+11, incrementEnd[0]);
						var.addShortcut(new Shortcut(increment,game.get(i).getName(),"increment"));
					}
				}
				catch(Exception e){}
				try{
					if(action.indexOf("<decrement>")>-1){
						int[] decrements=getIndexesOf(action,"<decrement>");
						int[] decrementEnd=getIndexesOf(action,"</decrement>");
						String decrement=action.substring(decrements[0]+11, decrementEnd[0]);
						var.addShortcut(new Shortcut(decrement,game.get(i).getName(),"decrement"));
					}
				}
				catch(Exception e){}
				//TODO complete
				
				
			}
		}
	}
	//three different set action to set an action based on the type as action listeners are added on the specific component class
	private void setAction(final String action,final JButton button){
		button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				log= new Logger(button.getName()+" log");
				var.addComment((doLogic(action)));
				doResets(var.getGameComponent(button.getName()).getResets());
			}
		});
	}
	private void setAction(final String action,final JTextField text){
		text.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				log= new Logger(text.getName()+" log");
				var.addComment((doLogic(action)));
				doResets(var.getGameComponent(text.getName()).getResets());
			}
		});
	}
	private void setAction(final String action, final JToggleButton toggle){
		toggle.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				log= new Logger(toggle.getName()+" log");
				var.addComment(doLogic(action));
				doResets(var.getGameComponent(toggle.getName()).getResets());
			}
		});
	}
	private String doLogic(String action){
		String state="variable";
		String[] data= action.split("(?!^)");
		String processing="";//string to store whatever variable or string that will be added to returnstr;
		String returnstr="";//String that is returned
		String condition="";//for if statements
		String name="";//for set statements
		int depth=0;//for nestled if statements
		for(int i=0;i<data.length;i++){
			log.addLog(data[i]+" processing "+processing+" return str "+returnstr);
			if(state.equals("variable")){
				if(data[i].equals("\"")){
					returnstr+=getVariableValue(processing);
					state="quotations";
					processing="";
					log.addLog("started quotes");
				}
				else if(data[i].equals("i")&&i+2<=data.length&&data[i+1].equals("f")&&data[i+2].equals("(")){
					state="ifStart";
					returnstr+=getVariableValue(processing);
					processing="";
					i=i+2;//to get first letter of condition statement;
					log.addLog("started if");
				}
				else if(data[i].equals("+")){
					returnstr+=getVariableValue(processing);
					log.addLog("added  variable:"+processing);
					processing="";
				}
				else if(data[i].equals("r")&&i+4<=data.length&&data[i+1].equals("e")&&data[i+2].equals("s")&&data[i+3].equals("e")&&data[i+4].equals("t")){
					processing="";
					reset();
					i=i+4;
					log.addLog("reset");
				}
				else if(data[i].equals("s")&&(i+3)<data.length&&data[i+1].equals("e")&&data[i+2].equals("t")&&data[i+3].equals("(")){
					state="setStart";
					returnstr+=getVariableValue(processing);
					log.addLog("started set");
					i=i+3;
					processing="";
				}
				else{
					processing+=data[i];
				}
			}
			else if(state.equals("quotations")){
				if(data[i].equals("\"")){//ignores everything except another quotation marking end of string
					returnstr+=processing;
					log.addLog("ended quotes, added:"+processing);
					processing="";
					state="variable";
				}
				else{
					processing+=data[i];
				}
			}
			else if(state.equals("ifStart")){
				if(data[i].equals(")")){
					state="ifStatement";
					depth=0;
					log.addLog("Ended ifstart,condition:"+condition);
				}
				else{
					condition+=data[i];
				}
			}
			else if(state.equals("ifStatement")){
				if(data[i].equals("{")){
					depth++;
					log.addLog("added depth of if statement");
					if(depth>1){
						processing+="{";
					}
				}
				else if(data[i].equals("}")&&depth==1){
					log.addLog("condition of if:"+checkCondition(condition));
					if(checkCondition(condition)){
						log.addLog("started if statment:"+processing);
						returnstr+=doLogic(processing);
						log.addLog("completed if statement:"+processing);
					}
					condition="";
					state="variable";
					processing="";
				}
				else if(data[i].equals("}")&&depth>1){
					depth--;
					log.addLog("removed depth of if statement");
					processing+="}";
				}
				else{
					processing+=data[i];
				}
			}
			else if(state.equals("setStart")){
				if(data[i].equals(")")){
					state="setStatement";
					name=processing;
					depth=0;
					log.addLog("setting variable:"+processing);
					processing="";
				}
				else{
					processing+=data[i];
				}
			}
			else if(state.equals("setStatement")){
				if(data[i].equals("{")){
					depth++;
					log.addLog("added depth of set statement");
					if(depth>1){
						processing+="{";
					}
				
				}
				else if(data[i].equals("}")&&depth==1){
					Components com=var.getGameComponent(name);
					log.addLog("doing set of "+name+":"+processing);
					if(com!=null){
						doSet(processing,com);
					}
					else{
						doSetS(testMath(processing),name);
					}
					log.addLog("done set");
					state="variable";
					processing="";
					
				}
				else if(data[i].equals("}")&&depth>1){
					depth--;
					log.addLog("removed depth of set statement");
					processing+="}";
				}
				else{
					processing+=data[i];
				}
				
			}
		}
		returnstr+=getVariableValue(processing);
		log.addLog("returning, returnstr "+returnstr);
		var.addLog(log);//adds the log once it completes the actions
		return returnstr;
	}
	private boolean checkCondition(String condition){
		String data[]= condition.split("(?!^)");
		String processing="";
		ArrayList<Boolean> orAnd=new ArrayList<Boolean>();//ArrayList to store whether the transiitons were or or and//true if and,false if or 
		ArrayList<Boolean> values=new ArrayList<Boolean>();//ArrayList to store values from comparisons
		for(int i=0;i<data.length;i++){
			log.addLog(data[i]+"CheckCondition:"+processing);
			if(data[i].equals("|")&&data[i+1].equals("|")){
				values.add(checkStatement(processing));
				orAnd.add(false);
				processing="";
				i++;
				log.addLog("added or");
			}
			else if(data[i].equals("&")&&data[i+1].equals("&")){
				values.add(checkStatement(processing));
				orAnd.add(true);
				processing="";
				i++;
				log.addLog("added and");
			}
			else{
				processing+=data[i];
			}
		}
		values.add(checkStatement(processing));//for the last one
		if(values.size()==1){
			return values.get(0);//final case where there's only one statement
		}
		boolean tester=values.get(0);
		int startIndex=-1;
		for(int i=0;i<orAnd.size();i++){	
			if(orAnd.get(i)){//if there's an and
				if(startIndex==-1){
					startIndex=i;
				}
			}
			else{
				if(startIndex>-1){
					ArrayList<Boolean> test=new ArrayList<Boolean>();
					for(int c=startIndex;c<=i;c++){
						test.add(values.get(c));
					}
					if(allTrue(test)){
						return true;
					}
					else{
						tester=false;
						startIndex=-1;
					}
				}
				else{
					if(tester){
						return true;
					}
					else{
						tester=values.get(i+1);
					}
				}
				startIndex=0;
			}
		}
		if(startIndex>-1){
			ArrayList<Boolean> test=new ArrayList<Boolean>();
			for(int c=startIndex;c<=orAnd.size();c++){
				test.add(values.get(c));
			}
			if(allTrue(test)){
				return true;
			}
		}
		else{
			if(tester){
				return true;
			}
			else{
				tester=values.get(orAnd.size()+1);
			}
		}
		return false;
	}
	private boolean allTrue(ArrayList<Boolean> test){//used for ands
		boolean isTrue=true;
		for(int i=0;i<test.size();i++){
			if(!test.get(i)){
				isTrue=false;//as and statements fail if there's even one false
			}
		}
		return isTrue;
	}
	private boolean checkStatement(String statement){//each statement a true/false comparison
		String[] data=statement.split("(?!^)");
		String processing="";
		String compareTo="";
		String operation="";
		for(int i=0;i<data.length;i++){
			log.addLog("CheckStatement:"+processing+" "+compareTo+" operation:"+operation);
			if(operation.equals("")){//for the first half of the statement
				if(data[i].equals("=")&&data[i+1].equals("=")){
					operation="==";
					i++;
				}
				else if(data[i].equals(">")){
					if(data[i+1].equals("=")){
						operation=">=";
						i++;
					}
					else{
						operation=">";
					}
				}
				else if(data[i].equals("<")){
					if(data[i+1].equals("=")){
						operation="<=";
						i++;
					}
					else{
						operation="<";
					}
				}
				else{
					processing+=data[i];
				}
			}
			else{
				compareTo+=data[i];
			}
		}
		log.addLog("comparing to?:"+compareTo);
		if(compareTo.equals("")){//case of not comparative operation
			if(processing.equals("true")){
				log.addLog("found \"true\"");
				return true;//test to see if it's just true/false in words
			}
			else if(processing.equals("false")){
				log.addLog("found\"false\"");
				return false;
			}
			else{
				String test=getVariableValue(processing);//otherwise assume it's a boolean variable and getValue
				log.addLog("testing boolean for variable:"+processing);
				if(test.equals("true")){
					return true;
				}
			}
		}
		else{
			if(operation.equals("==")){//possiblities for all the mathematical comparison operations//assuming everything's a number
				log.addLog("is "+processing+"/"+getVariableValue(processing)+"=="+compareTo+"/"+getVariableValue(compareTo));
				if(processing.equals(compareTo)){
					return true;
				}
				else if(getVariableValue(processing).equals(compareTo)){
					return true;
				}
				else if(processing.equals(getVariableValue(compareTo))){
					return true;
				}
				else if(getVariableValue(processing).equals(getVariableValue(compareTo))){
					return true;
				}
				
			}
			else if(operation.equals(">=")){
				if(testMath(processing)>=testMath(compareTo)){
					return true;
				}
			}
			else if(operation.equals(">")){
				if(testMath(processing)>testMath(compareTo)){
					return true;
				}
			}
			else if(operation.equals("<=")){
				if(testMath(processing)<=testMath(compareTo)){
					return true;
				}
			}
		}
		//actual logic happens here
		
		return false;
	}
	private int testMath(String maths){
		String[] data=maths.split("(?!^)");
		String processing="";
		String operation="";
		int value=0;
		for(int i=0;i<data.length;i++){
			log.addLog("Processing:"+processing+" operation:"+operation);
			if(data[i].equals("+")){
				value=doOperation(value,toNumber(processing),operation);
				processing="";
				operation="+";
			}
			else if(data[i].equals("-")){
				value=doOperation(value,toNumber(processing),operation);
				processing="";
				operation="-";
			}
			else if(data[i].equals("*")){
				value=doOperation(value,toNumber(processing),operation);
				processing="";
				operation="*";
			}
			else if(data[i].equals("/")){
				value=doOperation(value,toNumber(processing),operation);
				processing="";
				operation="/";
			}
			else if(data[i].equals("^")){
				value=doOperation(value,toNumber(processing),operation);
				processing="";
				operation="^";
			}
			else if(data[i].equals("%")){
				value=doOperation(value,toNumber(processing),operation);
				processing="";
				operation="%";
			}
			else{
				processing+=data[i];
			}
		}
		value=doOperation(value,toNumber(processing),operation);
		log.addLog("testMathResult:"+value);
		return value;
	}
	private int doOperation(int num1,int num2,String operation){
		if(operation.equals("+")){
			return num1+num2;
		}
		else if(operation.equals("-")){
			return num1-num2;
		}
		else if(operation.equals("/")){
			return num1/num2;
		}
		else if(operation.equals("*")){
			return num1*num2;
		}
		else if(operation.equals("^")){
			return num1^num2;
		}
		else if(operation.equals("%")){
			return num1%num2;
		}
		return num2;//if there's no math operation being done,assumes it's the first one and returns the new number
	}
	private int toNumber(String num){
		int number=0;
		try{
			number=Integer.parseInt(num);
		}
		catch(Exception e){
			try{
				number=Integer.parseInt(getVariableValue(num));
			}
			catch(Exception exc){
			}
		}
		return number;
	}
	//return value of the variable,if it doesn't exist, returns "" so nothing is added to the return string;
	private String getVariableValue(String varname){
		String type="";
		Components selected=var.getGameComponent(varname);
		//in the case variable doesn't exist it may be a score variable, checks to see starting letter
		if(selected==null){
			if(varname.startsWith("R")){
				type="redScore";
			}
			else if(varname.startsWith("B")){
				type="blueScore";
			}
			else{
				return "";
			}
		}
		if(type.equals("redScore")){
			ScoreField score=var.getScoreField(varname.substring(1));
			if(score!=null){
				return ""+score.getRValue();
			}
		}
		else if(type.equals("blueScore")){
			ScoreField score=var.getScoreField(varname.substring(1));
			if(score!=null){
				return ""+score.getBValue();
			}
		}
		//returns generic value for each of the different types of componnents
		if(selected.getType()==ComType.TextField){
			return ""+var.getTextField(varname).getText();
		}
		else if(selected.getType()==ComType.Button){
			return ""+var.getButton(varname).isSelected();
		}
		else if(selected.getType()==ComType.CheckBox){
			return ""+var.getCheckBox(varname).isSelected();
		}
		else if(selected.getType()==ComType.ComboBox){
			return ""+var.getComboBox(varname).getSelectedItem();
		}
		else if(selected.getType()==ComType.RadioButton){
			return ""+var.getRadioButton(varname).isSelected();//cause radio button is from variety of selections
		}
		else if(selected.getType()==ComType.ToggleButton){
			return ""+var.getToggleButton(varname).isSelected();
		}
		else if(selected.getType()==ComType.TextArea){
			return var.getTextArea(varname).getText();
		}
		else if(selected.getType()==ComType.TextPane){
			return var.getTextPane(varname).getText();
		}
		else if(selected.getType()==ComType.Spinner){
			return ""+var.getSpinner(varname).getValue();
		}
		else if(selected.getType()==ComType.List){
			return ""+var.getList(varname).getSelectedValue();
		}
		else if(selected.getType()==ComType.Slider){
			return ""+var.getSlider(varname).getValue();
		}
		else if(selected.getType()==ComType.Label){
			return ""+var.getLabel(varname).getName();
		}
		return "";
	}
	private void doResets(String[] resets){
		try {
			for(int i=0;i<resets.length;i++){
				ArrayList<Components> game=var.getGameComponents();
				for(int c=0;c<game.size();c++){
					if(game.get(c).getName().equals(resets[i])){
						doReset(game.get(c).getName(),game.get(c).getType(),game.get(c).getDefaultVar());
					}
				}
			}
		} catch (Exception e) {}//if null
	}
	public void doReset(String name, ComType type,String defaultVar){
		if(type==ComType.TextField){
			var.getTextField(name).setText(defaultVar);
			if(defaultVar.equals("null")){
				var.getTextField(name).setText("");
			}
		}
		else if(type==ComType.CheckBox){
			if(defaultVar.equals("true")){
				var.getCheckBox(name).setSelected(true);
			}
			else{
				var.getCheckBox(name).setSelected(false);
			}	
		}
		else if(type==ComType.ComboBox){
			try{
			var.getComboBox(name).setSelectedIndex(Integer.parseInt(defaultVar));
			}
			catch(Exception e){}
		}
		else if(type==ComType.RadioButton){
			if(defaultVar.equals("true")){
				var.getRadioButton(name).setSelected(true);
			}
			else{
				var.getRadioButton(name).setSelected(false);
			}
		}
		else if(type==ComType.ToggleButton){
			if(defaultVar.equals("true")){
				var.getToggleButton(name).setSelected(true);
			}
			else{
				var.getToggleButton(name).setSelected(false);
			}
		}
		else if(type==ComType.TextArea){
			var.getTextArea(name).setText(defaultVar);
			if(defaultVar.equals("null")){
				var.getTextArea(name).setText("");
			}
		}
		else if(type==ComType.TextPane){
			var.getTextPane(name).setText(defaultVar);
			if(defaultVar.equals("null")){
				var.getTextPane(name).setText("");
			}
		}
		else if(type==ComType.Spinner){
			try {
				var.getSpinner(name).setValue(Integer.parseInt(defaultVar));
			} catch (NumberFormatException e) {
				var.getSpinner(name).setValue(0);
			}
		}
		else if(type==ComType.Slider){
			try {
				var.getSlider(name).setValue(Integer.parseInt(defaultVar));
			} catch (NumberFormatException e) {
				var.getSlider(name).setValue(0);
			}
		}
		else if(type==ComType.List){
			try {
				var.getList(name).setSelectedIndex(Integer.parseInt(defaultVar));
			} catch (Exception e) {}
		}
		
	}
	private void doShortcutActions(String key){//finds the shortcuts related to that key, and does the action
		ArrayList<Shortcut> shortcuts=var.getShortcuts();
		for(int i=0;i<shortcuts.size();i++){
			if(shortcuts.get(i).getKey().equals(key)){
				doShortcutAction(shortcuts.get(i).getAction(),shortcuts.get(i).getName());
			}
		}
	}
	public void doShortcutAction(String action, String name){//does action based on name of action and name of component it is done on
		Components game=var.getGameComponent(name);
		System.out.println("action:"+action+"type"+game.getType());
		if(game.getType()==ComType.TextField){
			if(action.equals("clear")){
				var.getTextField(name).setText("");
			}
		}
		else if(game.getType()==ComType.Button){
			if(action.equals("click")){
				var.getButton(name).doClick();
			}
		}
		else if(game.getType()==ComType.CheckBox){
			JCheckBox check=var.getCheckBox(name);
			if(action.equals("select")){
				check.setSelected(true);
			}
			else if(action.equals("deselect")){
				check.setSelected(false);
			}
			else if(action.equals("change")){
				check.setSelected(!check.isSelected());
			}
		}
		else if(game.getType()==ComType.ComboBox){
			JComboBox<String> combo=var.getComboBox(name);
			if(action.equals("increment")){
				try{
					combo.setSelectedIndex(combo.getSelectedIndex()+1);
				}
				catch(Exception e){}
			}
			else if(action.equals("decrement")){
				try{
					combo.setSelectedIndex(combo.getSelectedIndex()-1);
				}
				catch(Exception e){}
			}
			else if(action.equals("clear")){
				combo.setSelectedIndex(-1);
			}
		}
		else if(game.getType()==ComType.RadioButton){
			JRadioButton radio=var.getRadioButton(name);
			if(action.equals("select")){
				radio.setSelected(true);
			}
			else if(action.equals("click")){
				radio.doClick();
			}
		}
		else if(game.getType()==ComType.ToggleButton){
			JToggleButton toggle=var.getToggleButton(name);
			if(action.equals("select")){
				toggle.setSelected(true);
			}
			else if(action.equals("deselect")){
				toggle.setSelected(false);
			}
			else if(action.equals("click")){
				toggle.doClick();
			}
		}
		else if(game.getType()==ComType.TextArea){
			if(action.equals("clear")){
				var.getTextArea(name).setText("");
			}
		}
		else if(game.getType()==ComType.TextPane){
			if(action.equals("clear")){
				var.getTextPane(name).setText("");
			}
		}
		else if(game.getType()==ComType.Spinner){
			JSpinner spin=var.getSpinner(name);
			if(action.equals("increment")){
				spin.setValue((int)spin.getValue()+1);
			}
			else if(action.equals("decrement")){
				spin.setValue((int)spin.getValue()-1);
			}
			else if(action.equals("clear")){
				spin.setValue(0);
			}
		}
		else if(game.getType()==ComType.List){
			JList<String> list= var.getList(name);
			if(action.equals("increment")){
				try{
					list.setSelectedIndex(list.getSelectedIndex()+1);
				}
				catch(Exception e){}
			}
			else if(action.equals("decrement")){
				try{
					list.setSelectedIndex(list.getSelectedIndex()-1);
				}
				catch(Exception e){}
			}
		}
		else if(game.getType()==ComType.Slider){
			JSlider slide=var.getSlider(name);
			if(action.equals("increment")){
				slide.setValue(slide.getValue()+game.getInterval());
			}
			else if(action.equals("decrement")){
				slide.setValue(slide.getValue()-game.getInterval());
			}
		}
	}
	//sets the score variables based on what's happened in game
	private void doSetS(int value,String name){
		ScoreField score=var.getScoreField(name.substring(1));//substring to remove R/B
		if(score!=null){
			//score variables annotated with R/B to indicate which side it's being applied to
			if(name.startsWith("R")){
				score.setRValue(value);
			}
			else if(name.startsWith("B")){
				score.setBValue(value);
			}
		}
	}
	private void doSet(String action, Components com){
		if(action.equals("increment")){
			doShortcutAction(action,com.getName());
		}
		else if(action.equals("decement")){
			doShortcutAction(action,com.getName());
		}
		else if(action.equals("clear")){
			doShortcutAction(action,com.getName());
		}
		else if(action.equals("select")){
			doShortcutAction(action,com.getName());
		}
		else if(action.equals("deselect")){
			doShortcutAction(action,com.getName());
		}
		else if(action.equals("click")){
			doShortcutAction(action,com.getName());
		}
		else if(action.equals("invisible")){
			doVisible(false,com);
		}
		else if(action.equals("visible")){
			doVisible(true,com);
		}
		else if(com.getType().setType.equals("boolean")){
			setValue(""+checkCondition(action),com);
		}
		else if(com.getType().setType.equals("int")){
			setValue(""+testMath(action),com);
		}
		else{
			setValue(doLogic(action),com);
		}
	}
	private void setValue(String value, Components com){
		System.out.println("setValue:"+value);
		//does a check on type and sets variable based on type by using getter in scout var class
		if(com.getType()==ComType.TextField){
			var.getTextField(com.getName()).setText(value);
		}
		else if(com.getType()==ComType.CheckBox){
			if(value.equals("true")){
				var.getCheckBox(com.getName()).setSelected(true);;
			}
			else{
				var.getCheckBox(com.getName()).setSelected(false);
			}
		}
		else if(com.getType()==ComType.ComboBox){
			try {
				var.getComboBox(com.getName()).setSelectedIndex(Integer.parseInt(trimDigit(value)));
			} catch (Exception e) {}
		}
		else if(com.getType()==ComType.RadioButton){
			if(value.equals("true")){
				var.getRadioButton(com.getName()).setSelected(true);
			}
			else{
				var.getRadioButton(com.getName()).setSelected(false);
			}
		}
		else if(com.getType()==ComType.ToggleButton){
			if(value.equals("true")){
				var.getToggleButton(com.getName()).setSelected(true);
			}
			else{
				var.getToggleButton(com.getName()).setSelected(false);
			}
		}
		else if(com.getType()==ComType.TextArea){
			var.getTextArea(com.getName()).setText(value);
		}
		else if(com.getType()==ComType.TextPane){
			var.getTextPane(com.getName()).setText(value);
		}
		else if(com.getType()==ComType.Spinner){
			var.getSpinner(com.getName()).setValue(Integer.parseInt(trimDigit(value)));
		}
		else if(com.getType()==ComType.List){
			var.getList(com.getName()).setSelectedIndex(Integer.parseInt(trimDigit(value)));
		}
		else if(com.getType()==ComType.Slider){
			try {
				var.getSlider(com.getName()).setValue(Integer.parseInt(trimDigit(value)));
			} catch (Exception e) {}
		}
	}
	//sets visibility of component
	private void doVisible(boolean visible,Components com){
		if(com.getType()==ComType.Button){
			var.getButton(com.getName()).setVisible(visible);
		}
		else if(com.getType()==ComType.CheckBox){
			var.getCheckBox(com.getName()).setVisible(visible);
		}
		else if(com.getType()==ComType.ComboBox){
			var.getComboBox(com.getName()).setVisible(visible);
		}
		else if(com.getType()==ComType.Label){
			var.getLabel(com.getName()).setVisible(visible);
		}
		else if(com.getType()==ComType.List){
			var.getList(com.getName()).setVisible(visible);
		}
		else if(com.getType()==ComType.RadioButton){
			var.getRadioButton(com.getName()).setVisible(visible);
		}
		else if(com.getType()==ComType.Slider){
			var.getSlider(com.getName()).setVisible(visible);
		}
		else if(com.getType()==ComType.Spinner){
			var.getSpinner(com.getName()).setVisible(visible);
		}
		else if(com.getType()==ComType.TextArea){
			var.getTextArea(com.getName()).setVisible(visible);
		}
		else if(com.getType()==ComType.TextField){
			var.getTextField(com.getName()).setVisible(visible);
		}
		else if(com.getType()==ComType.TextPane){
			var.getTextPane(com.getName()).setVisible(visible);
		}
		else if(com.getType()==ComType.ToggleButton){
			var.getToggleButton(com.getName()).setVisible(visible);
		}
	}
	//Syncs for all the different types of components
	private void textFieldSync(final JTextField text, final Components sync){
		text.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(sync.getType()==ComType.TextField){
					var.getTextField(sync.getName()).setText(text.getText());
				}
				else if(sync.getType()==ComType.Spinner){
					try {
						var.getSpinner(sync.getName()).setValue(Integer.parseInt(text.getText()));
					} catch (Exception e2){}
				}
				else if(sync.getType()==ComType.TextArea){
					var.getTextArea(sync.getName()).setText(text.getText());
				}
				else if(sync.getType()==ComType.TextPane){
					var.getTextPane(sync.getName()).setText(text.getText());
				}
				else if(sync.getType()==ComType.ComboBox){
					if(var.getComboBox(sync.getName()).getSelectedIndex()>-1){
						var.getComboModel(sync.getName()).set(var.getComboBox(sync.getName()).getSelectedIndex(), text.getText());
					}
				}
				else if(sync.getType()==ComType.List){
					if(var.getList(sync.getName()).getSelectedIndex()>-1){
						var.getListModel(sync.getName()).set(var.getList(sync.getName()).getSelectedIndex(), text.getText());
					}
				}
				else if(sync.getType()==ComType.Slider){
					try{
						var.getSlider(sync.getName()).setValue(Integer.parseInt(text.getText()));
					}catch(Exception e1){}
				}
				
			}
		});
		text.addFocusListener(new FocusListener(){

			@Override
			public void focusLost(FocusEvent e) {
				if(sync.getType()==ComType.TextField){
					var.getTextField(sync.getName()).setText(text.getText());
				}
				else if(sync.getType()==ComType.Spinner){
					try {
						var.getSpinner(sync.getName()).setValue(Integer.parseInt(text.getText()));
					} catch (Exception e2){}
				}
				else if(sync.getType()==ComType.TextArea){
					var.getTextArea(sync.getName()).setText(text.getText());
				}
				else if(sync.getType()==ComType.TextPane){
					var.getTextPane(sync.getName()).setText(text.getText());
				}
				else if(sync.getType()==ComType.ComboBox){
					if(var.getComboBox(sync.getName()).getSelectedIndex()>-1){
						var.getComboModel(sync.getName()).set(var.getComboBox(sync.getName()).getSelectedIndex(), text.getText());
					}
				}
				else if(sync.getType()==ComType.List){
					if(var.getList(sync.getName()).getSelectedIndex()>-1){
						var.getListModel(sync.getName()).set(var.getList(sync.getName()).getSelectedIndex(), text.getText());
					}
				}
				else if(sync.getType()==ComType.Slider){
					try{
						var.getSlider(sync.getName()).setValue(Integer.parseInt(text.getText()));
					}catch(Exception e1){}
				}
			}
			@Override
			public void focusGained(FocusEvent e) {}
			
		});
	}
	private void textAreaSync(final JTextArea text, final Components sync){
		text.addFocusListener(new FocusListener(){

			@Override
			public void focusLost(FocusEvent e) {
				if(sync.getType()==ComType.TextField){
					var.getTextField(sync.getName()).setText(text.getText());
				}
				else if(sync.getType()==ComType.Spinner){
					try {
						var.getSpinner(sync.getName()).setValue(Integer.parseInt(text.getText()));
					} catch (Exception e2){}
				}
				else if(sync.getType()==ComType.TextArea){
					var.getTextArea(sync.getName()).setText(text.getText());
				}
				else if(sync.getType()==ComType.TextPane){
					var.getTextPane(sync.getName()).setText(text.getText());
				}
				else if(sync.getType()==ComType.ComboBox){
					if(var.getComboBox(sync.getName()).getSelectedIndex()>-1){
						var.getComboModel(sync.getName()).set(var.getComboBox(sync.getName()).getSelectedIndex(), text.getText());
					}
				}
				else if(sync.getType()==ComType.List){
					if(var.getList(sync.getName()).getSelectedIndex()>-1){
						var.getListModel(sync.getName()).set(var.getList(sync.getName()).getSelectedIndex(), text.getText());
					}
				}
				else if(sync.getType()==ComType.Slider){
					try{
						var.getSlider(sync.getName()).setValue(Integer.parseInt(text.getText()));
					}catch(Exception e1){}
				}
			}
			@Override
			public void focusGained(FocusEvent e) {}
			
		});
	}
	private void textPaneSync(final JTextPane text, final Components sync){
		text.addFocusListener(new FocusListener(){

			@Override
			public void focusLost(FocusEvent e) {
				if(sync.getType()==ComType.TextField){
					var.getTextField(sync.getName()).setText(text.getText());
				}
				else if(sync.getType()==ComType.Spinner){
					try {
						var.getSpinner(sync.getName()).setValue(Integer.parseInt(text.getText()));
					} catch (Exception e2){}
				}
				else if(sync.getType()==ComType.TextArea){
					var.getTextArea(sync.getName()).setText(text.getText());
				}
				else if(sync.getType()==ComType.TextPane){
					var.getTextPane(sync.getName()).setText(text.getText());
				}
				else if(sync.getType()==ComType.ComboBox){
					if(var.getComboBox(sync.getName()).getSelectedIndex()>-1){
						var.getComboModel(sync.getName()).set(var.getComboBox(sync.getName()).getSelectedIndex(), text.getText());
					}
				}
				else if(sync.getType()==ComType.List){
					if(var.getList(sync.getName()).getSelectedIndex()>-1){
						var.getListModel(sync.getName()).set(var.getList(sync.getName()).getSelectedIndex(), text.getText());
					}
				}
				else if(sync.getType()==ComType.Slider){
					try{
						var.getSlider(sync.getName()).setValue(Integer.parseInt(text.getText()));
					}catch(Exception e1){}
				}
			}
			@Override
			public void focusGained(FocusEvent e) {}
			
		});
	}
	private void spinnerSync(final JSpinner spin, final Components sync){
		spin.addChangeListener(new ChangeListener(){

			@Override
			public void stateChanged(ChangeEvent e) {
				if(sync.getType()==ComType.TextField){
					var.getTextField(sync.getName()).setText(""+spin.getValue());
				}
				else if(sync.getType()==ComType.Spinner){
					var.getSpinner(sync.getName()).setValue(spin.getValue());
				}
				else if(sync.getType()==ComType.TextArea){
					var.getTextArea(sync.getName()).setText(""+spin.getValue());
				}
				else if(sync.getType()==ComType.TextPane){
					var.getTextPane(sync.getName()).setText(""+spin.getValue());
				}
				else if(sync.getType()==ComType.ComboBox){
					if(var.getComboBox(sync.getName()).getSelectedIndex()>-1){
						var.getComboModel(sync.getName()).set(var.getComboBox(sync.getName()).getSelectedIndex(), ""+spin.getValue());
					}
				}
				else if(sync.getType()==ComType.List){
					if(var.getList(sync.getName()).getSelectedIndex()>-1){
						var.getListModel(sync.getName()).set(var.getList(sync.getName()).getSelectedIndex(), ""+spin.getValue());
					}
				}
				else if(sync.getType()==ComType.Slider){
					try {
						var.getSlider(sync.getName()).setValue((int)spin.getValue());
					} catch (Exception e1) {}
				}
			}
			
		});
		
	}
	private void comboBoxSync(final JComboBox<String> combo, final Components sync){
		combo.addPropertyChangeListener(new PropertyChangeListener(){

			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				if(sync.getType()==ComType.TextField){
					var.getTextField(sync.getName()).setText(""+combo.getSelectedItem());
				}
				else if(sync.getType()==ComType.Spinner){
					try{
						var.getSpinner(sync.getName()).setValue(Integer.parseInt(""+combo.getSelectedItem()));
					}catch(Exception e){}
				}
				else if(sync.getType()==ComType.TextArea){
					var.getTextArea(sync.getName()).setText(""+combo.getSelectedItem());
				}
				else if(sync.getType()==ComType.TextPane){
					var.getTextPane(sync.getName()).setText(""+combo.getSelectedItem());
				}
				else if(sync.getType()==ComType.ComboBox){
					if(var.getComboBox(sync.getName()).getSelectedIndex()>-1){
						var.getComboModel(sync.getName()).set(var.getComboBox(sync.getName()).getSelectedIndex(), ""+combo.getSelectedItem());
					}
				}
				else if(sync.getType()==ComType.List){
					if(var.getList(sync.getName()).getSelectedIndex()>-1){
						var.getListModel(sync.getName()).set(var.getList(sync.getName()).getSelectedIndex(), ""+combo.getSelectedItem());
					}
				}
				else if(sync.getType()==ComType.Slider){
					try {
						var.getSlider(sync.getName()).setValue(Integer.parseInt(""+combo.getSelectedItem()));
					} catch (Exception e) {}
				}
			}
			
		});
	}
	private void listSync(final JList<String> list, final Components sync){
		list.addPropertyChangeListener(new PropertyChangeListener(){
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				if(sync.getType()==ComType.TextField){
					var.getTextField(sync.getName()).setText(var.getListModel(list.getName()).get(list.getSelectedIndex()));
				}
				else if(sync.getType()==ComType.Spinner){
					try{
						var.getSpinner(sync.getName()).setValue(Integer.parseInt(var.getListModel(list.getName()).get(list.getSelectedIndex())));
					}catch(Exception e){}
				}
				else if(sync.getType()==ComType.TextArea){
					var.getTextArea(sync.getName()).setText(var.getListModel(list.getName()).get(list.getSelectedIndex()));
				}
				else if(sync.getType()==ComType.TextPane){
					var.getTextPane(sync.getName()).setText(var.getListModel(list.getName()).get(list.getSelectedIndex()));
				}
				else if(sync.getType()==ComType.ComboBox){
					var.getComboModel(sync.getName()).set(var.getComboBox(sync.getName()).getSelectedIndex(), var.getListModel(list.getName()).get(list.getSelectedIndex()));
				}
				else if(sync.getType()==ComType.List){
					var.getListModel(sync.getName()).set(var.getList(sync.getName()).getSelectedIndex(), var.getListModel(list.getName()).get(list.getSelectedIndex()));
				}
				else if(sync.getType()==ComType.Slider){
					try {
						var.getSlider(sync.getName()).setValue(Integer.parseInt(var.getListModel(list.getName()).get(list.getSelectedIndex())));
					} catch (Exception e1) {}
				}
			}
		});
	}
	private void sliderSync(final JSlider slide, final Components sync){
		slide.addChangeListener(new ChangeListener(){
			@Override
			public void stateChanged(ChangeEvent e) {
				if(sync.getType()==ComType.TextField){
					var.getTextField(sync.getName()).setText(""+slide.getValue());
				}
				else if(sync.getType()==ComType.Spinner){
					try{
						var.getSpinner(sync.getName()).setValue(slide.getValue());
					}catch(Exception e2){}
				}
				else if(sync.getType()==ComType.TextArea){
					var.getTextArea(sync.getName()).setText(""+slide.getValue());
				}
				else if(sync.getType()==ComType.TextPane){
					var.getTextPane(sync.getName()).setText(""+slide.getValue());
				}
				else if(sync.getType()==ComType.ComboBox){
					var.getComboModel(sync.getName()).set(var.getComboBox(sync.getName()).getSelectedIndex(), ""+slide.getValue());
				}
				else if(sync.getType()==ComType.List){
					var.getListModel(sync.getName()).set(var.getList(sync.getName()).getSelectedIndex(), ""+slide.getValue());
				}
				else if(sync.getType()==ComType.Slider){
					try {
						var.getSlider(sync.getName()).setValue(slide.getValue());
					} catch (Exception e1) {}
				}
				
			}
			
		});
	}
	//returns an integer array of all instances of the string(used when setting things in setActions)
	private int[] getIndexesOf(String fullstr,String str){
		  int index=0;
		  ArrayList<Integer> arrays=new ArrayList<Integer>();//ArrayList to temporarily hold indexes
		  while(true){//break statement is used to end loop
		   index=fullstr.indexOf(str, index);//sees if there's anything that matches, returns -1 if no matches
		   if(index>-1){
		    arrays.add(index);//adds to array
		   }
		   else{
		     break;//if -1 means no matches and it's done
		   }
		   index++;//increment so that on next check it won't find the same one
		  }
		  int[] returnint= new int[arrays.size()];//creates array to return
		  for(int i=0;i<arrays.size();i++){
		   returnint[i]=arrays.get(i);//sets all variables of array using ArrayList
		  }
		  return returnint;
		  
		 }
	public String trimDigit(String text){
		String s="";
		char[] test=text.toCharArray();
		for(char c:test){
			if(Character.isDigit(c)){
				s+=c;
			}
		}
		return s;
	}
	public void startGame(){
		var.startGame();
		JButton start=var.getButton("StartButton");
		if(start!=null){
			start.setEnabled(false);
		}
	}
	public JPanel getP_Game(){
		return this;
	}
	public void reset(){//like do resets except does it for all the components, no filter
		ArrayList<Components> game=var.getGameComponents();
		for(int i=0;i<game.size();i++){
			doReset(game.get(i).getName(),game.get(i).getType(),game.get(i).getDefaultVar());
		}
		JButton start=var.getButton("StartButton");
		if(start!=null){
			start.setEnabled(true);
		}
	}
}
