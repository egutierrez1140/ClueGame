package clueGame;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

public class Board {
	private int numRows;
	private int numColumns;
	public static int BOARD_SIZE;
	
	BoardCell[][] board;
	Map<Character, String> rooms;
	
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
		loadRoomConfig();
		loadBoardConfig();
	}
	
	public void loadRoomConfig() {
		
	}
	
	public void loadBoardConfig() {
		
	}
	
	public void calcAdjacencies() {
		
	}
	
	public void calcTargets(BoardCell cell, int pathLength) {
		
	}
	
	public LinkedList<BoardCell> getAdjList(int row, int column) {
		return new LinkedList<BoardCell>();
	}
	
	public BoardCell getCellAt(int row, int column) {
		return new BoardCell(0,0);
	}
	
	public void calcTargets(int row, int column, int steps) {
		
	}
	
	public Set<BoardCell> getTargets() {
		return new HashSet<BoardCell>();
	}
	
	public static Map<Character, String> getRooms() {
		return new HashMap<Character, String>();
	}

	public int getNumRows() {
		return 0;
	}

	public int getNumColumns() {
		return 0;
	}
	
}
