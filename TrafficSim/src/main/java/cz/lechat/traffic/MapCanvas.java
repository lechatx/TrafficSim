package cz.lechat.traffic;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseWheelListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Shell;

import cz.lechat.traffic.objects.Car;
import cz.lechat.traffic.objects.MapObject;
import cz.lechat.traffic.objects.TrafficLights;

/**
 * Vykresluje mapu se vsemy objekty
 * 
 * 
 * @author lechat
 *
 */
public class MapCanvas {

	// Canvas
	private Canvas mapCanvas;
	
	// Meritko mapy
	private double scale = 5.0d;
	
	// Pozice nahledoveho okna
	private int viewX = 0;
	private int viewY = 0;
	
	private long lastRefreshTime = System.currentTimeMillis();
	
	// Seznam vsech objektu na mape
	private List<MapObject> mapObjects = new ArrayList<>();
	
	/**
	 * 
	 * @param shell
	 */
	public MapCanvas(final Shell shell) {
		shell.setLayout(new FillLayout());
		
		
		mapObjects.add(new Car(10d,10d));
		mapObjects.add(new Car(15d,10d));
		mapObjects.add(new Car(20d,10d));
		mapObjects.add(new TrafficLights(22d,14d));
		
		mapObjects.add(new Car(30d,10d));
		mapObjects.add(new TrafficLights(32d,14d));

		// Create the canvas for drawing
		mapCanvas = new Canvas(shell, SWT.NO_BACKGROUND);
		mapCanvas.addPaintListener(new PaintListener() {
			
			/**
			 * Prekresleni mapy, zde probiha kresleni vsech prvku pomoci double bufferingu
			 */
			public void paintControl(PaintEvent event) {
				// Create the image to fill the canvas
				Image image = new Image(shell.getDisplay(), mapCanvas.getBounds());

				// Set up the offscreen gc
				GC gcImage = new GC(image);
				
				// Draw the background
				gcImage.setBackground(event.gc.getBackground());
				gcImage.fillRectangle(image.getBounds());

				// Vykresli vsechny objekty na mape
				for (MapObject object : mapObjects) {
					
					int width = mapCanvas.getBounds().width;
					int height = mapCanvas.getBounds().height;
					
					if (object.isVisible(0, 0, width, height)) {
						object.draw(gcImage, viewX, viewY, scale);
					}
				}
				
				// Draw the offscreen buffer to the screen
				event.gc.drawImage(image, 0, 0);

				// Clean up
				image.dispose();
				gcImage.dispose();
			}
		});
		
		// Zaregistrujeme udalost na kolecko mysi jako ovladani meritka mapy
		mapCanvas.addMouseWheelListener(event -> { 
			scale += (((double)event.count) / 50d) * scale/2 ;
			System.out.println(String.format("Current scale : %.2f",scale));
		});
		
//		mapCanvas.addMouseMoveListener(event -> {
//			System.out.println(event.x);
//			System.out.println(event.y);
//		});
		
	}
	
	/**
	 * Obnova mapy, zde probihaji vypocty
	 */
	public void refresh() {

		// Zjistime cas od posledniho vypoctu
		long timeDiff = System.currentTimeMillis() - lastRefreshTime;
		
		// Prepocita vsechny objekty na mape
		for (MapObject object : mapObjects) {
			object.calculate(timeDiff);
		}
		
		// Ulozime si cas posledniho vypoctu
		lastRefreshTime = System.currentTimeMillis();
		
		// Prekresli mapu
		mapCanvas.redraw();
	}
	
}
