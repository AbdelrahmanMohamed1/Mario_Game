package com.mario.gfx;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Spritesheet {
	private BufferedImage sheet;
	
	public Spritesheet(String path){
		try {
			sheet =ImageIO.read(getClass().getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// to get a specified image from spriteSheet
	public BufferedImage getSprite(int x,int y){
		return sheet.getSubimage(x*32-32, y*32-32, 32, 32);
	}
}
