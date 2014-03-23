package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.*;

@SuppressWarnings("serial")
public class MainWindow extends JFrame {
	JToolBar toolbar = null;
	JComboBox<String> userMenu = null;
	JButton powerschool = null, calendar = null;
	final int dropDownWidthFrac = 5;
	
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
		setVisible(true);
	}

}
