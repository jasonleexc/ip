package borat.exception;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for BoratExceptions
 */
public class BoratExceptionsTest {

    @Test
    @DisplayName("Test BoratExceptions creation with message")
    void testBoratExceptionsCreation() {
        String errorMessage = "Test error message";
        BoratExceptions exception = new BoratExceptions(errorMessage);
        
        assertNotNull(exception);
        assertEquals(errorMessage, exception.getMessage());
    }

    @Test
    @DisplayName("Test BoratExceptions with empty message")
    void testBoratExceptionsWithEmptyMessage() {
        BoratExceptions exception = new BoratExceptions("");
        
        assertNotNull(exception);
        assertEquals("", exception.getMessage());
    }

    @Test
    @DisplayName("Test BoratExceptions with null message")
    void testBoratExceptionsWithNullMessage() {
        BoratExceptions exception = new BoratExceptions(null);
        
        assertNotNull(exception);
        assertNull(exception.getMessage());
    }

    @Test
    @DisplayName("Test BoratExceptions with special characters")
    void testBoratExceptionsWithSpecialCharacters() {
        String specialMessage = "Error with special chars: !@#$%^&*()";
        BoratExceptions exception = new BoratExceptions(specialMessage);
        
        assertEquals(specialMessage, exception.getMessage());
    }

    @Test
    @DisplayName("Test BoratExceptions can be thrown and caught")
    void testBoratExceptionsThrowing() {
        String errorMessage = "Test exception";
        
        try {
            throw new BoratExceptions(errorMessage);
        } catch (BoratExceptions e) {
            assertEquals(errorMessage, e.getMessage());
        }
    }
}
