package main;

import java.util.ArrayList;

@SuppressWarnings("unused")
public class Switch extends Railway {

	private Railway CurrentStanding;
	
	/**
	 * Konstruktor. A param�ter�l kapott kimenetet �ll�tja be a v�lt�nak.
	 * Null-eset�n a lehet�s�g k�z�l az els�t �ll�tja be.
	 * @param defaultStanding A v�lt� kezdeti �ll�sa.
	 * @param previousRailway El�z� s�n. A konstru�l�shoz kell.
	 */
	public Switch(Railway defaultStanding, Railway previousRailway) {
		super(previousRailway);
		CurrentStanding = defaultStanding;
		ThisNeighbour.add(previousRailway);
	}
	
	/**
	 * V�lt a param�ter�l kapott s�nre.
	 * @param nextStanding A kapott s�n.
	 */
	public void switchTo(Railway nextStanding) {
		//logger enter
		ArrayList<Object> paramlist=new ArrayList<Object>();
		paramlist.add(nextStanding);
		Application.logger.enter(this,"switchTo", paramlist);
				
		if (ThisNeighbour.contains(nextStanding) || ThatNeighbour.contains(nextStanding))
			CurrentStanding = nextStanding;
		
		//logger exit
		Application.logger.exit("");
	}
	
	public Railway next(Railway previousRailway) {
		//logger enter
		ArrayList<Object> paramlist=new ArrayList<Object>();
		paramlist.add(previousRailway);
		Application.logger.enter(this, "next", paramlist);
		
		Railway result = null;
		
		if (ThatNeighbour.contains(previousRailway))
			result = ThisNeighbour.get(0);
		else if (ThisNeighbour.contains(previousRailway))
			result = CurrentStanding;
		if (result != null)
			Application.logger.exit(result.toString());
		else 
			Application.logger.exit(null);
		return result;
	}
}
