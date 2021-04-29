package snakeGame;

import java.awt.Graphics;

/**
 * Interface for class that will implement food for the snake.
 */
public interface Edible extends Runnable, GameObject {
	
	/**
	 * The position of the food.
	 */
	public void position();
	/**
	 * Setting the size of the food.
	 * @param size size of the food
	 */
	public void setSize(int size);
	/**
	 * Drawing the food on the board
	 */
	public void drawEdible();
	/**
	 * Saving the graphics locally.
	 * @param g, the graphics itself.
	 */
	public void setGraphic(Graphics g);
	/**
	 * Checking if the food has been eaten yet.
	 * @param p, the position of the player.
	 * @return true if player did eat the food, else it will return false if players hasnt eaten the food yet.
	 */
	public boolean isEaten(Point p);
	/**
	 * Getting the width of the food.
	 * @return the width of the food.
	 */
	public int getWidth();
	
	/**
	 * Getting the height of the food.
	 * @return value of the height.
	 */
	public int getHeight();
	/**
	 * The thread for the timer.
	 */
	public void interruptFood();
	/**
	 * Restarting the timer.
	 */
	public void update();

}
