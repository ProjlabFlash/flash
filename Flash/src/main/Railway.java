package main;

import java.util.ArrayList;

@SuppressWarnings("unused")
public class Railway {

	protected ArrayList<Railway> ThisNeighbour = new ArrayList<>();
	protected ArrayList<Railway> ThatNeighbour = new ArrayList<>();
	protected MovingObject OnMe;
	private Station station;
	
	/**
	 * Konstruktor. Beállítja a paraméterül kapott Railway-t a szomszédjának,
	 * és beállítja magát annak a szomszédjaként.
	 * @param previousRailway A kapott railway
	 */
	public Railway(Railway previousRailway) {
		
		if (previousRailway != null)
			ThisNeighbour.add(previousRailway);
	}
	
	/**
	 * Visszaadja azt a sínt, amire a mozdony továbbléphet
	 * a paraméterül kapott elõzõ sín alapján.
	 * @param previousRailway
	 * @return A sín, amire a mozdony következõnek léphet.
	 */
	public Railway next(Railway previousRailway) {
		//logger enter
		ArrayList<Object> paramlist=new ArrayList<Object>();
		paramlist.add(previousRailway);
		Application.logger.enter(this, "next", paramlist);
		
		if (previousRailway == null) {
			
			if (ThisNeighbour.size() != 0)
				{
					//logger exit
					Application.logger.exit(ThisNeighbour.get(0).toString());
					return ThisNeighbour.get(0);
				}
			if (ThatNeighbour.size() != 0)
				{
					//logger exit
					Application.logger.exit(ThatNeighbour.get(0).toString());
					return ThatNeighbour.get(0);
				}
			//logger exit
			Application.logger.exit("");
			return null;
		}
		
		if (ThisNeighbour.contains(previousRailway)) {
			
			if (ThatNeighbour.size() == 0)
				{
					//logger exit
					Application.logger.exit("");
					return null;
				}
			//logger exit
			Application.logger.exit(ThatNeighbour.get(0).toString());
			return ThatNeighbour.get(0);
		}
		
		if (ThatNeighbour.contains(previousRailway)) {
			
			if (ThisNeighbour.size() == 0)
				{
					//logger exit
					Application.logger.exit("");
					return null;
				}
			//logger exit
			Application.logger.exit(ThisNeighbour.get(0).toString());
			return ThisNeighbour.get(0);
		}
		
		//logger exit
		Application.logger.exit("");
		
		return null;
	}
	
	/**
	 * A paraméter szerint beállítja, hogy ki melyik vonatelem van éppen a sínen.
	 * @param OnMe A sínre beállítandó vonatelem.
	 */
	public void setOnMe(MovingObject OnMe) {
		//logger enter
		ArrayList<Object> paramlist=new ArrayList<Object>();
		paramlist.add(OnMe);
		Application.logger.enter(this, "setOnMe", paramlist);
		
		this.OnMe = OnMe;
		
		//logger exit
		Application.logger.exit("");
	}
	
	/**
	 * Getter az egyik szomszédra
	 * @return Az egyik szomszéd
	 */
	public ArrayList<Railway> getThisNeighbour() {
		//logger enter
		Application.logger.enter(this, "getThisNeighbour", null);
		
		//logger exit
		Application.logger.exit(ThisNeighbour.toString());
		
		return ThisNeighbour;
	}
	
	/**
	 * Getter a másik szomszédra
	 * @return A másik szomszéd
	 */
	public ArrayList<Railway> getThatNeighbour() {
		//logger enter
		Application.logger.enter(this, "getThatNeighbour", null);
				
		//logger exit
		Application.logger.exit(ThatNeighbour.toString());
		
		return ThatNeighbour;
	}
	
	/**
	 * Elhelyezi a szomszédjai közt a paraméterül kapott Railway-t.
	 * @param newNeighbour A paraméterül kapott Railway
	 */
	public void insertNeighbour(Railway newNeighbour) {
		//logger enter
		Application.logger.enter(this, "getThatNeighbour", null);
						
		
		ThatNeighbour.add(newNeighbour);
		//logger exit
		Application.logger.exit("");
	}
	
	/**
	 * Beállítja a sínhez tartozó megállót.
	 * @param station A sínhez tartozó megálló.
	 */
	public void setStation(Station station) {
		//logger enter
		ArrayList<Object> paramlist=new ArrayList<Object>();
		paramlist.add(station);
		Application.logger.enter(this, "next", paramlist);
				
		this.station = station;
		
		//logger exit
		Application.logger.exit("");
	}
}
