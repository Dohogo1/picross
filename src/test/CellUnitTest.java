package test;
import org.junit.*;
import static org.junit.Assert.*;
import java.awt.*;
import main.Cell;

public class CellUnitTest {
    Cell cell;

    @Before
    public void setUp() {
        cell = new Cell();
    }

    @Test
    public void testInitialState() {
        assertFalse("Cell should not be filled initially", cell.isFilled());
        assertFalse("Cell should not be marked initially", cell.isMarked());
        assertEquals("Cell background should be white initially", Color.WHITE, cell.getBackground());
    }

    @Test
    public void testFillCell() {
        cell.fill();
        assertTrue("Cell should be filled after calling fill()", cell.isFilled());
        assertFalse("Cell should not be marked after calling fill()", cell.isMarked());
        assertEquals("Cell background should be black after calling fill()", Color.BLACK, cell.getBackground());
    }

    @Test
    public void testUnfillCell() {
        cell.fill(); // First fill
        cell.fill(); // Second fill to unfill
        assertFalse("Cell should not be filled after calling fill() twice", cell.isFilled());
        assertEquals("Cell background should be white after calling fill() twice", Color.WHITE, cell.getBackground());
    }

    @Test
    public void testMarkCell() {
        cell.mark();
        assertTrue("Cell should be marked after calling mark()", cell.isMarked());
        assertFalse("Cell should not be filled after calling mark()", cell.isFilled());
        assertEquals("Cell background should remain white after calling mark()", Color.WHITE, cell.getBackground());
        assertEquals("Cell text should be 'X' after calling mark()", "X", cell.getText());
    }

    @Test
    public void testUnmarkCell() {
        cell.mark(); // First mark
        cell.mark(); // Second mark to unmark
        assertFalse("Cell should not be marked after calling mark() twice", cell.isMarked());
        assertEquals("Cell background should remain white after calling mark() twice", Color.WHITE, cell.getBackground());
        assertNull("Cell text should be null after calling mark() twice", cell.getText());
    }

    @Test
    public void testFillAfterMark() {
        cell.mark();
        cell.fill();
        assertTrue("Cell should be filled after marking and then calling fill()", cell.isFilled());
        assertFalse("Cell should not be marked after marking and then calling fill()", cell.isMarked());
        assertEquals("Cell background should be black after calling fill()", Color.BLACK, cell.getBackground());
        assertNull("Cell text should be null after calling fill()", cell.getText());
    }

    @Test
    public void testMarkAfterFill() {
        cell.fill();
        cell.mark();
        assertTrue("Cell should be marked after filling and then calling mark()", cell.isMarked());
        assertFalse("Cell should not be filled after filling and then calling mark()", cell.isFilled());
        assertEquals("Cell background should return to white after calling mark()", Color.WHITE, cell.getBackground());
        assertEquals("Cell text should be 'X' after calling mark()", "X", cell.getText());
    }
}