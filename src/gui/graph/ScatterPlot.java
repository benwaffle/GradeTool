package gui.graph;

/**
 * A specific type of <code>ChartModel</code> that deals with the plotting of
 * XY-data.
 */
public class ScatterPlot extends ChartModel {
	private String title; // the title of the chart
	private XYData data;
	
	public void render(Graphics g, XYData data) {
		Graphics2D g2d = (Graphics2D) g;
		
	}
	public String toString() {
		return "ScatterPlot: \n" +
			"\tTitle: " + title + "\n";
	}
}
