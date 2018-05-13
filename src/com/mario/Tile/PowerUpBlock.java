package com.mario.Tile;

import java.awt.Graphics;

import com.mario.Game;
import com.mario.Handler;
import com.mario.ID;
import com.mario.Entity.PowerUp.Flower;
import com.mario.Entity.PowerUp.Mushroom;
import com.mario.Entity.PowerUp.liveUp;
import com.mario.gfx.Sprite;

public class PowerUpBlock extends Tile {
	
	private Sprite _PowerUp;
	// to check if mushroom has poppedup or not
	private boolean poppedUp=false;
	
	private int spriteY=get_y();
	
	
	public PowerUpBlock(int x, int y, int width, int height, boolean solid, ID id, Handler handler,Sprite PowerUp) {
		super(x, y, width, height, solid, id, handler);
		_PowerUp=PowerUp;
	}
	
	
	public void render(Graphics g) {
		if(!activated)g.drawImage(Game.PowerUp.getBufferedImage(),_x,_y,_width,_height,null);
		else g.drawImage(Game.usedPowerUp.getBufferedImage(), _x, _y, _width,_height,null);
	}
	
	private boolean bol=false;
	public void update() {
		if(activated&&!poppedUp){
			spriteY--;
		}
		
		if(spriteY<=_y-_height&&bol==false){
			if(Game.a==0){Game.handler.addEntity(new Mushroom(_x,_y,50,50,true,ID.mushroom,_handler));Game.bb=true;Game.a++;}
			else if(Game.a==1){Game.handler.addEntity(new liveUp(_x,_y,50,50,true,ID.liveUp,_handler));Game.bb=false;Game.a++;}
			else if(Game.a==2){Game.handler.addEntity(new Flower(_x+5,_y-50,50,50,true,ID.flower,_handler));Game.a++;Game.a%=3;}
			/*if(Game.bb==false){Game.handler.addEntity(new Mushroom(_x,_y,50,50,true,ID.mushroom,_handler));Game.bb=true;}
			else if(Game.bb==true){Game.handler.addEntity(new liveUp(_x,_y,50,50,true,ID.liveUp,_handler));Game.bb=false;}*/
			poppedUp=true;
			bol=true;
			//die();
		}
	}

}
