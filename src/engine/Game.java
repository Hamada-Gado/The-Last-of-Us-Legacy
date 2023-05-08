package engine;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import exceptions.InvalidTargetException;
import exceptions.NotEnoughActionsException;
import model.characters.Hero;
import model.characters.Character;
import model.characters.Explorer;
import model.characters.Fighter;
import model.characters.Medic;

import model.characters.Zombie;
import model.collectibles.Collectible;
import model.collectibles.Supply;
import model.collectibles.Vaccine;
import model.world.Cell;
import model.world.CharacterCell;
import model.world.CollectibleCell;
import model.world.TrapCell;

/**
 *  A class representing the Game itself. This class will represent the main engine of the
 *  game, and will ensure all game rules are followed.
 * @author Ahmed, Mostafa, Rasheed
 *
 */
public class Game {
	
	public static final int HEIGHT = 15;
	public static final int WIDTH = 15;
	public static Cell [][] map = new Cell[HEIGHT][WIDTH];
	public static ArrayList<Hero> availableHeroes = new ArrayList<Hero>();
	public static ArrayList<Hero> heroes = new ArrayList<Hero>();
	public static ArrayList<Zombie> zombies = new ArrayList<Zombie>();
	
	public static void loadHeroes(String filePath) throws FileNotFoundException, IOException  {
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		String line = "";
		String[] info;
		while((line = br.readLine()) != null) {
			info = line.split(",");
			switch (info[1]) {
			case "FIGH":
				availableHeroes.add(new Fighter(
						info[0], 
						Integer.parseInt(info[2]), 
						Integer.parseInt(info[4]), 
						Integer.parseInt(info[3])
						));
				break;
			case "EXP":
				availableHeroes.add(new Explorer(
						info[0], 
						Integer.parseInt(info[2]), 
						Integer.parseInt(info[4]), 
						Integer.parseInt(info[3])
						));
				break;
			case "MED":
				availableHeroes.add(new Medic(
						info[0], 
						Integer.parseInt(info[2]), 
						Integer.parseInt(info[4]), 
						Integer.parseInt(info[3])
						));
				break;
			}
		}
		
		br.close();
	}
	
	
	public static void changeCellVisibility(int x, int y, boolean visibility) {
		if (x < 0 || x >= Game.HEIGHT || y < 0 || y >= Game.WIDTH) return;
//		System.out.println(x + ", " + y);
//		System.out.println(visibility);

		Game.map[x][y].setVisible(visibility);
	}
	
	public static void changeAdjacentCellsVisibility(Character h, boolean visibility) {
		int x = h.getLocation().x;
		int y = h.getLocation().y;
		
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				changeCellVisibility(x + i, y + j, visibility);
			}
		}
	}
	
	public static void resetMapVisibility() {
		 
		 for (Cell[] cells : map) {
			 for (Cell cell : cells) {
				 cell.setVisible(false);
			 }
		 }
		 
		 for (Hero hero : heroes) {
			 changeAdjacentCellsVisibility(hero, true);
		 }
		
	}
	
	public static void addRandomZombie() {
		Random randGen = new Random();
		
		int x, y;
		Cell c;
		
		while(true) {
			x = randGen.nextInt(HEIGHT);
			y = randGen.nextInt(WIDTH);
			c = map[x][y];
			
			if (!(c instanceof CharacterCell)) continue;
			if (((CharacterCell) c).getCharacter() != null) continue;
			
			Zombie z = new Zombie();
			z.setLocation(new Point(x, y));
			zombies.add(z);
			((CharacterCell) c).setCharacter(z);
			
			break;
		}
	}
	
	public static void addRandomCollectible(Collectible collectible) {
		Random randGen = new Random();
		
		int x, y;
		Cell c;
		
		while(true) {
			x = randGen.nextInt(HEIGHT);
			y = randGen.nextInt(WIDTH);
			c = map[x][y];
			
			if (!(c instanceof CharacterCell)) continue;
			if (((CharacterCell) c).getCharacter() != null) continue;
			
			map[x][y] = new CollectibleCell(collectible);
			
			break;
		}
	}
	
	public static void addRandomTrap() {
		Random randGen = new Random();
		
		int x, y;
		Cell c;
		
		while(true) {
			x = randGen.nextInt(HEIGHT);
			y = randGen.nextInt(WIDTH);
			c = map[x][y];
			
			if (!(c instanceof CharacterCell)) continue;
			if (((CharacterCell) c).getCharacter() != null) continue;
			
			map[x][y] = new TrapCell();
			
			break;
		}
	}
	
	public static void startGame(Hero h) {
		
		for (int x = 0; x < HEIGHT; x++) {
			for (int y = 0; y < WIDTH; y++) {
				map[x][y] = new CharacterCell(null);
			}
		}
		
		availableHeroes.remove(h);
		heroes.add(h);
		h.setLocation(new Point(0, 0));
		map[0][0] = new CharacterCell(h);
		
		for (int i = 0; i < 5; i++) {
			addRandomCollectible(new Vaccine());
			addRandomCollectible(new Supply());
			addRandomTrap();
		}
		
		for (int i = 0; i < 10; i++) addRandomZombie();

		changeAdjacentCellsVisibility(h, true);	
	}
	
	public static boolean noMoreVaccines() {
		for (Cell[] cells : map) {
			for (Cell cell : cells) {
				if (cell instanceof CollectibleCell) {
					if (((CollectibleCell) cell).getCollectible() instanceof Vaccine) {
						return false;
					}
				}
			}
		}
		
		for (Hero hero : heroes) {
			if (hero.getVaccineInventory().size() > 0) return false;
		}
		
		return true;
	}
	
	public static boolean checkWin() {
		return noMoreVaccines() && heroes.size() >= 5;
	}
	
	
	public static boolean checkGameOver() {
		return noMoreVaccines() || heroes.isEmpty();
	}
	
	 public static void endTurn() {
		 resetMapVisibility();
		 
		 for (Zombie z : zombies) {
			 try {
				z.attack();
			 } catch (InvalidTargetException | NotEnoughActionsException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			 }
			 z.setTarget(null);
		 }
		 
		 
		 for (Hero h : heroes) {
			 h.setActionsAvailable(h.getMaxActions());
			 h.setTarget(null);
			 h.setSpecialAction(false);
			 changeAdjacentCellsVisibility(h, true);
		 }
		 
		 
		 addRandomZombie();
	 }
	 
	 
	
}
