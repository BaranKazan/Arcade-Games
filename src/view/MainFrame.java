package view;

import java.awt.Container;

import javax.swing.JFrame;

/**
 * The MainFrame is a class that expends JFrame class. Every JPanel there is represents in this class.
 *
 */

@SuppressWarnings("serial")
public class MainFrame extends JFrame{
	
	public void setView(View view) {
		this.setContentPane((Container) view);
		this.setSize(ViewCommons.FRAME_WIDTH, ViewCommons.FRAME_HEIGHT);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle(ViewCommons.MAINFRAME_TITLE);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
	}
}
