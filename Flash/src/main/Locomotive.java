package main;

@SuppressWarnings("unused")
public class Locomotive extends MovingObject {
	
	private int Speed;
	
	public Locomotive(Railway railwaySegment, Railway previousRailwaySegment, Cart nextCart, int speed) {
		super(railwaySegment, previousRailwaySegment, nextCart);
		this.Speed = speed;
	}
	
	public void move() {
		Railway nextRailwaySegment = CurrentRailwaySegment.next(PreviousRailwaySegment);
		step(nextRailwaySegment);
	}
	
	public void ArrivedAtStation(Station station) {
		if(Pulls.colorCheck(station) == true) {
			Application.win();
		}
	}
}
