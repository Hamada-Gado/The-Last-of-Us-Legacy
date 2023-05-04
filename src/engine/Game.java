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
	public static int vaccinesUsed = 0;

	
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
	
	public static void addRandomZombie() {
		Random randGen = new Random();
		
		int x, y;
		Cell c;
		
		while(true) {
			x = randGen.nextInt(WIDTH);
			y = randGen.nextInt(HEIGHT);
			c = map[y][x];
			
			if (!(c instanceof CharacterCell)) continue;
			if (((CharacterCell) c).getCharacter() != null) continue;
			
			Zombie z = new Zombie();
			z.setLocation(new Point(y, x));
			zombies.add(z);
			((CharacterCell) c).setCharacter(z);
			((CharacterCell) c).setSafe(false);
			
			break;
		}
	}
	
	public static void addRandomCollectible(Collectible collectible) {
		Random randGen = new Random();
		
		int x, y;
		Cell c;
		
		while(true) {
			x = randGen.nextInt(WIDTH);
			y = randGen.nextInt(HEIGHT);
			c = map[y][x];
			
			if (!(c instanceof CharacterCell)) continue;
			if (((CharacterCell) c).getCharacter() != null) continue;
			
			map[y][x] = new CollectibleCell(collectible);
			
			break;
		}
	}
	
	public static void addRandomTrap() {
		Random randGen = new Random();
		
		int x, y;
		Cell c;
		
		while(true) {
			x = randGen.nextInt(WIDTH);
			y = randGen.nextInt(HEIGHT);
			c = map[y][x];
			
			if (!(c instanceof CharacterCell)) continue;
			if (((CharacterCell) c).getCharacter() != null) continue;
			
			map[y][x] = new TrapCell();
			
			break;
		}
	}
	
	public static void startGame(Hero h) {
		
		for (int x = 0; x < WIDTH; x++) {
			for (int y = 0; y < HEIGHT; y++) {
				map[y][x] = new CharacterCell(null);
			}
		}
		
		h.setLocation(new Point(0, 0));
		heroes.add(h);
		availableHeroes.remove(h);
		map[0][0] = new CharacterCell(h, true);
		h.makeCellVisible(0, 0);
		h.makeAdjacentCellsVisible();
		for (int i = 0; i < 5; i++) {
			addRandomCollectible(new Vaccine());
			addRandomCollectible(new Supply());
			addRandomTrap();
		}
		
		for (int i = 0; i < 10; i++) addRandomZombie();
		
	}
	
	public static boolean checkWin() {
		return vaccinesUsed == 5 && heroes.size() >= 5;
	}
	
	public static boolean checkGameOver() {
		return vaccinesUsed == 5 || heroes.isEmpty();
	}
	
	 public static void endTurn() {
		 for (Zombie z : zombies) {
			 z.setTarget(null);
			 try {
				z.attack();
			} catch (InvalidTargetException | NotEnoughActionsException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
		 
		 for (Cell[] cells : map) {
			 for (Cell c : cells) {
				 c.setVisible(false);
			 }
		 }
		 
		 for (Hero h : heroes) {
			 h.setActionsAvailable(h.getMaxActions());
			 h.setTarget(null);
			 h.setSpecialAction(false);
			 h.makeAdjacentCellsVisible();
		 }
		 
		 addRandomZombie();
	 }
	 
	 
	
}
