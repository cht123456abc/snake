import java.awt.*;
import java.awt.event.KeyEvent;

public class Snake {
    private Node head;
    private Node tail;

    private Dir dir;

    public Snake() {
        this.head = new Node(20, 20);
        this.tail = head;
        dir = Dir.L;// 默认向左
    }

    public void eat(Egg e) {
        if (e.col == this.head.getCol() && e.row == this.head.getRow()) {
            addToHead();
            e.reAppear();
        }
    }

    public void draw(Graphics g) {
        Node n = head;
        while (n != null) {
            n.draw(g);
            n = n.next;
        }

        // 移动
        this.move();

    }

    private void boundary() {
        if (this.head.getCol() < 0) {
            this.head.setCol(Constant.NodeCount - 1);
        } else if (this.head.getCol() >= Constant.NodeCount){
            this.head.setCol(0);
        }

        if (this.head.getRow() < 0) {
            this.head.setRow(Constant.NodeCount - 1);
        } else if (this.head.getRow() > Constant.NodeCount - 1) {
            this.head.setRow(0);
        }
    }

    public void move() {
        addToHead();
        deleteTail();

        boundary();
    }

    private void deleteTail() {
        Node prev = this.tail.prev;
        tail.prev = null;
        prev.next = null;
        tail = prev;
    }

    private void addToHead() {
        Node n;
        switch (dir) {
            case L:
                n = new Node(this.head.getRow(),this.head.getCol() - 1);
                break;
            case R:
                n = new Node(this.head.getRow(), this.head.getCol() + 1);
                break;
            case U:
                n = new Node(this.head.getRow() - 1, this.head.getCol());
                break;
            case D:
                n = new Node(this.head.getRow() + 1, this.head.getCol());
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + dir);
        }
        n.next = head;
        head.prev = n;
        head = n;
    }

    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_LEFT:
                dir = Dir.L;
                break;
            case KeyEvent.VK_RIGHT:
                dir = Dir.R;
                break;
            case KeyEvent.VK_UP:
                dir = Dir.U;
                break;
            case KeyEvent.VK_DOWN:
                dir = Dir.D;
                break;
        }
    }
}
