package com.mario.Entity.mob;

import java.awt.Graphics;

import com.mario.Game;
import com.mario.Handler;
import com.mario.ID;
import com.mario.Entity.Entity;
import com.mario.Tile.Tile;

public class FireBall extends Entity {

	public FireBall(int x, int y, int width, int height, boolean solid, ID id, Handler handler) {
		super(x, y, width, height, solid, id, handler);
		switch(facing){
		case 0:this.setVelocityX(-8);
		break;
		case 1: this.setVelocityX(8);
		break;
		}
	}

	
	public void render(Graphics g) {
		g.drawImage(Game.fireBall.getBufferedImage(),_x,_y,_width,_height,null);
	}

	
	public void update() {
		_x+=this.velocityX;
		_y+=this.velocityY;
		
		for(int i=0;i<Game.handler.tile.size();i++) {
			Tile t = Game.handler.tile.get(i);
			
			if(t.is_solid()) {
				if(getBoundsLeft().intersects(t.getBounds())||getBoundsRight().intersects(t.getBounds())) die();
				
				if(getBoundsBottom().intersects(t.getBounds())) {
					jumping = true;
					falling = false;
					gravity = 4.0;
				} else if(!falling&&!jumping) {
					falling = true;
					gravity = 1.0;
				}
			}
		}
		
		for(int i=0;i<Game.handler.entity.size();i++) {
			Entity e = Game.handler.entity.get(i);
			
			if(e.getID()==ID.goomba) {
				if(getBounds().intersects(e.getBounds())) {
					die();
					e.die();
				}
			}
		}
		
		if(jumping) {
			gravity-=0.31;
			this.setVelocityY((int)-gravity);
			if(gravity<=0.5) {
				jumping = false;
				falling = true;
			}
		}	
		
		if(falling) {
			gravity+=0.31;
			this.setVelocityY((int)gravity);
		}
	}

}
