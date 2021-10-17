package tictactoe;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * A class that keeps track of the state of a TicTacToe game.
 */
public class TicTacToeModel implements TicTacToe {

  private final Player[][] board;
  private Player player;
  private Player winner;
  private boolean isOver;
  private int moveCount;

  /**
   * creates a new TicTacToe game with initial configuration.
   */
  public TicTacToeModel() {
    board = new Player[3][3];
    player = Player.X;
    winner = null;
    isOver = false;
    moveCount = 0;
  }

  private void updateState(int r, int c) {
    moveCount += 1;
    int cCount = 0;
    int rCount = 0;
    int d1Count = 0;
    int d2Count = 0;

    // count the number of X's or O's in the board.
    for (int i = 0; i <= 2; i ++) {
      if (board[r][i] == player) {
        rCount++;
      }
      if (board[i][c] == player) {
        cCount++;
      }
      if (board[i][i] == player) {
        d1Count++;
      }
      if (board[i][2 - i] == player) {
        d2Count++;
      }
    }

    // update game's winner.
    if (cCount == 3 || rCount == 3 || d1Count == 3 || d2Count == 3) {
      winner = player;
    }

    // check if there is a winner or if board is full and update gameOver.
    if (winner != null || moveCount == 9) {
      isOver = true;
    }

    // change the player's turn.
    if (player == Player.X) {
      player = Player.O;
    }
    else {
      player = Player.X;
    }
  }

  private void validateRowColumn(int r, int c) {
    if (r < 0 || r > 2 || c < 0 || c > 2) {
      throw new IllegalArgumentException("index out of bounds");
    }
  }

  @Override
  public void move(int r, int c) {
    // if game is over then throw IllegalStateException.
    if (isGameOver()) {
      throw new IllegalStateException("game over!, can't make more moves");
    }

    // if r, c are out of bounds throw IllegalArgumentException.
    validateRowColumn(r, c);

    // if (r, c) is not empty throw IllegalArgumentException.
    if (board[r][c] != null) {
      throw new IllegalArgumentException("position not empty");
    }

    // if all the validations pass, mark board with player.
    board[r][c] = player;

    // update game state.
    updateState(r, c);
  }

  @Override
  public Player getTurn() {
    return player;
  }

  @Override
  public boolean isGameOver() {
    return isOver;
  }

  @Override
  public Player getWinner() {
    return winner;
  }

  @Override
  public Player[][] getBoard() {
    Player[][] copyBoard = new Player[3][3];
    for (int i = 0; i < 3; i ++) {
      System.arraycopy(board[i], 0, copyBoard[i], 0, 3);
    }
    return copyBoard;
  }

  @Override
  public Player getMarkAt(int r, int c) {
    validateRowColumn(r, c);
    return board[r][c];
  }

  @Override
  public String toString() {
    // Using Java stream API to save code:
    return Arrays.stream(getBoard()).map(row -> " " + Arrays.stream(row).map(
      p -> p == null ? " " : p.toString()).collect(
              Collectors.joining(" | "))).collect(Collectors.joining("\n-----------\n"));
    // This is the equivalent code as above, but using iteration, and still using the helpful
    // built-in String.join method.
    // List<String> rows = new ArrayList<>();
    // for(Player[] row : getBoard()) {
    //   List<String> rowStrings = new ArrayList<>();
    //   for(Player p : row) {
    //     if(p == null) {
    //       rowStrings.add(" ");
    //     } else {
    //       rowStrings.add(p.toString());
    //     }
    //   }
    //   rows.add(" " + String.join(" | ", rowStrings));
    // }
    // return String.join("\n-----------\n", rows);
  }
}
