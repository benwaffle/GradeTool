package gui.calendar;

import java.awt.*;
import calendar.*;

public class NotificationWindow {
	private CalendarEvent e;
	private boolean open = false;
	
	public NotificationWindow(CalendarEvent e) {
		this.e = e;
	}
	/**
	 * Renders the current notification window.
	 * @param g The graphics component to render with.
	 */
	public void render(Graphics2D g, Dimension bounds) {
		Rectangle box = new Rectangle((bounds.width-300)/2,
			(bounds.height-400)/2, 300, 400);
		g.setColor(Color.white.darker());
		g.fillRect((int)box.getX(), (int)box.getY(), (int)box.getWidth(),
			(int)box.getHeight());
		// TODO: populate with information from CalendarEvent e
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
}
