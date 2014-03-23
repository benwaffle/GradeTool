import gui.MainWindow;

import javax.swing.UIManager;

public class GradeTool {
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {}

		new MainWindow();
	}
}
