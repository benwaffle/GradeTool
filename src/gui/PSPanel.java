package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class PSPanel extends JPanel implements ActionListener {
	JList<String> courses;
	
	public PSPanel() {
		courses = new JList<String>(
				new String[]{"math","lit","bio","chem"}
				);
		add(courses, BorderLayout.WEST);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("PowerSchool")){
			System.out.println("showing powerschool");
		} else if (e.getActionCommand().equals("Calendar")){
			System.out.println("showing calendar");
		}
	}
	
}
