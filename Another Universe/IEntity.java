

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glVertex2f;

import java.lang.reflect.Method;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.opengl.Texture;

public class IEntity {
	
	public static enum CONTROL{JUMP,LEFTFLY,RIGHTFLY,UPFLY,DOWNFLY,WALKLEFT,WALKRIGHT,GRAVITY_ENABLED,GRAVITY_DISABLED,GRAVITATION,PAUSE};
	public static enum KEYBOARD{STABLE,INSTANT};
	private double x;
	private double y;
	private double xframe;
	private double yframe;
	private double yborder;
	private double xborder;
	private double width;
	private double height;
	private float gravity;
	private float speed;
	private float flyspeed;
	private float jumpspeed;
	private float speed_wl;
	private float speed_wr;
	private float speed_fl;
	private float speed_fr;
	private float speed_fu;
	private float speed_fd;
	private float attack_delay;
	private float attack_range;
	private float attack_strength;
	private float health;
	private float defence;
	private boolean gravitation;
	private boolean bordered;
	private boolean jump;
	private int move;
	private Texture texture;
	private Texture texture2;
	private Texture texture3;
	private int[] keys;
	private CONTROL[] signed;
	private int[] keys2;
	private CONTROL[] signed2;
	private Method[] sidesigned;
	private Method[] sidesigned2;
	private Object[] sidesignedargs;
	private Object[] sidesignedargs2;
	private boolean playable;
	private boolean paused;
	private int jumpstrength = 5;
	
	public IEntity(double x, double y, double width, double height, double frame_width, double frame_height,Texture texture, float gravity, float jumpspeed, int velocity,float speed, float flyspeed,int allocation, boolean playable){
		this.x = x;
		this.y = y;
		this.height = height;
		this.width = width;
		this.texture = texture;
		this.texture2 = texture;
		this.texture3 = texture;
		this.gravity = gravity;
		this.gravitation = true;
		this.speed = speed;
		this.flyspeed = flyspeed;
		this.jumpspeed = jumpspeed;
		this.keys = new int[allocation];
		this.signed = new CONTROL[allocation];
		this.keys2 = new int[allocation];
		this.signed2 = new CONTROL[allocation];
		this.sidesigned = new Method[allocation];
		this.sidesignedargs = new Object[allocation];
		this.sidesigned2 = new Method[allocation];
		this.sidesignedargs2 = new Object[allocation];
		this.xframe = frame_width;
		this.yframe = frame_height;
		this.yborder = 0;
		this.xborder = 0;
		this.move = 1;
		this.bordered = true;
		this.playable = playable;
		this.yborder = y;
		this.jumpstrength = velocity;
	}
	
	public boolean isPlayable(){
		return this.playable;
	}
	
	public void addComputerController(int position, double speedx, double speedy, int loop){//TODO Unimplemented
		if(!isPlayable() && position >= 0 && loop > 0){
			
		}
	}
	
	private void computer(int delta){//TODO Unimplemented
		if(!isPlayable()){
			
		}
	}
	
	public void setPlayable(boolean playable){
		this.playable = playable;
	}
	
	public void setMoveTextures(Texture left, Texture center, Texture right){
		this.texture = center;
		this.texture2 = right;
		this.texture3 = left;
	}
	
	public boolean isPaused(){
		return this.paused;
	}
	
	public void pauseEntity(boolean pause){
		this.paused = pause;
	}
	
	public void flushControlls(int allocation){
		if(allocation > 0){
			this.keys = null;
			this.keys2 = null;
			this.signed = null;
			this.signed2 = null;
			this.sidesigned = null;
			this.sidesigned2 = null;
			this.sidesignedargs = null;
			this.sidesignedargs2 = null;
			this.keys = new int[allocation];
			this.keys2 = new int[allocation];
			this.signed = new CONTROL[allocation];
			this.signed2 = new CONTROL[allocation];
			this.sidesigned = new Method[allocation];
			this.sidesigned2 = new Method[allocation];
			this.sidesignedargs = new Object[allocation];
			this.sidesignedargs2 = new Object[allocation];
		}
	}
	
