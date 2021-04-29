package shooterGame;
import java.awt.Graphics;

public interface GameObject {
	
	/**
	 * @return x position
	 */
	double getX();
	
	/**
	 * @return y position
	 */
	double getY();
	
	/**
	 * @return size of object
	 */
	double getSize();
	
	/**
	 * @return boolean
	 * updates the positioning and state
	 */
	boolean update();
	
	/**
	 * @param g draws the object
	 */
	void draw(Graphics g);
	
	/**
	 * initiates the variables
	 */
	void init();
	
	/**
	 * @param x sets the x value
	 */
	void setX(double x);
	
	/**
	 * @param y sets the y value
	 */
	void setY(double y);
}
