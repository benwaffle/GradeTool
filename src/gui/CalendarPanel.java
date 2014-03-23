package gui;

import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import javax.swing.*;

import java.util.*;
import java.text.*;

import gui.gfx.*;

/**
 * Calendar item.
 */
@SuppressWarnings("serial")
public class CalendarPanel extends JPanel {
	// drawing
	private GraphicsConfiguration gc;
	private Dimension content;
	
	// data
	private Date date;
	private String[] days = new String[] {
		"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday",
		"Saturday"
	};
	
	// style
	private Color bgColor;
	int top = 130, btm = 40;
	int rheight, lincr;
	int rwidth, rincr;
	
	public CalendarPanel() {
		super(true); // set isDoubleBuffered to true
		gc = GraphicsEnvironment.getLocalGraphicsEnvironment()
				.getDefaultScreenDevice().getDefaultConfiguration();
		
		setSize(content = new Dimension(getWidth(), getHeight()));
		setBackground(new Color(0,0,0,0));
		
		// locale
		date = new Date();
		
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
		// style
		rheight = content.height - top - btm; // - top - bottom
			lincr = (int)Math.round((double)rheight/5);
		rwidth = content.width - btm;
			rincr = (int)Math.round((double)rwidth/7);
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
		for (int i=0; i<6; i++) // horizontal
			g.drawLine(20, top + i*lincr, content.width - 20, top + i*lincr);
		for (int i=0; i<8; i++) // vertical
			g.drawLine(20 + i*rincr, 100, 20 + i*rincr, content.height-btm);
	}
	private void renderLabels(Graphics2D g) {
		g.setColor(new Color(140,140,140));
		g.setFont(new Font("Arial", Font.PLAIN, 14));
		// render days
		for (int i=0; i<days.length; i++)
			Rendering.centerText(g, days[i], 20 + i*rincr + rincr/2, 120);
		// render numerical days
		for (int i=0; i<28; i++);
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
		renderGrid(g);
		renderLabels(g);
		
		screenUpdate();
		repaint();
	}
	// event handling
	protected void processMouseMotionEvent(MouseEvent e) {
		System.out.println(e.getX());
	}
}
