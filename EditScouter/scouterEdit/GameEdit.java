package scouterEdit;
//TODO add debug features
//TODO add ability to shift components up and down combobox select list?
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Component;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.event.ChangeListener;

import global.Components;
import global.Location;

import javax.swing.event.ChangeEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class GameEdit extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5509805783432568868L;
	private final EditVar var;//Object storing main variables

	//main components
	final JComboBox<String> CB_ComName;
	private JTextField TF_Name;//global var for textfields
	private JTextField TF_Grouping;
	private JSpinner Sp_X;
	private JSpinner Sp_Y;
	private JSpinner Sp_H;
	private JSpinner Sp_W;
	private JSpinner Sp_Max;
	private JSpinner Sp_Min;
	private JSpinner Sp_Interval;
	private JScrollPane SP_Tooltip;
	private JTextArea TA_Tooltip;
	private JScrollPane SP_Action;
	private JTextArea TA_Action;
	private JSpinner Sp_Default;
	private JCheckBox CB_Default;
	//labels
	private JLabel lblComponent;
	private JLabel lblX;
	private JLabel lblY;
	private JLabel lblH;
	private JLabel lblW;
	private JLabel lblMax;
	private JLabel lblMin;
	private JLabel lblInterval;
	private JLabel lblGrouping;

	private JPanel P_EditGame;//panel stored on class level, main is stored in EditVar
	private Vector<String> gameComponents =new Vector<String>();
	private JTextField TF_Default;
	/**
	 * Create the panel.
	 */
	public GameEdit(final EditVar var) {
		this.var=var;//retrieves the EditVar used by all classes from main class
		P_EditGame= new JPanel();//retrieves the variable from variable class>>disabled for the moment as there's no use and it messes up design view
		P_EditGame.setLayout(null);//sets layout to absolute to simplify things

		CB_ComName = new JComboBox<String>();
		CB_ComName.setToolTipText("Select the component you want to edit");
		CB_ComName.setModel(new DefaultComboBoxModel<String>(gameComponents));
		CB_ComName.setBounds(10, 14, 139, 20);
		P_EditGame.add(CB_ComName);

		lblComponent = new JLabel("Component");
		lblComponent.setBounds(10, 0, 74, 14);
		P_EditGame.add(lblComponent);

		final JButton B_NComponent = new JButton("New Component");
		B_NComponent.setToolTipText("Create new component");
		B_NComponent.setBounds(159, 13, 111, 23);
		P_EditGame.add(B_NComponent);

		Sp_X = new JSpinner();
		Sp_X.setToolTipText("Sets the x value of the upper left corner of the component in pixels");
		Sp_X.setBounds(10, 58, 60, 20);
		P_EditGame.add(Sp_X);

		Sp_Y = new JSpinner();
		Sp_Y.setToolTipText("Sets the y value of the upper left corner of the component in pixels");
		Sp_Y.setBounds(69, 58, 60, 20);
		P_EditGame.add(Sp_Y);

		Sp_H = new JSpinner();
		Sp_H.setToolTipText("Sets the height of the component in pixels");
		Sp_H.setBounds(69, 103, 60, 20);
		P_EditGame.add(Sp_H);

		Sp_W = new JSpinner();
		Sp_W.setToolTipText("Sets the width of the component in pixels");
		Sp_W.setBounds(10, 103, 60, 20);
		P_EditGame.add(Sp_W);

		lblX = new JLabel("x");
		lblX.setToolTipText("Sets the x value of the upper left corner of the component in pixels");
		lblX.setBounds(10, 44, 46, 14);
		P_EditGame.add(lblX);

		lblY = new JLabel("y");
		lblY.setToolTipText("Sets the y value of the upper left corner of the component in pixels");
		lblY.setBounds(68, 44, 46, 14);
		P_EditGame.add(lblY);

		lblW = new JLabel("w");
		lblW.setToolTipText("Sets the width of the component in pixels");
		lblW.setBounds(10, 89, 46, 14);
		P_EditGame.add(lblW);

		lblH = new JLabel("h");
		lblH.setToolTipText("Sets the height of the component in pixels");
		lblH.setBounds(69, 89, 46, 14);
		P_EditGame.add(lblH);

		Sp_Max = new JSpinner();
		Sp_Max.setBounds(143, 58, 60, 20);
		P_EditGame.add(Sp_Max);

		Sp_Min = new JSpinner();
		Sp_Min.setBounds(143, 103, 60, 20);
		P_EditGame.add(Sp_Min);

		lblMax = new JLabel("Max");
		lblMax.setBounds(143, 44, 46, 14);
		P_EditGame.add(lblMax);

		lblMin = new JLabel("Min");
		lblMin.setBounds(143, 89, 46, 14);
		P_EditGame.add(lblMin);

		Sp_Interval = new JSpinner();
		Sp_Interval.setToolTipText("Sets the interval between numers for sliders or interval to go up at for spinners");
		Sp_Interval.setBounds(143, 143, 60, 20);
		P_EditGame.add(Sp_Interval);

		lblInterval = new JLabel("Interval");
		lblInterval.setBounds(143, 129, 57, 14);
		P_EditGame.add(lblInterval);

		SP_Tooltip = new JScrollPane();
		SP_Tooltip.setBounds(280, 14, 280, 47);
		P_EditGame.add(SP_Tooltip);

		TA_Tooltip = new JTextArea();
		TA_Tooltip.setToolTipText("Sets the tooltip that's displayed when you hover over the component or its label");
		TA_Tooltip.setWrapStyleWord(true);
		SP_Tooltip.setViewportView(TA_Tooltip);

		JLabel lblTooltipText = new JLabel("Tooltip Text");
		lblTooltipText.setBounds(280, 0, 111, 14);
		P_EditGame.add(lblTooltipText);

		SP_Action = new JScrollPane();
		SP_Action.setBounds(280, 74, 278, 45);
		P_EditGame.add(SP_Action);
		TA_Action = new JTextArea();
		TA_Action.setToolTipText("Sets advanced action settings of the component see help>components>actions for more details");
		TA_Action.setWrapStyleWord(true);
		SP_Action.setViewportView(TA_Action);

		JLabel lblAction = new JLabel("Action");
		lblAction.setBounds(280, 60, 74, 14);
		P_EditGame.add(lblAction);

		TF_Name = new JTextField();
		TF_Name.setBounds(20, 143, 109, 20);
		P_EditGame.add(TF_Name);
		TF_Name.setColumns(10);

		JLabel lblComponentName = new JLabel("Component Name");
		lblComponentName.setBounds(20, 129, 129, 14);
		P_EditGame.add(lblComponentName);

		TF_Grouping = new JTextField();
		TF_Grouping.setToolTipText("Sets the grouping of the radio button");
		TF_Grouping.setBounds(210, 143, 86, 20);
		P_EditGame.add(TF_Grouping);
		TF_Grouping.setColumns(10);

		lblGrouping = new JLabel("Grouping");
		lblGrouping.setBounds(210, 129, 65, 14);
		P_EditGame.add(lblGrouping);

		JButton B_Save = new JButton("Save");
		B_Save.setToolTipText("Save the component(saving is also done automatically when you change tabs)");
		B_Save.setBounds(510, 142, 89, 23);
		P_EditGame.add(B_Save);

		JButton B_Delete = new JButton("Delete");
		B_Delete.setToolTipText("Deletes the selected component");
		B_Delete.setBounds(411, 142, 89, 23);
		P_EditGame.add(B_Delete);
		
		JLabel lblDefault = new JLabel("Default");
		lblDefault.setBounds(214, 60, 61, 16);
		P_EditGame.add(lblDefault);
		
		TF_Default = new JTextField();
		TF_Default.setBounds(215, 82, 60, 28);
		P_EditGame.add(TF_Default);
		TF_Default.setColumns(10);
		
		CB_Default = new JCheckBox("T/F");
		CB_Default.setBounds(215, 82, 60, 28);
		P_EditGame.add(CB_Default);
		
		Sp_Default = new JSpinner();
		Sp_Default.setBounds(215, 82, 60, 28);
		P_EditGame.add(Sp_Default);


		//action listeners at the end so that everything will be initiallized.
		//creates new component based on type retrieved by dialogue box
		B_NComponent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String[] possibilities = {""+ComType.TextField, ""+ComType.Button, ""+ComType.CheckBox,""+ComType.ComboBox,""+ComType.RadioButton,""+ComType.ToggleButton,""+ComType.TextArea,
						""+ComType.TextPane,""+ComType.Spinner,""+ComType.List,""+ComType.Slider,""+ComType.Label};
				String s = (String)JOptionPane.showInputDialog(
						(Component)B_NComponent,
						(Object)"Select the type of component \nyou want to create",
						"New Component",
						JOptionPane.PLAIN_MESSAGE,
						new ImageIcon(),
						possibilities,
						"ham");
				if(s!=null){
					addComponent(ComType.getType(s));
				}
			}
		});
		//changes all values based on which component is selected
		CB_ComName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(CB_ComName.getSelectedIndex()!=-1){
					ComType type=var.getGameComponents().get(CB_ComName.getSelectedIndex()).getType();
					setValues(type);
					setDisplay(type);
					setPresetEnables(var.getGameComponents().get(CB_ComName.getSelectedIndex()).getName());
					CB_ComName.updateUI();
				}
			}
		});
		
		//deletes the selected component
		B_Delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {//removes component based on index 
				int index=CB_ComName.getSelectedIndex();
				gameComponents.remove(CB_ComName.getSelectedIndex());
				var.getGameComponents().remove(index);
				if(index==0){//sets index of the combo box to the empty -1 value, to avoid glitches
					CB_ComName.setSelectedIndex(index-1);
					setDisplay(ComType.Null);
					setValues(ComType.Null);
					reset();
				}
				else{
					CB_ComName.setSelectedIndex(index-1);
					reset();
				}
			}
		});
		//saves the current data by using saveGameComponents method in var where the components are stored
		B_Save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				var.saveGameComponents();
			}
		});
		


		//changes location based on value change
		Sp_X.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				try {
					if((int)Sp_X.getValue()>=0){
						var.getGameComponents().get(CB_ComName.getSelectedIndex()).getLocation().setX((int)Sp_X.getValue());
					}
					else if((int)Sp_X.getValue()<0){
						Sp_X.setValue(0);
					}
					Sp_X.setToolTipText(""+Sp_X.getValue());
				} catch (Exception e) {
				}
			}
		});
		Sp_Y.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				try {
					if((int)Sp_Y.getValue()>=0){
						var.getGameComponents().get(CB_ComName.getSelectedIndex()).getLocation().setY((int)Sp_Y.getValue());
					}
					else if((int)Sp_Y.getValue()<0){
						Sp_Y.setValue(0);
					}
					Sp_Y.setToolTipText(""+Sp_Y.getValue());
				} catch (Exception e) {
				}
			}
		});
		Sp_H.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				try {
					if((int)Sp_H.getValue()>=0){
						var.getGameComponents().get(CB_ComName.getSelectedIndex()).getLocation().setH((int)Sp_H.getValue());
					}
					else if((int)Sp_H.getValue()<0){
						Sp_H.setValue(0);
					}
					Sp_H.setToolTipText(""+Sp_H.getValue());
				} catch (Exception e) {
				}
			}
		});
		Sp_W.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				try {
					if((int)Sp_W.getValue()>=0){
						var.getGameComponents().get(CB_ComName.getSelectedIndex()).getLocation().setW((int)Sp_W.getValue());
					}
					else if((int)Sp_W.getValue()<0){
						Sp_W.setValue(0);
					}
					Sp_W.setToolTipText(""+Sp_W.getValue());
				} catch (Exception e) {
				}
			}
		});
		//changes name when focus is lost(assuming user has finished typing and has moved out of component)
		TF_Name.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				if(TF_Name.getText()!=""&&CB_ComName.getSelectedIndex()>=0){
					String name =checkExisting(TF_Name.getText(),CB_ComName.getSelectedIndex());
					int index=CB_ComName.getSelectedIndex();
					gameComponents.set(index, name);
					var.getGameComponents().get(index).setName(name);
					CB_ComName.setSelectedIndex(index-1);//negative as it is possible to have index at -1 when it is zero
					CB_ComName.setSelectedIndex(index);//done to quickly swap between items so that new name is updated and displayed
					TF_Name.setToolTipText(name);
					TF_Name.setText(name);
				}
			}
		});
		//does the same thing when enter is pressed
		TF_Name.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(TF_Name.getText()!=""&&CB_ComName.getSelectedIndex()>=0){
					String name =checkExisting(TF_Name.getText(),CB_ComName.getSelectedIndex());
					int index=CB_ComName.getSelectedIndex();
					gameComponents.set(index, name);
					var.getGameComponents().get(index).setName(name);
					CB_ComName.setSelectedIndex(index-1);//negative as it is possible to have index at -1 when it is zero
					CB_ComName.setSelectedIndex(index);//done to quickly swap between items so that new name is updated and displayed
					TF_Name.setToolTipText(name);
					TF_Name.setText(name);
				}
			}
		});
		//sets value for grouping textfield
		TF_Grouping.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if(TF_Grouping.getText()!=""&&CB_ComName.getSelectedIndex()>=0){
					var.getGameComponents().get(CB_ComName.getSelectedIndex()).setGrouping(TF_Grouping.getText());
				}
			}
		});
		//same thing when enter is pressed
		TF_Grouping.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(TF_Grouping.getText()!=""&&CB_ComName.getSelectedIndex()>=0){
					var.getGameComponents().get(CB_ComName.getSelectedIndex()).setGrouping(TF_Grouping.getText());
				}
			}
		});
		
		
		//Changes tooltip when focus lost(which assumes that person is finished typing)
		TA_Tooltip.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				if(CB_ComName.getSelectedIndex()>=0){
				var.getGameComponents().get(CB_ComName.getSelectedIndex()).setTooltip(TA_Tooltip.getText());
				}
			}
		});
		//changes action commands when focus is lost
		TA_Action.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				if(CB_ComName.getSelectedIndex()>=0){
					var.getGameComponents().get(CB_ComName.getSelectedIndex()).setAction(TA_Action.getText());
				}
			}
		});
		
		//change listeners to make changes when min/max/interval spinners are changed
		Sp_Max.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				try {
					if((int)Sp_Max.getValue()<(int)Sp_Min.getValue()){
						Sp_Max.setValue(Sp_Min.getValue());
					}
					var.getGameComponents().get(CB_ComName.getSelectedIndex()).setMax((int)Sp_Max.getValue());
					Sp_Max.setToolTipText(""+Sp_Max.getValue());
				} catch (Exception e) {
				}
			}
		});
		Sp_Min.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				try {
					if((int)Sp_Min.getValue()>(int)Sp_Max.getValue()){
						Sp_Min.setValue(Sp_Max.getValue());
					}
					var.getGameComponents().get(CB_ComName.getSelectedIndex()).setMin((int)Sp_Min.getValue());
					Sp_Min.setToolTipText(""+Sp_Min.getValue());
				} catch (Exception e1) {}
			}
		});
		Sp_Interval.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if((int)Sp_Interval.getValue()<0){
					Sp_Interval.setValue(0);
				}
				if((int)Sp_Interval.getValue()>=0){
					var.getGameComponents().get(CB_ComName.getSelectedIndex()).setInterval((int)Sp_Interval.getValue());
				}
				Sp_Interval.setToolTipText(""+Sp_Interval.getValue());
			}
		});
		//sets defaults
		TF_Default.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!TF_Default.getText().equals("")){
					var.getGameComponents().get(CB_ComName.getSelectedIndex()).setDefaultVar(TF_Default.getText());
				}
			}
		});
		TF_Default.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if(!TF_Default.getText().equals("")){
					var.getGameComponents().get(CB_ComName.getSelectedIndex()).setDefaultVar(TF_Default.getText());
				}
			}
		});
		Sp_Default.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				var.getGameComponents().get(CB_ComName.getSelectedIndex()).setDefaultVar(""+Sp_Default.getValue());
			}
		});
		CB_Default.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (CB_ComName.getSelectedIndex()>-1) {
					ComType type = var.getGameComponents().get(CB_ComName.getSelectedIndex()).getType();
					if (type==ComType.CheckBox || type==ComType.RadioButton || type==ComType.ToggleButton) {
						var.getGameComponents().get(CB_ComName.getSelectedIndex())
								.setDefaultVar("" + CB_Default.isSelected());
					} 
				}
			}
		});
		
	}
	//checks if the default name for component(component type+number) is used, if not it will save as that otherwise it will continue searching
	public String checkExisting(String type){
		boolean noMatch=false;//boolean to check to make sure that there are no components that already have the same default names
		int num = 0;//number added to end of default names to differentiate
		while(!noMatch){//continues if a match has been found with the current name
			num++;
			noMatch=true;
			for(int i=0;i<gameComponents.size();i++){//checks if current numbering of default name exists
				if(gameComponents.get(i).equals(type+num)){
					noMatch=false;//set noMatch at false continuing search, 
				}
			}
		}
		return ""+type+num;
	}
	//similar to first one but has index to avoid checking against itself
	public String checkExisting(String type,int currentIndex){
		boolean noMatch=false;//boolean to check to make sure that there are no components that already have the same default names
		int num = 0;//number added to end of default names to differentiate
		while(!noMatch){//continues if a match has been found with the current name
			noMatch=true;
			num++;
			for(int i=0;i<gameComponents.size();i++){//checks if current numbering of default name exists
				if(i!=currentIndex){//to make sure that it isn't checking against itself
					if(gameComponents.get(i).equals(type)&&num==1){//first case if name is present
						System.out.println("firstCase");
						noMatch=false;
					}
					if(gameComponents.get(i).equals(type+num)&&num>1){//secound case if numbered name(from other checks) is present
						System.out.println("secound case");
						noMatch=false;//set noMatch at false continuing search,

					}
				}
				//Outside checking of current index as it must always be checked(case of only 1 component)
				if(Components.isPresetName(type)&&num==1){//third case if name is the same as preset names, cannot be so and number is added
					System.out.println("third case");
					noMatch=false;
				}
				
			}
		}
		if(num>1){
		return ""+type+num;
		}
		else{
			return type;
		}
	}
	//sets which parts are enabled based on type
	public void setDisplay(ComType type){//TODO convert to aquiring a list from components and enabling using variables from there.(mainly for presets)
		//Enable things that may be disabled from loading a preset
		TF_Name.setEnabled(true);
		TA_Action.setEnabled(true);
		if(type==ComType.Spinner||type==ComType.Slider){
			Sp_Max.setEnabled(true);
			Sp_Min.setEnabled(true);
			lblMax.setEnabled(true);
			lblMin.setEnabled(true);
			Sp_Max.setVisible(true);
			Sp_Min.setVisible(true);;
			lblMax.setVisible(true);
			lblMin.setVisible(true);
			}
		else{
			Sp_Max.setEnabled(false);
			Sp_Min.setEnabled(false);
			lblMax.setEnabled(false);
			lblMin.setEnabled(false);
			Sp_Max.setVisible(false);
			Sp_Min.setVisible(false);
			lblMax.setVisible(false);
			lblMin.setVisible(false);
		}
		if (type==ComType.Slider){
			Sp_Interval.setEnabled(true);
			lblInterval.setEnabled(true);
			Sp_Interval.setVisible(true);
			lblInterval.setVisible(true);

		}
		else{
			Sp_Interval.setEnabled(false);
			lblInterval.setEnabled(false);
			Sp_Interval.setVisible(false);
			lblInterval.setVisible(false);
			
		}
		if(type==ComType.RadioButton||type==ComType.ToggleButton){
			TF_Grouping.setEnabled(true);
			lblGrouping.setEnabled(true);
			TF_Grouping.setVisible(true);
			lblGrouping.setVisible(true);
		}
		else{
			TF_Grouping.setEnabled(false);
			lblGrouping.setEnabled(false);
			TF_Grouping.setVisible(false);
			lblGrouping.setVisible(false);
		}
		if(type==ComType.TextField||type==ComType.TextArea||type==ComType.TextPane||type==ComType.List){
			TF_Default.setEnabled(true);
			TF_Default.setVisible(true);
		}
		else{
			TF_Default.setEnabled(false);
			TF_Default.setVisible(false);
		}
		if(type==ComType.ComboBox||type==ComType.Spinner||type==ComType.Slider){
			Sp_Default.setEnabled(true);
			Sp_Default.setVisible(true);
		}
		else{
			Sp_Default.setEnabled(false);
			Sp_Default.setVisible(false);
		}
		if(type==ComType.CheckBox||type==ComType.RadioButton||type==ComType.ToggleButton){
			CB_Default.setEnabled(true);
			CB_Default.setVisible(true);
		}
		else{
			CB_Default.setEnabled(false);
			CB_Default.setVisible(false);
		}
	}
	public void setPresetEnables(String presetName){
		//TODO complete
		if(Components.isPresetName(presetName)){
			TF_Name.setEnabled(false);//Names aren't editable so people can't mess with the presets system
		}
	}
	public void setValues(ComType type){
		if(type!=ComType.Null){//null denotes that there's no element
			TF_Name.setText(var.getGameComponents().get(CB_ComName.getSelectedIndex()).getName());
			Sp_X.setValue(var.getGameComponents().get(CB_ComName.getSelectedIndex()).getLocation().getX());
			Sp_Y.setValue(var.getGameComponents().get(CB_ComName.getSelectedIndex()).getLocation().getY());
			Sp_H.setValue(var.getGameComponents().get(CB_ComName.getSelectedIndex()).getLocation().getH());
			Sp_W.setValue(var.getGameComponents().get(CB_ComName.getSelectedIndex()).getLocation().getW());
			TA_Tooltip.setText(var.getGameComponents().get(CB_ComName.getSelectedIndex()).getTooltip());
			TA_Action.setText(var.getGameComponents().get(CB_ComName.getSelectedIndex()).getAction());
		}
		if (type==ComType.Spinner||type==ComType.Slider){
			Sp_Max.setValue(var.getGameComponents().get(CB_ComName.getSelectedIndex()).getMax());
			Sp_Min.setValue(var.getGameComponents().get(CB_ComName.getSelectedIndex()).getMin());
			try{
			Sp_Default.setValue(var.getGameComponents().get(CB_ComName.getSelectedIndex()).getDefaultVar());
			}
			catch(Exception e){
				Sp_Default.setValue(0);
			}
		}
		if(type==ComType.Slider){
			Sp_Interval.setValue(var.getGameComponents().get(CB_ComName.getSelectedIndex()).getInterval());
		}
		if(type==ComType.RadioButton||type==ComType.ToggleButton){
			TF_Grouping.setText(var.getGameComponents().get(CB_ComName.getSelectedIndex()).getGrouping());
		}
		if(type==ComType.CheckBox||type==ComType.RadioButton||type==ComType.ToggleButton){
			if(var.getGameComponents().get(CB_ComName.getSelectedIndex()).getDefaultVar().equals("true")){
				CB_Default.setSelected(true);
			}
			else{
				CB_Default.setSelected(false);
			}
		}
		if(type==ComType.Spinner||type==ComType.Slider||type==ComType.ComboBox||type==ComType.List){
			try{
				Sp_Default.setValue(var.getGameComponents().get(CB_ComName.getSelectedIndex()).getDefaultVar());
				}
				catch(Exception e){
					Sp_Default.setValue(0);
				}
		}
		if(type==ComType.TextField||type==ComType.TextArea||type==ComType.TextArea){
			TF_Default.setText(var.getGameComponents().get(CB_ComName.getSelectedIndex()).getDefaultVar());
		}
	}
	public void reset(){
		gameComponents.clear();
		CB_ComName.setSelectedIndex(-1);
		TF_Name.setText("");//resets everything if it's reseting from reset button
		TF_Grouping.setText("");
		Sp_X.setValue(0);
		Sp_Y.setValue(0);
		Sp_H.setValue(0);
		Sp_W.setValue(0);
		Sp_Max.setValue(0);
		Sp_Min.setValue(0);
		Sp_Interval.setValue(0);
		TA_Tooltip.setText("");
		TA_Action.setText("");
		for(int i=0;i<var.getGameComponents().size();i++){
			gameComponents.add(var.getGameComponents().get(i).getName());//sets the names of the new components if it's reseting from opening
			System.out.println("added the name"+var.getGameComponents().get(i).getName()+" to index"+i);
		}
	}
	public void addComponent(ComType type){
		String name=checkExisting(""+type);
		gameComponents.add(name);
		var.getGameComponents().add(new Components(name,type,new Location(0,0,0,0),"0:0:0:default",name));//adds the new component to the ArrayList in editvar with basic data
		var.getOutput().append("Added new "+type+"\n");
		setDisplay(type);
		CB_ComName.updateUI();
		CB_ComName.setSelectedIndex(gameComponents.size()-1);
	}
	public void addPreset(String name){
		boolean alreadyExist=false;
		for(int i=0;i<gameComponents.size();i++){
			if(gameComponents.get(i).equals(name)){
				alreadyExist=true;
			}
		}
		if(!alreadyExist){//To check if the preset was already created or not
			addComponent(Components.presetType(name));
			TF_Name.setText(name);
			int index=CB_ComName.getSelectedIndex();
			gameComponents.set(index, name);
			var.getGameComponents().get(index).setName(name);
			CB_ComName.setSelectedIndex(index-1);//negative as it is possible to have index at -1 when it is zero
			CB_ComName.setSelectedIndex(index);//done to quickly swap between items so that new name is updated and displayed
			TF_Name.setToolTipText(name);
			setPresetEnables(name);
			TA_Action.setText("<preset>"+name+"</preset>");
			var.getGameComponents().get(index).setAction("<preset>"+name+"</preset>");
		}
	}
	public void setLocation(Location l){
		if(CB_ComName.getSelectedIndex()>-1){
			Sp_X.setValue(l.getX());
			Sp_Y.setValue(l.getY());
			Sp_W.setValue(l.getW());
			Sp_H.setValue(l.getH());
		}
	}
	public JPanel getP_EditGame(){
		return P_EditGame;
	}
}
