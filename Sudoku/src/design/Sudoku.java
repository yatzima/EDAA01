package design;

import java.util.Random;

public class Sudoku {

	private int[][] sudokuBoard;

	public Sudoku() {
		sudokuBoard = new int[9][9];
	}

	/**
	 * Sätter en siffra på angiven ruta.
	 * 
	 * @param row,
	 *            col, value
	 * @return void
	 */
	public void setVal(int row, int col, int val) {
		if (row < 9 && row >= 0 && col < 9 && col >= 0) {
			sudokuBoard[row][col] = val;
		}
	}

	/** Hämtar värder från en angiven ruta. */
	public int getVal(int row, int col) {
		if (row < 9 && row >= 0 && col < 9 && col >= 0) {
			return sudokuBoard[row][col];
		} else {
			return 0;
		}
	}

	/** Byter ut brädan. */
	public void setBoard(int input[][]) {
		clear();
		for (int row = 0; row < input[0].length; row++) {
			for (int col = 0; col < input.length; col++) {
				sudokuBoard[row][col] = input[row][col];
			}
		}
	}

	/** Sätter alla rutor till 0. */
	public void clear() {
		for (int row = 0; row < 9; row++) {
			for (int col = 0; col < 9; col++) {
				sudokuBoard[row][col] = 0;
			}
		}
	}

	/** Slumpar siffror på brädan. */
	public void newGame() {
		int randRow, randCol, randVal;
		Random rand = new Random();
		for (int i = 0; i < 17; i++) {
			randRow = rand.nextInt(9);
			randCol = rand.nextInt(9);
			randVal = rand.nextInt(9) + 1;

			while (!check(randRow, randCol, randVal) && getVal(randRow, randCol) != randVal) {
				randRow = rand.nextInt(9);
				randCol = rand.nextInt(9);
				randVal = rand.nextInt(9) + 1;
			}
			setVal(randRow, randCol, randVal);
		}
	}

	/** Löser sudokun. */
	public boolean solve() {
		for (int row = 0; row < 9; row++) {
			for (int col = 0; col < 9; col++) {
				if (getVal(row, col) != 0 && !check(row, col, getVal(row, col))) {
					return false;
				}
			}
		}
		return solve(0, 0);
	}

	private boolean solve(int row, int col) {

		int nextr = (col + 1) > 8 ? (row + 1) : row;
		int nextc = (col + 1) > 8 ? 0 : (col + 1);

 

		if (getVal(row, col) == 0) {
			for (int i = 1; i <= 9; i++) {
				if (check(row, col, i)) {
					setVal(row, col, i);
					if (solve(nextr, nextc)) {
						return true;
					}
				}
			}
			setVal(row, col, 0);
			return false;
		}
		return check(row, col, getVal(row, col)) ? solve(nextr, nextc) : false;
	}

	private boolean check(int row, int col, int num) {

		if (num <= 9 && num > 0) {
			for (int i = 0; i < 9; i++) {
				if (sudokuBoard[row][i] == num && (i != col)) {
					return false;
				}
			}

			for (int i = 0; i < 9; i++) {
				if (sudokuBoard[i][col] == num && (i != row)) {
					return false;
				}
			}

			int boxRow = ((row / 3) * 3) + 1;
			int boxCol = ((col / 3) * 3) + 1;

			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					if (sudokuBoard[(boxRow - 1) + i][(boxCol - 1) + j] == num
							&& (row != (boxRow - 1) + i && col != (boxCol - 1) + j)) {
						return false;
					}
				}
			}
		}
		return true;
	}
}
