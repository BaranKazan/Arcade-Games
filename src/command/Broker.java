package command;

import java.util.ArrayList;
import java.util.List;

/**
 * defines the undo and do operations
 *
 */
public class Broker {

	private static List<Command> undo = new ArrayList<Command>();

	/**
	 * 
	 * @param command
	 * executes the operation
	 */
	public static void executeOperation(Command command) {
		command.execute();
		undo.add(command);
	}

	/**
	 * undoes the operation
	 */
	public static void undoOperation() {
		if(!undo.isEmpty()) {
			Command command = undo.get(undo.size()-1);
			command.undo();
			undo.remove(undo.size()-1);
		}
	}
}
