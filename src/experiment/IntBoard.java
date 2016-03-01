package experiment;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

public class IntBoard {
	
	private static final int X_DIMENSION = 4;
	private static final int Y_DIMENSION = 4;
	
	private static final int START_X_LOCATION = 1;
	private static final int START_Y_LOCATION = 1;
	
	private Map<BoardCell, LinkedList<BoardCell>> adjacentCells;
	private Set<BoardCell> visited;
	private Set<BoardCell> targets;
	private BoardCell[][] grid;
	
	private BoardCell currentLocation;
	
	public BoardCell getCurrentLocation() {
		return currentLocation;
	}

	public void setCurrentLocation(BoardCell currentLocation) {
		this.currentLocation = currentLocation;
	}
	
	public BoardCell getCell(int x, int y) {
		return grid[x][y];
	}
	
	public void calcAdjacencies() {
		
		for (int i = 0; i < X_DIMENSION; ++i) {
			for (int j = 0; j < Y_DIMENSION; ++j) {
				BoardCell currentCell = getCell(i, j);
				LinkedList<BoardCell> currentAdjacentCells = new LinkedList<BoardCell>();
				if (i < X_DIMENSION - 1) {
					currentAdjacentCells.add(getCell(i + 1, j));
				}
				if (i > 0) {
					currentAdjacentCells.add(getCell(i - 1, j));
				}
				if (j < Y_DIMENSION - 1) {
					currentAdjacentCells.add(getCell(i, j + 1));
				}
				if (j > 0) {
					currentAdjacentCells.add(getCell(i, j - 1));
				}
				
				adjacentCells.put(currentCell, currentAdjacentCells);
			}
			
		}
	}

	
	public void calcTargets(BoardCell startCell, int pathLength){
		visited.add(startCell);
		findAllTargets(startCell, pathLength);
		visited.remove(startCell);
	}
	
	public void findAllTargets(BoardCell startCell, int pathLength) {
		// Get cells adjacent to the current one
				LinkedList<BoardCell> adjCells = new LinkedList<BoardCell>(getAdjList(startCell));
				// Remove those that have been visited already
				for (BoardCell c: visited) {
					if (adjCells.contains(c)) {
						adjCells.remove(c);
					}
				}
				
				for (BoardCell c: adjCells) {
					visited.add(c);
					if (pathLength == 1) {
						targets.add(c);
					}
					else {
						calcTargets(c, pathLength - 1);
					}
					visited.remove(c);
				}
	}

	public Set<BoardCell> getTargets() {
		return targets;
	}
	
	public Set<BoardCell> getVisited() {
		return visited;
	}
	
	public LinkedList<BoardCell> getAdjList(BoardCell cell) {
		return adjacentCells.get(cell);
	}
	
	public Map<BoardCell, LinkedList<BoardCell>> getAdjacentCells() {
		return adjacentCells;
	}
	
	public IntBoard(int x_dimension, int y_dimension) {
		grid = new BoardCell[x_dimension][y_dimension];
		for (int i = 0; i < x_dimension; ++i) {
			for (int j = 0; j < y_dimension; ++j) {
				grid[i][j] = new BoardCell(i, j);
			}
		}
		setCurrentLocation(new BoardCell(START_X_LOCATION,START_Y_LOCATION));
		adjacentCells = new HashMap<BoardCell, LinkedList<BoardCell>>();
		calcAdjacencies();
		visited = new HashSet<BoardCell>();
		targets = new HashSet<BoardCell>();
	}
	
	public static void main (String[] args) {
		
	}

	
	
	
}
