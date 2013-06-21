package Military;

import java.awt.Graphics; 
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Arrow extends JPanel{
	double xStart,yStart,xEnd,yEnd;
	
	String arrowFileNames [] ;
	Image image;
	double rad;
	Thread objectThread;
	int speed = 10;
	public Arrow(int xStart,int yStart,int xEnd,int yEnd) {
		this.xStart = xStart;
		this.xEnd = xEnd;
		this.yStart = yStart;
		this.yEnd = yEnd;
		rad = getAngle(xStart,yStart,xEnd,yEnd);
		loadImage();
		
		Thread objectThread = new Thread(){
			public void run (){
				while (speed != 0){
					update ();				
					try {
						Thread.sleep(33);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		};
		objectThread.start();
	}
	public void loadImage (){
		int x = 0;
		for (double d = - 5.625 ; d <= 365.375 ; d = d + 11.25)
		{
			if (rad >= d && rad <= d + 11.25){
				break;
			}
			x++;
		}
		
		try {
			image = ImageIO.read(getClass().getClassLoader().getResource("images/arrow/"+String.valueOf(x)+".gif"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void update(){
		
		if (xStart <= (xEnd + 5) && xStart >= xEnd - 5 ){
			speed = 0;			
		}
		else {
			yStart += speed * Math.sin(Math.toRadians(rad));
			xStart += speed * Math.cos(Math.toRadians(rad));
		}
		
	}
	public double getAngle(int x1,int y1,int x2,int y2)
	{
	    double dx = x2 - x1;
	    double dy = y2 - y1;
	    
	    double inRads = Math.atan2(dy,dx);

	    
	    if (inRads < 0)
        	inRads += 2*Math.PI;
	    return Math.toDegrees(inRads);
	}
	
	public void paintComponent (Graphics g){
		if (speed != 0)
			g.drawImage(image, (int)xStart, (int)yStart, null);
	}
}
