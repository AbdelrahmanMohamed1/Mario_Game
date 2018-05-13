package com.mario.Entity.PowerUp;

import java.awt.Graphics;

import com.mario.Game;
import com.mario.Handler;
import com.mario.ID;
import com.mario.Entity.Entity;

public class Flower extends Entity {

	public Flower(int x, int y, int width, int height, boolean solid, ID id, Handler handler) {
		super(x, y, width, height, solid, id, handler);
		
	}

	
	public void render(Graphics g) {
		g.drawImage(Game.Flower.getBufferedImage(), _x,_y,_width,_height,null);
	}

	
	public void update() {
		
	}

}
