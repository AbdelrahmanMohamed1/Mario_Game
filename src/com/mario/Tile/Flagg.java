package com.mario.Tile;

import java.awt.Graphics;

import com.mario.Game;
import com.mario.Handler;
import com.mario.ID;

public class Flagg extends Tile {

	public Flagg(int x, int y, int width, int height, boolean solid, ID id, Handler handler) {
		super(x, y, width, height, solid, id, handler);
		
	}

	
	public void render(Graphics g) {
		g.drawImage(Game.Flag,_x, _y, _width,_height,null);
	}

	
	public void update() {
		
	}

}
