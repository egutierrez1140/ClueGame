package clueGame;

public class BoardCell {
	private int row;
	private int column;
	private char initial;
	private DoorDirection doorDirection;
	
	boolean walkway = false;
	boolean room = false;
	boolean doorway = false;
	
	@Override
	public String toString() {
		return "BoardCell [row=" + row + ", column=" + column + "]" + initial;
	}

	public void setDoorDirection(DoorDirection doorDirection) {
		this.doorDirection = doorDirection;
	}

	public void setWalkway(boolean walkway) {
		this.walkway = walkway;
	}

	public void setRoom(boolean room) {
		this.room = room;
	}

	public void setDoorway(boolean doorway) {
		this.doorway = doorway;
	}
	
	public BoardCell(int row, int column, char i) {
		this.row = row;
		this.column = column;
		this.initial = i;
	}
	
	public boolean isWalkway() {
		return walkway;
	}
	
	public boolean isRoom() {
		return room;
	}
	
	public boolean isDoorway() {
		return doorway;
	}
	
	public DoorDirection getDoorDirection() {
		return doorDirection;
	}
	
	public char getInitial() {
		return initial;
	}
	
	public int getRow() {
		return row;
	}
	
	public int getColumn() {
		return column;
	}
}
