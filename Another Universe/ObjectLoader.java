import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;


public class ObjectLoader {

	public static void PlayIntro() {
		
		
	}

	public static Texture loadTexture(String key){
		try {
			return (Texture) TextureLoader.getTexture("PNG",new FileInputStream(new File("/img/"+key+".png")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
