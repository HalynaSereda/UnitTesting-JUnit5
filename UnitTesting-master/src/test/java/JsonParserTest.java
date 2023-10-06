import org.junit.jupiter.api.*;
import parser.JsonParser;
import shop.Cart;
import static org.junit.jupiter.api.Assertions.*;
import java.io.File;

public class JsonParserTest {

    private JsonParser jsonParser;
    private final String testCartName = "TestCart";
    private final String testFileName = "src/main/resources/TestCart.json";

    @BeforeEach
    public void setUp() {
        // Create a JsonParser instance before each test
        jsonParser = new JsonParser();
    }

    @AfterEach
    public void tearDown() {
        // Clean up the test file after each test
        File testFile = new File(testFileName);
        if (testFile.exists()) {
            testFile.delete();
        }
    }

    @Test
    public void testWriteToFile() {
        // Create a Cart instance with a name
        Cart cart = new Cart(testCartName);

        // Call the writeToFile method
        jsonParser.writeToFile(cart);

        // Assert that the file was created
        File testFile = new File(testFileName);
        assertTrue(testFile.exists());

        // Clean up the test file after assertions
        testFile.delete();
    }

    @Test
    public void testReadFromFile() {
        // Create a Cart instance with a name
        Cart expectedCart = new Cart(testCartName);

        // Call the writeToFile method to create a test JSON file
        jsonParser.writeToFile(expectedCart);

        // Call the readFromFile method to read the test JSON file
        Cart readCart = jsonParser.readFromFile(new File(testFileName));

        // Assert that the readCart is not null and contains the same data as the expectedCart
        assertNotNull(readCart);
        assertEquals(expectedCart.getCartName(), readCart.getCartName());

        // Clean up the test file after assertions
        File testFile = new File(testFileName);
        if (testFile.exists()) {
            testFile.delete();
        }
    }
}
