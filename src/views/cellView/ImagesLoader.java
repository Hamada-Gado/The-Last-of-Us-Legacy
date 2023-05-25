package views.cellView;

import java.util.HashMap;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class ImagesLoader {
	
	public static final int IMAGE_WIDTH = 48;
	public static final int IMAGE_HEIGHT = 48;
	
	public static final String EXPLORER = "Explorer";
	public static final String MEDIC = "Medic";
	public static final String WARRIOR = "Warrior";
	public static final String ZOMBIE = "Zombie";
	
	public static final String IDLE = "Idle";
	public static final String WALK = "Walk";
	public static final String ATTACK = "Attack";
	public static final String HURT = "Hurt";
	public static final String DEATH = "Death";
	
	public static final String UP = "Up";
	public static final String DOWN = "Down";
	public static final String LEFT = "Left";
	public static final String RIGHT = "Right";
	
	public static final String BASE_PATH = "file:res";
	public static final String[] CHARACTERS = {WARRIOR, EXPLORER, MEDIC, ZOMBIE};
	public static final String[] POSITION = {IDLE, WALK, ATTACK, HURT, DEATH};
	public static final String[] DIRECTIONS = {DOWN, UP, LEFT, RIGHT};
	
	// direction -> pattern -> image
	private static HashMap<String, HashMap<String, Image[]>> warriorImages = new HashMap<String, HashMap<String, Image[]>>();
	private static HashMap<String, HashMap<String, Image[]>> explorerImages = new HashMap<String, HashMap<String, Image[]>>();
	private static HashMap<String, HashMap<String, Image[]>> medicImages = new HashMap<String, HashMap<String, Image[]>>();
	private static HashMap<String, HashMap<String, Image[]>> zombieImages = new HashMap<String, HashMap<String, Image[]>>();
	
	private static Image warriorImage;
	private static Image explorerImage;
	private static Image medicImage;
	private static Image zombieImage;
	
	private static int frame_counter = 0;
	private static int current_frame = 1;
	
	private static String direction;
	private static String current_postion;
	
	private ImagesLoader() {}
	
	public static void update() {
		
	}
	
	public static Image[] get_farmes_of_image(int frames, int w, int h,  String pathFile ) {

        Image[] imgs =  new Image[ frames ];

        //img that contains all frames
        Image stripImg = new Image( pathFile );
        PixelReader pr =  stripImg.getPixelReader();
        PixelWriter pw = null;

        for( int i = 0; i < frames ; i++) {

            WritableImage wImg = new WritableImage( w, h );

            pw = wImg.getPixelWriter();

            for( int readY = 0 ; readY < h; readY++ )
            {

                int ww = (w * i);
                for( int readX = ww; readX < ww + w; readX++ )
                {
                    //get pixel at X  & Y position
                    Color color = pr.getColor( readX, readY );

                    //set pixel to writableimage through pixel writer
                    pw.setColor(readX - ww, readY, color);

                }//X


            }//Y


            //finally new image is stored
            imgs[ i ] = wImg;
        }


        stripImg = null;
        pr = null;
        pw = null;
         
        return imgs;
    }
	
	public static void get_images() {
		get_warrior_images();
		get_explorer_image();
		get_medic_images();
		get_zombie_image();
	}
	
	public static void get_warrior_images() {
		for (String direction : DIRECTIONS) {
			warriorImages.put(direction, new HashMap<String, Image[]>());
			for (String position : POSITION) {
				String path = BASE_PATH + "/" + CHARACTERS[0] + "/" + direction + "/" + CHARACTERS[0] + direction + position + ".png";
				
				Image im = new Image(path);
				int frames = (int) (im.getWidth() / 48);
				
				warriorImages.get(direction).put(position, get_farmes_of_image(frames, IMAGE_WIDTH, IMAGE_HEIGHT, path));
			}
		}
		
		warriorImage = warriorImages.get(RIGHT).get(IDLE)[0];
	}

	public static void get_explorer_image() {
		for (String direction : DIRECTIONS) {
			explorerImages.put(direction, new HashMap<String, Image[]>());
			for (String position : POSITION) {
				String path = BASE_PATH + "/" + CHARACTERS[1] + "/" + direction + "/" + CHARACTERS[1] + direction + position + ".png";
				
				Image im = new Image(path);
				int frames = (int) (im.getWidth() / 48);
				
				explorerImages.get(direction).put(position, get_farmes_of_image(frames, IMAGE_WIDTH, IMAGE_HEIGHT, path));
			}
		}
		
		explorerImage = explorerImages.get(RIGHT).get(IDLE)[0];
	}

	public static void get_medic_images() {
		for (String direction : DIRECTIONS) {
			medicImages.put(direction, new HashMap<String, Image[]>());
			for (String position : POSITION) {
				String path = BASE_PATH + "/" + CHARACTERS[2] + "/" + direction + "/" + CHARACTERS[2] + direction + position + ".png";
				
				Image im = new Image(path);
				int frames = (int) (im.getWidth() / 48);
				
				medicImages.get(direction).put(position, get_farmes_of_image(frames, IMAGE_WIDTH, IMAGE_HEIGHT, path));
			}
		}
		
		medicImage = medicImages.get(RIGHT).get(IDLE)[0];
	}

	public static void get_zombie_image() {
		for (String direction : DIRECTIONS) {
			zombieImages.put(direction, new HashMap<String, Image[]>());
			for (String position : POSITION) {
				String path = BASE_PATH + "/" + CHARACTERS[3] + "/" + direction + "/" + CHARACTERS[3] + direction + position + ".png";

				Image im = new Image(path);
				int frames = (int) (im.getWidth() / 48);
				
				
				zombieImages.get(direction).put(position, get_farmes_of_image(frames, IMAGE_WIDTH, IMAGE_HEIGHT, path));
			}
		}
		
		zombieImage = zombieImages.get(RIGHT).get(IDLE)[0];
	}

	public static Image getWarriorImage() {
		return warriorImage;
	}

	public static void setWarriorImage(Image warriorImage) {
		ImagesLoader.warriorImage = warriorImage;
	}

	public static Image getExplorerImage() {
		return explorerImage;
	}

	public static void setExplorerImage(Image explorerImage) {
		ImagesLoader.explorerImage = explorerImage;
	}

	public static Image getMedicImage() {
		return medicImage;
	}

	public static void setMedicImage(Image medicImage) {
		ImagesLoader.medicImage = medicImage;
	}

	public static Image getZombieImage() {
		return zombieImage;
	}

	public static void setZombieImage(Image zombieImage) {
		ImagesLoader.zombieImage = zombieImage;
	}
	
}
