package shooterGame;

import java.awt.Color;
import java.awt.Graphics;

/**
 * defines the minor bullet powerup
 */
public class BulletBoostOne implements PowerUp, Commons {

	private double x, y;
	private int size;
	private Color powerupColor;

	/**
	 * 
	 * @param x position
	 * @param y position
	 */
	public BulletBoostOne(double x, double y) {
		this.x = x;
		this.y = y;

		init();
	}

	@Override
	public double getX() {
		return x;
	}

	@Override
	public double getY() {
		return y;
	}

	@Override
	public double getSize() {
		return size;
	}

	@Override
	public boolean update() {
		y += POWERUP_DROP_SPEED;

		if (y > HEIGHT + size) {
			return true;
		}
		return false;
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(powerupColor);
		g.fillRect((int) (x - size), (int) (y - size), 2 * size, 2 * size);
	}

	@Override
	public void effect() {
		GamePanel.player.increasePower(1);
	}

	@Override
	public void init() {
		powerupColor = Color.ORANGE;
		size = POWERUP_SIZE;
		
	}

	@Override
	public void setX(double x) {
		this.x = x;
		
	}

	@Override
	public void setY(double y) {
		this.y = y;
		
	}
}