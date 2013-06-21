package WarScene;

import java.awt.BorderLayout; 
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.event.MouseInputListener;

import Military.*;

public class WarScene extends JPanel implements  MouseInputListener{
	private ArrayList<Military> military;
	private WarMap warMap ;
	private Point pointMotion;
	private JPanel altPanel ;
	private JButton towerButton;
	private JButton tower2Button;
	private boolean isAddTower = false ;
	private boolean isAddTower2 = false ;
	final int COLUMN = 20;
	final int ROW = 6;
		
	
	public WarScene () {
		
		
		
		military = new ArrayList<Military>();
   		military.add(new Castle(military,0));
   		warMap = new WarMap();
		
		setLayout(new BorderLayout());
		altPanel = new JPanel();
		altPanel.setLayout(new GridLayout());
		towerButton = new JButton("Tower");
		tower2Button = new JButton("Tower 2");
		altPanel.add(towerButton);
		altPanel.add(tower2Button);
		add(altPanel,BorderLayout.PAGE_END);		
		
		towerButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				isAddTower = true;			
			}
		});
		tower2Button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				isAddTower2 = true;
				
			}
		});
		
   		addMouseMotionListener(this);
   		addMouseListener(this);
   		Thread warThread = new Thread () {
    	       public void run() {
    	    	   long totalTime = 15000 ;
    	    	   long currentTime = 0;   	    	  
    	    	   long startTime = System.currentTimeMillis();
    	    	   while (true) {    	        		
    	        		try {
    	        			if ( (currentTime - startTime ) / totalTime >= 1 )
    	        			{
    	        				if (totalTime >= 3000)
    	        					totalTime -= 1000;
    	        				
    	        				Random random = new Random();
    	        				int ran = random.nextInt(3);
    	        				if (ran == 0){
    	        					military.add(new DisKnight(military,1));
    	        				}
    	        				else if (ran == 1){
    	        					military.add(new AxeMan(military,1));    	        					
    	        				}
    	        				else if (ran == 2){
    	        					military.add(new Catapult (military,1));
    	        				}
    	        				
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
  
	@Override
	public void mouseClicked(MouseEvent arg0) {
		Point addPoint = location(arg0.getX(),arg0.getY());
		if (isAddTower == true){
			military.add(new Tower(military, 0, addPoint.x*warMap.getImgWidth(), 2*(addPoint.y) * warMap.getImgWidth()));
			isAddTower = false;
		}
		else if (isAddTower2 == true){
			military.add(new Tower2(military, 0, addPoint.x*warMap.getImgWidth(), 2* (addPoint.y ) * warMap.getImgWidth()));
			isAddTower2 = false;
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		pointMotion = location(e.getX() , e.getY() );
		repaint();
	}
	public Point location (int x, int y){
		for (int i = 0 ; i < COLUMN ; i++ ) {
			for (int j = 0 ; j < ROW ; j++){
				if ( warMap.getImgWidth() * i < x &&  x <=warMap.getImgWidth() * (i+1) 
						&& warMap.getImgHeight() * j < y &&  y <=warMap.getImgHeight() * 2 *(j+1) ){	
					return new Point(i,j);
				}
			}
		}
		return null;	
	}
 	
   	public static void main(String[] args) {
   		
 		SwingUtilities.invokeLater(new Runnable() {
 			
		    public void run() {
		    	JFrame frame = new JFrame("Beylikler WarScene 1");
		    	try {
					frame.setIconImage(ImageIO.read(getClass().getClassLoader().getResource("images/disKnightImages/move/disKnight1.gif")));
				} catch (IOException e) {
					e.printStackTrace();
				}
		        frame.setContentPane(new WarScene());
		        frame.pack();
		        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		        frame.setLocationRelativeTo(null);
		        frame.setVisible(true);
		        frame.setSize(1280,830);
		        frame.setLocation(0,0);
		    }
	    });
 	}
}
