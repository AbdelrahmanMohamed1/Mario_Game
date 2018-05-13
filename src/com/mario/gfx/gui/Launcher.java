package com.mario.gfx.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.mario.Game;

public class Launcher {
	
	public Buttonn buttons[];
	
	public Launcher(){
		buttons=new Buttonn[2];
		
		buttons[0]=new Buttonn(100,200,100,100,"Start Game");
		buttons[1]=new Buttonn(100,300,100,100,"Exit Game");
	}
	
	public void render(Graphics g){
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, Game.getFrameWidth()+10, Game.getFrameHeight()+690);
		g.setColor(Color.blue);
		g.setFont(new Font("Courier",Font.BOLD,30));
		g.drawString("Welcome To Mario Game :)", 250, 180);
		for(int i=0;i<buttons.length;i++){
			buttons[i].render(g);
		}
	}
}
