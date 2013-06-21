package Military;
import javax.imageio.ImageIO; 
import java.net.URL;
import java.util.ArrayList;
import java.awt.*;
import java.io.*;


@SuppressWarnings("serial")
public class DisKnight extends Troops {
  
	private String[] attackImageFileNames = { 
			"images/disKnightImages/attack/e0000.gif", "images/disKnightImages/attack/e0001.gif",
			"images/disKnightImages/attack/e0002.gif", "images/disKnightImages/attack/e0003.gif",
			"images/disKnightImages/attack/e0004.gif", "images/disKnightImages/attack/e0005.gif", 
			"images/disKnightImages/attack/e0006.gif", "images/disKnightImages/attack/e0007.gif",
	};	
	private String[] attackPositiveYFileNames = {
			"images/disKnightImages/attackPositiveY/n0000.gif", "images/disKnightImages/attackPositiveY/n0001.gif",
			"images/disKnightImages/attackPositiveY/n0002.gif", "images/disKnightImages/attackPositiveY/n0003.gif",
			"images/disKnightImages/attackPositiveY/n0004.gif", "images/disKnightImages/attackPositiveY/n0005.gif", 
			"images/disKnightImages/attackPositiveY/n0006.gif", "images/disKnightImages/attackPositiveY/n0007.gif",
	};	
	private String[] attackNegativeYFileNames = {
			"images/disKnightImages/attackNegativeY/s0000.gif", "images/disKnightImages/attackNegativeY/s0001.gif",
			"images/disKnightImages/attackNegativeY/s0002.gif", "images/disKnightImages/attackNegativeY/s0003.gif",
			"images/disKnightImages/attackNegativeY/s0004.gif", "images/disKnightImages/attackNegativeY/s0005.gif", 
			"images/disKnightImages/attackNegativeY/s0006.gif", "images/disKnightImages/attackNegativeY/s0007.gif",
	};	
	private String[] moveXImageFileNames = {
			"images/disKnightImages/move/disKnight1.gif","images/disKnightImages/move/disKnight2.gif", 
			"images/disKnightImages/move/disKnight3.gif","images/disKnightImages/move/disKnight4.gif",
			"images/disKnightImages/move/disKnight5.gif","images/disKnightImages/move/disKnight6.gif",
			"images/disKnightImages/move/disKnight7.gif","images/disKnightImages/move/disKnight8.gif"
	};
	private String[] movePositiveYImageFileNames = {
			"images/disKnightImages/moveYPositive/n0000.gif", "images/disKnightImages/moveYPositive/n0001.gif",
			"images/disKnightImages/moveYPositive/n0002.gif", "images/disKnightImages/moveYPositive/n0003.gif",
			"images/disKnightImages/moveYPositive/n0004.gif", "images/disKnightImages/moveYPositive/n0005.gif", 
			"images/disKnightImages/moveYPositive/n0006.gif", "images/disKnightImages/moveYPositive/n0007.gif",
	};
	private String[] moveNegativeYImageFileNames = {
			"images/disKnightImages/moveYNegative/s0000.gif", "images/disKnightImages/moveYNegative/s0001.gif",
			"images/disKnightImages/moveYNegative/s0002.gif", "images/disKnightImages/moveYNegative/s0003.gif",
			"images/disKnightImages/moveYNegative/s0004.gif", "images/disKnightImages/moveYNegative/s0005.gif", 
			"images/disKnightImages/moveYNegative/s0006.gif", "images/disKnightImages/moveYNegative/s0007.gif",
	};
	private String[] deadImageFileNames = {
			"images/disKnightImages/dead/e0000.gif", "images/disKnightImages/dead/e0001.gif",
			"images/disKnightImages/dead/e0002.gif", "images/disKnightImages/dead/e0003.gif",
			"images/disKnightImages/dead/e0004.gif", "images/disKnightImages/dead/e0005.gif", 
			"images/disKnightImages/dead/e0006.gif", "images/disKnightImages/dead/e0007.gif",
	};
	
	private int maxMoveXFrame = moveXImageFileNames.length;
	private int maxMovePositiveYFrame = movePositiveYImageFileNames.length;
	private int maxMoveNegativeYFrame = moveNegativeYImageFileNames.length;
	private int maxAttackFrame = attackImageFileNames.length;
	private int maxAttackPositiveYFrame = attackPositiveYFileNames.length;
	private int maxAttackNegativeYFrame = attackNegativeYFileNames.length;
	private int maxDeadImageFrame = deadImageFileNames.length;
	
	
	private Image[] moveXImageFrames;
	private Image[] movePositiveYImageFrames;
	private Image[] moveNegativeYImageFrames;	
	private Image[] attackImageFrames;
	private Image[] attackPositiveYImageFrames;
	private Image[] attackNegativeYImageFrames;
	private Image[] deadImageFrames;
	
