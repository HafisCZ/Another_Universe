package entities;

import org.lwjgl.opengl.GL11;

public class EntityShip extends AbstractEntity{

	public EntityShip(double x, double y, double width, double height) {
		super(x, y, width, height);
	}

	@Override
	public void draw() {
		GL11.glColor3f(0.1F, 0.1F, 0.1F);
		GL11.glRectd(x, y, x + width, y + height);
		
	}

	@Override
	public void update(int delta) {
		// TODO Auto-generated method stub
		
	}
}