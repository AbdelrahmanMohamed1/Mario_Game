package com.mario;

import com.mario.Entity.Entity;

public class Camera {
	public int x,y;

	public void update(Entity player){
		setX(-player.get_x()+Game.WIDTH/2);
		setY(-player.get_y()+Game.HIGHT*5/3);
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
}
