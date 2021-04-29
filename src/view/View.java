package view;

import javax.swing.AbstractButton;

public interface View {

	/**
	 * Initializes the view of the menu
	 */
	public void initView();

	/**
	 * Adding the position and layout of its component.
	 */
	public void defineView();

	/**
	 * Adding listener for each of the button
	 */
	public void setListener();

	/**
	 * Settings the layout for the buttons
	 */
	public void setButtonLayout(AbstractButton button);

	/**
	 * Setting the layout for the label
	 */
	public void setLabelLayout();

	/**
	 * Adding the component to the JPanel.
	 */
	public void addComponent();
}
