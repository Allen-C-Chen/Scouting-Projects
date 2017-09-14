package global;
/*
 * A simple debug class that displays a text area containing various debug information depending on what's put into it
 * Call method addTextData(String data,String name) to add a new panel
 * name is what's displayed in the combo box
 * data is what's displayed in the text area
 * Call method clear();
 * to clear all loaded data.
 * Primary purpose is to display debug information in an organized way that would otherwise be difficult to see otherwise
 */
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JTextArea;
import java.awt.GridBagConstraints;
import javax.swing.JButton;
import java.awt.Insets;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class Debug_Vars extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1978297915873426734L;
	private JPanel contentPane;
	private final JFrame debug;
	private Vector<String> names;
	private ArrayList<String> data;
	private final JComboBox<String> CB_Select;
	private final Debug_Vars debugger;
	private final JTextArea TA_Display;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Debug_Vars frame = new Debug_Vars();
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
	public Debug_Vars() {
		debugger=this;
		names=new Vector<String>();
		data=new ArrayList<String>();
		debug=this;
		setTitle("Debug");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 621, 303);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);

		Component HG_R = Box.createHorizontalGlue();
		GridBagConstraints gbc_HG_R = new GridBagConstraints();
		gbc_HG_R.weightx = 2.0;
		gbc_HG_R.insets = new Insets(0, 0, 5, 5);
		gbc_HG_R.gridx = 1;
		gbc_HG_R.gridy = 0;
		contentPane.add(HG_R, gbc_HG_R);
		
		CB_Select = new JComboBox<String>();
		CB_Select.setModel(new DefaultComboBoxModel<String>(names));
		GridBagConstraints gbc_CB_Select = new GridBagConstraints();
		gbc_CB_Select.gridwidth = 2;
		gbc_CB_Select.fill = GridBagConstraints.HORIZONTAL;
		gbc_CB_Select.anchor = GridBagConstraints.BASELINE;
		gbc_CB_Select.weightx = 0.5;
		gbc_CB_Select.insets = new Insets(0, 0, 5, 5);
		gbc_CB_Select.gridx = 2;
		gbc_CB_Select.gridy = 0;
		contentPane.add(CB_Select, gbc_CB_Select);

		Component HG_L = Box.createHorizontalGlue();
		GridBagConstraints gbc_HG_L = new GridBagConstraints();
		gbc_HG_L.weightx = 2.0;
		gbc_HG_L.insets = new Insets(0, 0, 5, 5);
		gbc_HG_L.gridx = 4;
		gbc_HG_L.gridy = 0;
		contentPane.add(HG_L, gbc_HG_L);

		Component HS_R = Box.createHorizontalStrut(20);
		GridBagConstraints gbc_HS_R = new GridBagConstraints();
		gbc_HS_R.insets = new Insets(0, 0, 5, 5);
		gbc_HS_R.gridx = 0;
		gbc_HS_R.gridy = 1;
		contentPane.add(HS_R, gbc_HS_R);
		
		JScrollPane SP_Display = new JScrollPane();
		GridBagConstraints gbc_SP_Display = new GridBagConstraints();
		gbc_SP_Display.fill = GridBagConstraints.BOTH;
		gbc_SP_Display.gridwidth = 4;
		gbc_SP_Display.insets = new Insets(0, 0, 5, 5);
		gbc_SP_Display.gridx = 1;
		gbc_SP_Display.gridy = 1;
		contentPane.add(SP_Display, gbc_SP_Display);

		TA_Display = new JTextArea();
		SP_Display.setViewportView(TA_Display);
		TA_Display.setEditable(false);

		Component HS_L = Box.createHorizontalStrut(20);
		GridBagConstraints gbc_HS_L = new GridBagConstraints();
		gbc_HS_L.insets = new Insets(0, 0, 5, 0);
		gbc_HS_L.gridx = 5;
		gbc_HS_L.gridy = 1;
		contentPane.add(HS_L, gbc_HS_L);

		JButton B_Close = new JButton("close");
		GridBagConstraints gbc_B_Close = new GridBagConstraints();
		gbc_B_Close.weightx = 0.5;
		gbc_B_Close.insets = new Insets(0, 0, 0, 5);
		gbc_B_Close.gridx = 2;
		gbc_B_Close.gridy = 2;
		contentPane.add(B_Close, gbc_B_Close);
		
		JButton btnClear = new JButton("Clear");
		GridBagConstraints gbc_btnClear = new GridBagConstraints();
		gbc_btnClear.weightx = 0.5;
		gbc_btnClear.insets = new Insets(0, 0, 0, 5);
		gbc_btnClear.gridx = 3;
		gbc_btnClear.gridy = 2;
		contentPane.add(btnClear, gbc_btnClear);
		//action listeners
		B_Close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				debug.setVisible(false);
				debug.dispose();
			}
		});
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				debugger.reset();
			}
		});
		
		CB_Select.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index=CB_Select.getSelectedIndex();
				if(index>-1){
					TA_Display.setText(data.get(index));
				}
			}
		});

	}
	public void addData(String names,String data){
		this.names.add(checkExisting(names));
		this.data.add(data);
		if(CB_Select.getSelectedIndex()>-1){
			int index=CB_Select.getSelectedIndex();
			CB_Select.setSelectedIndex(index-1);
			CB_Select.setSelectedIndex(index);
		}
	}
	public String checkExisting(String name){
		boolean noMatch=false;//boolean to check to make sure that there are no components that already have the same default names
		int num = 0;//number added to end of default names to differentiate
		while(!noMatch){//continues if a match has been found with the current name
			num++;
			noMatch=true;
			for(int i=0;i<names.size();i++){//checks if current numbering of default name exists
				if(names.get(i).equals(name)&&num==1){//only checks on the first run to see if the name exists
					noMatch=false;//set noMatch at false continuing search, 
				}
				else if(names.get(i).equals(name+num)){//following checks to see if the assigned numbers are taken
					noMatch=false;
				}
			}
			
			
		}
		if(num<=1){
			return name;
		}
		else{
			return name+num;
		}
	}
	public void reset(){
		names.clear();
		data.clear();
		CB_Select.setSelectedIndex(-1);
		TA_Display.setText("");
	}
	public Debug_Vars start(){

		return this;
	}

}
