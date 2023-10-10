import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import parser.JsonParser;
import shop.Cart;
import static org.junit.jupiter.api.Assertions.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.stream.Stream;

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

    }

    @ParameterizedTest
    @MethodSource("exceptionTestData")
    public void testReadFromFile(JsonParserTestData testData) {
        // Attempt to read from the provided file
        assertThrows(testData.getExpectedException(), () -> {
            jsonParser.readFromFile(testData.getFile());
        });
    }

    @ParameterizedTest
    @MethodSource("exceptionTestData")
    @Disabled // Disable this specific test
    public void testReadFromFileDisabled(JsonParserTestData testData) {
        // This test is disabled and won't be executed
    }


    static Stream<JsonParserTestData> exceptionTestData() {
        // Define test data with different datasets
        return Stream.of(
                new JsonParserTestData(null, NullPointerException.class),
                new JsonParserTestData(new File("nonexistent.json"), NoSuchFileException.class),
                new JsonParserTestData(createMalformedJsonFile(), RuntimeException.class),
                new JsonParserTestData(createEmptyJsonFile(), IllegalArgumentException.class),
                new JsonParserTestData(createNonCartJsonFile(), IllegalArgumentException.class)
        );
    }

    private static File createMalformedJsonFile() {
        File file = new File("malformed.json");
        FileWriter writer = null;
        try {
            writer = new FileWriter(file);
            writer.write("Malformed JSON Data");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeFileWriterSoftly(writer);
        }
        return file;
    }
    private static void closeFileWriterSoftly(FileWriter writer) {
        try {
            if (writer != null) writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static File createEmptyJsonFile() {
        File file = new File("empty.json");
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    private static File createNonCartJsonFile() {
        File file = new File("noncart.json");
        try {
            FileWriter writer = new FileWriter(file);
            writer.write("{\"name\":\"Item\",\"price\":10.0}");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    static class JsonParserTestData {
        private final File file;
        private final Class<? extends Throwable> expectedException;

        public JsonParserTestData(File file, Class<? extends Throwable> expectedException) {
            this.file = file;
            this.expectedException = expectedException;
        }

        public File getFile() {
            return file;
        }

        public Class<? extends Throwable> getExpectedException() {
            return expectedException;
        }
    }
}