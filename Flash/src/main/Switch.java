package main;

@SuppressWarnings("unused")
public class Switch extends Railway {

	private Railway CurrentStanding;
	
	/**
	 * Konstruktor. A paraméterül kapott kimenetet állítja be a váltónak.
	 * Null-esetén a lehetõség közül az elsõt állítja be.
	 * @param defaultStanding A váltó kezdeti állása.
	 * @param previousRailway Elõzõ sín. A konstruáláshoz kell.
	 */
	public Switch(Railway defaultStanding, Railway previousRailway) {
		super(previousRailway);
	}
	
	/**
	 * Vált a paraméterül kapott sínre.
	 * @param nextStanding A kapott sín.
	 */
	public void switchTo(Railway nextStanding) {
		
	}
}
