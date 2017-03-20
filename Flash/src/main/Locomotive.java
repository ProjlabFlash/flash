package main;

import java.util.ArrayList;

@SuppressWarnings("unused")
public class Locomotive extends MovingObject {
	
	private int Speed;
	
	public Locomotive(Railway railwaySegment, Railway previousRailwaySegment, Cart nextCart, int speed) {
		super(railwaySegment, previousRailwaySegment, nextCart);
		this.Speed = speed;
	}
	
	public void move() {
		//logger enter
		Application.logger.enter(this,"move",null);
		
		Railway nextRailwaySegment = CurrentRailwaySegment.next(PreviousRailwaySegment);
		step(nextRailwaySegment);
		
		//logger exit
		Application.logger.exit("");
	}
	
	public void ArrivedAtStation(Station station) {
		//logger enter
		ArrayList<Object> paramlist=new ArrayList<Object>();
		paramlist.add(station);
		Application.logger.enter(this,"ArrivedAtStation", paramlist);
		
		if(Pulls.colorCheck(station) == true) {
			Application.win();
		}
		
		//logger exit
		Application.logger.exit("");
	}
}
