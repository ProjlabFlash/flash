package main;

import java.util.ArrayList;

public class Cart extends MovingObject {

	private Color color;
	private boolean Passengers;
	
	public Cart(Railway railwaySegment, Railway previousRailwaySegment, Cart nextCart, Color color, boolean passengers) {
		super(railwaySegment, previousRailwaySegment, nextCart);
	}
	
	private void leaveTheTrain(Station station) {
		//logger enter
		ArrayList<Object> paramlist=new ArrayList<Object>();
		paramlist.add(station);
		Application.logger.enter(this,"leaveTheTrain",paramlist);
		
		Passengers = false;
		
		//logger exit
		Application.logger.exit("");
	}
	
	public boolean colorCheck(Station station) {
		//logger enter
		ArrayList<Object> paramlist=new ArrayList<Object>();
		paramlist.add(station);
		Application.logger.enter(this,"colorCheck",paramlist);	
				
		if(station.getColor().equals(color)) leaveTheTrain(station);
		if(Passengers == false && Pulls != null) {
			
			//logger exit
			Application.logger.exit("???");
			
			return Pulls.colorCheck(station);
		}
		if(Passengers == false && Pulls == null) {
			
			//logger exit
			Application.logger.exit("true");
			return true;
		}
		
		//logger exit
		Application.logger.exit("false");
		return false;
	}
}
