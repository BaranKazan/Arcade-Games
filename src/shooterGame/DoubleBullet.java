package shooterGame;
import java.awt.Color;
import java.awt.Graphics;
import factory.ShooterFactory;

/**
 * makes player shoot 2 bullets at one time
 */
public class DoubleBullet implements Bullet, Commons{
	
	
	private double x,y,rad,dx,dy,angle;
	private int size;
	private Color bulletColor;
	private ShooterFactory factory;
	
	/**
	 * 
	 * @param x position
	 * @param y position
	 */
	public DoubleBullet(int x, int y) {
		this.x = x;
		this.y = y;
		this.angle = BULLET_ANGLE;
		init();
	}
	
	@Override
	public void init() {
		factory = ShooterFactory.getInstance();
		GamePanel.bullets.add(factory.createBasicBullet((int) angle+5, (int) getX(),  (int) getY()));
		GamePanel.bullets.add(factory.createBasicBullet((int) (angle-5), (int) getX(),  (int) getY()));
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
		x += dx;
		y += dy;
		return false;
	}
	
	@Override
	public void draw(Graphics g) {
		g.setColor(bulletColor);
		g.fillOval((int) (x-size), (int) (y-size), 2*size, 2*size);
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
	public void setRad(double rad) {
		this.rad = rad;
		
	}

	@Override
	public double getRad() {
		return rad;
	}
}
