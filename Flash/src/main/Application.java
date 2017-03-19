package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Application {

	
	static private List<MenuItem> items = new ArrayList<MenuItem>();
	
	static private Logger logger = new Logger();
	
	static BufferedReader input = new BufferedReader (new InputStreamReader(System.in));
	
	public static void main(String[] args) {
		
		System.out.println("hi");
		
		while(true) {
			

			
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

	
	
	public static void win() {
		
	}
	
	public static void lose() {
		
	}
	
	
	protected abstract class MenuItem {
		
		public final int id;
		public final String name;
		
		MenuItem(int id, String name) {
			this.id = id;
			this.name = name;
		}
		
		protected abstract void run() throws IOException;
		
	}
	
	
	protected class VonatLeptetes extends MenuItem {
		
		VonatLeptetes() {
			super(1, "A vonat leptetese a sinen");
		}
		
		@Override
		protected void run() {
			
			logger.setInit(true);
			
			Railway underC2 = new Railway(null);
			Railway underC1 = new Railway(underC2);
			Railway underL = new Railway(underC1);
			Railway nextForL = new Railway(underL);
			Cart C2 = new Cart(underC2, null, null, Color.KEK, true);
			Cart C1 = new Cart(underC1, underC2, C2, Color.SARGA, true);
			Locomotive L = new Locomotive(underL, underC1, C1, 20);
			
			underC2.insertNeighbour(underC1);
			underC1.insertNeighbour(underL);
			underL.insertNeighbour(nextForL);
			
			logger.setInit(false);
			
			L.move();
		}
	}
	
	protected class Utkozes extends MenuItem {
		
		Utkozes() {
			super(2, "2 vonat utkozese");
		}
		
		@Override
		protected void run() {
			
			logger.setInit(true);
			
			Railway prevForL1 = new Railway(null);
			Railway underL1 = new Railway(prevForL1);
			Railway forCollision = new Railway(underL1);
			Locomotive L1 = new Locomotive(underL1, prevForL1, null, 20);
			Locomotive L2 = new Locomotive(forCollision, null, null, 2);
			
			prevForL1.insertNeighbour(underL1);
			underL1.insertNeighbour(forCollision);
			
			logger.setInit(false);
			
			L1.move();
		}
	}
	
	protected class LeszallasMozdonyKocsi extends MenuItem {
		
		LeszallasMozdonyKocsi() {
			super(3, "Leszallas egy tetszoleges színu allomason ugy, hogy csak egy kocsit huz a vonat ami kek");
		}
		
		@Override
		protected void run(){
			
			logger.setInit(true);
			
			Railway rWayAtStation = new Railway(null);
			Railway rWayBeforeStation = new Railway(rWayAtStation);
			Cart C = new Cart(rWayBeforeStation, null, null, Color.KEK, true);
			Locomotive L = new Locomotive(rWayAtStation, rWayBeforeStation, C, 20);
			
			rWayAtStation.insertNeighbour(rWayBeforeStation);
			
			System.out.println("Milyen szinu legyen az allomas?");
			System.out.println("1: Kek");
			System.out.println("2: Piros");
			
			int control = 0;
			
			while (true) {
				
				
				try {
					control = Integer.parseInt(input.readLine());
				} catch (NumberFormatException e) {
					System.out.println("Helytelen bemenet, próbálja újra");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				if (control == 1 || control == 2) break;
				System.out.println("Helytelen bemenet, próbálja újra");
			}
			
			Station S;
			if (control == 1) {
				S = new Station(rWayAtStation, Color.KEK);
			} else {
				S = new Station(rWayBeforeStation, Color.PIROS);
			}
			
			logger.setInit(false);
			
			L.ArrivedAtStation(S);
		}
	}
	
	protected class LeszallasMozdonyUresJo extends MenuItem {
		
		LeszallasMozdonyUresJo() {
			super(1, "");
		}
		
		@Override
		protected void run() {
			
		}
	}
	
	protected class LeszallasMozdonyUresRossz extends MenuItem {
		
		LeszallasMozdonyUresRossz() {
			super(1, "");
		}
		
		@Override
		protected void run() {
			
		}
	}
	
	protected class LeszallasJoJoRossz extends MenuItem {
		
		LeszallasJoJoRossz() {
			super(1, "");
		}
		
		@Override
		protected void run() {
			
		}
	}
	
	protected class Valtas extends MenuItem {
		
		Valtas() {
			super(1, "");
		}
		
		@Override
		protected void run() {
			
		}
	}
	
	protected class AlagutEpites extends MenuItem {
		
		AlagutEpites() {
			super(1, "");
		}
		
		@Override
		protected void run() {
			
		}
	}
	
	protected class AlagutRombolas extends MenuItem {
		
		AlagutRombolas() {
			super(1, "");
		}
		
		@Override
		protected void run() {
			
		}
	}
	
	protected class ValtasVonattal extends MenuItem {
		
		ValtasVonattal() {
			super(1, "");
		}
		
		@Override
		protected void run() {
			
		}
	}

}
