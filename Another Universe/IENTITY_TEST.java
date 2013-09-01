

import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_NICEST;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_PERSPECTIVE_CORRECTION_HINT;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.GL_SMOOTH;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBlendFunc;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL11.glClearDepth;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glHint;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glOrtho;
import static org.lwjgl.opengl.GL11.glShadeModel;
import static org.lwjgl.opengl.GL11.glViewport;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class IENTITY_TEST {
	/**
	public static void main(String[] args) {
		IENTITY_TEST app = new IENTITY_TEST();
		app.start();
	}

	private long lastFrame;
	private Texture stone;
	private Texture dirt;
	private Texture grass;
	
	public void start(){
	try {
		Display.setDisplayMode(new DisplayMode(640,480));
	} catch (LWJGLException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	Display.setTitle("Another Universe Aplha 2");
	try {
		Display.create();
	} catch (LWJGLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
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
    glViewport(0,0,640,480);
	glMatrixMode(GL_PROJECTION);
	glLoadIdentity();
	glOrtho(0, 640, 0, 480, 1, -1);
	glMatrixMode(GL_MODELVIEW);
	getResources();
	//TODO IENTITY - GAME ENTITY COMPLEX ENGINE - TEST START
	
	
	IEntity player = new IEntity(0, 0, stone.getImageWidth(), stone.getImageHeight(),640,480, stone, 0.5F, 0.5F, 0.8F, 4, true);
	player.addController(Keyboard.KEY_RIGHT, IEntity.CONTROL.RIGHTFLY, 0, IEntity.KEYBOARD.STABLE);
	player.addController(Keyboard.KEY_LEFT, IEntity.CONTROL.LEFTFLY, 1, IEntity.KEYBOARD.STABLE);
	player.addController(Keyboard.KEY_UP, IEntity.CONTROL.UPFLY, 2, IEntity.KEYBOARD.STABLE);
	player.addController(Keyboard.KEY_DOWN, IEntity.CONTROL.DOWNFLY, 3, IEntity.KEYBOARD.STABLE);
	player.setBorder(0, 0, 320, 480);
	
	IEntity player2 = new IEntity(0, 0, dirt.getImageWidth(), dirt.getImageHeight(),640,480, dirt, 0.5F, 0.5F, 0.8F, 5, false);
	player2.setBorder(320, 0, 640, 480);
	
	
	//TODO IENTITY - GAME ENTITY COMPLEX ENGINE - TEST END
	while(!Display.isCloseRequested()){       
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		int delta = getDelta();
		player.update(delta);
		player2.update(delta);
		Display.update();
		Display.sync(60);
	}
	Display.destroy();
	}
	
	private int getDelta(){
		long time = getTime();
		int delta = (int) (time - lastFrame);
		lastFrame = time;
		return delta;
	}
	
	private void getResources(){//TODO RESOURCE LOADER
		try {
			stone = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("img/stone.png"));
			dirt = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("img/dirt.png"));
			grass = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("img/grass.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public long getTime(){
		return (Sys.getTime()*1000)/Sys.getTimerResolution();
	}*/
}
