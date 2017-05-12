package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import design.Sudoku;

public class SudokuTest {
	Sudoku S;

	@Before
	public void setUp() throws Exception {
		S = new Sudoku();
	}

	@After
	public void tearDown() throws Exception {
		S = null;
	}

	@Test
	public void testSolveEmpty(){
		assertTrue(S.solve());
	}
	
	@Test
	public void testSolveCaseTwoFives(){
		S.setVal(0, 0, 5);
		S.setVal(0, 1, 5);
		assertFalse(S.solve());
	}
	
	@Test 
	public void testSolveCaseUnsolvable(){
		S.setVal(0, 0, 1);
		S.setVal(0, 1, 2);
		S.setVal(0, 2, 3);
		S.setVal(1, 0, 4);
		S.setVal(1, 1, 5);
		S.setVal(1, 2, 6);
		S.setVal(2, 3, 7);
		assertFalse(S.solve());
	}
	
	@Test
	public void testSolveCaseClear(){
		S.setVal(0, 0, 5);
		S.setVal(0, 1, 5);
		assertFalse(S.solve());
		S.clear();
		assertTrue(S.solve());
	}
	
	

}
