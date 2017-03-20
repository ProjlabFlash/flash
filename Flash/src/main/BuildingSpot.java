package main;

import java.util.ArrayList;

@SuppressWarnings("unused")
public class BuildingSpot extends Railway {

	private Railway OldThisNeighbour;
	private Railway OldThatNeighbour;
	
	public BuildingSpot(Railway previousRailway) {
		super(previousRailway);
	}
	
	public void setNewNeighbours(ArrayList<Railway> thisNewNeighbour, ArrayList<Railway> thatNewNeighbour) {
		//logger enter
		ArrayList<Object> paramlist=new ArrayList<Object>();
		Object[] items1 = thisNewNeighbour.toArray();
		Object[] items2 = thatNewNeighbour.toArray();
		
		for(Object item: items1)
			paramlist.add(item);
		for(Object item: items2)
			paramlist.add(item);
		
		Application.logger.enter(this,"setNewNeighbours", paramlist);
		
		
		if (thisNewNeighbour != null) {

			OldThisNeighbour = ThisNeighbour.get(0);
			ThisNeighbour = thisNewNeighbour;
		} else {
			ThisNeighbour = new ArrayList<Railway>();
			ThisNeighbour.add(OldThisNeighbour);
		}
		
		if (thisNewNeighbour != null) {
			
			OldThatNeighbour = ThatNeighbour.get(0);
			this.ThatNeighbour = thatNewNeighbour;
		} else {
			
			ThatNeighbour = new ArrayList<Railway>();
			ThisNeighbour.add(OldThisNeighbour);
		}
		
		//logger exit
		Application.logger.exit("");
	}
}
