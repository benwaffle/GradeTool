package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

@SuppressWarnings("serial")
public class MainWindow extends JFrame {
	private Dimension screen;
	private Dimension window;
	
	private CardLayout layoutMgr;
	
	private JPanel wrapper;
	private Calendar calendar;
	private PSPanel powerschool;
	
	public MainWindow() {
		super("GradeTool");
		setResizable(false);
		
		screen = Toolkit.getDefaultToolkit().getScreenSize();
		window = new Dimension(1244, 700);
		setBounds((screen.width - window.width) / 2,
				(screen.height - window.height) / 2,
				window.width, window.height);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		wrapper = new JPanel(layoutMgr = new CardLayout());
		
		ActionListener switchPanels = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand().equals("showcal"))
					layoutMgr.show(wrapper, "cal");
				else if (e.getActionCommand().equals("showps"))
					layoutMgr.show(wrapper, "ps");
			}
		};
		
		getContentPane().add(new Toolbar(this.getWidth(), switchPanels), BorderLayout.NORTH);
		
		calendar = new Calendar();
		powerschool = new PSPanel();
		
		wrapper.add(calendar, "cal");
		wrapper.add(powerschool, "ps");
		
		getContentPane().add(wrapper);
		setVisible(true);
	}
}
