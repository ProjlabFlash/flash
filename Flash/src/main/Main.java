package main;

public class Main {
	public static void main(String[] args) {
		//Ez egy komment, ami csak kieg�sz�t�s, ez�rt nem jelent konfliktust
		
		String[] names = {"Tim", "Koni", "Lajos, Doma"}; //Doma bel�pett a csapatba, ez nem jelent konfliktust :)
		
		for(String name : names) { //ez majd konfliktust jelent.
			System.out.println(name + ", Welcome to Flash!");
		}
	}
}
