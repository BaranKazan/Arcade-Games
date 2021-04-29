package factory;

import command.Command;

import command.GetShooterLeaderboard;
import command.GetShooterMenu;
import command.GetSnakeLeaderboard;
import command.GetSnakeMenu;
import command.GetSnakeSettings;
import command.GetStartShooter;
import command.GetStartSnake;
import command.Run;

public class CommandFactory {
	
	private static final CommandFactory instance = new CommandFactory();
	
	public static CommandFactory getInstance() {
		return instance;
	}
	
	public Command createGetShooterMenu() {
		return new GetShooterMenu(); 
	}
	
	public Command createGetSnakeLeaderboard() {
		return new GetSnakeLeaderboard(); 
	}
	
	public Command createGetShooterLeaderboard() {
		return new GetShooterLeaderboard(); 
	}
	
	public Command createGetSnakeMenu() {
		return new GetSnakeMenu(); 
	}
	
	public Command createGetSnakeSettings() {
		return new GetSnakeSettings(); 
	}
	
	public Command createGetStartSnake() {
		return new GetStartSnake(); 
	}
	
	public Command createGetStartShooter() {
		return new GetStartShooter();
	}

	public Command createRun() {
		return new Run();
	}
}
