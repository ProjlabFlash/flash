package main;

import java.util.ArrayList;

public class Railway {

	protected ArrayList<Railway> ThisNeighbour = new ArrayList<>();
	protected ArrayList<Railway> ThatNeighbour = new ArrayList<>();
	protected MovingObject OnMe;
	private Station station;
	
	public Railway(Railway previousRailway) {
		
	}
	
	public Railway next(Railway previousRailway) {
		//atmenetileg
		return null;
	}
	
	public void setOnMe(MovingObject OnMe) {
		
	}
	
	public ArrayList<Railway> getThisNeighbour() {
		//atmenetileg
		return null;
	}
	
	public ArrayList<Railway> getThatNeighbour() {
		//atmenetileg
		return null;
	}
	
	public void insertNeighbour(Railway newNeighbour) {
		
	}
}
