import javax.swing.*;

public class Frame extends JFrame {
    public Frame() {
        setTitle("Picross");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Frame();
        System.out.println("asdasd");
    }


}
