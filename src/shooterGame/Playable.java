package shooterGame;

import enums.Direction;

public interface Playable extends GameObject{

	/**
	 * 
	 * @param power
	 * increases player power
	 */
	void increasePower(int power);
	/**
	 * 
	 * @return total player power
	 */
	int getPower();
	/**
	 * 
	 * @return if player is recovering
	 */
	boolean isRecovering();
	/**
	 * reduce player lives and set player recovering
	 */
	void loseLife();
	/**
	 * increase player lives
	 */
	void gainLife();
	/**
	 * 
	 * @return total player score
	 */
	int getScore();
	/**
	 * 
	 * @param i
	 * increase player score
	 */
	void addScore(int i);
	/**
	 * 
	 * @return total player lives
	 */
	int getLives();
	/**
	 * 
	 * @param firing
	 * set if player is firing or not
	 */
	void setFiring(boolean firing);
	/**
	 * while firing, add small delay between each bullet
	 */
	void isFiring();
	/**
	 * 
	 * @param direction
	 * set next player direction
	 */
	void nextMoveDirection(Direction direction);
}
