package main;

@SuppressWarnings("unused")
public class BuildingSpot extends Railway {

	private Railway OldThisNeighbour;
	private Railway OldThatNeighbour;
	
	public BuildingSpot(Railway previousRailway) {
		super(previousRailway);
	}
	
	public void setNewNeighbours(Railway thisNewNeighbour, Railway thatNewNeighbour) {
		
	}
}
