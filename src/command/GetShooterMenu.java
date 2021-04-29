package command;

import facade.Facade;

public class GetShooterMenu implements Command {

	private Facade facade;

	/**
	 * constructor
	 */
	public GetShooterMenu() {
		facade = Facade.getInstance();
	}

	@Override
	public void execute() {
		facade.setShooterMenu();
	}

	@Override
	public void undo() {
		facade.setGameMenu();
	}

}
