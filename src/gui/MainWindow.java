package gui;

import java.awt.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class MainWindow extends JFrame {
	private Dimension screen;
	private Dimension window;
	
	public MainWindow() { 
		//this window
		super("GradeTool");
		setBounds(100, 100, 800, 600);
		setResizable(false);

		add(new MainWindowToolbar(this.getWidth()), BorderLayout.NORTH);
		
		window = new Dimension(800, 600);
		screen = Toolkit.getDefaultToolkit().getScreenSize();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds((screen.width-window.width)/2, (screen.height-window.height)/2,
			window.width, window.height);
		setVisible(true);
	}
}
