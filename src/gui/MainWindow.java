package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import com.benwaffle.pslib.PSlib;

@SuppressWarnings("serial")
public class MainWindow extends JFrame {
	/**
	 * The dimensions of the computer screen
	 */
	private Dimension screen;
	
	/**
	 * The dimensions of the application window
	 */
	private Dimension window;
	
	/**
	 * CardLayout to switch between PowerSchool and Calendar views
	 */
	private CardLayout layoutMgr;
	
	/**
	 * Wrapper to hold all "cards"
	 */
	private JPanel wrapper;
	
	/**
	 * The calendar JPanel
	 */
	private CalendarPanel calendar;
	
	/**
	 * The powerschool JPanel
	 */
	private PSPanel powerschool;
	
	private PSlib lib;
	
	public MainWindow(PSlib lib) {		
		// this window
		super("GradeTool");
		setResizable(true);
		
		this.lib = lib;
		
		// set dimensions and center window
		screen = Toolkit.getDefaultToolkit().getScreenSize();
		window = new Dimension(1244, 700);
		setBounds((screen.width - window.width) / 2, 
				(screen.height - window.height) / 2, window.width, window.height);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// create a wrapper 
		wrapper = new JPanel(layoutMgr = new CardLayout());
		
		// buttons in the Toolbar should show correct panel in card layout
		ActionListener switchPanels = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand().equals("showcal"))
					layoutMgr.show(wrapper, "cal");
				else if (e.getActionCommand().equals("showps"))
					layoutMgr.show(wrapper, "ps");
			}
		};
		
		// add toolbar to this window
		getContentPane().add(new Toolbar(this.getWidth(), switchPanels), BorderLayout.NORTH);
		
		// create calendar and powerschool panels
		powerschool = new PSPanel(getWidth());
		calendar = new CalendarPanel();
		
		// add panels to the wrapper
		wrapper.add(powerschool, "ps");
		wrapper.add(calendar, "cal");
		
		// add wrapper to current window
		getContentPane().add(wrapper);
		
		// show this window
		setVisible(true);
	}
}
