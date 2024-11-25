package main;

import javax.swing.*;
import java.awt.*;

public class Grid extends JPanel {
    private Cell[][] cells;
    private int gridSize;


    public Grid(int gridSize) {
        this.gridSize = gridSize;
        this.cells = new Cell[gridSize][gridSize];
        setLayout(new GridLayout(gridSize, gridSize));
        for (int row = 0; row < gridSize; row++) {
            for (int col = 0; col < gridSize; col++) {
                cells[row][col] = new Cell();
                cells[row][col].setListener();
                add(cells[row][col]);
            }
        }
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
        System.arraycopy(cells[row], 0, rowCells, 0, gridSize);
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
        StringBuilder str = new StringBuilder();
        for (int row = 0; row < gridSize; row++) {
            str.append("\n");
            for (int col = 0; col < gridSize; col++) {
                if(cells[row][col].isFilled()) {
                    str.append("1 ");
                }
                else{
                    str.append("0 ");
                }
            }
        }
        return str.toString();
    }

    public void setCells(){
        for (int row = 0; row < gridSize; row++) {
            for (int col = 0; col < gridSize; col++) {
                cells[row][col].setListener();
            }
        }
    }

    public int getGridSize() {
        return gridSize;
    }

    public Cell getCell(int row, int col) {
        return cells[row][col];
    }
}