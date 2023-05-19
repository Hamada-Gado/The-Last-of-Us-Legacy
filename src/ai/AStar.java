package ai;

import java.awt.Point;
import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Stack;

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
	
	public boolean checkWithInBounds(int x, int y) {
		return 0 <= x && x < Game.ROWS && 0 <= y && y < Game.COLS;
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
			
			if (checkWithInBounds(x, y))
				results.put(action, maze[x][y]);
		}
		
		return results;
	}
	
	public Stack<SimpleImmutableEntry<Direction, Point>> sovle() throws Exception {
		
		Node start = new Node(maze[agent.x][agent.y], target, null, null);
		PriorityQueue<Node> frontier = new PriorityQueue<Node>();
		frontier.add(start);
		
		HashSet<Cell> explored = new HashSet<Cell>();
		
		Node node;
		Stack<SimpleImmutableEntry<Direction, Point>> path;
		
		while (true) {

			if (frontier.isEmpty()) {
				throw new Exception("No solution!!!");
			}
			
			node = frontier.poll();
			
			if (node.getState().getPoint().equals(target)) {
				path = new Stack<SimpleImmutableEntry<Direction, Point>>();
				
				while (node.getParent() != null) {
					path.add(new SimpleImmutableEntry<Direction, Point>(node.getAction(), node.getState().getPoint()));
					node = node.getParent();
				}
				
				return path;
			}
			
			explored.add(node.getState());
			
			for (Map.Entry<Direction, Cell> entry : neighbors(node.getState()).entrySet()) {
				if (!explored.contains(entry.getValue())) {
					frontier.add(new Node(entry.getValue(), target, node, entry.getKey()));
				}
			}
				
		}
		
	}

}