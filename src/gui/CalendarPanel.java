package gui;

import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import javax.swing.*;

import java.util.*;
import java.text.*;

import gui.gfx.*;
import gui.calendar.*;
import data.*;

/**
 * Calendar item.
 */
@SuppressWarnings("serial")
public class CalendarPanel extends JPanel {
	// drawing
	private GraphicsConfiguration gc;
	private Dimension content;
	
	// data
	private Calendar cal;
	private Date date;
	private String[] days = new String[] {
		"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday",
		"Saturday"
	};
	private Rectangle[] rects; // corresponding rectangles
	private ArrayList<Assignment>[] assignments; // list of events
	
	// style
	private Color bgColor;
	int top = 130, btm = 40;
	int rheight, lincr;
	int rwidth, rincr;
	
	// event handling
	private Point mouse;
	private boolean mouseDown;
	private NotificationWindow modal; // our modal notification dialog
	
	public CalendarPanel() {
		super(true); // set isDoubleBuffered to true
		gc = GraphicsEnvironment.getLocalGraphicsEnvironment()
				.getDefaultScreenDevice().getDefaultConfiguration();
		
		setSize(content = new Dimension(getWidth(), getHeight()));
		setBackground(new Color(0,0,0,0));
		
		// locale
		date = new Date();
		cal = Calendar.getInstance();
		
		// event handling
		enableEvents(MouseEvent.MOUSE_MOVED | MouseEvent.MOUSE_CLICKED
			| MouseEvent.MOUSE_PRESSED);
		mouse = new Point(-1,-1);
		addMouseListener(new MouseListener() {
			public void mousePressed(MouseEvent e) {
				mouse = e.getPoint();
				mouseDown = true;
			}
			public void mouseReleased(MouseEvent e) {
				mouse = e.getPoint();
				mouseDown = false;
			}
			public void mouseClicked(MouseEvent e) {
				mouse = e.getPoint();
			}
			public void mouseEntered(MouseEvent e) {
				mouse = e.getPoint();
			}
			public void mouseExited(MouseEvent e) {
				mouse = e.getPoint();
			}
		});
		
		// colors
		bgColor = new Color(32, 32, 32);
		
		// data
		rects = new Rectangle[31];
		assignments = new ArrayList[31];
	}
	/**
	 * Creates a new <code>BufferedImage</code> from graphics. Useful for
	 * double-buffering.
	 * @param gc A configuration for graphics.
	 * @param bounds Dimension of our window to draw graphics in.
	 * @param alpha Whether or not our image has an alpha channel.
	 * @return A new buffered image.
	 */
	public BufferedImage createBufferedImage(Dimension bounds, boolean alpha) {
		return gc.createCompatibleImage(bounds.width, bounds.height,
				alpha ? Transparency.TRANSLUCENT : Transparency.OPAQUE);
	}
	/**
	 * Refreshes contents in the buffer.
	 * @return If successful or not.
	 */
	public void screenUpdate() {
		Toolkit.getDefaultToolkit().sync();
	}
	public void paint(Graphics g) {
		content = new Dimension(getWidth(), getHeight());
		// style
		rheight = content.height - top - btm; // - top - bottom
			lincr = (int)Math.round((double)rheight/6);
		rwidth = content.width - btm;
			rincr = (int)Math.round((double)rwidth/7);
		
		// reset map items
		// update rendering of numerical days
		for (int i=0; i<assignments.length; i++)
			if (assignments[i] != null)
				for (Assignment a : assignments[i]) {
					Rectangle r = rects[i];
					int dOff = 7 - a.getCalendar().getMinimalDaysInFirstWeek();
					int x = 20 + rincr*(a.getCalendar().get(Calendar.DAY_OF_WEEK)-1),
						y = top + lincr*((dOff+a.getCalendar().get(Calendar.DAY_OF_MONTH))/7);
					r = new Rectangle(x, y, rincr, lincr);
					rects[i] = r; // reset rectangles
				}
		super.paint(g);
	}
		
