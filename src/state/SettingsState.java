package state;

import java.awt.Color;

import enums.Difficulty;

public class SettingsState extends State{

	@Override
	public void setEasy() {
		setDifficulty(Difficulty.EASY);
		setSpeed(5000);
		setTimer(30);
		
	}
	
	@Override
	public void setMedium() {
		setDifficulty(Difficulty.MEDIUM);
		setSpeed(250);
		setTimer(15);
	}
	
	@Override
	public void setHard() {
		setDifficulty(Difficulty.HARD);
		setSpeed(125);
		setTimer(10);
	}
	
	@Override
	public void setDark() {
		setBackgroundColor(Color.BLACK);
		setTextColor(Color.WHITE);
	}
	
	@Override
	public void setLight() {
		setBackgroundColor(Color.WHITE);
		setTextColor(Color.BLACK);
	}
}
