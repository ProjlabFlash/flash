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
	
	/**
	 * Az egész program belépési pontja
	 * @param args pl.: parancssori argumentumok
	 */
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
					
					ArrayList<String> teszt = new ArrayList();
					teszt.add("salyt");
					System.out.println(teszt.toString());
					
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

	
	/**
	 * Gyõzelem esetén (azaz, ha minden kocsi kiürült) a következõ pályát tölti be.
	 */
	public static void win() {
		
	}
	
	/**
	 * Vereség esetén a játékmenet leállítását kezeli.
	 */
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
			/** 
			 * 	Beállítja a Logger-t inicializáló módba
			 *	Nem ír ki ilyenkor a Logger semmit a kimenetre
			 */
			logger.setInit(true);
			/**
			 * Inicializálja az VonatLeptetes szekvenciáját
			 */
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
			/** 
			 * 	Beállítja a Logger-t tesztelései módba
			 *	A Logger ilyenkor ír standard outputra
			 */
			logger.setInit(false);
			/**
			 * Elindítja az VonatLeptetes szekvenciáját
			 */
			L.move();
		}
	}
	
	protected class Utkozes extends MenuItem {
		
		Utkozes() {
			super(1, "Két mozdony ütközése");
		}
		
		@Override
		protected void run() {
			/** 
			 * 	Beállítja a Logger-t inicializáló módba
			 *	Nem ír ki ilyenkor a Logger semmit a kimenetre
			 */
			logger.setInit(true);
			/**
			 * Inicializálja az Utkozes szekvenciáját
			 */
			Railway prevForL1 =new Railway(null);
			Railway underL1 = new Railway(prevForL1);
			Railway forCollision = new Railway(underL1);
			prevForL1.insertNeighbour(underL1);
			underL1.insertNeighbour(forCollision);
			Locomotive L1 = new Locomotive(underL1,prevForL1, null, 10);
			Locomotive L2 = new Locomotive(forCollision, null, null, 0);
			/** 
			 * 	Beállítja a Logger-t tesztelései módba
			 *	A Logger ilyenkor ír standard outputra
			 */
			logger.setInit(false);
			/**
			 * Elindítja az Utkozes szekvenciáját
			 */
			L1.move();
		}
	}
	
	protected class LeszallasMozdonyKocsi extends MenuItem {
		
		LeszallasMozdonyKocsi() {
			super(1, "Leszállás");
		}
		
		@Override
		protected void run() {
			/** 
			 * 	Beállítja a Logger-t inicializáló módba
			 *	Nem ír ki ilyenkor a Logger semmit a kimenetre
			 */
			logger.setInit(true);
			/**
			 * Inicializálja az LeszallasMozdonyKocsi szekvenciáját
			 */
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
			/** 
			 * 	Beállítja a Logger-t tesztelései módba
			 *	A Logger ilyenkor ír standard outputra
			 */
			logger.setInit(false);
			L.ArrivedAtStation(S);		
		}
		else if (leszall.equals('n'))
		{
			Station S = new Station(rwayAtStation, Color.PIROS);
			/** 
			 * 	Beállítja a Logger-t tesztelései módba
			 *	A Logger ilyenkor ír standard outputra
			 */
			logger.setInit(false);
			/**
			 * Elindítja az LeszallasMozdonyKocsi szekvenciáját
			 */
			L.ArrivedAtStation(S);
		}
		}
	}
	
	protected class LeszallasMozdonyUresJo extends MenuItem {
		
		LeszallasMozdonyUresJo() {
			super(1, "Leszállás egy üres vagon mögül.");
		}
		
		@Override
		protected void run() {
			/** 
			 * 	Beállítja a Logger-t inicializáló módba
			 *	Nem ír ki ilyenkor a Logger semmit a kimenetre
			 */
			logger.setInit(true);
			/**
			 * Inicializálja az LeszallasMozdonyUresJo szekvenciáját
			 */
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
			/** 
			 * 	Beállítja a Logger-t tesztelései módba
			 *	A Logger ilyenkor ír standard outputra
			 */
			logger.setInit(false);
			/**
			 * Elindítja az LeszallasMozdonyUresJo szekvenciáját
			 */
			L.ArrivedAtStation(S);
		}
	}
	
	protected class LeszallasMozdonyUresRossz extends MenuItem {
		
		LeszallasMozdonyUresRossz() {
			super(1, "Lesázllási kísérlet. Üres vagont egy rossz  követi.");
		}
		
		@Override
		protected void run() {
			/** 
			 * 	Beállítja a Logger-t inicializáló módba
			 *	Nem ír ki ilyenkor a Logger semmit a kimenetre
			 */
			logger.setInit(true);
			/**
			 * Inicializálja az LeszallasMozdonyUresRossz szekvenciáját
			 */

			Railway rwayAtStation = new Railway(null);
			Railway rwayBeforeStation = new Railway(rwayAtStation);
			Railway rway2BeoreStation = new Railway(rwayBeforeStation);
			rwayAtStation.insertNeighbour(rwayBeforeStation);
			rwayBeforeStation.insertNeighbour(rway2BeoreStation);
			Cart WrongColoredFull = new Cart(rway2BeoreStation,null, null, Color.PIROS,true);
			Cart EmptyCart = new Cart(rwayBeforeStation, rway2BeoreStation, WrongColoredFull, Color.KEK, false);
			Locomotive L = new Locomotive(rwayAtStation, rwayBeforeStation, EmptyCart, 10);
			Station S = new Station(rwayAtStation, Color.KEK);
			rwayAtStation.setStation(S);
			/** 
			 * 	Beállítja a Logger-t tesztelései módba
			 *	A Logger ilyenkor ír standard outputra
			 */
			logger.setInit(false);
			/**
			 * Elindítja az LeszallasMozdonyUresRossz szekvenciáját
			 */
			L.ArrivedAtStation(S);
		}
	}
	
	protected class LeszallasJoJoRossz extends MenuItem {
		
		LeszallasJoJoRossz() {
			super(1, "Lesázllás két vagonról.");
		}
		
		@Override
		protected void run() {
			/** 
			 * 	Beállítja a Logger-t inicializáló módba
			 *	Nem ír ki ilyenkor a Logger semmit a kimenetre
			 */
			logger.setInit(true);
			/**
			 * Inicializálja az LeszallasJoJoRossz szekvenciáját
			 */

			Railway rwayAtStation = new Railway(null);
			Railway rwayBeforeStation = new Railway(rwayAtStation);
			Railway rway2BeoreStation = new Railway(rwayBeforeStation);
			Railway rway3BeoreStation = new Railway(rway2BeoreStation);
			rwayAtStation.insertNeighbour(rwayBeforeStation);
			rwayBeforeStation.insertNeighbour(rway2BeoreStation);
			rway2BeoreStation.insertNeighbour(rway3BeoreStation);
			Cart WrongColoredFull = new Cart(rway3BeoreStation, null, null, Color.PIROS, true);
			Cart ReadyForLeave_1 = new Cart(rway2BeoreStation, rway3BeoreStation, WrongColoredFull, Color.KEK, true);
			Cart ReadyForLeave_2 = new Cart(rwayBeforeStation, rway2BeoreStation, ReadyForLeave_1, Color.KEK, true);
			Station S = new Station(rwayAtStation, Color.KEK);
			/** 
			 * 	Beállítja a Logger-t tesztelései módba
			 *	A Logger ilyenkor ír standard outputra
			 */
			logger.setInit(false);
			/**
			 * Elindítja az LeszallasJoJoRossz szekvenciáját
			 */
			rwayAtStation.setStation(S);
			
		}
	}
	
	protected class Valtas extends MenuItem {
		
		Valtas() {
			super(1, "Váltás");
		}
		
		@Override
		protected void run() {
			/** 
			 * 	Beállítja a Logger-t inicializáló módba
			 *	Nem ír ki ilyenkor a Logger semmit a kimenetre
			 */
			logger.setInit(true);
			/**
			 * Inicializálja az Valtas szekvenciáját
			 */

			Railway R1 = new Railway(null);
			Switch SW = new Switch(null, R1);
			Railway R2 = new Railway(SW);
			Railway R3 = new Railway(SW);
			R1.insertNeighbour(SW);
			SW.insertNeighbour(R2);
			SW.insertNeighbour(R3);
			SW.switchTo(R2);
			System.out.println("Áthaladjon a vonat?");
			/** 
			 * 	Beállítja a Logger-t tesztelései módba
			 *	A Logger ilyenkor ír standard outputra
			 */
			logger.setInit(false);
			Scanner scan = new Scanner(System.in);
			Character valt = scan.next().charAt(0);
			if(valt.equals('y'))
			{
				/**
				 * Elindítja az Valtas szekvenciáját
				 */
				SW.switchTo(R3);
			}
			logger.setInit(true);
			Locomotive L = new Locomotive(SW,R1, null,0);

		}
	}
	
	protected class AlagutEpites extends MenuItem {
		
		AlagutEpites() {
			super(1, "Alagútépítés");
		}
		
		@Override
		protected void run() {
			/** 
			 * 	Beállítja a Logger-t inicializáló módba
			 *	Nem ír ki ilyenkor a Logger semmit a kimenetre
			 */
			logger.setInit(true);
			/**
			 * Inicializálja az AlagutEpites szekvenciáját
			 */
			Railway r11 = new Railway(null);
			BuildingSpot bs1 = new BuildingSpot(r11);
			Railway r12 = new Railway(bs1);
			Railway r21 = new Railway(null);
			BuildingSpot bs2 = new BuildingSpot(r21);
			Railway r22 = new Railway(bs2);
			Tunnel T = new Tunnel();
			r11.insertNeighbour(bs1);
			bs1.insertNeighbour(r12);
			r21.insertNeighbour(bs2);
			bs2.insertNeighbour(r22);
			/** 
			 * 	Beállítja a Logger-t tesztelései módba
			 *	A Logger ilyenkor ír standard outputra
			 */
			logger.setInit(false);
			/**
			 * Elindítja az AlagutEpites szekvenciáját
			 */
			T.build(bs1, bs2);
		}
	}
	
	protected class AlagutRombolas extends MenuItem {
		
		AlagutRombolas() {
			super(1, "Alagút lerombolása");
		}
		
		@Override
		protected void run() {
			/** 
			 * 	Beállítja a Logger-t inicializáló módba
			 *	Nem ír ki ilyenkor a Logger semmit a kimenetre
			 */
			logger.setInit(true);
			/**
			 * Inicializálja az AlagutRombolas szekvenciáját
			 */
			Railway r11 = new Railway(null);
			BuildingSpot bs1 = new BuildingSpot(r11);
			Railway r12 = new Railway(bs1);
			Railway r21 = new Railway(null);
			BuildingSpot bs2 = new BuildingSpot(r21);
			Railway r22 = new Railway(bs2);
			Tunnel T = new Tunnel();
			r11.insertNeighbour(bs1);
			bs1.insertNeighbour(r12);
			r21.insertNeighbour(bs2);
			bs2.insertNeighbour(r22);
			ArrayList<Railway> swap21 = r21.getThisNeighbour();
			ArrayList<Railway> swap22 = r22.getThisNeighbour();
			bs1.setNewNeighbours(swap21, swap22);
			ArrayList<Railway> swap11 = r11.getThisNeighbour();
			ArrayList<Railway> swap12 = r12.getThisNeighbour();
			bs1.setNewNeighbours(swap11, swap12);
			/** 
			 * 	Beállítja a Logger-t tesztelései módba
			 *	A Logger ilyenkor ír standard outputra
			 */
			logger.setInit(false);
			/**
			 * Elindítja az AlagutRombolas szekvenciáját
			 */
			T.destroy();
		}
	}
	
	protected class ValtasVonattal extends MenuItem {
		
		ValtasVonattal() {
			super(1, "Váltlás vonatal");
		}
		
		@Override
		protected void run() {
			/** 
			 * 	Beállítja a Logger-t inicializáló módba
			 *	Nem ír ki ilyenkor a Logger semmit a kimenetre
			 */
			logger.setInit(true);
			/**
			 * Inicializálja az ValtasVonattal szekvenciáját
			 */
			Railway next = new Railway(null);
			Switch current = new Switch(null, next);
			Railway prev = new Railway(current);
			Railway R3 = new Railway(current);
			next.insertNeighbour(current);
			current.insertNeighbour(prev);
			current.insertNeighbour(R3);
			Locomotive L = new Locomotive(current, prev, null, 10);
			/** 
			 * 	Beállítja a Logger-t tesztelései módba
			 *	A Logger ilyenkor ír standard outputra
			 */
			logger.setInit(false);
			/**
			 * Elindítja az ValtasVonattal szekvenciáját
			 */

			L.move();
		}
	}

}
