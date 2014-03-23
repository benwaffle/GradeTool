package gui;

import java.awt.*;
import java.awt.image.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class MainWindow extends JFrame {
	JToolBar toolbar = null;
	JComboBox<String> userMenu = null;
	JButton showPSPanel = null, showCalPanel = null;
	final int dropDownWidthFrac = 5;
	
	private Dimension screen;
	private Dimension window;
	
	// specific panels
	private Calendar calendar;
	
	public MainWindow() {
		super("GradeTool");

		//dropdown menu
		userMenu = new JComboBox<String>(new String[] { "Ben Iofel", "Log Out" });
		Dimension comboBoxWidth = userMenu.getPreferredSize();
		comboBoxWidth.width = this.getWidth() / dropDownWidthFrac; // quarter of the window
		userMenu.setMinimumSize(comboBoxWidth);
		userMenu.setMaximumSize(comboBoxWidth);

		//buttons
		showPSPanel = new JButton("PowerSchool");
		showCalPanel = new JButton("Calendar");
		
		//button width
		Dimension buttonWidth = showPSPanel.getPreferredSize();
		buttonWidth.width = this.getWidth() - (this.getWidth() / dropDownWidthFrac) / 2; // half of width
		
		//set button size
		showPSPanel.setMinimumSize(buttonWidth);
		showPSPanel.setMaximumSize(buttonWidth);
		showCalPanel.setMinimumSize(buttonWidth);
		showCalPanel.setMaximumSize(buttonWidth);

		//add stuff to toolbar
		toolbar = new JToolBar(JToolBar.HORIZONTAL);
		toolbar.setFloatable(false);
		toolbar.add(userMenu);
		toolbar.add(showPSPanel);
		toolbar.add(showCalPanel);

		//add toolbar to window
		add(toolbar, BorderLayout.NORTH);
		
		window = new Dimension(800, 600);
		screen = Toolkit.getDefaultToolkit().getScreenSize();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds((screen.width-window.width)/2, (screen.height-window.height)/2,
			window.width, window.height);
		
		calendar = new Calendar();
		
		setResizable(false);
		setVisible(true);
		
		addCalendar(calendar);
	}
	public void addCalendar(Calendar calendar) {
		getContentPane().add(this.calendar = calendar);
		calendar.start(); // initialize the calendar
	}
}
