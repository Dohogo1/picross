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
        hint = getHints();
        System.out.println(hint);
        if (horizontal) {
            setLayout(new GridLayout(gridSize, 1));
            for (int i = 0; i < gridSize; i++) {
                JLabel label = new JLabel(formatHint(hint.get(i)));
              //  label.setHorizontalAlignment(JLabel.EAST);
                label.setVerticalAlignment(JLabel.CENTER);
                add(label);
            }
        } else {
            setLayout(new GridLayout(1, gridSize ));
            //add(new JLabel(""));
            for (int i = 0; i < gridSize; i++) {
                JLabel label = new JLabel(formatHint(hint.get(i)));
                label.setHorizontalAlignment(JLabel.CENTER);
                label.setVerticalAlignment(JLabel.BOTTOM);
                add(label);

            }
        }
        setSize(500,500);
    }

    private List<List<Integer>> getHints() {
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
       // String string = "";
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i)).append(" ");
            if(!horizontal) {
                sb.append("\n");
            }
        }

        return sb.toString();
    }

}