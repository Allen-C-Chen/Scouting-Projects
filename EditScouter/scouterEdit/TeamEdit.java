package scouterEdit;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.GridLayout;
import javax.swing.JSpinner;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class TeamEdit extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4903504000375039878L;
	private final EditVar var;
	
	private Vector<String> teamComponents= new Vector<String>();
	private JTextField TF_FieldName;
	private final JComboBox<String> CB_FieldName;
	private JLabel lblField;
	private final JTextArea TA_ToolTip;
	private final JSpinner Sp_ColWidth;
	private final JButton B_Model;
	//radio buttons
	private JRadioButton RB_Text;
	private JRadioButton RB_Check;
	private JRadioButton RB_Num;
	private JRadioButton RB_Combo;
	
	/**
	 * Create the panel.
	 */
	public TeamEdit(final EditVar var) {
		this.var=var;
		setLayout(null);
		
		CB_FieldName=new JComboBox<String>();
		CB_FieldName.setModel(new DefaultComboBoxModel<String>(teamComponents));
		CB_FieldName.setBounds(10, 14, 139, 20);
		this.add(CB_FieldName);
		
		lblField = new JLabel("Field");
		lblField.setBounds(10, 0, 74, 14);
		this.add(lblField);
		
		TF_FieldName = new JTextField();
		TF_FieldName.setBounds(10, 127, 134, 28);
		add(TF_FieldName);
		TF_FieldName.setColumns(10);
		
		JLabel lblFieldName = new JLabel("Field Name");
		lblFieldName.setBounds(13, 110, 87, 16);
		add(lblFieldName);
		
		JPanel P_Type = new JPanel();
		P_Type.setBorder(new LineBorder(new Color(0, 0, 0)));
		P_Type.setBounds(417, 40, 156, 124);
		add(P_Type);
		
		final JButton B_NField = new JButton("New Field");
		B_NField.setToolTipText("Create new field");
		B_NField.setBounds(159, 13, 111, 23);
		this.add(B_NField);
		P_Type.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblType = new JLabel("Type");
		P_Type.add(lblType);
		
		RB_Text = new JRadioButton("Text");
		P_Type.add(RB_Text);
		
		RB_Check = new JRadioButton("CheckBox");
		P_Type.add(RB_Check);
		
		RB_Num = new JRadioButton("Numeric");
		P_Type.add(RB_Num);

		RB_Combo = new JRadioButton("Combo Box");
		P_Type.add(RB_Combo);
		//sets button group for the radio button and adds the buttons
		ButtonGroup type= new ButtonGroup();
		type.add(RB_Text);
		type.add(RB_Check);
		type.add(RB_Num);
		type.add(RB_Combo);
		//adds the action commands for use by the action listener
		RB_Text.setActionCommand("TEXT");
		RB_Check.setActionCommand("CHECK");
		RB_Num.setActionCommand("NUM");
		RB_Combo.setActionCommand("COMBO");
		
		
		JScrollPane SP_ToolTip = new JScrollPane();
		SP_ToolTip.setBounds(169, 59, 205, 91);
		add(SP_ToolTip);
		
		TA_ToolTip = new JTextArea();
		SP_ToolTip.setViewportView(TA_ToolTip);
		TA_ToolTip.setWrapStyleWord(true);
		
		JLabel lblTooltip = new JLabel("Tooltip/Description");
		lblTooltip.setBounds(169, 40, 156, 16);
		add(lblTooltip);
		
		JButton B_Delete = new JButton("Delete");
		B_Delete.setBounds(10, 43, 117, 29);
		add(B_Delete);//TODO add action listener
		
		Sp_ColWidth = new JSpinner();
		Sp_ColWidth.setBounds(20, 84, 107, 28);
		add(Sp_ColWidth);
		
		JLabel lblColumnWidth = new JLabel("Column Width");
		lblColumnWidth.setBounds(20, 68, 107, 16);
		add(lblColumnWidth);
		
		B_Model = new JButton("Set Model");
		B_Model.setVisible(false);
		B_Model.setBounds(306, 10, 117, 29);
		add(B_Model);
		
		
		
		//adds type to the field ArrayList
		ActionListener listen=new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(CB_FieldName.getSelectedIndex()>=0){
					if(e.getActionCommand().equals("COMBO")){
						B_Model.setVisible(true);
					}
					else{
						B_Model.setVisible(false);
					}
					var.getTeamFields().get(CB_FieldName.getSelectedIndex()).setType(e.getActionCommand());
				}
				
			}
		};
		RB_Text.addActionListener(listen);
		RB_Check.addActionListener(listen);
		RB_Num.addActionListener(listen);
		RB_Combo.addActionListener(listen);	
		
		//action listeners
		
		//adds name to the fields ArrayList
		TF_FieldName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index=CB_FieldName.getSelectedIndex();//outside cause if it's inside the if statement for some reason the index is always -1
				if(CB_FieldName.getSelectedIndex()>-1){//only does something if there are fields created
					var.getTeamFields().get(CB_FieldName.getSelectedIndex()).setName(TF_FieldName.getText());//changes save data
					teamComponents.set(CB_FieldName.getSelectedIndex(), TF_FieldName.getText());//changes display data in combobo
					CB_FieldName.setSelectedIndex(index-1);
					CB_FieldName.setSelectedIndex(index);
				}
			}
		});
		//same for when focus is lost
		TF_FieldName.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				int index=CB_FieldName.getSelectedIndex();//outside cause if it's inside the if statement for some reason the index is always -1
				if(CB_FieldName.getSelectedIndex()>-1){//only does something if there are fields created
					String name=checkExisting(TF_FieldName.getText(),CB_FieldName.getSelectedIndex());
					var.getTeamFields().get(CB_FieldName.getSelectedIndex()).setName(name);//changes save data
					teamComponents.set(CB_FieldName.getSelectedIndex(), name);//changes displaydata in combobox
					CB_FieldName.setSelectedIndex(index-1);
					CB_FieldName.setSelectedIndex(index);
				}
			}
		});
		TA_ToolTip.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if(CB_FieldName.getSelectedIndex()>-1){
					if(!TA_ToolTip.getText().equals("")){
						var.getTeamFields().get(CB_FieldName.getSelectedIndex()).setTooltip(TA_ToolTip.getText());
					}
				}
			}
		});
		Sp_ColWidth.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if(CB_FieldName.getSelectedIndex()>-1){
					try {
						var.getTeamFields().get(CB_FieldName.getSelectedIndex()).setColWidth((int)Sp_ColWidth.getValue());
					} catch (Exception e1) {}
				}
			}
		});
		
		//adds a new component//see GameEdit for notes
		B_NField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int num=0;
				String name="";
				boolean noMatch=false;//sets false for no duplicate
				while(!noMatch){
					noMatch=true;
					num++;
					for(int i=0;i<teamComponents.size();i++){
						if(teamComponents.get(i).equals("Field"+num)){//sees if there's any component with matching name
							noMatch=false;
						}
					}
				}
				name="Field"+num;
				teamComponents.add(name);
				var.getTeamFields().add(new TeamField(name));
				System.out.println(teamComponents.size());
				CB_FieldName.setSelectedIndex(teamComponents.size()-2);
				CB_FieldName.setSelectedIndex(teamComponents.size()-1);
				System.out.println(CB_FieldName.getSelectedIndex());
				
			}
		});
		B_Delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(CB_FieldName.getSelectedIndex()>-1){
					var.getTeamFields().remove(CB_FieldName.getSelectedIndex());
					reset();
				}
			}
		});
		B_Model.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(CB_FieldName.getSelectedIndex()>-1){
					TeamModeld model= new TeamModeld(var.getTeamFields().get(CB_FieldName.getSelectedIndex()));
					model.setVisible(true);
				}
			}
		});
		
		CB_FieldName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(CB_FieldName.getSelectedIndex()>-1){
					setValues();
				}
			}
		});
		
		
	}
	public String checkExisting(String type,int currentIndex){
		boolean noMatch=false;//boolean to check to make sure that there are no components that already have the same default names
		int num = 0;//number added to end of default names to differentiate
		while(!noMatch){//continues if a match has been found with the current name
			noMatch=true;
			num++;
			for(int i=0;i<teamComponents.size();i++){//checks if current numbering of default name exists
				if(i!=currentIndex){//to make sure that it isn't checking against itself
					if(teamComponents.get(i).equals(type)&&num==1){//first case if name is present
						System.out.println("firstCase");
						noMatch=false;
					}
					if(teamComponents.get(i).equals(type+num)&&num>1){//secound case if numbered name(from other checks) is present
						System.out.println("secound case");
						noMatch=false;//set noMatch at false continuing search,

					}
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
	public void setValues(){
		TF_FieldName.setText(var.getTeamFields().get(CB_FieldName.getSelectedIndex()).getName());
		String type=var.getTeamFields().get(CB_FieldName.getSelectedIndex()).getType();
		B_Model.setVisible(false);
		if(type.equals("TEXT")){
			RB_Text.setSelected(true);
		}
		else if(type.equals("NUM")){
			RB_Num.setSelected(true);
		}
		else if(type.equals("CHECK")){
			RB_Check.setSelected(true);
		}
		else if(type.equals("COMBO")){
			B_Model.setVisible(true);
			RB_Combo.setSelected(true);
		}
		else{
			RB_Text.setSelected(true);
		}
		TA_ToolTip.setText(var.getTeamFields().get(CB_FieldName.getSelectedIndex()).getTooltip());
		Sp_ColWidth.setValue(var.getTeamFields().get(CB_FieldName.getSelectedIndex()).getColWidth());
	}
	public void reset(){
		teamComponents.clear();
		CB_FieldName.setSelectedIndex(-1);
		RB_Text.setSelected(true);//clears everything if it's resetting from reset button
		TA_ToolTip.setText("");
		TF_FieldName.setText("");
		B_Model.setVisible(false);
		for(int i=0;i<var.getTeamFields().size();i++){
			teamComponents.add(var.getTeamFields().get(i).getName());//sets name of the team fields if it's resetting from openning
			System.out.println("added the field"+var.getTeamFields().get(i).getName()+" to index"+i);
		}
		
	}
	public TeamEdit getP_EditTeam(){
		return this;
	}
}
