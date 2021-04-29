package command;

import facade.Facade;

public class GetSnakeSettings implements Command {

	private Facade facade;

	/**
	 * constructor
	 */
	public GetSnakeSettings() {
		facade = Facade.getInstance();
	}

	@Override
	public void execute() {
		facade.setSnakeSettings();
	}

	@Override
	public void undo() {
		facade.setSnakeMenu();

	}

}
