import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;
import tictactoe.Player;
import tictactoe.TicTacToe;
import tictactoe.TicTacToeModel;


/**
 * Test class for Tic-Tac-Toe.
 */
public class TicTacToeTest {

  TicTacToe game;

  private TicTacToeModel newTicTacToeGame() {
    return new TicTacToeModel();
  }

  private void rWinScenario() {
    // horizontal win (Player X) - (0, 0), (0, 1), (0, 2)
    game.move(0, 0); // x move
    game.move(2, 2); // o move
    game.move(0, 1); // x move
    game.move(2, 1); // o move
    game.move(0, 2); // x move
  }

  private void cWinScenario() {
    // vertical win (Player O) - (0, 2), (1, 2), (2, 2)
    game.move(0, 0); // x move
    game.move(2, 2); // o move
    game.move(0, 1); // x move
    game.move(1, 2); // o move
    game.move(1, 0); // x move
    game.move(0, 2); // o move
  }

  private void dWinScenario() {
    // diagonal win (Player X) - (0, 2), (1, 1), (2, 0)
    game.move(0, 2); // x move
    game.move(2, 2); // o move
    game.move(1, 1); // x move
    game.move(2, 1); // o move
    game.move(2, 0); // x move
  }

  private void drawScenario() {
    game.move(0, 0); // x move
    game.move(0, 1); // o move
    game.move(0, 2); // x move
    game.move(1, 1); // o move
    game.move(1, 0); // x move
    game.move(2, 0); // o move
    game.move(1, 2); // x move
    game.move(2, 2); // o move
    game.move(2, 1); // x move
  }

  /**
   * Tests to check if moves are being performed correctly.
   */
  @Test
  public void testBasics() {
    game = newTicTacToeGame();

    // check if winner is null
    assertNull(game.getWinner());
    // check if game is not over
    assertFalse(game.isGameOver());
    // checking to see if player X takes the first turn.
    assertEquals(Player.X, game.getTurn());
    assertNull(game.getMarkAt(0, 0));
    assertNull(game.getMarkAt(0, 1));
    assertNull(game.getMarkAt(0, 2));
    assertNull(game.getMarkAt(1, 0));
    assertNull(game.getMarkAt(1, 1));
    assertNull(game.getMarkAt(1, 2));
    assertNull(game.getMarkAt(2, 0));
    assertNull(game.getMarkAt(2, 1));
    assertNull(game.getMarkAt(2, 2));

    game.move(0, 0);
    assertEquals(Player.X, game.getMarkAt(0, 0));
    assertNull(game.getMarkAt(0, 1));
    assertNull(game.getMarkAt(0, 2));
    assertNull(game.getMarkAt(1, 0));
    assertNull(game.getMarkAt(1, 1));
    assertNull(game.getMarkAt(1, 2));
    assertNull(game.getMarkAt(2, 0));
    assertNull(game.getMarkAt(2, 1));
    assertNull(game.getMarkAt(2, 2));

    // check if winner is null
    assertNull(game.getWinner());
    // check if game is not over
    assertFalse(game.isGameOver());
    // check to see if the next player is player O
    assertEquals(Player.O, game.getTurn());

    game.move(0, 1);
    assertEquals(Player.X, game.getMarkAt(0, 0));
    assertEquals(Player.O, game.getMarkAt(0, 1));
    assertNull(game.getMarkAt(0, 2));
    assertNull(game.getMarkAt(1, 0));
    assertNull(game.getMarkAt(1, 1));
    assertNull(game.getMarkAt(1, 2));
    assertNull(game.getMarkAt(2, 0));
    assertNull(game.getMarkAt(2, 1));
    assertNull(game.getMarkAt(2, 2));


    // check if winner is null
    assertNull(game.getWinner());
    // check if game is not over
    assertFalse(game.isGameOver());
    // check to see if the next player is player X
    assertEquals(Player.X, game.getTurn());

    game.move(0, 2);
    assertEquals(Player.X, game.getMarkAt(0, 0));
    assertEquals(Player.O, game.getMarkAt(0, 1));
    assertEquals(Player.X, game.getMarkAt(0, 2));
    assertNull(game.getMarkAt(1, 0));
    assertNull(game.getMarkAt(1, 1));
    assertNull(game.getMarkAt(1, 2));
    assertNull(game.getMarkAt(2, 0));
    assertNull(game.getMarkAt(2, 1));
    assertNull(game.getMarkAt(2, 2));

    // check if winner is null
    assertNull(game.getWinner());
    // check if game is not over
    assertFalse(game.isGameOver());
    // check to see if the next player is player O
    assertEquals(Player.O, game.getTurn());
  }

