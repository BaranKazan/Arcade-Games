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
 * Menu for the shooter game.
 */

@SuppressWarnings("serial")
public class ShooterMenu extends JPanel implements View {

	private JLabel chooseOptions;
	private JButton startGame, leaderboards, backButton;
	private Image background;
	private ViewFactory viewFactory;
	private CommandFactory commandFactory;

	/**
	 * The constructor that initializes the menu and calls a factory instance.
	 */
	public ShooterMenu() {
		viewFactory = ViewFactory.getInstance();
		commandFactory = CommandFactory.getInstance();
		initView();
	}
	
	@Override
	public void initView() {
		this.setLayout(null);
		chooseOptions = new JLabel(ViewCommons.CHOOSEOPTIONS);
		startGame = new JButton(ViewCommons.START_GAME);
		leaderboards = new JButton(ViewCommons.LEADERBOARD);
		backButton = new JButton(ViewCommons.BACK_BUTTON);
		background = viewFactory.createPanelBackground(ViewCommons.BREAKOUTMENU_GIF).createBackground();	
		backButton.setBounds(ViewCommons.BACK_BUTTON_POSITION_WIDTH, ViewCommons.BACK_BUTTON_POSITION_HEIGHT,
				ViewCommons.BUTTON_WIDTH, ViewCommons.BUTTON_HEIGHT);
		defineView();
	}
	
	@Override
	public void defineView() {
		chooseOptions.setBounds(150, 0, 215, 100);
		startGame.setBounds(200, 100, 110, 30);
		leaderboards.setBounds(200, 150, 110, 30);

		setLabelLayout();
		setButtonLayout(startGame);
		setButtonLayout(leaderboards);
		setButtonLayout(backButton);
		setListener();
	}

	@Override
	public void setButtonLayout(AbstractButton button) {
		button.setFont(new Font("Calibri", Font.PLAIN, 14));
		button.setBackground(new Color(0x2dce98));
		button.setForeground(Color.white);
		button.setUI(viewFactory.createButtonDesign());
	}
	
	@Override
	public void setListener() {
		startGame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Broker.executeOperation(commandFactory.createGetStartShooter());
			}
		});
		
		leaderboards.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Broker.executeOperation(commandFactory.createGetShooterLeaderboard());
			}
		});
		
		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Broker.undoOperation();
			}
		});
		addComponent();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(background, 0, -30, this);

	}
	
	@Override
	public void addComponent() {
		this.add(chooseOptions);
		this.add(startGame);
		this.add(leaderboards);
		this.add(backButton);
		this.setBackground(Color.black);
		this.repaint();
	}
	
	@Override
	public void setLabelLayout() {
		viewFactory.createLabelDesign().design(chooseOptions, 20, Color.white);
		
	}

}
