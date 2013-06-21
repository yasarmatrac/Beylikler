package Military;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;

@SuppressWarnings("serial")
public class Castle extends Construction{
	
	String imageFileName = "images/castle.gif";
	Image image;
	ArrayList<Arrow> arrow = new ArrayList<Arrow>();
	public Castle(ArrayList<Military> military,int team){
		this.military = military;
		this.team = team;
		frameRate = 1;
		hp = 100; str = 10000; defence = 10000;
		loadImage();
		x = 1280-imageWidth;
		y= 400 - imageHeight/2;
		
		objectThread.start();
	}
	
	
	private void loadImage (){
		URL imageFileUrl = getClass().getClassLoader().getResource(imageFileName);
		try {
			image = ImageIO.read(imageFileUrl).getSubimage(70, 40, 413, 446).getScaledInstance(256, 256, 0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		imageHeight = image.getHeight(this);
		imageWidth = image.getWidth(this);
	}


	@Override
	public Image getCurrentImage() {
		return image;
	}

	@Override
	protected void update() {
		int onCol = onCollision();
		if (onCol != -1)
			attack(military.get(onCol));	
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
	protected void attack(Military m) {
		arrow.add(new Arrow(x,y,m.x,m.y));
		m.defenceAttack(str);		
	}
	
}

