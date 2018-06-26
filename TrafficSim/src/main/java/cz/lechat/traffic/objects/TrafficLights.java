package cz.lechat.traffic.objects;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.widgets.Display;

/**
 * 
 * @author lechat
 *
 */
public class TrafficLights extends MapObject{
	
	/**
	 * 
	 * @author lechat
	 *
	 */
	private enum TrafficLightsStates {
		RED(3000),
		RED_ORANGE(500),
		ORANGE(1000),
		GREEN(3000);
		
		private int time;
		private TrafficLightsStates(int time) {
			this.time = time;
		}
		public int getTime() {
			return time;
		}
	}
	
	private TrafficLightsStates state = TrafficLightsStates.RED; // Indikuje stav semaforu
	private TrafficLightsStates nextState = null; // Indikuje nasledujici stav semaforu
	
	private long runningTime = 0;	// kolik ms svetlo sviti
	
	private Color red = new Color(Display.getCurrent(), 255,50,50);
	private Color green = new Color(Display.getCurrent(), 50,250,50);
	private Color orange = new Color(Display.getCurrent(), 255,165,50);
	private Color gray = new Color(Display.getCurrent(), 100,100,100);
	
	/**
	 * 
	 * @param x
	 * @param y
	 */
	public TrafficLights(double x, double y) {
		super(x, y);
	}

	/**
	 * 
	 */
	@Override
	public void draw(GC gcImage, double viewX, double viewY, double scale) {
		Color colorRed = gray;
		Color colorOrange = gray;
		Color colorGreen = gray;

		switch (state) {
			case RED : 	
						colorRed = red; 
						break;
						
			case RED_ORANGE : 
						colorRed = red; 
						colorOrange = orange;
						break;
						
			case GREEN : 			
						colorGreen = green; 
						break;
						
			case ORANGE : 			
						colorOrange = orange; 
						break;
		}
		
		int posx = getObjectPositionX(viewX, scale);
		int posy = getObjectPositionY(viewY, scale);
		
		int lightSize = (int)(1.25*scale);
		
		gcImage.setBackground(colorRed);
		gcImage.fillRectangle(posx, posy, lightSize, lightSize);
		
		gcImage.setBackground(colorOrange);
		gcImage.fillRectangle(posx, posy+lightSize, lightSize, lightSize);
		
		gcImage.setBackground(colorGreen);
		gcImage.fillRectangle(posx, posy+lightSize*2, lightSize, lightSize);
	}

	/**
	 * 
	 */
	@Override
	public void calculate(long timeDiff) {
		runningTime += timeDiff;
		if (runningTime >= state.getTime()) {
			switch (state) {
				case RED : 	nextState = TrafficLightsStates.RED_ORANGE; 
							break;
							
				case RED_ORANGE : 
							nextState = TrafficLightsStates.GREEN; 
							break;
							
				case GREEN : 			
							nextState = TrafficLightsStates.ORANGE; 
							break;
							
				case ORANGE : 			
							nextState = TrafficLightsStates.RED; 
							break;	
			}
			state = nextState; 
			runningTime = 0;
		}
		
	}
	
	
	/**
	 * 
	 */
	@Override
	public boolean isVisible(int x, int y, int width, int height) {
		return true;
	}

}
