import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Cell extends JButton {
    private boolean filled;
    private boolean marked;

    public Cell() {
        this.filled = false;
        this.marked = false;
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setOpaque(true);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(e.getButton() == MouseEvent.BUTTON1) {
                    fill();
                }
                else if(e.getButton() == MouseEvent.BUTTON3) {
                    mark();
                }
            }
        });

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
