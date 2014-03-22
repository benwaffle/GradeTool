package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.image.*;

public class MainWindow extends JFrame implements Runnable {
	private boolean started = false; 
	private boolean running = false;
	
	private Dimension screen;
	private Dimension window; // window _content_ (less than actual window)
	
	// buffering
	private Graphics2D g, bg; // graphics and background graphics
	private BufferStrategy strategy;
	private BufferedImage background;
	
	// drawing
	private GraphicsConfiguration gConfig;
	private Canvas canvas;
	
	// data
	
	/**
	 * Creates a new <code>MainWindow</code> from basic window parameters.
	 * @param title The window's displayed title.
	 * @param width The width of the window.
	 * @param height The height of the window.
	 */
	public MainWindow(String title, int width, int height) {
		super(title);
		
		gConfig = GraphicsEnvironment.getLocalGraphicsEnvironment()
				.getDefaultScreenDevice().getDefaultConfiguration();
		screen = Toolkit.getDefaultToolkit().getScreenSize();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds((screen.width-width)/2, (screen.height-height)/2, width, height);
		window = new Dimension(getSize().width,
				getSize().height - getInsets().top - getInsets().bottom);
		canvas = new Canvas(gConfig) {
			public void update(Graphics g) {
				System.out.println("Update.");
			}
		};
		
		setResizable(true);
		setVisible(true);
		
		getContentPane().add(canvas, 0);
		background = createBufferedImage(gConfig, window, true);
		canvas.createBufferStrategy(2); // two buffers
		
		do {
			strategy = canvas.getBufferStrategy();
		} while (strategy == null);
		start();
	}
	/**
	 * Creates a new <code>BufferedImage</code> from graphics. Useful for
	 * double-buffering.
	 * @param gc A configuration for graphics.
	 * @param bounds Dimension of our window to draw graphics in.
	 * @param alpha Whether or not our image has an alpha channel.
	 * @return A new buffered image.
	 */
	public BufferedImage createBufferedImage(GraphicsConfiguration gc,
			Dimension bounds, boolean alpha) {
		return gc.createCompatibleImage(bounds.width, bounds.height,
				alpha ? Transparency.TRANSLUCENT : Transparency.OPAQUE);
	}
	/**
	 * Refreshes contents in the buffer.
	 * @return If successful or not.
	 */
	public boolean screenUpdate() {
		g.dispose(); // free resources
		g = null;
		
		try {
			strategy.show();
			Toolkit.getDefaultToolkit().sync();
			return !strategy.contentsLost();
		} catch (NullPointerException e) {
			System.out.println("NullPointerException encountered: ");
			e.printStackTrace();
		} catch (IllegalStateException e) {
			System.out.println("IllegalStateException encountered: ");
			e.printStackTrace();
		}
		
		return true;
	}
	/**
	 * Gets the main screen buffer.
	 * @return The front buffer for the graphics.
	 */
	public Graphics2D getBuffer() {
		if (g == null)
			try {
				g = (Graphics2D) strategy.getDrawGraphics();
			} catch (IllegalStateException e) {
				System.out.println("Illegal state for buffer strategy.");
				e.printStackTrace();
			}
		return g;
	}
	/* Renders contents to the <code>canvas</code> from graphics. */
	public void render(Graphics2D g2d) {
		g2d.setColor(Color.black);
		// g2d.fillRect(0, 0, window.width, window.height);
		g2d.fillArc(30, 30, 200, 200, 0, 360);
	}
	/* Starts the running of the MainWindow. */
	public void start() {
		started = true;
		running = true;
		run();
	}
	/**
	 * Begins running of the MainWindow. Performs double-buffering and draw
	 * calls.
	 */
	public void run() {
		if (!started) return;
		main: while (running) {
			bg = (Graphics2D) background.getGraphics();
			do {
				if (!running) break main;
				Graphics2D gb = getBuffer(); // front-screen buffer
				render(bg); // render with background graphics (offscreen)
				gb.drawImage(background, 0, 0, null); // draw to screen 
			} while (!screenUpdate());
		}
	}
}
