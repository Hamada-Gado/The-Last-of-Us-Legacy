package views.images;

import java.util.HashMap;

import javafx.scene.image.Image;
import model.characters.Direction;

public class ImageLoader {
	//TODO remove size constraint to when images are ready
	
	public static final int IMAGE_WIDTH = 48;
	public static final int IMAGE_HEIGHT = 48;
	
	public static final String FIGHTER = "Fighter";
	public static final String EXPLORER = "Explorer";
	public static final String MEDIC = "Medic";
	public static final String ZOMBIE = "Zombie";
	
	public static final String IDLE = "Idle";
	public static final String MOVE = "Move";
	public static final String ATTACK = "Attack";
	public static final String DEATH = "Death";
	public static final String SPECIAL = "Special";
	
	public static final String BASE_PATH = "file:res";
	public static final String[] CHARACTERS = {FIGHTER, EXPLORER, MEDIC, ZOMBIE};
	public static final String[] STATE = {IDLE, MOVE, ATTACK, DEATH, SPECIAL};
	public static final Direction[] DIRECTIONS = {Direction.DOWN, Direction.UP, Direction.LEFT, Direction.RIGHT};

	// character -> direction -> pattern -> image
	private static HashMap<String, HashMap<Direction, HashMap<String, Image>>> characterImages = new HashMap<String, HashMap<Direction, HashMap<String, Image>>>();
	// direction -> pattern -> image
	private static HashMap<Direction, HashMap<String, Image>> fighterImages = new HashMap<Direction, HashMap<String, Image>>();
	private static HashMap<Direction, HashMap<String, Image>> explorerImages = new HashMap<Direction, HashMap<String, Image>>();
	private static HashMap<Direction, HashMap<String, Image>> medicImages = new HashMap<Direction, HashMap<String, Image>>();
	private static HashMap<Direction, HashMap<String, Image>> zombieImages = new HashMap<Direction, HashMap<String, Image>>();

	public static final Image SUPPLY_IMAGE = new Image("file:./res/supply.jpg", 48, 48, false, false);
	public static final Image VACCINE_IMAGE = new Image("file:./res/vaccine.jpg", 48, 48, false, false);
	public static final Image EMPTY_CELL = new Image("file:./res/background.png", 48, 48, false, false);
	public static final Image NOT_VISIBLE_CELL_IMAGE = new Image("file:./res/not visible.png", 48, 48, false, false);
	
	private static Image fighterImage;
	private static Image explorerImage;
	private static Image medicImage;
	private static Image zombieImage;
	
		
	private ImageLoader() {}
	
	public static void update() {
		
	}
	
	public static void get_images() {
		characterImages.put(FIGHTER, fighterImages);
		characterImages.put(EXPLORER, explorerImages);
		characterImages.put(MEDIC, medicImages);
		characterImages.put(ZOMBIE, zombieImages);
		
		get_warrior_images();
		get_explorer_image();
		get_medic_images();
		get_zombie_image();
	}
	
	public static void get_warrior_images() {
		for (Direction direction : DIRECTIONS) {
			fighterImages.put(direction, new HashMap<String, Image>());
			for (String state : STATE) {
				String path = BASE_PATH + "/" + CHARACTERS[0] + "/" + direction + "/" + CHARACTERS[0] + direction + state + ".gif";
				fighterImages.get(direction).put(state, new Image(path, 48, 48, false, false));
			}
		}
		
		fighterImage = fighterImages.get(Direction.RIGHT).get(IDLE);
	}

	public static void get_explorer_image() {
		for (Direction direction : DIRECTIONS) {
			explorerImages.put(direction, new HashMap<String, Image>());
			for (String state : STATE) {
				String path = BASE_PATH + "/" + CHARACTERS[1] + "/" + direction + "/" + CHARACTERS[1] + direction + state + ".gif";
				explorerImages.get(direction).put(state, new Image(path));
			}
		}
		
		explorerImage = explorerImages.get(Direction.RIGHT).get(IDLE);
	}

	public static void get_medic_images() {
		for (Direction direction : DIRECTIONS) {
			medicImages.put(direction, new HashMap<String, Image>());
			for (String state : STATE) {
				String path = BASE_PATH + "/" + CHARACTERS[2] + "/" + direction + "/" + CHARACTERS[2] + direction + state + ".gif";
				medicImages.get(direction).put(state, new Image(path));
			}
		}
		
		medicImage = medicImages.get(Direction.RIGHT).get(IDLE);
	}

	public static void get_zombie_image() {
		for (Direction direction : DIRECTIONS) {
			zombieImages.put(direction, new HashMap<String, Image>());
			for (String state : STATE) {
				String path = BASE_PATH + "/" + CHARACTERS[3] + "/" + direction + "/" + CHARACTERS[3] + direction + state + ".gif";
				zombieImages.get(direction).put(state, new Image(path));
			}
		}
		
		zombieImage = zombieImages.get(Direction.RIGHT).get(IDLE);
	}
	
	public static Image getCharacterImage(String character, Direction direction, String state) {
		return characterImages.get(character).get(direction).get(state);
	}
	
	public static Image getFighterImage() {
		return fighterImage;
	}

	public static Image getExplorerImage() {
		return explorerImage;
	}

	public static Image getMedicImage() {
		return medicImage;
	}

	public static Image getZombieImage() {
		return zombieImage;
	}
	
}
