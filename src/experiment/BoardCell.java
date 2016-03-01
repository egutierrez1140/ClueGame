package experiment;

public class BoardCell {
	
	private int column;
	private int row;
	
	public BoardCell(int column, int row) {
		this.column = column;
		this.row = row;
	}

	@Override
	public String toString() {
		return "BoardCell [column=" + column + ", row=" + row + "]";
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}
	
	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}
	
}
