package experiment;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

public class IntBoardTests {
	
	private static final int X_DIMENSION = 4;
	private static final int Y_DIMENSION = 4;
	
	IntBoard board;
	
	@Before
	public void setUp() {
		board = new IntBoard(X_DIMENSION,Y_DIMENSION);
	}
	
	// Adjacency tests
	@Test
	public void testTopLeftCorner_Adj() {
		BoardCell cell = board.getCell(0, 0);
		LinkedList<BoardCell> testList = board.getAdjList(cell);
		assertTrue(testList.contains(board.getCell(1, 0)));
		assertTrue(testList.contains(board.getCell(0, 1)));
		assertEquals(2, testList.size());
	}
	
	@Test
	public void testBottomRightCorner_Adj() {
		BoardCell cell = board.getCell(3,3);
		LinkedList<BoardCell> testList = board.getAdjList(cell);
		assertTrue(testList.contains(board.getCell(3,2)));
		assertTrue(testList.contains(board.getCell(2,3)));
		assertEquals(2, testList.size());
	}
	
	@Test
	public void testRightEdge_Adj() {
		BoardCell cell = board.getCell(3,1);
		LinkedList<BoardCell> testList = board.getAdjList(cell);
		assertTrue(testList.contains(board.getCell(3,0)));
		assertTrue(testList.contains(board.getCell(2,1)));
		assertTrue(testList.contains(board.getCell(3,2)));
		assertEquals(3, testList.size());
	}
	
	@Test
	public void testLeftEdge_Adj() {
		BoardCell cell = board.getCell(0,2);
		LinkedList<BoardCell> testList = board.getAdjList(cell);
		assertTrue(testList.contains(board.getCell(0,1)));
		assertTrue(testList.contains(board.getCell(1,2)));
		assertTrue(testList.contains(board.getCell(0,3)));
		assertEquals(3, testList.size());
	}
	
	@Test
	public void testSecondColumnMiddle_Adj() {
		BoardCell cell = board.getCell(1,1);
		LinkedList<BoardCell> testList = board.getAdjList(cell);
		assertTrue(testList.contains(board.getCell(1,0)));
		assertTrue(testList.contains(board.getCell(0,1)));
		assertTrue(testList.contains(board.getCell(1,2)));
		assertTrue(testList.contains(board.getCell(2,1)));
		assertEquals(4, testList.size());
	}
	
	@Test
	public void testSecondLastColumnMiddle_Adj() {
		BoardCell cell = board.getCell(2,2);
		LinkedList<BoardCell> testList = board.getAdjList(cell);
		assertTrue(testList.contains(board.getCell(2,1)));
		assertTrue(testList.contains(board.getCell(1,2)));
		assertTrue(testList.contains(board.getCell(2,3)));
		assertTrue(testList.contains(board.getCell(3,2)));
		assertEquals(4, testList.size());
	}
	
	// Target tests
	@Test
	public void testTopLeftCorner_Target() {
		BoardCell cell = board.getCell(0, 0);
		board.calcTargets(cell, 3);
		Set<BoardCell> targets = board.getTargets();
		assertEquals(6, targets.size());
		assertTrue(targets.contains(board.getCell(0, 3)));
		assertTrue(targets.contains(board.getCell(2, 1)));
		assertTrue(targets.contains(board.getCell(1, 2)));
		assertTrue(targets.contains(board.getCell(0, 3)));
		assertTrue(targets.contains(board.getCell(0, 1)));
		assertTrue(targets.contains(board.getCell(1, 0)));
	}
	
	@Test
	public void testBottomRightCorner_Target() {
		BoardCell cell = board.getCell(3, 3);
		board.calcTargets(cell, 4);
		Set<BoardCell> targets = board.getTargets();
		assertEquals(6, targets.size());
		assertTrue(targets.contains(board.getCell(2, 0)));
		assertTrue(targets.contains(board.getCell(1, 1)));
		assertTrue(targets.contains(board.getCell(3, 1)));
		assertTrue(targets.contains(board.getCell(0, 2)));
		assertTrue(targets.contains(board.getCell(2, 2)));
		assertTrue(targets.contains(board.getCell(1, 3)));
	}
	
	@Test
	public void testRightEdge_Target() {
		BoardCell cell = board.getCell(3, 1);
		board.calcTargets(cell, 1);
		Set<BoardCell> targets = board.getTargets();
		assertEquals(3, targets.size());
		assertTrue(targets.contains(board.getCell(3, 0)));
		assertTrue(targets.contains(board.getCell(2, 1)));
		assertTrue(targets.contains(board.getCell(3, 2)));
	}
	
	@Test
	public void testLeftEdge_Target() {
		BoardCell cell = board.getCell(0, 2);
		board.calcTargets(cell, 2);
		Set<BoardCell> targets = board.getTargets();
		assertEquals(4, targets.size());
		assertTrue(targets.contains(board.getCell(0, 0)));
		assertTrue(targets.contains(board.getCell(1, 1)));
		assertTrue(targets.contains(board.getCell(2, 2)));
		assertTrue(targets.contains(board.getCell(1, 3)));
	}
	
	@Test 
	public void testSecondColumnMiddle_Target() {
		BoardCell cell = board.getCell(1, 1);
		board.calcTargets(cell, 6);
		Set<BoardCell> targets = board.getTargets();
		assertEquals(7, targets.size());
		assertTrue(targets.contains(board.getCell(0, 0)));
		assertTrue(targets.contains(board.getCell(2, 0)));
		assertTrue(targets.contains(board.getCell(3, 1)));
		assertTrue(targets.contains(board.getCell(0, 2)));
		assertTrue(targets.contains(board.getCell(2, 2)));
		assertTrue(targets.contains(board.getCell(1, 3)));
		assertTrue(targets.contains(board.getCell(3, 3)));
	}
	
	@Test
	public void testSecondLastColumnMiddle_Target() {
		BoardCell cell = board.getCell(2, 2);
		board.calcTargets(cell, 5);
		Set<BoardCell> targets = board.getTargets();
		assertEquals(8, targets.size());
		assertTrue(targets.contains(board.getCell(1, 0)));
		assertTrue(targets.contains(board.getCell(3, 0)));
		assertTrue(targets.contains(board.getCell(0, 1)));
		assertTrue(targets.contains(board.getCell(2, 1)));
		assertTrue(targets.contains(board.getCell(1, 2)));
		assertTrue(targets.contains(board.getCell(3, 2)));
		assertTrue(targets.contains(board.getCell(0, 3)));
		assertTrue(targets.contains(board.getCell(2, 3)));
	}
}
