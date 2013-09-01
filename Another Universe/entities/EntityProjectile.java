package entities;

import org.lwjgl.opengl.GL11;

public class EntityProjectile extends AbstractMoveableEntity {

	public EntityProjectile(double x, double y, double width, double height) {
		super(x, y, width, height);
	}

	@Override
	public void draw() {
		GL11.glColor3f(1.0F, 1.0F, 0.5F);
		GL11.glRectd(x, y, x + width, y + height);
		
	}
}