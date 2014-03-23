package gui.gfx;

public class Animation {
	/* Start and end times for our animation. */
	private long start, end;
	/* Length of our animation, in milliseconds. */
	public final int duration;
	
	/**
	 * Creates a new animation.
	 * @param duration The length of the animation, in milliseconds.
	 */
	public Animation(int duration) {
		this.duration = duration;
	}
}
