package main;

import java.util.ArrayList;

public class Tunnel extends MetaData {
	
	private BuildingSpot bs1, bs2;

	public Tunnel() {
		
	}
	
	public void build(BuildingSpot buildingSpot1, BuildingSpot buildingSpot2) {
		//logger enter
		ArrayList<Object> paramlist=new ArrayList<Object>();
		paramlist.add(buildingSpot1);
		paramlist.add(buildingSpot2);
		Application.logger.enter(this,"build", paramlist);
				
		bs1 = buildingSpot1; bs2 = buildingSpot2;
		
		ArrayList<Railway> bs1thisNeighbour = buildingSpot1.getThisNeighbour();
		ArrayList<Railway> bs1thatNeighbour = buildingSpot1.getThatNeighbour();
		ArrayList<Railway> bs2thisNeighbour = buildingSpot2.getThisNeighbour();
		ArrayList<Railway> bs2thatNeighbour = buildingSpot2.getThatNeighbour();
		
		bs1.setNewNeighbours(bs2thisNeighbour, bs2thatNeighbour);
		bs2.setNewNeighbours(bs1thisNeighbour, bs1thatNeighbour);
		
		//logger exit
		Application.logger.exit("");
	}
	
	public void destroy() {
		//logger enter
		Application.logger.enter(this,"destroy",null);
				
		bs1.setNewNeighbours(null, null);
		bs2.setNewNeighbours(null, null);
		
		//logger exit
		Application.logger.exit("");
	}
}
