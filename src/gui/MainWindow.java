package gui;

import java.awt.*;
import java.awt.image.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class MainWindow extends JFrame {
	private Dimension screen;
	private Dimension window;
	
	// specific panels
	private Calendar calendar;
	
	public MainWindow() {
		super("GradeTool");
		
		window = new Dimension(800, 600);
		screen = Toolkit.getDefaultToolkit().getScreenSize();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds((screen.width-window.width)/2, (screen.height-window.height)/2,
			window.width, window.height);
		
		calendar = new Calendar();
		getContentPane().add(calendar);
		
		setResizable(false);
		setVisible(true);
	}

}
