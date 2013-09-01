package utilities;


public class GObjectManager {
	
	public static float getXBounds(float xpos, int width, int nwidth){
		if (xpos < nwidth/2) xpos = nwidth/2;
		if (xpos > width-nwidth/2) xpos = width-nwidth/2;
		return xpos;
	}
	
	public static float getYBounds(float ypos, int height, int nheight){
		if (ypos < nheight/2) ypos = nheight/2;
		if (ypos > height-nheight/2) ypos = height-nheight/2;
		return ypos;
	}
}
