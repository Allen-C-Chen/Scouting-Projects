package data;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class dataScore extends JPanel {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 6880389546223194877L;
	
	private dataVar var;
	private JTable T_Scores;
	private Scorem scorem;
	private final JButton B_Red;
	private final JButton B_Blue;
	/**
	 * Create the panel.
	 */
	public dataScore(final dataVar var) {
		this.var=var;
		scorem=new Scorem(var.getScoreFields());
		GridBagLayout gridBagLayout = new GridBagLayout();
		setLayout(gridBagLayout);
		
		JButton B_Update = new JButton("Update Scores");
		GridBagConstraints gbc_B_Update = new GridBagConstraints();
		gbc_B_Update.weightx = 1.0;
		gbc_B_Update.insets = new Insets(0, 0, 5, 5);
		gbc_B_Update.gridx = 0;
		gbc_B_Update.gridy = 1;
		add(B_Update, gbc_B_Update);
		
		B_Red = new JButton("Red Scores");
		B_Red.setEnabled(false);
		GridBagConstraints gbc_B_Red = new GridBagConstraints();
		gbc_B_Red.weightx = 1.0;
		gbc_B_Red.insets = new Insets(0, 0, 5, 0);
		gbc_B_Red.gridx = 1;
		gbc_B_Red.gridy = 0;
		add(B_Red, gbc_B_Red);
		
		B_Blue = new JButton("Blue Scores");
		B_Blue.setEnabled(false);
		GridBagConstraints gbc_B_Blue = new GridBagConstraints();
		gbc_B_Blue.weightx = 1.0;
		gbc_B_Blue.insets = new Insets(0, 0, 5, 0);
		gbc_B_Blue.gridx = 1;
		gbc_B_Blue.gridy = 1;
		add(B_Blue, gbc_B_Blue);
		
		JScrollPane Sp_Scores = new JScrollPane();
		GridBagConstraints gbc_Sp_Scores = new GridBagConstraints();
		gbc_Sp_Scores.weighty = 10.0;
		gbc_Sp_Scores.fill = GridBagConstraints.BOTH;
		gbc_Sp_Scores.gridwidth = 2;
		gbc_Sp_Scores.insets = new Insets(0, 0, 0, 5);
		gbc_Sp_Scores.gridx = 0;
		gbc_Sp_Scores.gridy = 2;
		add(Sp_Scores, gbc_Sp_Scores);
		
		T_Scores = new JTable(){
			/**
			 * 
			 */
			private static final long serialVersionUID = 2535621L;

			public boolean getScrollableTracksViewportWidth(){
				return getPreferredSize().width < getParent().getWidth();
			}
		};
		T_Scores.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		T_Scores.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		T_Scores.setCellSelectionEnabled(true);
		T_Scores.setModel(scorem);
		T_Scores.updateUI();
		Sp_Scores.setViewportView(T_Scores);
		T_Scores.getColumnModel().getColumn(0).setPreferredWidth(50);
		T_Scores.getColumnModel().getColumn(1).setPreferredWidth(50);
		T_Scores.getColumnModel().getColumn(2).setPreferredWidth(50);
		T_Scores.getColumnModel().getColumn(3).setPreferredWidth(50);
		T_Scores.getColumnModel().getColumn(4).setPreferredWidth(50);
		T_Scores.getColumnModel().getColumn(5).setPreferredWidth(50);
		T_Scores.getColumnModel().getColumn(6).setPreferredWidth(50);
		T_Scores.getColumnModel().getColumn(7).setPreferredWidth(50);
		for(int i=0;i<var.getScoreFields().size();i++){
			if(var.getScoreFields().get(i).getWidth()!=0){
				T_Scores.getColumnModel().getColumn(i+8).setPreferredWidth(var.getScoreFields().get(i).getWidth());
			}
		}
		
		
		
		B_Update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				scorem.clear();
				try {
					for(int i=0;i<var.getMatches().size();i++){
						scorem.addRow(var.getMatches().get(i), var.getMatchNum().get(i));
					}
				} catch (Exception e1) {}//in the case that it goes over the amount// additional matches searched
				T_Scores.updateUI();
				B_Blue.setEnabled(true);
				B_Red.setEnabled(false);
			}
			
		});
		
		B_Blue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					for(int i=0;i<var.getMatches().size();i++){
						scorem.changeScores(var.getMatches().get(i).getBScore(), i);
					}
				} catch (Exception e1) {}//in the case that it goes over the amount// additional matches searched
				T_Scores.updateUI();
				B_Red.setEnabled(true);
				B_Blue.setEnabled(false);
			}
		});
		B_Red.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					for(int i=0;i<var.getMatches().size();i++){
						scorem.changeScores(var.getMatches().get(i).getRScore(), i);
					}
				} catch (Exception e1) {}//in the case that it goes over the amount// additional matches searched
				T_Scores.updateUI();
				B_Red.setEnabled(false);
				B_Blue.setEnabled(true);
			}
			
		});
	}
	public void reset(){
		scorem.clear();
		T_Scores.updateUI();
		B_Red.setEnabled(false);
		B_Blue.setEnabled(false);
	}
	
	public JPanel getP_Score(){
		return this;
	}

}