	public static int cou = 1;
	
	
	private boolean isMoveX ;
	private boolean isMovePositiveY ;
	private boolean isMoveNegativeY ;
	private boolean isAttack ;	
	
	int startY[] = {100,400,500};
	private int currentMoveXFrame = 0;    
	private int currentMovePositiveYFrame = 0; 
	private int currentMoveNegativeYFrame = 0;
	private int currentAttackFrame = 0; 
	
	private int currentDeadFrame = 0;
	
   	public DisKnight(ArrayList<Military> military, int team) {
   		this.team = team;
   		this.military = military;
   		frameRate = 33;
   		speedX = 3;		speedY = 3;
   		x= -30;	y = startY[(cou++)%3];
   		hp = 100; defence = 1200 ; str = 90;
   		isAttack= false;	isMoveX = true;  isMovePositiveY = false ; isMoveNegativeY = false; isDead = false; 
   		
   		loadImages();   		
   		objectThread.start();    		
   	}   
   @Override
   protected void update() {
	   if (onCastleCollision( x + imageWidth / 2) == true){		  
		   isAttack = true;
	   }
	   else if (x + imageWidth/2 > military.get(0).x + military.get(0).imageWidth/2){
		   isMoveX = false;
		   if (y  <  military.get(0).y + military.get(0).getHeight() / 2){
			   isMoveNegativeY = true;
		   }			  
		   else{
			   isMovePositiveY = true;
		   }
	   }
	   
	   
	   if (hp  <= 0)
		   dead();
	   else if (isAttack == true)
		   attack(military.get(0));
	   else if (isMoveX == true)
		   moveX();
	   else if (isMoveNegativeY == true)
		   moveYNegative();
	   else if (isMovePositiveY == true)
		   moveYPositive();
	    
	}
   
  	@SuppressWarnings("deprecation")
	protected void dead(){
  		
  		isAttack= false;
  		isMoveX = false;
  		isMoveNegativeY = false ;
  		isMovePositiveY = false;
  		if (isDead != true)
  			currentDeadFrame++;
  		
  		if (currentDeadFrame>= maxDeadImageFrame) {
 		   currentDeadFrame= 0;
 		   isDead = true;
 		   objectThread.stop();
   		} 
  		
  	}
  	protected void attack(Military m) {
  		m.defenceAttack(str);
  		currentAttackFrame++;   
  		if (currentAttackFrame>= maxAttackFrame) {
		   currentAttackFrame = 0;		  
  		}  
  	}
	
   
   	@Override
   	public void moveX() {
   		x += speedX ;
   		currentMoveXFrame++;	 
   		if (currentMoveXFrame >= maxMoveXFrame) {
   			currentMoveXFrame = 0;
   		}  
   	}
	@Override
	protected void moveYNegative() {
	   y += speedY;
	   currentMoveNegativeYFrame++;	 
	   if (currentMoveNegativeYFrame >= maxMoveNegativeYFrame) {
		   currentMoveNegativeYFrame = 0;
	   }
	}


	@Override
	protected void moveYPositive() {
	   y -= speedY;
	   currentMovePositiveYFrame++;	 
	   if (currentMovePositiveYFrame >= maxMovePositiveYFrame) {
		   currentMovePositiveYFrame = 0;
	   }	
	}   
   
   @Override
   public Image getCurrentImage (){
	   if (isDead == true)
		   return deadImageFrames [7];
	   else if (hp <= 0)
		   return deadImageFrames[currentDeadFrame];
	   else if (isAttack == true && isMoveNegativeY == false && isMovePositiveY == false){
		   return attackImageFrames[currentAttackFrame];
	   }
	   else if (isAttack == true && isMoveNegativeY == false && isMovePositiveY == true){
		   return attackPositiveYImageFrames[currentAttackFrame];
	   }
	   else if (isAttack == true && isMoveNegativeY == true && isMovePositiveY == false ){
		   return attackNegativeYImageFrames[currentAttackFrame];
	   }
	   else if (isMoveX == true)
		   return moveXImageFrames[currentMoveXFrame];
	   else if (isMovePositiveY == true){
		   return movePositiveYImageFrames[currentMovePositiveYFrame];
	   }
	   else if (isMoveNegativeY == true)
		   return moveNegativeYImageFrames[currentMoveNegativeYFrame];
	   return null;	 	   
   } 
	
