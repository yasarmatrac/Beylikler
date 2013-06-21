package WarScene;


import javax.swing.*; 
import javax.imageio.ImageIO;
import java.net.URL;
import java.awt.*;
import java.io.*;

@SuppressWarnings("serial")
public class WarMap extends JPanel  {
	public static int ROW = 12;
	public static int COLUMN = 20;
   	private Image img[][];  
   	private int imgWidth, imgHeight;  
  
   	public WarMap() {   		
   		
   		img = new Image [ROW][COLUMN];   		
   		
   		try {
   			loadImages();
		} 
   		catch (Exception e) {
			e.printStackTrace();
		}   		  		
   	}
   	
   	public int getImgWidth() {
		return imgWidth;
	}

	public int getImgHeight() {
		return imgHeight;
	}

	public void paintComponent(Graphics g) {      
   		for (int i = 0 ; i < img.length ; i++){
   			for(int j = 0 ; j < img[i].length ; j++){
   				g.drawImage(img[i][j], 0 + imgWidth * j , 0 + imgHeight * i,this);
   			}
   		}   
   	}
   	
   	public void loadImages () throws IOException{
   		URL url[] = new URL[4];
   		
   	  	String ustTasUrl = "images/ustToprak.bmp";
   	  	String altTasUrl = "images/altToprak.bmp";
   	  	String toprakUrl = "images/toprak.bmp";
   	  	String yesilUrl = "images/yesil.bmp";
   	  	
   		url[0] = getClass().getClassLoader().getResource(ustTasUrl);
   		url[1] = getClass().getClassLoader().getResource(toprakUrl);
   		url[2] = getClass().getClassLoader().getResource(altTasUrl);
   		url[3] = getClass().getClassLoader().getResource(yesilUrl);
   		
		for (int i = 0 ; i <  img[0].length ; i++){
   	   		img[0][i] = ImageIO.read(url[3]);
   	   	}
   		for (int i = 0 ; i <  img[0].length ; i++){
   	   		img[1][i] = ImageIO.read(url[0]);
   	   	}
   		for (int i = 0 ; i <  img[0].length ; i++){
   	   		img[2][i] = ImageIO.read(url[0]);
   	   	}
   	   	for (int i = 0 ; i < img[0].length; i++){
   	   		img[img.length-1][i] = ImageIO.read(url[2]);
   	   	}
   	   	for (int i = 2; i < img.length-1 ; i++){
   	   		for  (int j = 0 ; j < img[i].length ; j++){
   	   			img [i][j] =  ImageIO.read(url[1]);
   	   		}
   	   	}  
   	   	imgWidth = img[0][0].getWidth(this);
		imgHeight = img[0][0].getHeight(this); 
   	}    	

}
