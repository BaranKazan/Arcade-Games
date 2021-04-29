package shooterGame;
import java.awt.Color;
import java.awt.Graphics;

/**
 * defines the slow-down powerup
 */
public class TimerSlowDown implements PowerUp, Runnable, Commons{
	private double x, y;
	private int size;
	private Color powerupColor;
	private Thread thread;

	/**
	 * 
	 * @param x position
	 * @param y position
	 */
	public TimerSlowDown (double x, double y) {
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
		for(int j = 0; j < GamePanel.enemies.size(); j++) {
			 GamePanel.enemies.get(j).setSlow(true);
		}
		thread.start();
	}
	
	@Override
	public void run() {
		try {
			Thread.sleep(SLOWDOWN_TIMER);
			for(int j = 0; j < GamePanel.enemies.size(); j++) {
				GamePanel.enemies.get(j).setSlow(false);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void init() {
		powerupColor = Color.PINK;
		size = POWERUP_SIZE;
		thread = new Thread(this);

		
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
