package cz.lechat;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;


public class Road {
	
	private double scale = 10; 
	private List<Car> cars = new ArrayList<>();
	
	/**
	 * 
	 * @param carsCount
	 */
	public Road(int carsCount) {
		double startPos = -30;
		for (int i = 0; i < carsCount; i++) {
			Car anotherCar = cars.isEmpty() ? null : cars.get(i-1);
			cars.add(new Car(i+1, startPos+(i*(Car.LENGTH + 2.5)), anotherCar));
		}
	}
	

	/**
	 * 
	 */
	public void recalc() {
		cars.forEach(Car::recalc);
	}
	
	
	/**
	 * 
	 * @param e
	 * @param running
	 */
	public void redrawRoad(final PaintEvent e, boolean running) {

		e.gc.setForeground(e.display.getSystemColor(SWT.COLOR_BLACK));
		e.gc.setBackground(e.display.getSystemColor(SWT.COLOR_YELLOW));
		
		for (Car car : cars) {
			
			int fixedTopPosition = 150;
			int carWidth = (int)(Car.WIDTH * scale);
			int carLength = (int)(Car.LENGTH * scale);
			int carFrontPosition = (int)(car.getPositionX() * scale);
			int carBackPosition = carFrontPosition - carLength;
			
			e.gc.fillRectangle(carBackPosition, fixedTopPosition, carLength, carWidth);
			e.gc.drawRectangle(carBackPosition, fixedTopPosition, carLength, carWidth);
			e.gc.drawText(car.getId() + " " + (int)car.getSpeed() + "km/h", carBackPosition+2 , fixedTopPosition+2 );
		}
		
	}
	
}
