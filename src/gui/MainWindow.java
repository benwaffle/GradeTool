package gui;

import java.awt.*;
import java.awt.image.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class MainWindow extends JFrame {
	JToolBar toolbar = null;
	JComboBox<String> userMenu = null;
	JButton powerschool = null, calendar = null;
	final int dropDownWidthFrac = 5;
	
	private Dimension screen;
	private Dimension window;
	
	public MainWindow() { 
		//this window
		super("GradeTool");
		setBounds(100, 100, 800, 600);
		setResizable(false);

		//dropdown menu
		userMenu = new JComboBox<String>(new String[] { "Ben Iofel", "Log Out" });
		Dimension comboBoxWidth = userMenu.getPreferredSize();
		comboBoxWidth.width = this.getWidth() / dropDownWidthFrac; // quarter of the window
		userMenu.setMinimumSize(comboBoxWidth);
		userMenu.setMaximumSize(comboBoxWidth);

		//buttons
		powerschool = new JButton("PowerSchool");
		calendar = new JButton("Calendar");
		
		//button width
		Dimension buttonWidth = powerschool.getPreferredSize();
		buttonWidth.width = this.getWidth() - (this.getWidth() / dropDownWidthFrac) / 2; // half of width
		
		//set button size
		powerschool.setMinimumSize(buttonWidth);
		powerschool.setMaximumSize(buttonWidth);
		calendar.setMinimumSize(buttonWidth);
		calendar.setMaximumSize(buttonWidth);

		//add stuff to toolbar
		toolbar = new JToolBar(JToolBar.HORIZONTAL);
		toolbar.setFloatable(false);
		toolbar.add(userMenu);
		toolbar.add(powerschool);
		toolbar.add(calendar);

		//add toolbar to window
		add(toolbar, BorderLayout.NORTH);
		
		window = new Dimension(800, 600);
		screen = Toolkit.getDefaultToolkit().getScreenSize();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds((screen.width-window.width)/2, (screen.height-window.height)/2,
			window.width, window.height);
		setVisible(true);
	}

}
