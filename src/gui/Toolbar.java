package gui;

import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JToolBar;

@SuppressWarnings("serial")
public class Toolbar extends JToolBar {
	JComboBox<String> userMenu = null;
	JButton showPSPanel = null, showCalPanel = null;

	public Toolbar(int parentWidth, ActionListener toggle) {
		create(toggle);
		setSizes(parentWidth);

		setFloatable(false);
		add(userMenu);
		add(showPSPanel);
		add(showCalPanel);
	}

	private void create(ActionListener toggle) {
		userMenu = new JComboBox<String>(
				new String[] {"Ben Iofel","Log Out" });
		
		showPSPanel = new JButton("PowerSchool");
		showPSPanel.setActionCommand("showps");
		showPSPanel.addActionListener(toggle);
		
		showCalPanel = new JButton("Calendar");
		showCalPanel.setActionCommand("showcal");
		showCalPanel.addActionListener(toggle);
	}

	private void setSizes(int parentWidth) {
		Dimension menuDim = userMenu.getPreferredSize();
		menuDim.width = parentWidth / 5;
		userMenu.setMinimumSize(menuDim);
		userMenu.setMaximumSize(menuDim);
		
		Dimension buttDim = showPSPanel.getPreferredSize();
		buttDim.width = parentWidth / 5 * 4 / 2;
		showPSPanel.setMinimumSize(buttDim);
		showPSPanel.setMaximumSize(buttDim);
		showCalPanel.setMinimumSize(buttDim);
		showCalPanel.setMaximumSize(buttDim);
	}
}
