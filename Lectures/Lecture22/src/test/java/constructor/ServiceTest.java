package constructor;

import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class ServiceTest {

    @Test
    void testGetUser() {
        Database db = mock(Database.class);

        when(db.findUser()).thenReturn("Giorgi");

        GoodUserService service = new GoodUserService(db);

        assertEquals("Giorgi", service.getUser());
    }
}