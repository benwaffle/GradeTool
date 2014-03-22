package gui.graph;

import java.awt.*;
import java.awt.image.*;
import data.*;

/**
 * A <code>ChartModel</code> is a basic chart type that provides particular
 * analysis of a given dataset.
 */
public abstract class ChartModel {
	/**
	 * The name of the chart, for example, a "Bar" or "Scatter Plot."
	 */
	private String name;
	/**
	 * Renders the <code>ChartModel</code> to a graphics buffer.
	 * @param g The graphics object to render with.
	 * @param data A set of data relevant to this graph.
	 */
	public abstract void render(Graphics g, DataModel data);
	/**
	 * Returns the name of the <code>ChartModel</code>.
	 */
	public abstract String toString();
}
