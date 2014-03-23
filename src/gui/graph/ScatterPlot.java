package gui.graph;

/**
 * A specific type of <code>ChartModel</code> that deals with the plotting of
 * XY-data.
 */
public class ScatterPlot extends ChartModel {
	private String title; // the title of the chart
	private double[] x, y;
	
	/**
	 * Creates a new <code>ScatterPlot</code>, with title and XY-data.
	 * @param title The title of the chart.
	 * @param x x-coordinate data (domain values)
	 * @param y y-coordinate data (range values)
	 */
	public ScatterPlot(String title, double[] x, double[] y) {
		this.title = title;
		this.x = x;
		this.y = y;
	}
	public ScatterPlot(String title, double... xy) {
		double[] t1 = new double[xy.length/2 - xy.length%2];
		double[] t2 = new double[xy.length/2 - xy.length%2];
		for (int i=0; i+1<xy.length; i+=2)
			t1[i/2] = xy[i];
			t2[i/2] = xy[i+1];
		this(title, t1, t2);
	}
	public void render(Graphics g, DataModel data) {
		Graphics2D g2d = (Graphics2D) g;
		
	}
	public String toString() {
		return "ScatterPlot: \n" +
			"\tTitle: " + title + "\n";
	}
}
