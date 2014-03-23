package gui.graph;

/**
 * A range is a type of data format useful for graphing.
 */
public class Range {
	private double lower, higher;
	
	/**
	 * Creates a new range from lower/higher points.
	 */
	public Range(double lower, double higher) {
		if (higher < lower) {
			double t = lower;
			lower = higher;
			higher = t;
		}
		this.lower = lower;
		this.higher = higher;
	}
	/**
	 * Gets an equal distribution of n points between j and k.
	 * @param n Our number of points
	 */
	public double[] getEqualDistribution(int n) {
		double[] dist = new double[n];
		for (int i=0; i<n; i++)
			dist[i] = lower + (double)i/((double)(n-1)) * (higher-lower);
		return dist;
	}
	/**
	 * Gets an equal distribution between j and k, assuming j and k are integers
	 * and our distance between points a and a+1 is 1 (integer).
	 */
	public double[] getEqualDistribution() {
		return getEqualDistribution((int)higher - (int)lower);
	}
}
