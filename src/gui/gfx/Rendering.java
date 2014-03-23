package gui.gfx;

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
}
