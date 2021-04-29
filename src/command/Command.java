package command;

public interface Command {

	/**
	 * executes actions
	 */
	public void execute();
	
	/**
	 * reverses the execution
	 */
	public void undo();

}
