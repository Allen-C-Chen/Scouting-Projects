package global;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;
import javax.swing.JSplitPane;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.JTextPane;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.io.IOException;
import java.net.URL;

import javax.swing.JEditorPane;
import javax.swing.event.TreeSelectionListener;
import javax.swing.event.TreeSelectionEvent;

public class HelpFrame extends JFrame {

	private JPanel contentPane;
	private JEditorPane EP_Data;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HelpFrame frame = new HelpFrame("");
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
	public HelpFrame(String selected) {
		setTitle("Help");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 771, 399);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JScrollPane SP_Index = new JScrollPane();
		GridBagConstraints gbc_SP_Index = new GridBagConstraints();
		gbc_SP_Index.fill = GridBagConstraints.BOTH;
		gbc_SP_Index.insets = new Insets(0, 0, 0, 5);
		gbc_SP_Index.gridx = 0;
		gbc_SP_Index.gridy = 0;
		SP_Index.setPreferredSize(new Dimension(0,0));
		contentPane.add(SP_Index, gbc_SP_Index);
		
		DefaultMutableTreeNode top= new DefaultMutableTreeNode("Help Index");
		setTree(top);//sets the different nodes of the tree
		final JTree Tr_Index = new JTree(top);
		SP_Index.setViewportView(Tr_Index);
		Tr_Index.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		Tr_Index.setPreferredSize(new Dimension(0,0));
		
		JScrollPane SP_Data = new JScrollPane();
		GridBagConstraints gbc_SP_Data = new GridBagConstraints();
		gbc_SP_Data.weightx = 3.0;
		gbc_SP_Data.fill = GridBagConstraints.BOTH;
		gbc_SP_Data.gridx = 1;
		gbc_SP_Data.gridy = 0;
		SP_Data.setPreferredSize(new Dimension(0,0));
		contentPane.add(SP_Data, gbc_SP_Data);
		
		EP_Data = new JEditorPane();
		EP_Data.setEditable(false);
		EP_Data.setPreferredSize(new Dimension(0,0));
		SP_Data.setViewportView(EP_Data);
		//action listeners after everything so that everything's initialized.
		Tr_Index.addTreeSelectionListener(new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent e) {
				DefaultMutableTreeNode node = (DefaultMutableTreeNode)Tr_Index.getLastSelectedPathComponent();
				if(node==null) return;
				if(node.isLeaf()){
					Item item=(Item)node.getUserObject();
					System.out.println("setDisplay:"+item);
					setHelpDisplay(item.filePath);
				}
				
			}
		});
		
	}
	//edit this to change the tree//sets up tree
	private void setTree(DefaultMutableTreeNode top){
		DefaultMutableTreeNode topCategory=null;
		DefaultMutableTreeNode category=null;
		DefaultMutableTreeNode item=null;
		topCategory=new DefaultMutableTreeNode("Scouting");
		top.add(topCategory);
		
		category= new DefaultMutableTreeNode("Preparation Tab");
		topCategory.add(category);

		item=new DefaultMutableTreeNode(new Item("General","/global/resources/PGeneral.html"));
		category.add(item);
		
		item=new DefaultMutableTreeNode(new Item("Team Numbers","/global/resources/TeamNum.html"));
		category.add(item);
		
		item=new DefaultMutableTreeNode(new Item("Match Presets","/global/resources/MatchPresets.html"));
		category.add(item);
		
		item=new DefaultMutableTreeNode(new Item("Setting Game Time","/global/resources/GameTime.html"));
		category.add(item);
		
		category= new DefaultMutableTreeNode("Game Tab");
		topCategory.add(category);
		
		item=new DefaultMutableTreeNode(new Item("General","/global/resources/GGeneral.html"));
		category.add(item);
		
		item=new DefaultMutableTreeNode(new Item("Starting the Game","/global/resources/GStart.html"));
		category.add(item);
		
		item=new DefaultMutableTreeNode(new Item("Autonomous","/global/resources/Auto.html"));
		category.add(item);
		
		item=new DefaultMutableTreeNode(new Item("Teleop","/global/resources/Teleop.html"));
		category.add(item);
		
		item=new DefaultMutableTreeNode(new Item("Shortcuts","/global/resources/Shortcuts.html"));
		category.add(item);
		
		category= new DefaultMutableTreeNode("Scoring Tab");
		topCategory.add(category);
		
		item=new DefaultMutableTreeNode(new Item("General","/global/resources/SGeneral.html"));
		category.add(item);
		
		item=new DefaultMutableTreeNode(new Item("Saving a match","/global/resources/SaveMatch.html"));
		category.add(item);
		
		topCategory=new DefaultMutableTreeNode("Data Viewing");
		top.add(topCategory);
		
		category=new DefaultMutableTreeNode("Single Match");
		topCategory.add(category);
		
		item=new DefaultMutableTreeNode(new Item("Setting Directory","/global/resources/DSingle.html"));
		category.add(item);
		
		item=new DefaultMutableTreeNode(new Item("Choosing Match","/global/resources/ChooseSMatch.html"));
		category.add(item);
		
		item=new DefaultMutableTreeNode(new Item("Data Seen","/global/resources/SinData.html"));
		category.add(item);
		
		category=new DefaultMutableTreeNode("Searching Matches");
		topCategory.add(category);
		
		item=new DefaultMutableTreeNode(new Item("Selecting Matches","/global/resources/SearchSelect.html"));
		category.add(item);
		
		item=new DefaultMutableTreeNode(new Item("SearchingMatches","/global/resources/SearchSearch.html"));
		category.add(item);
		
		category=new DefaultMutableTreeNode("Scores");
		topCategory.add(category);
		
		item=new DefaultMutableTreeNode(new Item("Viewing Scores","/global/resources/ScoreView.html"));
		category.add(item);
		
	}
	public void setHelpDisplay(URL url){
		try {
			if (url != null) {
				EP_Data.setPage(url);
			} else { //null url, loads a default null html page for items that are not completed
				EP_Data.setPage(this.getClass().getResource("/global/resources/Null.html"));
			}
		} catch (IOException e) {
			System.err.println("Attempted to read a bad URL: " + url);
		}
	}
	private class Item{
		public String name;
		public URL filePath;
		public Item(String name, String filePath){
			this.name=name;
			this.filePath=this.getClass().getResource(filePath);
			if(this.filePath!=null){
				System.out.println("file found:"+filePath);
			}
			else{
				System.out.println("file not found:"+filePath);
			}
		}
		public String toString(){
			return name;
		}
		
	}

}
