package main;

import java.util.ArrayList;

@SuppressWarnings("unused")
public class Railway {

	protected ArrayList<Railway> ThisNeighbour = new ArrayList<>();
	protected ArrayList<Railway> ThatNeighbour = new ArrayList<>();
	protected MovingObject OnMe;
	private Station station;
	
	/**
	 * Konstruktor. Be�ll�tja a param�ter�l kapott Railway-t a szomsz�dj�nak,
	 * �s be�ll�tja mag�t annak a szomsz�djak�nt.
	 * @param previousRailway A kapott railway
	 */
	public Railway(Railway previousRailway) {
		
		if (previousRailway != null)
			ThisNeighbour.add(previousRailway);
	}
	
	/**
	 * Visszaadja azt a s�nt, amire a mozdony tov�bbl�phet
	 * a param�ter�l kapott el�z� s�n alapj�n.
	 * @param previousRailway
	 * @return A s�n, amire a mozdony k�vetkez�nek l�phet.
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
					Application.logger.exit("???");
					return ThisNeighbour.get(0);
				}
			if (ThatNeighbour.size() != 0)
				{
					//logger exit
					Application.logger.exit("???");
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
			Application.logger.exit("???");
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
			Application.logger.exit("???");
			return ThisNeighbour.get(0);
		}
		
		//logger exit
		Application.logger.exit("");
		
		return null;
	}
	
	public void setOnMe(MovingObject OnMe) {
		//logger enter
		ArrayList<Object> paramlist=new ArrayList<Object>();
		paramlist.add(OnMe);
		Application.logger.enter(this, "setOnMe", paramlist);
		
		this.OnMe = OnMe;
		
		//logger exit
		Application.logger.exit("");
	}
	
	public ArrayList<Railway> getThisNeighbour() {
		//logger enter
		Application.logger.enter(this, "getThisNeighbour", null);
		
		//logger exit
		Application.logger.exit(ThisNeighbour.toString());
		
		return ThisNeighbour;
	}
	
	public ArrayList<Railway> getThatNeighbour() {
		//logger enter
		Application.logger.enter(this, "getThatNeighbour", null);
				
		//logger exit
		Application.logger.exit(ThatNeighbour.toString());
		
		return ThatNeighbour;
	}
	
	public void insertNeighbour(Railway newNeighbour) {
		//logger enter
		Application.logger.enter(this, "getThatNeighbour", null);
						
		
		ThatNeighbour.add(newNeighbour);
		//logger exit
		Application.logger.exit("");
	}
	
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
