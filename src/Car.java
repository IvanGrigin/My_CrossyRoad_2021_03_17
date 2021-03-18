import java.awt.*;

public class Car {
    public int x;
    public int y;
    public int w;
    public int h;
    public int number;
    public Car(int x0, int y0){
        this.x = x0;
        this.y = y0;
        number = 0;
        w = 38;
        h = 20;
    }
    public void drawCar(Graphics2D g2d, int w0){
        g2d.setColor(Color.BLUE);
        g2d.fillRect(x, y, w, h);
        g2d.fillRect(x - w0, y, w, h);
        g2d.fillRect(x + w0, y, w, h);

    }
    public void updateState(int speed0, int w0){
        if(x > w0){ x = x - w0; }
        if(x < 0){ x = x + w0; }
        this.x = (this.x + speed0);
    }

}
