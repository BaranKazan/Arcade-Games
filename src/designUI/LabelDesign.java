package designUI;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

/**
 * designs the label UI
 */
public class LabelDesign {

	/**
	 * 
	 * @param label
	 * @param fontSize
	 * @param color
	 * @return the label
	 */
	public JLabel design(JLabel label, int fontSize, Color color) {
		Font font = new Font("Verdana", Font.BOLD, fontSize);
		label.setFont(font);
		label.setForeground(color);
		return label;
	}
}