	private void moveRestriction(boolean restricted){
		if(restricted){
			if(this.x < this.xborder){
				this.x = this.xborder;
			}
			if(this.y < this.yborder){
				this.y = this.yborder;
			}
			if(this.x + this.width > this.xframe){
				this.x = this.xframe - this.width;
			}
			if(this.y + this.height > this.yframe){
				this.y = this.yframe - this.height;
			}
		}
	}
	
	public boolean isBordered(){
		return this.bordered;
	}
	
	public void setBorder(double x, double y, double mx, double my){
		this.xborder = x;
		this.xframe = mx;
		this.yborder = y;
		this.yframe = my;
	}
	
	public double[] getBorder(){
		return new double[]{0,this.xframe,this.yborder,this.yframe};
	}
	
	public void setBordered(boolean bordered){
		this.bordered = bordered;
	}
	
	public void setMinimalY(double y){
		this.yborder = y;
	}
	
	public double getMinimalY(){
		return this.yborder;
	}
	
	public void setJumpVelocity(int velocity){
		this.jumpstrength = velocity;
	}
	
	public int getJumpVelocitity(){
		return this.jumpstrength;
	}
	
	public void setFrameWidth(double frame_width){
		this.xframe = frame_width;
	}
	
	public double getFrameWidth(){
		return this.xframe;
	}
	
	public void setFrameHeight(double frame_height){
		this.yframe = frame_height;
	}
	
	public double getFrameHeight(){
		return this.yframe;
	}
	
	public void setGravity(float gravity){
		this.gravity = gravity;
	}
	
	public float getGravity(){
		return this.gravity;
	}
	
	public void setSpeed(float speed){
		this.speed = speed;
	}
	
	public float getSpeed(){
		return this.speed;
	}
	
	public void setJumpSpeed(float speed){
		this.jumpspeed = speed;
	}
	
	public float getJumpSpeed(){
		return this.jumpspeed;
	}
	
	public void setFlySpeed(float speed){
		this.flyspeed = speed;
	}
	
	public float getFlySpeed(){
		return this.flyspeed;
	}
	
	public void setWidth(double width){
		this.width = width;
	}
	
	public double getWidth(){
		return this.width;
	}
	
	public void setHeight(double height){
		this.height = height;
	}
	
	public double getHeight(){
		return this.height;
	}
	
	public void setX(double x){
		this.x = x;
	}
	
	public double getX(){
		return this.x;
	}
	
	public void setY(double y){
		this.y = y;
	}
	
	public double getY(){
		return this.y;
	}
	
	public void setGravity(boolean gravitation){
		this.gravitation = gravitation;
	}
	
	public boolean isGravity(){
		return this.gravitation;
	}
	
	public Texture getTexture(){
		return this.texture;
	}
	
	public Texture getRightTexture(){
		return this.texture2;
	}
	
	public Texture getLeftTexture(){
		return this.texture3;
	}
	
	public void setTexture(Texture tex){
		this.texture = tex;
	}
	
	public void setRightTexture(Texture tex){
		this.texture2 = tex;
	}
	
	public void setLeftTexture(Texture tex){
		this.texture3 = tex;
	}
	
	private void draw(){
		glPushMatrix();
		if(this.move == 2){
			texture2.bind();
		}else if(this.move == 1){
			texture.bind();
		}else if(this.move == 0){
			texture3.bind();
		}else{
			texture.bind();
		}
		glBegin(GL_QUADS);
		glTexCoord2f(0,1);
		glVertex2f((float)this.x,(float)this.y);
		glTexCoord2f(1,1);
		glVertex2f((float)this.x+texture.getTextureWidth(),(float)this.y);
		glTexCoord2f(1,0);
		glVertex2f((float)this.x+texture.getTextureWidth(),(float)this.y+texture.getTextureHeight());
		glTexCoord2f(0,0);
		glVertex2f((float)this.x,(float)this.y+texture.getTextureHeight());
		glEnd();
		glPopMatrix();
	}
	
	public void update(int delta){//TODO
		if(isPlayable()){
			listener(delta);
		}
		if(!isPlayable()){
			computer(delta);
		}
		gravity(delta);
		moveRestriction(isBordered());
		draw();
		System.out.println(generateTrajectory());
	}
	
