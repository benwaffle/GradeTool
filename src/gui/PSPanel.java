package gui;

import java.awt.*;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class PSPanel extends JPanel {
	JList<String> courses;
	
	public PSPanel() {
		courses = new JList<String>(
				new String[]{"math","lit","bio","chem"}
				);
		add(courses, BorderLayout.WEST);
	}
	
}
