package com.mario.Entity;

import java.awt.Graphics;
import java.awt.Rectangle;

import com.mario.Game;
import com.mario.Handler;
import com.mario.ID;

public abstract class Entity {
	public int _x,_y;
	public int _width,_height;
	public int facing=0;//0->left,1->right;
	
	public boolean _solid;
	public boolean jumping=false;
	public boolean falling=true;
	
	public int velocityX,velocityY;
	
	public ID _id;
	
	public double gravity=0.0;
	
	public Handler _handler;
	
	public void setVelocityX(int velocityX) {
		this.velocityX = velocityX;
	}
	
	public void setVelocityY(int velocityY) {
		this.velocityY = velocityY;
	}
	
	public Entity(int x,int y,int width,int height,boolean solid,ID id,Handler handler){
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
		_handler.removeEntity(this);
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
	public Rectangle getBoundsTop(){
		return new Rectangle(get_x()+10,get_y(),_width-20,5);
	}
	public Rectangle getBoundsBottom(){
		return new Rectangle(get_x()+10,get_y()+_height-5,_width-20,5);
	}
	public Rectangle getBoundsLeft(){
		return new Rectangle(get_x(),get_y()+10,5,_height-20);
	}
	public Rectangle getBoundsRight(){
		return new Rectangle(get_x()+_width-5,get_y()+10,5,_height-20);
	}
}
