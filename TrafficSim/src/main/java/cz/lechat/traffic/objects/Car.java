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
	public void draw(GC gcImage) {
		gcImage.setBackground(new Color(Display.getCurrent(), 128,128,128));
		gcImage.fillRectangle((int)x, (int)y, 50, 50);
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
