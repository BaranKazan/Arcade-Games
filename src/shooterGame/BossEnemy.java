package shooterGame;
import java.awt.Color;
import java.awt.Graphics;
import factory.ShooterFactory;

/**
 * defines the boss type enemy object
 */
public class BossEnemy implements Enemy, Commons{

	private double x,y,rad,speed,dx,dy, angle;
	private int size, health, amount;
	private Color enemyColor,hitColor;
	private boolean ready, dead, hit, slow;
	private Thread hitThread;
	private ShooterFactory factory;
	private Enemy enemy;

	/**
	 * constructor
	 */
	public BossEnemy() {
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
		if(health <=0) {
			dead = true;
		}
		hit = true;
	}
	
	@Override
	public void explode() {
		GamePanel.player.addScore(ENEMY_BOSS_POINTS);
		for (int i = 0; i < amount; i++) {
			enemy = factory.createHealthEnemy();
			enemy.setX(this.getX()); 
			enemy.setY(this.getY()); 
			if(!ready) {
				angle = Math.random()*ANGLE_RANDOM;
			}
			else {
				angle = Math.random()*360;
			}
			enemy.setRad(Math.toRadians(angle));
			GamePanel.enemies.add(enemy);
		}
	}
	
	@Override
	public boolean update() {
		if(slow) {
			x += dx*SLOW;
			y += dy*SLOW;
		}
		
		else {
			x += dx;
			y += dy;
		}

		if(x < size && dx < 0) {
			dx = -dx;
		}
		
		if(y < size && dy < 0) {
			dy = -dy;
		}
		
		if(x > WIDTH-size && dx >0) {
			dx = -dx;
		}
		
		if(y > COLLISON_HEIGHT-size && dy >0) {
			dy = -dy;
		}
		
		hitThread = new Thread(new Runnable() {           
			public void run() { 
				if(hit) {
					try {
						Thread.sleep(ENEMY_HIT_DELAY);
						hit = false;

					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			} 
		});
		hitThread.start();
		return false;
	}
	
	@Override
	public void draw(Graphics g) {
		if(hit) {
			g.setColor(hitColor);
			g.fillOval((int) (x-size), (int) (y-size), 4*size, 4*size);

		}
		else {
			g.setColor(enemyColor);
			g.fillOval((int) (x-size), (int) (y-size), 4*size, 4*size);
		}
	}
	
	@Override
	public void init() {
		factory = ShooterFactory.getInstance();
		enemyColor = Color.RED;
		hitColor = Color.WHITE;
		speed = ENEMY_BOSS_SPEED;
		size = ENEMY_BOSS_SIZE;
		health = ENEMY_BOSS_HEALTH;
		amount = ENEMY_BOSS_EXPLODE_AMOUNT;
		x = Math.random()*WIDTH / 2 + WIDTH / 4;
		y = -size;
		angle = Math.random()*ANGLE_RANDOM;
		rad = Math.toRadians(angle);
		dx = Math.cos(rad)*speed;
		dy = Math.sin(rad)*speed;
		dead = false;
		hit = false;
	}
}
