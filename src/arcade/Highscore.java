package arcade;

import java.util.List;

/**
 * Interface that saves or gets the players top five score.
 * @param <E> domain of the object that will represent in the leaderboard.
 */

public interface Highscore<E> {

	/**
	 * Saves the score if it makes to the top five.
	 * @param score
	 */
	public void writeScore(int score);
	/**
	 * Returns top five players score.
	 * @return the domain that will represent in the leaderboard.
	 */
	public List<E> getScore();
	
}
