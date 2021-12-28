
import java.awt.Rectangle;

public class brick {
    int x;
    int y;
    private boolean available;

    public brick(int x, int y) {
        initBrick(x, y);
    }

    private void initBrick(int x, int y) {
        this.x = x;
        this.y = y;
        getRect();
        available = true;

    }

    public Rectangle getRect() {
        Rectangle brickRect = new Rectangle(x, y, 80, 40);
        return brickRect;
    }

    boolean isAvailable() {
        return available;
    }

    void setAvailable(boolean value) {
        available = value;
    }
}
