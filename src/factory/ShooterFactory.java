package factory;

import shooterGame.BasicBullet;
import shooterGame.BasicEnemy;
import shooterGame.BossEnemy;
import shooterGame.Bullet;
import shooterGame.BulletBoostOne;
import shooterGame.BulletBoostTwo;
import shooterGame.DoubleBullet;
import shooterGame.Enemy;
import shooterGame.GamePanel;
import shooterGame.HealthBoost;
import shooterGame.HealthEnemy;
import shooterGame.Level;
import shooterGame.LevelFour;
import shooterGame.LevelOne;
import shooterGame.LevelThree;
import shooterGame.LevelTwo;
import shooterGame.Playable;
import shooterGame.Player;
import shooterGame.PowerUp;
import shooterGame.SplitEnemy;
import shooterGame.TimerSlowDown;
import shooterGame.TrippleBullet;

public class ShooterFactory {
	
	private static final ShooterFactory instance = new ShooterFactory();
	
	public static ShooterFactory getInstance() {
		return instance;
	}
	
	public Enemy createBasicEnemy() {
		return new BasicEnemy();
	}
	
	public Enemy createHealthEnemy() {
		return new HealthEnemy();
	}
	
	public PowerUp createBulletBoostOne(double x, double y) {
		return new BulletBoostOne(x,y);
	}
	
	public PowerUp createTimerSlowDown(double x, double y) {
		return new TimerSlowDown(x,y);
	}
	public PowerUp createHealthBoost(double x, double y) {
		return new HealthBoost(x,y);
	}
	public PowerUp createBulletBoostTwo(double x, double y) {
		return new BulletBoostTwo(x,y);
	}
	
	public Bullet createBasicBullet(int angle, int x, int y) {
		return new BasicBullet(angle, x, y);
	}
	
	public Bullet createDoubleBullet(int x, int y) {
		return new DoubleBullet(x, y);
	}
	
	public Bullet createTrippleBullet(int x, int y) {
		return new TrippleBullet(x, y);
	}
	
	public GamePanel createGamePanel() {
		return new GamePanel();
	}
	
	public Playable createPlayer() {
		return new Player();
	}
	
	public Enemy createSplitEnemy() {
		return new SplitEnemy();
	}
	public Enemy createBossEnemy() {
		return new BossEnemy();
	}
	
	public Level createLevelOne() {
		return new LevelOne();
	}
	public Level createLevelTwo() {
		return new LevelTwo();
	}
	public Level createLevelThree() {
		return new LevelThree();
	}
	public Level createLevelFour() {
		return new LevelFour();
	}
}
