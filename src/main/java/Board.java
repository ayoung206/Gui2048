
/*
 * @author Yong Hoon Do, yhdo@ucsd.edu
 * @author A Kim, ayk037@ucsd.edu
 * @since Nov 8, 2017
 * File: Board.java
 *
 * This Class is used to construct a Board object to be used
 * for the simulation of the game 2048. It can create a fresh
 * board or load an already existing board. In addition this
 * class allows the user to save their current game to a new, 
 * specified file. The class also allows for the board to be 
 * rotated 90 degrees to the right or left. Baed on the direction
 * passed in by the user, this class will then move tiles
 * existing on the board in a certain direction, combining tiles
 * of the same value. The game is considered to be over when
 * the board cannot move in any direction.
 * /

//------------------------------------------------------------------//
// Board.java                                                       //
//                                                                  //
// Class used to represent a 2048 game board                        //
//                                                                  //
// Author:  W16-CSE8B-TA group                                      //
// Date:    1/17/16                                                 //
//------------------------------------------------------------------//

/**
 * Sample Board
 * <p/>
 * 0   1   2   3
 * 0   -   -   -   -
 * 1   -   -   -   -
 * 2   -   -   -   -
 * 3   -   -   -   -
 * <p/>
 * The sample board shows the index values for the columns and rows
 * Remember that you access a 2D array by first specifying the row
 * and then the column: grid[row][column]
 */

import java.util.Random;

/**
 * Provides an ability to play the 2048game. Manages each value of tiles when the tiles are moving
 * on the board so that updates the total score of the game.
 */
public class Board {
  /**
   * Number of tiles showing when the game starts
   */
  public final int NUM_START_TILES = 2;

  /**
   * The probability (times 100) that a randomly generated tile will be a 2 (vs a 4)
   */
  public final int TWO_PROBABILITY = 90;

  /**
   * The size of the grid
   */
  public final int GRID_SIZE;

  private int[][] grid;  // The grid of tile values
  private int score;     // The current score

  // You do not have to use these variables
  private final Random random;    // A random number generator (for testing)

  /**
   * Name: Board(Random random, int boardSize).
   *
   * Purpose: The purpose of this method is to create or construct a fresh board for the user with
   * two random tiles places within the board. This board will have a particular boardSize that the
   * user sets, as well as a random
   *
   * @param boardSize size of the 2048 game board to be used.
   * @param random Random random represents the random number which be used to specific where (after
   * every move) a new tile should be added to the board when playing.
   */
  public Board(Random random, int boardSize) {
    if (boardSize < 2) boardSize = 4;

    // initialize member variables
    this.random = random;
    this.GRID_SIZE = boardSize;
    this.grid = new int[boardSize][boardSize];
    this.score = 0;

    // loop through and add two initial tiles to the board randomly
    for (int index = 0; index < NUM_START_TILES; index++) {
      addRandomTile();
    }
  }


  /**
   * Constructor used to load boards for grading/testing
   *
   * THIS IS USED FOR GRADING - DO NOT CHANGE IT
   */
  public Board(Random random, int[][] inputBoard) {
    this.random = random;
    this.GRID_SIZE = inputBoard.length;
    this.grid = new int[GRID_SIZE][GRID_SIZE];
    for (int r = 0; r < GRID_SIZE; r++) {
      for (int c = 0; c < GRID_SIZE; c++) {
        this.grid[r][c] = inputBoard[r][c];
      }
    }
  }

  /**
   * return the tile value in a particular cell in the grid.
   *
   * @param row The row
   * @param col The column
   * @return The value of the tile at (row, col)
   */
  public int getTileValue(int row, int col) {
    return grid[row][col];
  }

  /**
   * Get the current score
   *
   * @return the current score of the game
   */
  public int getScore() {
    return score;
  }

  /**
   * Name: addRandomTile()
   *
   * Purpose: The purpose of this method is to add a random tile of either value 2 or 4 to a random
   * empty space on the 2048 board. The place where this tile is added is dependent on the random
   * value associated with each board object. If no tiles are empty, it returns without changing the
   * board.
   */
  public void addRandomTile() {
    int count = 0;
    // loop through grid keeping count of every empty space on board
    for (int rowI = 0; rowI < grid.length; rowI++) {
      for (int colI = 0; colI < grid[rowI].length; colI++) {
        if (grid[rowI][colI] == 0) {
          count++;
        }
      }
    }

    // if count is still 0 after loop, no empty spaces, return
    if (count == 0) {
      System.out.println("There are no empty spaces!");
      return;
    }

    // keep track of where on board random tile should be placed
    int location = random.nextInt(count);
    int value = random.nextInt(100);

    // reset count
    count = 0;
    // loop through grid checking where grid is 0 & incrementing count
    for (int rowI = 0; rowI < grid.length; rowI++) {
      for (int colI = 0; colI < grid[rowI].length; colI++) {
        if (grid[rowI][colI] == 0) {
          // if count equals random location generated, place tile
          if (count == location) {
            System.out.println("Adding a tile to location " + rowI + ", " + colI);
            if (value < TWO_PROBABILITY) {
              grid[rowI][colI] = 2;
            } else {
              grid[rowI][colI] = 4;
            }
          }
          count++;
        }
      }
    }
  }

