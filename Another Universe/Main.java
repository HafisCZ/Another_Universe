import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_NICEST;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_PERSPECTIVE_CORRECTION_HINT;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.GL_SMOOTH;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glBlendFunc;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL11.glClearDepth;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glHint;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glOrtho;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glShadeModel;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glVertex2f;
import static org.lwjgl.opengl.GL11.glViewport;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class Main {

	private static final String version = "Alpha 3";
	private static final int w = 640; //Frame width
	private static final int h = 480; //Frame height
	private volatile long lastFPS;
	private volatile long lastFrame;
	private volatile int fps;
	private volatile boolean vsync; //Enables Vertical Sync
	private volatile boolean gsync; //Enables FPS Sync (60 FPS)
	public static enum Game{LOADING,INTRO,MAIN_MENU,OPTIONS,INGAME,ENDGAME};
	public static Game GameState;
	private Texture stone;
	private Texture dirt;
	private Texture grass;
	private Texture air;
	private Texture player;
	
	private IEntity player1;
	
	public static void main(String[] args) {
		Main app = new Main();
		try {
			app.start();
		} catch (LWJGLException e) {
			System.out.println("[Another Universe "+version+"] Critical error appeared ! ");
			e.printStackTrace();
			System.exit(-1);
		}
	}
	
	public void start() throws LWJGLException{ //TODO MAIN LOOP
		Display.setDisplayMode(new DisplayMode(w,h));
		Display.setTitle("Another Universe Aplha 2");
		Display.create();
		glEnable(GL_TEXTURE_2D);       
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		glEnable(GL_TEXTURE_2D);
		glShadeModel(GL_SMOOTH);
		glClearColor(0.0f, 0.0f, 0.0f, 0.0f);          
		glClearDepth(1.0f);
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        glHint(GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST);
        glViewport(0,0,w,h);
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, w, 0, h, 1, -1);
		glMatrixMode(GL_MODELVIEW);
		
		getResources();
		
		player1 = new IEntity(0, 128, player.getImageWidth(), player.getImageHeight(),640,480, player, 0.5F, 0.4F, 5, 0.5F, 0.8F, 5, true);
		player1.addController(Keyboard.KEY_UP, IEntity.CONTROL.JUMP, 0, IEntity.KEYBOARD.INSTANT);
		player1.addController(Keyboard.KEY_RIGHT, IEntity.CONTROL.WALKRIGHT, 0, IEntity.KEYBOARD.STABLE);
		player1.addController(Keyboard.KEY_LEFT, IEntity.CONTROL.WALKLEFT, 1, IEntity.KEYBOARD.STABLE);
		
		lastFPS = getTime();
		GameState = Game.LOADING;
		
		while(!Display.isCloseRequested()){       
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
			LoopGameLogic();
			updateWindow();
			Display.update();
			if(!gsync){
				Display.sync(60);
			}
		}
		Display.destroy();
	}

	public void LoopGameLogic(){ //TODO GAME LOGIC
		switch(GameState){
		case LOADING:
			GameState = Game.INTRO;
			break;
		case INTRO:
			ObjectLoader.PlayIntro();
			GameState = Game.MAIN_MENU;
			break;
		case MAIN_MENU:
			LogicLoop(0);
			break;
		case OPTIONS:
			LogicLoop(1);
			break;
		case INGAME:
			LogicLoop(2);
			break;
		case ENDGAME:
			LogicLoop(3);
			break;
		}
	}
	
	public void LogicLoop(int i){
		if(i == 0){
			if (Keyboard.isKeyDown(Keyboard.KEY_S)) GameState = Game.INGAME;
		}else if(i == 1){
			//Display Options
		}else if(i == 2){
			updateInGame(getDelta());
		}else if(i == 3){
			//Display Save(+Options)
		}
	}
	
	public void updateWindow(){
		updateFPS();
		while(Keyboard.next()){
			if(Keyboard.getEventKeyState()){
				if(Keyboard.getEventKey() == Keyboard.KEY_F1){
					Graphics.setDisplayMode(w,h,!Display.isFullscreen());
					System.err.println("Fullscreen is now " + Display.isFullscreen());
				}else if(Keyboard.getEventKey() == Keyboard.KEY_F2){
					vsync = !vsync;
					Display.setVSyncEnabled(vsync);
					System.err.println("Vertical sync is now " + vsync);
				}else if(Keyboard.getEventKey() == Keyboard.KEY_F3){
					gsync = !gsync;
					System.err.println("FPS sync is now " + !gsync);
				}
			}
		}
	}
	
	public void updateInGame(int delta){//TODO MAIN INGAME LOOP
		for(int x = 0; x < w/64; x++){
			drawTexture(64*x,0*64,dirt);
		}
		for(int x = 0; x < w/64; x++){
			drawTexture(64*x,1*64,grass);
		}
		for(int x = 0; x < w/64; x++){
			drawTexture(64*x,2*64,air);
		}
		for(int x = 0; x < w/64; x++){
			drawTexture(64*x,3*64,air);
		}
		for(int x = 0; x < w/64; x++){
			drawTexture(64*x,4*64,air);
		}
		for(int x = 0; x < w/64; x++){
			drawTexture(64*x,5*64,air);
		}
		for(int x = 0; x < w/64; x++){
			drawTexture(64*x,6*64,air);
		}
		for(int x = 0; x < w/64; x++){
			drawTexture(64*x,7*64,air);
		}
		player1.update(delta);
	}

	public void drawTexture(int i, int j, Texture texture){
		glPushMatrix();
		texture.bind();
		glBegin(GL_QUADS);
		glTexCoord2f(0,1);
		glVertex2f(i,j);
		glTexCoord2f(1,1);
		glVertex2f(i+texture.getTextureWidth(),j);
		glTexCoord2f(1,0);
		glVertex2f(i+texture.getTextureWidth(),j+texture.getTextureHeight());
		glTexCoord2f(0,0);
		glVertex2f(i,j+texture.getTextureHeight());
		glEnd();
		glPopMatrix();
	}
	
	private int getDelta(){
		long time = getTime();
		int delta = (int) (time - lastFrame);
		lastFrame = time;
		return delta;
	}
	
	private void updateFPS(){
		if(getTime() - lastFPS > 1000){
			Display.setTitle(" FPS: "+ fps);
			fps = 0;
			lastFPS += 1000;
		}
		fps++;
	}
	
	private void getResources(){//TODO RESOURCE LOADER
		try {
			stone = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("img/stone.png"));
			dirt = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("img/dirt.png"));
			grass = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("img/grass.png"));
			air = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("img/air.png"));
			player = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("img/player.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public long getTime(){
		return (Sys.getTime()*1000)/Sys.getTimerResolution();
	}

}
