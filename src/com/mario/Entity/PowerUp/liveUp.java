package com.mario.Entity.PowerUp;

import java.awt.Graphics;
import java.util.Random;

import com.mario.Game;
import com.mario.Handler;
import com.mario.ID;
import com.mario.Entity.Entity;
import com.mario.Tile.Tile;

public class liveUp extends Entity {

	private Random random=new Random();
	
	public liveUp(int x, int y, int width, int height, boolean solid, ID id, Handler handler) {
		super(x, y, width, height, solid, id, handler);
		
		int dir=random.nextInt(2);
		switch(dir){
		case 0:
			setVelocityX(2);
		case 1:
			setVelocityX(-2);
		}
	}

	
	public void render(Graphics g) {
		g.drawImage(Game.LiveUp.getBufferedImage(),_x,_y,_width,_height,null);
	}

	
	public void update() {
		_x+=velocityX;
		_y+=velocityY;
		
		for(int i=0;i<_handler.tile.size();i++){
			Tile t=_handler.tile.get(i);
			if(!t._solid)break;
			if(t.getID()==ID.wall){
				
				if(getBoundsBottom().intersects(t.getBounds())){
					setVelocityY(0);
					if(falling)falling=false;
					_y=t.get_y()-t._height+14;
				}else{
					if(!falling){
						gravity=0.8;
						falling=true;
					}
				}
				if(getBoundsLeft().intersects(t.getBounds())){
					setVelocityX(3);
				}
				if(getBoundsRight().intersects(t.getBounds())){
					setVelocityX(-3);
				}
			}	
	     }
		if(falling){
			gravity+=0.2;
			setVelocityY((int)gravity);
			//jumping=true;
			//falling=false;
		}
	}
	
}
