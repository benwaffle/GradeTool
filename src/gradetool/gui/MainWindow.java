package gradetool.gui;

import gradetool.gui.calendar.NotificationWindow;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JPanel;

import powerschool.rest.vo.xsd.AssignmentVO;
import powerschool.rest.vo.xsd.SectionVO;
import powerschool.rest.vo.xsd.StudentDataVO;

import com.benwaffle.pslib.PSlib;

import gradetool.data.Assignment;
import gradetool.data.Course;

@SuppressWarnings("serial")
public class MainWindow extends JFrame {
	/**
	 * The dimensions of the computer screen
	 */
	private Dimension screen;
	
	/**
	 * The dimensions of the application window
	 */
	private Dimension window;
	
	/**
	 * CardLayout to switch between PowerSchool and Calendar views
	 */
	private CardLayout layoutMgr;
	
	/**
	 * Wrapper to hold all "cards"
	 */
	private JPanel wrapper;
	
	/**
	 * The calendar JPanel
	 */
	private CalendarPanel calendar;
	
	/**
	 * The powerschool JPanel
	 */
	private PSPanel powerschool;
	
	public Course[] getCourses(PSlib lib){
		if (lib == null) return null;
		StudentDataVO data = lib.getStudentData();
		HashMap<Long, Course> tmpCourses = new HashMap<Long, Course>();
		
		for (SectionVO sec : data.getSections()){ // get all courses
			if (sec.getSchoolCourseTitle().toLowerCase().contains("lunch") ||
					sec.getSchoolCourseTitle().toLowerCase().contains("homeroom"))
				continue;
			if (!tmpCourses.containsKey(sec.getId()))
				tmpCourses.put(sec.getId(), new Course(sec.getSchoolCourseTitle(), sec.getId()));
		}
		
		for (AssignmentVO ass : data.getAssignments()){
			Calendar dueDate = Calendar.getInstance();
			dueDate.set(ass.getDueDate().getYear(), 
					ass.getDueDate().getMonth(), 
					ass.getDueDate().getDay());
			
			tmpCourses.get(ass.getSectionid()).addAsmt(					
					new Assignment(
							ass.getName(), // ps assignment name 
							dueDate.getTime(),
							ass.getDescription(), // ps description
							ass.getId(), // ps assignment ID
							ass.getSectionid())); // ps course ID
		}
		
		return tmpCourses.values().toArray(new Course[tmpCourses.size()]);
	}
	
	public MainWindow(PSlib lib) {		
		// this window
		super("GradeTool");
		setResizable(true);
		
		// set dimensions and center window
		screen = Toolkit.getDefaultToolkit().getScreenSize();
		window = new Dimension(1244, 700);
		setBounds((screen.width - window.width) / 2, 
				(screen.height - window.height) / 2, window.width, window.height);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// create a wrapper 
		wrapper = new JPanel(layoutMgr = new CardLayout());
		
		// buttons in the Toolbar should show correct panel in card layout
		ActionListener switchPanels = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand().equals("showcal"))
					layoutMgr.show(wrapper, "cal");
				else if (e.getActionCommand().equals("showps"))
					layoutMgr.show(wrapper, "ps");
			}
		};
		
		// add toolbar to this window
		getContentPane().add(new Toolbar(this.getWidth(), switchPanels, 
				lib.getStudentData().getStudent().getFirstName() + " " + lib.getStudentData().getStudent().getLastName()), 
				BorderLayout.NORTH);
		
		// create calendar and powerschool panels
		calendar = new CalendarPanel();
		powerschool = new PSPanel(getWidth(), getCourses(lib), calendar);
		
		// add panels to the wrapper
		wrapper.add(powerschool, "ps");
		wrapper.add(calendar, "cal");
		
		// add wrapper to current window
		getContentPane().add(wrapper);
		
		// test
		testCalendarData(calendar);
		
		// show this window
		setVisible(true);
	}
	/**
	 * Tests functionality of the <code>CalendarPanel</code>.
	 */
	public void testCalendarData(CalendarPanel c) {
		Date current = new Date();
		NotificationWindow nw = new NotificationWindow(
			new Assignment("Floating Project", new Date(), "Mr. Weems", 0L, 0L)
		);
		c.addAssignment(new Assignment("Floating Project", new Date(), "Mr. Weems", 0L, 0L));
		c.addAssignment(new Assignment("Microscopy Paper", new Date(), "Teacher", 0L, 0L));
		current.setDate(current.getDay() + 10); // set ten days ahead
		c.addAssignment(new Assignment("Project", current, "Data", 0L, 0L));
	}
}
