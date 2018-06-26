package cz.lechat;

/**
 * 
 * @author lechat
 *
 */
public class Car {

	private int id;
	private double positionX;
	public static double LENGTH = 3.75;
	public static double WIDTH = 1.85;
	private double speed = 25.0 + Math.random()*10  ; // km/h
	
	private double acceleration = 0;
	
	private long lastUpdateTime;

	private Car anotherCar;
	
	/**
	 * 
	 * @param id
	 */
	public Car(int id, double positionX, Car anotherCar) {
		super();
		this.id = id;
		this.positionX = positionX;
		lastUpdateTime = System.currentTimeMillis();
		this.anotherCar = anotherCar;
	}
	
	public void recalc() {
		long timeDiff = System.currentTimeMillis() - lastUpdateTime;
		
		// aktualizuj pozici podle rychlosti
		double distDiff = speed * ((double)timeDiff / 1000d / 60d / 60d); // km/h
		positionX += distDiff * 1000; //* 1000 = zpet na metry
		lastUpdateTime = System.currentTimeMillis();
		
		// co dela auto predemnou ?
		if (anotherCar != null) {
			if (anotherCar.getPositionX() >= getPositionX()) {
				this.speed +=0.5;
			}
		}
		
		
	}
	
	public double getPositionX() {
		return positionX;
	}

	public int getId() {
		return id;
	}

	public double getSpeed() {
		return speed;
	}
	
	
	
	
	
}
