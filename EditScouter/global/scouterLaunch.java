package global;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;

import data.dataMain;
import scouter.scoutMain;

import java.awt.GridBagLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Component;
import javax.swing.Box;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.event.CaretListener;
import javax.swing.event.CaretEvent;

public class scouterLaunch extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3501552635343287938L;
	private JPanel contentPane;
	private final JFileChooser fc=new JFileChooser();
	private scouterLaunch launch;
	private JTextField TF_Name;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					scouterLaunch frame = new scouterLaunch();
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
	public scouterLaunch() {
		launch=this;
		//setting the file chooser
		fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fc.setFileFilter(new FileFilter()
        {
           @Override
           public boolean accept(File file)
           {
              return file.getName().toUpperCase().endsWith(".TXT");
           }

           @Override
           public String getDescription()
           {
              return ".txt files";
           }
        });
		
		
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 469, 196);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblScouterName = new JLabel("Scouter Name");
		GridBagConstraints gbc_lblScouterName = new GridBagConstraints();
		gbc_lblScouterName.insets = new Insets(0, 0, 5, 5);
		gbc_lblScouterName.gridx = 2;
		gbc_lblScouterName.gridy = 0;
		contentPane.add(lblScouterName, gbc_lblScouterName);
		
		Component VS_FillVertical = Box.createVerticalGlue();
		GridBagConstraints gbc_VS_FillVertical = new GridBagConstraints();
		gbc_VS_FillVertical.weighty = 2.0;
		gbc_VS_FillVertical.insets = new Insets(0, 0, 5, 5);
		gbc_VS_FillVertical.gridx = 0;
		gbc_VS_FillVertical.gridy = 1;
		contentPane.add(VS_FillVertical, gbc_VS_FillVertical);
		
		TF_Name = new JTextField();
		GridBagConstraints gbc_TF_Name = new GridBagConstraints();
		gbc_TF_Name.insets = new Insets(0, 0, 5, 5);
		gbc_TF_Name.fill = GridBagConstraints.HORIZONTAL;
		gbc_TF_Name.gridx = 2;
		gbc_TF_Name.gridy = 1;
		contentPane.add(TF_Name, gbc_TF_Name);
		TF_Name.setColumns(10);
		
		final JButton B_Launch = new JButton("Launch Scouter");
		B_Launch.setEnabled(false);
		GridBagConstraints gbc_B_Launch = new GridBagConstraints();
		gbc_B_Launch.weightx = 1.0;
		gbc_B_Launch.insets = new Insets(0, 0, 5, 5);
		gbc_B_Launch.gridx = 1;
		gbc_B_Launch.gridy = 3;
		contentPane.add(B_Launch, gbc_B_Launch);
		
		JButton B_Data = new JButton("Launch Data Viewer");
		GridBagConstraints gbc_B_Data = new GridBagConstraints();
		gbc_B_Data.weightx = 1.0;
		gbc_B_Data.insets = new Insets(0, 0, 5, 5);
		gbc_B_Data.gridx = 2;
		gbc_B_Data.gridy = 3;
		contentPane.add(B_Data, gbc_B_Data);
		
		JButton B_Close = new JButton("Close");
		GridBagConstraints gbc_B_Close = new GridBagConstraints();
		gbc_B_Close.weightx = 1.0;
		gbc_B_Close.insets = new Insets(0, 0, 5, 5);
		gbc_B_Close.gridx = 3;
		gbc_B_Close.gridy = 3;
		contentPane.add(B_Close, gbc_B_Close);
		
		Component HS_LeftB = Box.createHorizontalStrut(20);
		GridBagConstraints gbc_HS_LeftB = new GridBagConstraints();
		gbc_HS_LeftB.insets = new Insets(0, 0, 5, 0);
		gbc_HS_LeftB.gridx = 4;
		gbc_HS_LeftB.gridy = 3;
		contentPane.add(HS_LeftB, gbc_HS_LeftB);
		
		Component VS_Bottom = Box.createVerticalStrut(20);
		GridBagConstraints gbc_VS_Bottom = new GridBagConstraints();
		gbc_VS_Bottom.insets = new Insets(0, 0, 0, 5);
		gbc_VS_Bottom.gridx = 0;
		gbc_VS_Bottom.gridy = 4;
		contentPane.add(VS_Bottom, gbc_VS_Bottom);
		
		Component HS_RightB = Box.createHorizontalStrut(20);
		GridBagConstraints gbc_HS_RightB = new GridBagConstraints();
		gbc_HS_RightB.insets = new Insets(0, 0, 5, 5);
		gbc_HS_RightB.gridx = 0;
		gbc_HS_RightB.gridy = 3;
		contentPane.add(HS_RightB, gbc_HS_RightB);
		
		TF_Name.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				if(TF_Name.getText().equals("")){
					B_Launch.setEnabled(false);
				}
				else{
					B_Launch.setEnabled(true);
				}
			}
		});
		
		//Action Listeners after everything so everything's initallized
		B_Launch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int returnVal= fc.showOpenDialog(B_Launch);
				if(returnVal==JFileChooser.APPROVE_OPTION){//if approved
					File file=fc.getSelectedFile();
					String fileData="";
					try{
					      BufferedReader in = new BufferedReader(new FileReader(file));
					      String str;
					      while((str=in.readLine())!=null){
					    	  fileData+=str;
					      }
					      in.close();
					    }
					    catch(IOException s){
					      s.printStackTrace();
					      System.out.println("Error reading file to open for editor");
					    }
					if(!fileData.equals("")){//a simple check to see if a text file has been read
						scoutMain scout = new scoutMain(fileData);//opens window, passes on file data that is read
						scout.setVisible(true);
						scout.setScouter(TF_Name.getText());
						launch.dispose();//close the window
					}
				}
			}
		});
		B_Data.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int returnVal= fc.showOpenDialog(B_Launch);
				if(returnVal==JFileChooser.APPROVE_OPTION){//if approved
					File file=fc.getSelectedFile();
					String fileData="";
					try{
					      BufferedReader in = new BufferedReader(new FileReader(file));
					      String str;
					      while((str=in.readLine())!=null){
					    	  fileData+=str;
					      }
					      in.close();
					    }
					    catch(IOException s){
					      s.printStackTrace();
					      System.out.println("Error reading file to open for editor");
					    }
					if(!fileData.equals("")){//a simple check to see if a text file has been read
						dataMain data=new dataMain(fileData);
						data.setVisible(true);
						launch.dispose();//close the window
					}
				}
			}
		});
		
		B_Close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				launch.dispose();
			}
		});
	}

}
