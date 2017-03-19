package main;

@SuppressWarnings("unused")
public class Cart extends MovingObject {

	private Color color;
	private boolean Passengers;
	
	public Cart(Railway railwaySegment, Railway previousRailwaySegment, Cart nextCart, Color color, boolean passengers) {
		super(railwaySegment, previousRailwaySegment, nextCart);
	}
	
	private void leaveTheTrain(Station station) {
		Passengers = false;
	}
	
	public boolean colorCheck(Station station) {
		if(station.getColor().equals(color)) leaveTheTrain(station);
		if(Passengers == false && Pulls != null) {
			return Pulls.colorCheck(station);
		}
		if(Passengers == false && Pulls == null) {
			return true;
		}
		
		return false;
	}
}
