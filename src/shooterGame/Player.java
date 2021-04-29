package shooterGame;

import java.awt.Color;
import java.awt.Graphics;
import enums.Direction;
import factory.ShooterFactory;

/**
 * defines the player object
 */
public class Player implements Commons, Playable {

	private double x, y, size, dx, dy, speed;
	private int lives, score, power;
	private boolean firing, recovering;
	private long firingTimer, firingDelay;
	private Color playerColor, recoveryColor;
	private Direction nextDir;
	private ShooterFactory factory;

	/**
	 * constructor
	 */
	public Player() {
		init();
	}

	@Override
	public void increasePower(int power) {
		this.power += power;
	}

	@Override
	public int getPower() {
		return power;
	}

	@Override
	public boolean isRecovering() {
		return recovering;
	}

	@Override
	public void loseLife() {
		recovering = true;
		lives--;
	}

	@Override
	public void gainLife() {
		lives++;
	}

	@Override
	public int getScore() {
		return score;
	}

	@Override
	public void addScore(int i) {
		score += i;
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
	public int getLives() {
		return lives;
	}

	@SuppressWarnings("incomplete-switch")
	public void nextMoveDirection(Direction direction){
		switch (direction) {
		case LEFT:
			nextDir = direction;
			break;
		case RIGHT:
			nextDir = direction;
			break;
		case STOP:
			nextDir = null;
			break;
		}
	}

	@Override
	public void setFiring(boolean firing) {
		this.firing = firing;
	}

	@Override
	public boolean update() {
		if (Direction.LEFT == nextDir) {
			dx = -speed;
		}

		if (Direction.RIGHT == nextDir) {
			dx = speed;
		}

		x += dx;
		y += dy;

		if (x < size)
			x = size;

		if (x > WIDTH - size)
			x = WIDTH - size;

		dx = 0;
		dy = 0;

		if (firing) {
			isFiring();
		}

		Thread recoveryThread = new Thread(new Runnable() {
			public void run() {
				while (recovering) {
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					recovering = false;
				}
			}
		});
		recoveryThread.start();
		return false;
	}

	@Override
	public void isFiring() {
		long elapsed = (System.nanoTime() - firingTimer) / 1000000;
		if (elapsed > firingDelay) {
			firingTimer = System.nanoTime();

			if (power < 5) {
				factory.createBasicBullet(BULLET_ANGLE, (int) x, (int) y);
			} else if (power < 10 && power > 4) {
				factory.createDoubleBullet((int) x, (int) y);
			} else if (power > 9) {
				factory.createTrippleBullet((int) x, (int) y);
			}
		}
	}

	@Override
	public void draw(Graphics g) {
		if (recovering) {
			g.setColor(recoveryColor);
			g.fillOval((int)(x - size), (int)(y - size), (int)(2 * size), (int)(2 * size));
		} else {
			g.setColor(playerColor);
			g.fillOval((int)(x - size), (int)(y - size), (int)(2 * size), (int)(2 * size));
		}
	}

	@Override
	public void init() {
		factory = ShooterFactory.getInstance();
		x = WIDTH / 2;
		y = COLLISON_HEIGHT;
		size = PLAYER_SIZE;
		dx = 0;
		dy = 0;
		speed = PLAYER_SPEED;
		lives = PLAYER_LIVES;
		playerColor = Color.WHITE;
		recoveryColor = Color.RED;
		firing = false;
		firingTimer = System.nanoTime();
		firingDelay = PLAYER_FIRING_DELAY;
		recovering = false;
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