package gradetool.gui.calendar;

import java.awt.*;

import gradetool.data.*;

public class NotificationWindow {
	private Assignment e;
	private Rectangle rect;
	private boolean open = false;
	
	public NotificationWindow(Assignment e) {
		this.e = e;
	}
	/**
	 * Renders the current notification window.
	 * @param g The graphics component to render with.
	 */
	public void render(Graphics2D g, Dimension bounds) {
		if (!open) return; // do not render if not open
		rect = new Rectangle((bounds.width-300)/2,
			(bounds.height-400)/2, 300, 400);
		g.setColor(Color.white.darker());
		g.fillRect((int)rect.getX(), (int)rect.getY(), (int)rect.getWidth(),
			(int)rect.getHeight());
		// TODO: populate with information from Assignment asmn
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
