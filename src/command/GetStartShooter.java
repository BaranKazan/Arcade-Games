package command;

import facade.Facade;

public class GetStartShooter implements Command {

	private Facade facade;

	/**
	 * constructor
	 */
	public GetStartShooter() {
		facade = Facade.getInstance();
	}

	@Override
	public void execute() {
		facade.startShooterGame();
	}

	@Override
	public void undo() {
		facade.setShooterMenu();
	}

}
