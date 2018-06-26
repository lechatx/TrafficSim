package cz.lechat.traffic.objects;

import org.eclipse.swt.graphics.GC;

/**
 * Abstrakni objekt v mape jako hlavni spolecny predek
 * @author lechat
 *
 */
public abstract class MapObject {

	// Pozice x
	protected double x;
	
	// Pozice y
	protected double y;
	
	/**
	 * 
	 * @param x
	 * @param y
	 */
	public MapObject(double x, double y) {
		super();
		this.x = x;
		this.y = y;
	}

	/**
	 * 
	 * @return
	 */
	public double getX() {
		return x;
	}
	
	/**
	 * 
	 * @param x
	 */
	public void setX(double x) {
		this.x = x;
	}
	
	/**
	 * 
	 * @return
	 */
	public double getY() {
		return y;
	}
	
	/**
	 * 
	 * @param y
	 */
	public void setY(double y) {
		this.y = y;
	}
	
	
	/**
	 * Slouzi k vykresleni daneho objektu na mapu
	 */
	public abstract void draw(final GC gcImage); 
	
	
	/**
	 * Slouzi k prepocitani vsech internich hodnot daneho objektu
	 */
	public abstract void calculate(final long timeDiff);
	
	
	/**
	 * Vraci true pokud je dany objekt viditelny v aktivnim okne mapy
	 * @return
	 */
	public abstract boolean isVisible(int x, int y, int width, int height);
	
	
}
