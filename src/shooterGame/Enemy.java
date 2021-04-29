package shooterGame;
public interface Enemy extends GameObject{
	
	/**
	 * @param slow set slow to change enemy speed
	 */
	void setSlow(boolean slow);
	
	/**
	 * @return if enemy is dead or not
	 */
	boolean isDead();
	
	/**
	 * control if the enemy is hit and if eney dies
	 */
	void isHit();
	
	/**
	 * increase score once enemy has died
	 */
	void explode();
	
	/**
	 * @param rad set the radius value
	 */
	void setRad(double rad);
}
