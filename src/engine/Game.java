package engine;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import model.characters.Hero;
import model.characters.Explorer;
import model.characters.Fighter;
import model.characters.Medic;

import model.characters.Zombie;

import model.world.Cell;

/**
 *  A class representing the Game itself. This class will represent the main engine of the
 *  game, and will ensure all game rules are followed.
 * @author Ahmed, Mostafa, Rasheed
 *
 */
public class Game {
	
	public static ArrayList<Hero> availableHeroes = new ArrayList<Hero>();;
	public static ArrayList<Hero> heroes = new ArrayList<Hero>();;
	public static ArrayList<Zombie> zombies = new ArrayList<Zombie>();;
	public static Cell [][] map;
	
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

}
