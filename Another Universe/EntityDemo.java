import org.lwjgl.Sys;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

import utilities.JTextRenderer;


public class EntityDemo {

	private long lastFrame;
	public EntityDemo(){
		try{
			Display.setDisplayMode(new DisplayMode(640,480));
			Display.setTitle("Entity Demo");
			Display.create();
		}catch(Exception e){
			e.printStackTrace();
		}
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, 640, 480, 0, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		lastFrame = getTime();
		while(!Display.isCloseRequested()){
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
			int delta = getDelta();
			JTextRenderer.drawString("ANOTHER UNIVERSE", 320, 240);
			Display.update();
			Display.sync(60);
		}
		Display.destroy();
	}
	
	public static void main(String[] args){
		new EntityDemo();
	}
	
	private int getDelta(){
		long time = getTime();
		int delta = (int) (time - lastFrame);
		lastFrame = time;
		return delta;
	}
	
	public long getTime(){
		return (Sys.getTime()*1000)/Sys.getTimerResolution();
	}
}
