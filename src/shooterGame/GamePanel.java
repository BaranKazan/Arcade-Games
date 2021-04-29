package shooterGame;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.Timer;

import arcade.Highscore;
import command.Broker;
import enums.Direction;
import factory.ShooterFactory;
import view.ShooterPlayerScoreView;

/**
 * connects the different gameobjects to create a game session
 */
@SuppressWarnings("serial")
public class GamePanel extends JPanel implements KeyListener, Commons, ActionListener{

	private Timer timer;
	private boolean remove;
	public static Playable player;
	private PowerUp powerUp;
	private Enemy enemy;
	private Bullet bullet;
	public static List<Bullet> bullets;
	public static List<Enemy> enemies;
	public static List<PowerUp> powerups;
	private double playerX, playerY, playerSize, powerUpX, powerUpY, powerUpSize, enemyX, enemyY, enemySize, bulletX, bulletY, bulletSize, rand;
	private double dx, dy, dist;
	private Color background, playerZone;
	private ShooterFactory gameObjectFactory;
	private Highscore<ShooterPlayerScoreView> highscore;

	/**
	 * constructor
	 */
	public GamePanel() {
		highscore = ShooterHighscore.getInstance();
		setFocusable(true);
		requestFocus();
		addKeyListener(this);
		init();
	}

	/**
	 * initiates variables and starts session
	 */
	private void init() {
		gameObjectFactory = ShooterFactory.getInstance();
		player = gameObjectFactory.createPlayer();
		bullets = new ArrayList<Bullet>();
		enemies = new ArrayList<Enemy>();
		powerups = new ArrayList<PowerUp>();
		timer = new Timer(1, this);
		background = Color.black;
		playerZone = Color.blue;
		timer.start();
		startGame();
	}

	/**
	 * uodates state of the game session
	 */
	public void update() {
		getPlayerData();
		bulletRemove();
		powerupRemove();
		enemyUpdate();
		bulletEnemyCollision();
		powerupDrop();
		playerRecovering();
		playerPowerupCollision();
	}

	/**
	 * updates the enemy state
	 */
	private void enemyUpdate() {
		for(int i = 0; i < enemies.size(); i++) {
			enemies.get(i).update();
		}
	}

	/**
	 * updates player state
	 */
	private void getPlayerData() {
		player.update();
		playerX = player.getX();
		playerY = player.getY();
		playerSize = player.getSize()*2;
	}

	/**
	 * updates powerups to remove those who fall out of range or collide with player
	 */
	private void powerupRemove() {
		for(int i = 0; i < powerups.size(); i++) {
			remove = powerups.get(i).update();

			if(remove) {
				powerups.remove(i);
				i--;
			}
		}
	}

	/**
	 * updates bullets to remove those who fall out of range or collide with enemy
	 */
	private void bulletRemove() {
		for(int i = 0; i < bullets.size(); i++) {
			remove = bullets.get(i).update();
			if(remove) {
				bullets.remove(i);
				i--;
			}
		}
	}

	/**
	 * update if bullet collides with an enemy
	 */
	private void bulletEnemyCollision() {
		for(int i = 0; i < bullets.size(); i++) {
			bullet = bullets.get(i);
			bulletX = bullet.getX();
			bulletY = bullet.getY();
			bulletSize = bullet.getSize();

			for(int j = 0; j < enemies.size(); j++) {
				enemy = enemies.get(j);
				enemyX = enemy.getX();
				enemyY = enemy.getY();
				enemySize = enemy.getSize()*2;
				dx = bulletX - enemyX;
				dy = bulletY - enemyY;
				dist = Math.sqrt(dx*dx+dy*dy);

				if(dist < bulletSize*2 + enemySize) {
					enemy.isHit();
					bullets.remove(i);
					i--;
					break;
				}
			}
		}
	}

	/**
	 * updates if player collides with a powerup
	 */
	private void playerPowerupCollision() {
		for(int i = 0; i < powerups.size(); i++) {
			powerUp = powerups.get(i);
			powerUpX = powerUp.getX();
			powerUpY = powerUp.getY();
			powerUpSize = powerUp.getSize();
			dx = playerX - powerUpX;
			dy = playerY - powerUpY;
			dist = Math.sqrt(dx*dx+dy*dy);

			if(dist < playerSize*2 + powerUpSize) {
				powerUp.effect();
				powerups.remove(i);
				i--;
			}
		}
	}

	/**
	 * updates chance of each powerup to drop
	 */
	private void powerupDrop() {
		for(int i = 0; i < enemies.size(); i++) {
			if(enemies.get(i).isDead()) {
				enemy = enemies.get(i);
				rand = Math.random()*20;

				if(rand < 5) {
					powerups.add(gameObjectFactory.createHealthBoost(enemy.getX(), enemy.getY()));
				}
				else if(rand < 10 && rand > 4) {
					powerups.add(gameObjectFactory.createBulletBoostOne(enemy.getX(), enemy.getY()));
				}
				else if(rand < 15 && rand > 9) {
					powerups.add(gameObjectFactory.createBulletBoostTwo(enemy.getX(), enemy.getY()));
				}
				else if(rand < 21 && rand > 14) {
					powerups.add(gameObjectFactory.createTimerSlowDown(enemy.getX(), enemy.getY()));
				}

				enemies.remove(i);
				i--;
				enemy.explode();
			}
		}
	}

