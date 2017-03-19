package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Application {

	
	static private List<MenuItem> items = new ArrayList<MenuItem>();
	
	public static void main(String[] args) {
		
		System.out.println("hi");
		
		while(true) {
			
			BufferedReader input = new BufferedReader (new InputStreamReader(System.in));
			
			//Menüpontok létrehozása
			
			
			while(true) {
				
				try {
					
					System.out.println("");
					
					int controlNumber = Integer.parseInt(input.readLine());
					System.out.println(controlNumber);
					
					if (controlNumber == 0)
						break;
					
					for (MenuItem item: items) {
						if (item.id == controlNumber) {
							
							System.out.println("\n------------------\n");
							item.run();
							break;
						}
					}
					
					
				} catch (NumberFormatException e) {
					
					System.out.println("A megadott érték nem egy egész szám");
				} catch (IOException e) {
					
					e.printStackTrace();
				}
			}
		}
		
		
	}

	
	
	protected abstract class MenuItem {
		
		public final int id;
		public final String name;
		
		MenuItem(int id, String name) {
			this.id = id;
			this.name = name;
		}
		
		protected abstract void run();
		
	}
	
	public static void win() {
		
	}
	
	public static void lose() {
		
	}
}
