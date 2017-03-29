package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Application {

	//Tarolja a lehetseges menupontokat
	static private List<MenuItem> items = new ArrayList<MenuItem>();
	
	//Az obejktumok kozott torteno fuggvenyhivasokat kozvetiti a felhasznalo fele.
	static public Logger logger = new Logger();
	
	/**
	 * Az egesz program belepesi pontja
	 * @param args pl.: parancssori argumentumok
	 */
	public static void main(String[] args) {
		
		/**
		 * A program fo hurka amiben a felhasznalot kerdezi melyik tesztesetet szeretne latni, majd azt vegre is hajtja
		 */
		while(true) {
			
			BufferedReader input = new BufferedReader (new InputStreamReader(System.in));
			
			//A menuelemek listahoz adasa
			items.add(new VonatLeptetes());
			items.add(new Utkozes());
			items.add(new LeszallasMozdonyKocsi());
			items.add(new LeszallasMozdonyUresJo());
			items.add(new LeszallasMozdonyUresRossz());
			items.add(new LeszallasJoJoRossz());
			items.add(new Valtas());
			items.add(new AlagutEpites());
			items.add(new AlagutRombolas());
			items.add(new ValtasVonattal());
			
			int controlNumber = 1;
			//Beolvasasi ciklus... Amig nem kaptunk egy megfelelo
			while(true) {
				
				try {
					
					// A felhasznalonak kiirt uzenet. Tartalmaz minden menupontrol egy leirast.
					System.out.println("�rja be a megfelel� men�ponthoz tartoz� sz�mot annak kiv�laszt�s�hoz, vagy 0-�t a kil�p�shez...");
					for (MenuItem item: items)
						System.out.println(item.id + ": " + item.name);
					
					// A felhasznalo valaszanak beolvasasa
					controlNumber = Integer.parseInt(input.readLine());
					
					//Kilepes a programbol
					if (controlNumber == 0)
						break;
					
					//A megfelelo menuponthoz tartozo objektum kivalaszasa
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
			if (controlNumber == 0) break;
		}
		
		
	}

	
	/**
	 * Gyozelem eseten (azaz, ha minden kocsi kiurult) a kovetkezo palyat tolti be.
	 */
	public static void win() {
		logger.enter(new Application(), "win", null);
		logger.exit(null);
	}
	
	/**
	 * Vereseg eseten a jatekmenet leallitasat kezeli.
	 */
	public static void lose() {
		logger.enter(new Application(), "lose", null);
		logger.exit(null);
	}
	
	/**
	 * A logger altali kiiratashoz szukseges.
	 */
	public String toString() {
		return "Application";
	}
	
	/**
	 * Az egyes menupontok adatait fogja ossze.
	 * Tartalmaz egy szamot ami alapjan lehet ra hivatkoznia a felhasznalonak, egy rovid leirast,
	 * es egy f�ggvenyt ami az adott menuponthoz tartozo teszteset, es ezt fogja ossze egy osztallya, 
	 * amibol aztan leszarmazassal lehet menupontokat letrehozni.
	 */
	protected static abstract class MenuItem {
		
		public final int id;
		public final String name;
		
		MenuItem(int id, String name) {
			this.id = id;
			this.name = name;
		}
		
		protected abstract void run();
		
	}
	
	
	protected static class VonatLeptetes extends MenuItem {
		
		VonatLeptetes() {
			super(1, "A vonat l�ptet�se a s�nen");
		}
		
		@Override
		protected void run() {
			/** 
			 * 	Beallitja a Logger-t inicializalo modba
			 *	Nem ir ki ilyenkor a Logger semmit a kimenetre
			 */
			logger.setInit(true);
			/**
			 * Inicializalja az VonatLeptetes szekvenciajat
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
			 * 	Beallitja a Logger-t tesztelesei modba
			 *	A Logger ilyenkor ir standard outputra
			 */
			logger.setInit(false);
			/**
			 * Elinditja az VonatLeptetes szekvenciajat
			 */
			L.move();
		}
	}
	
	protected static class Utkozes extends MenuItem {
		
		Utkozes() {
			super(2, "K�t mozdony �tk�z�se");
		}
		
		@Override
		protected void run() {
			/** 
			 * 	Beallitja a Logger-t inicializalo modba
			 *	Nem ir ki ilyenkor a Logger semmit a kimenetre
			 */
			logger.setInit(true);
			/**
			 * Inicializalja az Utkozes szekvenciajat
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
			 * 	Beallitja a Logger-t tesztelesei modba
			 *	A Logger ilyenkor ir standard outputra
			 */
			logger.setInit(false);
			/**
			 * Elinditja az Utkozes szekvenciajat
			 */
			L1.move();
		}
	}
	
	protected static class LeszallasMozdonyKocsi extends MenuItem {
		
		LeszallasMozdonyKocsi() {
			super(3, "Lesz�ll�s egy kocsival");
		}
		
		@Override
		protected void run() {
			/** 
			 * 	Beallitja a Logger-t inicializal� modba
			 *	Nem ir ki ilyenkor a Logger semmit a kimenetre
			 */	
			logger.setInit(true);
			/**
			 * Inicializalja az LeszallasMozdonyKocsi szekvenciajat
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
		
			System.out.println("Lesz�llhatnak az utasok? (y/n)");
			Scanner scan = new Scanner(System.in);
			Character leszall = scan.next().charAt(0);
			if(leszall.equals('y')) {
				
				Station S = new Station(rwayAtStation, Color.KEK);
				S.setName("S");
				/** 
				 * 	Beallitja a Logger-t tesztelesei modba
				 *	A Logger ilyenkor ir standard outputra
				 */
				logger.setInit(false);
				/**
				 * Elinditja az LeszallasMozdonyKocsi szekvenciajat
				 */
				L.ArrivedAtStation(S);
			} else if (leszall.equals('n')) {
			
				Station S = new Station(rwayAtStation, Color.PIROS);
				S.setName("S");
				/** 
				 * 	Beallitja a Logger-t tesztelesei modba
				 *	A Logger ilyenkor ir standard outputra
				 */
				logger.setInit(false);
				/**
				 * Elinditja az LeszallasMozdonyKocsi szekvenciajat
				 */
				L.ArrivedAtStation(S);
			}
		}
	}
	
	protected static class LeszallasMozdonyUresJo extends MenuItem {
		
		LeszallasMozdonyUresJo() {
			super(4, "Lesz�ll�s egy �res vagon m�g�l.");
		}
		
		@Override
		protected void run() {
			/** 
			 * 	Beallitja a Logger-t inicializalo modba
			 *	Nem �r ki ilyenkor a Logger semmit a kimenetre
			 */
			logger.setInit(true);
			/** 
			 * 	Be�ll�tja a Logger-t inicializ�l� m�dba
			 *	Nem �r ki ilyenkor a Logger semmit a kimenetre
			 */
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
	
	protected static class LeszallasMozdonyUresRossz extends MenuItem {
		
		LeszallasMozdonyUresRossz() {
			super(5, "Les�zll�si k�s�rlet. �res vagont egy rossz k�veti.");
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
			L.setName("L");
			S.setName("S");
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
	
	protected static class LeszallasJoJoRossz extends MenuItem {
		
		LeszallasJoJoRossz() {
			super(6, "Les�zll�s k�t vagonr�l, amit ut�na egy rossz sz�n� vagon k�vet.");
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
			Locomotive L = new Locomotive(rwayAtStation, rwayBeforeStation, ReadyForLeave_2, 10);
			Station S = new Station(rwayAtStation, Color.KEK);
			rwayAtStation.setStation(S);
			
			rwayAtStation.setName("rwayAtStation");
			rwayBeforeStation.setName("rwayBeforeStation");
			rway2BeoreStation.setName("rway2BeforeStation");
			rway3BeoreStation.setName("rway3BeforeStation");
			WrongColoredFull.setName("WrongColoredFull");
			ReadyForLeave_1.setName("ReadyForLeave_1");
			ReadyForLeave_2.setName("ReadyForLeave_2");
			S.setName("S");
			L.setName("L");
			/** 
			 * 	Be�ll�tja a Logger-t tesztel�sei m�dba
			 *	A Logger ilyenkor �r standard outputra
			 */
			logger.setInit(false);
			/**
			 * Elind�tja az LeszallasJoJoRossz szekvenci�j�t
			 */
			L.ArrivedAtStation(S);
		}
	}
	
	protected static class Valtas extends MenuItem {
		
		Valtas() {
			super(7, "V�lt�s, majd azon �thalad�s.");
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
			Switch SW;
			Railway R2 = new Railway(null);
			SW = new Switch(R2, R1);
			Railway R3 = new Railway(SW);
			R1.insertNeighbour(SW);
			R2.insertNeighbour(SW);
			SW.insertNeighbour(R2);
			SW.insertNeighbour(R3);
			SW.switchTo(R2);
			Locomotive L = new Locomotive(R1, null, null, 10);
			
			R1.setName("R1");
			R2.setName("R2");
			R3.setName("R3");
			SW.setName("SW");
			L.setName("L");
			
			logger.setInit(false);
			System.out.println("A v�lt� R2 fele �ll. V�ltson-e a v�lt�?(y/n)");
			Scanner scan = new Scanner(System.in);
			Character valt = scan.next().charAt(0);
			if(valt.equals('y'))
			{
				/**
				 * Elind�tja az Valtas szekvenci�j�t
				 */
				SW.switchTo(R3);
			}
			
			/**
			 * Vonat �thalad�sa a v�lt�n.
			 */
			L.move();
			L.move();
		}
	}
	
	protected static class AlagutEpites extends MenuItem {
		
		AlagutEpites() {
			super(8, "Alag�t�p�t�s");
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
			
			r11.setName("r11");
			r12.setName("r12");
			r21.setName("r21");
			r22.setName("r22");
			bs1.setName("bs1");
			bs2.setName("bs2");
			T.setName("T");
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
	
	protected static class AlagutRombolas extends MenuItem {
		
		AlagutRombolas() {
			super(9, "Alag�t lerombol�sa");
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
			
			r11.setName("r11");
			r12.setName("r12");
			r21.setName("r21");
			r22.setName("r22");
			bs1.setName("bs1");
			bs2.setName("bs2");
			T.setName("T");
			
			T.build(bs1, bs2);
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
	
	protected static class ValtasVonattal extends MenuItem {
		
		ValtasVonattal() {
			super(10, "V�ltl�s vonatal");
		}
		
		@Override
		protected void run() {
			/** 
			 * 	Be�ll�tja a Logger-t inicializ�l� m�dba
			 *	Nem �r ki ilyenkor a Logger semmit a kimenetre
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
			
			next.setName("next");
			current.setName("currentSwitch");
			prev.setName("prev");
			R3.setName("R3");
			L.setName("L");
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