package clueGame;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

public class Board {
	private int numRows;
	private int numColumns;
	public static int BOARD_SIZE = 50;
	
	BoardCell[][] board;
	static Map<Character, String> rooms;
	
	Map<BoardCell, LinkedList<BoardCell>> adjMatrix;
	Set<BoardCell> targets;
	String boardConfigFile;
	String roomConfigFile;
	
	public Board() {
		this.boardConfigFile = "ClueLayout.csv";
		this.roomConfigFile = "ClueLegend.txt";
	}
	
	public Board(String boardFileName, String roomFileName) {
		this.boardConfigFile = boardFileName;
		this.roomConfigFile = roomFileName;
	}
	
	// initialize() will call loadRoomConfig() and loadBoardConfig()
	public void initialize() {
		board = new BoardCell[BOARD_SIZE][BOARD_SIZE];
		rooms = new HashMap<Character, String>();
		targets = new HashSet<BoardCell>();
		loadRoomConfig();
		loadBoardConfig();
	}
	
	public void loadRoomConfig() {
		
	}
	
	public void loadBoardConfig() {
		
	}
	
	public void calcAdjacencies() {
		
		for (int i = 0; i < numColumns; ++i) {
			for (int j = 0; j < numRows; ++j) {
				BoardCell currentCell = getCellAt(i, j);
				LinkedList<BoardCell> currentAdjacentCells = new LinkedList<BoardCell>();
				if (i < numColumns - 1) {
					currentAdjacentCells.add(getCellAt(i + 1, j));
				}
				if (i > 0) {
					currentAdjacentCells.add(getCellAt(i - 1, j));
				}
				if (j < numRows - 1) {
					currentAdjacentCells.add(getCellAt(i, j + 1));
				}
				if (j > 0) {
					currentAdjacentCells.add(getCellAt(i, j - 1));
				}
				
				adjMatrix.put(currentCell, currentAdjacentCells);
			}
			
		}
	}
	
	public void calcTargets(BoardCell cell, int pathLength) {
		
	}
	
	public LinkedList<BoardCell> getAdjList(int row, int column) {
		return adjMatrix.get(getCellAt(column, row));
	}
	
	public BoardCell getCellAt(int row, int column) {
		return board[column][row];
	}
	
	public void calcTargets(int row, int column, int steps) {
		
	}
	
	public Set<BoardCell> getTargets() {
		return targets;
	}
	
	public static Map<Character, String> getRooms() {
		return rooms;
	}

	public int getNumRows() {
		return numRows;
	}

	public int getNumColumns() {
		return numColumns;
	}
	
}
