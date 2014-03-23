package gradetool.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableCellRenderer;

import gradetool.data.*;

@SuppressWarnings("serial")
public class PSPanel extends JPanel {
	JList<Course> courseView;
	JTable asmtsView;
	
	Course[] courses;
	
	public PSPanel(int parentWidth, Course[] coursesFromPs) {
		setLayout(new BorderLayout());
		
		final int sidebarWidth = parentWidth / 5, asmtWidth = sidebarWidth * 4;
		
		courses = coursesFromPs;
		Arrays.sort(this.courses);
		
		asmtsView = new JTable(30, 1);
		asmtsView.setDefaultRenderer(Assignment.class, new AssignmentCellRenderer());
		
		JScrollPane asmtsScroll = new JScrollPane(asmtsView);
		
		final ListSelectionListener selectLis = new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				
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

class AssignmentCellRenderer extends JPanel implements TableCellRenderer {

	@Override
	public Component getTableCellRendererComponent(
			JTable table,
			Object value,
			boolean isSelected,
			boolean hasFocus,
			int row,
			int column) {
		this.add(new JLabel(value.toString()));
		this.add(new JButton(">"));
		
		return this;
	}
	
}
