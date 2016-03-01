package clueGame;

public class BoardCell {
	private int row;
	private int column;
	private char initial;
	private DoorDirection doorDirection;
	
	boolean walkway;
	boolean room;
	boolean doorway;
	
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
		return null;
	}
	
	public char getInitial() {
		return initial;
	}
}
