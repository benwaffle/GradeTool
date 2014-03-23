package gradetool.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JToolBar;
import javax.swing.border.MatteBorder;

@SuppressWarnings("serial")
public class Toolbar extends JToolBar {
	JLabel curUser;
	JButton showPSPanel = null, showCalPanel = null;

	/**
	 * Constructs a toolbar
	 * 
	 * @param parentWidth
	 *            width of the parent component (the JFrame)
	 * @param toggle
	 *            buttons will notify this action listener
	 */
	public Toolbar(int parentWidth, ActionListener toggle, String name) {
		create(toggle, name);
		setSizes(parentWidth);

		setFloatable(false);
		add(curUser);
		add(showPSPanel);
		add(showCalPanel);
		
		setBorder(new MatteBorder(0, 0, 1, 0, Color.black));
	}

	/**
	 * Instantiate all fields and set listeners for button presses
	 * 
	 * @param toggle
	 */
	private void create(ActionListener toggle, String name) {
		curUser = new JLabel(name);

		showPSPanel = new JButton("PowerSchool");
		showPSPanel.setActionCommand("showps");
		showPSPanel.addActionListener(toggle);

		showCalPanel = new JButton("Calendar");
		showCalPanel.setActionCommand("showcal");
		showCalPanel.addActionListener(toggle);
	}

	/**
	 * Set the size of the components:
	 * 1/5 for the Context Menu
	 * 2/5 for each button
	 * 
	 * @param parentWidth
	 *            the width of the parent component
	 */
	private void setSizes(int parentWidth) {
		// create dropdown menu
		Dimension menuDim = curUser.getPreferredSize();
		menuDim.width = parentWidth / 5;
		curUser.setMinimumSize(menuDim);
		curUser.setMaximumSize(menuDim);

		// get button dimensions
		Dimension buttDim = showPSPanel.getPreferredSize();
		buttDim.width = parentWidth / 5 * 4 / 2;
		
		// set properties for both buttons
		List<JButton> buttons = java.util.Arrays.asList(showPSPanel, showCalPanel);
		for (JButton b : buttons){
			b.setMinimumSize(buttDim);
			b.setMaximumSize(buttDim);
			b.setFont(new Font("Arial", Font.PLAIN, 20));
		}
	}
}