  /**
   * Name: isGameOver() <p> Purpose: The purpose of this method is to check whether or not the game
   * in play is over. The game is officially over once there are no longer any valid moves that can
   * be made in any direction. If the game is over, this method will return true and print the
   * words: "Game Over!" This method will be checked before any movement is ever made.
   *
   * @return true if the game is over, and false if the game isn't over
   */
  public boolean isGameOver() {
    return (!canMoveLeft() && !canMoveRight() && !canMoveUp()
        && !canMoveDown());
  }


  /**
   * Name: canMove(Direction direction)
   *
   * Purpose: The purpose of this method is to check to see if the movement of the tiles in any
   * direction can actually take place. It does not move the tiles, but at every index of the grid,
   * checks to see if there is a tile above, below, to the left or right that has the same value. If
   * this is the case, then that tile can be moved. It also checks if there is an empty (0) tile at
   * a specified index, as this also indicates that movement can be possible. This method is called
   * within move() so that that method can determine whether or not tiles should be moved.
   *
   * @param direction direction the tiles will move (if possible)
   * @return true if the movement can be done and false if it cannot
   */
  public boolean canMove(Direction direction) {
    // utilize helper methods to check if movement in a particular
    // direction is possible
    if (direction == null) {
      return false;
    }

    switch (direction) {
      case UP:
        return canMoveUp();
      case RIGHT:
        return canMoveRight();
      case DOWN:
        return canMoveDown();
      case LEFT:
        return canMoveLeft();
      default:
        // If we got here, something went wrong, so return false
        return false;
    }
  }

  public void rotate(boolean clockwise) {
    int n = grid[0].length - 1;
    // transpose first.
    for (int i = 0; i < grid.length; i++) {
      for (int j = i + 1; j < grid[i].length; j++) {
        // swap.
        int tmp = grid[i][j];
        grid[i][j] = grid[j][i];
        grid[j][i] = tmp;
      }
    }

    if (clockwise) {
      // flip the matrix horizontally.
      for (int i = 0; i < grid.length; i++) {
        for (int j = 0; j < grid[i].length / 2; j++) {
          // swap.
          int tmp = grid[i][j];
          grid[i][j] = grid[i][n - j];
          grid[i][n - j] = tmp;
        }
      }
    } else {
      // flip the matrix vertically.
      n = grid.length - 1;
      for (int i = 0; i < grid.length / 2; i++) {
        for (int j = 0; j < grid[i].length; j++) {
          // swap.
          int tmp = grid[i][j];
          grid[i][j] = grid[n - i][j];
          grid[n - i][j] = tmp;
        }
      }
    }
  }

  /**
   * Name: move(Direction direction)
   *
   * Purpose: The purpose of this method is to move the tiles in the game board by a specified
   * direction passed in as a parameter. If the movement cannot be done, the method returns false.
   * If the movement can be done, it moves the tiles and returns true. This method relies on the
   * help of four other helper methods to perform the game play.
   *
   * @param direction direction the tiles will move (if possible)
   * @return true if the movement can be done and false if it cannot
   */
  public boolean move(Direction direction) {
    // if canMove is false, exit and don't move tiles
    if (!canMove(direction)) return false;

    // move in relationship to the direction passed in
    switch (direction) {
      case UP:
        moveUp();
        break;
      case RIGHT:
        moveRight();
        break;
      case DOWN:
        moveDown();
        break;
      case LEFT:
        moveLeft();
        break;
      default:
        // This should never happen
        return false;
    }

    return true;
  }

  /**
   * Name: moveRight();
   *
   * Purpose: It is a helper method for public boolean move(Direction direction). When the user hits
   * the right arrow and canMoveRight method returns true, this method will run so that all the
   * tiles move to right and merge together.
   *
   * @param: none
   * @return: none
   */
  private void moveRight() {
    rotate(true);
    moveDown();
    rotate(false);
  }

  /**
   * Name: moveLeft();
   *
   * Purpose: It is a helper method for public boolean move(Direction direction). When the user hits
   * the left arrow and canMoveLeft method returns true, this method will run so that all the tiles
   * move to left and merge together.
   *
   * @param: none
   * @return: none
   */
  private void moveLeft() {
    rotate(false);
    moveDown();
    rotate(true);
  }

  /**
   * Name: moveDown();
   *
   * Purpose: It is a helper method for public boolean move(Direction direction). When the user hits
   * the down arrow and canMoveDown method returns true, this method will run so that all the tiles
   * move down and merge together.
   *
   * @param: none
   * @return: none
   */
  private void moveDown() {
    //loop through the columns
    for (int c = 0; c < grid.length; c++) {
      //loop through the rows starting from down
      int p = grid.length - 1;
      while (p >= 0) {
        //k is a row above p
        for (int k = p - 1; k >= 0; k--) {

          //4 cases
          if (grid[k][c] == 0) {
            continue;
          }

          if (grid[p][c] > 0 && grid[p][c] != grid[k][c]) {
            break;
          }

          if (grid[k][c] > 0 && grid[p][c] == 0) {
            //move tiles down
            grid[p][c] = grid[k][c];
            grid[k][c] = 0;
            continue;
          }

          if (grid[p][c] > 0 && grid[k][c] == grid[p][c]) {
            //increment score
            score += 2 * grid[k][c];
            //merge tiles
            grid[p][c] += grid[k][c];
            grid[k][c] = 0;
            break;
          }
        }
        p--;
      }
    }
  }

