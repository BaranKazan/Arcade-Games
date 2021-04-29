package arcade;
import javax.swing.SwingUtilities;

import command.Broker;
import factory.CommandFactory;

public class Main{

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run()
			{
				CommandFactory factory = CommandFactory.getInstance();
				Broker.executeOperation(factory.createRun());
			}
		});
	}
}
