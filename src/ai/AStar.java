package ai;

import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

import engine.Game;
import model.characters.Direction;

public class AStar {
	
	private Cell[][] maze;
	private Point target;
	private Point agent;
	
	public AStar(Cell[][] maze, Point target, Point agent) {
		this.maze = maze;
		this.target = target;
		this.agent = agent;
	}
	
	public HashMap<Direction, Cell> neighbors(Cell state) {
		int currentX = state.getX();
		int currentY = state.getY();
		
		HashMap<Direction, Point> candidates = new HashMap<Direction, Point>();
		candidates.put(Direction.UP, new Point(currentX+1, currentY));
		candidates.put(Direction.DOWN, new Point(currentX-1, currentY));
		candidates.put(Direction.RIGHT, new Point(currentX, currentY+1));
		candidates.put(Direction.LEFT, new Point(currentX, currentY-1));
		
		HashMap<Direction, Cell> results = new HashMap<Direction, Cell>();
		
		Direction action;
		int x, y;

		for (Map.Entry<Direction, Point> entry : candidates.entrySet()) {
			action = entry.getKey();
			x = entry.getValue().x;
			y = entry.getValue().y;
			
			if (checkWithInBounds(x, y) && checkForNewState(x, y, currentX, currentY, action))
				results.put(action, maze[x][y]);
		}
		
		return results;
	}
	
	public boolean checkWithInBounds(int x, int y) {
		return 0 <= x && x < Game.ROWS && 0 <= y && y < Game.COLS;
	}
	
	public boolean checkForNewState(int x, int y, int currentX, int currentY, Direction action) {
		return maze[x][y].getConnected().containsValue(maze[currentX][currentY])
			   || maze[currentX][currentY].getConnected().containsKey(action);
	}

}