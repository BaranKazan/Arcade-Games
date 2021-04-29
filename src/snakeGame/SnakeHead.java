package snakeGame;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import enums.Direction;
import factory.SnakeFactory;

/**
 * The class defines the head of the snake. 
 */

public class SnakeHead implements SnakeCommons{
	
	private List<SnakePart> parts;
	private Point pos;
	private SnakeFactory snakeFactory;
	
	/**
	 * Constructor that sets the position of the snake and adds starting body parts.
	 */
	public SnakeHead(){
		snakeFactory = SnakeFactory.getInstance();
		this.pos = snakeFactory.createPoint(200, 200);
		this.parts = new ArrayList<SnakePart>();
		snakeFactory = SnakeFactory.getInstance();
		parts.add(snakeFactory.createSnakePart(pos));
		parts.add(snakeFactory.createSnakePart(parts.get(0).getPosition()));
	}
	
	/**
	 * Painting the snakes head itself.
	 * @param g that will be required to draw the snake.
	 * @return the graphics itself.
	 */
	public Graphics paintSnake(Graphics g){
		g.setColor(Color.red);
		g.fillRect(pos.getX(), pos.getY(), PIXELSIZE, PIXELSIZE);
		g.setColor(Color.green);
		for (SnakePart part: parts){
			g.fillRect(part.getPosition().getX(), part.getPosition().getY(), PIXELSIZE, PIXELSIZE);
		}
		return g;
	}
	
	public boolean fieldBounds(int width, int height){
		if (pos.getX() >= width-10 || pos.getX() <= -10 ||
			pos.getY() >= height-10 || pos.getY() <= -10){
			return true;
		}
		return false;
	}
	
	/**
	 * Checks of the snake collided with itself.
	 * @return true if snake collided on itself, false if snake didn't.
	 */
	public boolean collisionSelf(){
		for (SnakePart part: parts){
			if (part.collision(pos)) 
				return true;
		}
		return false;
	}
	
	/**
	 * Adding new body parts for each timer snake collects food.
	 * @param n is how many parts it should add.
	 */
	public void addParts(int n){
		for (int i = 0; i < n; i++){
			parts.add(snakeFactory.createSnakePart(parts.get(parts.size()-1 ).getPosition()));
		}
	}
	
	/**
	 * Getting the position of the snakes head.
	 * @return point class.
	 */
	public Point getPoint(){
		return this.pos;
	}
	
	/**
	 * Changing the direction of the snakes head to left.
	 */
	public void moveLeft(){
		pos.nextMoveDirection(Direction.LEFT);
	}
	
	/**
	 * Changing the direction of the snakes head to right.
	 */
	public void moveRight(){
		pos.nextMoveDirection(Direction.RIGHT);
	}
	
	/**
	 * Changing the direction of the snakes head to up.
	 */
	public void moveUp(){
		pos.nextMoveDirection(Direction.UP);
	}
	
	/**
	 * Changing the direction of the snakes head to down.
	 */
	public void moveDown(){
		pos.nextMoveDirection(Direction.DOWN);
	}
	
	/**
	 * Moving the snakes head and its body parts.
	 */
	public void moveAll(){
		Point tmp = snakeFactory.createPoint(pos.getX(), pos.getY());
		pos.move(PIXELSIZE);
		for (SnakePart part: parts){
			part.setPosition(tmp);
			tmp = part.getOldPosition();
		}
	}
	
	/**
	 * Clears the snakes body but the head still stays.
	 */
	public void endGame() {
		parts.clear();
	}
}
