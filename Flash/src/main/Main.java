package main;

public class Main {
	public static void main(String[] args) {
		//Ez egy komment, ami csak kiegészítés, ezért nem jelent konfliktust
		System.out.println("Hello Flash!");
		
		String[] names = {"Tim", "Koni", "Lajos, Doma"}; //Doma belépett a csapatba, ez nem jelent konfliktust :)
		
		for(String name : names) { //ez majd konfliktust jelent, mert töröltünk.
			System.out.println(name + ", Welcome to Flash!");
		}
	}
}