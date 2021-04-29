package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

import command.Broker;
import factory.ViewFactory;
import state.SettingsState;
import state.State;

/**
 * Settings for the snake game.
 */

@SuppressWarnings("serial")
public class SettingsView extends JPanel implements View{
	
	private JLabel chooseDifficulty, chooseTheme, selectedDifficulty, selectedTheme;
	private JToggleButton easyButton, mediumButton, hardButton, lightButton, darkButton;
	private ButtonGroup buttonGroupDifficulty;
	private ButtonGroup buttonGroupTheme;
	private JButton backButton;
	private ViewFactory viewFactory;
	private State state;

	/**
	 * The constructor that initializes the menu and calls a factory instance. Its also sets the deffault settings.
	 */
	public SettingsView() {
		viewFactory = ViewFactory.getInstance();
		state = new SettingsState();
		state.setMedium();
		state.setDark();
		State.setState(state);	
		initView();
	}

	@Override
	public void initView() {
		this.setLayout(null);
		backButton = new JButton(ViewCommons.BACK_BUTTON);
		buttonGroupDifficulty = new ButtonGroup();
		chooseDifficulty = new JLabel(ViewCommons.DIFFICULTY);
		easyButton = new JToggleButton(ViewCommons.EASY);
		mediumButton = new JToggleButton(ViewCommons.MEDIUM);
		hardButton = new JToggleButton(ViewCommons.HARD);
		selectedDifficulty = new JLabel(ViewCommons.SELECTED);

		buttonGroupTheme = new ButtonGroup();
		chooseTheme = new JLabel(ViewCommons.THEME);
		lightButton = new JToggleButton(ViewCommons.LIGHT);
		darkButton = new JToggleButton(ViewCommons.DARK);
		selectedTheme = new JLabel(ViewCommons.SELECTED);
		defineView();
	}
	
	@Override
	public void defineView() {
		chooseDifficulty.setBounds(150, 0, ViewCommons.LABEL_WIDTH, ViewCommons.LABEL_HEIGHT);
		easyButton.setBounds(150, 50,  ViewCommons.BUTTON_WIDTH, ViewCommons.BUTTON_HEIGHT);
		mediumButton.setBounds(150, 100,  ViewCommons.BUTTON_WIDTH, ViewCommons.BUTTON_HEIGHT);
		hardButton.setBounds(150, 150,  ViewCommons.BUTTON_WIDTH, ViewCommons.BUTTON_HEIGHT);
		backButton.setBounds(ViewCommons.BACK_BUTTON_POSITION_WIDTH, ViewCommons.BACK_BUTTON_POSITION_HEIGHT, 
				ViewCommons.BUTTON_WIDTH, ViewCommons.BUTTON_HEIGHT);
		
		setButtonLayout(easyButton);
		setButtonLayout(mediumButton);
		setButtonLayout(hardButton);
		
		chooseTheme.setBounds(150, 200, 215, 30);
		lightButton.setBounds(150, 250,  ViewCommons.BUTTON_WIDTH, ViewCommons.BUTTON_HEIGHT);
		darkButton.setBounds(150, 300,  ViewCommons.BUTTON_WIDTH, ViewCommons.BUTTON_HEIGHT);
		
		setLabelLayout();
		setButtonLayout(lightButton);
		setButtonLayout(darkButton);
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
		easyButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				state.setEasy();
				State.setState(state);
				selectedDifficulty.setBounds(350, 40,ViewCommons.LABEL_WIDTH, ViewCommons.LABEL_HEIGHT);
			}
		});
		mediumButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				state.setMedium();
				State.setState(state);
				selectedDifficulty.setBounds(350, 90,ViewCommons.LABEL_WIDTH, ViewCommons.LABEL_HEIGHT);
			}
		});
		hardButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				state.setHard();
				State.setState(state);
				selectedDifficulty.setBounds(350, 140,ViewCommons.LABEL_WIDTH, ViewCommons.LABEL_HEIGHT);
			}
		});
		lightButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				state.setLight();
				selectedTheme.setBounds(350, 240,ViewCommons.LABEL_WIDTH, ViewCommons.LABEL_HEIGHT);
			}
		});
		darkButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				state.setDark();
				selectedTheme.setBounds(350, 290,ViewCommons.LABEL_WIDTH, ViewCommons.LABEL_HEIGHT);
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
	public void addComponent() {
		this.add(chooseDifficulty);
		buttonGroupDifficulty.add(easyButton);
		buttonGroupDifficulty.add(mediumButton);
		buttonGroupDifficulty.add(hardButton);
		this.add(easyButton);
		this.add(mediumButton);
		this.add(hardButton);
		this.add(selectedDifficulty);
		this.add(selectedTheme);
		this.add(chooseTheme);
		buttonGroupTheme.add(lightButton);
		buttonGroupTheme.add(darkButton);
		this.add(lightButton);
		this.add(darkButton);
		this.add(backButton);
		this.repaint();
	}
	
	@Override
	public void setLabelLayout() {
		viewFactory.createLabelDesign().design(chooseDifficulty, 20, Color.black);
		viewFactory.createLabelDesign().design(chooseTheme, 20, Color.black);
		viewFactory.createLabelDesign().design(selectedDifficulty, 10, Color.black);
		viewFactory.createLabelDesign().design(selectedTheme, 10, Color.black);
	}

}
