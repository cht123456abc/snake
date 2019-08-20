import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Map extends Frame {
    private static Map instance;
    private static Snake s = new Snake();
    private static Egg e = Egg.getInstance();
    private static final Color BackGround = Color.WHITE;
    private static final Color Line = Color.BLACK;

    public static Map createInstance() {
        if (instance == null) {
            instance = new Map();
        }
        return instance;
    }

    public static Snake getSnake() {
        if (s == null) {
            s = new Snake();
        }
        return s;
    }

    private Map() {
        // 设置窗口大小
        this.setSize(2 * Constant.MapSize, 2 * Constant.MapSize);
        // 设置窗口可见
        this.setVisible(true);
        // 设置窗口关闭
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

    }

    @Override
    public void paint(Graphics g) {
        // 设置画布背景颜色
        g.setColor(BackGround);
        g.fillRect(0,0,this.getWidth(),this.getHeight());
        // 画表格
        g.setColor(Line);
        for (int i = 0; i <= Constant.NodeCount; i++) {
            g.drawLine(Constant.X, Constant.Y + i * Constant.NodeSize, Constant.X + Constant.MapSize, Constant.Y + i * Constant.NodeSize);
            g.drawLine(Constant.X + i * Constant.NodeSize, Constant.Y, Constant.X + i * Constant.NodeSize, Constant.Y + Constant.MapSize);
        }

        // 画蛋
        e.draw(g);
        // 画蛇
        s.draw(g);
        // 吃蛋
        s.eat(e);
    }

    // double buffer
    Image offScreenImage = null;
    @Override
    public void update(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = this.createImage(this.getWidth(), this.getHeight());
        }
        Graphics gOff = offScreenImage.getGraphics();
        paint(gOff);
        g.drawImage(offScreenImage,0,0,null);
    }
}
