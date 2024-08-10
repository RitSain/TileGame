package tests;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import model.BoardCell;
import model.ClearCellGame;
import model.Game;

/* The following directive executes tests in sorted order */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class StudentTests {
	
	/* Remove the following test and add your tests */
	@Test
	public void CreateBoard() {
		int maxRows = 4, maxCols = 4, strategy = 1;
		Game ccGame = new ClearCellGame(maxRows, maxCols, new Random(1L), strategy);
		String answer = getBoardStr(ccGame);
		String expected = "Board(Rows: 4, Columns: 4)\n"
						+ "....\n"
						+ "....\n"
						+ "....\n"
						+ "....";
		assertTrue(answer.replace('\n', ' ').trim().equals(expected.replace('\n',' ').trim()));
	}
	
	@Test
	public void Animation() {
		int maxRows = 4, maxCols = 4, strategy = 1;
		Game ccGame = new ClearCellGame(maxRows, maxCols, new Random(1L), strategy);
		ccGame.nextAnimationStep();
		ccGame.nextAnimationStep();
		String answer = getBoardStr(ccGame);
		String expected = "Board(Rows: 4, Columns: 4)\n"
						+ "RRRG\n"
						+ "RYBY\n"
						+ "....\n"
						+ "....";
		assertTrue(answer.replace('\n', ' ').trim().equals(expected.replace('\n',' ').trim()));
	}
	@Test
	public void setMethods() {
	int maxRows = 8, maxCols = 8, strategy = 1;
	Game ccGame = new ClearCellGame(maxRows, maxCols, new Random(1L), strategy);

	ccGame.setBoardWithColor(BoardCell.BLUE);
	ccGame.setRowWithColor(maxRows - 1, BoardCell.EMPTY);
	ccGame.setRowWithColor(1, BoardCell.YELLOW);
	ccGame.setBoardCell(1, maxCols - 1, BoardCell.RED);
	ccGame.setRowWithColor(2, BoardCell.RED);
	ccGame.setColWithColor(0, BoardCell.GREEN);
	
	
	
	String answer = getBoardStr(ccGame);
	String expected = "Board(Rows: 8, Columns: 8)\n"
					+ "GBBBBBBB\n"
					+ "GYYYYYYR\n"
					+ "GRRRRRRR\n"
					+ "GBBBBBBB\n"
					+ "GBBBBBBB\n"
					+ "GBBBBBBB\n"
					+ "GBBBBBBB\n"
					+ "G.......";
	assertTrue(answer.replace('\n', ' ').trim().equals(expected.replace('\n',' ').trim()));
	}
	
	@Test
	public void processCellMethod() {
		int maxRows = 8, maxCols = 8, strategy = 1;
		Game ccGame = new ClearCellGame(maxRows, maxCols, new Random(1L), strategy);

		ccGame.setBoardWithColor(BoardCell.BLUE);
		ccGame.setRowWithColor(maxRows - 1, BoardCell.EMPTY);
		ccGame.setRowWithColor(1, BoardCell.YELLOW);
		ccGame.setBoardCell(1, maxCols - 1, BoardCell.RED);
		ccGame.setRowWithColor(2, BoardCell.RED);
		ccGame.setColWithColor(0, BoardCell.GREEN);
		
		ccGame.processCell(2, 1); // the reds on the third row
		
		String answer = getBoardStr(ccGame);
		String expected = "Board(Rows: 8, Columns: 8)\n"
						+ "GBBBBBBB\n"
						+ "GYYYYYY.\n"
						+ "G.......\n"
						+ "GBBBBBBB\n"
						+ "GBBBBBBB\n"
						+ "GBBBBBBB\n"
						+ "GBBBBBBB\n"
						+ "G.......";
		
		assertTrue(answer.replace('\n', ' ').trim().equals(expected.replace('\n',' ').trim()));
	}
	
	@Test
	public void getScoreMethod() {
		int maxRows = 8, maxCols = 8, strategy = 1;
		Game ccGame = new ClearCellGame(maxRows, maxCols, new Random(1L), strategy);

		ccGame.setBoardWithColor(BoardCell.BLUE);
		ccGame.setRowWithColor(maxRows - 1, BoardCell.EMPTY);
		ccGame.setRowWithColor(1, BoardCell.YELLOW);
		ccGame.setBoardCell(1, maxCols - 1, BoardCell.RED);
		ccGame.setRowWithColor(2, BoardCell.RED);
		ccGame.setColWithColor(0, BoardCell.GREEN);
		
		ccGame.processCell(2, 1); // the reds on the third row
		
		int score = ccGame.getScore();
		System.out.print(score);
		
		assertTrue(score == 8);
	}
	
	@Test
	public void collapseCells() {
		int maxRows = 8, maxCols = 8, strategy = 1;
		Game ccGame = new ClearCellGame(maxRows, maxCols, new Random(1L), strategy);

		ccGame.setBoardWithColor(BoardCell.BLUE);
		ccGame.setRowWithColor(maxRows - 1, BoardCell.EMPTY);
		ccGame.setRowWithColor(1, BoardCell.YELLOW);
		ccGame.setBoardCell(1, maxCols - 1, BoardCell.RED);
		ccGame.setRowWithColor(2, BoardCell.RED);
		ccGame.setColWithColor(0, BoardCell.GREEN);
		
		ccGame.processCell(2, 1); // the reds on the third row
		ccGame.processCell(0,  0); // the greens on the left
		
		String answer = getBoardStr(ccGame);
		String expected = "Board(Rows: 8, Columns: 8)\n"
						+ ".BBBBBBB\n"
						+ ".YYYYYY.\n"
						+ ".BBBBBBB\n"
						+ ".BBBBBBB\n"
						+ ".BBBBBBB\n"
						+ ".BBBBBBB\n"
						+ "........\n"
						+ "........\n";
		
		assertTrue(answer.replace('\n', ' ').trim().equals(expected.replace('\n',' ').trim()));
	}
	
	@Test
	public void getMethods() {
		int maxRows = 8, maxCols = 8, strategy = 1;
		Game ccGame = new ClearCellGame(maxRows, maxCols, new Random(1L), strategy);

		ccGame.setBoardWithColor(BoardCell.BLUE);
		ccGame.setRowWithColor(maxRows - 1, BoardCell.EMPTY);
		ccGame.setRowWithColor(1, BoardCell.YELLOW);
		ccGame.setBoardCell(1, maxCols - 1, BoardCell.RED);
		ccGame.setRowWithColor(2, BoardCell.RED);
		ccGame.setColWithColor(0, BoardCell.GREEN);
		
		int getMaxRows = ccGame.getMaxRows();
		int getMaxCols = ccGame.getMaxCols();
		BoardCell cell = ccGame.getBoardCell(0,0);
		assertTrue(getMaxRows == maxRows && getMaxCols == maxCols && cell == BoardCell.GREEN);
	}
	
	
	@Test
	public void gameOver1() {
		int maxRows = 8, maxCols = 8, strategy = 1;
		Game ccGame = new ClearCellGame(maxRows, maxCols, new Random(1L), strategy);

		ccGame.setBoardWithColor(BoardCell.BLUE);
		ccGame.setRowWithColor(maxRows - 1, BoardCell.EMPTY);
		ccGame.setRowWithColor(1, BoardCell.YELLOW);
		ccGame.setBoardCell(1, maxCols - 1, BoardCell.RED);
		ccGame.setRowWithColor(2, BoardCell.RED);
		//ccGame.setColWithColor(0, BoardCell.GREEN);
		
		boolean gameOver = ccGame.isGameOver();
		
		assertTrue(gameOver == false);
	}
	
	@Test
	public void gameOver2() {
		int maxRows = 8, maxCols = 8, strategy = 1;
		Game ccGame = new ClearCellGame(maxRows, maxCols, new Random(1L), strategy);

		ccGame.setBoardWithColor(BoardCell.BLUE);
		ccGame.setRowWithColor(maxRows - 1, BoardCell.EMPTY);
		ccGame.setRowWithColor(1, BoardCell.YELLOW);
		ccGame.setBoardCell(1, maxCols - 1, BoardCell.RED);
		ccGame.setRowWithColor(2, BoardCell.RED);
		ccGame.setColWithColor(0, BoardCell.GREEN);
		
		boolean gameOver = ccGame.isGameOver();
		
		assertTrue(gameOver == true);
	}
	

	
	private static String getBoardStr(Game game) {
		int maxRows = game.getMaxRows(), maxCols = game.getMaxCols();

		String answer = "Board(Rows: " + maxRows + ", Columns: " + maxCols + ")\n";
		for (int row = 0; row < maxRows; row++) {
			for (int col = 0; col < maxCols; col++) {
				answer += game.getBoardCell(row, col).getName();
			}
			answer += "\n";
		}

		return answer;
	}
}
