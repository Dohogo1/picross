package test;
import org.junit.*;
import static org.junit.Assert.*;
import main.Grid;
import main.HintPanel;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class HintPanelUnitTest {
    private Grid grid;
    private HintPanel horizontalHintPanel;
    private HintPanel verticalHintPanel;

    @Before
    public void setUp() {
        int gridSize = 5;
        grid = new Grid(gridSize);
        horizontalHintPanel = new HintPanel(grid, true);
        verticalHintPanel = new HintPanel(grid, false);
    }

    @Test
    public void testInitialState() {
        assertNotNull("Horizontal hint panel should be initialized", horizontalHintPanel);
        assertNotNull("Vertical hint panel should be initialized", verticalHintPanel);

        assertEquals("Hint panel should match grid size", grid.getGridSize(), horizontalHintPanel.getHints().size());
        assertEquals("Hint panel should match grid size", grid.getGridSize(), verticalHintPanel.getHints().size());
    }

    @Test
    public void testMakeHintsEmptyGrid() {
        List<List<Integer>> expectedHints = new LinkedList<>();
        for (int i = 0; i < grid.getGridSize(); i++) {
            expectedHints.add(List.of(0)); // Empty grid should produce 0 as hints
        }

        assertEquals("Hints for an empty grid should be all zeros", expectedHints, horizontalHintPanel.getHints());
        assertEquals("Hints for an empty grid should be all zeros", expectedHints, verticalHintPanel.getHints());
    }

    @Test
    public void testMakeHintsFilledGrid() {
        // Fill some cells in the grid
        grid.getCell(0, 0).fill();
        grid.getCell(0, 1).fill();
        grid.getCell(1, 2).fill();
        grid.getCell(2, 4).fill();
        grid.getCell(2, 3).fill();

        horizontalHintPanel = new HintPanel(grid, true);
        verticalHintPanel = new HintPanel(grid, false);

        // Expected hints for horizontal rows
        List<List<Integer>> expectedHorizontalHints = Arrays.asList(
                List.of(2),        // Row 0: two filled cells
                List.of(1),        // Row 1: one filled cell
                List.of(2),        // Row 2: two filled cells
                List.of(0),        // Row 3: no filled cells
                List.of(0)         // Row 4: no filled cells
        );

        // Expected hints for vertical columns
        List<List<Integer>> expectedVerticalHints = Arrays.asList(
                List.of(1),        // Column 0: one filled cell
                List.of(1),        // Column 1: one filled cell
                List.of(1),        // Column 2: one filled cell
                List.of(1),        // Column 3: one filled cell
                List.of(1)         // Column 4: one filled cell
        );

        assertEquals("Horizontal hints should match the expected hints", expectedHorizontalHints, horizontalHintPanel.getHints());
        assertEquals("Vertical hints should match the expected hints", expectedVerticalHints, verticalHintPanel.getHints());
    }

    @Test
    public void testFormatHint() {
        List<Integer> hintList = List.of(1, 2, 3);
        String formattedHorizontalHint = "<html>1 2 3 ";
        String formattedVerticalHint = "<html>1<BR>2<BR>3";

        // Access private method using reflection
        try {
            var formatHintMethod = HintPanel.class.getDeclaredMethod("formatHint", List.class);
            formatHintMethod.setAccessible(true);

            String horizontalOutput = (String) formatHintMethod.invoke(horizontalHintPanel, hintList);
            String verticalOutput = (String) formatHintMethod.invoke(verticalHintPanel, hintList);

            assertEquals("Horizontal hint formatting should be correct", formattedHorizontalHint, horizontalOutput);
            assertEquals("Vertical hint formatting should be correct", formattedVerticalHint, verticalOutput);
        } catch (Exception e) {
            fail("Reflection error while testing formatHint: " + e.getMessage());
        }
    }

    @Test
    public void testHintPanelBackground() {
        assertEquals("Hint panel background should be light green", new Color(220, 255, 220), horizontalHintPanel.getBackground());
        assertEquals("Hint panel background should be light green", new Color(220, 255, 220), verticalHintPanel.getBackground());
    }

    
}