package scouterEdit;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import global.Location;

import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class GameHelper extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2414457507788056285L;
	private JPanel contentPane;
	private JTextField TF_X;
	private JTextField TF_Y;
	private JTextField TF_W;
	private JTextField TF_H;
	private GameDraw draw= new GameDraw();//-20/-98.5/1.012

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameHelper frame = new GameHelper();
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
	public GameHelper() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 550, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JPanel P_Draw = draw.getPanel();
		GridBagConstraints gbc_P_Draw = new GridBagConstraints();
		gbc_P_Draw.weighty = 100.0;
		gbc_P_Draw.insets = new Insets(0, 0, 5, 5);
		gbc_P_Draw.fill = GridBagConstraints.BOTH;
		gbc_P_Draw.gridx = 1;
		gbc_P_Draw.gridy = 0;
		contentPane.add(P_Draw, gbc_P_Draw);
		
		Component VS_R = Box.createVerticalStrut(20);
		GridBagConstraints gbc_VS_R = new GridBagConstraints();
		gbc_VS_R.insets = new Insets(0, 0, 0, 5);
		gbc_VS_R.gridx = 0;
		gbc_VS_R.gridy = 1;
		contentPane.add(VS_R, gbc_VS_R);
		
		JPanel P_Info = new JPanel();
		GridBagConstraints gbc_P_Info = new GridBagConstraints();
		gbc_P_Info.insets = new Insets(0, 0, 0, 5);
		gbc_P_Info.fill = GridBagConstraints.BOTH;
		gbc_P_Info.gridx = 1;
		gbc_P_Info.gridy = 1;
		contentPane.add(P_Info, gbc_P_Info);
		GridBagLayout gbl_P_Info = new GridBagLayout();
		gbl_P_Info.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gbl_P_Info.rowHeights = new int[]{0, 0, 0};
		gbl_P_Info.columnWeights = new double[]{1.0, 1.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_P_Info.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		P_Info.setLayout(gbl_P_Info);
		
		JLabel lblXywh = new JLabel("X/Y/W/H");
		GridBagConstraints gbc_lblXywh = new GridBagConstraints();
		gbc_lblXywh.weightx = 0.1;
		gbc_lblXywh.insets = new Insets(0, 0, 5, 5);
		gbc_lblXywh.anchor = GridBagConstraints.EAST;
		gbc_lblXywh.gridx = 0;
		gbc_lblXywh.gridy = 0;
		P_Info.add(lblXywh, gbc_lblXywh);
		
		TF_X = new JTextField();
		GridBagConstraints gbc_TF_X = new GridBagConstraints();
		gbc_TF_X.weightx = 1.0;
		gbc_TF_X.insets = new Insets(0, 0, 5, 5);
		gbc_TF_X.fill = GridBagConstraints.HORIZONTAL;
		gbc_TF_X.gridx = 1;
		gbc_TF_X.gridy = 0;
		P_Info.add(TF_X, gbc_TF_X);
		TF_X.setColumns(10);
		
		TF_Y = new JTextField();
		GridBagConstraints gbc_TF_Y = new GridBagConstraints();
		gbc_TF_Y.weightx = 1.0;
		gbc_TF_Y.insets = new Insets(0, 0, 5, 5);
		gbc_TF_Y.fill = GridBagConstraints.HORIZONTAL;
		gbc_TF_Y.gridx = 2;
		gbc_TF_Y.gridy = 0;
		P_Info.add(TF_Y, gbc_TF_Y);
		TF_Y.setColumns(10);
		
		TF_W = new JTextField();
		GridBagConstraints gbc_TF_W = new GridBagConstraints();
		gbc_TF_W.weightx = 1.0;
		gbc_TF_W.insets = new Insets(0, 0, 5, 5);
		gbc_TF_W.fill = GridBagConstraints.HORIZONTAL;
		gbc_TF_W.gridx = 3;
		gbc_TF_W.gridy = 0;
		P_Info.add(TF_W, gbc_TF_W);
		TF_W.setColumns(10);
		
		TF_H = new JTextField();
		GridBagConstraints gbc_TF_H = new GridBagConstraints();
		gbc_TF_H.weightx = 1.0;
		gbc_TF_H.insets = new Insets(0, 0, 5, 0);
		gbc_TF_H.fill = GridBagConstraints.HORIZONTAL;
		gbc_TF_H.gridx = 4;
		gbc_TF_H.gridy = 0;
		P_Info.add(TF_H, gbc_TF_H);
		TF_H.setColumns(10);
		
		JButton B_Save = new JButton("Save To Selected");
		GridBagConstraints gbc_B_Save = new GridBagConstraints();
		gbc_B_Save.insets = new Insets(0, 0, 0, 5);
		gbc_B_Save.gridx = 0;
		gbc_B_Save.gridy = 1;
		P_Info.add(B_Save, gbc_B_Save);
		
		Component VS_L = Box.createVerticalStrut(20);
		GridBagConstraints gbc_VS_L = new GridBagConstraints();
		gbc_VS_L.gridx = 2;
		gbc_VS_L.gridy = 1;
		contentPane.add(VS_L, gbc_VS_L);
		
		draw.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				Location l=draw.getRectLoc();
				TF_X.setText(""+l.getX());
				TF_Y.setText(""+l.getY());
				TF_W.setText(""+l.getW());
				TF_H.setText(""+l.getH());
			}
		});
		ActionListener updateRec= new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!((JTextField)e.getSource()).getText().equals("")){
					draw.setRectLoc(new Location(Integer.parseInt(TF_X.getText()),Integer.parseInt(TF_Y.getText()),Integer.parseInt(TF_W.getText()),
							Integer.parseInt(TF_H.getText())));
				}
			}

		};
		FocusAdapter focusRec=new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if(!((JTextField)e.getComponent()).getText().equals("")){
					draw.setRectLoc(new Location(Integer.parseInt(TF_X.getText()),Integer.parseInt(TF_Y.getText()),Integer.parseInt(TF_W.getText()),
							Integer.parseInt(TF_H.getText())));
				}
			}
		};
		TF_X.addActionListener(updateRec);
		TF_Y.addActionListener(updateRec);
		TF_W.addActionListener(updateRec);
		TF_H.addActionListener(updateRec);
		TF_X.addFocusListener(focusRec);
		TF_Y.addFocusListener(focusRec);
		TF_W.addFocusListener(focusRec);
		TF_H.addFocusListener(focusRec);

	}
	public GameHelper(final EditMain main,EditVar var) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);

		JPanel P_Draw = draw.getPanel();
		GridBagConstraints gbc_P_Draw = new GridBagConstraints();
		gbc_P_Draw.weighty = 100.0;
		gbc_P_Draw.insets = new Insets(0, 0, 5, 5);
		gbc_P_Draw.fill = GridBagConstraints.BOTH;
		gbc_P_Draw.gridx = 1;
		gbc_P_Draw.gridy = 0;
		contentPane.add(P_Draw, gbc_P_Draw);

		Component VS_R = Box.createVerticalStrut(20);
		GridBagConstraints gbc_VS_R = new GridBagConstraints();
		gbc_VS_R.insets = new Insets(0, 0, 0, 5);
		gbc_VS_R.gridx = 0;
		gbc_VS_R.gridy = 1;
		contentPane.add(VS_R, gbc_VS_R);

		JPanel P_Info = new JPanel();
		GridBagConstraints gbc_P_Info = new GridBagConstraints();
		gbc_P_Info.insets = new Insets(0, 0, 0, 5);
		gbc_P_Info.fill = GridBagConstraints.BOTH;
		gbc_P_Info.gridx = 1;
		gbc_P_Info.gridy = 1;
		contentPane.add(P_Info, gbc_P_Info);
		GridBagLayout gbl_P_Info = new GridBagLayout();
		gbl_P_Info.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gbl_P_Info.rowHeights = new int[]{0, 0, 0};
		gbl_P_Info.columnWeights = new double[]{1.0, 1.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_P_Info.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		P_Info.setLayout(gbl_P_Info);

		JLabel lblXywh = new JLabel("X/Y/W/H");
		GridBagConstraints gbc_lblXywh = new GridBagConstraints();
		gbc_lblXywh.weightx = 0.1;
		gbc_lblXywh.insets = new Insets(0, 0, 5, 5);
		gbc_lblXywh.anchor = GridBagConstraints.EAST;
		gbc_lblXywh.gridx = 0;
		gbc_lblXywh.gridy = 0;
		P_Info.add(lblXywh, gbc_lblXywh);

		TF_X = new JTextField();
		GridBagConstraints gbc_TF_X = new GridBagConstraints();
		gbc_TF_X.weightx = 1.0;
		gbc_TF_X.insets = new Insets(0, 0, 5, 5);
		gbc_TF_X.fill = GridBagConstraints.HORIZONTAL;
		gbc_TF_X.gridx = 1;
		gbc_TF_X.gridy = 0;
		P_Info.add(TF_X, gbc_TF_X);
		TF_X.setColumns(10);

		TF_Y = new JTextField();
		GridBagConstraints gbc_TF_Y = new GridBagConstraints();
		gbc_TF_Y.weightx = 1.0;
		gbc_TF_Y.insets = new Insets(0, 0, 5, 5);
		gbc_TF_Y.fill = GridBagConstraints.HORIZONTAL;
		gbc_TF_Y.gridx = 2;
		gbc_TF_Y.gridy = 0;
		P_Info.add(TF_Y, gbc_TF_Y);
		TF_Y.setColumns(10);

		TF_W = new JTextField();
		GridBagConstraints gbc_TF_W = new GridBagConstraints();
		gbc_TF_W.weightx = 1.0;
		gbc_TF_W.insets = new Insets(0, 0, 5, 5);
		gbc_TF_W.fill = GridBagConstraints.HORIZONTAL;
		gbc_TF_W.gridx = 3;
		gbc_TF_W.gridy = 0;
		P_Info.add(TF_W, gbc_TF_W);
		TF_W.setColumns(10);

		TF_H = new JTextField();
		GridBagConstraints gbc_TF_H = new GridBagConstraints();
		gbc_TF_H.weightx = 1.0;
		gbc_TF_H.insets = new Insets(0, 0, 5, 0);
		gbc_TF_H.fill = GridBagConstraints.HORIZONTAL;
		gbc_TF_H.gridx = 4;
		gbc_TF_H.gridy = 0;
		P_Info.add(TF_H, gbc_TF_H);
		TF_H.setColumns(10);

		JButton B_Save = new JButton("Save To Selected");
		GridBagConstraints gbc_B_Save = new GridBagConstraints();
		gbc_B_Save.insets = new Insets(0, 0, 0, 5);
		gbc_B_Save.gridx = 0;
		gbc_B_Save.gridy = 1;
		P_Info.add(B_Save, gbc_B_Save);

		Component VS_L = Box.createVerticalStrut(20);
		GridBagConstraints gbc_VS_L = new GridBagConstraints();
		gbc_VS_L.gridx = 2;
		gbc_VS_L.gridy = 1;
		contentPane.add(VS_L, gbc_VS_L);

		draw.setComponents(var.getGameComponents());
		draw.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				Location l=draw.getRectLoc();
				TF_X.setText(""+l.getX());
				TF_Y.setText(""+l.getY());
				TF_W.setText(""+l.getW());
				TF_H.setText(""+l.getH());
			}
		});
		ActionListener updateRec= new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!((JTextField)e.getSource()).getText().equals("")){
					draw.setRectLoc(new Location(Integer.parseInt(TF_X.getText()),Integer.parseInt(TF_Y.getText()),Integer.parseInt(TF_W.getText()),
							Integer.parseInt(TF_H.getText())));
				}
			}

		};
		FocusAdapter focusRec=new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if(!((JTextField)e.getComponent()).getText().equals("")){
					draw.setRectLoc(new Location(Integer.parseInt(TF_X.getText()),Integer.parseInt(TF_Y.getText()),Integer.parseInt(TF_W.getText()),
							Integer.parseInt(TF_H.getText())));
				}
			}
		};
		
		TF_X.addActionListener(updateRec);
		TF_Y.addActionListener(updateRec);
		TF_W.addActionListener(updateRec);
		TF_H.addActionListener(updateRec);
		TF_X.addFocusListener(focusRec);
		TF_Y.addFocusListener(focusRec);
		TF_W.addFocusListener(focusRec);
		TF_H.addFocusListener(focusRec);
		
		B_Save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				main.HelpGame(new Location(Integer.parseInt(TF_X.getText()),Integer.parseInt(TF_Y.getText()),Integer.parseInt(TF_W.getText()),
						Integer.parseInt(TF_H.getText())));
				draw.repaint();
			}
		});
		//sets the size of the window based on how large the game panel will be in the scouter
		if(var.getBooleanOutput()&&var.getOutH()>(var.getWinLocation().getY()-23)*.1489){
			System.out.println("using output");
			setSize(var.getWinLocation().getW()-31, (int)(var.getWinLocation().getH()*0.9158-((var.getOutH()+92.092)*0.9158)+98.5));
		}
		else{
			setSize(var.getWinLocation().getW()-26, (int)(var.getWinLocation().getH()*0.9158+15.16));
		}
		
	}
}
