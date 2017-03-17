package main;

public class Cart extends MovingObject {

	private Color color;
	private boolean Passengers;
	
	public Cart(Railway railwaySegment, Railway previousRailwaySegment, Cart nextCart, Color color, boolean passengers) {
		super(railwaySegment, previousRailwaySegment, nextCart);
	}
	
	public void leaveTheTrain(Station station) {
		
	}
	
	public boolean colorCheck(Station station) {
		//atmenetileg
		return true;
	}
}
