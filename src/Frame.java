import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

public class Frame extends JFrame implements KeyEventDispatcher {
    public Man man;
    public ArrayList<Road> roads;

    public Frame() {
        this.setTitle("Crossy_Road");
        this.setSize(700, 1008);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);

        this.man = new Man(301, 941);
        this.roads = new ArrayList<>();
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
        }
        if ((man.y > getHeight())) {
            man.start();
        }
        if (man.y < 21) {
            newGame();
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
        g2d.drawRect(1, 30, getWidth() - 1, getHeight() - 1);

        for (int i = 0; i < roads.size(); i = i + 1) {
            roads.get(i).drawRoad(g2d);
        }
        man.drawMan(g2d);


        g.dispose();
        bufferStrategy.show();
    }

    public void updateState() {
        for (int i = 0; i < roads.size(); i = i + 1) {
            roads.get(i).updateRoad();
            if (man.checkCollisionRoad(roads.get(i))) {
                man.start();
            }
        }
    }

    public void startRoads() {
        roads.clear();
        int otstup = 101;
        //roads.add(new Road(otstup, getWidth()));
        for(int i = 0; i < 40; i = i + 1){
            double d1 = Math.random();
            if(d1 > 0.66){
                roads.add(new Road(otstup + (i+1) * 20, getWidth()));
                roads.add(new Road(otstup + (i+2) * 20, getWidth()));
            }else if(d1 < 0.33){
                roads.add(new Road(otstup + i * 20, getWidth()));
                roads.add(new Road(otstup + (i+1) * 20, getWidth()));
            }else{
                roads.add(new Road(otstup + i * 20, getWidth()));
                roads.add(new Road(otstup + (i+2) * 20, getWidth()));
            }
            i = i + 3;
        }
    }

    public void newGame() {
        man.start();
        startRoads();
    }
}
