import javax.swing.*;
import java.awt.*;

public class Cell extends JButton {
    private boolean filled;
    private boolean marked;

    public Cell() {
        this.filled = false;
        this.marked = false;
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setOpaque(true);
    }

    public void fill() {
        if (!filled) {
            filled = true;
            marked = false;
            setBackground(Color.BLACK);
            setIcon(null);
            repaint();
        }
        else{
            filled = false;
            setBackground(Color.WHITE);
            repaint();
        }
    }



    public void mark() {
        if (!marked) {
            marked = true;
            filled = false;
            //Image image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("Adolf.png"));
            setBackground(Color.WHITE);
            setLabel("X");
            repaint();
        }
        else{
            marked = false;
            setIcon(null);
            setBackground(Color.WHITE);
            repaint();
        }
    }

    public boolean isFilled() {
        return filled;
    }
}
