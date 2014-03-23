import gui.MainWindow;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class GradeTool {
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"Something terrible has happened. Please try again.",
					"Fatal Error", JOptionPane.ERROR_MESSAGE);
			System.exit(1);
		}

		MainWindow mw = new MainWindow();
	}
}
