package gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JToolBar;

@SuppressWarnings("serial")
public class Toolbar extends JToolBar {
	JComboBox<String> userMenu = null;
	JButton showPSPanel = null, showCalPanel = null;

	PSPanel psPanel;
	CalendarPanel calPanel;
	
	public Toolbar(int parentWidth, PSPanel psPanel, CalendarPanel calPanel) {
		this.psPanel = psPanel;
		this.calPanel = calPanel;
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
		showPSPanel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				psPanel.setVisible(true);
				calPanel.setVisible(false);
			}
		});
		
		showCalPanel = new JButton("CalendarPanel");
		showCalPanel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				psPanel.setVisible(false);
				calPanel.setVisible(true);
			}
		});
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
