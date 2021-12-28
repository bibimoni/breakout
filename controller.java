import java.awt.event.*;

public class controller implements KeyListener {
    panel Panel;

    public controller(panel Panel) {
        this.Panel = Panel;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                Panel.moveLeft();
                break;
            case KeyEvent.VK_RIGHT:
                Panel.moveRight();
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}