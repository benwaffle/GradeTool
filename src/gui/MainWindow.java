package gui;

import java.awt.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class MainWindow extends JFrame {
	private Dimension screen;
	private Dimension window;

	public MainWindow() {
		super("GradeTool");
		setResizable(false);

		window = new Dimension(800, 600);
		screen = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds((screen.width - window.width) / 2,
				(screen.height - window.height) / 2,
				window.width, window.height);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		add(new Toolbar(this.getWidth()), BorderLayout.NORTH);

		setVisible(true);
	}
}
