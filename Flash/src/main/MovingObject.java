package main;

import java.util.ArrayList;

public abstract class MovingObject extends MetaData {
	
	protected Railway CurrentRailwaySegment;
	protected Railway PreviousRailwaySegment;
	protected Cart Pulls;
	
	public MovingObject(Railway railwaySegment, Railway previousRailwaySegment, Cart nextCart) {
		railwaySegment.setOnMe(this);
	}
	
	public void step(Railway toHere) {
		//logger enter
		ArrayList<Object> paramlist=new ArrayList<Object>();
		paramlist.add(toHere);
		Application.logger.enter(this, "step", paramlist);
		
		
		CurrentRailwaySegment.setOnMe(null);
		toHere.setOnMe(this);
		if(Pulls != null) Pulls.step(CurrentRailwaySegment);
		
		
		//logger exit
		Application.logger.exit("");
	}
	
	public void crash() {
		//logger enter
		Application.logger.enter(this, "crash", null);
		
		Application.lose();
		
		//logger exit
		Application.logger.exit("");
	}
}