  /**
   * Tests if IllegalArguments are handled.
   */
  @Test
  public void testIllegalArguments() {
    game = newTicTacToeGame();

    // index == negative argument.
    try {
      game.move(-1, 0);
      fail("this should have caused an exception");
    }
    catch (IllegalArgumentException iAE) {
      assertEquals("index out of bounds", iAE.getMessage());
    }

    // index > size argument.
    try {
      game.move(0, 3);
      fail("this should have caused an exception");
    }
    catch (IllegalArgumentException iAE) {
      assertEquals("index out of bounds", iAE.getMessage());
    }

    // check if playing in a non-empty cell.
    game.move(1, 0);
    try {
      game.move(1, 0);
      fail("this should have caused an exception");
    }
    catch (IllegalArgumentException iAE) {
      assertEquals("position not empty", iAE.getMessage());
    }

    // check to see if markAt() throws an exception - index < 0.
    try {
      game.getMarkAt(-1, 0);
      fail("this should have caused an exception");
    }
    catch (IllegalArgumentException iAE) {
      assertEquals("index out of bounds", iAE.getMessage());
    }

    // check to see if markAt() throws an exception - index > max.
    try {
      game.getMarkAt(0, 3);
      fail("this should have caused an exception");
    }
    catch (IllegalArgumentException iAE) {
      assertEquals("index out of bounds", iAE.getMessage());
    }
  }

  /**
   * Tests for game over, winner and illegal states.
   */
  @Test
  public void testGameState() {
    game = newTicTacToeGame();
    // check if there is no winner initially.
    assertNull(game.getWinner());
    rWinScenario();
    // check if X won the game (row win).
    assertEquals(Player.X, game.getWinner());
    // check if game is over.
    assertTrue(game.isGameOver());
    // check no more moves allowed.
    try {
      game.move(1, 0);
      fail("no more moves should be allowed");
    }
    catch (IllegalStateException iSE) {
      assertEquals("game over!, can't make more moves", iSE.getMessage());
    }

    game = newTicTacToeGame();
    cWinScenario();
    // check if O won the game (col win).
    assertEquals(Player.O, game.getWinner());
    // check if game is over after O wins.
    assertTrue(game.isGameOver());
    // check no more moves allowed.
    try {
      game.move(1, 0);
      fail("no more moves should be allowed");
    }
    catch (IllegalStateException iSE) {
      assertEquals("game over!, can't make more moves", iSE.getMessage());
    }

    game = newTicTacToeGame();
    dWinScenario();
    // check if X won the game (row win).
    assertEquals(Player.X, game.getWinner());
    // check if game is over after X wins.
    assertTrue(game.isGameOver());
    // check no more moves allowed.
    try {
      game.move(1, 0);
      fail("no more moves should be allowed");
    }
    catch (IllegalStateException iSE) {
      assertEquals("game over!, can't make more moves", iSE.getMessage());
    }


    game = newTicTacToeGame();
    drawScenario();
    // check if winner is null.
    assertNull(game.getWinner());
    // check if game is over after draw.
    assertTrue(game.isGameOver());
    // check no more moves allowed after draw.
    try {
      game.move(1, 0);
      fail("no more moves should be allowed");
    }
    catch (IllegalStateException iSE) {
      assertEquals("game over!, can't make more moves", iSE.getMessage());
    }
  }

  /**
   * test if the board state is mutable.
   */
  @Test
  public void testBoard() {
    game = newTicTacToeGame();
    assertNull(game.getMarkAt(0, 0));
    Player[][] board = game.getBoard();
    board[0][0] = Player.O;
    assertNull(game.getMarkAt(0, 0));
  }

}