package command;

import facade.Facade;

public class Run implements Command{

	private Facade facade;

	/**
	 * constructor
	 */
	public Run() {
		facade = Facade.getInstance();
	}

	@Override
	public void execute() {
		facade.setMainFrame();
	}

	@Override
	public void undo() {

	}

}
