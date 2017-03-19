package main;

import java.util.ArrayList;

@SuppressWarnings("unused")
public class Railway extends MetaData {

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
		this.OnMe = OnMe; 
	}
	
	public ArrayList<Railway> getThisNeighbour() {
		return ThisNeighbour;
	}
	
	public ArrayList<Railway> getThatNeighbour() {
		return ThatNeighbour;
	}
	
	public void insertNeighbour(Railway newNeighbour) {
		//atmenetileg ures
	}
}
