import java.awt.*;
import java.util.ArrayList;

public class River {

    public int y;
    public int width;
    public int height;
    ArrayList<Bridge> bridges;


    public River(int y0, int width0) {
        bridges = new ArrayList<>();
        if(Math.random() > 0.3){
            bridges.add(new Bridge(((int )(Math.random()*width0/2)),y0));
            bridges.add(new Bridge(((int )(Math.random() * width0/2 + width0/2)),y0));
        }else {
            bridges.add(new Bridge(((int )(Math.random() * width0)),y0));
        }
        this.y = y0;
        this.width = width0;
        this.height = 20;
    }
    public void draw(Graphics2D g2d){
        g2d.setColor(new Color(250,100,0));
        g2d.fillRect(0,y,width,height);
        for(int i = 0; i < bridges.size(); i = i + 1){
            bridges.get(i).draw(g2d);
        }
    }
}
