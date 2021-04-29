package facade;

import java.awt.Container;

import factory.ShooterFactory;
import factory.SnakeFactory;
import factory.ViewFactory;
import shooterGame.Commons;
import shooterGame.GamePanel;
import snakeGame.SnakeCommons;
import snakeGame.SnakeGamePanel;
import view.MainFrame;
import view.View;
import view.ViewCommons;

public class Facade {
	
	private static final Facade instance = new Facade();
	private ShooterFactory gameObjectFactory = ShooterFactory.getInstance();
	private ViewFactory viewFactory = ViewFactory.getInstance();
	private SnakeFactory snakeFactory = SnakeFactory.getInstance();
	private MainFrame mainFrame;
	private View shooterMenu, gameMenu, snakeLeaderboardView, shooterLeaderboardView, settingsView, snakeMenu;
	private SnakeGamePanel snakeGamePanel;
	private GamePanel shooterGamePanel;
	private Facade() {
		mainFrame = viewFactory.createMainFrame();
		gameMenu = viewFactory.createGameMenu();
		snakeMenu = viewFactory.createSnakeMenu();
		shooterMenu = viewFactory.createShooterMenu();
		settingsView = viewFactory.createSettingsView();
	}
	
	public static Facade getInstance() {
		return instance;
	}	
	
	public void setMainFrame() {
		mainFrame.setView(gameMenu);
	}
	
	public void setGameMenu() {
		mainFrame.getContentPane().removeAll();
		mainFrame.setContentPane((Container) gameMenu);
		mainFrame.setTitle(ViewCommons.MAINFRAME_TITLE);
		mainFrame.revalidate();
		gameMenu.addComponent();
	}
	
	public void setSnakeMenu() {
		mainFrame.getContentPane().removeAll();
		mainFrame.setSize(ViewCommons.FRAME_WIDTH, ViewCommons.FRAME_HEIGHT);
		mainFrame.setTitle(ViewCommons.SNAKEGAME);
		mainFrame.setContentPane((Container) snakeMenu);
		mainFrame.revalidate();
		snakeMenu.addComponent();
	}
	
	public void setShooterMenu() {
		mainFrame.getContentPane().removeAll();
		mainFrame.setSize(ViewCommons.FRAME_WIDTH, ViewCommons.FRAME_HEIGHT);
		mainFrame.setTitle(ViewCommons.SHOOTERGAME);
		mainFrame.setContentPane((Container) shooterMenu);	
		mainFrame.revalidate();
		shooterMenu.addComponent();
	}
	
	public void setSnakeSettings() {
		mainFrame.getContentPane().removeAll();
		mainFrame.setContentPane((Container) settingsView);
		mainFrame.setTitle(ViewCommons.SETTINGS);
		mainFrame.revalidate();
		settingsView.addComponent();
	}
	
	public void setSnakeLeaderboard() {
		snakeLeaderboardView = viewFactory.createSnakeLeaderboardView();
		mainFrame.getContentPane().removeAll();
		mainFrame.setContentPane((Container) snakeLeaderboardView);
		mainFrame.setTitle(ViewCommons.LEADERBOARD);
		mainFrame.revalidate();
		snakeLeaderboardView.addComponent();
	}
	
	public void startSnakeGame() {
		snakeGamePanel = (SnakeGamePanel) snakeFactory.createSnakeGame();
		mainFrame.getContentPane().removeAll();
		mainFrame.setSize(SnakeCommons.BOARDWIDTH, SnakeCommons.BOARDHEIGHT);	
		mainFrame.setTitle(ViewCommons.SNAKEGAME);
		mainFrame.setContentPane(snakeGamePanel);
		mainFrame.revalidate();
		snakeGamePanel.requestFocusInWindow();
	}

	public void startShooterGame() {
		shooterGamePanel = gameObjectFactory.createGamePanel();
		mainFrame.getContentPane().removeAll();
		mainFrame.setSize(Commons.WIDTH, Commons.HEIGHT);	
		mainFrame.setTitle(ViewCommons.SHOOTERGAME);
		mainFrame.setContentPane(shooterGamePanel);
		mainFrame.revalidate();
		shooterGamePanel.requestFocusInWindow();
	}

	public void setShooterLeaderboard() {
		shooterLeaderboardView = viewFactory.createShooterLeaderboardView();
		mainFrame.getContentPane().removeAll();
		mainFrame.setContentPane((Container) shooterLeaderboardView);
		mainFrame.setTitle(ViewCommons.LEADERBOARD);
		mainFrame.revalidate();
		shooterLeaderboardView.addComponent();
		
	}
}
