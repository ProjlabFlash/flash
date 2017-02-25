package main;

public class Main {
	public static void main(String[] args) {
		System.out.println("Hello Flash!");
		
		String[] names = {"Tim", "Koni", "Lajos"};
		
		for(String name : names) { //ez majd konfliktust jelent, mert töröltünk.
			System.out.println(name + ", Welcome to Flash!");
		}
	}
}