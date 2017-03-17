package main;

public class Switch extends Railway {

	private Railway CurrentStanding;
	
	public Switch(Railway defaultStanding, Railway previousRailway) {
		super(previousRailway);
	}
	
	public void switchTo(Railway nextStanding) {
		
	}
}
