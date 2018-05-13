package com.mario.Tile;

import java.awt.Graphics;

import com.mario.Game;
import com.mario.Handler;
import com.mario.ID;
import com.mario.Entity.PowerUp.Mushroom;
import com.mario.Entity.PowerUp.liveUp;
import com.mario.gfx.Sprite;

public class LiveUpBlock extends Tile{

	
	// to check if block has poppedup or not
	private boolean poppedUp=false;
	
	private int spriteY=get_y();
	
	public LiveUpBlock(int x, int y, int width, int height, boolean solid, ID id, Handler handler) {
		super(x, y, width, height, solid, id, handler);
		//_PowerUp=PowerUp;
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
			Game.handler.addEntity(new liveUp(_x,_y,50,50,true,ID.liveUp,_handler));
			poppedUp=true;
			bol=true;
			//die();
		}
	}
	
}
