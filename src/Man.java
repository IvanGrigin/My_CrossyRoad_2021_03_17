import java.awt.*;

public class Man {
    public int x;
    public int y;
    public int w;
    public int h;
    public int speed;
    public Man(int x0, int y0){
        this.x = x0;
        this.y = y0;
        w = 18;
        h = 18;
        speed = 20;
    }
    public void drawMan(Graphics2D g2d){
        g2d.setColor(Color.RED);
        g2d.fillOval(x, y, w, h);
    }
    public boolean checkCollisionRoad(Road r){
        boolean ret = false;
        for (int i = 0; i < r.cars.length; i = i + 1){
            if(r.cars[i] != null) {
                if (checkCollisionCar(r.cars[i])) {
                    ret = true;
                }
            }
        }
        return ret;
    }
    public boolean checkCollisionCar(Car t){
        if((this.x < t.x + t.w)&&(this.x  > t.x)&&(this.y + 2 < t.y + t.h)&&(this.y + 2 > t.y)){
            return true;
        }else {
            return false;
        }
    }
    public void start(){
        this.x = 301;
        this.y = 901;
    }
}
