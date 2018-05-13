package com.mario.Entity.mob;

import java.awt.Graphics;
import java.util.Random;

import com.mario.Game;
import com.mario.Handler;
import com.mario.ID;
import com.mario.Entity.Entity;
import com.mario.Tile.Tile;

public class Goomba extends Entity {

	private int frame=0;
	private int frameDelay=0;
	
	private Random random=new Random();
	
	public Goomba(int x, int y, int width, int height, boolean solid, ID id, Handler handler) {
		super(x, y, width, height, solid, id, handler);
		
		int dir=random.nextInt(2);
		switch(dir){
		case 0:
			setVelocityX(2);
		case 1:
			setVelocityX(-2);
		}
	}
    
	private boolean animate=true;
	public void render(Graphics g) {
	
		if(facing==0){
			g.drawImage(Game.goomba[frame+4].getBufferedImage(), _x, _y,_width,_height,null);
			//facing=1;
		}
		else if(facing==1){
			g.drawImage(Game.goomba[frame].getBufferedImage(), _x, _y,_width,_height,null);
			//facing=0;
		}
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
					facing=1;
				}
				if(getBoundsRight().intersects(t.getBounds())){
					setVelocityX(-3);
					facing=0;
				}
			}	
	     }
		if(falling){
			gravity+=0.2;
			setVelocityY((int)gravity);
			//jumping=true;
			//falling=false;
		}
		if(animate){
			 frameDelay++;
				if(frameDelay>=5){
					frame++;
					if(frame>=4){
						frame=0;
				     }
				      frameDelay=0;	
			     }
		   }
	}
	
}
