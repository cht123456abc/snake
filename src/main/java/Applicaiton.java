import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Applicaiton {
    public static void main(String[] args) {
        final Map GAME = Map.createInstance();

        // 用一个额外线程接受键盘输入
        new Thread(new Runnable() {
            public void run() {
                // 设置键盘监听器
                GAME.addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyPressed(KeyEvent e) {
                        GAME.getSnake().keyPressed(e);
                    }
                });
            }
        }).start();


        // 设置FPS为20
        while (true) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            GAME.repaint();
        }

    }
}