	// render functions
	private void renderTitle(Graphics2D g) {
		String month = new SimpleDateFormat("MMMM", Locale.ENGLISH).format(date),
			year = new SimpleDateFormat("yyyy", Locale.ENGLISH).format(date);
		g.setColor(new Color(240,250,255));
		g.setFont(new Font("Arial", Font.PLAIN, 30));
		g.drawString(month + " " + year, 30, 50);
	}
	private void renderGrid(Graphics2D g) {
		g.setColor(new Color(140,140,140,140));
		for (int i=0; i<7; i++) // horizontal
			g.drawLine(20, top + i*lincr, content.width - 20, top + i*lincr);
		for (int i=0; i<8; i++) // vertical
			g.drawLine(20 + i*rincr, top-40, 20 + i*rincr, content.height-btm);
		// render rectangles for selection
		for (int x=20; x<content.width-20; x+=rincr)
			for (int y=top; y<content.height-btm; y+=lincr)
				if (Rendering.pointWithin(mouse, x, y, rincr, lincr)
				&& Rendering.pointWithin(mouse, 20, top, content.width-20,
					content.height-btm-top)) {
					Color old = g.getColor();
					g.setColor(mouseDown ? new Color(149,229,55)
						: new Color(49,129,217));
					g.fillRect(x, y, rincr, lincr);
					g.setColor(old);
				}
	}
	private void renderLabels(Graphics2D g) {
		g.setColor(new Color(140,140,140));
		g.setFont(new Font("Arial", Font.PLAIN, 14));
		// render days
		for (int i=0; i<days.length; i++)
			Rendering.centerText(g, days[i], 20 + i*rincr + rincr/2, 120);
		// render numerical days
		int last = cal.getActualMaximum(Calendar.DAY_OF_MONTH),
			day = 7 - cal.getMinimalDaysInFirstWeek(),
			iDay = day;
		for (int i=iDay; i<last+iDay; day=(++i)%7)
			g.drawString((i-iDay+1)+"", 20+10+day*rincr, top+20+lincr*(i/7));
	}
	private void renderEvents(Graphics2D g) {
		g.setColor(Color.red);
		for (int i=0; i<assignments.length; i++)
			if (assignments[i] != null)
				for (int j=0; j<assignments[i].size(); j++) {
					ArrayList<Assignment> as = assignments[i];
					Assignment a = as.get(j);
					int px = 30, px2 = 10, py = 10;
					Rectangle r = rects[i],
						ar = new Rectangle((int)r.getX() + px,
						(int)(r.getY() + r.getHeight()*j/((double)as.size()))
							+ (j == 0 ? py : 0), 
						(int)r.getWidth() - (px+px2),
						(int)(r.getHeight()/((double)as.size())) - 2*py);
					g.fillRect((int)ar.getX(), (int)ar.getY(),
						(int)ar.getWidth(), (int)ar.getHeight());
					Color old = g.getColor();
					g.setColor(new Color(210,210,210));
					g.setFont(new Font("Arial", Font.PLAIN, 14));
					g.drawString(a.getTitle(), (int)ar.getX(), (int)ar.getY()
						+ (int)(ar.getHeight()*0.8));
					g.setColor(old);
				}
	}
	private void renderNotification(Graphics2D g) {
		if (modal != null)
			modal.render(g, content); // render with notification window
	}
	// render functions
	
	/* Renders contents to the <code>canvas</code> from graphics. */
	public void paintComponent(Graphics graphics) { // formerly render(Graphics2D g)
		Graphics2D g = (Graphics2D) graphics;
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
							RenderingHints.VALUE_ANTIALIAS_ON);
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
							RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		
		g.clearRect(0, 0, content.width, content.height);
		// render background
		g.setColor(bgColor);
		g.fillRect(0, 0, content.width, content.height);
		
		renderTitle(g);
		renderGrid(g);
		renderEvents(g);
		renderLabels(g);
		renderNotification(g); // modal goes above all
		
		screenUpdate();
		// repaint(); // called on mouse events	
	}
	
	// event handling
	protected void processMouseMotionEvent(MouseEvent e) {
		mouse = e.getPoint();
		repaint();
	}
	/**
	 * Displays a new notification window as a modal dialog in the Calendar.
	 * @param win A notification window to display.
	 */
	public void displayNotification(NotificationWindow win) {
		modal = win;
		modal.set(true); // open the notification object
	}
	
	// data handling
	public void addAssignment(Assignment a) {
		for (ArrayList<Assignment> as : assignments)
			if (as != null && as.contains(a)) return;// prevent duplicate entries
		Calendar cal = a.getCalendar();
		int dOff = 7 - cal.getMinimalDaysInFirstWeek();
		int d = cal.get(Calendar.DAY_OF_MONTH);
		Rectangle r = new Rectangle(20 + d*rincr, top + lincr*((dOff+d)/7),
			rincr, lincr);
		rects[d-1] = r;
		if (assignments[d-1] == null)
			assignments[d-1] = new ArrayList<Assignment>();
		assignments[d-1].add(a);
	}
}
