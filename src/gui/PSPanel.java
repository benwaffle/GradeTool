package gui;

import java.awt.BorderLayout;

import javax.swing.JList;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PSPanel extends JPanel {
	JList<String> courses;
	
	public PSPanel() {
		courses = new JList<String>(
				new String[]{"math","lit","bio","chem"}
				);
		add(courses, BorderLayout.WEST);
	}
}
