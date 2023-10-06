import org.junit.jupiter.api.*;
import shop.Cart;
import shop.RealItem;
import shop.VirtualItem;

import static org.junit.jupiter.api.Assertions.*;

public class CartTest {

    private Cart cart;

    @BeforeEach
    public void setUp() {
        // Create a new Cart instance before each test
        cart = new Cart("TestCart");
    }

    @AfterEach
    public void tearDown() {
        // Clean up resources or reset state after each test (if needed)
        cart = null;
    }

    @Test
    public void testAddRealItemToCart() {
        // Create a RealItem
        RealItem realItem = new RealItem();
        realItem.setName("Laptop");
        realItem.setPrice(1000.0);
        realItem.setWeight(2.5);

        // Add the RealItem to the Cart
        cart.addRealItem(realItem);

        // Get the total price of the Cart
        double totalPrice = cart.getTotalPrice();

        // Assert that the total price matches the expected price
        assertEquals(1200.0, totalPrice, 0.001); // Price with 20% tax
    }

    @Test
    public void testAddVirtualItemToCart() {
        // Create a VirtualItem
        VirtualItem virtualItem = new VirtualItem();
        virtualItem.setName("Software License");
        virtualItem.setPrice(99.99);
        virtualItem.setSizeOnDisk(50.0);

        // Add the VirtualItem to the Cart
        cart.addVirtualItem(virtualItem);

        // Get the total price of the Cart
        double totalPrice = cart.getTotalPrice();

        // Assert that the total price matches the expected price
        assertEquals(119.99, totalPrice, 0.001); // Price with 20% tax
    }
}
