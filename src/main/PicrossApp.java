package main;

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
    int cellSize = 20;

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
        setSize(380+cellSize*gridSize,220+cellSize*gridSize);
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
            int i = Solver.solve(input,solution);
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

        colHint = new HintPanel(solution, false);
        rowHint = new HintPanel(solution, true);

        input.setBounds(333, 150, cellSize*gridSize, cellSize*gridSize);
        colHint.setBounds(333, 0, cellSize*gridSize, 150);
        rowHint.setBounds(180, 150, 150, cellSize*gridSize);
        saveButton.setBounds(30, 10, 150, 45);
        loadButton.setBounds(30, 60, 150, 45);
        solveButton.setBounds(30, 110, 150, 45);
        mistakesTextArea.setBounds(30, 160, 150, 25);
        newButton.setBounds(30, 190, 150, 45);
        sizeSpinner.setBounds(30, 240, 150, 25);

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


    @Override
    public void run() {
        setVisible(true);
    }
}