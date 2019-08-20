import java.awt.*;
import java.util.Random;

/**
 * 单例
 */
public class Egg {
    private static Egg singleton;
    public static int col, row;
    private static Random random;
    private static Color COLOR;

    private Egg() {
        col = 15;
        row = 15;
        random = new Random();
        COLOR = Color.red;
    }

    public void draw(Graphics g) {
        int x = Constant.X + col * Constant.NodeSize;
        int y = Constant.Y + row * Constant.NodeSize;
        Color c = g.getColor();
        g.setColor(COLOR);
        g.fillOval(x, y, Constant.NodeSize, Constant.NodeSize);
        g.setColor(c);

    }

    public static Egg getInstance() {
        if (singleton == null) {
            singleton = new Egg();
        }
        return singleton;
    }

    public void reAppear() {
        this.row = random.nextInt(Constant.NodeCount);
        this.col = random.nextInt(Constant.NodeCount);
    }
}
