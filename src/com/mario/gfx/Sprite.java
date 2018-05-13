package com.mario.gfx;

import java.awt.image.BufferedImage;

public class Sprite {
	
	//public Spritesheet sheet;
	
	public BufferedImage image;
	
	public Sprite(Spritesheet sheet,int x,int y){
		image=sheet.getSprite(x, y);
	}
	
	public BufferedImage getBufferedImage(){
		return image;
	}
}
