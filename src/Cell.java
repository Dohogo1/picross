import javax.swing.*;
import java.awt.*;

public class Cell extends JLabel {
    private boolean filled;
    private boolean marked;
    private final int row;
    private final int col;

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
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
            Image image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("Adolf.png"));
            setBackground(Color.WHITE);
            setIcon(new ImageIcon(image));
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
