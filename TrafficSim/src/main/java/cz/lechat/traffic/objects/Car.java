package cz.lechat.traffic.objects;


import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.widgets.Display;

/**
 * 
 * @author lechat
 *
 */
public class Car extends MapObject {

	private static final double carWidth = 2.2; // m
	private static final double carHeight= 3.5; // m
	

	/**
	 * 
	 * @param x
	 * @param y
	 */
	public Car(double x, double y) {
		super(x, y);
	}

	/**
	 * 
	 */
	@Override
	public void draw(GC gcImage, double viewX, double viewY, double scale) {
		gcImage.setBackground(new Color(Display.getCurrent(), 128,128,128));
		gcImage.fillRectangle(getObjectPositionX(viewX, scale), getObjectPositionY(viewY, scale), (int)(carHeight*scale), (int)(carWidth*scale));
	}

	/**
	 * 
	 */
	@Override
	public void calculate(final long timeDiff) {
		//System.out.println("Cas = " + timeDiff);
	}

	/**
	 * 
	 */
	@Override
	public boolean isVisible(int x, int y, int width, int height) {
		return true;
	}

}
