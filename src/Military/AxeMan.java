package Military;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.text.StyledEditorKit.BoldAction;
@SuppressWarnings({"serial","unused"})
public class AxeMan extends Troops {
	
	private String[] moveXImageFileNames = {
			"images/axemanRun/run0.gif","images/axemanRun/run1.gif", 
			"images/axemanRun/run2.gif","images/axemanRun/run3.gif",
			"images/axemanRun/run4.gif","images/axemanRun/run5.gif",
		    "images/axemanRun/run6.gif","images/axemanRun/run7.gif"                            	
	};
                             
	private String[] movePositiveYImageFileNames = {
			"images/axemanRunYPozitif/running n0000.gif", "images/axemanRunYPozitif/running n0001.gif",
			"images/axemanRunYPozitif/running n0002.gif", "images/axemanRunYPozitif/running n0003.gif",
			"images/axemanRunYPozitif/running n0004.gif", "images/axemanRunYPozitif/running n0005.gif", 
			"images/axemanRunYPozitif/running n0006.gif", "images/axemanRunYPozitif/running n0007.gif",										
	};
	private String[] moveNegativeYImageFileNames = {
			"images/axemanRunYNegatif/running s0000.gif", "images/axemanRunYNegatif/running s0001.gif",
			"images/axemanRunYNegatif/running s0002.gif", "images/axemanRunYNegatif/running s0003.gif",
			"images/axemanRunYNegatif/running s0004.gif", "images/axemanRunYNegatif/running s0005.gif", 
			"images/axemanRunYNegatif/running s0006.gif", "images/axemanRunYNegatif/running s0007.gif",
	};	
	private String[] attackImageFileNames = { 
			"images/axemanAttack/attack0.gif","images/axemanAttack/attack1.gif", 
			"images/axemanAttack/attack2.gif","images/axemanAttack/attack3.gif",
			"images/axemanAttack/attack4.gif","images/axemanAttack/attack5.gif",
			"images/axemanAttack/attack6.gif","images/axemanAttack/attack7.gif",									  
	};

	private String[] attackPositiveYImageFileNames={
			"images/axemanAttackYPozitif/attack0.gif","images/axemanAttackYPozitif/attack1.gif",											
			"images/axemanAttackYPozitif/attack2.gif","images/axemanAttackYPozitif/attack3.gif",
			"images/axemanAttackYPozitif/attack4.gif","images/axemanAttackYPozitif/attack5.gif",
			"images/axemanAttackYPozitif/attack6.gif","images/axemanAttackYPozitif/attack7.gif",																							
	};
	
	private String[] attackNegativeYImageFileNames={
			"images/axemanAttackYNegatif/attack0.gif","images/axemanAttackYNegatif/attack1.gif",							
			"images/axemanAttackYNegatif/attack2.gif","images/axemanAttackYNegatif/attack3.gif",
			"images/axemanAttackYNegatif/attack4.gif","images/axemanAttackYNegatif/attack5.gif",
			"images/axemanAttackYNegatif/attack6.gif","images/axemanAttackYNegatif/attack7.gif",												
	};
	private String[] deadImageFileNames = { 
			"images/axemanDeadX/tipping over e0000.gif","images/axemanDeadX/tipping over e0001.gif",
			"images/axemanDeadX/tipping over e0002.gif","images/axemanDeadX/tipping over e0003.gif",
			"images/axemanDeadX/tipping over e0004.gif","images/axemanDeadX/tipping over e0005.gif",
			"images/axemanDeadX/tipping over e0006.gif","images/axemanDeadX/tipping over e0007.gif",
			"images/axemanDeadX/tipping over e0008.gif","images/axemanDeadX/tipping over e0009.gif",
			"images/axemanDeadX/tipping over e0010.gif","images/axemanDeadX/tipping over e0011.gif"
	};
	private int maxMoveXFrame = moveXImageFileNames.length;
	private int maxMovePositiveYFrame = movePositiveYImageFileNames.length;
	private int maxMoveNegativeYFrame = moveNegativeYImageFileNames.length;
	
	private int maxAttackFrame = attackImageFileNames.length;
	private int maxAttackPositiveYFrame = attackPositiveYImageFileNames.length;
	private int maxAttackNegativeYFrame = attackNegativeYImageFileNames.length;
	
	private int maxDeadFrame = deadImageFileNames.length;

	
	private Image[] moveXImageFrames;
	private Image[] movePositiveYImageFrames;
	private Image[] moveNegativeYImageFrames;
	
	private Image[] attackImageFrames;
	private Image[] attackPozitiveYImageFrames;
	private Image[] attackNegativeYImageFrames;
	
	private Image[] deadImageFrames;
	
	private boolean isMoveX ;
	private boolean isMovePositiveY ;
	private boolean isMoveNegativeY ;
	
	private boolean isAttack ;	
	private boolean isAttackNegativeY ;
	private boolean isAttackPozitiveY ;
	
	private boolean isDead ;	
	
	private int currentMoveXFrame = 0;    
	private int currentMovePositiveYFrame = 0; 
	private int currentMoveNegativeYFrame = 0;
	private int currentAttackFrame = 0;
	private int currentDeadFrame = 0;

