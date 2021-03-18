import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Painter {
    ArrayList<Image> cars = new ArrayList<>();
    ArrayList<Image> forest = new ArrayList<>();

    Painter() throws IOException {
        String filePath = "C:\\Users\\forStudy\\IdeaProjects\\My_CrossyRoad_2021_03_18\\data\\";
        cars.add(ImageIO.read(new File(filePath + "Cars\\Car_03.bmp")));
        cars.add(ImageIO.read(new File(filePath + "Cars\\Car_02.jfif")));
    }

    public void draw(Graphics2D g2d, int x, int y, int w, int h, String s, int number){
        if(s.equals("car")){
            Image test = cars.get(number).getScaledInstance(w, h, Image.SCALE_SMOOTH);
            g2d.drawImage(test, x, y, null);
        }
    }
}
