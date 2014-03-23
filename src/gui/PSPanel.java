package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.Arrays;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.MatteBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import data.Assignment;
import data.Course;

@SuppressWarnings("serial")
public class PSPanel extends JPanel {
	JList<Course> courseView;
	JList<Assignment> asmtsView;
	
	Course[] courses;
	
	public PSPanel(int parentWidth, Course[] coursesFromPs) {
		setLayout(new BorderLayout());
		
		final int sidebarWidth = parentWidth / 5, asmtWidth = sidebarWidth * 4;
		
		courses = coursesFromPs;
		Arrays.sort(this.courses);
		
		asmtsView = new JList<Assignment>(){{
			setFixedCellHeight(30);
			setFixedCellWidth(asmtWidth);
			setBackground(null);
			setFont(new Font("Arial", Font.PLAIN, 20));
		}};
		JScrollPane asmtsScroll = new JScrollPane(asmtsView);
		
		final ListSelectionListener selectLis = new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				asmtsView.setListData(
						courses[courseView.getSelectedIndex()].getAsmts().toArray(
								new Assignment[10]));
			}
		};
		
		courseView = new JList<Course>(this.courses) {{
			setSelectionBackground(new Color(0, 128, 255));
			setFixedCellHeight(30);
			setFixedCellWidth(sidebarWidth);
			setBackground(null);
			setFont(new Font("Arial", Font.PLAIN, 20));
			addListSelectionListener(selectLis);
		}};
	
		JScrollPane sidebar = new JScrollPane(courseView);
		sidebar.setBorder(new MatteBorder(0, 0, 0, 1, Color.black));
		add(sidebar, BorderLayout.WEST);
		add(asmtsScroll, BorderLayout.CENTER);
	}
}
