package main;

import java.awt.*;

public class Solver {

    /**
     *  Check if the input is correct, if not returns the number of differences with solution
     * @param input
     * @param solution
     * @return the number of differences between input and solution
     */
    public static int solve(Grid input, Grid solution) {
        int gridSize = input.getGridSize();
        HintPanel colHint = new HintPanel(solution, false);
        HintPanel rowHint = new HintPanel(solution, true);
        HintPanel inputColHint = new HintPanel(input,false);
        HintPanel inputRowHint = new HintPanel(input,true);
        int mistakes = 0; // Number of mistakes found

        // Checking if the hints had an other correct solution
        if(inputColHint.getHints().equals(colHint.getHints()) && (inputRowHint.getHints().equals(rowHint.getHints()))){
            return 0;
        }
        for (int row = 0; row < gridSize; row++) {
            Cell[] inputRow = input.getRow(row);
            Cell[] solutionRow = solution.getRow(row);

            for (int col = 0; col < gridSize; col++) {
                Cell inputCell = inputRow[col];
                Cell solutionCell = solutionRow[col];

                // If the input cell is not filled but the solution cell is
                if (!inputCell.isFilled() && solutionCell.isFilled()) {
                    if(inputCell.isMarked()) inputCell.mark();
                    inputCell.setBackground(new Color(170,0,130)); // Highlight as missing
                    mistakes++;
                }

                // If the input cell is filled but the solution cell is not
                else if (inputCell.isFilled() && !solutionCell.isFilled()) {
                    inputCell.setText("X"); // Mark as incorrect
                    inputCell.setForeground(new Color(170,0,130)); // Color it wrong
                    inputCell.setBackground(Color.WHITE); // Clear background
                    mistakes++;
                }
            }
        }
        return mistakes;
    }
}
