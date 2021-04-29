package designUI;

import java.awt.Image;
import java.awt.Toolkit;

import view.ViewCommons;

/**
 * adapts image into a panel
 *
 */
public class PanelBackground {

	private String img;

	/**
	 * 
	 * @param img path
	 * constructor
	 */
	public PanelBackground(String img) {
		this.img = img;
	}
 
	/**
	 * 
	 * @return fit image
	 */
	public Image createBackground() {
		return Toolkit.getDefaultToolkit().createImage(img)
				.getScaledInstance(ViewCommons.GIF_WIDTH, ViewCommons.GIF_HEIGHT, Image.SCALE_DEFAULT);

	}

}
