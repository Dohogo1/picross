import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class PicrossApp extends JFrame {
    private static  int GRID_SIZE = 20;
    Grid input;
    Grid solution;

    public PicrossApp() {
        setTitle("Picross");
        setVisible(true);
        setSize(700,700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        //setResizable(false);
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JButton saveButton = new JButton("Save");
        JButton loadButton = new JButton("Load");
        JButton newButton = new JButton("New Game");
        newButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the current JFrame
                new PicrossApp(); // Create a new instance of the application
            }
        });
        JButton solveButton = new JButton("Show Solution");
        solveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int i = solve(input,solution);
                System.out.println("Number of mistakes: " + i);
            }
        });
        Integer[] list = {1,2,3,4,5};

        JComboBox<Integer> box = new JComboBox<>(list);
        box.setSize(5,50);

        fileMenu.add(loadButton);
        fileMenu.add(saveButton);
        menuBar.add(fileMenu);
        menuBar.add(solveButton);
        menuBar.add(newButton);
        menuBar.add(box);
        setJMenuBar(menuBar);

        solution = new Grid(GRID_SIZE);
        solution.setSize(600,600);
        solution.randomizeGrid();
        input = new Grid(GRID_SIZE);
        input.setSize(600,600);
        add(input, BorderLayout.CENTER);

        HintPanel colHint = new HintPanel(solution,GRID_SIZE, false);
        HintPanel rowHint = new HintPanel(solution,GRID_SIZE, true);
        JPanel sidePanel = new JPanel();
        JPanel bottomPanel = new JPanel();
        add(sidePanel, BorderLayout.EAST);
        add(bottomPanel, BorderLayout.SOUTH);
        add(colHint,BorderLayout.NORTH);
        add(rowHint,BorderLayout.WEST);
        System.out.println(solution.toString());
    }

    public static void main(String[] args) {
        PicrossApp app = new PicrossApp();
    }

    private int solve(Grid input, Grid solution) {
        int i = 0;
        for (int row = 0; row < GRID_SIZE; row++) {
            Cell[] inputRow = input.getRow(row);
            Cell[] solutionRow = solution.getRow(row);

            for (int col = 0; col < GRID_SIZE; col++) {
                Cell inputCell = inputRow[col];
                Cell solutionCell = solutionRow[col];

                // If the input cell is not filled but the solution cell is
                if (!inputCell.isFilled() && solutionCell.isFilled()) {
                    if(inputCell.isMarked()) inputCell.mark();
                    inputCell.setBackground(Color.RED); // Highlight as missing
                    i++;
                }

                // If the input cell is filled but the solution cell is not
                else if (inputCell.isFilled() && !solutionCell.isFilled()) {
                    inputCell.setText("X"); // Mark as incorrect
                    inputCell.setForeground(Color.RED);
                    inputCell.setBackground(Color.WHITE);
                    i++;
                }
            }
        }
        return i;
    }
}
