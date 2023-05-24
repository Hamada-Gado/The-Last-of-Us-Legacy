package tests;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.awt.Point;
import java.util.Stack;
import java.util.AbstractMap.SimpleImmutableEntry;

import org.junit.Test;

import ai.path_finding.AStar;
import ai.path_finding.Cell;
import model.characters.Direction;

public class MyTests {
	
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

	@Test(timeout = 3000)
	public void testAStarAlgorithmRunningWithoutExceptions() {
		
		Cell[][] maze = new Cell[15][15];
		
		for (int x = 0; x < 15; x++) {
			for (int y = 0; y < 15; y++) {
				maze[x][y] = new Cell(x, y);
			}
		}
		
		AStar aStar = new AStar(maze, new Point(0, 0), new Point(5, 5));
		
		try {
			aStar.sovle();
//			printPath(aStar.getPath());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("An exception occured");
		}
	}
	
	@Test(timeout = 6000)
	public void testAStarAlgorithmTakeShortestPath() {
		
		Cell[][] maze = new Cell[15][15];
		
		for (int x = 0; x < 15; x++) {
			for (int y = 0; y < 15; y++) {
				maze[x][y] = new Cell(x, y);
			}
		}
		
		AStar aStar = new AStar(maze, new Point(0, 0), new Point(3, 7));
		
		maze[0][1].setOccupied(true);
		maze[1][1].setOccupied(true);
		maze[1][2].setOccupied(true);
		maze[2][2].setOccupied(true);
		maze[3][3].setOccupied(true);
		maze[5][5].setOccupied(true);
		maze[4][6].setOccupied(true);
		maze[3][6].setOccupied(true);
		maze[2][6].setOccupied(true);
		maze[1][6].setOccupied(true);
		
		int pathLength = 0;
		
		try {
			aStar.sovle();
			pathLength = aStar.getPath().size();
//			printPath(aStar.getPath());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("An exception occured");
		}
		
		assertTrue("Did not find the best path expected <16> but got: <" + pathLength + ">", pathLength == 16);
		
	}
	
	@Test(timeout = 6000)
	public void testAStarAlgorithmTakeCollectVaccineAlongTheWay() {
		
		Cell[][] maze = new Cell[15][15];
		
		for (int x = 0; x < 15; x++) {
			for (int y = 0; y < 15; y++) {
				maze[x][y] = new Cell(x, y);
			}
		}
		
		AStar aStar = new AStar(maze, new Point(0, 0), new Point(3, 7));
		
		maze[7][4].setContainsVaccine(true);
		
		maze[0][1].setOccupied(true);
		maze[1][1].setOccupied(true);
		maze[1][2].setOccupied(true);
		maze[2][2].setOccupied(true);
		maze[3][3].setOccupied(true);
		maze[5][5].setOccupied(true);
		maze[4][6].setOccupied(true);
		maze[3][6].setOccupied(true);
		maze[2][6].setOccupied(true);
		maze[1][6].setOccupied(true);
		
		try {
			aStar.sovle();
//			printPath(aStar.getPath());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("An exception occured");
		}
	
		aStar.getPath().forEach(t -> {
			Point p = new Point(7, 4);
			if (t.getValue().equals(p)) assertTrue(true);});
	}
}
