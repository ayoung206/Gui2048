//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.util.HashMap;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class GameTiletnn050 extends GameTile {
  private static HashMap<Integer, Color> colors = new HashMap();

  public GameTiletnn050(int var1) {
    populateColors();
    Circle var2 = new Circle(50.0D);
    if (var1 >= 0 && (var1 & var1 - 1) == 0 && var1 <= 8192) {
      Color var3 = (Color)colors.get(var1);
      var2.setFill(var3);
    } else {
      var2.setFill(Color.BLACK);
    }

    this.getChildren().add(var2);
    if (var1 > 0) {
      String var5 = Integer.toString(var1);
      Text var4 = new Text(var5);
      var4.setFont(Font.font("Georgia", FontWeight.BOLD, 23.0D));
      var4.setFill(Color.rgb(255, 180, 0));
      this.getChildren().add(var4);
    }

  }

  public static void populateColors() {
    colors.put(Integer.valueOf(0), Color.rgb(255, 255, 255, 0.35D));
    colors.put(Integer.valueOf(2), Color.rgb(251, 195, 219));
    colors.put(Integer.valueOf(4), Color.rgb(250, 161, 200));
    colors.put(Integer.valueOf(8), Color.rgb(249, 126, 180));
    colors.put(Integer.valueOf(16), Color.rgb(253, 91, 162));
    colors.put(Integer.valueOf(32), Color.rgb(251, 56, 142));
    colors.put(Integer.valueOf(64), Color.rgb(250, 28, 126));
    colors.put(Integer.valueOf(128), Color.rgb(232, 2, 103));
    colors.put(Integer.valueOf(256), Color.rgb(198, 1, 87));
    colors.put(Integer.valueOf(512), Color.rgb(165, 1, 73));
    colors.put(Integer.valueOf(1024), Color.rgb(136, 2, 61));
    colors.put(Integer.valueOf(2048), Color.rgb(111, 0, 49));
    colors.put(Integer.valueOf(4096), Color.rgb(77, 0, 34));
    colors.put(Integer.valueOf(8192), Color.rgb(59, 1, 27));
  }

  public static GameTile makeNewTile(int var0) {
    GameTiletnn050 var1 = new GameTiletnn050(var0);
    return var1;
  }
}
