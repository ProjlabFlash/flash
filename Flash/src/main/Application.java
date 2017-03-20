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
	 * Az eg�sz program bel�p�si pontja
	 * @param args pl.: parancssori argumentumok
	 */
	public static void main(String[] args) {
		
		System.out.println("hi");
		
		while(true) {
			
			BufferedReader input = new BufferedReader (new InputStreamReader(System.in));
			
			//Men�pontok l�trehoz�sa
			
			
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
					
					System.out.println("A megadott �rt�k nem egy eg�sz sz�m");
				} catch (IOException e) {
					
					e.printStackTrace();
				}
			}
		}
		
		
	}

	
	/**
	 * Gy�zelem eset�n (azaz, ha minden kocsi ki�r�lt) a k�vetkez� p�ly�t t�lti be.
	 */
	public static void win() {
		
	}
	
	/**
	 * Veres�g eset�n a j�t�kmenet le�ll�t�s�t kezeli.
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
			super(1, "A vonat l�ptet�se a s�nen");
		}
		
		@Override
		protected void run() {
			/** 
			 * 	Be�ll�tja a Logger-t inicializ�l� m�dba
			 *	Nem �r ki ilyenkor a Logger semmit a kimenetre
			 */
			logger.setInit(true);
			/**
			 * Inicializ�lja az VonatLeptetes szekvenci�j�t
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
			 * 	Be�ll�tja a Logger-t tesztel�sei m�dba
			 *	A Logger ilyenkor �r standard outputra
			 */
			logger.setInit(false);
			/**
			 * Elind�tja az VonatLeptetes szekvenci�j�t
			 */
			L.move();
		}
	}
	
	protected class Utkozes extends MenuItem {
		
		Utkozes() {
			super(1, "K�t mozdony �tk�z�se");
		}
		
		@Override
		protected void run() {
			/** 
			 * 	Be�ll�tja a Logger-t inicializ�l� m�dba
			 *	Nem �r ki ilyenkor a Logger semmit a kimenetre
			 */
			logger.setInit(true);
			/**
			 * Inicializ�lja az Utkozes szekvenci�j�t
			 */
			Railway prevForL1 =new Railway(null);
			Railway underL1 = new Railway(prevForL1);
			Railway forCollision = new Railway(underL1);
			prevForL1.insertNeighbour(underL1);
			underL1.insertNeighbour(forCollision);
			Locomotive L1 = new Locomotive(underL1,prevForL1, null, 10);
			Locomotive L2 = new Locomotive(forCollision, null, null, 0);
			/** 
			 * 	Be�ll�tja a Logger-t tesztel�sei m�dba
			 *	A Logger ilyenkor �r standard outputra
			 */
			logger.setInit(false);
			/**
			 * Elind�tja az Utkozes szekvenci�j�t
			 */
			L1.move();
		}
	}
	
	protected class LeszallasMozdonyKocsi extends MenuItem {
		
		LeszallasMozdonyKocsi() {
			super(1, "Lesz�ll�s");
		}
		
		@Override
		protected void run() {
			/** 
			 * 	Be�ll�tja a Logger-t inicializ�l� m�dba
			 *	Nem �r ki ilyenkor a Logger semmit a kimenetre
			 */
			logger.setInit(true);
			/**
			 * Inicializ�lja az LeszallasMozdonyKocsi szekvenci�j�t
			 */
		Railway rwayAtStation = new Railway(null);
		Railway rwayBeforeStation = new Railway(rwayAtStation);
		rwayBeforeStation.insertNeighbour(rwayAtStation);
		Cart C = new Cart(rwayBeforeStation, null,null,Color.KEK,true);
		Locomotive L = new Locomotive(rwayAtStation,rwayBeforeStation, C, 10);
		System.out.println("Lesz�llhatnak az utasok? (y/n)");
		Scanner scan = new Scanner(System.in);
		Character leszall = scan.next().charAt(0);
		if(leszall.equals('y'))
		{
			Station S = new Station(rwayAtStation, Color.KEK);
			/** 
			 * 	Be�ll�tja a Logger-t tesztel�sei m�dba
			 *	A Logger ilyenkor �r standard outputra
			 */
			logger.setInit(false);
			L.ArrivedAtStation(S);		
		}
		else if (leszall.equals('n'))
		{
			Station S = new Station(rwayAtStation, Color.PIROS);
			/** 
			 * 	Be�ll�tja a Logger-t tesztel�sei m�dba
			 *	A Logger ilyenkor �r standard outputra
			 */
			logger.setInit(false);
			/**
			 * Elind�tja az LeszallasMozdonyKocsi szekvenci�j�t
			 */
			L.ArrivedAtStation(S);
		}
		}
	}
	
	protected class LeszallasMozdonyUresJo extends MenuItem {
		
		LeszallasMozdonyUresJo() {
			super(1, "Lesz�ll�s egy �res vagon m�g�l.");
		}
		
		@Override
		protected void run() {
			/** 
			 * 	Be�ll�tja a Logger-t inicializ�l� m�dba
			 *	Nem �r ki ilyenkor a Logger semmit a kimenetre
			 */
			logger.setInit(true);
			/**
			 * Inicializ�lja az LeszallasMozdonyUresJo szekvenci�j�t
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
			 * 	Be�ll�tja a Logger-t tesztel�sei m�dba
			 *	A Logger ilyenkor �r standard outputra
			 */
			logger.setInit(false);
			/**
			 * Elind�tja az LeszallasMozdonyUresJo szekvenci�j�t
			 */
			L.ArrivedAtStation(S);
		}
	}
	
	protected class LeszallasMozdonyUresRossz extends MenuItem {
		
		LeszallasMozdonyUresRossz() {
			super(1, "Les�zll�si k�s�rlet. �res vagont egy rossz  k�veti.");
		}
		
		@Override
		protected void run() {
			/** 
			 * 	Be�ll�tja a Logger-t inicializ�l� m�dba
			 *	Nem �r ki ilyenkor a Logger semmit a kimenetre
			 */
			logger.setInit(true);
			/**
			 * Inicializ�lja az LeszallasMozdonyUresRossz szekvenci�j�t
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
			 * 	Be�ll�tja a Logger-t tesztel�sei m�dba
			 *	A Logger ilyenkor �r standard outputra
			 */
			logger.setInit(false);
			/**
			 * Elind�tja az LeszallasMozdonyUresRossz szekvenci�j�t
			 */
			L.ArrivedAtStation(S);
		}
	}
	
	protected class LeszallasJoJoRossz extends MenuItem {
		
		LeszallasJoJoRossz() {
			super(1, "Les�zll�s k�t vagonr�l.");
		}
		
		@Override
		protected void run() {
			/** 
			 * 	Be�ll�tja a Logger-t inicializ�l� m�dba
			 *	Nem �r ki ilyenkor a Logger semmit a kimenetre
			 */
			logger.setInit(true);
			/**
			 * Inicializ�lja az LeszallasJoJoRossz szekvenci�j�t
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
			 * 	Be�ll�tja a Logger-t tesztel�sei m�dba
			 *	A Logger ilyenkor �r standard outputra
			 */
			logger.setInit(false);
			/**
			 * Elind�tja az LeszallasJoJoRossz szekvenci�j�t
			 */
			rwayAtStation.setStation(S);
			
		}
	}
	
	protected class Valtas extends MenuItem {
		
		Valtas() {
			super(1, "V�lt�s");
		}
		
		@Override
		protected void run() {
			/** 
			 * 	Be�ll�tja a Logger-t inicializ�l� m�dba
			 *	Nem �r ki ilyenkor a Logger semmit a kimenetre
			 */
			logger.setInit(true);
			/**
			 * Inicializ�lja az Valtas szekvenci�j�t
			 */

			Railway R1 = new Railway(null);
			Switch SW = new Switch(null, R1);
			Railway R2 = new Railway(SW);
			Railway R3 = new Railway(SW);
			R1.insertNeighbour(SW);
			SW.insertNeighbour(R2);
			SW.insertNeighbour(R3);
			SW.switchTo(R2);
			System.out.println("�thaladjon a vonat?");
			/** 
			 * 	Be�ll�tja a Logger-t tesztel�sei m�dba
			 *	A Logger ilyenkor �r standard outputra
			 */
			logger.setInit(false);
			Scanner scan = new Scanner(System.in);
			Character valt = scan.next().charAt(0);
			if(valt.equals('y'))
			{
				/**
				 * Elind�tja az Valtas szekvenci�j�t
				 */
				SW.switchTo(R3);
			}
			logger.setInit(true);
			Locomotive L = new Locomotive(SW,R1, null,0);

		}
	}
	
	protected class AlagutEpites extends MenuItem {
		
		AlagutEpites() {
			super(1, "Alag�t�p�t�s");
		}
		
		@Override
		protected void run() {
			/** 
			 * 	Be�ll�tja a Logger-t inicializ�l� m�dba
			 *	Nem �r ki ilyenkor a Logger semmit a kimenetre
			 */
			logger.setInit(true);
			/**
			 * Inicializ�lja az AlagutEpites szekvenci�j�t
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
			 * 	Be�ll�tja a Logger-t tesztel�sei m�dba
			 *	A Logger ilyenkor �r standard outputra
			 */
			logger.setInit(false);
			/**
			 * Elind�tja az AlagutEpites szekvenci�j�t
			 */
			T.build(bs1, bs2);
		}
	}
	
	protected class AlagutRombolas extends MenuItem {
		
		AlagutRombolas() {
			super(1, "Alag�t lerombol�sa");
		}
		
		@Override
		protected void run() {
			/** 
			 * 	Be�ll�tja a Logger-t inicializ�l� m�dba
			 *	Nem �r ki ilyenkor a Logger semmit a kimenetre
			 */
			logger.setInit(true);
			/**
			 * Inicializ�lja az AlagutRombolas szekvenci�j�t
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
			 * 	Be�ll�tja a Logger-t tesztel�sei m�dba
			 *	A Logger ilyenkor �r standard outputra
			 */
			logger.setInit(false);
			/**
			 * Elind�tja az AlagutRombolas szekvenci�j�t
			 */
			T.destroy();
		}
	}
	
	protected class ValtasVonattal extends MenuItem {
		
		ValtasVonattal() {
			super(1, "V�ltl�s vonatal");
		}
		
		@Override
		protected void run() {
			/** 
			 * 	Be�ll�tja a Logger-t inicializ�l� m�dba
			 *	Nem �r ki ilyenkor a Logger semmit a kimenetre
			 */
			logger.setInit(true);
			/**
			 * Inicializ�lja az ValtasVonattal szekvenci�j�t
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
			 * 	Be�ll�tja a Logger-t tesztel�sei m�dba
			 *	A Logger ilyenkor �r standard outputra
			 */
			logger.setInit(false);
			/**
			 * Elind�tja az ValtasVonattal szekvenci�j�t
			 */

			L.move();
		}
	}

}
