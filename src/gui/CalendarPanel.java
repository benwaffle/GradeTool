package gui;

import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

/**
 * Calendar item.
 */
@SuppressWarnings("serial")
public class CalendarPanel extends JPanel {
	// drawing
	private GraphicsConfiguration gc;
	private Dimension content;
	
	// style
	private Color bgColor;
	
	public CalendarPanel() {
		super(true); // set isDoubleBuffered to true
		gc = GraphicsEnvironment.getLocalGraphicsEnvironment()
				.getDefaultScreenDevice().getDefaultConfiguration();
		
		setSize(content = new Dimension(getWidth(), getHeight()));
		setBackground(new Color(0,0,0,0));
		
		// event handling
		enableEvents(MouseEvent.MOUSE_MOVED);
		
		// colors
		bgColor = new Color(32, 32, 32);
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
		super.paint(g);
	}
	
	// render functions
	private void renderTitle(Graphics2D g) {
		//String month = .getDisplayName(Calendar.MONTH,
			//Calendar.SHORT_FORMAT, new Locale("en_US"));
		g.setColor(new Color(220,250,255));
		g.setFont(new Font("Arial", Font.PLAIN, 30));
		g.drawString("March 2014", 30, 50);
	}
	private void renderGrid(Graphics2D g) {
		
	}
	// render functions
	
	/* Renders contents to the <code>canvas</code> from graphics. */
	public void paintComponent(Graphics graphics) { // formerly render(Graphics2D g)
		Graphics2D g = (Graphics2D) graphics;
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
							RenderingHints.VALUE_ANTIALIAS_ON);
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
							RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		
		// render background
		g.setColor(bgColor);
		g.fillRect(0, 0, content.width, content.height);
		
		renderTitle(g);
		
		screenUpdate();
		repaint();
	}
	// event handling
	protected void processMouseMotionEvent(MouseEvent e) {
		System.out.println(e.getX());
	}
}
