import javax.swing.*;
import java.awt.*;

public class PicrossApp {
    /*


    public PicrossApp() {


        // Create the grid

        // Create hint panels for rows and columns
        HintPanel rowHints = new HintPanel(GRID_SIZE, true, SOLUTION);
        HintPanel colHints = new HintPanel(GRID_SIZE, false, SOLUTION);

        // Create a container panel for the grid and row hints
        JPanel gridContainer = new JPanel(new BorderLayout());
        gridContainer.add(grid, BorderLayout.CENTER);
        gridContainer.add(rowHints, BorderLayout.WEST);

        // Add components to the frame
        frame.add(colHints, BorderLayout.NORTH); // Column hints go above
        frame.add(gridContainer, BorderLayout.CENTER); // Grid + Row hints go in the center

        // Add a "Check Solution" button
        JButton checkButton = new JButton("Check Solution");
        checkButton.addActionListener(e -> {
            boolean correct = Grid.checkSolution(grid.getUserGrid(), SOLUTION);
            JOptionPane.showMessageDialog(null, correct ? "Correct!" : "Try Again!");
        });
        frame.add(checkButton, BorderLayout.SOUTH);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(PicrossApp::new);
    }*/

    public static  int GRID_SIZE = 5;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Picross");
        frame.setVisible(true);
        frame.setSize(500,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        Grid solution = new Grid(GRID_SIZE);
        solution.randomizeGrid();
        frame.add(solution, BorderLayout.CENTER);
        HintPanel hint = new HintPanel(solution,GRID_SIZE);

    }
}
