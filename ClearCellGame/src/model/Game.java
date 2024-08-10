package model;

/**
 * This class represents the logic of a game where a board is updated on each
 * step of the game animation. The board can also be updated by selecting a
 * board cell.
 * 
 * @author Department of Computer Science, UMCP
 */

public abstract class Game {
	protected BoardCell[][] board;
	int maxRows; 
	int maxCols; 

	/**
	 * Defines a board with BoardCell.EMPTY cells.
	 * 
	 * @param maxRows
	 * @param maxCols
	 */
	public Game(int maxRows, int maxCols) {
		//throw new UnsupportedOperationException("Implement this method");
		this.maxRows = maxRows; // initializes the instance variables 
		this.maxCols = maxCols; 
		board = new BoardCell[maxRows][maxCols]; // creates a new boardcell array with empty elements 
		for(int i = 0; i<maxRows; i++) {
			for(int j = 0; j< maxCols; j++) {
				board[i][j] = BoardCell.EMPTY;
			}
		}
	}

	public int getMaxRows() {
		//throw new UnsupportedOperationException("Implement this method");
		return maxRows;
	}

	public int getMaxCols() {
		//throw new UnsupportedOperationException("Implement this method");
		return maxCols;
	}

	public void setBoardCell(int rowIndex, int colIndex, BoardCell boardCell) {
		//throw new UnsupportedOperationException("Implement this method");
		board[rowIndex][colIndex] = boardCell;
	}

	public BoardCell getBoardCell(int rowIndex, int colIndex) {
		//throw new UnsupportedOperationException("Implement this method");
		return board[rowIndex][colIndex];
	}

	/**
	 * Initializes row with the specified color.
	 * 
	 * @param rowIndex
	 * @param cell
	 */
	public void setRowWithColor(int rowIndex, BoardCell cell) {
		for(int i = 0; i < maxRows; i++) {
			board[rowIndex][i] = cell; 
		}
	}

	/**
	 * Initializes column with the specified color.
	 * 
	 * @param colIndex
	 * @param cell
	 */
	public void setColWithColor(int colIndex, BoardCell cell) {
		for(int row = 0; row < maxRows; row++) {
			for (int col = 0; col < maxCols; col++){
				if (col==colIndex) {
					board[row][col] = cell; 
				}
			}
		}
	}

	/**
	 * Initializes the board with the specified color.
	 * 
	 * @param cell
	 */
	public void setBoardWithColor(BoardCell cell) {
		for (int row = 0; row < maxRows; row++) {
			for (int col = 0; col < maxCols; col++) {
				board[row][col] = cell;
			}
		}
	}


	public abstract boolean isGameOver();

	public abstract int getScore();

	/**
	 * Advances the animation one step.
	 */
	public abstract void nextAnimationStep();

	/**
	 * Adjust the board state according to the current board state and the selected
	 * cell.
	 * 
	 * @param rowIndex
	 * @param colIndex
	 */
	public abstract void processCell(int rowIndex, int colIndex);
}