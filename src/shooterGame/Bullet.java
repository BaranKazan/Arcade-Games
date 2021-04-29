package shooterGame;

public interface Bullet extends GameObject{
	
	/**
	 * @param rad sets the radius value
	 */
	void setRad(double rad);
	
	/**
	 * @return the radius
	 */
	double getRad();
}
