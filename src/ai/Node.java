package ai;

import java.awt.Point;

import model.characters.Direction;

public class Node implements Comparable<Node> {
	
	private Cell state;
	private Point target;
	private Node parent;
	private int steps;
	private int gain;
	private Direction action;
	
	public Node(Cell state, Point target, Node parent, Direction action) {
		this.state = state;
		this.target = target;
		this.parent = parent;
		this.steps = parent == null ? 0 : parent.steps + 1;
		this.action = action;
		
		gain = parent == null ? 0 : parent.gain;
		gain += state.isContainsVaccine() || state.isContainsSupply() ? -10 : 0;
	}
	
	public int cost() {
		return steps;
	}
	
	public int heuristic() {
		return Math.abs(state.getX() - target.x) + Math.abs(state.getY() - target.y);
	}
	
	public int evaluation() {
		return cost() + heuristic() + gain;
	}
	
	public Cell getState() {
		return state;
	}
	
	public Node getParent() {
		return parent;
	}
	
	public Direction getAction() {
		return action;
	}

	@Override
	public int compareTo(Node o) {
		return evaluation() - o.evaluation();
	}
	
	@Override
	public String toString() {
		return "Direction: " + (action == null ? "NONE" : action.toString()) + ", Point: " + state.getPoint().toString();
	}

}
