package snakeGame;

/**
 * The class is the body part of the snake.
 */

public class SnakePart implements SnakeCommons{
	
	private Point newPoint;
	private Point oldPoint;
	
	/**
	 * Adds the position of of the obdy part.
	 * @param pos
	 */
	public SnakePart(Point pos){
		this.newPoint = new Point(pos.getX(), pos.getY()+PIXELSIZE);
		this.oldPoint = null;
	}
	
	/**
	 * Getting the new position of the body part.
	 * @return the position object.
	 */
	public Point getPosition(){
		return this.newPoint;
	}
	
	/**
	 * Getting the old position of the body part.
	 * @return the position object.
	 */
	public Point getOldPosition(){
		return this.oldPoint;
	}
	
	/**
	 * Sets the position of the body part.
	 * @param p
	 */
	public void setPosition(Point p){
		this.oldPoint = this.newPoint;
		this.newPoint = p;
	}
	
	/**
	 * Checks the collision of the body part.
	 * @param p position.
	 * @return true if it collided, false if it didnt.
	 */
	public boolean collision(Point p){
		return this.newPoint.collision(p, 10);
	}
}
