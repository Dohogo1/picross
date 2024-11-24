import javax.swing.*;
import java.awt.*;

public class PicrossApp extends JFrame {
    static int gridSize = 5;
    Grid input;
    Grid solution;
    HintPanel colHint;
    HintPanel rowHint;

    public PicrossApp() {
        setTitle("Picross");
        setVisible(true);
        setSize(380+32*gridSize,220+32*gridSize);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(220,255,220));
        setResizable(true);

        JPanel panel = new JPanel();
        panel.setVisible(true);
        panel.setLayout(null);

        JButton saveButton = new JButton("Save");
        JButton loadButton = new JButton("Load");
        JButton newButton = new JButton("New Game");
        newButton.addActionListener(e -> {
            dispose(); // Close the current JFrame
            new PicrossApp(); // Create a new instance of the application
        });

        TextField mistakesTextArea = new TextField("Mistakes: ");

        JButton solveButton = new JButton("Show Solution");
        solveButton.addActionListener(e -> {
            int i = solve(input,solution);
            System.out.println("Number of mistakes: " + i);
            mistakesTextArea.setText("Mistakes: " + i + '/' + gridSize*gridSize);
            mistakesTextArea.repaint();
        });

        JSpinner sizeSpinner = new JSpinner(new SpinnerNumberModel(gridSize,5,25,1));
        sizeSpinner.addChangeListener(e -> {
            gridSize = (int)sizeSpinner.getValue();
        });

        Color purple = new Color(240,190,255);

        saveButton.setBackground(purple);
        loadButton.setBackground(purple);
        newButton.setBackground(purple);
        solveButton.setBackground(purple);
        sizeSpinner.getComponent(1).setBackground(purple);
        sizeSpinner.getComponent(0).setBackground(purple);

        solution = new Grid(gridSize);
        solution.randomizeGrid();
        input = new Grid(gridSize);

        colHint = new HintPanel(solution, gridSize, false);
        rowHint = new HintPanel(solution, gridSize, true);

       // TextField mistakesTextArea = new TextField("Number of mistakes: "+mistakes);
        mistakesTextArea.setEditable(false);
        mistakesTextArea.setBackground(Color.WHITE);
        mistakesTextArea.setFont(new Font("",Font.PLAIN,15));
        mistakesTextArea.repaint();

        input.setBounds(333, 150, 32*gridSize, 32*gridSize);
        colHint.setBounds(333, 0, 32*gridSize, 150);
        rowHint.setBounds(180, 150, 150, 32*gridSize);
        saveButton.setBounds(30, 10, 150, 50);
        loadButton.setBounds(30, 70, 150, 50);
        solveButton.setBounds(30, 130, 150, 50);
        mistakesTextArea.setBounds(30, 190, 150, 30);
        newButton.setBounds(30, 230, 150, 50);
        sizeSpinner.setBounds(30, 290, 150, 30);

        add(input);
        add(colHint);
        add(rowHint);
        add(solveButton);
        add(loadButton);
        add(newButton);
        add(saveButton);
        add(sizeSpinner);
        add(mistakesTextArea);

        System.out.println(solution.toString());
    }

    public static void main(String[] args) {
        PicrossApp app = new PicrossApp();
    }

    private int solve(Grid input, Grid solution) {
        int mistakes = 0;
        HintPanel inputColHint = new HintPanel(input, gridSize,false);
        HintPanel inputRowHint = new HintPanel(input, gridSize,true);

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
                    inputCell.setForeground(new Color(170,0,130));
                    inputCell.setBackground(Color.WHITE);
                    mistakes++;
                }
            }
        }
        return mistakes;
    }
}