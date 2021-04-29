package state;

import java.awt.Color;

import enums.Difficulty;

public abstract class State {

	private Difficulty difficulty;
	private int speed;
	private int randomTimer;
	private Color backgroundColor, textColor;
	private static State currentState;
	
	public void setEasy() {}
	public void setMedium() {}
	public void setHard() {}
	public void setDark() {}
	public void setLight() {}
	
	public static void setState(State newState) {
		currentState = newState;
	}
	
	public static State getState() {
		return currentState;
	}
		
	public void setDifficulty(Difficulty difficulty) {
		this.difficulty = difficulty;
	}
	
	public Difficulty getDifficulty() {
		return this.difficulty;
	}
	
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	public int getSpeed() {
		return this.speed;
	}
	
	public void setTimer(int randomTimer) {
		this.randomTimer = randomTimer;
	}
	
	public int getTimer() {
		return randomTimer;
	}
	
	public void setBackgroundColor(Color backgroundcolor) {
		this.backgroundColor = backgroundcolor;
	}
	
	public Color getBackgroundColor() {
		return this.backgroundColor;
	}
	
	public void setTextColor(Color textColor) {
		this.textColor = textColor;
	}
	
	public Color getTextColor() {
		return this.textColor;
	}
	
}
