package Military;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public abstract class Military extends JPanel{
	public boolean isDead;
	protected double hp;
	protected double defence;
	protected double str;
	protected int team;	
	protected ArrayList<Military> military;	
	protected String imageFileNames [];	
	protected int imageWidth;
	protected int imageHeight;
	protected int x;
	protected int y;	
	protected int frameRate ;

	protected Thread objectThread = new Thread(){
		public void run (){
			while (true){
				update ();				
				try {
					Thread.sleep(1000/frameRate);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	};	

	public int getImageWidth (){
		return imageWidth;
	}	
	public int getImageHeight (){
		return imageHeight;
	}
	public abstract Image getCurrentImage ();
	   
	public AffineTransform getPoint (){
		AffineTransform transform = new AffineTransform() ;
		transform.translate(x, y);
		return transform;
	}	
	protected void defenceAttack(double attck) {
		hp -= (attck / defence );	
	}
	abstract protected void update ();
	abstract protected void attack(Military m);	
	abstract public void paintComponent (Graphics g);
	
}
