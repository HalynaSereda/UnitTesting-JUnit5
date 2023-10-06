import org.junit.jupiter.api.*;
import shop.VirtualItem;
import static org.junit.jupiter.api.Assertions.*;

public class VirtualItemTest {

    private VirtualItem virtualItem;

    @BeforeEach
    public void setUp() {
        // Create a new VirtualItem instance before each test
        virtualItem = new VirtualItem();
    }

    @AfterEach
    public void tearDown() {
        // Clean up resources or reset state after each test (if needed)
        virtualItem = null;
    }

    @Test
    public void testSetSizeOnDiskAndGet() {
        // Set the size on disk using the setter method
        virtualItem.setSizeOnDisk(1024.5);

        // Get the size on disk using the getter method
        double sizeOnDisk = virtualItem.getSizeOnDisk();

        // Grouped assertion to check multiple conditions
        assertAll("VirtualItem size on disk",
                () -> assertEquals(1024.5, sizeOnDisk, 0.001), // Use delta for double comparisons
                () -> assertTrue(sizeOnDisk > 0, "Size on disk should be positive")
        );
    }
}
