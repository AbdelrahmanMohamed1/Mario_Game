package com.mario.Entity.mob;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Set;

import com.mario.Game;
import com.mario.Handler;
import com.mario.ID;
import com.mario.Entity.Entity;
import com.mario.Tile.Tile;
import com.mario.states.PlayerState;

public class Player extends Entity{
	
	
	private PlayerState state;
	
	private int frame=0;
	private int frameDelay=0;
	private int w,h;
	
	public Player(int x, int y, int width, int height, boolean solid, ID id, Handler handler) {
		super(x, y, width, height, solid, id, handler);
		w=width;
		h=height;
		
		state=PlayerState.small;
	}
	
	private boolean animate=false;
	
	public void render(Graphics g) {
		if(state==PlayerState.suberbig){
			if(facing==0){
				g.drawImage(Game.FirePlayer[frame+4].getBufferedImage(), _x, _y,_width,_height,null);
				//facing=1;
			}
			else if(facing==1){
				g.drawImage(Game.FirePlayer[frame].getBufferedImage(), _x, _y,_width,_height,null);
				//facing=0;
			}
		}
		else{
		      if(facing==0){
			g.drawImage(Game.player[frame+4].getBufferedImage(), _x, _y,_width,_height,null);
			//facing=1;
		      }
		      else if(facing==1){
			g.drawImage(Game.player[frame].getBufferedImage(), _x, _y,_width,_height,null);
			//facing=0;
		      }
		 }
	}
	
	
	public void update() {
		_x+=velocityX;
		_y+=velocityY;
		//if(_x<=0)_x=0;
		//if(_y<=0)_y=0;
		//if(_x+_width>=870)_x=870-_width+4;
		//if(_y+_height>=696)_y=696-_height;
		if(velocityX!=0)animate=true;
		else animate=false;
		for(int i=0;i<_handler.tile.size();i++){
			Tile t=_handler.tile.get(i);
			if(!t._solid)break;
			if(t.getID()==ID.powerUp){
				//setVelocityY(0);
				//_y=t.get_y()+t._height;
				//if(getBoundsTop().intersects(t.getBounds())){setVelocityY(0);t.activated=true;}
				if(getBoundsTop().intersects(t.getBounds())){
					setVelocityY(0);
					t.activated=true;
					_y=t.get_y()+t._height;
					if(jumping){
					jumping=false;
					gravity=0.8;
					falling=true;
				    }
				}
				if(getBoundsBottom().intersects(t.getBounds())){
					setVelocityY(0);
					if(falling)falling=false;
					//_y=t.get_y()-t._height+24;
					if(w==_width&&h==_height)_y=t.get_y()-t._height+24;
					else _y=t.get_y()-t._height;
				}else{
					if(!falling&&!jumping){
						gravity=0.8;
						falling=true;
					}
				}
				if(getBoundsLeft().intersects(t.getBounds())){
					setVelocityX(0);
					_x=t.get_x()+t._width;
				}
				if(getBoundsRight().intersects(t.getBounds())){
					setVelocityX(0);
					//_x=t.get_x()-t._width+23;
					if(w==_width&&h==_height)_x=t.get_x()-t._width+23;
					else _x=t.get_x()-t._width;
				}
			}
			else if(t.getID()==ID.liveUpBlock){
				//setVelocityY(0);
				//_y=t.get_y()+t._height;
				//if(getBoundsTop().intersects(t.getBounds())){setVelocityY(0);t.activated=true;}
				if(getBoundsTop().intersects(t.getBounds())){
					setVelocityY(0);
					t.activated=true;
					_y=t.get_y()+t._height;
					if(jumping){
					jumping=false;
					gravity=0.8;
					falling=true;
				    }
				}
				if(getBoundsBottom().intersects(t.getBounds())){
					setVelocityY(0);
					if(falling)falling=false;
					//_y=t.get_y()-t._height+24;
					if(w==_width&&h==_height)_y=t.get_y()-t._height+24;
					else _y=t.get_y()-t._height;
				}else{
					if(!falling&&!jumping){
						gravity=0.8;
						falling=true;
					}
				}
				if(getBoundsLeft().intersects(t.getBounds())){
					setVelocityX(0);
					_x=t.get_x()+t._width;
				}
				if(getBoundsRight().intersects(t.getBounds())){
					setVelocityX(0);
					//_x=t.get_x()-t._width+23;
					if(w==_width&&h==_height)_x=t.get_x()-t._width+23;
					else _x=t.get_x()-t._width;
				}
			}
			else if(t.getID()==ID.wall){
			if(getBoundsTop().intersects(t.getBounds())){
					setVelocityY(0);
					_y=t.get_y()+t._height;
					if(jumping){
					jumping=false;
					gravity=0.8;
					falling=true;
				    }
				}
				if(getBoundsBottom().intersects(t.getBounds())){
					setVelocityY(0);
					if(falling)falling=false;
					//_y=t.get_y()-t._height+24;
					if(w==_width&&h==_height)_y=t.get_y()-t._height+24;
					else _y=t.get_y()-t._height;
				}else{
					if(!falling&&!jumping){
						gravity=0.8;
						falling=true;
					}
				}
				if(getBoundsLeft().intersects(t.getBounds())){
					setVelocityX(0);
					_x=t.get_x()+t._width;
				}
				if(getBoundsRight().intersects(t.getBounds())){
					setVelocityX(0);
					//_x=t.get_x()-t._width+23;
					if(w==_width&&h==_height)_x=t.get_x()-t._width+23;
					else _x=t.get_x()-t._width;
				}
			}
			else if(t.getID()==ID.coin){
				if(getBounds().intersects(t.getBounds())){
					Game.coins++;
					t.die();
				}
			}
	     }
		 for(int i=0;i<_handler.entity.size();i++){
			Entity e=_handler.entity.get(i);
			if(e.getID()==ID.mushroom){
				if(getBounds().intersects(e.getBounds())&&state==PlayerState.small){
					_width+=25;
					_height+=25;
					//Game.handler.removeEntity(e);
					e.die();
					if(state==PlayerState.small)state=PlayerState.big;
				}
			}
			else if(e.getID()==ID.liveUp){
				if(getBounds().intersects(e.getBounds())){
					Game.lives++;
					e.die();
				}
			}
			else if(e.getID()==ID.goomba){
				if(getBoundsBottom().intersects(e.getBoundsTop())){
					e.die();
				}
				else if(getBounds().intersects(e.getBounds())){
					if(state==PlayerState.big||state==PlayerState.suberbig){state=PlayerState.small;
					_width-=25;
					_height-=25;
					if(getBoundsRight().intersects(e.getBounds()))_x-=_width;
					if(getBoundsLeft().intersects(e.getBounds()))_x+=_width;
					//_y+=_width;
					}
					else if(state==PlayerState.small){
					die();
					Game.lives--;
					Game.ShowDeathScreen=true;
					if(Game.lives<=0)Game.GameOver=true;
					}
				}
			}
			else if(e.getID()==ID.jumpEnemey){
				if(getBoundsBottom().intersects(e.getBoundsTop())){
					e.die();
				}
				else if(getBounds().intersects(e.getBounds())){
					if(state==PlayerState.big){state=PlayerState.small;
					_width-=25;
					_height-=25;
					if(getBoundsRight().intersects(e.getBounds()))_x-=_width;
					if(getBoundsLeft().intersects(e.getBounds()))_x+=_width;
					//_y+=_width;
					}
					else if(state==PlayerState.small){
					die();Game.lives--;
					Game.ShowDeathScreen=true;
					if(Game.lives<=0)Game.GameOver=true;
					}
				}
			}
			else if(e.getID()==ID.flower){
				if(getBounds().intersects(e.getBounds())){
				    if(state==PlayerState.big){
					state=PlayerState.suberbig;
				    }
				    else if(state==PlayerState.small){
				    _width+=25;
					_height+=25;
					state=PlayerState.big;
				    }
				    e.die();
			     }
		     }
		}
		
		if(jumping){
			gravity-=0.1;
		    setVelocityY((int)-gravity);
		    if(gravity<=0.0){
			jumping=false;
			falling=true;
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
