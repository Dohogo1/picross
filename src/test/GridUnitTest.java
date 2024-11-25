package test;
import org.junit.*;
import static org.junit.Assert.*;
import main.Grid;
import main.Cell;

public class GridUnitTest {
    private Grid grid;
    private final int gridSize = 5;

    @Before
    public void setUp() {
        grid = new Grid(gridSize);
    }

    @Test
    public void testInitialState() {
        assertEquals("Grid size should be initialized correctly", gridSize, grid.getGridSize());
        for (int row = 0; row < gridSize; row++) {
            for (int col = 0; col < gridSize; col++) {
                assertFalse("All cells should initially not be filled", grid.getCell(row, col).isFilled());
                assertFalse("All cells should initially not be marked", grid.getCell(row, col).isMarked());
            }
        }
    }

    @Test
    public void testRandomizeGrid() {
        grid.randomizeGrid();
        boolean atLeastOneFilled = false;
        for (int row = 0; row < gridSize; row++) {
            for (int col = 0; col < gridSize; col++) {
                if (grid.getCell(row, col).isFilled()) {
                    atLeastOneFilled = true;
                    break;
                }
            }
        }
        assertTrue("At least one cell should be filled after randomizeGrid()", atLeastOneFilled);
    }

    @Test
    public void testGetRow() {
        int rowIndex = 2;
        Cell[] row = grid.getRow(rowIndex);
        assertEquals("Row should have correct number of cells", gridSize, row.length);
        for (int col = 0; col < gridSize; col++) {
            assertEquals("Row cells should match the grid's cells", grid.getCell(rowIndex, col), row[col]);
        }
    }

    @Test
    public void testGetCol() {
        int colIndex = 3;
        Cell[] col = grid.getCol(colIndex);
        assertEquals("Column should have correct number of cells", gridSize, col.length);
        for (int row = 0; row < gridSize; row++) {
            assertEquals("Column cells should match the grid's cells", grid.getCell(row, colIndex), col[row]);
        }
    }

    @Test
    public void testSetCells() {
        grid.setCells();
        for (int row = 0; row < gridSize; row++) {
            for (int col = 0; col < gridSize; col++) {
                assertNotNull("Each cell should have a listener set", grid.getCell(row, col).getMouseListeners());
            }
        }
    }
}

