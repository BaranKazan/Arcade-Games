package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JPanel;

import command.Broker;
import factory.ViewFactory;
import shooterGame.ShooterHighscore;

@SuppressWarnings("serial")
public class ShooterLeaderboardView extends JPanel implements View{
	
	private JButton backButton;
	private List<ShooterPlayerScoreView> list;
	private ViewFactory viewFactory;
	
	public ShooterLeaderboardView() {
		viewFactory = ViewFactory.getInstance();
		list = ShooterHighscore.getInstance().getScore();
		initView();
	}
	
	@Override
	public void initView() {
		this.setLayout(null);
		backButton = new JButton(ViewCommons.BACK_BUTTON);
		defineView();
	}
	
	@Override
	public void defineView() {
		int position = 75;
		for(int i = 0; i<list.size(); i++) {
			list.get(i).setBounds(50, position, 215, 30);
			position += 50;
		}
		backButton.setBounds(ViewCommons.BACK_BUTTON_POSITION_WIDTH, ViewCommons.BACK_BUTTON_POSITION_HEIGHT, 
				ViewCommons.BUTTON_WIDTH, ViewCommons.BUTTON_HEIGHT);
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
		for(ShooterPlayerScoreView p: list) {
			this.add(p);
		}
		this.add(backButton);
		this.repaint();
	}

	@Override
	public void setLabelLayout() {
	}
	
}
