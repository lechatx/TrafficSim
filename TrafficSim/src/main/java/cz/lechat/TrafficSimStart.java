package cz.lechat;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * 
 * @author lechat
 *
 */
public class TrafficSimStart {

	private Road road = new Road(10);
	private boolean running = false;
	private Canvas canvas; 
	
	
	
	/**
	 * 
	 */
	public void run() {
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setText("Traffic Sim");
		createContents(shell);
		shell.open();
		
		
		// Set up the timer for the animation
		Runnable runnable = new Runnable() {
			public void run() {
				if (road != null && canvas != null) {
					road.recalc();
					canvas.redraw();
				}
				display.timerExec(50, this);
			}
		};

	    // Launch the timer
	    display.timerExec(150, runnable);

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
	 * @param shell
	 */
	private void createContents(Shell shell) {
		shell.setLayout(new FillLayout());
		final Canvas canvas = new Canvas(shell, SWT.NONE);
		
		this.canvas = canvas;
		
		final Button button = new Button(canvas, SWT.PUSH);
		button.setBounds(10, 10, 300, 40);
		button.setText("Start traffic");
		button.addMouseListener(new MouseListener() {
			public void mouseUp(MouseEvent arg0) {
				running = !running;
				String label = running ? "Start traffic" : "Stop traffic";
				button.setText(label);
			}
			public void mouseDown(MouseEvent arg0) {}
			public void mouseDoubleClick(MouseEvent arg0) {}
		});
		
			
		canvas.addPaintListener(new PaintListener() {
			public void paintControl(PaintEvent e) {
					road.redrawRoad(e, running);
			}
		});
	}

	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		new TrafficSimStart().run();
	}

}
