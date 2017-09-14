package scouterEdit;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class WindowEdit extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5855907052065596672L;
	private final EditVar var;
	private JPanel P_EditWindow;
	private JTextField TF_Title;
	private JCheckBox CB_Output;
	private JSpinner Sp_OutH;
	private final JSpinner Sp_X;
	private final JSpinner Sp_Y;
	private final JSpinner Sp_W;
	private final JSpinner Sp_H;
	public WindowEdit(final EditVar var) {
		this.var=var;
		P_EditWindow=new JPanel();
		P_EditWindow.setLayout(null);
		
		TF_Title = new JTextField();
		TF_Title.setToolTipText("Sets the name for the heading of the ");
		TF_Title.setBounds(207, 132, 86, 20);
		P_EditWindow.add(TF_Title);
		TF_Title.setColumns(10);
		
		Sp_X = new JSpinner();
		Sp_X.setToolTipText("Sets the x value of the upper left corner of the component in pixels");
		Sp_X.setBounds(45, 83, 60, 20);
		P_EditWindow.add(Sp_X);
		
		Sp_Y = new JSpinner();
		Sp_Y.setToolTipText("Sets the y value of the upper left corner of the component in pixels");
		Sp_Y.setBounds(105, 83, 60, 20);
		P_EditWindow.add(Sp_Y);
		
		Sp_W = new JSpinner();
		Sp_W.setToolTipText("Sets the width of the component in pixels");
		Sp_W.setBounds(45, 132, 60, 20);
		P_EditWindow.add(Sp_W);
		
		Sp_H = new JSpinner();
		Sp_H.setToolTipText("Sets the height of the component in pixels");
		Sp_H.setBounds(105, 132, 60, 20);
		P_EditWindow.add(Sp_H);
		
		JLabel lblX = new JLabel("x");
		lblX.setToolTipText("Sets the x value of the upper left corner of the component in pixels");
		lblX.setBounds(46, 65, 46, 14);
		P_EditWindow.add(lblX);
		
		JLabel lblY = new JLabel("y");
		lblY.setToolTipText("Sets the y value of the upper left corner of the component in pixels");
		lblY.setBounds(106, 65, 46, 14);
		P_EditWindow.add(lblY);
		
		JLabel lblHeight = new JLabel("Height");
		lblHeight.setToolTipText("Sets the height of the component in pixels");
		lblHeight.setBounds(106, 120, 46, 14);
		P_EditWindow.add(lblHeight);
		
		JLabel lblWidth = new JLabel("Width");
		lblWidth.setToolTipText("Sets the width of the component in pixels");
		lblWidth.setBounds(46, 120, 46, 14);
		P_EditWindow.add(lblWidth);
		
		JLabel lblTitle = new JLabel("Title");
		lblTitle.setToolTipText("Sets the name for the heading of the ");
		lblTitle.setBounds(207, 120, 46, 14);
		P_EditWindow.add(lblTitle);
		
		CB_Output = new JCheckBox("Output Information");
		CB_Output.setToolTipText("Whether the console output boxs is included when program launches under the main part");
		CB_Output.setBounds(333, 92, 124, 23);
		P_EditWindow.add(CB_Output);
		
		Sp_OutH = new JSpinner();
		Sp_OutH.setEnabled(false);
		Sp_OutH.setBounds(333, 132, 72, 20);
		P_EditWindow.add(Sp_OutH);
		
		JLabel lblOutputBoxHeight = new JLabel("Output Box height");
		lblOutputBoxHeight.setToolTipText("Sets the height in pixels");
		lblOutputBoxHeight.setBounds(329, 120, 128, 14);
		P_EditWindow.add(lblOutputBoxHeight);
		
		JLabel lblNewLabel = new JLabel("Window Size");
		lblNewLabel.setBounds(46, 37, 94, 16);
		P_EditWindow.add(lblNewLabel);
		//event listeners at the end so that all variables are initiallized
		//sets output box whether it is displayed or not
		CB_Output.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if(CB_Output.isSelected()){
					Sp_OutH.setEnabled(true);
				}
				else{
					Sp_OutH.setEnabled(false);
				}
				var.setBooleanOutput(CB_Output.isSelected());
			}
		});
		//sets value of output box height,length determined by length of window
		Sp_OutH.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if((int)Sp_OutH.getValue()>=0){
					var.setOutH((int)Sp_OutH.getValue());
				}
				else if((int)Sp_OutH.getValue()<0){
					Sp_OutH.setValue(0);
				}
				Sp_OutH.setToolTipText(""+Sp_OutH.getValue());
			}
		});
		
		//sets the location variables
		Sp_X.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if((int)Sp_X.getValue()>=0){
					var.getWinLocation().setX((int)Sp_X.getValue());
				}
				else if((int)Sp_X.getValue()<0){
					Sp_X.setValue(0);
				}
				Sp_X.setToolTipText(""+Sp_X.getValue());
			}
		});
		Sp_Y.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if((int)Sp_Y.getValue()>=0){
					var.getWinLocation().setY((int)Sp_Y.getValue());
				}
				else if((int)Sp_Y.getValue()<0){
					Sp_Y.setValue(0);
				}
				Sp_Y.setToolTipText(""+Sp_Y.getValue());
			}
		});
		Sp_W.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if((int)Sp_W.getValue()>=0){
					var.getWinLocation().setW((int)Sp_W.getValue());
				}
				else if((int)Sp_W.getValue()<0){
					Sp_W.setValue(0);
				}
				Sp_W.setToolTipText(""+Sp_W.getValue());
			}
		});
		Sp_H.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if((int)Sp_H.getValue()>=0){
					var.getWinLocation().setH((int)Sp_H.getValue());
				}
				else if((int)Sp_H.getValue()<0){
					Sp_H.setValue(0);
				}
				Sp_H.setToolTipText(""+Sp_H.getValue());
			}
		});
		//Listeners for the title of the window
		TF_Title.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(TF_Title.getText()!=""){
					var.setWinTitle(TF_Title.getText());
				}
				TF_Title.setToolTipText(""+TF_Title.getText());
			}
		});
		TF_Title.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if(TF_Title.getText()!=""){
					var.setWinTitle(TF_Title.getText());
				}
				TF_Title.setToolTipText(""+TF_Title.getText());
			}
		});

		

	}
	public void reset(){//just resets everything to values from var cause they are reset there
		CB_Output.setSelected(var.getBooleanOutput());
		TF_Title.setText(var.getWinTitle());
		Sp_OutH.setEnabled(CB_Output.isSelected());
		Sp_OutH.setValue(var.getOutH());
		Sp_X.setValue(var.getWinLocation().getX());
		Sp_Y.setValue(var.getWinLocation().getY());
		Sp_W.setValue(var.getWinLocation().getW());
		Sp_H.setValue(var.getWinLocation().getH());
	}
	public JPanel getP_EditWindow(){
		return P_EditWindow;
	}
}
