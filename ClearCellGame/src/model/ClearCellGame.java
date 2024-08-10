package model;

import java.awt.Color;
import java.util.Random;

/**
 * This class extends GameModel and implements the logic of the clear cell game.
 * We define an empty cell as BoardCell.EMPTY. An empty row is defined as one
 * where every cell corresponds to BoardCell.EMPTY.
 * 
 * @author Department of Computer Science, UMCP
 */

public class ClearCellGame extends Game {

	Random random;
	int strategy;
	int score;

	/**
	 * Defines a board with empty cells. It relies on the super class constructor to
	 * define the board. The random parameter is used for the generation of random
	 * cells. The strategy parameter defines which clearing cell strategy to use
	 * (for this project it will be 1). For fun, you can add your own strategy by
	 * using a value different that one.
	 * 
	 * @param maxRows
	 * @param maxCols
	 * @param random
	 * @param strategy
	 */
	public ClearCellGame(int maxRows, int maxCols, Random random, int strategy) {
		super(maxRows, maxCols);
		this.random = random;
		this.strategy = strategy;
		score = 0;
	}

	/**
	 * The game is over when the last board row (row with index board.length -1) is
	 * different from empty row.
	 */
	public boolean isGameOver() {
		for (int i = 0; i < maxCols; i++) { // checks to see if the board cell at the bottom of the board are empty or
											// not
			if (board[maxRows - 1][i] != BoardCell.EMPTY) {
				return true; // if they are not empty it returns true meaning the game is over
			}
		}
		return false;// if the are empty then it return false meaning the game is not over
	}

	public int getScore() {
		return score;
	}

	/**
	 * This method will attempt to insert a row of random BoardCell objects if the
	 * last board row (row with index board.length -1) corresponds to the empty row;
	 * otherwise no operation will take place.
	 */
	public void nextAnimationStep() {
		// throw new UnsupportedOperationException("Implement this method");
		if (!isGameOver()) {
			BoardCell[][] boardUpdated = new BoardCell[board.length][maxCols]; // new board is created to hold the
																				// updated state of the board
			for (int row = 0; row < board.length; row++) {
				for (int col = 0; col < maxCols; col++) {
					if (row == 0) { // creates a row of random cells in the top row
						boardUpdated[row][col] = BoardCell.getNonEmptyRandomBoardCell(random);
					} else {// shifts down all the other rows
						boardUpdated[row][col] = board[row - 1][col];
					}
				}

			}
			board = boardUpdated; // updates the board to the new board with the changes made above
		}
	}

	/**
	 * This method will turn to BoardCell.EMPTY the cell selected and any adjacent
	 * surrounding cells in the vertical, horizontal, and diagonal directions that
	 * have the same color. The clearing of adjacent cells will continue as long as
	 * cells have a color that corresponds to the selected cell. Notice that the
	 * clearing process does not clear every single cell that surrounds a cell
	 * selected (only those found in the vertical, horizontal or diagonal
	 * directions).
	 * 
	 * IMPORTANT: Clearing a cell adds one point to the game's score.<br />
	 * <br />
	 * 
	 * If after processing cells, any rows in the board are empty,those rows will
	 * collapse, moving non-empty rows upward. For example, if we have the following
	 * board (an * represents an empty cell):<br />
	 * <br />
	 * RRR<br />
	 * GGG<br />
	 * YYY<br />
	 * * * *<br/>
	 * <br />
	 * then processing each cell of the second row will generate the following
	 * board<br />
	 * <br />
	 * RRR<br />
	 * YYY<br />
	 * * * *<br/>
	 * * * *<br/>
	 * <br />
	 * IMPORTANT: If the game has ended no action will take place.
	 * 
	 * 
	 */

	private boolean isValid(int row, int col) {// checks to see if a row and column index are valid 
		if (row >= 0 && col >= 0 && row < maxRows && col < maxCols) {
			return true; // returns true of they are valid 
		}
		return false; // returns false if they are invalid
	}

	public void processCell(int rowIndex, int colIndex) {
		Color color = board[rowIndex][colIndex].getColor();
		ffclear(rowIndex, colIndex, new boolean[board.length][board[0].length], color);
		collapseCells();
	}

	private void ffclear(int rowIndex, int colIndex, boolean[][] visited, Color color) {
		if (!isValid(rowIndex, colIndex)) {// checks to see if the indexes are valid 
			return;
		}
		if (visited[rowIndex][colIndex]) {// checks to see if the indexed have already been checked
			return;
		}
		visited[rowIndex][colIndex] = true; // sets the index to show as already checked 
		if (board[rowIndex][colIndex].getColor() == color) {//if the cell is the same at the clicked cell it becomes an empty cell
			board[rowIndex][colIndex] = BoardCell.EMPTY;
			score++; // when a cell becomes empty we increment the score
		} else {
			return;
		}// continue to look for other matching cells in all 8 directions
		ffclear(rowIndex - 1, colIndex - 1, visited, color);
		ffclear(rowIndex - 1, colIndex, visited, color);
		ffclear(rowIndex - 1, colIndex + 1, visited, color);
		ffclear(rowIndex, colIndex + 1, visited, color);
		ffclear(rowIndex + 1, colIndex + 1, visited, color);
		ffclear(rowIndex + 1, colIndex, visited, color);
		ffclear(rowIndex + 1, colIndex - 1, visited, color);
		ffclear(rowIndex, colIndex - 1, visited, color);

	}

	private void collapseCells() {
		int emptyRow = -1; // Creates variable to capture empty row index
		for (int row = 0; row < board.length; row++) {
			boolean isEmpty = true;
			for (int col = 0; col < maxCols; col++) {
				if (board[row][col] != BoardCell.EMPTY) {
					isEmpty = false;
				}
			}
			if (isEmpty) { // if an empty row is found the index is recorded
				emptyRow = row;
				break;
			}
		}

		BoardCell[][] boardUpdated = new BoardCell[board.length][maxCols]; // creates a duplicate of the current board
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < maxCols; col++) {
				boardUpdated[row][col] = board[row][col];
			}
		}
		// moves up the rows below where the empty rows exist
		if (emptyRow != -1) {
			for (int rows = emptyRow; rows < board.length - 1; rows++) {
				for (int col = 0; col < maxCols; col++) {
					boardUpdated[rows][col] = board[rows + 1][col];
				}
			}
		}

		board = boardUpdated; // updates the new board to be the duplicate board we created
	}
}