	/**
	 * updates if player is recovering from a collision
	 */
	private void playerRecovering() {
		if(!player.isRecovering()) {
			for(int i = 0; i < enemies.size(); i++) {
				enemy = enemies.get(i);
				enemyX = enemy.getX();
				enemyY = enemy.getY();
				enemySize = enemy.getSize();
				dx = playerX - enemyX;
				dy = playerY - enemyY;
				dist = Math.sqrt(dx*dx+dy*dy);
				if(dist < playerSize + enemySize*3) {
					player.loseLife();
				}
			}
		}
	}

	/**
	 * 
	 * @param g draws objects
	 */
	public void draw(Graphics g) {
		drawBackground(g);
		drawPlayer(g);
		drawBullets(g);
		drawEnemies(g);
		drawPowerups(g);
		drawPlayerLives(g);
		drawPlayerPowerBar(g);
		drawScore(g);
		checkGameOver(g);
	}

	/**
	 * 
	 * @param g 
	 * checks if game is over
	 */
	private void checkGameOver(Graphics g) {
		if(player.getLives() <= 0) {
			GameOver(g);
		}
	}

	/**
	 * 
	 * @param g 
	 * draws background
	 */
	private void drawBackground(Graphics g) {
		g.setColor(background);
		g.fillRect(0, 0, Commons.WIDTH, Commons.HEIGHT);
		g.setColor(playerZone);
		g.fillRect(0, COLLISON_HEIGHT, Commons.WIDTH, Commons.HEIGHT/10);
	}
	/**
	 * 
	 * @param g
	 * draws player score
	 */
	private void drawScore(Graphics g) {
		g.setColor(Color.white);
		g.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		g.drawString("Score: " + player.getScore(), Commons.WIDTH - 100, 30);
	}

	/**
	 * 
	 * @param g
	 * draws player powerbar
	 */
	private void drawPlayerPowerBar(Graphics g) {
		for(int i = 0; i < player.getPower(); i++) {
			if(player.getPower() < POWERBAR_SIZE) {
				g.setColor(Color.white);
				g.fillRect(20, 40, player.getPower()*8, 8);
			}
			else {
				g.setColor(Color.YELLOW);
				g.fillRect(20, 40, POWERBAR_SIZE*8, 8);
			}
		}
	}

	/**
	 * 
	 * @param g
	 * draws player lives
	 */
	private void drawPlayerLives(Graphics g) {
		for(int i = 0; i < player.getLives(); i++) {
			g.setColor(Color.white);
			g.fillOval(20+(25*i), 20, (int) player.getSize()*2, (int) player.getSize()*2);
		}
	}

	/**
	 * 
	 * @param g
	 * draws powerups
	 */
	private void drawPowerups(Graphics g) {
		for(int i = 0; i < powerups.size(); i++) {
			powerups.get(i).draw(g);
		}
	}

	/**
	 * 
	 * @param g
	 * draws enemies
	 */
	private void drawEnemies(Graphics g) {
		for(int i = 0; i < enemies.size(); i++) {
			enemies.get(i).draw(g);
		}
	}

	/**
	 * 
	 * @param g
	 * draws bullets
	 */
	private void drawBullets(Graphics g) {
		for(int i = 0; i < bullets.size(); i++) {
			bullets.get(i).draw(g);
		}
	}

	/**
	 * 
	 * @param g
	 * draws player
	 */
	private void drawPlayer(Graphics g) {
		player.draw(g);
	}

	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		this.setBackground(Color.black);
		this.setForeground(Color.black);
		draw(g);
	}

	/**
	 * starts the game at level one
	 */
	public void startGame() {
		enemies.clear();
		new LevelOne();
	}

	/**
	 * 
	 * @param g
	 * draws game over screen
	 */
	private void GameOver(Graphics g) {
		enemies.clear();
		bullets.clear();
		powerups.clear();
		int boardHeightValue = 0;
		String message = "Game over\nPress ESC button to exit the game.\nScore: " + player.getScore();
		Font font = new Font("Times New Roman", Font.BOLD, 18);
		FontMetrics metrics = getFontMetrics(font);
		g.setColor(background);
		g.fillRect(0, 0, Commons.WIDTH, Commons.HEIGHT);
		g.setColor(Color.red);
		g.setFont(font);
		for(String s : message.split("\n")) {
			g.drawString(s, (Commons.WIDTH - metrics.stringWidth(s)) / 2,
					(Commons.HEIGHT / 2) + boardHeightValue);
			boardHeightValue += 25;
		}
		
		timer.stop();
		try {
			this.finalize();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	@Override
	public void keyTyped(KeyEvent key) {}

	@Override
	public void keyPressed(KeyEvent key) {
		switch (key.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			player.nextMoveDirection(Direction.LEFT);
			break;
		case KeyEvent.VK_RIGHT:
			player.nextMoveDirection(Direction.RIGHT);
			break;
		case KeyEvent.VK_DOWN:
			player.setFiring(true);
			break;
		case KeyEvent.VK_ESCAPE:
			highscore.writeScore(player.getScore());
			Broker.undoOperation();
			break;
		}
	}
	
	@Override
	public void keyReleased(KeyEvent key) {
		switch (key.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			player.nextMoveDirection(Direction.STOP);
			break;
		case KeyEvent.VK_RIGHT:
			player.nextMoveDirection(Direction.STOP);
			break;
		case KeyEvent.VK_DOWN:
			player.setFiring(false);
			break;
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		repaint();
		update();
	}
}