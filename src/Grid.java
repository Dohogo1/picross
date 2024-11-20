import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Grid extends JPanel {
    private Cell[][] cells;
    private int gridSize;

    public Grid(int gridSize) {
        this.gridSize = gridSize;
        this.cells = new Cell[gridSize][gridSize];
        setLayout(new GridLayout(gridSize, gridSize));
        setPreferredSize(new Dimension(gridSize * 30, gridSize * 30));
        this.setMinimumSize(new Dimension(gridSize * 30, gridSize * 30));
        for (int row = 0; row < gridSize; row++) {
            for (int col = 0; col < gridSize; col++) {
                cells[row][col] = new Cell();
                add(cells[row][col]);

                int finalRow = row;
                int finalCol = col;
                cells[row][col].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        if(e.getButton() == MouseEvent.BUTTON1) {
                            cells[finalRow][finalCol].fill();
                        }
                        else if(e.getButton() == MouseEvent.BUTTON3) {
                            cells[finalRow][finalCol].mark();
                        }
                    }
                });

            }
        }


    }

    public boolean[][] getUserGrid() {
        boolean[][] userGrid = new boolean[gridSize][gridSize];
        for (int row = 0; row < gridSize; row++) {
            for (int col = 0; col < gridSize; col++) {
                userGrid[row][col] = cells[row][col].isFilled();
            }
        }
        return userGrid;
    }

    public static boolean checkSolution(boolean[][] userGrid, int[][] solution) {
        for (int row = 0; row < solution.length; row++) {
            for (int col = 0; col < solution[row].length; col++) {
                if (userGrid[row][col] != (solution[row][col] == 1)) {
                    return false;
                }
            }
        }
        return true;
    }

    public void randomizeGrid() {
        for (int row = 0; row < gridSize; row++) {
            for (int col = 0; col < gridSize; col++) {
                if(Math.random() < 0.5) {
                    cells[row][col].fill();
                }
            }
        }
    }

    public Cell[] getRow(int row) {
        Cell[] rowCells = new Cell[gridSize];
        for (int col = 0; col < gridSize; col++) {
            rowCells[col] = cells[row][col];
        }
        return rowCells;
    }
    public Cell[] getCol(int col) {
        Cell[] colCells = new Cell[gridSize];
        for (int row = 0; row < gridSize; row++) {
            colCells[row] = cells[row][col];
        }
        return colCells;
    }

    @Override
    public String toString(){
        String str = "";
        for (int row = 0; row < gridSize; row++) {
            str = str + "\n";
            for (int col = 0; col < gridSize; col++) {
                if(cells[row][col].isFilled()) {
                    str = str + "1 ";
                }
                else{
                    str = str + "0 ";
                }
            }
        }
        return str;
    }




}
