package shooterGame;
import java.awt.Color;
import java.awt.Graphics;

/**
 * Class that defines the bullets a player can shoot at enemies
 */
public class BasicBullet implements Bullet, Commons{
	
	private double x,y,rad,speed,dx,dy,angle;
	private int size;
	private Color bulletColor;
	
	/**
	 * 
	 * @param angle at which bullet is fired
	 * @param x player position
	 * @param y player position
	 */
	public BasicBullet(int angle, int x, int y) {
		this.x = x;
		this.y = y;
		this.angle = angle;
		init();
	}
	
	/**
	 * 
	 * @param x player position
	 * @param y player position
	 */
	public BasicBullet(int x, int y) {
		this.x = x;
		this.y = y;
		this.angle = BULLET_ANGLE;
		init();
	}
	
	@Override
	public void init() {
		size = BULLET_SIZE;
		rad = Math.toRadians(angle);
		speed = BULLET_SPEED;
		dx = Math.cos(rad)*speed;
		dy = Math.sin(rad)*speed;
		bulletColor = Color.RED;
		GamePanel.bullets.add(this);
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