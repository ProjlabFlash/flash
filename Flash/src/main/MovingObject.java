package main;

public abstract class MovingObject extends MetaData {
	
	protected Railway CurrentRailwaySegment;
	protected Railway PreviousRailwaySegment;
	protected Cart Pulls;
	
	public MovingObject(Railway railwaySegment, Railway previousRailwaySegment, Cart nextCart) {
		railwaySegment.setOnMe(this);
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
