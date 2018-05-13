package com.mario.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.mario.Game;
import com.mario.ID;
import com.mario.Entity.Entity;

public class Keyinput implements KeyListener {

	public void keyPressed(KeyEvent e) {
		int key=e.getKeyCode();
		for(Entity en:Game.handler.entity){
		if(en.getID()==ID.player){
			switch(key){
			case KeyEvent.VK_W:
				if(!en.jumping){en.jumping=true;
				en.gravity=7.7;
				}
				break;
			case KeyEvent.VK_D:
				en.setVelocityX(4);
				en.facing=1;
				break;
			case KeyEvent.VK_A:
				en.setVelocityX(-4);
				en.facing=0;
				break;
			}
		 }	
	  }
	}

	public void keyReleased(KeyEvent e) {
		int key=e.getKeyCode();
		for(Entity en:Game.handler.entity){
			if(en.getID()==ID.player){
			switch(key){
			case KeyEvent.VK_W:
				en.setVelocityY(0);
				break;
			case KeyEvent.VK_S:
				en.setVelocityY(0);
				break;
			case KeyEvent.VK_D:
				en.setVelocityX(0);
				break;
			case KeyEvent.VK_A:
				en.setVelocityX(0);
				break;
			}
		 }
	  }
   }

	
	public void keyTyped(KeyEvent arg0) {
		
		
	}

}
