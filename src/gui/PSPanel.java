package gui;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.util.Arrays;
import java.util.Calendar;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableCellRenderer;

import data.Assignment;
import data.Course;

@SuppressWarnings("serial")
public class PSPanel extends JPanel {
	JList<Course> courseView;
	JTable asmtsView;

	Course[] courses;

	final int numAss = 30;

	public PSPanel(int parentWidth, Course[] coursesFromPs, final CalendarPanel calView) {
		setLayout(new BorderLayout());

		final int sidebarWidth = parentWidth / 5, asmtWidth = sidebarWidth * 4;

		courses = coursesFromPs;
		Arrays.sort(this.courses);

		asmtsView = new JTable(numAss, 1) {
			@Override
			public TableCellRenderer getDefaultRenderer(Class<?> columnClass) {
				return new AssignmentCellRenderer();
			}
		};
		asmtsView.setRowHeight(40);
		
		asmtsView.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = asmtsView.rowAtPoint(e.getPoint());
				calView.addAssignment((Assignment)asmtsView.getValueAt(row, 0));
			}
		});

		JScrollPane asmtsScroll = new JScrollPane(asmtsView);

		final ListSelectionListener selectLis = new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				int i = 0;
				for (; i < Math.min(courses[courseView.getSelectedIndex()].getAsmts().size(), numAss); i++)
					asmtsView.setValueAt(
							courses[courseView.getSelectedIndex()].getAsmts().get(i), i, 0);
				for (; i < numAss; i++)
					asmtsView.setValueAt(null, i, 0);
			}
		};

		courseView = new JList<Course>(this.courses) {
			{
				setSelectionBackground(new Color(0, 128, 255));
				setFixedCellHeight(30);
				setFixedCellWidth(sidebarWidth);
				setBackground(null);
				setFont(new Font("Arial", Font.PLAIN, 20));
				addListSelectionListener(selectLis);
			}
		};

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
		
		if (value != null && value instanceof Assignment) {
			Assignment rowAss = (Assignment)value;
			
			setLayout(new FlowLayout());
			
			this.add(new JLabel(rowAss.toString()), FlowLayout.LEFT);
			this.add(new JLabel(
					"Due: " + rowAss.getCalendar().get(Calendar.MONTH) +
					"/" + rowAss.getCalendar().get(Calendar.DAY_OF_MONTH) + 
					"/" + rowAss.getCalendar().get(Calendar.YEAR)
					), FlowLayout.CENTER);
			this.add(new JButton(">"), FlowLayout.RIGHT);
		}
		return this;
	}
}