import java.awt.*;

public class Forest {

    public int y;
    public int width;
    public int height;

    public Forest(int y0, int width0) {
        this.y = y0;
        this.width = width0;
        this.height = 20;
    }

    public void drawRoad(Graphics2D g2d) {
        g2d.setColor(Color.GREEN);
        g2d.fillRect(0, y, width, height);
    }
}