	private void addSideControlFunction(Method method, Object args, int position, KEYBOARD event){ //TODO Unimplemented
		if(position >= 0 && event.equals(KEYBOARD.STABLE)){
			sidesigned[position] = method;
			sidesignedargs[position] = args;
		}else if(position >= 0 && event.equals(KEYBOARD.INSTANT)){
			sidesigned2[position] = method;
			sidesignedargs2[position] = args;
		}
	}
	
	private void gravity(int delta){
		if(this.jump){
			if(this.y < this.yborder + jumpstrength*this.yborder/6 && this.y + this.height < this.yframe){
				if(this.y < this.yborder + jumpstrength*this.yborder/2){
					this.y += jumpspeed*delta;
				}else if(this.y < this.yborder + jumpstrength*this.yborder/3){
					this.y += (jumpspeed/2)*delta;
				}else if(this.y < this.yborder + jumpstrength*this.yborder/4){
					this.y += (jumpspeed/3)*delta;
				}else if(this.y < this.yborder + jumpstrength*this.yborder/5){
					this.y += (jumpspeed/4)*delta;
				}else{
					this.y += (jumpspeed/5)*delta;
				}
			}else{
				this.jump = false;
			}
		}else if(isGravity() && this.y > this.yborder){
			if(this.y <= this.yborder + jumpstrength*this.yborder/6){
				if(this.y < this.yborder + jumpstrength*this.yborder/2){
					this.y -= gravity*delta;
				}else if(this.y < this.yborder + jumpstrength*this.yborder/3){
					this.y -= (gravity/2)*delta;
				}else if(this.y < this.yborder + jumpstrength*this.yborder/4){
					this.y -= (gravity/3)*delta;
				}else if(this.y < this.yborder + jumpstrength*this.yborder/5){
					this.y -= (gravity/4)*delta;
				}else{
					this.y -= (gravity/5)*delta;
				}
			}else{
				this.y -= gravity*delta;
			}
		}
	}
	
	private void player(CONTROL func, int delta){//TODO
		switch(func){
		case JUMP:
			setGravity(true);
			this.jump = true;
			break;
		case LEFTFLY:
			setGravity(false);
			this.move = 0;
			this.x -= flyspeed*delta;
			break;
		case RIGHTFLY:
			this.move = 2;
			setGravity(false);
			this.x += flyspeed*delta;
			break;
		case DOWNFLY:
			setGravity(false);
			this.y -= flyspeed*delta;
			break;
		case UPFLY:
			setGravity(false);
			this.y += flyspeed*delta;
			break;
		case GRAVITATION:
			setGravity(!isGravity());
			break;
		case GRAVITY_DISABLED:
			setGravity(false);
			break;
		case GRAVITY_ENABLED:
			setGravity(true);
			break;
		case WALKLEFT:
			this.move = 0;
			setGravity(true);
			this.x -= speed*delta;
			break;
		case WALKRIGHT:
			this.move = 2;
			setGravity(true);
			this.x += speed*delta;
			break;
		case PAUSE:
			pauseEntity(!isPaused());
			break;
		}
	}
	
	public void addController(int key, CONTROL func, int position, KEYBOARD event){
		if(position >= 0 && event.equals(KEYBOARD.STABLE)){
			keys[position] = key;
			signed[position] = func;
		}else if(position >= 0 && event.equals(KEYBOARD.INSTANT)){
			keys2[position] = key;
			signed2[position] = func;
		}
	}
	
	private void listener(int delta){
		if(keys != null){
			for(int i = 0; i < keys.length; i++){
				if(Keyboard.isKeyDown(keys[i])){
					player(signed[i], delta);
					if(sidesigned[i] != null);//TODO Unimplemented
				}
			}
		}
		while(Keyboard.next()){
			if(Keyboard.getEventKeyState()){
				for(int i = 0; i < keys2.length; i++){
					if(Keyboard.getEventKey() == keys2[i]){
						player(signed2[i], delta);
						if(sidesigned2[i] != null);//TODO Unimplemented
					}
				}
			}
		}
	}
	
	private double generateTrajectory(){
		double x = Mouse.getX() - this.x;
		double y = (this.yframe - Mouse.getY()) - this.y;
		double iterator = x/y;
		return iterator;
	}
}
