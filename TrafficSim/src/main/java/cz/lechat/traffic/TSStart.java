package cz.lechat.traffic;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class TSStart {

	// Instance mapy
	private MapCanvas mapCanvas;
	
	// Interval obnovy mapy v ms
	private static final int MAP_REFRESH_INTERVAL = 25;

	
	/**
	 * Runs the application
	 */
	public void run() {
		final Display display = new Display();
		Shell shell = new Shell(display);
		shell.setText("TrafficSim");
		
		mapCanvas = new MapCanvas(shell);
		
		shell.open();

		// Casovac na obnovu mapy
		Runnable runnable = new Runnable() {
			public void run() {
				mapCanvas.refresh();
				display.timerExec(MAP_REFRESH_INTERVAL, this);
			}
		};

		// Spusti casovac
		display.timerExec(MAP_REFRESH_INTERVAL, runnable);

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}

		// Kill the timer
		display.timerExec(-1, runnable);
		display.dispose();
	}


	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		new TSStart().run();
	}

}
