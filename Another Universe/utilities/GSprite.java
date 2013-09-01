package utilities;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glTranslatef;
import static org.lwjgl.opengl.GL11.glVertex2f;

import java.io.IOException;

public class GSprite {
	private GTexture texture;
	private int w;
	private int h;
	
	public GSprite(GTextureLoader loader, String ref){
		try{
			texture = loader.getTexture("spaceinvaders/" + ref);
		}catch(IOException ioe){
			ioe.printStackTrace();
			System.exit(-1);
		}
	}
	
	public int getWidth(){
		return texture.getImageWidth();
	}
	
	public int getHeight(){
		return texture.getImageHeight();
	}
	
	public void draw(int x, int y){
		glPushMatrix();
		texture.bind();
		glTranslatef(x,y,0);
		glBegin(GL_QUADS);
		{
			glTexCoord2f(0, 0);
			glVertex2f(0, 0);
			glTexCoord2f(0, texture.getHeight());
			glVertex2f(0, h);
			glTexCoord2f(texture.getWidth(), texture.getHeight());
			glVertex2f(w, h);
			glTexCoord2f(texture.getWidth(), 0);
			glVertex2f(w, 0);
		}
		glEnd();
		glPopMatrix();
	}
	
}
