package scouterEdit;

import java.awt.EventQueue;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.event.ChangeListener;

import global.Debug_Vars;
import global.Location;
import scouter.scoutMain;

import javax.swing.event.ChangeEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.WindowFocusListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EditMain extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private EditVar var=new EditVar();//variable class that contains the main variables that are passed around the classes, most variables must be extracted from this using get methods
	//frames/panels
	private GameEdit game;// -51/ height/.9243-201-142
	private WindowEdit window;
	private TeamEdit team;
	private ScoreEdit score;
	private EditMain mainEditW;
	private Debug_Vars debugging;
	private scoutMain preview;
	private GameHelper gameHelp;
	private TeamPresetEdit teamPreset;
	
	//sets menu passes on this class containing everything to it
	private EditMainMenu menu;
	private JMenuBar menuBar;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditMain frame = new EditMain();
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
	public EditMain() {
		mainEditW=this;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 650, 413);
		
		menu = new EditMainMenu(this,var);
		menuBar= menu.getMenuBar();
		setJMenuBar(menuBar);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane TP_Main = new JTabbedPane(JTabbedPane.TOP);//Tabbed portion is approximately 28 pixels tall
		TP_Main.setBounds(10, 11, 614, 211);
		contentPane.add(TP_Main);
		
		game=new GameEdit(var);
		window=new WindowEdit(var);
		team=new TeamEdit(var);
		score=new ScoreEdit(var);
		
		JPanel P_EditGame = game.getP_EditGame();
		TP_Main.addTab("Game", null, P_EditGame, null);
		
		JPanel P_EditWindow = window.getP_EditWindow();
		TP_Main.addTab("Window", null, P_EditWindow, null);
		
		JPanel P_EditTeam= team.getP_EditTeam();
		TP_Main.addTab("Teams",null, P_EditTeam, null);
		
		JPanel P_EditScore= score.getP_EditScore();
		TP_Main.addTab("Scoring",null, P_EditScore, null);
		
		JScrollPane SP_output = new JScrollPane();
		SP_output.setBounds(10, 238, 614, 102);
		contentPane.add(SP_output);
		
		JTextArea output = var.getOutput();
		SP_output.setViewportView(output);
		
		JLabel lblOutput = var.getlblOutput();
		lblOutput.setBounds(10, 224, 46, 14);
		contentPane.add(lblOutput);
		
		//listeners at the end
		TP_Main.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				mainEditW.save();
			}
		});
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				if(preview!=null&&preview.isShowing()){
					preview.dispose();
				}
				if(gameHelp!=null&&gameHelp.isShowing()){
					gameHelp.dispose();
				}
				if(teamPreset!=null&&teamPreset.isShowing()){
					teamPreset.dispose();
				}
			}
		});
		
		
	}
	public void addGamePreset(String name){
		game.addPreset(name);
	}
	public void launchPreview(){
		var.saveGameComponents();
		var.saveTeam();
		var.saveWindow();
		var.saveScore();
		if(preview==null||!preview.isShowing()){
			preview= new scoutMain(var.getPrintOut());
			preview.setVisible(true);
		}
		else if(preview.isShowing()){
			preview.dispose();
			preview= new scoutMain(var.getPrintOut());
			preview.setVisible(true);
		}
	}
	public Debug_Vars getDebugFrame(){
		if(debugging==null||!debugging.isShowing()){
			debugging=new Debug_Vars();
			debugging.setVisible(true);	
		}
		return debugging;
	}
	public void openTeamPresets() {
		if(teamPreset==null){
			teamPreset=new TeamPresetEdit();
			teamPreset.setVisible(true);
		}
	}
	public void loadData(String data){
		var.decode(data);
		game.reset();
		team.reset();
		window.reset();
		score.reset();
	}
	public void save(File file){//actually saving all the data to a file
		var.saveGameComponents();
		var.saveTeam();
		var.saveWindow();
		var.saveScore();
		var.savePrintout(file);
	}
	public void save(){//saving the printout done every time you switch to a new tab
		var.saveGameComponents();
		var.saveTeam();
		var.saveWindow();
		var.saveScore();
	}
	public EditVar getEditVar(){
		return var;
	}
	public void HelpGame(){
		if (gameHelp==null||!gameHelp.isShowing()) {
			gameHelp = new GameHelper(this, var);
			gameHelp.setVisible(true);
		}
		else{
			gameHelp.dispose();
			gameHelp = new GameHelper(this, var);
			gameHelp.setVisible(true);
		}
	}
	public void HelpGame(Location l){
		game.setLocation(l);
	}
	public void reset(){//resets everything to blank/default values
		var.resetData();
		game.reset();
		team.reset();
		window.reset();
		score.reset();
	}

	
}
