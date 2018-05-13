package com.mario;
// to display stuff into our frame
import java.awt.Canvas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import com.mario.Entity.Entity;
import com.mario.Entity.mob.Player;
import com.mario.Tile.wall;
import com.mario.gfx.Sprite;
import com.mario.gfx.Spritesheet;
import com.mario.gfx.gui.Launcher;
import com.mario.input.Keyinput;
import com.mario.input.MouseInput;

public class Game extends Canvas implements Runnable {
	// set resolutions
	public static final int WIDTH=290;
	public static final int HIGHT=WIDTH/15*12;
	// set scale
	public static final int SCALE=3;
	public static final String TITLE="Mario";
	
	private Thread thread;
	private boolean running =true;
	// BufferedImage ele hya el image ele el java ht3rf t2raha we tt3aml m3aha 
	private BufferedImage image;
	public static BufferedImage Flag;
	
	public static int coins=0;
	public static int lives=3;
	public static int DeathScreenTime=0;
	
	public static boolean ShowDeathScreen=true;
	public static boolean GameOver=false;
	public static boolean Playing=false;
	public static boolean bb=false;
	public static boolean ba=false;
	public static int a=0;
	
	// synchronized protect thread from any other threads interference and memory area
	// start() which thread initialized 
	private synchronized void start(){
		if(running)return;
		running=true;
		thread=new Thread(this,"Thread");
		thread.start();
	}
	// stop() which thread stop
	private synchronized void stop(){
		if(!running)return;
		running=false;
		try {
			// try this risk code
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static Handler handler;
	public static Spritesheet sheet;
	public static Camera cam;
	public static Launcher launcher;
	public static MouseInput mouse;
	
	public static Sprite gras;
	public static Sprite PowerUp;
	public static Sprite usedPowerUp;
	public static Sprite player[]=new Sprite[8];
	public static Sprite FirePlayer[]=new Sprite[8];
	public static Sprite mushroom;
	public static Sprite jumpEnemey;
	public static Sprite goomba[]=new Sprite[8];
	public static Sprite coin;
	public static Sprite LiveUp;
	public static Sprite fireBall;
	public static Sprite Flower;
	
	private void init(){
		handler=new Handler();
		sheet=new Spritesheet("/SpriteSheet.png");
		cam=new Camera();
		launcher=new Launcher();
		mouse=new MouseInput();
		
		// to make our game listen to mouse and keyboard !
		addKeyListener(new Keyinput());
		addMouseListener(mouse);
		addMouseMotionListener(mouse);
		
		gras=new Sprite(sheet,1,1);
		PowerUp=new Sprite(sheet,3,1);
		usedPowerUp=new Sprite(sheet,4,1);
		
		coin=new Sprite(sheet,5,1);
		
		mushroom=new Sprite(sheet,2,1);
		LiveUp=new Sprite(sheet,6,1);
		Flower=new Sprite(sheet,8,1);
		fireBall=new Sprite(sheet,9,1);
		
		jumpEnemey=new Sprite(sheet,1,13);
		
		for(int i=0;i<player.length;i++){
			player[i]=new Sprite(sheet,i+1,16);
		}
		
		for(int i=0;i<FirePlayer.length;i++){
			FirePlayer[i]=new Sprite(sheet,i+9,16);
		}
		
		for(int i=0;i<goomba.length;i++){
			goomba[i]=new Sprite(sheet,i+1,15);
		}
		
		try {
			image=ImageIO.read(getClass().getResourceAsStream("/level.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			Flag=ImageIO.read(getClass().getResourceAsStream("/18281016_1885896088324789_915857135_n.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//handler.createLev(image);
		//handler.addEntity(new Player(200,512,52,52,true,ID.player,handler));
		//handler.addTile(new wall(200,200,64,64,true,ID.wall,handler));
	}
	
	// to run the game
	// game loop
	public void run() {
		init();
		// put our frame in focus mode
		requestFocus();
		
		// we need to create the timer to render per second and updates per second 
		
		// gets the time in nanoseconds while executing the program
		long lastTime=System.nanoTime();
		// gets the time in milliseconds while executing the program
		long timer=System.currentTimeMillis();
		double delta=0.0;
		// to convert to nanosecond from milisecond
		double ns=1000000000.0/60.0;
		// frames per second
		int frames=0;
		// updates per seconds
		int updates=0;
		while(running){
			long now=System.nanoTime();
			delta+=(now-lastTime)/ns;
			lastTime=now;
			while(delta>=1){
				tick();
				//updates++;
				delta--;
			}
			render();
			/*frames++;
			if(System.currentTimeMillis()-timer>1000){
				timer+=1000;
				System.out.println(frames+" frames per second "+updates+" updates per second ");
				frames=0;
				updates=0;
			}*/
		}
	}
	// to display everything on the screen
	public void render(){
		// to convert images to buffer to display it on the screen
		BufferStrategy bs= getBufferStrategy();
		if(bs==null){
			createBufferStrategy(3);
			return;
		}
		// draw everything on screen !
		Graphics g=bs.getDrawGraphics();
		g.setColor(Color.CYAN);
		g.fillRect(0, 0, getWidth(), getHeight());
		if(!ShowDeathScreen){
			g.setColor(Color.WHITE);
			g.setFont(new Font("Courier",Font.ITALIC,30));
			g.drawImage(coin.getBufferedImage(),5,23,25,25,null);
			g.drawString("x"+coins, 7, 20);
		}
		if(ShowDeathScreen){
			if(!GameOver){
				handler.ClearLevel();
				g.setColor(Color.WHITE);
				g.setFont(new Font("Courier",Font.BOLD,30));
				g.drawImage(player[4].getBufferedImage(),350,300,100,100,null);
				g.drawString("x"+lives, 460, 380);
				//g.drawString("Welcome To Mario Game :)", 250, 280);
			}
			else{
				handler.ClearLevel();
				g.setColor(Color.WHITE);
				g.setFont(new Font("Courier",Font.BOLD,30));
				g.drawImage(player[4].getBufferedImage(),350,300,100,100,null);
				g.drawString("x"+lives, 460, 380);
				g.drawString("Game Over :(", 320,290);
			}
		}
		
		if(Playing)g.translate(cam.getX(), cam.getY()-15);
		if(Playing)handler.render(g);
		else if(!Playing)launcher.render(g);
		g.dispose();
		bs.show();
	}
	// to update everything
	public void tick(){
		if(Playing)handler.update();
		for(Entity en:handler.entity){
			if(en.getID()==ID.player){
				cam.update(en);
			}
		}
		if(ShowDeathScreen&&!GameOver)DeathScreenTime++;
		if(DeathScreenTime>=180){
			ShowDeathScreen=false;
			DeathScreenTime=0;
			handler.ClearLevel();
			handler.createLev(image);
		}
	}
	
	public static int getFrameWidth(){
		return WIDTH*SCALE;
	}
	public static int getFrameHeight(){
		return HEIGHT*SCALE;
	}
	
	public Game(){
		// set Dimension to our frame from Dimension class
		Dimension size=new Dimension(WIDTH*SCALE,HIGHT*SCALE);
		// set the size form canvas class
		setPreferredSize(size);
		setMaximumSize(size);
		setMinimumSize(size);
	}
	
	public static void main(String[] args){
		Game game=new Game();
		JFrame frame=new JFrame(TITLE);
		// add the content of the game obj to our frame
		frame.add(game);
		// to pack everything together
		frame.pack();
		// to not resize our frame again
		frame.setResizable(false);
		// to set the frame into the middle of the screen 
		frame.setLocale(null);
		// Jframe happen 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// set our frame visible 
		frame.setVisible(true);
		game.run();
	}

	
	
}
