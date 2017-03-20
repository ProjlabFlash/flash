package main;

import java.util.ArrayList;

@SuppressWarnings("unused")
public class BuildingSpot extends Railway {

	private Railway OldThisNeighbour;
	private Railway OldThatNeighbour;
	
	/**
	 * Konstruktor. Beállítja a paraméterül kapott Railway-t a szomszédjának,
	 * és beállítja magát annak a szomszédjaként.
	 * @param previousRailway A kapott railway
	 */
	public BuildingSpot(Railway previousRailway) {
		super(previousRailway);
	}
	
	/**
	 * A paraméterül kapott Railway-eket beállítja a saját szomszédjának.
	 * @param thisNewNeighbour Egyik szomszéd
	 * @param thatNewNeighbour Másik szomszéd
	 */
	public void setNewNeighbours(ArrayList<Railway> thisNewNeighbour, ArrayList<Railway> thatNewNeighbour) {
		//logger enter
		ArrayList<Object> paramlist=new ArrayList<Object>();
		paramlist.add(thisNewNeighbour);
		paramlist.add(thatNewNeighbour);
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
