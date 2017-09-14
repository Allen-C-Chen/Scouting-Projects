package scouterEdit;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import global.Components;
import global.Location;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class GameDraw extends JPanel {
	private int X=0;
	private int Y=0;
	private int W=0;
	private int H=0;
	private ArrayList<Components> components= new ArrayList<Components>();
	
	/**
	 * Create the panel.
	 */
	public GameDraw() {
		setBorder(BorderFactory.createLineBorder(Color.black));
		addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
            	X=e.getX();
            	Y=e.getY();
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent e) {
                W=e.getX()-X;
                H=e.getY()-Y;
                if(W<0){
                	W=0;
                }
                if(H<0){
                	H=0;
                }
                repaint();
            }
        });
	}
	public void setComponents(ArrayList<Components> components){
		this.components=components;
	}
	public Location getRectLoc(){
		return new Location(X,Y,W,H);
	}
	public void setRectLoc(Location l){
		X=l.getX();
		Y=l.getY();
		W=l.getW();
		H=l.getH();
		repaint();
	}
	public void drawRect(int x, int y, int w, int h){
		
	}
	 public void paintComponent(Graphics g) {
	        super.paintComponent(g);      
	        for(Components c:components){
	        	g.drawRect(c.getLocation().getX(), c.getLocation().getY(), c.getLocation().getW(), c.getLocation().getH());
	        	g.drawString(c.getName(), c.getLocation().getX(), c.getLocation().getY()+12);
	        }
	        g.drawRect(X, Y, W, H);
	    }  
	 public JPanel getPanel(){
		 return this;
	 }
}
