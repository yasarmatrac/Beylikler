package WarScene;

import java.awt.BorderLayout; 
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import Military.AxeMan;
import Military.Castle;
import Military.Catapult;
import Military.DisKnight;
import Military.Military;
import Military.Tower;
import Military.Tower2;

@SuppressWarnings("serial")
public class WarScene2 extends JPanel {

	private ArrayList<Military> military;
	private WarMap warMap ;
	private Point pointMotion;
	
	private boolean mouseListenerIsActive;
	private int clickHakki=100;
	private int uret=0;
	private JButton disK = new JButton("DisKnight");
	private JButton cata= new JButton("Catapult");
	private JButton axe= new JButton("Axeman");
	private Box box1 = Box.createHorizontalBox();
	
	public WarScene2 () {
		setLayout(new BorderLayout()) ; 
		military = new ArrayList<Military>();
   		military.add(new Castle(military,0));
   		military.add(new Tower(military,4,550,100));
   		military.add(new Tower2(military,4,650,400));
   		warMap = new WarMap();

   		box1.add(Box.createHorizontalStrut(20));
   		box1.add(cata);
   		box1.add(Box.createHorizontalStrut(20));
   		box1.add(axe);
   		box1.add(Box.createHorizontalStrut(20));
   		box1.add(disK);
     	add(box1, BorderLayout.NORTH);
     	doSmthWithMouseListeners ();
   		
   		Thread warThread = new Thread () {
 	       public void run() {
 	    	   long currentTime = 0;   	    	  
 	    	   long startTime = System.currentTimeMillis();
 	    	   while (true) {    	        		
 	        		try {
 	        			if ( (currentTime - startTime ) / 15000 >= 1 )
 	        			{
 	        				for (int i = 0 ; i < military.size() ; i++){
 	        					if (military.get(i) != null){
 	        						if (military.get(i).isDead == true){
 	   	        						military.remove(i);
 	   	        					}
 	        					}
 	        				}
 	        				startTime = System.currentTimeMillis();
 	        			}
 	        			
 	        			repaint();        	
 	        			Thread.sleep(10);
 	        			currentTime = System.currentTimeMillis();
 	       			} catch (InterruptedException ex) { }
 	       		}
 	       	}
 	   	};
 	   	warThread.start();		
	}
	public void doSmthWithMouseListeners () {
	    mouseListenerIsActive = true;

		disK.addMouseListener(new MouseAdapter() {
	        public void mouseClicked(MouseEvent evt) {
	            if (mouseListenerIsActive) {
	            	clickHakki--;
	            	military.add(new DisKnight(military,1));
	            }

	            if(clickHakki<=0)	
	            	 mouseListenerIsActive = false;
	        }
	    });
		
		cata.addMouseListener(new MouseAdapter() {
	        public void mouseClicked(MouseEvent evt) {
	            if (mouseListenerIsActive) {
	            	clickHakki-=3;
	            	military.add(new Catapult(military,2));	
	            }
	            if(clickHakki<=0){ 
	            	 mouseListenerIsActive = false;	
	            }
	        }
	    });
		axe.addMouseListener(new MouseAdapter() {
	        public void mouseClicked(MouseEvent evt) {
	            if (mouseListenerIsActive) {
	            	clickHakki--;
	            	military.add(new AxeMan(military,3));
	            }
	            if(clickHakki<=0){
	            	 mouseListenerIsActive = false;
	            }
	        }
	    });
		
	}

   	public void paintComponent(Graphics g) {
   		super.paintComponent(g);      
   		setBackground(Color.WHITE);
   		g.setColor(new Color(0,255,0,128));
   		Graphics2D g2d = (Graphics2D) g;
   			
   		warMap.paintComponent(g2d);
   		if (pointMotion != null){
			g2d.fillRect(warMap.getImgWidth() * pointMotion.x, 2 * warMap.getImgHeight() * pointMotion.y,
					warMap.getImgWidth(), 2 * warMap.getImgHeight());
		}
   		for(int i = 0 ; i < military.size() ; i++){
   			if (military.get(i) != null)
   				military.get(i).paintComponent(g2d);
   		}
   	}
  	public static void main(String[] args) {
   		
 		SwingUtilities.invokeLater(new Runnable() {
		    
		    public void run() {
		    	JFrame frame = new JFrame("Beylikler WarScene 2-Saldýrý-");	    	
		        frame.setContentPane(new WarScene2());
		        frame.pack();
		        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		        frame.setLocationRelativeTo(null); // center the application window
		        frame.setVisible(true);
		        frame.setSize(1280,830);
		        frame.setLocation(0,0);
		    }
	    });
 	}
   		
	
}
