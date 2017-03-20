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
					
					System.out.println("Írja be a megfelelõ menüponthoz tartozó számot annak kiválasztásához...");
					for (MenuItem item: items)
						System.out.println(item.id + ": " + item.name);
					
					int controlNumber = Integer.parseInt(input.readLine());
					
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
			
			underC2.setName("underC2");
			underC1.setName("underC1");
			underL.setName("underL");
			nextForL.setName("nextForL");
			C2.setName("C2");
			C1.setName("C1");
			L.setName("L");

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
			super(2, "Két mozdony ütközése");
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
			
			prevForL1.setName("prevForL1");
			underL1.setName("underL1");
			forCollision.setName("forCollision");
			L1.setName("L1");
			L2.setName("L2");
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
			super(3, "Leszállás egy kocsival");
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
		
		rwayAtStation.setName("rwayAtStation");
		rwayBeforeStation.setName("rwayBeforeStation");
		C.setName("C");
		L.setName("L");
		
		System.out.println("Leszállhatnak az utasok? (y/n)");
		Scanner scan = new Scanner(System.in);
		Character leszall = scan.next().charAt(0);
		if(leszall.equals('y'))
			{
			Station S = new Station(rwayAtStation, Color.KEK);
			S.setName("S");
			/** 
			 * 	Beállítja a Logger-t tesztelései módba
			 *	A Logger ilyenkor ír standard outputra
			 */
			logger.setInit(false);
			L.ArrivedAtStation(S);
			
		} else if (leszall.equals('n'))
			{
			Station S = new Station(rwayAtStation, Color.PIROS);
			S.setName("S");
			/** 
			 * 	Beállítja a Logger-t tesztelései módba
			 *	A Logger ilyenkor ír standard outputra
			 */
			logger.setInit(false);
			L.ArrivedAtStation(S);
		}
		}
	}
	
	protected class LeszallasMozdonyUresJo extends MenuItem {
		
		LeszallasMozdonyUresJo() {
			super(4, "Leszállás egy üres vagon mögül.");
		}
		
		@Override
		protected void run() {
			/** 
			 * 	Beállítja a Logger-t inicializáló módba
			 *	Nem ír ki ilyenkor a Logger semmit a kimenetre
			 */
			logger.setInit(true);
			
			Railway rwayAtStation = new Railway(null);
			Railway rwayBeforeStation = new Railway(rwayAtStation);
			Railway rway2BeforeStation = new Railway(rwayBeforeStation);
			rwayAtStation.insertNeighbour(rwayBeforeStation);
			rwayBeforeStation.insertNeighbour(rway2BeforeStation);
			Cart ReadyforLeaveCart = new Cart(rway2BeforeStation,null, null, Color.KEK,true);
			Cart EmptyCart = new Cart(rwayBeforeStation, rway2BeforeStation, ReadyforLeaveCart, Color.KEK, false);
			Locomotive L = new Locomotive(rwayAtStation, rwayBeforeStation, EmptyCart, 10);
			Station S = new Station(rwayAtStation, Color.KEK);
			rwayAtStation.setStation(S);
			
			rwayAtStation.setName("rwayAtStation");
			rwayBeforeStation.setName("rwayBeforeStation");
			rway2BeforeStation.setName("rway2BeforeStation");
			ReadyforLeaveCart.setName("ReadyForLeaveCart");
			EmptyCart.setName("EmptyCart");
			L.setName("L");
			S.setName("S");
			/** 
			 * 	Beállítja a Logger-t tesztelései módba
			 *	A Logger ilyenkor ír standard outputra
			 */
			logger.setInit(false);
			L.ArrivedAtStation(S);
		}
	}
	
	protected class LeszallasMozdonyUresRossz extends MenuItem {
		
		LeszallasMozdonyUresRossz() {
			super(5, "Lesázllási kísérlet. Üres vagont egy rossz követi.");
		}
		
		@Override
		protected void run() {
			/** 
			 * 	Beállítja a Logger-t inicializáló módba
			 *	Nem ír ki ilyenkor a Logger semmit a kimenetre
			 */
			logger.setInit(true);
			
			Railway rwayAtStation = new Railway(null);
			Railway rwayBeforeStation = new Railway(rwayAtStation);
			Railway rway2BeforeStation = new Railway(rwayBeforeStation);
			rwayAtStation.insertNeighbour(rwayBeforeStation);
			rwayBeforeStation.insertNeighbour(rway2BeforeStation);
			Cart WrongColoredFull = new Cart(rway2BeforeStation,null, null, Color.PIROS,true);
			Cart EmptyCart = new Cart(rwayBeforeStation, rway2BeforeStation, WrongColoredFull, Color.KEK, false);
			Locomotive L = new Locomotive(rwayAtStation, rwayBeforeStation, EmptyCart, 10);
			Station S = new Station(rwayAtStation, Color.KEK);
			rwayAtStation.setStation(S);
			
			rwayAtStation.setName("rwayAtStation");
			rwayBeforeStation.setName("rwayBeforeStation");
			rway2BeforeStation.setName("rway2BeforeStation");
			WrongColoredFull.setName("WrongColoredFull");
			EmptyCart.setName("EmptyCart");
			/** 
			 * 	Beállítja a Logger-t tesztelései módba
			 *	A Logger ilyenkor ír standard outputra
			 */
			logger.setInit(false);
			L.ArrivedAtStation(S);
		}
	}
	
	protected class LeszallasJoJoRossz extends MenuItem {
		
		LeszallasJoJoRossz() {
			super(6, "Lesázllás két vagonról.");
		}
		
		@Override
		protected void run() {
			/** 
			 * 	Beállítja a Logger-t inicializáló módba
			 *	Nem ír ki ilyenkor a Logger semmit a kimenetre
			 */
			logger.setInit(true);
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
			rwayAtStation.setStation(S);
			
		}
	}
	
	protected class Valtas extends MenuItem {
		
		Valtas() {
			super(7, "Váltás");
		}
		
		@Override
		protected void run() {
			/** 
			 * 	Beállítja a Logger-t inicializáló módba
			 *	Nem ír ki ilyenkor a Logger semmit a kimenetre
			 */
			logger.setInit(true);
			Railway R1 = new Railway(null);
			Switch SW = new Switch(null, R1);
			Railway R2 = new Railway(SW);
			Railway R3 = new Railway(SW);
			R1.insertNeighbour(SW);
			SW.insertNeighbour(R2);
			SW.insertNeighbour(R3);
			SW.switchTo(R2);
			System.out.println("Áthaladjon a vonat?");
			logger.setInit(false);
			Scanner scan = new Scanner(System.in);
			Character valt = scan.next().charAt(0);
			if(valt.equals('y'))
			{
				SW.switchTo(R3);
			}
			logger.setInit(true);
			Locomotive L = new Locomotive(SW,R1, null,0);
	
			logger.setInit(false);

		}
	}
	
	protected class AlagutEpites extends MenuItem {
		
		AlagutEpites() {
			super(8, "Alagútépítés");
		}
		
		@Override
		protected void run() {
			/** 
			 * 	Beállítja a Logger-t inicializáló módba
			 *	Nem ír ki ilyenkor a Logger semmit a kimenetre
			 */
			logger.setInit(true);
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
			T.build(bs1, bs2);
		}
	}
	
	protected class AlagutRombolas extends MenuItem {
		
		AlagutRombolas() {
			super(9, "Alagút lerombolása");
		}
		
		@Override
		protected void run() {
			/** 
			 * 	Beállítja a Logger-t inicializáló módba
			 *	Nem ír ki ilyenkor a Logger semmit a kimenetre
			 */
			logger.setInit(true);
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
			T.destroy();
		}
	}
	
	protected class ValtasVonattal extends MenuItem {
		
		ValtasVonattal() {
			super(10, "Váltlás vonatal");
		}
		
		@Override
		protected void run() {
			/** 
			 * 	Beállítja a Logger-t inicializáló módba
			 *	Nem ír ki ilyenkor a Logger semmit a kimenetre
			 */
			logger.setInit(true);
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
			L.move();
		}
	}

}