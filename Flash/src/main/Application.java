package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Application {

	
	static private List<MenuItem> items = new ArrayList<MenuItem>();
	
	static public Logger logger = new Logger();
	
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
		
		protected abstract void run();
		
	}
	
	
	protected class VonatLeptetes extends MenuItem {
		
		VonatLeptetes() {
			super(1, "A vonat léptetése a sínen");
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
			super(1, "Két mozdony ütközése");
		}
		
		@Override
		protected void run() {
			Railway prevForL1 =new Railway(null);
			Railway underL1 = new Railway(prevForL1);
			Railway forCollision = new Railway(underL1);
			prevForL1.insertNeighbour(underL1);
			underL1.insertNeighbour(forCollision);
			Locomotive L1 = new Locomotive(underL1,prevForL1, null, 10);
			Locomotive L2 = new Locomotive(forCollision, null, null, 0);
		}
	}
	
	protected class LeszallasMozdonyKocsi extends MenuItem {
		
		LeszallasMozdonyKocsi() {
			super(1, "Leszállás");
		}
		
		@Override
		protected void run() {
		Railway rwayAtStation = new Railway(null);
		Railway rwayBeforeStation = new Railway(rwayAtStation);
		rwayBeforeStation.insertNeighbour(rwayAtStation);
		Cart C = new Cart(rwayBeforeStation, null,null,Color.KEK,true);
		Locomotive L = new Locomotive(rwayAtStation,rwayBeforeStation, C, 10);
		System.out.println("Leszállhatnak az utasok? (y/n)");
		Scanner scan = new Scanner(System.in);
		Character leszall = scan.next().charAt(0);
		if(leszall.equals('y'))
		{
			Station S = new Station(rwayAtStation, Color.KEK);
		}
		else if (leszall.equals('n'))
		{
			Station S = new Station(rwayAtStation, Color.PIROS);
		}
		}
	}
	
	protected class LeszallasMozdonyUresJo extends MenuItem {
		
		LeszallasMozdonyUresJo() {
			super(1, "Leszállás egy üres vagon mögül.");
		}
		
		@Override
		protected void run() {
			Railway rwayAtStation = new Railway(null);
			Railway rwayBeforeStation = new Railway(rwayAtStation);
			Railway rway2BeoreStation = new Railway(rwayBeforeStation);
			rwayAtStation.insertNeighbour(rwayBeforeStation);
			rwayBeforeStation.insertNeighbour(rway2BeoreStation);
			Cart ReadyforLeaveCart = new Cart(rway2BeoreStation,null, null, Color.KEK,true);
			Cart EmptyCart = new Cart(rwayBeforeStation, rway2BeoreStation, ReadyforLeaveCart, Color.KEK, false);
			Locomotive L = new Locomotive(rwayAtStation, rwayBeforeStation, EmptyCart, 10);
			Station S = new Station(rwayAtStation, Color.KEK);
			rwayAtStation.setStation(S);
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
