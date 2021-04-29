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
 * The menu for the snake game.
 *
 */

@SuppressWarnings("serial")
public class SnakeMenu extends JPanel implements View{

	private JLabel chooseOptions;
	private JButton startGame, settings, leaderboards, backButton;
	private Image background;
	private CommandFactory factory;
	private ViewFactory viewFactory;

	/**
	 * The constructor that initializes the menu and calls a factory instance.
	 */
	public SnakeMenu() {
		factory = CommandFactory.getInstance();
		viewFactory = ViewFactory.getInstance();
		initView();
	}
	
	@Override
	public void initView() {
		this.setLayout(null);
		chooseOptions = new JLabel(ViewCommons.CHOOSEOPTIONS);
		startGame = new JButton(ViewCommons.START_GAME);
		settings = new JButton(ViewCommons.SETTINGS);
		leaderboards = new JButton(ViewCommons.LEADERBOARD);
		backButton = new JButton(ViewCommons.BACK_BUTTON);
		background = viewFactory.createPanelBackground(ViewCommons.SNAKEMENU_GIF).createBackground();	
		defineView();
	}
	
	@Override
	public void defineView() {
		chooseOptions.setBounds(150, 0, ViewCommons.LABEL_WIDTH, ViewCommons.LABEL_HEIGHT);
		startGame.setBounds(200, 100,  ViewCommons.BUTTON_WIDTH, ViewCommons.BUTTON_HEIGHT);
		settings.setBounds(200, 150,  ViewCommons.BUTTON_WIDTH, ViewCommons.BUTTON_HEIGHT);
		leaderboards.setBounds(200, 200,  ViewCommons.BUTTON_WIDTH, ViewCommons.BUTTON_HEIGHT);
		backButton.setBounds(ViewCommons.BACK_BUTTON_POSITION_WIDTH, ViewCommons.BACK_BUTTON_POSITION_HEIGHT, 
				ViewCommons.BUTTON_WIDTH, ViewCommons.BUTTON_HEIGHT);

		setLabelLayout();
		setButtonLayout(startGame);
		setButtonLayout(settings);
		setButtonLayout(leaderboards);
		setButtonLayout(backButton);
		setListener();
	}
	
	@Override
	public void setButtonLayout(AbstractButton button) {
		button.setFont(new Font("Calibri", Font.BOLD, 14));
		button.setBackground(new Color(0x2dce98));
		button.setForeground(Color.white);
		button.setUI(viewFactory.createButtonDesign());	
	}
	
	@Override
	public void setListener() {
		startGame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Broker.executeOperation(factory.createGetStartSnake());
			}
		});
		settings.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Broker.executeOperation(factory.createGetSnakeSettings());
			}
		});
		leaderboards.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Broker.executeOperation(factory.createGetSnakeLeaderboard());
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

	/**
	 * Painting the components.
	 * @param g that is required to paint the component.
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(background, 0, 0, this);

	}
	
	@Override
	public void addComponent() {
		this.add(chooseOptions);
		this.add(startGame);
		this.add(settings);
		this.add(leaderboards);
		this.add(backButton);
		this.setBackground(Color.green);
		this.repaint();
	}
	
	@Override
	public void setLabelLayout() {
		viewFactory.createLabelDesign().design(chooseOptions, 20, Color.white);
		
	}

}
