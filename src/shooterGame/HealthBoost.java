package shooterGame;
import java.awt.Color;
import java.awt.Graphics;

/**
 * defines the health powerup, that adds +1 to player health
 */
public class HealthBoost implements PowerUp, Commons{

	private double x, y;
	private int size;
	private Color powerupColor;

	/**
	 * 
	 * @param x position
	 * @param y position
	 */
	public HealthBoost (double x, double y) {
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

		if(y > HEIGHT + size) {
			return true;
		}
		return false;
	}
	@Override
	public void draw(Graphics g) {
		g.setColor(powerupColor);
		g.fillRect((int) (x-size), (int) (y-size), 2*size, 2*size);
	}

	@Override
	public void effect() {
		GamePanel.player.gainLife();
		
	}
	@Override
	public void setX(double x) {
		this.x = x;
		
	}

	@Override
	public void setY(double y) {
		this.y = y;
		
	}
	@Override
	public void init() {
		powerupColor = Color.WHITE;
		size = POWERUP_SIZE;
		
	}
}
