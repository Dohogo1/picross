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
        setFont(new Font("Courier", Font.PLAIN, 50));
    }

    public void fill() {
        if (!filled) {
            filled = true;
            marked = false;
            setBackground(Color.BLACK);
            setText(null);
        }
        else{
            filled = false;
            setBackground(Color.WHITE);
        }
    }

    public void mark() {
        if (!marked) {
            marked = true;
            filled = false;
            setBackground(Color.WHITE);
            setText("X");
        }
        else{
            marked = false;
            setIcon(null);
            setBackground(Color.WHITE);
            setText(null);
        }
    }

    public boolean isFilled() {
        return filled;
    }

    public boolean isMarked() {
        return marked;
    }
}