  /**
   * Name: moveUp();
   *
   * Purpose: It is a helper method for public boolean move(Direction direction). When the user hits
   * the up arrow and canMoveUp method returns true, this method will run so that all the tiles move
   * up and merge together.
   *
   * @param: none
   * @return: none
   */
  private void moveUp() {
    rotate(true);
    rotate(true);
    moveDown();
    rotate(true);
    rotate(true);
  }

  /**
   * Name: canMoveLeft();
   *
   * Purpose: It is a helper method for public boolean canMove(Direction direction). It determines
   * if the board can move in the left direction and return true if the board can move.
   *
   * @param: none
   * @return: boolean
   */
  private boolean canMoveLeft() {
    //loop through rows
    for (int r = 0; r < grid.length; r++) {
      //loop through columns
      for (int c = grid.length - 1; c >= 1; c--) {

        //2 cases
        if (grid[r][c] == 0) {
          continue;
        }

        if (grid[r][c - 1] == 0 || grid[r][c - 1] == grid[r][c]) {
          return true;
        }
      }
    }

    return false;
  }

  /**
   * Name: canMoveRight();
   *
   * Purpose: It is a helper method for public boolean canMove(Direction direction). It determines
   * if the board can move in the right direction and return true if the board can move.
   *
   * @param: none
   * @return: boolean
   */
  private boolean canMoveRight() {
    //loop through rows
    for (int r = 0; r < grid.length; r++) {
      //loop through columns
      for (int c = 0; c < grid.length - 1; c++) {

        //2 cases
        if (grid[r][c] == 0) {
          continue;
        }

        if (grid[r][c + 1] == 0 || grid[r][c + 1] == grid[r][c]) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * Name: canMoveUp();
   *
   * Purpose: It is a helper method for public boolean canMove(Direction direction). It determines
   * if the board can move in the up direction and return true if the board can move.
   *
   * @param: none
   * @return: boolean
   */
  private boolean canMoveUp() {
    //loop through columns
    for (int c = 0; c < grid.length; c++) {
      //loop through rows
      for (int r = grid.length - 1; r >= 1; r--) {

        //2 cases
        if (grid[r][c] == 0) {
          continue;
        }

        if (grid[r - 1][c] == 0 || grid[r - 1][c] == grid[r][c]) {
          return true;
        }
      }
    }

    return false;
  }

  /**
   * Name: canMoveDown(); Purpose: It is a helper method for public boolean canMove(Direction
   * direction). It determines if the board can move in the down direction and return true if the
   * board can move.
   *
   * @param: none
   * @return: boolean
   */
  private boolean canMoveDown() {
    //loop through columns
    for (int c = 0; c < grid.length; c++) {
      //loop through rows
      for (int r = 0; r < grid.length - 1; r++) {

        //2 cases
        if (grid[r][c] == 0) {
          continue;
        }

        if (grid[r + 1][c] == 0 || grid[r + 1][c] == grid[r][c]) {
          return true;
        }
      }
    }

    return false;
  }

  @Override
  public String toString() {
    StringBuilder outputString = new StringBuilder();
    outputString.append(String.format("Score: %d\n", score));
    for (int row = 0; row < GRID_SIZE; row++) {
      for (int column = 0; column < GRID_SIZE; column++)
        outputString.append(grid[row][column] == 0 ? "    -"
            : String.format("%5d", grid[row][column]));

      outputString.append("\n");
    }
    return outputString.toString();
  }

  /**
   * Set the grid to a new value.  This method can be used for testing and is used by our
   * testing/grading script.
   *
   * @param newGrid The values to set the grid to
   */
  public void setGrid(int[][] newGrid) {
    if (newGrid.length != GRID_SIZE ||
        newGrid[0].length != GRID_SIZE) {
      System.out.println("Attempt to set grid to incorrect size");
      return;
    }
    this.grid = new int[GRID_SIZE][GRID_SIZE];
    for (int r = 0; r < grid.length; r++) {
      for (int c = 0; c < grid[r].length; c++) {
        grid[r][c] = newGrid[r][c];
      }
    }
  }

  /**
   * get a copy of the grid
   *
   * @return A copy of the grid
   */
  public int[][] getGrid() {
    int[][] gridCopy = new int[GRID_SIZE][GRID_SIZE];
    for (int r = 0; r < grid.length; r++) {
      for (int c = 0; c < grid[r].length; c++) {
        gridCopy[r][c] = grid[r][c];
      }
    }
    return gridCopy;
  }
}
