package view;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Class that hold the position and the score of top five player in Shooter leaderboard.
 *
 */

@SuppressWarnings("serial")
public class ShooterPlayerScoreView extends JPanel{
	
	private int position;
	private int score;
	private JLabel label;
	
	/**
	 * Initializes the view of the label.
	 */
	public void initView() {
		label = new JLabel(Integer.toString(position) + " " + Integer.toString(score));
		addComponent();
	}
	
	/**
	 * Adds the label to the component(JPanel).
	 */
	private void addComponent() {
		this.add(label);
	}
	
	/**
	 * Sets the position of the players score.
	 * @param position
	 */
	public void setPosition(int position){
		this.position = position;
	}
	
	/**
	 * Gets the players position
	 * @return the position of the player.
	 */
	public int getPosition() {
		return this.position;
	}
	
	/**
	 * Setting the score that the player has earned.
	 * @param score
	 */
	public void setScore(int score) {
		this.score = score;
	}
	
	/**
	 * Getting the score that the player has earned.
	 * @return the score.
	 */
	public int getScore() {
		return this.score;
	}
}
