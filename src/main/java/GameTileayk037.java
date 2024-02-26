/**
 * GameTileayk037.java
 *
 * GameTile class that is in charge of creating tiles for 2048.
 *
 * @author Yong Hoon Do, yhdo@ucsd.edu
 * @author A Kim, ayk037@ucsd.edu
 * @since Oct 30, 2017
 */

import java.util.HashMap;
import javafx.scene.paint.Color;

/**
 * A GameTile is a StackPane that groups together the visual items needed to display a 2048 tile.
 */
public class GameTileayk037 extends GameTile {

  public static final int WIDTH = 100;
  public static final int HEIGHT = 100;
  public static final int FONT_SIZE = 35;

  // Hashmap that uses tilevalue as the key to access appropriate color
  // Check out the populateColors method that populates the HashMap
  private static HashMap<Integer, Color> colors =
      new HashMap<Integer, Color>();


  public static GameTile makeNewTile(int tileValue) {
    return new GameTileayk037(tileValue);
  }

  /**
   * Creates the tile with a tile value.
   *
   * @param tileValue A tile value.
   */
  public GameTileayk037(int tileValue) {
    //calls the empty constructor
    super(tileValue);
  }

  /* Name: populateColors()
   *
   * Purpose: The purpose of this method is to populate the HashMap
   * with RGB values pertaining to certain tileValues. For example,
   * the tileValue 2 has an RGB value of (238, 228, 218). Therefore,
   * if we want to access the color of tileValue 2 from the hashmap,
   * we would say colors.get(2) and it would return the color object
   * Color.rgb(238, 228, 218).
   *
   * You are free to change the RGB values of each tileValue as you wish.
   *
   * Parameters: None
   *
   * Return: None
   */
  public static void populateColors() {
    colors.put(0, Color.rgb(238, 228, 218, 0.35)); //empty tile
    colors.put(2, Color.rgb(238, 228, 218));
    colors.put(4, Color.rgb(237, 224, 200));
    colors.put(8, Color.rgb(242, 177, 121));
    colors.put(16, Color.rgb(245, 149, 99));
    colors.put(32, Color.rgb(246, 124, 95));
    colors.put(64, Color.rgb(246, 94, 59));
    colors.put(128, Color.rgb(237, 207, 114));
    colors.put(256, Color.rgb(237, 204, 97));
    colors.put(512, Color.rgb(237, 200, 80));
    colors.put(1024, Color.rgb(237, 197, 63));
    colors.put(2048, Color.rgb(237, 194, 46));
    colors.put(4096, Color.rgb(237, 194, 46));
    colors.put(8192, Color.rgb(237, 194, 46));
  }
}
