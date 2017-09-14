package global;
import java.awt.Dimension;
public class Location {
	//basic information for use in absolute location stuff and size
	private int x;//x position of the component relative to overarching component
	private int y;//y position of the component relative to overarching component
	private int w;//width of the component(across)
	private int h;//height of the component(up/down)
	//sets variables
	public Location(int x, int y, int w, int h){
		this.x=x;
		this.y=y;
		this.w=w;
		this.h=h;
	}
	public Location(int w, int h){
		this.w=w;
		this.h=h;
		x=0;
		y=0;
	}
	public Location(){
		x=0;
		y=0;
		w=0;
		h=0;
	}
	//setters
	public void setX(int x){
		this.x=x;
	}
	public void setY(int y){
		this.y=y;
	}
	public void setW(int w){
		this.w=w;
	}
	public void setH(int h){
		this.h=h;
	}
	public void setDimension(Dimension dim){
		w=(int)dim.getWidth();
		h=(int)dim.getHeight();
	}
	//getters
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public int getW(){
		return w;
	}
	public int getH(){
		return h;
	}
	public Dimension getDimension(){
		Dimension dim=new Dimension(w,h);
		return dim;
	}
}
