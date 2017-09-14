package scouter;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import scouterEdit.ScoreField;

import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JSpinner;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class scoutScore extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3491429637959235525L;
	private JSpinner Sp_RTotal;
	private JSpinner Sp_BTotal;
	private JButton B_Save;
	private ArrayList<JSpinner> RScores=new ArrayList<JSpinner>();
	private ArrayList<JSpinner> BScores=new ArrayList<JSpinner>();

	/**
	 * Create the panel.
	 */
	public scoutScore(final scoutVar var) {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, 1.0, 0.0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0};
		setLayout(gridBagLayout);
		
		//sets the basic total score components
		JLabel lblRedTotalScore = new JLabel("Red Total Score");
		lblRedTotalScore.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblRedTotalScore = new GridBagConstraints();
		gbc_lblRedTotalScore.weightx = 1.0;
		gbc_lblRedTotalScore.weighty = 0.5;
		gbc_lblRedTotalScore.insets = new Insets(0, 0, 5, 5);
		gbc_lblRedTotalScore.gridx = 0;
		gbc_lblRedTotalScore.gridy = 0;
		add(lblRedTotalScore, gbc_lblRedTotalScore);
		
		JLabel lblBlueTotalScore = new JLabel("Blue Total Score");
		lblBlueTotalScore.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblBlueTotalScore = new GridBagConstraints();
		gbc_lblBlueTotalScore.weightx = 1.0;
		gbc_lblBlueTotalScore.weighty = 0.5;
		gbc_lblBlueTotalScore.insets = new Insets(0, 0, 5, 0);
		gbc_lblBlueTotalScore.gridx = 3;
		gbc_lblBlueTotalScore.gridy = 0;
		add(lblBlueTotalScore, gbc_lblBlueTotalScore);
		
		Sp_RTotal = new JSpinner();
		Sp_RTotal.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				var.setRScore(0,(int)Sp_RTotal.getValue());
			}
		});
		GridBagConstraints gbc_Sp_RTotal = new GridBagConstraints();
		gbc_Sp_RTotal.weightx = 1.0;
		gbc_Sp_RTotal.insets = new Insets(0, 0, 5, 5);
		gbc_Sp_RTotal.fill = GridBagConstraints.HORIZONTAL;
		gbc_Sp_RTotal.gridx = 0;
		gbc_Sp_RTotal.gridy = 1;
		add(Sp_RTotal, gbc_Sp_RTotal);
		
		Sp_BTotal = new JSpinner();
		Sp_BTotal.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				var.setBScore(0, (int)Sp_BTotal.getValue());
			}
		});
		GridBagConstraints gbc_Sp_BTotal = new GridBagConstraints();
		gbc_Sp_BTotal.weightx = 1.0;
		gbc_Sp_BTotal.insets = new Insets(0, 0, 5, 0);
		gbc_Sp_BTotal.fill = GridBagConstraints.HORIZONTAL;
		gbc_Sp_BTotal.gridx = 3;
		gbc_Sp_BTotal.gridy = 1;
		add(Sp_BTotal, gbc_Sp_BTotal);
		
		ArrayList<ScoreField> scoring=var.getScoreFields();
		
		for(int i=1;i<scoring.size();i++){
			final int index=i;
			if(i%2==1){
				//sets the score spinners that are located in the middle
				JLabel lblR=new JLabel("Red "+scoring.get(i).getName());
				lblR.setHorizontalAlignment(SwingConstants.CENTER);
				lblR.setToolTipText(scoring.get(i).getTooltip());
				GridBagConstraints gbc_lblR=new GridBagConstraints();
				gbc_lblR.weightx=1.0;
				gbc_lblR.weighty=0.5;
				gbc_lblR.fill=GridBagConstraints.HORIZONTAL;
				gbc_lblR.gridx=1;
				gbc_lblR.gridy=i-1;
				add(lblR,gbc_lblR);
				
				final JSpinner Sp_RScore= new JSpinner();
				Sp_RScore.setToolTipText(scoring.get(i).getTooltip());
				GridBagConstraints gbc_R=new GridBagConstraints();
				gbc_R.weightx=1.0;
				gbc_R.weighty=1.0;
				gbc_R.insets=new Insets(0, 0, 5, 0);
				gbc_R.fill=GridBagConstraints.HORIZONTAL;
				gbc_R.gridx=1;
				gbc_R.gridy=i;
				add(Sp_RScore,gbc_R);
				RScores.add(Sp_RScore);
				
				JLabel lblB=new JLabel("Blue "+scoring.get(i).getName());
				lblB.setHorizontalAlignment(SwingConstants.CENTER);
				lblB.setToolTipText(scoring.get(i).getTooltip());
				GridBagConstraints gbc_lblB=new GridBagConstraints();
				gbc_lblB.weightx=1.0;
				gbc_lblB.weighty=0.5;
				gbc_lblB.fill=GridBagConstraints.HORIZONTAL;
				gbc_lblB.gridx=2;
				gbc_lblB.gridy=i-1;
				add(lblB,gbc_lblB);
				
				final JSpinner Sp_BScore= new JSpinner();
				Sp_BScore.setToolTipText(scoring.get(i).getTooltip());
				GridBagConstraints gbc_B=new GridBagConstraints();
				gbc_B.weightx=1.0;
				gbc_B.weighty=1.0;
				gbc_B.insets=new Insets(0, 0, 5, 0);
				gbc_B.fill=GridBagConstraints.HORIZONTAL;
				gbc_B.gridx=2;
				gbc_B.gridy=i;
				add(Sp_BScore,gbc_B);
				BScores.add(Sp_BScore);
				
				Sp_RScore.addChangeListener(new ChangeListener() {
					public void stateChanged(ChangeEvent e) {
						var.setRScore(index, (int)Sp_RScore.getValue());
					}
				});
				Sp_BScore.addChangeListener(new ChangeListener() {
					public void stateChanged(ChangeEvent e) {
						var.setBScore(index, (int)Sp_BScore.getValue());
					}
				});
				
			}
			else{
					//sets score spinners that are located on the sides
					JLabel lblR=new JLabel("Red "+scoring.get(i).getName());
					lblR.setHorizontalAlignment(SwingConstants.CENTER);
					GridBagConstraints gbc_lblR=new GridBagConstraints();
					gbc_lblR.weightx=1.0;
					gbc_lblR.weighty=0.5;
					gbc_lblR.fill=GridBagConstraints.HORIZONTAL;
					gbc_lblR.gridx=0;
					gbc_lblR.gridy=i;
					add(lblR,gbc_lblR);
					
					final JSpinner Sp_RScore= new JSpinner();
					GridBagConstraints gbc_R=new GridBagConstraints();
					gbc_R.weightx=1.0;
					gbc_R.weighty=1.0;
					gbc_R.insets=new Insets(0, 0, 5, 0);
					gbc_R.fill=GridBagConstraints.HORIZONTAL;
					gbc_R.gridx=0;
					gbc_R.gridy=i+1;
					add(Sp_RScore,gbc_R);
					RScores.add(Sp_RScore);
					
					JLabel lblB=new JLabel("Blue "+scoring.get(i).getName());
					lblB.setHorizontalAlignment(SwingConstants.CENTER);
					GridBagConstraints gbc_lblB=new GridBagConstraints();
					gbc_lblB.weightx=1.0;
					gbc_lblB.weighty=0.5;
					gbc_lblB.fill=GridBagConstraints.HORIZONTAL;
					gbc_lblB.gridx=3;
					gbc_lblB.gridy=i;
					add(lblB,gbc_lblB);
					
					final JSpinner Sp_BScore= new JSpinner();
					GridBagConstraints gbc_B=new GridBagConstraints();
					gbc_B.weightx=1.0;
					gbc_B.weighty=1.0;
					gbc_B.insets=new Insets(0, 0, 5, 0);
					gbc_B.fill=GridBagConstraints.HORIZONTAL;
					gbc_B.gridx=3;
					gbc_B.gridy=i+1;
					add(Sp_BScore,gbc_B);
					BScores.add(Sp_BScore);
					
					Sp_RScore.addChangeListener(new ChangeListener() {
						public void stateChanged(ChangeEvent e) {
							var.setRScore(index, (int)Sp_RScore.getValue());
						}
					});
					Sp_BScore.addChangeListener(new ChangeListener() {
						public void stateChanged(ChangeEvent e) {
							var.setBScore(index, (int)Sp_BScore.getValue());
						}
					});
			}
		}
		//save button is moved based on how many components there are
		B_Save = new JButton("Save Game");
		GridBagConstraints gbc_B_Save = new GridBagConstraints();
		gbc_B_Save.gridwidth = 2;
		gbc_B_Save.insets = new Insets(0, 0, 0, 5);
		gbc_B_Save.gridx = 1;
		gbc_B_Save.gridy = var.getScoreFields().size();
		add(B_Save, gbc_B_Save);
		
		B_Save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				var.saveMatch(false);
			}
		});
		//mouse event used to sync scores from score Fields variable in scout var when mouse moves in(as that must be done to make changes)
		//use to sync score changes made in game panel
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				Sp_RTotal.setValue(var.getScoreFields().get(0).getRValue());
				Sp_BTotal.setValue(var.getScoreFields().get(0).getBValue());
				for(int i=1;i<var.getScoreFields().size();i++){
					RScores.get(i-1).setValue(var.getScoreFields().get(i).getRValue());
					BScores.get(i-1).setValue(var.getScoreFields().get(i).getBValue());
				}
			}
		});
	}
	public JPanel getP_Score(){
		return this;
	}
	public void reset(){
		Sp_RTotal.setValue(0);
		Sp_BTotal.setValue(0);
		B_Save.setEnabled(true);
		for(int i=0;i<RScores.size();i++){
			RScores.get(i).setValue(0);
		}
		for(int i=0;i<BScores.size();i++){
			BScores.get(i).setValue(0);
		}	
	}
}
