package scouterEdit;

import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.event.ChangeListener;

import global.Components;

import javax.swing.event.ChangeEvent;
import javax.swing.JSpinner;

public class ScoreEdit extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3941490067584696778L;

	private EditVar var;
	
	private Vector<String> scoreFields=new Vector<String>();
	private JTextField TF_Name;
	private JTextArea TA_Tooltip;
	private JCheckBox CB_Add;
	private final JSpinner Sp_Width;
	private final JComboBox<String> CB_ScoreField;
	/**
	 * Create the panel.
	 */
	public ScoreEdit(final EditVar var) {
		this.var=var;
		setLayout(null);
		
		CB_ScoreField = new JComboBox<String>();
		CB_ScoreField.setModel(new DefaultComboBoxModel<String>(scoreFields));
		CB_ScoreField.setBounds(6, 18, 125, 27);
		add(CB_ScoreField);
		
		JLabel lblFields = new JLabel("Fields");
		lblFields.setBounds(6, 6, 61, 16);
		add(lblFields);
		
		TF_Name = new JTextField();
		TF_Name.setBounds(6, 139, 134, 28);
		add(TF_Name);
		TF_Name.setColumns(10);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(6, 125, 61, 16);
		add(lblName);
		
		JScrollPane SP_Tooltip = new JScrollPane();
		SP_Tooltip.setBounds(157, 51, 182, 91);
		add(SP_Tooltip);
		
		TA_Tooltip = new JTextArea();
		SP_Tooltip.setViewportView(TA_Tooltip);
		
		JLabel lblTooltip = new JLabel("Tooltip");
		lblTooltip.setBounds(156, 29, 61, 16);
		add(lblTooltip);
		
		JButton B_New = new JButton("New Field");
		B_New.setBounds(16, 57, 117, 29);
		add(B_New);
		
		CB_Add = new JCheckBox("Add To Total Score?");
		CB_Add.setBounds(155, 144, 184, 23);
		add(CB_Add);
		
		Sp_Width = new JSpinner();
		Sp_Width.setBounds(351, 47, 76, 28);
		add(Sp_Width);
		
		JLabel lblWidth = new JLabel("Width");
		lblWidth.setBounds(351, 22, 61, 16);
		add(lblWidth);
		
		//Action listeners after everythign so everything's initiallized
		CB_ScoreField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(CB_ScoreField.getSelectedIndex()>-1){
					setValues();
				}
			}
		});
		TF_Name.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index=CB_ScoreField.getSelectedIndex();
				if(index>-1){
					String name=checkExisting(TF_Name.getText(),index);
					var.getScoreFields().get(index).setName(name);
					scoreFields.set(index, name);
					CB_ScoreField.setSelectedIndex(index-1);
					CB_ScoreField.setSelectedIndex(index);
				}
			}
		});
		TF_Name.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				int index=CB_ScoreField.getSelectedIndex();
				if(index>-1){
					String name=checkExisting(TF_Name.getText(),index);
					var.getScoreFields().get(index).setName(name);
					scoreFields.set(index, name);
					CB_ScoreField.setSelectedIndex(index-1);
					CB_ScoreField.setSelectedIndex(index);
				}
			}
		});
		TA_Tooltip.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				int index=CB_ScoreField.getSelectedIndex();
				if(index>-1){
					var.getScoreFields().get(index).setTooltip(TA_Tooltip.getText());
				}
			}
		});
		CB_Add.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				int index=CB_ScoreField.getSelectedIndex();
				if(index>-1){
					var.getScoreFields().get(index).setAddToScore(CB_Add.isSelected());
				}
			}
		});
		Sp_Width.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				int index=CB_ScoreField.getSelectedIndex();
				if(index>-1){
					var.getScoreFields().get(index).setWidth((int)Sp_Width.getValue());
				}
			}
		});
		B_New.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int num=0;
				String name="";
				boolean noMatch=false;//sets false for no duplicate
				while(!noMatch){
					noMatch=true;
					num++;
					for(int i=0;i<scoreFields.size();i++){
						if(scoreFields.get(i).equals("Field"+num)){//sees if there's any component with matching name
							noMatch=false;
						}
					}
				}
				name="Field"+num;
				scoreFields.add(name);
				var.getScoreFields().add(new ScoreField(name));
				CB_ScoreField.setSelectedIndex(scoreFields.size()-2);
				CB_ScoreField.setSelectedIndex(scoreFields.size()-1);
			}
		});
		
		
		
	}
	public String checkExisting(String type,int currentIndex){
		boolean noMatch=false;//boolean to check to make sure that there are no components that already have the same default names
		int num = 0;//number added to end of default names to differentiate
		while(!noMatch){//continues if a match has been found with the current name
			noMatch=true;
			num++;
			for(int i=0;i<scoreFields.size();i++){//checks if current numbering of default name exists
				if(i!=currentIndex){//to make sure that it isn't checking against itself
					if(scoreFields.get(i).equals(type)&&num==1){//first case if name is present
						System.out.println("firstCase");
						noMatch=false;
					}
					if(scoreFields.get(i).equals(type+num)&&num>1){//secound case if numbered name(from other checks) is present
						System.out.println("secound case");
						noMatch=false;//set noMatch at false continuing search,

					}
				}
				//Outside checking of current index as it must always be checked(case of only 1 component)
				if(Components.isPresetName(type)&&num==1){//third case if name is the same as preset names, cannot be so and number is added
					System.out.println("third case");//TODO change to use scoreFields
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
	public void setValues(){
		TF_Name.setText(var.getScoreFields().get(CB_ScoreField.getSelectedIndex()).getName());
		TA_Tooltip.setText(var.getScoreFields().get(CB_ScoreField.getSelectedIndex()).getTooltip());
		CB_Add.setSelected(var.getScoreFields().get(CB_ScoreField.getSelectedIndex()).getAddToScore());
		Sp_Width.setValue(var.getScoreFields().get(CB_ScoreField.getSelectedIndex()).getWidth());
	}
	public void reset(){
		scoreFields.clear();
		CB_ScoreField.setSelectedIndex(-1);
		TA_Tooltip.setText("");
		TF_Name.setText("");
		Sp_Width.setValue(0);
		for(int i=0;i<var.getScoreFields().size();i++){
			scoreFields.add(var.getScoreFields().get(i).getName());
			System.out.println("added scorefield"+var.getScoreFields().get(i).getName());
		}
	}
	public JPanel getP_EditScore(){
		return this;
	}
}
