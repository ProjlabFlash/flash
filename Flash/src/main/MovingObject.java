package main;

@SuppressWarnings("unused")
public abstract class MovingObject {
	
	private Railway CurrentRailwaySegment;
	private Railway PreviousRailwaySegment;
	private Cart Pulls;
	
	public MovingObject(Railway railwaySegment, Railway previousRailwaySegment, Cart nextCart) {
		
	}
	
	public void step(Railway toHere) {
		CurrentRailwaySegment.setOnMe(null);
	}
	
	public void crash() {
		
	}
}
