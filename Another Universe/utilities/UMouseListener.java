package utilities;
import org.lwjgl.Sys;
import org.lwjgl.input.Mouse;

public class UMouseListener {
	
	public static enum MouseAction{LEFT,MIDDLE,RIGHT}
	private long lastAction1;
	private long lastAction2;
	private long lastAction3;
	
	public UMouseListener(){
		
	}
	
	public long getAction(MouseAction action){
		switch(action)
		{
			case LEFT:
				return lastAction1;
			case MIDDLE:
				return lastAction2;
			case RIGHT:
				return lastAction3;
			default:
				return Sys.getTime();
		}
	}
	
	public void markAction(MouseAction action){
		switch(action)
		{
		case LEFT:
			lastAction1 = Sys.getTime();
			break;
		case MIDDLE:
			lastAction2 = Sys.getTime();
			break;
		case RIGHT:
			lastAction3 = Sys.getTime();
			break;
		}
	}
	
	public  MouseAction checkAction(){
		if(Mouse.isButtonDown(0)){
			return MouseAction.LEFT;
		}else if(Mouse.isButtonDown(2)){
			return MouseAction.MIDDLE;
		}else if(Mouse.isButtonDown(1)){
			return MouseAction.RIGHT;
		}else{
			return null;
		}
	}
	
	public boolean checkLoop(MouseAction action){
		switch(action){
			case LEFT:
				if(Mouse.isButtonDown(0)){
					markAction(MouseAction.LEFT);
					return true;
				}else{
					return false;
				}
			case MIDDLE:
				if(Mouse.isButtonDown(2)){
					markAction(MouseAction.MIDDLE);
					return true;
				}else{
					return false;
				}
			case RIGHT:
				if(Mouse.isButtonDown(1)){
					markAction(MouseAction.RIGHT);
					return true;
				}else{
					return false;
				}
			default:
				return false;
		}
	}
}
