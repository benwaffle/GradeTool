package gui;

import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JToolBar;

@SuppressWarnings("serial")
public class MainWindowToolbar extends JToolBar {
	JComboBox<String> userMenu = null;
	JButton showPSPanel = null, showCalPanel = null;

	public MainWindowToolbar(int parentWidth) {
		create();
		setSizes(parentWidth);

		setFloatable(false);
		add(userMenu);
		add(showPSPanel);
		add(showCalPanel);
	}

	private void create() {
		userMenu = new JComboBox<String>(
				new String[] {"Ben Iofel","Log Out" });
		showPSPanel = new JButton("PowerSchool");
		showCalPanel = new JButton("Calendar");
	}

	private void setSizes(int parentWidth) {
		Dimension menuDim = userMenu.getPreferredSize();
		menuDim.width = parentWidth / 4;
		userMenu.setMinimumSize(menuDim);
		userMenu.setMaximumSize(menuDim);
		
		Dimension buttDim = showPSPanel.getPreferredSize();
		buttDim.width = parentWidth / 4 * 3 / 2;
		showPSPanel.setMinimumSize(buttDim);
		showPSPanel.setMaximumSize(buttDim);
		showCalPanel.setMinimumSize(buttDim);
		showCalPanel.setMaximumSize(buttDim);
	}
}
