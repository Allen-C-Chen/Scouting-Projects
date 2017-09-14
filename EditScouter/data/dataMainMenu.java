package data;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.filechooser.FileFilter;

import global.HelpFrame;
import scouter.scoutMain;

public class dataMainMenu extends JMenuBar {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5971347435364084668L;
	private final JFileChooser fc=new JFileChooser();
	
	public dataMainMenu(final dataMain main, final dataVar var){
		

		
		JMenu M_File= new JMenu("File");
		add(M_File);
		
		final JMenuItem MI_Open= new JMenuItem("Open");
		M_File.add(MI_Open);
		
		JMenuItem MI_Reset= new JMenuItem("Reset");
		M_File.add(MI_Reset);
		
		JMenuItem MI_Close= new JMenuItem("Close");
		M_File.add(MI_Close);
		
		final JMenuItem MI_Directory=new JMenuItem("Set Directory");
		M_File.add(MI_Directory);
		
		JMenuItem MI_Scout= new JMenuItem("Open Scouter");
		M_File.add(MI_Scout);
		
		JMenu M_Help=new JMenu("Help");
		add(M_Help);
		
		JMenuItem MI_Help=new JMenuItem("Help Index");
		M_Help.add(MI_Help);
		
		MI_Open.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
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
				int returnVal= fc.showOpenDialog(MI_Open);
				if(returnVal==JFileChooser.APPROVE_OPTION){//if approved
					File file=fc.getSelectedFile();
					String fileData="";
					try{
					      BufferedReader in = new BufferedReader(new FileReader(file));
					      String str;
					      while((str=in.readLine())!=null){
					    	  fileData=str;
					      }
					      in.close();
					    }
					    catch(IOException s){
					      System.out.println("Error reading file to open for editor");
					    }
					if(!fileData.equals("")){//a simple check to see if a text file has been read
						main.newConfig(fileData);
					}
				}
			}
		});
		MI_Reset.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				main.reset();
			}
		});
		MI_Close.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				main.dispose();
			}
		});
		MI_Directory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int returnVal= fc.showOpenDialog(MI_Directory);
				if(returnVal==JFileChooser.APPROVE_OPTION){//if approved
					var.setDirectory(fc.getSelectedFile()+"/");
				}
			}
		});
		MI_Scout.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				scoutMain scout= new scoutMain(main.getConfig());
				scout.setVisible(true);
			}
		});
		MI_Help.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				HelpFrame help=new HelpFrame("");
				help.setVisible(true);
			}
		});
	}
	public JMenuBar getMenuBar(){
		return this;
	}

}
