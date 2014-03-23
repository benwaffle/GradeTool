package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

import data.Assignment;
import data.Course;

@SuppressWarnings("serial")
public class PSPanel extends JPanel {
	JList<Course> courseView;
	JList<Assignment> asmtsView;

//	Course
	
	public PSPanel(int parentWidth, Course[] courses) {
		setLayout(new BorderLayout());
		
		final int cellWidth = parentWidth / 5;
		
		courseView = new JList<Course>() {{
			setSelectionBackground(new Color(0, 128, 255));
			setFixedCellHeight(30);
			setFixedCellWidth(cellWidth);
			setForeground(Color.black);
			setBackground(null);
			setFont(new Font("Arial", Font.PLAIN, 20));
			setBorder(new MatteBorder(0, 0, 0, 1, Color.black));
		}};
		
		add(courseView, BorderLayout.WEST);
	}
}
