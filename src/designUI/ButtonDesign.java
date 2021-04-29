package designUI;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.AbstractButton;
import javax.swing.JComponent;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicButtonUI;

/**
 * designs the UI of the buttons
 *
 */
public class ButtonDesign extends BasicButtonUI {

	@Override
	public void installUI (JComponent jComp) {
		super.installUI(jComp);
		AbstractButton button = (AbstractButton) jComp;
		button.setOpaque(false);
		button.setBorder(new EmptyBorder(5, 15, 5, 15));
	}

	@Override
	public void paint (Graphics g, JComponent jComp) {
		AbstractButton abstractButton = (AbstractButton) jComp;
		paintBackground(g, abstractButton, abstractButton.getModel().isPressed() ? 2 : 0);
		super.paint(g, jComp);
	}
	
	/**
	 * 
	 * @param g
	 * @param jComp
	 * @param yOffset
	 * 
	 * paints the button
	 */
	private void paintBackground (Graphics g, JComponent jComp, int yOffset) {
		Dimension size = jComp.getSize();
		Graphics2D graph2D = (Graphics2D) g;
		graph2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setColor(jComp.getBackground().darker());
		g.fillRoundRect(0, yOffset, size.width, size.height - yOffset, 10, 10);
		g.setColor(jComp.getBackground());
		g.fillRoundRect(0, yOffset, size.width, size.height + yOffset - 5, 10, 10);
	}
}
