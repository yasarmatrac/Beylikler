package Military;

import java.awt.Color; 
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;

@SuppressWarnings("serial")
public class Catapult extends Troops {
	private String[] moveXImageFileNames = { 
			"images/spearCatapultMoveX/0.gif","images/spearCatapultMoveX/1.gif",
			"images/spearCatapultMoveX/2.gif","images/spearCatapultMoveX/3.gif",
			"images/spearCatapultMoveX/4.gif","images/spearCatapultMoveX/5.gif",
			"images/spearCatapultMoveX/6.gif","images/spearCatapultMoveX/7.gif",
			"images/spearCatapultMoveX/8.gif",
	};
	private String[] attackImageFileNames = { 
			"images/spearCatapultAttackX/shooting0.gif","images/spearCatapultAttackX/shooting1.gif",
			"images/spearCatapultAttackX/shooting2.gif","images/spearCatapultAttackX/shooting3.gif",
			"images/spearCatapultAttackX/shooting4.gif","images/spearCatapultAttackX/shooting5.gif",
			"images/spearCatapultAttackX/shooting6.gif","images/spearCatapultAttackX/shooting7.gif",
			"images/spearCatapultAttackX/shooting8.gif",
	};
	private String[] attackSpearXFileNames = {
			"images/spearX/spear0.gif","images/spearX/spear1.gif",
			"images/spearX/spear2.gif","images/spearX/spear3.gif",
			"images/spearX/spear4.gif","images/spearX/spear5.gif",
			"images/spearX/spear6.gif","images/spearX/spear7.gif",
			"images/spearX/spear8.gif"
	};

	private int maxMoveXFrame = moveXImageFileNames.length;
	private int maxAttackFrame = attackImageFileNames.length;
	private int maxattackSpearXFrames = attackSpearXFileNames.length;
	
	private Image[] moveXImageFrames;
	private Image[] attackImageFrames;
	private Image[] attackSpearXFrames;

	private boolean isMoveX ;
	private boolean isAttack ;
	private boolean isDead ;
	
	private static int cou=0;
	
	private int currentMoveXFrame = 0;    
	private int currentAttackFrame = 0; 
	private int currentSpearAttack = 0;
	
    private int startY[] = {240,310,380};
 
	private int spearStartX,spearStartY,speadSpear;
	
   	public Catapult(ArrayList<Military> military, int team) {
   		this.team = team;
   		this.military = military;
   		frameRate = 33;
   		speedX = 3;	speadSpear=100;
   		x= -10;	y = startY[(cou++)%3];
   		spearStartX=x;	spearStartY=y+10;
   		hp=200;	defence = 1200;	str = 90;
   	    isAttack= false;	isMoveX = true;		isDead = false;
   	     
   		loadImages();   		
   		objectThread.start();    		
   	}   
   	
   	public Image getCurrentImage (){
	   if (isAttack == true ){
		   return attackImageFrames[currentAttackFrame];
	   }
	   else if (isMoveX == true)
		   return moveXImageFrames[currentMoveXFrame];
	   return null;	 	   
   	} 
   	
    public Image getCurrentImage2(){
    	return attackSpearXFrames[currentAttackFrame];
    }
    
    @Override
   	protected void update() {
    	
    	if (onCastleCollision(x +imageWidth*4) == true){	
    		isAttack = true;
    	}
	   
    	if(hp<=0)
    		dead();
	    if (isAttack == true)
	    {
		   attack(military.get(0));
		   attackSpear(military.get(0));
		}
	    else if (isMoveX == true)
		   moveX();
	    
	}
    @SuppressWarnings("deprecation")
    protected void dead(){
    	isAttack= false;
    	isMoveX = false;
    	isDead = true;
    	objectThread.stop();
    }
   
    protected void attack(Military m) {
    	frameRate = 25;
    	m.defenceAttack(str);
    	currentAttackFrame++;
    	if (currentAttackFrame >= maxAttackFrame){ 
    		currentAttackFrame = 0;}
    }  
  	
  	protected void attackSpear(Military m) {
  		spearStartX += speadSpear;
  		if(spearStartX>=military.get(0).x)
  			spearStartX=x+25;
  		
  			currentSpearAttack++;   
  		if (currentSpearAttack>= maxattackSpearXFrames) 
  			currentSpearAttack = 0;	
  	}
  	
   	public void moveX() {
   		x += speedX ;
   		spearStartX=x+25;
   		currentMoveXFrame++;	 
   		if (currentMoveXFrame >= maxMoveXFrame) {
   			currentMoveXFrame = 0;
   		}  
   	}
	@Override
	public void paintComponent(Graphics g) {
		g.drawImage(getCurrentImage(),x,y,null);
		if(isAttack==true && isDead==false)
			g.drawImage(getCurrentImage2(),spearStartX,spearStartY,null);
		g.setColor(Color.WHITE);
		if(isDead == false)
			g.drawString(String.valueOf((int)hp), x+50, y+20);
			
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
   		attackSpearXFrames = new Image[maxattackSpearXFrames];
   		for (int i = 0; i < maxattackSpearXFrames; i++) {
   			imageUrl = getClass().getClassLoader().getResource(attackSpearXFileNames[i]);
   			if (imageUrl == null) {
   				System.err.println("Couldn't find file: " + attackSpearXFileNames[i]);
   			} else {
   				try {
   					attackSpearXFrames[i] = ImageIO.read(imageUrl);  
   				} catch (IOException ex) {
   					ex.printStackTrace();
   				}
   			}
   		}
   	 		
   		imageWidth = moveXImageFrames[0].getWidth(null);
   		imageHeight = moveXImageFrames[0].getHeight(null);     
   	}
	@Override
	protected void moveYNegative() {	
	}
	@Override
	protected void moveYPositive() {

	}
	
}
