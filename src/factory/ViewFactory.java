package factory;

import designUI.ButtonDesign;
import designUI.LabelDesign;
import designUI.PanelBackground;
import view.GameMenu;
import view.MainFrame;
import view.SettingsView;
import view.ShooterLeaderboardView;
import view.ShooterMenu;
import view.SnakeLeaderboardView;
import view.SnakeMenu;
import view.View;

public class ViewFactory {
	
private static final ViewFactory instance = new ViewFactory();
	
	public static ViewFactory getInstance() {
		return instance;
	}

	public MainFrame createMainFrame() {
		return new MainFrame();
	}
	
	public View createGameMenu() {
		return new GameMenu(); 
	}
	
	public View createSnakeMenu() {
		return new SnakeMenu(); 
	}
	
	public View createShooterMenu() {
		return new ShooterMenu(); 
	}
	
	public View createSnakeLeaderboardView() {
		return new SnakeLeaderboardView(); 
	}
	
	public View createShooterLeaderboardView() {
		return new ShooterLeaderboardView(); 
	}
	
	public View createSettingsView() {
		return new SettingsView(); 
	}
	public LabelDesign createLabelDesign() {
		return new LabelDesign(); 
	}
	
	public PanelBackground createPanelBackground(String img) {
		return new PanelBackground(img); 
	}
	public ButtonDesign createButtonDesign() {
		return new ButtonDesign(); 
	}
}
