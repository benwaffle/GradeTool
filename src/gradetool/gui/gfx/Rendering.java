package gradetool.gui.gfx;

import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * A set of useful functions for rendering to the display.
 */
public class Rendering {
	public static void centerText(Graphics2D g, String s, int x, int y) {
		int len = (int) g.getFontMetrics().getStringBounds(s, g).getWidth();
		g.drawString(s, x - len/2, y);
	}
	/**
	 * Determines if a point is within a rectangular boundary.
	 */
	public static boolean pointWithin(Point p, int x, int y, int width, int height) {
		return p.getX() >= x && p.getX() <= x+width 
			&& p.getY() >= y && p.getY() <= y+height;
	}
}
