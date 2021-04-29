package snakeGame;

import enums.Direction;

/**
 * The class will hold the position of the snake and the direction it goes.
 */

public class Point implements GameObject{

	private int x;
	private int y;
	private Direction nextDir;
	
	/**
	 * Constructor that sets the position of the snake.
	 * @param x value position of the snake in the board.
	 * @param y value position of the snake in the board.
	 */
	public Point(int x, int y){
		this.x = x;
		this.y = y;
		nextDir = Direction.LEFT;
	}

	/**
	 * Changes the direction that snake goes.
	 * @param direction the snake will face.
	 */
	public void nextMoveDirection(Direction direction){
		switch (direction) {
		case LEFT:
			if (nextDir != Direction.RIGHT){
				nextDir = direction;
			}
			break;
		case RIGHT:
			if (nextDir != Direction.LEFT){
				nextDir = direction;
			}
			break;
		case UP:
			if (nextDir != Direction.DOWN){
				nextDir = direction;
			}
			break;
		case DOWN:
			if (nextDir != Direction.UP){
				nextDir = direction;
			}
			break;
		default:
			break;
		}
		
	}
	
	/**
	 * Changing the position of the snake based on the direction it faces.
	 * @param speed of the snake.
	 */
	public void move(int speed){
		switch (nextDir){
		case LEFT:
			this.x -= speed;
			break;
		case RIGHT:
			this.x += speed;
			break;
		case UP:
			this.y -= speed;
			break;
		case DOWN:
			this.y += speed;
			break;
		default:
			break;
		}
	}
	
	@Override
	public int getX(){
		return x;
	}
	
	@Override
	public int getY(){
		return y;
	}

	/**
	 * Prints the position of the snake.
	 * @return string of the snakes position.
	 */
	public String toString(){
		return x+"|"+y;
	}

	/**
	 * Checks if the snake has collided with something.
	 * @param p of the snake.
	 * @param tollerance
	 * @return true if the snake did collide, else false if it didnt.
	 */
	public boolean collision(Point p, int tollerance){
		double x = p.getX() - this.x;
		double y = p.getY() - this.y;
		double distance = Math.sqrt ((x*x) + (y*y));
		if (distance < tollerance) {
			return true;
		} else {
			return false;
		}
	}
}
