import javax.swing.*;
import java.awt.*;

/**
 *
 */
public class PicrossApp extends JFrame implements Runnable {
    static int gridSize = 5;
    Grid input;
    Grid solution;
    HintPanel colHint;
    HintPanel rowHint;

    /**
     *  Constructor that creates an instance with given solution and input grid
     * @param input
     * @param solution
     */
    public PicrossApp(Grid input, Grid solution) {
        this.input = input;
        this.solution = solution;
        gridSize = input.getRow(0).length;
        input.setCells();
        initFrame();
    }

    /**
     *  Constructor that creates an instance with random solution and a blank input grid
     */
    public PicrossApp() {
        solution = new Grid(gridSize);
        solution.randomizeGrid();
        input = new Grid(gridSize);
        initFrame();
    }

    /**
     *  Initialises the frame
     */
    private void initFrame() {
        setTitle("Picross");
        setSize(380+32*gridSize,220+32*gridSize);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(220,255,220));
        setResizable(false);

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(e -> {
            Saver.save("savefile", input, solution);
        });
        JButton loadButton = new JButton("Load");
        loadButton.addActionListener(e -> {
            Grid[] grids = Saver.load("savefile");
            if(grids == null) {return;}
            input = grids[0];
            solution = grids[1];
            dispose(); // Close the current JFrame
            new PicrossApp(input,solution).setVisible(true); // Create a new instance of the application
        });
        JButton newButton = new JButton("New Game");
        newButton.addActionListener(e -> {
            dispose(); // Close the current JFrame
            new PicrossApp().setVisible(true); // Create a new instance of the application

        });
        TextField mistakesTextArea = new TextField("Mistakes: ");

        mistakesTextArea.setEditable(false);
        mistakesTextArea.setBackground(Color.WHITE);
        mistakesTextArea.setFont(new Font("",Font.PLAIN,15));
        mistakesTextArea.repaint();

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

        colHint = new HintPanel(solution, gridSize, false);
        rowHint = new HintPanel(solution, gridSize, true);

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
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new PicrossApp());
    }

    /**
     *  Check if the input is correct, if not returns the number of differences with solution
     * @param input
     * @param solution
     * @return the number of differences between input and solution
     */
    private int solve(Grid input, Grid solution) {
        int mistakes = 0; // Number of mistakes found
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
                    inputCell.setForeground(new Color(170,0,130)); // Color it wrong
                    inputCell.setBackground(Color.WHITE); // Clear background
                    mistakes++;
                }
            }
        }
        return mistakes;
    }

    @Override
    public void run() {
        setVisible(true);
    }
}