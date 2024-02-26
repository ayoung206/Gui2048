//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.util.HashMap;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class GameTilekbmelend extends GameTile {
  private static HashMap<Integer, Color> colors = new HashMap();

  public GameTilekbmelend(int var1) {
    Circle var2 = new Circle(50.0D);
    populateColors();
    var2.setFill((Paint)colors.get(var1));
    Text var3 = null;
    if (var1 != 0) {
      var3 = new Text(Integer.toString(var1));
      var3.setFont(Font.font("Lucida Calligraphy", FontWeight.BOLD, 30.0D));
      var3.setFill(Color.rgb(100, 100, 100));
    }

    this.getChildren().add(var2);
    if (var1 != 0) {
      this.getChildren().add(var3);
    }

  }

  public static GameTile makeNewTile(int var0) {
    GameTilekbmelend var1 = new GameTilekbmelend(var0);
    return var1;
  }

  public static void populateColors() {
    colors.put(Integer.valueOf(0), Color.rgb(238, 228, 218, 0.75D));
    colors.put(Integer.valueOf(2), Color.rgb(240, 253, 55));
    colors.put(Integer.valueOf(4), Color.rgb(255, 204, 229));
    colors.put(Integer.valueOf(8), Color.rgb(204, 255, 204));
    colors.put(Integer.valueOf(16), Color.rgb(255, 230, 179));
    colors.put(Integer.valueOf(32), Color.rgb(213, 114, 216));
    colors.put(Integer.valueOf(64), Color.rgb(121, 229, 255));
    colors.put(Integer.valueOf(128), Color.rgb(255, 255, 153));
    colors.put(Integer.valueOf(256), Color.rgb(255, 153, 204));
    colors.put(Integer.valueOf(512), Color.rgb(51, 255, 175));
    colors.put(Integer.valueOf(1024), Color.rgb(255, 220, 110));
    colors.put(Integer.valueOf(2048), Color.rgb(255, 60, 105));
    colors.put(Integer.valueOf(4096), Color.rgb(0, 230, 0));
    colors.put(Integer.valueOf(8192), Color.rgb(204, 153, 0));
  }
}
