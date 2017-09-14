package scouterEdit;

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
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;



public class EditMainMenu extends JMenuBar {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8262138842005627897L;
	
	private final JFileChooser fc=new JFileChooser();
	private final EditMain mainW;
	private JMenuBar menuBar;
	private EditVar var;
	public EditMainMenu(final EditMain mainW,final EditVar var){
		this.var=var;
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
		this.mainW=mainW;
		menuBar=this;
		JMenu M_File = new JMenu("File");
		add(M_File);
		
		final JMenuItem MI_Open=new JMenuItem("Open");
		M_File.add(MI_Open);
		
		final JMenuItem MI_Save = new JMenuItem("save");
		M_File.add(MI_Save);
		
		final JMenuItem MI_Reset=new JMenuItem("Reset");
		M_File.add(MI_Reset);
		
		JMenuItem MI_TeamPreset= new JMenuItem("Team Preset Editor");
		M_File.add(MI_TeamPreset);
		
		JMenu M_Debug = new JMenu("Debug");
		add(M_Debug);
		
		JMenuItem MI_TeamVars= new JMenuItem("Team Variables");
		M_Debug.add(MI_TeamVars);
		
		JMenuItem MI_GameVars=new JMenuItem("Game Variables");
		M_Debug.add(MI_GameVars);
		
		JMenuItem MI_WindowVars=new JMenuItem("Window Variables");
		M_Debug.add(MI_WindowVars);
		
		JMenu M_Presets = new JMenu("Presets");
		add(M_Presets);
		
		JMenuItem MI_Timer = new JMenuItem("Timer");
		M_Presets.add(MI_Timer);
		
		JMenuItem MI_ShortcutBox= new JMenuItem("Shortcut Box");
		M_Presets.add(MI_ShortcutBox);
		
		JMenuItem MI_CommentBox= new JMenuItem("Comment Box");
		M_Presets.add(MI_CommentBox);
		
		JMenuItem MI_StartButton= new JMenuItem("Start Button");
		M_Presets.add(MI_StartButton);
		
		JMenuItem MI_TeamSelect=new JMenuItem("Team Select");
		M_Presets.add(MI_TeamSelect);
		
		JMenu M_Previews= new JMenu("Preview");
		add(M_Previews);
		
		JMenuItem MI_Preview= new JMenuItem("Preview Scouter");
		M_Previews.add(MI_Preview);
		
		JMenuItem MI_GameH= new JMenuItem("Game Helper");
		M_Previews.add(MI_GameH);
		
		//actions after so that everything is initiallized
		MI_Open.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				int returnVal= fc.showOpenDialog(MI_Open);
				if(returnVal==JFileChooser.APPROVE_OPTION){
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
					if(fileData!=null){//a simple check to see if a text file has been read
						mainW.loadData(fileData);//loads new data
					}
				}
				
			}
		});
		MI_Save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				int returnVal= fc.showSaveDialog(MI_Save);
				if(returnVal==JFileChooser.APPROVE_OPTION){
					File file=new File(fc.getCurrentDirectory()+"/"+fc.getSelectedFile().getName()+".txt");
					mainW.save(file);
				}
				
				
			}
		});
		MI_Reset.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				int response=JOptionPane.showConfirmDialog(MI_Reset, "Confirm Reset?");
				if(response==JOptionPane.OK_OPTION){
					mainW.reset();
				}
			}
		});
		MI_TeamPreset.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				mainW.openTeamPresets();
			}
		});
		MI_Preview.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				mainW.launchPreview();
			}
		});
		MI_GameH.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				mainW.HelpGame();
			}
		});
		MI_WindowVars.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				mainW.getDebugFrame().addData("Window", var.debugWindow());
			}
		});
		MI_GameVars.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				for(int i=0;i<var.getGameComponents().size();i++){
					mainW.getDebugFrame().addData(var.getGameComponents().get(i).getName(), var.debugGame(i));
				}
			}
		});
		MI_TeamVars.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				for(int i=0;i<var.getTeamFields().size();i++){
					mainW.getDebugFrame().addData(var.getTeamFields().get(i).getName(), var.debugTeam(i));
				}
			}
		});
		MI_Timer.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				mainW.addGamePreset("Timer");
			}
		});
		MI_ShortcutBox.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				mainW.addGamePreset("ShortcutBox");
			}
		});
		MI_CommentBox.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				mainW.addGamePreset("CommentBox");
			}
		});
		MI_StartButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				mainW.addGamePreset("StartButton");
			}
		});
		MI_TeamSelect.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				mainW.addGamePreset("TeamSelect");
			}
		});
		
	}
	public JMenuBar getMenuBar(){
		return menuBar;
	}

}
