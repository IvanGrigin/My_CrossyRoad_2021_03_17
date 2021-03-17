import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

public class Frame extends JFrame implements KeyEventDispatcher {
    public Man man;
    public ArrayList<Road> roads;

    public Frame(){
        this.setTitle("Crossy_Road");
        this.setSize(700,1008);
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
                man.x = man.x - man.speed ;
            } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                man.x = man.x + man.speed ;
            } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                man.y = man.y - man.speed ;
            } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                man.y = man.y + man.speed ;
            }
        }
        if((man.x < 0)||(man.x > getWidth())){
            man.start();
        }
        if((man.y > getHeight())){
            man.start();
        }
        if(man.y < 21){
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
        g2d.fillRect(0,0,getWidth(), getHeight());
        g2d.setColor(Color.BLACK);
        g2d.drawRect(1,30,getWidth()-1,getHeight()-1);

        for(int i = 0; i < roads.size(); i = i + 1){
            roads.get(i).drawRoad(g2d);
        }
        man.drawMan(g2d);


        g.dispose();
        bufferStrategy.show();
    }

    public void updateState(){
        for(int i = 0; i < roads.size(); i = i + 1){
            roads.get(i).updateRoad();
            if(man.checkCollisionRoad(roads.get(i))){
                man.start();
            }
        }
    }

    public void startRoads(){
        roads.clear();
        roads.add(new Road(101,getWidth()));
        int d1;
        while(true) {
            if(roads.get(roads.size()-1).y > getHeight()-40){
                break;
            }
            d1 = (int) (Math.random() * 5) + 1;
            Road test = new Road(roads.get(roads.size() - 1).y + d1 * 20, getWidth());
            roads.add(test);

        }
    }
    public void newGame(){
        man.start();
        startRoads();
    }
}
