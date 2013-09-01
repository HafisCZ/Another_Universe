import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public class Graphics {

	public static void setDisplayMode(int w, int h, boolean fc){
		if((Display.getDisplayMode().getWidth() == w) && (Display.getDisplayMode().getHeight() == h) && (Display.isFullscreen() == fc)){
			return;
		}
		try{
			DisplayMode targetDisplayMode = null;
			if(fc){
				DisplayMode[] modes = Display.getAvailableDisplayModes();
				int freq = 0;
				for (int i=0;i<modes.length;i++) {
					DisplayMode current = modes[i];
					if ((current.getWidth() == w) && (current.getHeight() == h)) {
						if ((targetDisplayMode == null) || (current.getFrequency() >= freq)) {
							if ((targetDisplayMode == null) || (current.getBitsPerPixel() > targetDisplayMode.getBitsPerPixel())) {
								targetDisplayMode = current;
								freq = targetDisplayMode.getFrequency();
							}
						}
						if ((current.getBitsPerPixel() == Display.getDesktopDisplayMode().getBitsPerPixel()) && (current.getFrequency() == Display.getDesktopDisplayMode().getFrequency())) {
							targetDisplayMode = current;
							break;
						}
					}
				}
			}else{
				targetDisplayMode = new DisplayMode(w,h);
			}
			if (targetDisplayMode == null) {
				System.out.println("Failed to find value mode: "+w+"x"+h+" fs="+fc);
				return;
			}
			Display.setDisplayMode(targetDisplayMode);
			Display.setFullscreen(fc);
		}catch(LWJGLException e){
			System.out.println("Unable to setup mode "+w+"x"+h+" fullscreen="+fc + e);
		}
	}
}
