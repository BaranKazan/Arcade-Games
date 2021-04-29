package command;

import facade.Facade;

public class GetSnakeLeaderboard implements Command {

	private Facade facade;

	/**
	 * constructor
	 */
	public GetSnakeLeaderboard() {
		facade = Facade.getInstance();
	}

	@Override
	public void execute() {
		facade.setSnakeLeaderboard();
	}

	@Override
	public void undo() {
		facade.setSnakeMenu();

	}

}
