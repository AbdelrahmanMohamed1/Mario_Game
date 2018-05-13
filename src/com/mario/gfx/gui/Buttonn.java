package com.mario.gfx.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import com.mario.Game;

public class Buttonn {
	
	public int x,y,width,height;
	
	public String label;

	public Buttonn(int x, int y, int width, int height, String label) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.label = label;
	}
	
	
	public void render(Graphics g){
		g.setColor(Color.RED);
		g.setFont(new Font("Century Gothic",Font.BOLD,50));
		//g.drawRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
		
		// to allowing our text to be in the center of our buttons !
		FontMetrics fm=g.getFontMetrics();
		int stringX=(getWidth()-fm.stringWidth(getLabel()))/2;
		int stringY=(fm.getAscent()+(getHeight()-(fm.getAscent()+fm.getDescent()))/2);
		g.drawString(getLabel(),getX()+stringX,getY()+stringY);
		
	}

	// to executing all the code in it when button is clicked
	public void TriggerEvent(){
		if(this.getLabel().toLowerCase().contains("start"))Game.Playing=true;
		else if(this.getLabel().toLowerCase().contains("exit"))System.exit(0);
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

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
	
	
}
