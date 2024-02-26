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

public class GameTileyokina extends GameTile {
  private static HashMap<Integer, Color> colors = new HashMap();

  public GameTileyokina(int var1) {
    Circle var2 = new Circle(30.0D, 30.0D, 50.0D);
    populateColors();
    Color var3 = (Color)colors.get(var1);
    var2.setFill(var3);
    this.getChildren().add(var2);
    if (var1 != 0) {
      String var4 = Integer.toString(var1);
      Text var5 = new Text(var4);
      var5.setFont(Font.font("Helvectica", FontWeight.BOLD, 18.0D));
      var5.setFill(Color.BLACK);
      this.getChildren().add(var5);
    }

  }

  public static void populateColors() {
    colors.put(Integer.valueOf(0), Color.rgb(238, 205, 110, 0.35D));
    colors.put(Integer.valueOf(2), Color.rgb(163, 253, 223));
    colors.put(Integer.valueOf(4), Color.rgb(116, 218, 190));
    colors.put(Integer.valueOf(8), Color.rgb(91, 199, 174));
    colors.put(Integer.valueOf(16), Color.rgb(59, 174, 147));
    colors.put(Integer.valueOf(32), Color.rgb(135, 250, 196));
    colors.put(Integer.valueOf(64), Color.rgb(104, 225, 168));
    colors.put(Integer.valueOf(128), Color.rgb(63, 196, 134));
    colors.put(Integer.valueOf(256), Color.rgb(33, 172, 107));
    colors.put(Integer.valueOf(512), Color.rgb(225, 201, 252));
    colors.put(Integer.valueOf(1024), Color.rgb(194, 159, 235));
    colors.put(Integer.valueOf(2048), Color.rgb(158, 115, 208));
    colors.put(Integer.valueOf(4096), Color.rgb(120, 66, 182));
    colors.put(Integer.valueOf(8192), Color.rgb(82, 30, 142));
  }

  public static GameTile makeNewTile(int var0) {
    GameTileyokina var1 = new GameTileyokina(var0);
    return var1;
  }
}
