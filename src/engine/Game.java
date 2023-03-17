package engine;

import java.io.FileReader;
import java.util.ArrayList;
import model.world.Cell;

/**
 *  A class representing the Game itself. This class will represent the main engine of the
 *  game, and will ensure all game rules are followed.
 * @author Ahmed, Mostafa, Rasheed
 *
 */
public class Game {
	
	public static ArrayList<Hero> availableHeros;
	public static ArrayList<Hero> heros;
	public static ArrayList<Zombie> zombies;
	public static Cell [][] map;
	
	public static void loadHeros(String filePath) throws Exception {
		FileReader reader = new FileReader(filePath);
		int data = reader.read();
		String line = "";
		String[] info;
		while(data != -1) {
			if(data != '\r' && data != '\n') {
				line += (char) data;
			} else {
				info = line.split(",");
				switch (info[1]) {
				case "FIGH":
					availableHeros.add(new Fighter(
							info[0], 
							Integer.parseInt(info[2]), 
							Integer.parseInt(info[4]), 
							Integer.parseInt(info[3])
							));
					break;
				case "EXP":
					availableHeros.add(new Explorer(
							info[0], 
							Integer.parseInt(info[2]), 
							Integer.parseInt(info[4]), 
							Integer.parseInt(info[3])
							));
					break;
				case "MED":
					availableHeros.add(new Medic(
							info[0], 
							Integer.parseInt(info[2]), 
							Integer.parseInt(info[4]), 
							Integer.parseInt(info[3])
							));
					break;
				}
				line = "";
			}
			
			data = reader.read();
			if(data == '\n') data = reader.read();
		}
		
		reader.close();
	}

}
