import shop.RealItem;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RealItemTest {

    private RealItem realItem;

    @BeforeEach
    public void setUp() {
        // Create a new RealItem instance before each test
        realItem = new RealItem();
    }

    @AfterEach
    public void tearDown() {
        // Clean up resources or reset state after each test (if needed)
        realItem = null;
    }

    @Test
    public void testSetNameAndGetWeight() {
        // Set the name and weight using the setter methods
        realItem.setName("Laptop");
        realItem.setWeight(2.5);

        // Use the getter methods to retrieve the values
        String itemName = realItem.getName();
        double itemWeight = realItem.getWeight();

        // Assert that the retrieved values match the expected values
        assertEquals("Laptop", itemName);
        assertEquals(2.5, itemWeight, 0.001); // Use delta for double comparisons
    }
}