	int startY[] = {470,650,300};
	public static int cou = 1;
	
	public AxeMan(ArrayList<Military> military, int team) {
   		this.team = team;
   		this.military = military;
   		frameRate = 20;
   		speedX = 5;		speedY = 3;
   		x= -30;	y = startY[(cou++)%3];
   		hp = 100; defence = 1200 ; str = 90;
   		isMoveX = true;  	isMovePositiveY = false ; 	isMoveNegativeY = false; 
  		isAttack= false;	isAttackNegativeY = false; 	isAttackPozitiveY = false ;
  		isDead = false;  	
   	 
   		loadImages();   		
   		objectThread.start();    		
   	}    
   @Override
   protected void update() {
	   if (onCastleCollision(x + imageWidth / 2) == true){		  
		   isAttack = true;
	   }
	   else if (x + imageWidth/2 > military.get(0).x + military.get(0).imageWidth/2)
	   {
		   isMoveX = false;
		   if (y  <  military.get(0).y + military.get(0).getHeight() / 2)
		   {
			   isMoveNegativeY = true;
		   }			  
		   else
		   {
			   isMovePositiveY = true;
		   }
	   }
	   if (hp  <= 0){
		   dead();
		   }
	   else if (isAttack == true)
		   attack(military.get(0));
	   else if (isMoveX == true)
		   moveX();
	   else if (isMoveNegativeY == true)
		   moveYNegative();
	   else if (isMovePositiveY == true)
		   moveYPositive();
	}
   public Image getCurrentImage (){
	   
	   if (isDead == true)
		     return deadImageFrames[11]; 
	   else if (hp <= 0)
		   return deadImageFrames[currentDeadFrame];
	   else if (isAttack == true && isMoveNegativeY == false && isMovePositiveY == false){
		   return attackImageFrames[currentAttackFrame];
	   }
	   else if (isAttack == true && isMoveNegativeY == false && isMovePositiveY == true){
		   return attackPozitiveYImageFrames[currentAttackFrame];
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
   
  	@SuppressWarnings("deprecation")
	protected void dead(){
  		isAttack= false;
  		isMoveX = false;
  		isMoveNegativeY = false ;
  		isMovePositiveY = false;
  		if (isDead != true)
  			currentDeadFrame++;
  		
  		if (currentDeadFrame>= maxDeadFrame) {
 		   currentDeadFrame= 0;
 		   isDead = true;
 		   objectThread.stop();
   		} 
  	}
  	@Override
  	protected void attack(Military m) {
  		m.defenceAttack(str);
  		currentAttackFrame++;   
  		if (currentAttackFrame >= maxAttackFrame) {
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
	public void paintComponent(Graphics g) {
		if(isDead==false)
		{
			g.drawImage(getCurrentImage(),x,y,null);
			g.setColor(Color.WHITE);
			g.drawString(String.valueOf((int)hp), x, y);
		}
	
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
            	moveXImageFrames[i] = ImageIO.read(imageUrl);  
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
            	movePositiveYImageFrames[i] = ImageIO.read(imageUrl);  
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
            	moveNegativeYImageFrames[i] = ImageIO.read(imageUrl);  
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
   					attackImageFrames[i] = ImageIO.read(imageUrl);  
   				} catch (IOException ex) {
   					ex.printStackTrace();
   				}
   			}
   		}  
   		attackPozitiveYImageFrames = new Image[maxAttackFrame];
   		for (int i = 0; i < maxAttackFrame; i++) {
   			imageUrl = getClass().getClassLoader().getResource(attackPositiveYImageFileNames[i]);
   			if (imageUrl == null) {
   				System.err.println("Couldn't find file: " + attackPositiveYImageFileNames[i]);
   			} else {
   				try {
   					attackPozitiveYImageFrames[i] = ImageIO.read(imageUrl);  
   				} catch (IOException ex) {
   					ex.printStackTrace();
   				}
   			}
   		}  
   		attackNegativeYImageFrames = new Image[maxAttackFrame];
   		for (int i = 0; i < maxAttackFrame; i++) {
   			imageUrl = getClass().getClassLoader().getResource(attackNegativeYImageFileNames[i]);
   			if (imageUrl == null) {
   				System.err.println("Couldn't find file: " + attackNegativeYImageFileNames[i]);
   			} else {
   				try {
   					attackNegativeYImageFrames[i] = ImageIO.read(imageUrl);  
   				} catch (IOException ex) {
   					ex.printStackTrace();
   				}
   			}
   		} 
   		deadImageFrames = new Image[maxDeadFrame];
   		for (int i = 0; i < maxDeadFrame; i++) {
   			imageUrl = getClass().getClassLoader().getResource(deadImageFileNames[i]);
   			if (imageUrl == null) {
   				System.err.println("Couldn't find file: " + deadImageFileNames[i]);
   			} else {
   				try {
   			   		deadImageFrames[i] = ImageIO.read(imageUrl);  
   				} catch (IOException ex) {
   					ex.printStackTrace();
   				}
   			}
   		}

   		imageWidth = moveXImageFrames[0].getWidth(null);
   		imageHeight = moveXImageFrames[0].getHeight(null);     
   	}




}
	