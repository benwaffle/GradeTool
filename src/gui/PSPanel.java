package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

@SuppressWarnings("serial")
public class PSPanel extends JPanel {
	JList<String> courses;

	public PSPanel(final int parentWidth) {
		setLayout(new BorderLayout());
		courses = new JList<String>( // temporary, replace with powerschool access
				new String[] { "AP Calculus AB", "Honors World Literature", "Biology", "Chemistry" }
		) {
			{
				setSelectionBackground(new Color(0, 128, 255));
				setFixedCellHeight(40);
				setFixedCellWidth(parentWidth / 5);
				setForeground(Color.black);
				setBackground(null);
				setFont(new Font("Arial", Font.PLAIN, 30));
				setBorder(new MatteBorder(0, 0, 0, 1, Color.black));
			}
		};
		
		add(courses, BorderLayout.WEST);
	}
}
