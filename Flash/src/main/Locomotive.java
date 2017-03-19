package main;

@SuppressWarnings("unused")
public class Locomotive extends MovingObject {
	
	private int Speed;
	
	public Locomotive(Railway railwaySegment, Railway previousRailwaySegment, Cart nextCart, int speed) {
		super(railwaySegment, previousRailwaySegment, nextCart);
	}
	
	public void move() {
		
	}
	
	public void ArrivedAtStation(Station station) {
		
	}
}
