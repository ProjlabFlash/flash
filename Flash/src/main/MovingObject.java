package main;

@SuppressWarnings("unused")
public abstract class MovingObject {
	
	private Railway CurrentRailwaySegment;
	private Railway PreviousRailwaySegment;
	protected Cart Pulls;
	
	public MovingObject(Railway railwaySegment, Railway previousRailwaySegment, Cart nextCart) {
		
	}
	
	public void step(Railway toHere) {
		CurrentRailwaySegment.setOnMe(null);
		toHere.setOnMe(this);
		if(Pulls != null) Pulls.step(CurrentRailwaySegment);
	}
	
	public void crash() {
		Application.lose();
	}
}
