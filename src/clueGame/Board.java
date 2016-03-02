package clueGame;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;
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
		try {
			loadRoomConfig();
		}
		catch (BadConfigFormatException b) {
			System.out.println("BadConfigFormatException");
		}
		catch (FileNotFoundException f) {
			System.out.println("FileNotFoundException");
		}
		
		
		try {
			loadBoardConfig();
		} catch (FileNotFoundException | BadConfigFormatException e) {
			e.printStackTrace();
		}
	}
	
	public void loadRoomConfig() throws BadConfigFormatException, FileNotFoundException {
		FileReader reader = new FileReader(roomConfigFile);
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(reader);
		while (scanner.hasNextLine()) {
			String next = scanner.nextLine();
			String[] parts = next.split(",");
			if (parts.length != 3) {
				throw new BadConfigFormatException();
			}
			rooms.put(parts[0].charAt(0), parts[1].substring(1));
		}
		scanner.close();
	}
	
	public void loadBoardConfig() throws BadConfigFormatException, FileNotFoundException {
		numColumns = 0;
		numRows = 0;
		FileReader reader = new FileReader(boardConfigFile);
		Scanner scanner = new Scanner(reader);
		int r = 0; int c = 0;
		while (scanner.hasNextLine()) {
			String next = scanner.nextLine();
			String[] parts = next.split(",");
			
			if (numColumns == 0) {numColumns = parts.length;}
			else if (numColumns != parts.length) { throw new BadConfigFormatException();}
			
			for (int i = 0; i < parts.length; ++i) {
				//System.out.println(parts[i].charAt(0));
				//System.out.println("Cell: " + x + ", " + y);
				if (parts[i] == null) {throw new BadConfigFormatException();}
				board[r][c] = new BoardCell(r, c, parts[i].charAt(0));
				if (parts[i].length() > 1 && parts[i].charAt(1) != 'N') {
					board[r][c].setDoorway(true);
					if (parts[i].charAt(1) == 'U') {
						board[r][c].setDoorDirection(DoorDirection.UP);
					}
					if (parts[i].charAt(1) == 'D') {
						board[r][c].setDoorDirection(DoorDirection.DOWN);
					}
					if (parts[i].charAt(1) == 'L') {
						board[r][c].setDoorDirection(DoorDirection.LEFT);
					}
					if (parts[i].charAt(1) == 'R') {
						board[r][c].setDoorDirection(DoorDirection.RIGHT);
					}
				}
				
				System.out.println("Cell(" + c + "," + r + ") = " + this.getCellAt(c,r));
				c++;
			}
			c = 0;
			r++;
			numRows++;
		}
		scanner.close();
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
		return board[row][column];
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
