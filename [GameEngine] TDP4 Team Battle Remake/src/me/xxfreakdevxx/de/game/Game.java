package me.xxfreakdevxx.de.game;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import me.xxfreakdevxx.de.game.audio.AudioManager;
import me.xxfreakdevxx.de.game.object.ID;
import me.xxfreakdevxx.de.game.object.block.StoneWallBlock;
import me.xxfreakdevxx.de.game.object.entity.Monster;
import me.xxfreakdevxx.de.game.object.entity.Player;
import me.xxfreakdevxx.de.game.object.manager.PlatformManager;

@SuppressWarnings("serial")
public class Game extends Canvas implements Runnable {
	
	/* Window */
	public static int windowWidth = 1200;
	public static int windowHeight = 900;
	public static Window window = null;
	
	private boolean isRunning = false;
	public boolean readyToRender = false;
	public boolean readyToTick = false;
	private Thread thread;
	private Handler handler;
	private BufferedImage level = null;
	private Camera camera;
	private TextureAtlas textureAtlas;
	public static final int blocksize = 32;
	public KeyInput keyinput = null;
	public MouseInput mouseinput = null;
	public static AudioManager audiomanager = null;
	
	/* FPS */
	public static int fps_current = 0;
	public static int fps_maximal = -1;
	public static double tickSpeed = 1200;
	
	public Player player = null;
	
	static Game instance;
	public static Game getInstance() {
		return instance;
	}
	
	public static void main(String[] args) {
		new Game();
	}
	
	public Game() {
		instance = this;
		setBackground(Color.BLACK);
		window = new Window(windowWidth, windowHeight, "GameEngine Preset", this);
		start();
		
		keyinput = new KeyInput();
		mouseinput = new MouseInput();
		handler = new Handler();
		new PlatformManager();
		camera = new Camera(0,0);
		textureAtlas = new TextureAtlas();
		this.addKeyListener(keyinput);
		this.addMouseListener(mouseinput);
		this.addMouseMotionListener(new MouseMotion());
		
		readyToRender = true;
		readyToTick = true;
		
		audiomanager = new AudioManager();
		level = textureAtlas.loadImage("/wizard_level.png");
		loadLevel(level);
	}
	
	private void start() {
		isRunning = true;
		thread = new Thread(this);
		thread.start();
	}
	private void stop() {
		isRunning = false;
		try {
			thread.join();
		} catch (InterruptedException e) {e.printStackTrace();}
	}
	
	
	@Override
	public void run() {
		/*
		 * GameLoop - Made by Notch
		 */
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = tickSpeed;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		boolean allowRender = false;
		while(isRunning) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1) {
				tick();
				//update++;
				delta--;
			}
			if(fps_maximal == -1 ) allowRender = true;
			if(frames != fps_maximal || allowRender) {
				render();
				frames++;
			}
			
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				fps_current = frames;
				frames = 0;
			}
		}
		stop();
	}
	
	public void tick() {
		windowWidth = window.frame.getWidth();
		windowHeight = window.frame.getHeight();
		if(readyToTick == false) return;
		for(int i = 0; i < handler.object.size(); i++) {
			if(handler.object.get(i).getId() == ID.PLAYER) {
				camera.tick(handler.object.get(i));
			}
		}
		handler.tick();
		keyinput.tick();
		mouseinput.shoot();
		if(audiomanager != null) audiomanager.playNextAudio();
	}
	public void render() {
		if(readyToRender == false) return;
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		Graphics2D g2d = (Graphics2D) g;
		g.setColor(Color.GRAY);
		g.fillRect(0, 0, windowWidth, windowHeight);
		g2d.translate(-camera.getX(), -camera.getY());
		
		handler.render(g);
		PlatformManager.render(g);
		g2d.translate(camera.getX(), camera.getY());
		
		g.dispose();
		bs.show();
	}
	
	private void loadLevel(BufferedImage image) {
		int w = image.getWidth();
		int h = image.getHeight();
		for(int xx = 0; xx < w; xx++) {
			for(int yy = 0; yy < h; yy++) {
				int pixel = image.getRGB(xx, yy);
				int red = (pixel >> 16) & 0xff;
				int green = (pixel >> 8) & 0xff;
				int blue = (pixel) & 0xff;
				
				if(red == 255) handler.addObject(new StoneWallBlock(ID.BLOCK, new Location(xx*blocksize, yy*blocksize), 32, 32));
				if(green == 255) handler.addObject(new Monster(ID.ENEMY, new Location(xx*blocksize, yy*blocksize), 32, 32, 15.0D));
				if(blue == 255) {
					player = new Player(ID.PLAYER, new Location(xx*blocksize, yy*blocksize), 100.0D);
					handler.addObject(player);
				}
			}
		}
	}
	
	public TextureAtlas getTextureAtlas() {
		return textureAtlas;
	}
	
	public Handler getHandler() {
		return handler;
	}
	
	public Camera getCamera() {
		return camera;
	}
	
}
