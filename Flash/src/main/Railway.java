package main;

import java.util.ArrayList;

@SuppressWarnings("unused")
public class Railway extends MetaData {

	protected ArrayList<Railway> ThisNeighbour = new ArrayList<>();
	protected ArrayList<Railway> ThatNeighbour = new ArrayList<>();
	protected MovingObject OnMe;
	private Station station;
	
	public Railway(Railway previousRailway) {
		
		if (previousRailway != null)
			ThisNeighbour.add(previousRailway);
	}
	
	public Railway next(Railway previousRailway) {
		if (previousRailway == null) {
			
			if (ThisNeighbour.size() != 0) return ThisNeighbour.get(0);
			if (ThatNeighbour.size() != 0) return ThatNeighbour.get(0);
			return null;
		}
		
		if (ThisNeighbour.contains(previousRailway)) {
			
			if (ThatNeighbour.size() == 0) return null;
			return ThatNeighbour.get(0);
		}
		
		if (ThatNeighbour.contains(previousRailway)) {
			
			if (ThisNeighbour.size() == 0) return null;
			return ThisNeighbour.get(0);
		}
		return null;
	}
	
	public void setOnMe(MovingObject OnMe) {
		this.OnMe = OnMe;
			
	}
	
	public ArrayList<Railway> getThisNeighbour() {
		return ThisNeighbour;
	}
	
	public ArrayList<Railway> getThatNeighbour() {
		return ThatNeighbour;
	}
	
	public void insertNeighbour(Railway newNeighbour) {
		ThatNeighbour.add(newNeighbour);
	}
}
