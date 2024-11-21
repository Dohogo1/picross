import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class HintPanel extends JPanel {
    private List<List<Integer>> hint;
    int gridSize;
    Grid solution;
    boolean horizontal;

    public HintPanel(Grid solution, int gridSize, boolean horizontal) {
        this.gridSize = gridSize;
        this.solution = solution;
        this.horizontal = horizontal;
        setSize(gridSize*50, gridSize);
        hint = makeHints();
        if (horizontal) {
            setLayout(new GridLayout(gridSize, 1));
            for (int i = 0; i < gridSize; i++) {
                JLabel label = new JLabel(formatHint(hint.get(i)));
                label.setHorizontalAlignment(JLabel.RIGHT);
                label.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
                label.setVerticalAlignment(JLabel.CENTER);
                add(label);
            }
            setSize(100,700);
        } else {
            setLayout(new GridLayout(1, gridSize ));
            for (int i = 0; i < gridSize; i++) {
                JLabel label = new JLabel(formatHint(hint.get(i)));
                label.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
                label.setHorizontalAlignment(JLabel.CENTER);
                label.setVerticalAlignment(JLabel.BOTTOM);
                add(label);
            }
            setSize(700,100);
        }

    }

    private List<List<Integer>> makeHints() {
        List<List<Integer>> hints = new LinkedList<>();
        int num = 0;
        for (int i = 0; i < gridSize; i++) {
            LinkedList<Integer> temp = new LinkedList<>();
            Cell[] row = horizontal ? solution.getRow(i) : solution.getCol(i);
            for (int j = 0; j < gridSize; j++) {
                if (row[j].isFilled()) {
                    num++;
                } else if (num > 0) {
                    temp.add(num);
                    num = 0;
                }
            }
            if (num > 0) {
                temp.add(num);
                num = 0;
            }
            if (temp.isEmpty()) temp.add(num);
            hints.add(temp);
        }
        return hints;
    }

    private String formatHint(List<Integer> list) {
        StringBuilder sb = new StringBuilder("<html>");
        for (Integer integer : list) {
            sb.append(integer).append(" ");
            if (!horizontal) {
                sb.append("<BR>");
            }
        }
        return sb.toString();
    }

    public List<List<Integer>> getHints() {
        return hint;
    }


}