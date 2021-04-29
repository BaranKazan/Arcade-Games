package view;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import command.Broker;
import factory.CommandFactory;
import factory.ViewFactory;

/**
 * The game menu itself that the player can choose from what game to play.
 */

@SuppressWarnings("serial")
public class GameMenu extends JPanel implements View{
	
	private JLabel gameLabel;
	private JButton snakeGame, shooterGame;
	private Image background;
	private ViewFactory viewFactory;
	private CommandFactory commandFactory;

	/**
	 * The constructor that initializes the menu and calls a factory instance.
	 */
	public GameMenu() {
		viewFactory = ViewFactory.getInstance();
		commandFactory = CommandFactory.getInstance();
		initView();
	}
	
	@Override
	public void initView() {
		this.setLayout(null);
		gameLabel = new JLabel(ViewCommons.GAMELABEL);
		snakeGame = new JButton(ViewCommons.SNAKEGAME);
		shooterGame = new JButton(ViewCommons.SHOOTERGAME);
		background = viewFactory.createPanelBackground(ViewCommons.GAMEMENU_GIF).createBackground();		
		defineView();
	}
	
	@Override
	public void defineView() {
		gameLabel.setBounds(150, 0, ViewCommons.LABEL_WIDTH, ViewCommons.LABEL_HEIGHT);
		snakeGame.setBounds(200, 100, ViewCommons.BUTTON_WIDTH, ViewCommons.BUTTON_HEIGHT);
		shooterGame.setBounds(200, 150,  ViewCommons.BUTTON_WIDTH, ViewCommons.BUTTON_HEIGHT);
		setLabelLayout();
		setButtonLayout(snakeGame);
		setButtonLayout(shooterGame);
		setListener();
	}
	
	@Override
	public void setLabelLayout() {
		viewFactory.createLabelDesign().design(gameLabel, 20, Color.white);
	}
	
	@Override
	public void setButtonLayout(AbstractButton button) {
		button.setFont(new Font("Calibri", Font.BOLD, 14));
		button.setBackground(new Color(0x2dce98));
		button.setForeground(Color.white);
		button.setUI(viewFactory.createButtonDesign());
	}

	@Override
	  public void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    g.drawImage(background, 0, 0, this);
	  }
	
	@Override
	public void setListener() {
		snakeGame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Broker.executeOperation(commandFactory.createGetSnakeMenu());
			}
		});
		shooterGame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Broker.executeOperation(commandFactory.createGetShooterMenu());
			}
		});
		addComponent();
	}
	
	@Override
	public void addComponent() {
		this.add(gameLabel);
		this.add(snakeGame);
		this.add(shooterGame);
		this.repaint();
		this.setBackground(Color.black);
	}	

}
