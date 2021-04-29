package command;

import facade.Facade;

public class GetShooterLeaderboard implements Command {

	private Facade facade;

	/**
	 * constructor
	 */
	public GetShooterLeaderboard() {
		facade = Facade.getInstance();
	}

	@Override
	public void execute() {
		facade.setShooterLeaderboard();
	}

	@Override
	public void undo() {
		facade.setShooterMenu();

	}

}
