package Military;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;

@SuppressWarnings("serial")
public class Tower extends Construction {
	
	String imageFileName = "images/tower/towers.gif";
	Image image;
	ArrayList<Arrow> arrow = new ArrayList<Arrow>();
	
	public Tower (ArrayList<Military> military,int team ,int x , int y){
		this.military = military;
		this.team = team;
		this.x = x ;
		this.y = y ;
		hp = 100; 
		str = 10000; 
		defence = 10000;
		frameRate = 1;
		loadImage();
		
		objectThread.start();
	}

	private void loadImage (){
		URL imageFileUrl = getClass().getClassLoader().getResource(imageFileName);
		
		try {
			image = ImageIO.read(imageFileUrl).getSubimage(0,0,192,288);
		} catch (IOException e) {
			System.out.println("image yüklenemedi");
			e.printStackTrace();
		}
		imageWidth = image.getWidth(this);
		imageHeight = image.getHeight(this);
		image = image.getScaledInstance(imageWidth/2, imageHeight/2, 1);
	}
	
	
	@Override
	protected void update() {
		int onCol = onCollision();
		if (onCol != -1){
			attack(military.get(onCol));
		}
		
	}

	@Override
	protected void attack(Military m) {
		arrow.add(new Arrow(x,y,m.x,m.y));
		m.defenceAttack(str);
	}

	@Override
	public void paintComponent(Graphics g) {
		g.drawImage(getCurrentImage(),x,y,null);
		g.setColor(Color.WHITE);
		g.drawString(String.valueOf(hp), x, y);
		for (int i = 0; i < arrow.size(); i++) {
			if (arrow.get(i) != null)
				arrow.get(i).paintComponent(g);
		}
	}
		@Override
	public Image getCurrentImage() {
		return image;
	}
}
