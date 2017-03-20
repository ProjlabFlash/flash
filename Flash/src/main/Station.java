package main;

public class Station extends MetaData {
	
	private Color color;
	
	/**
	 * Konstruktor. Beállítja az állomás színét, és hozzárendeli
	 * egy sínhez, aminél a leszállás történik.
	 * @param railway A sín, amihez hozzárendelték
	 * @param color Az állomás színe
	 */
	public Station(Railway railway, Color color) {
		railway.setStation(this);
	}
	
	/**
	 * Visszaadja az állomás színét.
	 * @return
	 */
	public Color getColor() {
		//logger enter
		Application.logger.enter(this,"getColor",null);
		
		//logger exit
		Application.logger.exit("");
		
		return this.color;
	}
}
