package shooterGame;

import java.awt.Color;
import java.awt.Graphics;

/**
 * defines the basic enemy type
 */
@SuppressWarnings("unused")
public class BasicEnemy implements Enemy, Commons {

	private double x, y, rad, speed, dx, dy, angle;
	private int size, health;
	private Color enemyColor;
	private boolean dead, slow;

	/**
	 * constructor
	 */
	public BasicEnemy() {
		init();
	}

	@Override
	public void setSlow(boolean slow) {
		this.slow = slow;
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
	public boolean isDead() {
		return dead;
	}

	@Override
	public void isHit() {
		health--;
		dead = true;
	}

	@Override
	public void explode() {
		GamePanel.player.addScore(ENEMY_BASIC_POINTS);
	}

	@Override
	public boolean update() {
		if (slow) {
			x += dx * SLOW;
			y += dy * SLOW;
		}

		else {
			x += dx;
			y += dy;
		}

		if (x < size && dx < 0) {
			dx = -dx;
		}

		if (y < size && dy < 0) {
			dy = -dy;
		}

		if (x > WIDTH - size && dx > 0) {
			dx = -dx;
		}

		if (y > COLLISON_HEIGHT - size && dy > 0) {
			dy = -dy;
		}
		return false;
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(enemyColor);
		g.fillOval((int) (x - size), (int) (y - size), 4 * size, 4 * size);
	}

	@Override
	public void init() {
		enemyColor = Color.YELLOW;
		speed = ENEMY_BASIC_SPEED;
		size = ENEMY_BASIC_SIZE;
		health = ENEMY_BASIC_HEALTH;
		x = Math.random() * WIDTH / 2 + WIDTH / 4;
		y = -size;
		angle = Math.random() * ANGLE_RANDOM;
		rad = Math.toRadians(angle);
		dx = Math.cos(rad) * speed;
		dy = Math.sin(rad) * speed;
		dead = false;
	}
}
