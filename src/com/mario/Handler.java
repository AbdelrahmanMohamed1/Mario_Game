package com.mario;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import com.mario.Entity.Entity;
import com.mario.Entity.PowerUp.Mushroom;
import com.mario.Entity.mob.Goomba;
import com.mario.Entity.mob.JumpEnemey;
import com.mario.Entity.mob.Player;
import com.mario.Tile.Coin;
import com.mario.Tile.Flagg;
import com.mario.Tile.LiveUpBlock;
import com.mario.Tile.PowerUpBlock;
import com.mario.Tile.Tile;
import com.mario.Tile.wall;

public class Handler {
	public LinkedList<Entity>entity=new LinkedList<Entity>();
	public LinkedList<Tile>tile=new LinkedList<Tile>();
	
	public Handler(){
		//createLev();
	}
	
	public void render(Graphics g){
		for(int i=0;i<entity.size();i++){
			Entity en=entity.get(i);
			en.render(g);
		}
		
		for(int i=0;i<tile.size();i++){
			Tile t=tile.get(i);
			t.render(g);
		}
	}
	
	public void update(){
		for(int i=0;i<entity.size();i++){
			Entity en=entity.get(i);
			en.update();
		}
		for(int i=0;i<tile.size();i++){
			Tile t=tile.get(i);
			t.update();
		}
	}
	
	public void addEntity(Entity en){
		entity.add(en);
	}
	
	public void removeEntity(Entity en){
		entity.remove(en);
	}
	
	public void addTile(Tile t){
		tile.add(t);
	}
	public void removeTile(Tile t){
		tile.remove(t);
	}
	public void createLev(BufferedImage level){
		int width=level.getWidth();
		int height=level.getHeight();
		
		for(int i=0;i<height;i++){
			for(int j=0;j<width;j++){
				int pixel=level.getRGB(j, i);
				// get the full red,green,blue value of each pixel
				int red=(pixel>>16)& 0xff;
				int green=(pixel>>8)& 0xff;
				int blue=(pixel)& 0xff;
				// black pixel for walls
				if(red==0&&green==0&&blue==0)addTile(new wall(j*64,i*64,64,64,true,ID.wall,this));
				// blue pixel for player
				if(red==0&&green==0&&blue==255)addEntity(new Player(j*70,i*70,40,40,true,ID.player,this));
				// red pixel for mushroom
				if(red==255&&green==0&&blue==0)addEntity(new Mushroom(j*70,i*70,50,50,true,ID.mushroom,this));
				// Orange pixel for goomba
				if(red==255&&green==119&&blue==0)addEntity(new Goomba(j*70,i*70,50,50,true,ID.goomba,this));
				// yellow pixel for powerUp
				if(red==255&&green==255&&blue==0)addTile(new PowerUpBlock(j*64,i*64,64,64,true,ID.powerUp,this,Game.mushroom));
				// coin
				if(red==255&&green==250&&blue==0)addTile(new Coin(j*64,i*64,64,64,true,ID.coin,this));
				// jumpEnemey
				if(red==160&&green==160&&blue==160)addEntity(new JumpEnemey(j*70,i*70,60,60,true,ID.jumpEnemey,this));
				// flag
				if(red==255&&green==0&&blue==127)addTile(new Flagg(j*64,i*63,75,190,true,ID.flag,this));
				// liveUpBlock
				if(red==204&&green==229&&blue==255)addTile(new LiveUpBlock(j*64,i*64,64,64,true,ID.liveUpBlock,this));
			}
		}
		/*for(int i=0;i<Game.WIDTH*Game.SCALE/64+1;i++){
			addTile(new wall(i*64,Game.HIGHT*Game.SCALE-64,64,72,true,ID.wall,this));
			if(i!=0&&i!=1&&i!=15&&i!=16&i!=17)addTile(new wall(i*56,320,64,60,true,ID.wall,this));
		}*/
	}
	
	public void ClearLevel(){
		entity.clear();
		tile.clear();
	}
}
