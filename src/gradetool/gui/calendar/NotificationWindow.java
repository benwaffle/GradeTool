package gradetool.gui.calendar;

import java.awt.*;
import java.text.*;
import java.util.*;

import javax.swing.JOptionPane;

import gradetool.data.*;
import gradetool.gui.gfx.*;
import gradetool.net.*;

public class NotificationWindow {
	private Assignment e;
	private Rectangle rect;
	private boolean open = false;
	
	// rendering
	private Color bg;
	
	public NotificationWindow(Assignment e) {
		this.e = e;
		bg = new Color(194,194,194,210);
	}
	/**
	 * Renders the current notification window.
	 * @param g The graphics component to render with.
	 */
	public void render(Graphics2D g, Dimension bounds) {
		if (!open) return; // do not render if not open
		rect = new Rectangle((bounds.width-300)/2,
			(bounds.height-400)/2, 300, 300);
		g.setColor(bg);
		g.fillRect((int)rect.getX(), (int)rect.getY(), (int)rect.getWidth(),
			(int)rect.getHeight());
		
		// populate with information from Assignment asmn
		g.setColor(Color.white);
		g.setFont(new Font("Arial", Font.PLAIN, 20));
		Rendering.centerText(g, e.getTitle(), // title
			(int)(rect.getX()+rect.getWidth()/2),
			(int)rect.getY() + 20);
		g.setFont(new Font("Arial", Font.PLAIN, 14));
		String due = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
			.format(e.getDueDate());
		Rendering.centerText(g, "Due: "+due, // date
			(int)(rect.getX()+rect.getWidth()/2),
			(int)rect.getY() + 100);
		Rendering.centerText(g, "Description: "+e.getDescription(), // date
			(int)(rect.getX()+rect.getWidth()/2),
			(int)rect.getY() + 180);
	}
	// act on mouse click
	public void act(Point p) {
		if (!rect.contains(p)) return;
		bg = bg.darker();
//		PostNotification.addToTwilioQueue(JOptionPane.showInputDialog("Phone number"), e.getTitle(), e.getDueDate());
		PostNotification.addToTwilioQueue(e);
	}
	/**
	 * Either opens or closes the <code>NotificationWindow</code>.
	 * @param isOpen Whether or not the window is open.
	 */
	public void set(boolean isOpen) {
		open = isOpen;
	}
	public boolean closed() {
		return !open;
	}
	public Assignment getAssignment() {
		return e;
	}
	/**
	 * Gets this window's bounding rectangle.
	 */
	public Rectangle getRect() {
		return rect;
	}
}
