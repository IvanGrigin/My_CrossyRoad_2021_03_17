public class Main {
    public static void main(String[] args) throws InterruptedException {
        Frame frame = new Frame();
        while (true){
            frame.repaint();
            frame.updateState();
            Thread.sleep(20);
        }
    }
}