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
		
	}
}
