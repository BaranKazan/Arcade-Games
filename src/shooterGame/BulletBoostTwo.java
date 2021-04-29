package shooterGame;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import factory.ShooterFactory;

/**
 * defines the major bullet powerup
 */
public class BulletBoostTwo implements PowerUp, Commons{

	private double x, y;
	private int size;
	private Color powerupColor;
	private List<PowerUp> powerup;
	private ShooterFactory factory;
	
	/**
	 * 
	 * @param x position
	 * @param y position
	 */
	public BulletBoostTwo (double x, double y) {
		this.x = x;
		this.y = y;
		init();
	}
	
	@Override
	public void init() {
		powerupColor = Color.MAGENTA;
		size = POWERUP_SIZE;
		powerup = new ArrayList<PowerUp>();
		factory = ShooterFactory.getInstance();
		def();
	}

	private void def() {
		for(int i = 0; i < 2; i++) {
			powerup.add(factory.createBulletBoostOne(getX(), getY()));
		}
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
		for(int i = 0; i < powerup.size(); i++) {
			powerup.get(i).effect();
		}
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
