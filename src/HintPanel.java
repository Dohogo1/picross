import javax.swing.*;
import java.util.LinkedList;
import java.util.List;

public class HintPanel extends JPanel {
    private List<List<Integer>> rowHint;
    private List<List<Integer>> colHint;
    int gridSize;
    Grid solution;
    public HintPanel(Grid solution, int gridSize){
        this.gridSize = gridSize;
        this.solution = solution;
        rowHint = getHints(true);
        colHint = getHints(false);
        System.out.println(rowHint);
        System.out.println(colHint);
    }

    private List<List<Integer>> getHints(boolean horizontal){
        List<List<Integer>> hints = new LinkedList<>();
        int num = 0;
        for (int i = 0; i < gridSize; i++) {
            LinkedList<Integer> temp = new LinkedList<>();
            Cell[] row = horizontal ? solution.getRow(i) : solution.getCol(i);
            for (int j = 0; j < gridSize; j++) {
                if(row[j].isFilled()){
                    num++;
                } else if (num > 0) {
                    temp.add(num);
                    num = 0;
                }
            }
            if(num > 0){
                temp.add(num);
                num = 0;
            }
            hints.add(temp);
        }
        return hints;
    }

}
