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
		rooms = new HashMap<Character, String>();
		targets = new HashSet<BoardCell>();
		adjMatrix = new HashMap<BoardCell, LinkedList<BoardCell>>();
		
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
		}
		catch (FileNotFoundException | BadConfigFormatException e) {
			e.printStackTrace();
		}
		
		calcAdjacencies();
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
		board = new BoardCell[BOARD_SIZE][BOARD_SIZE];
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
				//System.out.println("r = " + r + ", c = " + c);
				
				if (!rooms.containsKey(parts[i].charAt(0))) {
					throw new BadConfigFormatException();
				}
				
				board[r][c] = new BoardCell(r, c, parts[i].charAt(0));
				if (Character.toString(parts[i].charAt(0)).equals("W")) {
					board[r][c].setWalkway(true);
				}
				else {
					board[r][c].setRoom(true);
				}
				
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
				//System.out.println("Cell(" + r + "," + c + ") = " + this.getCellAt(r,c));
				c++;
			}
			c = 0;
			r++;
			numRows++;
		}
		scanner.close();
	}
	
	public void calcAdjacencies() {
		
		for (int i = 0; i < numRows; ++i) {
			for (int j = 0; j < numColumns; ++j) {
				
				BoardCell currentCell = getCellAt(i, j);
				LinkedList<BoardCell> currentAdjacentCells = new LinkedList<BoardCell>();
				//System.out.println(currentCell.isWalkway());
				//System.out.println(currentCell.isDoorway());
				
				if (i < numRows - 1) {
					BoardCell downCell = getCellAt(i + 1, j);
					if ((!currentCell.isRoom() || currentCell.isDoorway()) && downCell.isWalkway() || (downCell.isDoorway() && !currentCell.isDoorway() && downCell.getDoorDirection().equals(DoorDirection.UP))) {
						currentAdjacentCells.add(getCellAt(i + 1, j));
					}
				}
				if (i > 0) {
					BoardCell upCell = getCellAt(i - 1, j);
					if ((!currentCell.isRoom() || currentCell.isDoorway()) && upCell.isWalkway() || (upCell.isDoorway() && !currentCell.isDoorway() && upCell.getDoorDirection().equals(DoorDirection.DOWN))) {
						currentAdjacentCells.add(getCellAt(i - 1, j));
					}
				}
				if (j < numColumns - 1) {
					BoardCell rightCell = getCellAt(i, j + 1);
					if ((!currentCell.isRoom() || currentCell.isDoorway()) && rightCell.isWalkway() || (rightCell.isDoorway() && !currentCell.isDoorway() && rightCell.getDoorDirection().equals(DoorDirection.LEFT))) {
						currentAdjacentCells.add(getCellAt(i, j + 1));
					}
				}
				if (j > 0) {
					BoardCell leftCell = getCellAt(i, j - 1);
					if ((!currentCell.isRoom() || currentCell.isDoorway()) && leftCell.isWalkway() || (leftCell.isDoorway() && !currentCell.isDoorway() && leftCell.getDoorDirection().equals(DoorDirection.RIGHT))) {
						currentAdjacentCells.add(getCellAt(i, j - 1));
					}
				}
				
				adjMatrix.put(currentCell, currentAdjacentCells);
			}
		}
	}
	
	public void calcTargets(BoardCell cell, int pathLength) {
		
	}
	
	public LinkedList<BoardCell> getAdjList(int row, int column) {
		return adjMatrix.get(getCellAt(row, column));
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
