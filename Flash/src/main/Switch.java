package main;

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
	}
	
	/**
	 * V�lt a param�ter�l kapott s�nre.
	 * @param nextStanding A kapott s�n.
	 */
	public void switchTo(Railway nextStanding) {
		
	}
}
