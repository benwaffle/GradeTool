package gui;

import java.awt.*;

import javax.swing.*;

@SuppressWarnings("serial")
public class MainWindow extends JFrame {
	private Dimension screen;
	private Dimension window;
	
	private Calendar calendar;
	private PSPanel powerschool;
	
	public MainWindow() {
		super("GradeTool");
		setResizable(false);

		window = new Dimension(800, 600);
		screen = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds((screen.width - window.width) / 2,
				(screen.height - window.height) / 2,
				window.width, window.height);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		getContentPane().add(new Toolbar(this.getWidth(), powerschool, calendar), BorderLayout.NORTH);
		getContentPane().add(calendar = new Calendar());
		getContentPane().add(powerschool = new PSPanel());
		setVisible(true);
	}
}
