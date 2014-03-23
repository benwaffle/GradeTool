package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.*;

import javax.swing.*;

/**
 * Calendar item.
 */
@SuppressWarnings("serial")
public class Calendar extends JPanel implements ActionListener {
	// drawing
	private GraphicsConfiguration gc;
	private Dimension content;
	
	public Calendar() {
		super(true); // set isDoubleBuffered to true
		gc = GraphicsEnvironment.getLocalGraphicsEnvironment()
				.getDefaultScreenDevice().getDefaultConfiguration();
		
		setSize(content = new Dimension(getWidth(), getHeight()));
		setBackground(Color.red);
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
	/* Renders contents to the <code>canvas</code> from graphics. */
	public void paintComponent(Graphics g) { // formerly render(Graphics2D g)
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setColor(Color.black);
		g2d.fillRect(0, 0, content.width, content.height);
		g2d.setColor(Color.red);
		g2d.fillArc(50, 50, 200, 200, 0, 360);
		screenUpdate();
	}
	@Override
	public void actionPerformed(ActionEvent e) {}
}
