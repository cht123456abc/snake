import java.awt.*;

/**
 * 双向链表
 */
public class Node {
    private int row, col;
    protected Node prev, next;
    private final Color COLOR = Color.BLUE;

    public Node(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public void draw(Graphics g) {
        int x = Constant.X + col * Constant.NodeSize;
        int y = Constant.Y + row * Constant.NodeSize;
        Color c = g.getColor();
        g.setColor(this.COLOR);
        g.fillRect(x, y, Constant.NodeSize, Constant.NodeSize);
        g.setColor(c);
    }
}
