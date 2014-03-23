import gui.*;

import java.awt.*;
import javax.swing.*;

public class CalendarTest {
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		new MainWindow(null);
	}
}
