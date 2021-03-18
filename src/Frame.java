import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.io.IOException;
import java.util.ArrayList;

public class Frame extends JFrame implements KeyEventDispatcher {
    public Man man;
    public ArrayList<Road> roads;
    public ArrayList<Forest> forests;
    public Painter p;
    public int numberOfLevel = 1;
    public int numberOfDeath = 0;

    public Frame() throws IOException {
        this.setTitle("Crossy_Road");
        this.setSize(700, 1008);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);

        this.p = new Painter();
        this.man = new Man(301, 941);
        this.roads = new ArrayList<>();
        this.forests = new ArrayList<>();
        startRoads();

        KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        manager.addKeyEventDispatcher(this);
    }


    @Override
    public boolean dispatchKeyEvent(KeyEvent e) {
        if (e.getID() == KeyEvent.KEY_PRESSED) { // Если кнопка была нажата (т.е. сейчас она зажата)
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                man.x = man.x - man.speed;
            } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                man.x = man.x + man.speed;
            } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                man.y = man.y - man.speed;
            } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                man.y = man.y + man.speed;
            }
        }
        if ((man.x < 0) || (man.x > getWidth())) {
            man.start();
            numberOfDeath = numberOfDeath +1;
        }
        if ((man.y > getHeight())) {
            man.start();
            numberOfDeath = numberOfDeath + 1;
        }
        if (man.y < 21) {
            newGame();
            numberOfLevel = numberOfLevel + 1;
        }


        return false;
    }

    @Override
    public void paint(Graphics g) {
        BufferStrategy bufferStrategy = getBufferStrategy();
        if (bufferStrategy == null) {
            createBufferStrategy(2);
            bufferStrategy = getBufferStrategy();
        }
        g = bufferStrategy.getDrawGraphics();
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, getWidth(), getHeight());
        g2d.setColor(Color.BLACK);
        g2d.drawString(("Level: "+numberOfLevel),10,50 );
        g2d.drawString(("Deaths: "+numberOfDeath),10,70 );

        if((roads != null)&&(forests != null)&&(man != null)) {
            for (int i = 0; i < forests.size(); i = i + 1) {
                drawForest(g2d, forests.get(i));
            }
            for (int i = 0; i < roads.size(); i = i + 1) {
                drawRoad(g2d, roads.get(i));
            }
            man.drawMan(g2d);
        }




        g.dispose();
        bufferStrategy.show();
    }

    public void updateState() {
        for (int i = 0; i < roads.size(); i = i + 1) {
            roads.get(i).updateRoad();
            if (man.checkCollisionRoad(roads.get(i))) {
                man.start();
                numberOfDeath = numberOfDeath + 1;
            }
        }
    }

    public void startRoads() {
        roads.clear();
        int otstup = 101;

        for(int i = 0; i < 40; i = i + 1){
            double d1 = Math.random();
            if(d1 > 0.66){
                forests.add(new Forest(otstup + i * 20, getWidth()));
                roads.add(new Road(otstup + (i+1) * 20, getWidth()));
                roads.add(new Road(otstup + (i+2) * 20, getWidth()));
            }else if(d1 < 0.33){
                roads.add(new Road(otstup + i * 20, getWidth()));
                roads.add(new Road(otstup + (i+1) * 20, getWidth()));
                forests.add(new Forest(otstup + (i+2) * 20, getWidth()));
            }else{
                roads.add(new Road(otstup + i * 20, getWidth()));
                forests.add(new Forest(otstup + (i+1) * 20, getWidth()));
                roads.add(new Road(otstup + (i+2) * 20, getWidth()));
            }
            forests.add(new Forest(otstup + (i+3) * 20, getWidth()));
            i = i + 3;
        }
    }

    public void newGame() {
        man.start();
        startRoads();
    }
    public void drawCar(Graphics2D g2d, Car c){
        /*
        p.draw(g2d, c.x, c.y, c.w, c.h, "car", c.number);
        p.draw(g2d, c.x - getWidth(), c.y, c.w, c.h, "car", c.number);
        p.draw(g2d, c.x + getWidth(), c.y, c.w, c.h, "car", c.number);
         */
        g2d.setColor(Color.BLUE);
        g2d.fillRect(c.x, c.y, c.w, c.h);
        g2d.fillRect(c.x - getWidth(), c.y, c.w, c.h);
        g2d.fillRect(c.x + getWidth(), c.y, c.w, c.h);

    }
    public void drawRoad(Graphics2D g2d, Road r){
        g2d.setColor(Color.LIGHT_GRAY);
        g2d.fillRect(0, r.y, r.width, r.height);
        for(int i = 0; i < r.cars.length; i = i + 1){
            if(r.cars[i] != null) {
                drawCar(g2d, r.cars[i]);
            }
        }
    }
    public void drawForest(Graphics2D g2d, Forest f) {
        g2d.setColor(Color.GREEN);
        g2d.fillRect(0, f.y, f.width, f.height);
    }
}
