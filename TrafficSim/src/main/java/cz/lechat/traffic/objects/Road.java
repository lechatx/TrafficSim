package cz.lechat.traffic.objects;

import org.eclipse.swt.graphics.GC;

/**
 * 
 * @author lechat
 *
 */
public class Road extends MapObject {
	
	/**
	 * 
	 * @param x
	 * @param y
	 */
	public Road(double x, double y) {
		super(x, y);
	}

	/**
	 * 
	 */
	@Override
	public void draw(GC gcImage) {
		// TODO Auto-generated method stub

	}

	/**
	 * 
	 */
	@Override
	public void calculate(long timeDiff) {
		// TODO Auto-generated method stub

	}
	
	
	/**
	 * 
	 */
	@Override
	public boolean isVisible(int x, int y, int width, int height) {
		return true;
	}

}
