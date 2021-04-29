package snakeGame;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import factory.SnakeFactory;
import state.State;

/**
 * Food class for the snake.
 */

public class BasicEdible implements Edible, SnakeCommons {

	private Point point;
	private Random random;
	private int height;
	private int width;
	private int size;
	private Graphics g;
	private Thread foodThread;
	private Timer timer;
	private SnakeFactory snakeFactory;

	/**
	 * Constructor for the food class.
	 * @param height of the food
	 * @param width of the food
	 * @param size of the food
	 */
	public BasicEdible(int height, int width, int size) {
		this.height = height - 7;
		this.width = width - 7;
		this.size = PIXELSIZE;
		this.random = new Random();
		snakeFactory = SnakeFactory.getInstance();
		this.position();
		snakeFactory = SnakeFactory.getInstance();
		(foodThread = new Thread(this)).start();
	}

	@Override
	public void position() {
		this.point = snakeFactory.createPoint(random.nextInt(width - 20), random.nextInt(height - 20));
	}

	@Override
	public int getX() {
		return this.point.getX();
	}

	@Override
	public int getY() {
		return this.point.getY();
	}

	@Override
	public void setSize(int size) {
		this.size = size;
	}

	@Override
	public int getWidth() {
		return size;
	}

	@Override
	public int getHeight() {
		return size;
	}

	@Override
	public void drawEdible() {
		g.setColor(Color.red);
		g.fillOval(point.getX(), point.getY(), size, size);
	}

	@Override
	public void setGraphic(Graphics g) {
		this.g = g;
	}

	@Override
	public boolean isEaten(Point p) {
		if (point.collision(p, 10)) {
			this.update();
			this.position();
			return true;
		}
		return false;
	}

	@Override
	public void update() {
		timer.cancel();
		run();
	}

	@Override
	public void run() {		
		TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                position();
                update();
            }
        };
        timer = new Timer();
        timer.schedule(timerTask, State.getState().getTimer() * 1000);
	}

	@Override
	@SuppressWarnings("deprecation")
	public void interruptFood() {
		foodThread.stop();
	}
}
