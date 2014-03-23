package gui;

import java.awt.*;

import javax.swing.*;

@SuppressWarnings("serial")
public class MainWindow extends JFrame {
	private Dimension screen;
	private Dimension window;
	
	private CalendarPanel calendar;
	private PSPanel powerschool;
	
	public MainWindow() {
		super("GradeTool");
		setResizable(false);

		window = new Dimension(1244, 700);
		calendar = new CalendarPanel();
		powerschool = new PSPanel();
		
		screen = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds((screen.width - window.width) / 2,
				(screen.height - window.height) / 2,
				window.width, window.height);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		getContentPane().add(new Toolbar(this.getWidth(), powerschool, calendar), BorderLayout.NORTH);
		getContentPane().add(calendar);
		// getContentPane().add(powerschool);
		setVisible(true);
	}
}