	@Override
	public void paintComponent(Graphics g) {
	
			g.drawImage(getCurrentImage(),x,y,null);
			g.setColor(Color.WHITE);
			g.drawString(String.valueOf(hp), x, y);
		
	}
   	protected void loadImages() {   
   		
   		moveXImageFrames = new Image[maxMoveXFrame]; 
   		URL imageUrl = null;
   		for (int i = 0; i < maxMoveXFrame; i++) {
   			imageUrl = getClass().getClassLoader().getResource(moveXImageFileNames[i]);
   			if (imageUrl == null) {
   				System.err.println("Couldn't find file: " + moveXImageFileNames[i]);
   			} else {
            try {
            	moveXImageFrames[i] = ImageIO.read(imageUrl).getSubimage(19, 19, 64, 64);  
               	} catch (IOException ex) {
               		ex.printStackTrace();
               	}
   			}
   		}      
   		
   		movePositiveYImageFrames= new Image[maxMovePositiveYFrame]; 
   		imageUrl = null;
   		for (int i = 0; i < maxMovePositiveYFrame; i++) {
   			imageUrl = getClass().getClassLoader().getResource(movePositiveYImageFileNames[i]);
   			if (imageUrl == null) {
   				System.err.println("Couldn't find file: " + movePositiveYImageFileNames[i]);
   			} else {
            try {
            	movePositiveYImageFrames[i] = ImageIO.read(imageUrl).getSubimage(19, 19, 64, 64);  
               	} catch (IOException ex) {
               		ex.printStackTrace();
               	}
   			}
   		}      
   		
   		moveNegativeYImageFrames= new Image[maxMoveNegativeYFrame]; 
   		imageUrl = null;
   		for (int i = 0; i < maxMoveNegativeYFrame; i++) {
   			imageUrl = getClass().getClassLoader().getResource(moveNegativeYImageFileNames[i]);
   			if (imageUrl == null) {
   				System.err.println("Couldn't find file: " + moveNegativeYImageFileNames[i]);
   			} else {
            try {
            	moveNegativeYImageFrames[i] = ImageIO.read(imageUrl).getSubimage(19, 19, 64, 64);  
               	} catch (IOException ex) {
               		ex.printStackTrace();
               	}
   			}
   		}    
   
   		attackImageFrames = new Image[maxAttackFrame];
   		for (int i = 0; i < maxAttackFrame; i++) {
   			imageUrl = getClass().getClassLoader().getResource(attackImageFileNames[i]);
   			if (imageUrl == null) {
   				System.err.println("Couldn't find file: " + attackImageFileNames[i]);
   			} else {
   				try {
   					attackImageFrames[i] = ImageIO.read(imageUrl).getSubimage(19, 19, 64, 64);  
   				} catch (IOException ex) {
   					ex.printStackTrace();
   				}
   			}
   		} 
   		
   		attackPositiveYImageFrames = new Image[maxAttackPositiveYFrame];
   		for (int i = 0; i < maxAttackPositiveYFrame; i++) {
   			imageUrl = getClass().getClassLoader().getResource(attackPositiveYFileNames[i]);
   			if (imageUrl == null) {
   				System.err.println("Couldn't find file: " + attackPositiveYFileNames[i]);
   			} else {
   				try {
   					attackPositiveYImageFrames[i] = ImageIO.read(imageUrl).getSubimage(19, 19, 64, 64);  
   				} catch (IOException ex) {
   					ex.printStackTrace();
   				}
   			}
   		}
   		
   		attackNegativeYImageFrames = new Image[maxAttackNegativeYFrame];
   		for (int i = 0; i < maxAttackPositiveYFrame; i++) {
   			imageUrl = getClass().getClassLoader().getResource(attackNegativeYFileNames[i]);
   			if (imageUrl == null) {
   				System.err.println("Couldn't find file: " + attackNegativeYFileNames[i]);
   			} else {
   				try {
   					attackNegativeYImageFrames[i] = ImageIO.read(imageUrl).getSubimage(19, 19, 64, 64);  
   				} catch (IOException ex) {
   					ex.printStackTrace();
   				}
   			}
   		}
   		
   		deadImageFrames = new Image[maxDeadImageFrame];
   		for (int i = 0; i < maxDeadImageFrame; i++) {
   			imageUrl = getClass().getClassLoader().getResource(deadImageFileNames[i]);
   			if (imageUrl == null) {
   				System.err.println("Couldn't find file: " + deadImageFileNames[i]);
   			} else {
   				try {
   					deadImageFrames[i] = ImageIO.read(imageUrl).getSubimage(19, 19, 64, 64);  
   				} catch (IOException ex) {
   					ex.printStackTrace();
   				}
   			}
   		}   		
   		imageWidth = moveXImageFrames[0].getWidth(null);
   		imageHeight = moveXImageFrames[0].getHeight(null);     
   	}
}