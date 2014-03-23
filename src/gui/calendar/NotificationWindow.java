package gui.calendar;

import java.awt.*;

import data.*;

public class NotificationWindow {
	private Assignment e;
	private boolean open = false;
	
	public NotificationWindow(Assignment e) {
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
}