package snakeGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;
import javax.swing.Timer;

import arcade.Highscore;
import command.Broker;
import factory.SnakeFactory;
import state.State;
import view.SnakePlayerScoreView;

/**
 * The JPanel for the snake game and the board itself.
 */

@SuppressWarnings("unused")
public class SnakeGamePanel extends JPanel implements ActionListener, SnakeCommons {

	private static final long serialVersionUID = 1L;
	private int score;
	public int speed;
	private SnakeHead snake;
	private Edible basicEdible;
	private boolean inGame = true;
	private Timer gameTimer;
	private Highscore<SnakePlayerScoreView> highscore;
	private Graphics g;
	private int time;
	KeyAdapter keyAdapter;
	private State state;
	private SnakeFactory snakeFactory;

	/**
	 * Constructor that initializes the game.
	 * @param width of the board.
	 * @param height of the board.
	 */
	public SnakeGamePanel(int width, int height) {
		super();
		snakeFactory = SnakeFactory.getInstance();
		super.setSize(width, height);
		state = State.getState();
		setSpeed();
		setTimer();
		highscore = SnakeHighscore.getInstance();
		snake = snakeFactory.createSnakeHead();
		basicEdible = snakeFactory.createBasicEdible(this.getSize().height, this.getSize().width, PIXELSIZE);
		init();
		setFocusable(true);
		keyAdapter = new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_LEFT:
					snake.moveLeft();
					break;
				case KeyEvent.VK_RIGHT:
					snake.moveRight();
					break;
				case KeyEvent.VK_UP:
					snake.moveUp();
					break;
				case KeyEvent.VK_DOWN:
					snake.moveDown();
					break;
				case KeyEvent.VK_ESCAPE:
					Broker.undoOperation();
					highscore.writeScore(score);
					break;
				}

				repaint();
			}
		};
		addKeyListener(keyAdapter);

	}

	/**
	 * Setting the speed of the snake.
	 */
	private void setSpeed() {
		this.speed = state.getSpeed();
	}

	/**
	 * Setting the time for the timer. 
	 */
	private void setTimer() {
		this.time = 1000 * state.getTimer();
	}

	/**
	 * Painting the game itself.
	 * @param g that will be required to draw the content of the game.
	 */
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		this.g = g;
		basicEdible.setGraphic(g);
		g.drawString("Score: " + this.score, 15, 15);
		if (!inGame) {
			endGame(g);
		}
		snake.moveAll();
		snake.paintSnake(g);
		basicEdible.drawEdible();
	}

	/**
	 * Draw the end game screen if the player loses the game.
	 * @param g that will be required to draw the content.
	 */
	public void endGame(Graphics g) {
		int boardHeightValue = 0;
		String message = "Game over\nPress ESC button to exit the game.";
		Font font = new Font("Times New Roman", Font.BOLD, 14);
		FontMetrics metrics = getFontMetrics(font);
		g.setColor(Color.red);
		g.setFont(font);
		for(String s : message.split("\n")) {
			g.drawString(s, (BOARDWIDTH - metrics.stringWidth(s)) / 2,
					(BOARDHEIGHT / 2) + boardHeightValue);
			boardHeightValue += 25;
		}
		snake.endGame();
	}

	/**
	 * Checks if the player has collided with something. Such as food, itself or the end line of the board.
	 */
	private void collision() {
		if (basicEdible.isEaten(snake.getPoint())) {
			snake.addParts(4);
			this.score += 10;
		}
		if (snake.collisionSelf()) {
			this.inGame = false;
		}
		if (snake.fieldBounds(this.getSize().width, this.getSize().height)) {
			this.inGame = false;
		}
		if (!inGame) {
			removeKeyListener(keyAdapter);
			addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
						Broker.undoOperation();
						highscore.writeScore(score);
					}
				}
			});
			gameTimer.stop();
		}
	}

	/**
	 * Getting the score the player has collected.
	 * @return the points that player has been collected.
	 */
	public int getScore() {
		return score;
	}

	/**
	 * Sets the background of the game and starts the timer.
	 */
	public void init() {
		this.setBackground(state.getBackgroundColor());
		this.setForeground(state.getTextColor());
		gameTimer = new Timer(speed, this);
		gameTimer.start();
		repaint();
	}

	/**
	 * Checks constantly if the player has collided with something.
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (inGame == true) {
			collision();
		}
		repaint();
	}
}
