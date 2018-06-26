package cz.lechat.traffic.objects;

import org.eclipse.swt.graphics.GC;

/**
 * Abstraktni objekt v mape jako hlavni spolecny predek
 * @author lechat
 *
 */
public abstract class MapObject {

	// Pozice x
	protected double x;
	
	// Pozice y
	protected double y;
	
	// Pozice z - vrstva
	protected int layer;
	
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
	 * @param x
	 * @param y
	 * @param layer
	 */
	public MapObject(double x, double y, int layer) {
		this(x,y);
		this.layer = layer;
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
	 * 
	 * @return
	 */
	public int getLayer() {
		return layer;
	}

	/**
	 * 
	 * @param layer
	 */
	public void setLayer(int layer) {
		this.layer = layer;
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
	 */
	public abstract boolean isVisible(int x, int y, int width, int height);
	
	
}
