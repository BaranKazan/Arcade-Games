package command;

import facade.Facade;

public class GetStartSnake implements Command {

	private Facade facade;

	/**
	 * constructor
	 */
	public GetStartSnake() {
		facade = Facade.getInstance();
	}

	@Override
	public void execute() {
		facade.startSnakeGame();
	}

	@Override
	public void undo() {
		facade.setSnakeMenu();
	}

}
