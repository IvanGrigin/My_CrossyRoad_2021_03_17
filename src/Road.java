import java.awt.*;

public class Road {
    public Car[] cars;
    int n = 8;
    public int y;
    public int width;
    public int height;
    public int speed;


    public Road(int y0, int width0){
        this.y = y0;
        this.width = width0;
        this.height = 20;
        cars = new Car[n];
        newCars(width0);
        int sign = 1;
        for(int i = 1; i < (Math.random()*10); i = i + 1){
            sign = sign * (-1);
        }
        speed = sign*((int)(Math.random()*4) + 1);
    }
    public void newCars(int width0){
        cars[0] = new Car(0, y);
        for (int i = 1; i < cars.length; i = i + 1) {
            int d1 = (int)(Math.random()*(width0/(n))) + 25;
            if (cars[i-1] != null) {
                if (!(cars[i - 1].x + cars[i - 1].w + d1 > width0)) {
                    cars[i] = new Car(cars[i - 1].x + cars[i - 1].w + d1, y);
                }
            }
        }
    }

    public void updateRoad(){
        for(int i = 0; i < cars.length; i = i + 1){
            if(cars[i] != null) {
                cars[i].updateState(speed, width);
            }
        }
    }
}
