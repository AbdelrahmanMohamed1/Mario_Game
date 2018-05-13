package com.mario.Tile;

import java.awt.Graphics;
import java.awt.Rectangle;

import com.mario.Handler;
import com.mario.ID;

public abstract class Tile {
	public int _x,_y;
	public int _width,_height;
	
	public boolean _solid;
	public boolean activated=false;
	
	public int velocityX,velocityY;
	public int _facing;
	
	public ID _id;
	public Handler _handler;
	
	public void setVelocityX(int velocityX) {
		this.velocityX = velocityX;
	}
	
	public void setVelocityY(int velocityY) {
		this.velocityY = velocityY;
	}
	public Tile(int x,int y,int width,int height,boolean solid,ID id,Handler handler){
		_x=x;
		_y=y;
		_width=width;
		_height=height;
		_solid=solid;
		_id=id;
		_handler=handler;
	}
	public ID getID(){
		return _id;
	}
	
    public abstract void render(Graphics g);
	
	public abstract void update();
	
	public void die(){
		_handler.removeTile(this);
	}
	
	public Handler getHandler(){
		return _handler;
	}
	
	public int get_x() {
		return _x;
	}
	public void set_x(int _x) {
		this._x = _x;
	}
	public int get_y() {
		return _y;
	}
	public void set_y(int _y) {
		this._y = _y;
	}
	public boolean is_solid() {
		return _solid;
	}
	public Rectangle getBounds(){
		return new Rectangle(get_x(),get_y(),_width,_height);
	}
}
