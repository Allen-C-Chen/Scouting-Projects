package scouterEdit;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TeamModeld extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2906400802348690495L;
	private final JPanel contentPanel = new JPanel();
	private final JTextArea TA_Model;
	private JDialog dialog;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			TeamModeld dialog = new TeamModeld(new TeamField("test"));
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public TeamModeld(final TeamField tf) {
		dialog=this;
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{1, 0};
		gbl_contentPanel.rowHeights = new int[]{16, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JScrollPane SP_Model = new JScrollPane();
			GridBagConstraints gbc_SP_Model = new GridBagConstraints();
			gbc_SP_Model.weighty = 1.0;
			gbc_SP_Model.weightx = 1.0;
			gbc_SP_Model.fill = GridBagConstraints.BOTH;
			gbc_SP_Model.gridx = 0;
			gbc_SP_Model.gridy = 0;
			contentPanel.add(SP_Model, gbc_SP_Model);
			{
				TA_Model = new JTextArea();
				SP_Model.setViewportView(TA_Model);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(!TA_Model.getText().equals("")){
							String[] model= TA_Model.getText().split("\n");
							String modeler="";
							for(String s:model){
								modeler+=s+",";
							}
							modeler= modeler.substring(0, modeler.length()-1);
							tf.setModel(modeler);
						}
						dialog.dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dialog.dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
