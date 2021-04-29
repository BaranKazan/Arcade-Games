package command;

import facade.Facade;

public class GetSnakeMenu implements Command {

	private Facade facade;

	/**
	 * constructor
	 */
	public GetSnakeMenu() {
		facade = Facade.getInstance();
	}

	@Override
	public void execute() {
		facade.setSnakeMenu();
	}

	@Override
	public void undo() {
		facade.setGameMenu();

	}

}
