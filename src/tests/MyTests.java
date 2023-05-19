package tests;

import static org.junit.Assert.fail;

import java.awt.Point;
import java.util.Stack;
import java.util.AbstractMap.SimpleImmutableEntry;

import org.junit.Test;

import ai.AStar;
import ai.Cell;
import model.characters.Direction;

public class MyTests {

	@Test(timeout = 3000)
	public void testAStarAlgorithmRunningWithoutExceptions() {
		
		Cell[][] maze = new Cell[15][15];
		
		for (int x = 0; x < 15; x++) {
			for (int y = 0; y < 15; y++) {
				maze[x][y] = new Cell(x, y);
			}
		}
		
		AStar aStar = new AStar(maze, new Point(5, 5), new Point(0, 0));
		
		try {
			printPath(aStar.sovle());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("An exception occured");
		}
	}
	
	private void printPath(Stack<SimpleImmutableEntry<Direction, Point>> path) {
		Direction d;
		Point p;
		
		while (!path.isEmpty()) {
			d = path.peek().getKey();
			p = path.peek().getValue();
			
			System.out.print("Direction: ");
			System.out.print(d);
			System.out.print(", Move: ");
			System.out.println(p);
			
			path.pop();
		}
		
	}
}
